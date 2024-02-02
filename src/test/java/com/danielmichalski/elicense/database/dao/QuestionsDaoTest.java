package com.danielmichalski.elicense.database.dao;

import com.danielmichalski.elicense.database.provider.ExamQuestionProvider;
import com.danielmichalski.elicense.database.provider.QuestionsProvider;
import com.danielmichalski.elicense.model.SpecialistQuestion;
import com.danielmichalski.elicense.model.StandardQuestion;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Author: Daniel
 */
@Ignore
public class QuestionsDaoTest {
    private ExamQuestionProvider ExamQuestionsProvider;

    @Before
    public void setUp() throws Exception {
        ExamQuestionsProvider = ExamQuestionProvider.getInstance();
    }

    @After
    public void tearDown() throws Exception {
        ExamQuestionsProvider = null;
    }

    @Test
    public void testGetAllStandardQuestions() throws Exception {
        QuestionsProvider questionsProvider = QuestionsProvider.getInstance();

        List<StandardQuestion> allStandardQuestions =
                questionsProvider.getAllStQuestions();

        assertNotNull(allStandardQuestions);

        assertNotEquals(0, allStandardQuestions.size());
    }

    @Test
    public void testGetStandard20Questions() throws Exception {
        List<StandardQuestion> standard20Questions =
                ExamQuestionsProvider.getStandard20Questions();

        assertNotNull(standard20Questions);
        assertNotEquals(0, standard20Questions.size());
        assertEquals(20, standard20Questions.size());
    }

    @Test
    public void testGetAllSpecialistQuestions() throws Exception {
        QuestionsProvider questionsProvider = QuestionsProvider.getInstance();

        List<SpecialistQuestion> allSpecialistQuestions =
                questionsProvider.getAllSpQuestions();

        assertNotNull(allSpecialistQuestions);
        assertNotEquals(0, allSpecialistQuestions.size());
    }

    @Test
    public void testGetSpecialist12Questions() throws Exception {
        List<SpecialistQuestion> specialist12Questions =
                ExamQuestionsProvider.getSpecialist12Questions();

        assertNotNull(specialist12Questions);
        assertNotEquals(0, specialist12Questions.size());
        assertEquals(12, specialist12Questions.size());
    }
}
