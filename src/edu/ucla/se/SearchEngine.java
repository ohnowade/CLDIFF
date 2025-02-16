package edu.ucla.se;

import edu.fdu.se.cldiff.CLDiffLocal;
import edu.ucla.se.utils.Config;
import edu.ucla.se.utils.ParserHelper;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;

public class SearchEngine {
    private String repoPath;
    private String repoName;
    private String commitId;

    private GitHandler gitHandler;
    private boolean highlevelGroupFLAG;

    public SearchEngine(String repoPath, String commitId, P_LANG lang, boolean highlevelGroupFLAG) {
        this.repoPath = repoPath.substring(0, repoPath.length() - 5);
        this.gitHandler = new GitHandler(repoPath, commitId, lang);
        this.highlevelGroupFLAG = highlevelGroupFLAG;
    }

    public SearchEngine(String oldPath, String newPath, String repoName, P_LANG lang, boolean highlevelGroupFLAG) {
        oldPath = Paths.get(oldPath).toString();
        newPath = Paths.get(newPath).toString();
        GitCreator gitCreator = new GitCreator();
        gitCreator.deleteRepo(repoName);
        gitCreator.createNewRepo(repoName);
        this.repoPath = gitCreator.getRepoPath(repoName);
        gitCreator.commitFilesToRepo(repoName, oldPath);
        String commitId = gitCreator.commitFilesToRepo(repoName, newPath);
        this.repoName = repoName;
        this.commitId = commitId;
        this.gitHandler = new GitHandler(gitCreator.getRepo(repoName), repoName, commitId, lang);
        this.highlevelGroupFLAG = highlevelGroupFLAG;
    }

    public void run() {
        try {
            //====================== CLEAR PREVIOUSLY GENERATED FILES ============================
            File outputDir = new File(Paths.get(Config.CLDIFF_OUTPUT_PATH, repoName).toString());
            FileUtils.deleteDirectory(outputDir);
            System.out.println();

            //=============================== RUN CLDIFF =========================================
            System.out.println("=========================== Start Running CLDiff ==============================");
            CLDiffLocal clDiffLocal = new CLDiffLocal();
            clDiffLocal.run(commitId, Paths.get(repoPath, ".git").toString(), Config.CLDIFF_OUTPUT_PATH);
            System.out.println();

            if (Config.OUTPUT_TO_FILE) {
                String filePath = Paths.get(Config.CLDIFF_OUTPUT_PATH, String.format("search_%s_output.txt", repoName))
                                       .toString();
                File file = new File(filePath);
                if (file.exists()) file.delete();
                file.createNewFile();
                System.setOut(new PrintStream(new FileOutputStream(file)));
            }


            //========================== STEP 1: GET GROUPING INFO ===============================
            System.out.println("========================== Getting Grouping Info ===============================");
            HashMap<Integer, HashMap<String, List<List<Integer>>>> groups = ParserHelper.getChangeGroups(
                    repoName, commitId, this.highlevelGroupFLAG);
            System.out.println();

            //============== STEP 2.1: GENERATE REGEX AND MATCH IN CURRENT COMMIT ================

            System.out.println("============== Generating and Matching Regex in Current Commit =================");
            Map<Integer, List<String>> regex = new RegexGenerator(groups, this.gitHandler).generateRegex();
            Map<String, Map<Integer, List<MissingChangeInfo>>> regexResults = gitHandler.matchRegex(regex);
            System.out.println("Results found by Regex:");
            gitHandler.printResult(regexResults);
            System.out.println();

            //=================== STEP 2.2: LCS MATCH IN CURRENT COMMIT ==========================
            System.out.println("================== Applying LCS Matching in Current Commit =====================");
            System.out.printf("sim_score_thresh = %f\nmin_sup_ratio = %f\nmatch_pattern_ratio = %f\nmatch_score = %f\n",
                                Config.SIM_SCORE_THRESH, Config.MIN_SUP_RATIO, Config.MATCH_PATTERN_RATIO, Config.MATCH_SCORE);
            Map<String, Map<Integer, List<MissingChangeInfo>>> LCSResults = LCSMatcher.matchLCS(gitHandler, groups);
            System.out.println("Results found by LCS:");
            gitHandler.printSimpleResult(LCSResults);
            System.out.println();


            //======================== STEP 3: COMBINE MATCHING OUTPUT ===========================
            System.out.println("======================== Combining Matching Results ============================");
            Map<String, Map<Integer, List<MissingChangeInfo>>> results = mergeResults(regexResults, LCSResults);

            System.out.println(results);
            gitHandler.printResult(results);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private Map<String, Map<Integer, List<MissingChangeInfo>>> mergeResults (Map<String, Map<Integer, List<MissingChangeInfo>>> rs1,
                                                                             Map<String, Map<Integer, List<MissingChangeInfo>>> rs2) {
        Map<String, Map<Integer, List<MissingChangeInfo>>> rs = new HashMap<>();

        for (Map.Entry<String, Map<Integer, List<MissingChangeInfo>>> entry : rs1.entrySet()) {
            String fileName = entry.getKey();
            Map<Integer, List<MissingChangeInfo>> changeInfo1 = entry.getValue();

            if (!rs2.containsKey(fileName)) {
                rs.put(fileName, changeInfo1);
                continue;
            }

            Map<Integer, List<MissingChangeInfo>> changeInfo2 = rs2.get(fileName);
            Map<Integer, List<MissingChangeInfo>> mergedInfo = new HashMap<>();

            for (int groupId : changeInfo1.keySet()) {
                List<MissingChangeInfo> tempGroup = new ArrayList<>();
                tempGroup.addAll(changeInfo1.get(groupId));

                if (!changeInfo2.containsKey(groupId)) {
                    Collections.sort(tempGroup, (a, b) -> a.startLine - b.startLine);
                    mergedInfo.put(groupId, tempGroup);
                    continue;
                }

                tempGroup.addAll(changeInfo2.get(groupId));
                Collections.sort(tempGroup, (a, b) -> a.startLine - b.startLine);

                List<MissingChangeInfo> mergedGroup = new ArrayList<>();
                int curChangeStart = tempGroup.get(0).startLine;
                int curChangeEnd = tempGroup.get(0).endLine;

                for (MissingChangeInfo info : tempGroup) {
                    if (info.startLine > curChangeEnd) {
                        mergedGroup.add(new MissingChangeInfo(curChangeStart, curChangeEnd));
                        curChangeStart = info.startLine;
                        curChangeEnd = info.endLine;
                    } else {
                        curChangeEnd = Math.max(curChangeEnd, info.endLine);
                    }
                }
                mergedGroup.add(new MissingChangeInfo(curChangeStart, curChangeEnd));

                mergedInfo.put(groupId, mergedGroup);
            }

            for (int groupId : changeInfo2.keySet()) {
                if (mergedInfo.containsKey(groupId)) continue;
                mergedInfo.put(groupId, changeInfo2.get(groupId));
            }

            rs.put(fileName, mergedInfo);
        }

        for (String fileName : rs2.keySet()) {
            if (rs.containsKey(fileName)) continue;
            rs.put(fileName, rs2.get(fileName));
        }

        return rs;
    }
}
