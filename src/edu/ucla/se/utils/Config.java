package edu.ucla.se.utils;

import java.nio.file.Paths;

public class Config {
    public final static String REPO_PATH = Paths.get("./repos").toString();

    public final static String CLDIFF_OUTPUT_PATH = Paths.get("./output").toString();

    public final static double SIM_SCORE_THRESH = 0.79;

    public final static double MIN_SUP_RATIO = 0.8;

    public final static double MATCH_SCORE = 0.95;

    public final static double MATCH_PATTERN_RATIO = 0.3;

    public final static boolean OUTPUT_TO_FILE = true;
}
