package database.provider;

import database.csv.CSVSpecialistQuestionDataProvider;
import database.csv.CSVStandardQuestionDataProvider;
import model.SpecialistQuestion;
import model.StandardQuestion;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
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

        try {
            allStandardQuestions = CSVStandardQuestionDataProvider.getAllStandardQuestions();
            allSpecialistQuestions = CSVSpecialistQuestionDataProvider.getAllSpecialistQuestions();

            stQuestionsAt3 = new ArrayList<StandardQuestion>();
            stQuestionsAt2 = new ArrayList<StandardQuestion>();
            stQuestionsAt1 = new ArrayList<StandardQuestion>();

            spQuestionsAt3 = new ArrayList<SpecialistQuestion>();
            spQuestionsAt2 = new ArrayList<SpecialistQuestion>();
            spQuestionsAt1 = new ArrayList<SpecialistQuestion>();

            Random random = new Random(47);
            int x = random.nextInt(5) + 1;

            for (int i = 0; i < 100; i++) {
                Collections.shuffle(allStandardQuestions);
                Collections.shuffle(allSpecialistQuestions);
            }

            setQuestions();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("Time " + (end - start) + " ms");
    }

    private void setQuestions() {
        setStQuestionsAt3();
        setStQuestionsAt2();
        setStQuestionsAt1();

        setSpQuestions3();
        setSpQuestions2();
        setSpQuestions1();

        set20StandardQuestions();
        set12SpecialistQuestions();
    }

    private void setStQuestionsAt3() {
        int i = 0;

        for (StandardQuestion standardQuestion : allStandardQuestions) {
            if (standardQuestion.getPoints() == 3) {
                stQuestionsAt3.add(standardQuestion);
                i++;

                if (i == 10) {
                    break;
                }
            }
        }
    }

    private void setStQuestionsAt2() {
        int i = 0;

        for (StandardQuestion standardQuestion : allStandardQuestions) {
            if (standardQuestion.getPoints() == 2) {
                stQuestionsAt2.add(standardQuestion);
                i++;

                if (i == 6) {
                    break;
                }
            }
        }
    }

    private void setStQuestionsAt1() {
        int i = 0;

        for (StandardQuestion standardQuestion : allStandardQuestions) {
            if (standardQuestion.getPoints() == 1) {
                stQuestionsAt1.add(standardQuestion);
                i++;

                if (i == 4) {
                    break;
                }
            }
        }
    }


    private void setSpQuestions3() {
        int i = 0;

        for (SpecialistQuestion specialistQuestion : allSpecialistQuestions) {
            if (specialistQuestion.getPoints() == 3) {
                spQuestionsAt3.add(specialistQuestion);
                i++;

                if (i == 6) {
                    break;
                }
            }
        }
    }

    private void setSpQuestions2() {
        int i = 0;

        for (SpecialistQuestion specialistQuestion : allSpecialistQuestions) {
            if (specialistQuestion.getPoints() == 2) {
                spQuestionsAt2.add(specialistQuestion);
                i++;

                if (i == 4) {
                    break;
                }
            }
        }
    }

    private void setSpQuestions1() {
        int i = 0;

        for (SpecialistQuestion specialistQuestion : allSpecialistQuestions) {
            if (specialistQuestion.getPoints() == 1) {
                spQuestionsAt1.add(specialistQuestion);
                i++;

                if (i == 2) {
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

    public List<StandardQuestion> getStandard20Questions() {
        return standard20Questions;
    }

    public List<SpecialistQuestion> getSpecialist12Questions() {
        return specialist12Questions;
    }
}
