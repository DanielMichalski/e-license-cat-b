package database.provider;

import model.Module;
import model.SpecialistQuestion;
import model.StandardQuestion;
import org.junit.Test;

import java.util.List;

/**
 * Author: Daniel
 */
public class QuestionsProviderTest {
    @Test
    public void testGetStanQuestionsByModule() throws Exception {
        long start = System.currentTimeMillis();

        QuestionsProvider questionsProvider = QuestionsProvider.getInstance();
        ModuleProvider moduleProvider = ModuleProvider.getInstance();

        List<Module> allModules = moduleProvider.getAllModules();

        int allSuma = 0;
        for (Module module : allModules) {
            int suma = 0;
            List<StandardQuestion> stanQuestionsByModule =
                    questionsProvider.getStanQuestionsByModule(module);

            List<SpecialistQuestion> specQuestionsByModule =
                    questionsProvider.getSpecQuestionsByModule(module);

            suma += stanQuestionsByModule.size();
            suma += specQuestionsByModule.size();

            System.out.println(suma);
            allSuma += suma;
        }

        System.out.println("All: " + allSuma);

        long end = System.currentTimeMillis();
        double time = (double) (end - start) / 1000;
        System.out.println(String.format("Wczytywanie: %.2f sec.", time));
    }
}
