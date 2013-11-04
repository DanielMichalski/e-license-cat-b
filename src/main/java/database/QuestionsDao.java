package database;

import model.StandardQuestion;
import model.YesOrNoAnswer;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Daniel
 * Date: 03.11.13
 */
public class QuestionsDao {
    public static Map<Integer, StandardQuestion> getlistOfStandardQuestion() {

        Map<Integer, StandardQuestion> questions =
                new HashMap<Integer, StandardQuestion>();

        for (int i = 1; i <= 20; i++) {
            StandardQuestion question = new StandardQuestion(
                    "Pytanie standard " + i + ": Czy wyjeżdzając za te znaki informacyjne włączysz się do ruchu? ", YesOrNoAnswer.yes, i);
            questions.put(i, question);
        }

        return questions;
    }

    public static Map<Integer, StandardQuestion> getlistOfSpecialistdQuestion() {

        Map<Integer, StandardQuestion> questions =
                new HashMap<Integer, StandardQuestion>();

        for (int i = 1; i <= 20; i++) {
            StandardQuestion question = new StandardQuestion(
                    "Pytanie specialist " + i + ": Czy wyjeżdzając za te znaki informacyjne włączysz się do ruchu? ", YesOrNoAnswer.yes, i);
            questions.put(i, question);
        }

        return questions;
    }
}
