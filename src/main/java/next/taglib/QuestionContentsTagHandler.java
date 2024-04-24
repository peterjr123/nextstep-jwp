package next.taglib;

import jakarta.servlet.jsp.JspContext;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;

import java.io.IOException;

public class QuestionContentsTagHandler extends SimpleTagSupport {
    String contents;

    public void setContents(String contents) {
        this.contents = contents;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter writer = getJspContext().getOut();
        String[] paragraphs = contents.split("\n");
        for(String paragraph : paragraphs) {
            writer.println("<p>" + paragraph + "</p>");
        }
    }
}
