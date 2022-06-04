package main;

import edu.ucla.se.GitCreator;
import edu.ucla.se.GitHandler;
import edu.ucla.se.P_LANG;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestGitMain {
    public static void main(String[] args) {
        String newPath = "../DataSet/Patch1/NEW_JDT9801";
        String oldPath = "../DataSet/Patch1/OLD_JDT9800";
        String repoName = "testpatch1";
        GitCreator gitCreator = new GitCreator();
        gitCreator.deleteRepo(repoName);
        gitCreator.createNewRepo(repoName);
        gitCreator.commitFilesToRepo(repoName, oldPath);
        String commitId = gitCreator.commitFilesToRepo(repoName, newPath);
        GitHandler gitHandler = new GitHandler(gitCreator.getRepo(repoName), repoName, commitId, P_LANG.JAVA);

        String filePath = "dom/org/eclipse/jdt/core/dom/DefaultCommentMapper.java";

        Map<String, List<List<Integer>>> linesPerFile = new HashMap<>();
        linesPerFile.put(filePath, new ArrayList<>());
        List<Integer> curGroup = new ArrayList<>();
        curGroup.add(204);
        curGroup.add(241);
        curGroup.add(254);
        linesPerFile.get(filePath).add(curGroup);


        Map<String, Map<Integer, String>> lineContent = gitHandler.getOldFileContentByLine(linesPerFile);
        System.out.println(lineContent);
    }
}
