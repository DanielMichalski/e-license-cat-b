package com.danielmichalski.elicense.database.provider;

import com.danielmichalski.elicense.model.Module;
import com.danielmichalski.elicense.model.SpecialistQuestion;
import com.danielmichalski.elicense.model.StandardQuestion;
import org.apache.log4j.Logger;
import org.junit.Test;
import com.danielmichalski.elicense.util.ApplicationUtils;

import java.util.List;

/**
 * Author: Daniel
 */
public class QuestionsProviderTest {
    public Logger logger = ApplicationUtils.getLogger(QuestionsProviderTest.class);

    @Test
    public void testGetStanQuestionsByModule() throws Exception {
        long start = System.currentTimeMillis();

        LearningQuestionProvider questionsProvider = LearningQuestionProvider.getInstance();
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

            logger.info(suma);
            allSuma += suma;
        }

        logger.info("All: " + allSuma);

        long end = System.currentTimeMillis();
        double time = (double) (end - start) / 1000;
        logger.info(String.format("Wczytywanie: %.2f sec.", time));
    }
}
