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
                    "Czy wyjeżdzając za te znaki informacyjne włączysz się do ruchu? " + i, YesOrNoAnswer.yes);
            questions.put(i, question);
        }

        return questions;
    }
}
