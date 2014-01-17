package database.provider;

import database.csv.CSVQuestionDataProvider;
import model.Module;
import model.SpecialistQuestion;
import model.StandardQuestion;
import org.apache.log4j.Logger;
import util.ApplicationUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Author: Daniel
 */
public class QuestionsProvider {
    public Logger logger = ApplicationUtils.getLogger(QuestionsProvider.class);

    List<StandardQuestion> allStandardQuestionsForExam;
    List<SpecialistQuestion> allSpecialistQuestionsForExam;

    List<StandardQuestion> allStandardQuestionsForLearning;
    List<SpecialistQuestion> allSpecialistQuestionsForExamLearning;

    private List<StandardQuestion> stQuestionsAt3;
    private List<StandardQuestion> stQuestionsAt2;
    private List<StandardQuestion> stQuestionsAt1;

    private List<SpecialistQuestion> spQuestionsAt3;
    private List<SpecialistQuestion> spQuestionsAt2;
    private List<SpecialistQuestion> spQuestionsAt1;

    List<StandardQuestion> standard20Questions;
    List<SpecialistQuestion> specialist12Questions;

    private static QuestionsProvider ourInstance = new QuestionsProvider();

    public static QuestionsProvider getInstance() {
        return ourInstance;
    }

    private QuestionsProvider() {
        long start = System.currentTimeMillis();

        CSVQuestionDataProvider csvQuestionDataProvider = new CSVQuestionDataProvider();

        allStandardQuestionsForExam = csvQuestionDataProvider.getAllStandardQuestions();
        allSpecialistQuestionsForExam = csvQuestionDataProvider.getAllSpecialistQuestions();

        allStandardQuestionsForLearning = new ArrayList<StandardQuestion>();
        allSpecialistQuestionsForExamLearning = new ArrayList<SpecialistQuestion>();

        allStandardQuestionsForLearning.addAll(allStandardQuestionsForExam);
        allSpecialistQuestionsForExamLearning.addAll(allSpecialistQuestionsForExam);

        stQuestionsAt3 = new ArrayList<StandardQuestion>();
        stQuestionsAt2 = new ArrayList<StandardQuestion>();
        stQuestionsAt1 = new ArrayList<StandardQuestion>();

        spQuestionsAt3 = new ArrayList<SpecialistQuestion>();
        spQuestionsAt2 = new ArrayList<SpecialistQuestion>();
        spQuestionsAt1 = new ArrayList<SpecialistQuestion>();

        setQuestions();
        shuffleAllQuestions(30);

        long end = System.currentTimeMillis();
        double time = (double) (end - start) / 1000;
        logger.info(String.format("Całość: %.2f sec.", time));
    }

    private void shuffleAllQuestions(int howManyTimes) {
        Random random = new Random();
        int x = random.nextInt(howManyTimes) + 1;

        for (int i = 0; i < x; i++) {
            Collections.shuffle(allStandardQuestionsForExam);
            Collections.shuffle(allSpecialistQuestionsForExam);
        }
    }

    public void shuffleSelectedQuestions(int howManyTimes) {
        Random random = new Random();
        int x = random.nextInt(howManyTimes) + 1;

        for (int i = 0; i < x; i++) {
            Collections.shuffle(standard20Questions);
            Collections.shuffle(specialist12Questions);
        }
    }

    private void setQuestions() {
        setStandQuestions(stQuestionsAt3, 3, 10);
        setStandQuestions(stQuestionsAt2, 2, 6);
        setStandQuestions(stQuestionsAt1, 1, 4);

        setSpecQuestions(spQuestionsAt3, 3, 6);
        setSpecQuestions(spQuestionsAt2, 2, 4);
        setSpecQuestions(spQuestionsAt1, 1, 2);

        set20StandardQuestions();
        set12SpecialistQuestions();
    }

    private void setStandQuestions(List<StandardQuestion> questions, int howManyPoints, int howManyQuestions) {
        int i = 0;

        for (StandardQuestion standardQuestion : allStandardQuestionsForExam) {
            if (standardQuestion.getPoints() == howManyPoints) {
                questions.add(standardQuestion);
                i++;

                if (i == howManyQuestions) {
                    break;
                }
            }
        }
    }

    private void setSpecQuestions(List<SpecialistQuestion> questions, int howManyPoints, int howManyQuestions) {
        int i = 0;

        for (SpecialistQuestion specialistQuestion : allSpecialistQuestionsForExam) {
            if (specialistQuestion.getPoints() == howManyPoints) {
                questions.add(specialistQuestion);
                i++;

                if (i == howManyQuestions) {
                    break;
                }
            }
        }
    }

    private void set12SpecialistQuestions() {
        specialist12Questions = new ArrayList<SpecialistQuestion>();
        specialist12Questions.addAll(spQuestionsAt1);
        specialist12Questions.addAll(spQuestionsAt2);
        specialist12Questions.addAll(spQuestionsAt3);

        Random random = new Random();
        int x = random.nextInt(11);
        for (int i = 0; i < x; i++) {
            Collections.shuffle(specialist12Questions);
        }
    }

    private void set20StandardQuestions() {
        standard20Questions = new ArrayList<StandardQuestion>();
        standard20Questions.addAll(stQuestionsAt1);
        standard20Questions.addAll(stQuestionsAt2);
        standard20Questions.addAll(stQuestionsAt3);

        Random random = new Random();
        int x = random.nextInt(11);
        for (int i = 0; i < x; i++) {
            Collections.shuffle(standard20Questions);
        }
    }

    public List<StandardQuestion> getStanQuestionsByModule(Module module) {
        List<StandardQuestion> questions = new ArrayList<StandardQuestion>();

        for (StandardQuestion standardQuestion : allStandardQuestionsForLearning) {
            if (standardQuestion.getModule().equalsIgnoreCase(String.valueOf(module.getId()))) {
                questions.add(standardQuestion);
            }
        }

        return questions;
    }


    public List<SpecialistQuestion> getSpecQuestionsByModule(Module module) {
        List<SpecialistQuestion> questions = new ArrayList<SpecialistQuestion>();

        for (SpecialistQuestion specialistQuestion : allSpecialistQuestionsForExamLearning) {
            if (specialistQuestion.getModule().equalsIgnoreCase(String.valueOf(module.getId()))) {
                questions.add(specialistQuestion);
            }
        }

        return questions;
    }

    public List<StandardQuestion> getStandard20Questions() {
        set20StandardQuestions();
        return standard20Questions;
    }

    public List<SpecialistQuestion> getSpecialist12Questions() {
        set12SpecialistQuestions();
        return specialist12Questions;
    }

    public List<StandardQuestion> getAllStandardQuestionsForExam() {
        return allStandardQuestionsForExam;
    }

    public List<SpecialistQuestion> getAllSpecialistQuestionsForExam() {
        return allSpecialistQuestionsForExam;
    }

    public List<StandardQuestion> getAllStandardQuestionsForLearning() {
        return allStandardQuestionsForLearning;
    }

    public List<SpecialistQuestion> getAllSpecialistQuestionsForExamLearning() {
        return allSpecialistQuestionsForExamLearning;
    }
}
