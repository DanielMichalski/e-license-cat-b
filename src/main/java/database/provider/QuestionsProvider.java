package database.provider;

import database.csv.CSVQuestionDataProvider;
import model.Module;
import model.SpecialistQuestion;
import model.StandardQuestion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Author: Daniel
 */
public class QuestionsProvider {
    List<StandardQuestion> allStandardQuestions;
    List<SpecialistQuestion> allSpecialistQuestions;

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

        allStandardQuestions = csvQuestionDataProvider.getAllStandardQuestions();
        allSpecialistQuestions = csvQuestionDataProvider.getAllSpecialistQuestions();

        stQuestionsAt3 = new ArrayList<StandardQuestion>();
        stQuestionsAt2 = new ArrayList<StandardQuestion>();
        stQuestionsAt1 = new ArrayList<StandardQuestion>();

        spQuestionsAt3 = new ArrayList<SpecialistQuestion>();
        spQuestionsAt2 = new ArrayList<SpecialistQuestion>();
        spQuestionsAt1 = new ArrayList<SpecialistQuestion>();

        shuffleQuestions();
        setQuestions();

        long end = System.currentTimeMillis();
        double time = (double) (end - start) / 1000;
        System.out.println(String.format("Całość: %.2f sec.", time));
    }

    private void shuffleQuestions() {
        Random random = new Random(47);
        int x = random.nextInt(10) + 1;

        for (int i = 0; i < x; i++) {
            Collections.shuffle(allStandardQuestions);
            Collections.shuffle(allSpecialistQuestions);
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

        for (StandardQuestion standardQuestion : allStandardQuestions) {
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

        for (SpecialistQuestion specialistQuestion : allSpecialistQuestions) {
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
        Collections.shuffle(specialist12Questions);
    }

    private void set20StandardQuestions() {
        standard20Questions = new ArrayList<StandardQuestion>();
        standard20Questions.addAll(stQuestionsAt1);
        standard20Questions.addAll(stQuestionsAt2);
        standard20Questions.addAll(stQuestionsAt3);
        Collections.shuffle(standard20Questions);
    }

    public List<StandardQuestion> getStanQuestionsByModule(Module module) {
        List<StandardQuestion> questions = new ArrayList<StandardQuestion>();

        for (StandardQuestion standardQuestion : allStandardQuestions) {
            if (standardQuestion.getModule().equalsIgnoreCase(String.valueOf(module.getId()))) {
                questions.add(standardQuestion);
            }
        }

        return questions;
    }


    public List<SpecialistQuestion> getSpecQuestionsByModule(Module module) {
        List<SpecialistQuestion> questions = new ArrayList<SpecialistQuestion>();

        for (SpecialistQuestion specialistQuestion : allSpecialistQuestions) {
            if (specialistQuestion.getModule().equalsIgnoreCase(String.valueOf(module.getId()))) {
                questions.add(specialistQuestion);
            }
        }

        return questions;
    }

    public List<StandardQuestion> getStandard20Questions() {
        return standard20Questions;
    }

    public List<SpecialistQuestion> getSpecialist12Questions() {
        return specialist12Questions;
    }

    public List<StandardQuestion> getAllStandardQuestions() {
        return allStandardQuestions;
    }

    public List<SpecialistQuestion> getAllSpecialistQuestions() {
        return allSpecialistQuestions;
    }
}
