package database.xls;

import model.Module;
import model.SpecialistQuestion;
import model.StandardQuestion;
import model.enums.ABCAnswer;
import model.enums.YesOrNoAnswer;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Daniel
 * Date: 05.11.13
 */
public class XLSSpecialistQuestionDataProvider {
    public static Map<Integer, SpecialistQuestion> getAllSpecialistQuestions() {
        return new HashMap<Integer, SpecialistQuestion>();
    }

    public static Map<Integer, SpecialistQuestion> get12SpecialistQuestions() {
        Map<Integer, SpecialistQuestion> map = new HashMap<Integer, SpecialistQuestion>();

        for (int i = 1; i <= 12; i++) {
            SpecialistQuestion standardQuestion = new SpecialistQuestion(
                    i,
                    i + ". W którym momencie powinno nastąpić ustawienie lusterek podczas przygotowywania się do jazdy?",
                    "A) po ustawieniu fotela ",
                    "B) w dowolnym momencie ",
                    "C) przed ustawieniem fotela",
                    ABCAnswer.A,
                    new Module(1, "module name"),
                    null,
                    null
            );
            map.put(i, standardQuestion);
        }

        return map;
    }
}
