package next.dao;

import core.jdbc.ConnectionManager;
import next.model.Question;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.util.Date;
import java.util.List;

public class QuestionDaoTest {
    @Before
    public void setup() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("jwp.sql"));
        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
    }

    @Test
    public void get() {
        QuestionDao questionDao = new QuestionDao();
        Question question = questionDao.getQuestionById(1);
        Assert.assertEquals(1, question.getId());
        Assert.assertEquals("자바지기", question.getWriter());
    }

    @Test
    public void getAll() {
        QuestionDao questionDao = new QuestionDao();
        List<Question> questions = questionDao.findAll();
        Assert.assertEquals(8, questions.size());
    }

    @Test
    public void add() {
        Question expected = new Question(999, "peter", "title", "contents", new Date(), 0);
        QuestionDao questionDao = new QuestionDao();
        questionDao.insert(expected);

        List<Question> result = questionDao.findAll();
        Assert.assertEquals(9, result.size());
    }

    @Test
    public void delete() {
        Question expected = new Question(9, "peter", "title", "contents", new Date(), 0);
        QuestionDao questionDao = new QuestionDao();
        questionDao.insert(expected);

        questionDao.delete(9);

        List<Question> result = questionDao.findAll();
        Assert.assertEquals(8, result.size());
    }

    @Test
    public void updateContent() {
        Question expected = new Question(9, "peter", "title", "contents", new Date(), 0);
        QuestionDao questionDao = new QuestionDao();
        questionDao.insert(expected);

        questionDao.updateContent(9, "contents2");

        Question result = questionDao.getQuestionById(9);
        Assert.assertEquals("contents2", result.getContents());
    }

    @Test
    public void setCountOfAnswer() {
        Question expected = new Question(9, "peter", "title", "contents", new Date(), 0);
        QuestionDao questionDao = new QuestionDao();
        questionDao.insert(expected);

        questionDao.updateCountOfAnswer(9, 1);

        Question result = questionDao.getQuestionById(9);
        Assert.assertEquals(1, result.getCountOfAnswer());
    }
}
