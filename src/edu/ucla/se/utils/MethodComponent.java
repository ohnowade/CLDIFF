package edu.ucla.se.utils;

import java.util.*;

public class MethodComponent {
    private List<Integer> changeItems;
    public List<Integer> changeIndex; // the list of links within each function group
    public List<Integer> roots;       // the roots of changedIndex
    private String description;
    public String functionName;
    public List<Integer> lineNumbers;
    public HashSet<Integer> changeScope;
    public List<Integer> changeScopeList;
    public UnionFind uf;               // call api here to find unionId

    public MethodComponent(UnionFind uf) {
        changeItems = new ArrayList<>();
        changeIndex = new ArrayList<>();
        lineNumbers = new ArrayList<>();
        changeScopeList = new ArrayList<>();
        functionName = "";
        description = "";
        roots = new ArrayList<>();
        changeScope = new HashSet<>();
        this.uf = uf;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public void addLineScope(String s1, String s2){
        if (!s1.equals(s2)) {
            return;
        }
        int begin = Integer.parseInt(s1);
        int end = Integer.parseInt(s2);

        for (int i = begin; i <= end; i ++) {
            this.changeScope.add(i);
        }
    }

    public void calulateChangeScopeList() {
        this.changeScopeList.addAll(this.changeScope);
        Collections.sort(changeScopeList);
    }


    public void calculateRoots() {
        for (Integer s : changeIndex) {
            roots.add(uf.find(s));
        }
    }

    public List<Integer> getRoots() {
        return roots;
    }

    public List<Integer> getChangeIndex() {
        return changeIndex;
    }

    public String getDescription() {
        return description;
    }

    public void addChangeIndex(int idx) {
        changeIndex.add(idx);
    }

//    public void addDescription(List<String> words) {
//        description = String.join(" ", words);
//    }
//
//    public void addChangeItems(List<String> words) {
//        String result = String.join(" ", words);
//        changeItems.add(result);
//        changeIndex.add(words.get(0));
//    }



}
