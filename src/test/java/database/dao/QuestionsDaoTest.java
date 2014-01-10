package database.dao;

import database.provider.QuestionsProvider;
import model.SpecialistQuestion;
import model.StandardQuestion;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Author: Daniel
 */
public class QuestionsDaoTest {
    private QuestionsProvider questionsProvider;

    @Before
    public void setUp() throws Exception {
        questionsProvider = QuestionsProvider.getInstance();
    }

    @After
    public void tearDown() throws Exception {
        questionsProvider = null;
    }

    @Test
    public void testGetAllStandardQuestions() throws Exception {
        List<StandardQuestion> allStandardQuestions =
                questionsProvider.getAllStandardQuestionsForExam();

        assertNotNull(allStandardQuestions);
        assertNotEquals(0, allStandardQuestions.size());
    }

    @Test
    public void testGetStandard20Questions() throws Exception {
        List<StandardQuestion> standard20Questions =
                questionsProvider.getStandard20Questions();

        assertNotNull(standard20Questions);
        assertNotEquals(0, standard20Questions.size());
        assertEquals(20, standard20Questions.size());
    }

    @Test
    public void testGetAllSpecialistQuestions() throws Exception {
        List<SpecialistQuestion> allSpecialistQuestions =
                questionsProvider.getAllSpecialistQuestionsForExam();

        assertNotNull(allSpecialistQuestions);
        assertNotEquals(0, allSpecialistQuestions.size());
    }

    @Test
    public void testGetSpecialist12Questions() throws Exception {
        List<SpecialistQuestion> specialist12Questions =
                questionsProvider.getSpecialist12Questions();

        assertNotNull(specialist12Questions);
        assertNotEquals(0, specialist12Questions.size());
        assertEquals(12, specialist12Questions.size());
    }
}
