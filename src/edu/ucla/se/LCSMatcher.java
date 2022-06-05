package edu.ucla.se;

import java.nio.file.Path;
import edu.ucla.se.utils.Config;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.HashSet;

public class LCSMatcher {
    public static Map<String, Map<Integer, List<MissingChangeInfo>>> matchLCS(GitHandler gitHandler,
                                                                HashMap<Integer, HashMap<String, List<List<Integer>>>> groups)
                                                                throws IOException {
        Map<String, Map<Integer, List<MissingChangeInfo>>> rs = new HashMap<>();

        for (int curGroup : groups.keySet()) {
            Map<String, List<MissingChangeInfo>> curGroupRs = matchSingleGroup(gitHandler, groups.get(curGroup));
            for (Map.Entry<String, List<MissingChangeInfo>> entry : curGroupRs.entrySet()) {
                if (entry.getValue().size() == 0) continue;
                rs.computeIfAbsent(entry.getKey(), k -> new HashMap<>()).put(curGroup, entry.getValue());
            }
        }

        return rs;
    }

    private static Map<String, List<MissingChangeInfo>> matchSingleGroup(GitHandler gitHandler,
                                                                          Map<String, List<List<Integer>>> curGroup) throws IOException {
        Map<String, Map<Integer, String>> dict = gitHandler.getOldFileContentByLine(curGroup);
        ArrayList<String> oldContentsCode = new ArrayList<>();
        HashSet<String> fileSet = new HashSet<>();
        for (Map.Entry f: curGroup.entrySet()){        // file
            String fileName = (String)f.getKey();
            fileSet.add(gitHandler.repoPath+"/"+fileName);
            if (!dict.containsKey(fileName)){
                continue;
            }
            Map<Integer, String> fileCodeDict = dict.get(fileName);
            for (int i = 0; i < curGroup.get(fileName).size(); ++i){   // code snippet
                String code = "";
                for (int j = 0; j < curGroup.get(fileName).get(i).size(); j++) {    // lines of code
                    Integer idx = curGroup.get(fileName).get(i).get(j);
                    //code += ";";       // new line
                    if (!fileCodeDict.containsKey(idx)){
                        continue;
                    }
                    if (fileCodeDict.get(idx).length() > 0){
                        code += fileCodeDict.get(idx);
                    }
                }
                oldContentsCode.add(code);
            }
        }

        for (String s : oldContentsCode) {
            System.out.println("code snippet");
            System.out.println(s);
        }
        LCSScoreComputer sc = new LCSScoreComputer();
        PEAM peam = new PEAM(sc);

        System.out.println("Start Finding Patterns...");
        peam.FindFrequentPattern(oldContentsCode, 
        		fileSet, 
        		Config.SIM_SCORE_THRESH, 
        		Config.MIN_SUP_RATIO,
        		Config.MIN_UNIQUE_RATIO);
        peam.PrintPatterns();
        System.out.println("Done");

        //Path p = Paths.get("D:\\2021-2022IMPORTANT\\cs230\\test-cases\\debug\\to-match.java");
        //Map<String, List<MissingChangeInfo>> ret = peam.RecursiveFindMatch(p, 4, 2, 2, Config.MATCH_SCORE);
        //System.out.println("Match Finish");
		/*for(Map.Entry<String, List<MissingChangeInfo>> entry : ret.entrySet()) {
			if (entry.getValue().size() == 0) continue;
			System.out.println(entry.getKey());
			for(MissingChangeInfo m : entry.getValue()) {
				System.out.printf("%d->%d\n", m.startLine, m.endLine);
			}
		}
		System.out.println(sc.getScore("if (((c1 = Character.getNumericValue(this.source[this.currentPosition++])) > 15 || c1 < 0) || ((c2 = Character.getNumericValue(this.source[this.currentPosition++])) > 15 || c2 < 0) || ((c3 = Character.getNumericValue(this.source[this.currentPosition++])) > 15 || c3 < 0) || ((c4 = Character.getNumericValue(this.source[this.currentPosition++])) > 15 || c4 < 0))", 
				"if ((c1 = Character.getNumericValue(this.source[this.currentPosition++])) > 15 || c1 < 0 || (c2 = Character.getNumericValue(this.source[this.currentPosition++])) > 15 || c2 < 0 || (c3 = Character.getNumericValue(this.source[this.currentPosition++])) > 15 || c3 < 0 || (c4 = Character.getNumericValue(this.source[this.currentPosition++])) > 15 || c4 < 0)\n"));
		
        System.exit(-1);*/
        int pattern_cnt = peam.GetPatternCnt();

        return gitHandler.matchLCS(peam,4,
                                   2,
                                   2, Config.MATCH_SCORE);
    }
}
