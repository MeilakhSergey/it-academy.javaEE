package javaEE.meilakh;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class StringDefinerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("value");
        String result = StringDefiner.defineContent(name);
        PrintWriter writer = resp.getWriter();
        writer.append("<html>\n" +
                "<head>\n" +
                "<title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "String: \n" +
                name + " is " + result +
                "</body>\n" +
                "</html>");
    }
}
