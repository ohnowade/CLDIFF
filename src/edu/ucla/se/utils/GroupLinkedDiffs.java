package edu.ucla.se.utils;

import com.github.gumtreediff.utils.Pair;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Function;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


// m1, m2, m3
// 0   3   0
// 1   0   1
// 3   1   3
// 0
// 1

public class GroupLinkedDiffs {
    private List<List<Integer>> methodLinkedEdits;
    private final HashMap<Integer, HashMap<String, ArrayList<Integer>>> group2Loc = new HashMap<>();
    private HashMap<Integer, List<Integer>> linkSetOrders = new HashMap<>();

    private Set<Integer> editSetIds;
    private int maxMethodEditLen;

    public GroupLinkedDiffs(List<List<Integer>> methodlinkedEdits){
        this.methodLinkedEdits = methodlinkedEdits;
        this.populateGroup2Locations(methodlinkedEdits);
    }

    public static HashMap<Integer, Double> sortByValue(HashMap<Integer, Double> hm)
    {
        HashMap<Integer, Double> temp
                = hm.entrySet()
                .stream()
                .sorted((i1, i2)
                        -> i1.getValue().compareTo(
                        i2.getValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));

        return temp;
    }

    private List<Integer> sortLinkSets(){
        HashMap<Integer, Double> setPriorities = new HashMap<>();
        for (Integer set_id : linkSetOrders.keySet()){
            double avg_order = linkSetOrders.get(set_id).stream()
                   .mapToDouble(d -> d)
                   .average()
                   .orElse(0.0);
            setPriorities.put(set_id, avg_order);
        }
        return new ArrayList<> (sortByValue(setPriorities).keySet());

    }

    private void populateGroup2Locations(List<List<Integer>> methodLinkedEdits){
        this.editSetIds = new HashSet<>();
        this.maxMethodEditLen = 0;
        for (int i= methodLinkedEdits.size() - 1; i >= 0 ; i--){
            if (methodLinkedEdits.get(i).isEmpty()){
                methodLinkedEdits.remove(i);
            }
        }
        for (int i=0; i< methodLinkedEdits.size(); i++){
            int methodEditLen = methodLinkedEdits.get(i).size();
            if (maxMethodEditLen < methodEditLen){
                maxMethodEditLen = methodEditLen;
            }
            for (int j = 0; j< methodEditLen; j++) {
                Integer group_i = methodLinkedEdits.get(i).get(j);

                if (!group2Loc.containsKey(group_i)){
                    group2Loc.put(group_i, new HashMap<> ());
                    this.linkSetOrders.put(group_i, new ArrayList<>());
                }
                String[] loc_types = new String[] {"method", "index"};
                for (String type : loc_types) {
                    if (!group2Loc.get(group_i).containsKey(type)) {
                        group2Loc.get(group_i).put(type, new ArrayList<>());
                    }
                }

                group2Loc.get(group_i).get("method").add(i);
                group2Loc.get(group_i).get("index").add(j);

                this.linkSetOrders.get(group_i).add(j);

                this.editSetIds.add(group_i);
            }
        }
    }

    private int getLinkSetAt(int method_i,  int index_j){
        if (method_i < this.methodLinkedEdits.size() && index_j < this.methodLinkedEdits.get(method_i).size()){
            return this.methodLinkedEdits.get(method_i).get(index_j);
        }
        else{
            return -1;
        }
    }

    private List<Integer> getLinkSetAt(int method_i, int index_j_from,  int index_j_to){

        int j_size = this.methodLinkedEdits.get(method_i).size();
        if (method_i < this.methodLinkedEdits.size() && index_j_from < j_size && index_j_to <= j_size){
            return this.methodLinkedEdits.get(method_i).subList(index_j_from, index_j_to);
        }
        else{
            return null;
        }
    }

    public HashMap<List<Integer>, Set<List<Integer>>> getLinkedStmtGroups(){
        HashMap<List<Integer>, Set<List<Integer>>> resultEditGroups = new HashMap<> ();

        Set<Pair<Integer, Integer>> visitedLocs = new HashSet<>();

        List<Integer> sortLinkSets = sortLinkSets();

        for (Integer set_i : sortLinkSets){

            List<Integer> method_locs = group2Loc.get(set_i).get("method");
            List<Integer> init_locs = group2Loc.get(set_i).get("index");
            List<Integer> cur_locs = new ArrayList<>();
            cur_locs.addAll(init_locs);

            Set<Integer> chosenLocInds = new HashSet<>(IntStream.range(0, init_locs.size()).boxed().
                    collect(Collectors.toList()));
            HashMap<List<Integer>, List<List<Integer>>> group_index_intervals = new HashMap<> ();
            for (int j = 1; j < this.maxMethodEditLen+2; j++) {
                if (chosenLocInds.isEmpty()){
                    break;
                }

                for (int k = 0 ; k < cur_locs.size(); k++){
                    cur_locs.set(k, cur_locs.get(k) + 1);
                }

                ArrayList<Integer> group_vals = new ArrayList<>(IntStream.range(0, cur_locs.size())
                        .mapToObj(k -> this.getLinkSetAt(method_locs.get(k), cur_locs.get(k)))
                        .collect(Collectors.toList()));


                HashMap<List<Integer>, List<Integer>> group_locations = new HashMap<> ();
                HashMap<List<Integer>, List<Integer>> null_group_locations = new HashMap<> ();
                group_index_intervals = new HashMap<> ();
                HashMap<List<Integer>, List<List<Integer>>> null_group_index_intervals = new HashMap<> ();

                for (Integer loc_i : chosenLocInds){
                    Integer method_loc = method_locs.get(loc_i);
                    Integer init_loc = init_locs.get(loc_i);
                    Integer cur_loc = cur_locs.get(loc_i);

                    List<Integer> editList = this.getLinkSetAt(method_loc, init_loc, cur_loc);

                    boolean visited_position = false;
                    for (int k = init_loc; k < cur_loc; k++){
                        if (visitedLocs.contains(new Pair<>(method_loc, k))){
                            visited_position = true;
                        }
                    }
                    if (visited_position) {
                        continue;
                    }

                    if (editList != null) {
                        if ( !group_index_intervals.containsKey(editList) ){
                            group_index_intervals.put(editList, new ArrayList<>());
                        }
                        group_index_intervals.get(editList).add(new ArrayList<>(
                                Arrays.asList(method_loc, init_loc, cur_loc)));

                        if ( !group_locations.containsKey(editList)){
                            group_locations.put(editList, new ArrayList<>());
                        }
                        group_locations.get(editList).add(loc_i);
                    }
                    else{
                        cur_loc --;
                        editList = this.getLinkSetAt(method_loc, init_loc, cur_loc);
                        if ( !null_group_index_intervals.containsKey(editList) ){
                            null_group_index_intervals.put(editList, new ArrayList<>());
                        }
                        null_group_index_intervals.get(editList).add(new ArrayList<>(
                                Arrays.asList(method_loc, init_loc, cur_loc)));

                        if ( !null_group_locations.containsKey(editList)){
                            null_group_locations.put(editList, new ArrayList<>());
                        }
                        null_group_locations.get(editList).add(loc_i);
                    }

                }

                chosenLocInds = new HashSet<>();
                for (List<Integer> editList : group_locations.keySet()){
                    if (group_locations.get(editList).size() > 1){
                        chosenLocInds.addAll(group_locations.get(editList));

                    }
                    else {
                        List<List<Integer>> curLocs = group_index_intervals.get(editList);
                        for (int k=0; k < curLocs.size(); k++){
                            List<Integer> curGroupEditList = curLocs.get(k);
                            if (curGroupEditList.get(2) - curGroupEditList.get(1) > 1){
                                curGroupEditList.set(2, curGroupEditList.get(2) - 1);
                            }
                        }

                        if (editList.size() > 1) {
                            editList = editList.subList(0, editList.size() - 1);
                        }

                        if (!resultEditGroups.containsKey(editList)){
                            resultEditGroups.put(editList, new HashSet<>());
                        }
                        resultEditGroups.get(editList).addAll(curLocs);
                        for (List<Integer> loc : curLocs){
                            for (int k = loc.get(1); k < loc.get(2); k++ ){
                                visitedLocs.add(new Pair<>(loc.get(0), k));
                            }
                        }
                    }
                }
                for (List<Integer> editList : null_group_locations.keySet()){
                    List<List<Integer>> curLocs = null_group_index_intervals.get(editList);

                    if (!resultEditGroups.containsKey(editList)){
                        resultEditGroups.put(editList, new HashSet<>());
                    }
                    resultEditGroups.get(editList).addAll(curLocs);
                    for (List<Integer> loc : curLocs){
                        for (int k = loc.get(1); k < loc.get(2); k++ ){
                            visitedLocs.add(new Pair<>(loc.get(0), k));
                        }
                    }
                }

            }
        }
        System.out.println(resultEditGroups);
        return resultEditGroups;

    }

}
