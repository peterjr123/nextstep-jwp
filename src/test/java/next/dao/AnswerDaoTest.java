package next.dao;

import core.jdbc.ConnectionManager;
import next.model.Answer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.util.Date;
import java.util.List;

public class AnswerDaoTest {
    @Before
    public void setup() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("jwp.sql"));
        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
    }

    @Test
    public void get() {
        AnswerDao answerDao = new AnswerDao();
        List<Answer> result = answerDao.findAnswer(7);
        Assert.assertEquals(2, result.size());
    }

    @Test
    public void addAnswer() {
        AnswerDao answerDao = new AnswerDao();
        Answer answer = new Answer(99, "writer", "contents", new Date(), 7);
        answerDao.insert(answer);
        List<Answer> result = answerDao.findAnswer(7);
        Assert.assertEquals(3, result.size());
    }

    @Test
    public void deleteAnswer() {
        AnswerDao answerDao = new AnswerDao();
        List<Answer> before = answerDao.findAnswer(7);
        Assert.assertEquals(2, before.size());

        answerDao.delete(before.get(0).getAnswerId());

        List<Answer> after = answerDao.findAnswer(7);
        Assert.assertEquals(1, after.size());
    }

    @Test
    public void updateAnswer() {
        AnswerDao answerDao = new AnswerDao();
        List<Answer> result = answerDao.findAnswer(7);
        answerDao.update(result.get(0).getAnswerId(), "contents");

        result = answerDao.findAnswer(7);
        Assert.assertEquals("contents", result.get(0).getContents());
    }
}
