package com.danielmichalski.elicense.util;

/**
 * Author: Daniel
 */
public class ExamUtil {

    public static final int HOW_MANY_TO_PASS_EXAM = 69;

    public static String getExamResultFromPoints(int points) {
        if (points >= HOW_MANY_TO_PASS_EXAM) {
            return "EGZAMIN ZALICZONY";
        } else {
            return "EGZAMIN NIEZALICZONY";
        }
    }

    public static boolean isPassedExam(int points) {
        return points >= HOW_MANY_TO_PASS_EXAM;
    }
}
