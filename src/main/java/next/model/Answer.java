package next.model;

import java.util.Date;

public class Answer {
    int answerId;
    String writer;
    String contents;
    Date createdDate;
    int questionId;

    public Answer(int answerId, String writer, String contents, Date createdDate, int questionId) {
        this.answerId = answerId;
        this.writer = writer;
        this.contents = contents;
        this.createdDate = createdDate;
        this.questionId = questionId;
    }

    public int getAnswerId() {
        return answerId;
    }

    public String getWriter() {
        return writer;
    }

    public String getContents() {
        return contents;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public int getQuestionId() {
        return questionId;
    }
}
