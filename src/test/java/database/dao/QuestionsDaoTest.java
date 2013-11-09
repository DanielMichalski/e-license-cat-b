package database.dao;

import model.SpecialistQuestion;
import model.StandardQuestion;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Author: Daniel
 * Date: 08.11.13
 */
public class QuestionsDaoTest {
    @Test
    public void testGet20StandardQuestion() throws Exception {
        List<SpecialistQuestion> questions =
                QuestionsDao.get12SpecialistQuestion();

        assertNotNull(questions);
        assertEquals(12, questions.size());
    }

    @Test
    public void testGetAllStandardQuestion() throws Exception {
        List<StandardQuestion> questions =
                QuestionsDao.getAllStandardQuestion();

        assertNotNull(questions);

    }

    @Test
    public void testGet12SpecialistQuestion() throws Exception {
        List<SpecialistQuestion> questions =
                QuestionsDao.get12SpecialistQuestion();

        assertNotNull(questions);
        assertEquals(12, questions.size());
    }

    @Test
    public void testGetAllSpecialistQuestion() throws Exception {
        List<SpecialistQuestion> questions =
                QuestionsDao.getAllSpecialistQuestion();

        assertNotNull(questions);
    }
}
