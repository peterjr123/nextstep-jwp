package next.model;

import java.time.LocalDateTime;
import java.util.Date;

public class Question {
    int id;
    String writer;
    String title;
    String contents;
    Date createdDate;
    int countOfAnswer;

    public int getId() {
        return id;
    }

    public String getWriter() {
        return writer;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public int getCountOfAnswer() {
        return countOfAnswer;
    }

    public Question(int id, String writer, String title, String contents, Date createdDate, int countOfAnswer) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdDate = createdDate;
        this.countOfAnswer = countOfAnswer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", writerName='" + writer + '\'' +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", createdDate=" + createdDate +
                ", countOfAnswer=" + countOfAnswer +
                '}';
    }
}
