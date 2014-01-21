package database.provider;

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
public class ExamQuestionProvider {
    public Logger logger = ApplicationUtils.getLogger(ExamQuestionProvider.class);

    private List<StandardQuestion> allStQuestions;
    private List<SpecialistQuestion> allSpQuestions;

    private List<StandardQuestion> stQuestionsAt3;
    private List<StandardQuestion> stQuestionsAt2;
    private List<StandardQuestion> stQuestionsAt1;

    private List<SpecialistQuestion> spQuestionsAt3;
    private List<SpecialistQuestion> spQuestionsAt2;
    private List<SpecialistQuestion> spQuestionsAt1;

    private List<StandardQuestion> standard20Questions;
    private List<SpecialistQuestion> specialist12Questions;

    private static ExamQuestionProvider ourInstance = new ExamQuestionProvider();

    private ExamQuestionProvider() {

        QuestionsProvider provider = QuestionsProvider.getInstance();

        this.allStQuestions = new ArrayList<StandardQuestion>(provider.getAllStQuestions());
        this.allSpQuestions = new ArrayList<SpecialistQuestion>(provider.getAllSpQuestions());

        initializeLists();
        shuffleAndSetQuestions();
    }

    private void initializeLists() {
        stQuestionsAt3 = new ArrayList<StandardQuestion>();
        stQuestionsAt2 = new ArrayList<StandardQuestion>();
        stQuestionsAt1 = new ArrayList<StandardQuestion>();

        spQuestionsAt3 = new ArrayList<SpecialistQuestion>();
        spQuestionsAt2 = new ArrayList<SpecialistQuestion>();
        spQuestionsAt1 = new ArrayList<SpecialistQuestion>();

        standard20Questions = new ArrayList<StandardQuestion>();
        specialist12Questions = new ArrayList<SpecialistQuestion>();
    }

    public void shuffleAndSetQuestions() {
        shuffleAllQuestions();
        setQuestions();
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
        shuffleSelectedQuestions();
    }

    private void shuffleAllQuestions() {
        Random random = new Random();
        int x = random.nextInt(30) + 1;

        for (int i = 0; i < x; i++) {
            Collections.shuffle(allStQuestions);
            Collections.shuffle(allSpQuestions);
        }
    }

    public void shuffleSelectedQuestions() {
        Random random = new Random();
        int x = random.nextInt(25) + 1;

        for (int i = 0; i < x; i++) {
            Collections.shuffle(standard20Questions);
            Collections.shuffle(specialist12Questions);
        }
    }

    private void setStandQuestions(List<StandardQuestion> questions, int howManyPoints, int howManyQuestions) {
        int i = 0;

        questions.clear();
        for (StandardQuestion standardQuestion : allStQuestions) {
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

        questions.clear();
        for (SpecialistQuestion specialistQuestion : allSpQuestions) {
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
        specialist12Questions.clear();
        specialist12Questions.addAll(spQuestionsAt1);
        specialist12Questions.addAll(spQuestionsAt2);
        specialist12Questions.addAll(spQuestionsAt3);
    }

    private void set20StandardQuestions() {
        standard20Questions.clear();
        standard20Questions.addAll(stQuestionsAt1);
        standard20Questions.addAll(stQuestionsAt2);
        standard20Questions.addAll(stQuestionsAt3);
    }

    public List<StandardQuestion> getStandard20Questions() {
        shuffleAndSetQuestions();
        return standard20Questions;
    }

    public List<SpecialistQuestion> getSpecialist12Questions() {
        shuffleAndSetQuestions();
        return specialist12Questions;
    }

    public static ExamQuestionProvider getInstance() {
        return ourInstance;
    }
}
