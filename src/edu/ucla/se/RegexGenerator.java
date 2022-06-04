package edu.ucla.se;

import java.util.*;


public class RegexGenerator {
    /* Input object */
    HashMap<Integer, HashMap<String, List<List<Integer>>>> grouping;      // group_num -> (filename: [line_changed])
    // a b c d
    // - - -      1
    // - - -      1
    //
    // - -   -    2
    //
    //{ 1:
    //      {
    //          a.java: [[3,7,8],[0,1,2]], // being deleted
    //      }
    // }
    // {2:
    //       {
    //          b.java: [[1,2,3], [6,7,8]]
    //       }
    // }

    Map<String, Map<Integer, String>>  dict;                // filename -> (line_num: code)
    GitHandler gitHandler;

    public RegexGenerator(HashMap<Integer, HashMap<String, List<List<Integer>>>> _grouping, Map<String,
            Map<Integer, String>> _dict){
        this.grouping = _grouping;
        this.dict = _dict;
    }

    public RegexGenerator(HashMap<Integer, HashMap<String, List<List<Integer>>>> grouping, GitHandler gitHandler) {
        this.grouping = grouping;
        this.gitHandler = gitHandler;
    }

    public HashMap<String, List<List<Integer>>> cleanSnippet(HashMap<String, List<List<Integer>>> files) {
        HashMap<String, List<List<Integer>>> ret = new HashMap<>();
        for (Map.Entry f : files.entrySet()) {
            String fileName = (String)f.getKey();
            HashMap<Integer, Integer> lens = new HashMap<>();
            for (List<Integer> code : files.get(fileName)){
                if (lens.containsKey(code.size())){
                    lens.put(code.size(), lens.get(code.size()) + 1);
                }else{
                    lens.put(code.size(), 1);
                }
            }
            Integer commonLength = 0;
            int mostCount = 0;
            for (Integer len : lens.keySet()){
                if (lens.get(len) > mostCount){
                    commonLength = len;
                    mostCount = lens.get(len);
                }
            }
            List<List<Integer>> cleaned = new ArrayList<>();
            for (List<Integer> code : files.get(fileName)){
                if (code.size() == commonLength){
                    cleaned.add(code);
                }
            }
            ret.put(fileName, cleaned);
        }
        return ret;
    }

    /**
     *
     * @return: HashMap (group -> [[code snippet1],[code snippet2]])
     * */
    public HashMap<Integer,ArrayList<String>> getCodeSnippet(){
        HashMap<Integer,ArrayList<String>> ret = new HashMap<>();

        for (Integer g : grouping.keySet()) {           // group
            HashMap<String, List<List<Integer>>> files = grouping.get(g);

            // remove snippets with different length
            files= cleanSnippet(files);
            this.dict = gitHandler.getOldFileContentByLine(files);
            ArrayList<String> val = new ArrayList<>();


            for (Map.Entry f: files.entrySet()){        // file
                String fileName = (String)f.getKey();
                if (!dict.containsKey(fileName)){
                    continue;
                }
                Map<Integer, String> fileCodeDict = dict.get(fileName);
                for (int i = 0; i < files.get(fileName).size(); ++i){   // code snippet
                    String code = "";
                    for (int j = 0; j < files.get(fileName).get(i).size(); j++) {    // lines of code
                        Integer idx = files.get(fileName).get(i).get(j);
                        if (j != 0){
                            code += "#;";       // new line
                        }
                        if (!fileCodeDict.containsKey(idx)){
                            continue;
                        }
                        if (fileCodeDict.get(idx).length() > 0){
                            code += fileCodeDict.get(idx);
                        }
                    }
                    val.add(code);
                }
            }
            ret.put(g, val);
        }
        return ret;
    }

    /**
     * @input: a single line code of function call
     * @return: add funcstart label, argument count
     * **/
    public ArrayList<String> processFunction(String func){
        ArrayList<String> ret = new ArrayList<>();
        if (func.trim().charAt(0) == '('){
            return ret;
        }
        Integer args = 1;
        for (int i = 0; i < func.length(); i++){
            if (func.charAt(i) == '(' && i+1 < func.length() && func.charAt(i+1) == ')'){
                args = 0;
                break;
            }
            if (func.charAt(i) == ',') args++;
        }
        String [] raw = func.split(",|\\(|\\)");
        ret.add("funcstart");
        ret.add(args.toString());

        ret.add(raw[0]);
        return ret;
    }

    public String generator(ArrayList<String> tokens){
        String unit = ".*"; //  or [^0-9a-ZA-Z\_]*;
        String regex = "";
        char [] escape = {'*', '.', '?', '+', '$', '^', '|', '\\', '/'};
        for (int i = 0; i < tokens.size(); ++i){
            String s = tokens.get(i);
            if (s.length() == 0){
                continue;
            }
            if(s.charAt(0) == '.'){
                regex += "\\";
                regex += s;
            }else if (s.equals("funcstart")){
                if (i+2 >= tokens.size()) {
                    i += 2;
                    continue;
                }
                if (tokens.get(i+2).length() <= 0){
                    i += 2;
                    continue;
                }
                Integer argsNum = 0;
                try{
                    argsNum = Integer.parseInt(tokens.get(i+1));
                } catch (NumberFormatException err){
                    i += 2;
                    continue;
                }

                regex += unit;
                if (tokens.get(i+2).equals("elseif")){
                    regex = regex + "else\\sif.*";
                }else{
                    if (tokens.get(i+2).charAt(0) == '.'){
                        regex += "\\";
                    }
                    regex += tokens.get(i+2);
                    regex += unit;
                }
                regex += "\\(";
                for (int j = 0; j < argsNum; ++j){
                    regex += unit;
                    if (j != argsNum-1) regex += ",";
                }
                regex += "\\)";
                regex += unit;
                i += 2;
            }else if (s.equals("#")){
                regex += unit;
                regex += "\\n";
                regex += unit;
            }else if (new String(escape).indexOf(s.charAt(0)) != -1){
                regex += unit;
                regex += "\\";
                regex += s.charAt(0);
                regex += unit;
            }
            else{
                regex += unit;
                regex += s;
                regex += unit;
            }
        }
        regex += unit;
        return regex;
    }

    public ArrayList<String> findRegex(ArrayList<String> codes){
        // input: ["event.item = item;sendEvent(true, event);", "ev.item = item;sendEvent(true, ev);"]
        // parse: ["event", ".item", "=item", "funcstart", "2", "sendEvent"]
        // output ".*\\.item=.*sendEvent(.*,.*)"
        // output "(\\S*)\.item=(\\S*)sendEvent(true,\\1)"

//        System.out.println(codes);

        String delimiter = "((?=\\.|=|\\+|-|\\*|/|%|\\^|<|>|<=|>=)|(;|\\{|\\})|(?<=\\+|-|\\*|/|%|\\^|<|>|<=|>=))";
        ArrayList<ArrayList<String>> tokens = new ArrayList<>();
        int shortest = 0;
        // Step1: split
        for (int i = 0; i < codes.size(); ++i){
            String striped = codes.get(i);
            ArrayList<String> raw = new ArrayList<>(Arrays.asList(striped.split(delimiter,0)));
            ArrayList<String> token = new ArrayList<>();
            for (int j = 0; j < raw.size(); ++j){
                if (raw.get(j).contains("(") && raw.get(j).contains(")")){
                    ArrayList<String> processed = processFunction(raw.get(j));
                    for (String p: processed){
                        token.add(p.trim());
                    }
                }else if (raw.get(j).contains("(")){        // extract possible function name
                    token.add(raw.get(j).substring(0,raw.get(j).indexOf("(")).trim());
                }else if (raw.get(j).contains(")")){
                    continue;
                }else{
                    token.add(raw.get(j).trim());   // .replaceAll("[\\s\\n]*","")
                }
            }
            tokens.add(token);
        }
        System.out.println(tokens);

        //Step2: union set
        double threshold = 1;
        double currentMax = Double.POSITIVE_INFINITY;
        ArrayList<ArrayList<String>> maximum = new ArrayList<>();
        for (ArrayList<String> based: tokens){
//            System.out.printf("Current base: %s\n", based);
            ArrayList<String> previous = (ArrayList<String>) based.clone();
            ArrayList<String> temp = (ArrayList<String>) based.clone();
            for (ArrayList<String> other: tokens){
                temp.retainAll(other);
//                System.out.printf("Current temp: %s\n", temp);
                if (temp.size() < based.size() * threshold){
                    temp = (ArrayList<String>)previous.clone();
                }else{
                    previous = (ArrayList<String>)temp.clone();
                }
            }
            if (previous.size() == currentMax){
//                System.out.printf("Final output for one base: %s\n", previous);
                maximum.add(previous);
            }else if (previous.size() < currentMax){
                maximum.clear();
                maximum.add(previous);
                currentMax = previous.size();
            }
        }
        System.out.printf("Union result: %s\n", maximum);

        ArrayList<String> regexSet = new ArrayList<>();
        for (ArrayList<String> regexToken: maximum){
            String regex = generator(regexToken);
            regex = regex.replaceAll("[\\s,]+",".*");   // replace all ',' and ' ' with .*
            if (!regexSet.contains(regex)){
                regexSet.add(regex);
            }
        }

        return regexSet;
    }

    public ArrayList<String> generateRegex(){
        HashMap<Integer,ArrayList<String>> codeSnippet = getCodeSnippet();
        System.out.println(codeSnippet);
        ArrayList<String> ret = new ArrayList<>();

        for (Integer g: codeSnippet.keySet()){
            ArrayList<String> codes = codeSnippet.get(g);
            ArrayList<String> patternSet = findRegex(codes);
            for (String pattern : patternSet){
                pattern = pattern.replaceAll("\\[", "\\\\[");
                pattern = pattern.replaceAll("\\]", "\\\\]");
                pattern = pattern.replaceAll("(\\.\\*){2,}", ".*");
                String _check = pattern.replaceAll("\\.\\*","");
                String _check2 = pattern.replaceAll("\\w", "");
                if (_check.length() != 0 && _check2.length() != pattern.length()){
                    ret.add(pattern);
                }else{
                    ret.add(null);
                }
            }
        }

        return ret;
    }

}
