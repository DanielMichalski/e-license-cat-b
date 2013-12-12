package database.dao;

import database.provider.QuestionsProvider;
import org.junit.Test;

/**
 * Author: Daniel
 */
public class QuestionDaoTimeTest {
    @Test(timeout = 1000)
    public void testGetQuestionsTime() throws Exception {
        QuestionsProvider questionsProvider =
                QuestionsProvider.getInstance();
    }
}