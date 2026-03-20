import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class StudentProfileServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        HttpSession session = req.getSession(false);

        String username = "";

        if (session != null && session.getAttribute("username") != null) {
            username = (String) session.getAttribute("username");
        } else {
            res.sendRedirect("student_login.html");
            return;
        }

        String name = "", email = "", course = "";

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:XE",
                    "system",
                    "system"
            );

            PreparedStatement ps = con.prepareStatement(
                    "SELECT NAME, EMAIL, COURSE FROM STUDENT_REGISTER WHERE USERNAME=?"
            );

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                name = rs.getString("NAME");
                email = rs.getString("EMAIL");
                course = rs.getString("COURSE");

            }

            con.close();

        } catch (Exception e) {
            out.println(e);
        }

        out.println("<html><head><title>Student Profile</title>");

        out.println("<style>");

        out.println("body{font-family:Arial;background:#f4f0ff;}");

        out.println(".container{max-width:600px;margin:50px auto;padding:30px;"
                + "background:#ffffff;box-shadow:0 0 18px rgba(106,13,173,0.3);"
                + "border-radius:14px;}");

        out.println("h2{color:#6a0dad;text-align:center;}");

        out.println("p{font-size:16px;margin:10px 0;color:#4b0082;}");

        out.println(".btn{padding:10px 22px;"
                + "background:linear-gradient(135deg,#6a0dad,#8a2be2);"
                + "color:white;text-decoration:none;border-radius:25px;"
                + "margin-top:18px; display:inline-block;}");

        out.println(".btn:hover{background:linear-gradient(135deg,#8a2be2,#6a0dad);}");


        out.println("</style>");

        out.println("</head><body>");

        out.println("<div class='container'>");

        out.println("<h2>Student Profile</h2>");

        out.println("<p><b>Name:</b> " + name + "</p>");
        out.println("<p><b>Username:</b> " + username + "</p>");
        out.println("<p><b>Email:</b> " + email + "</p>");
        out.println("<p><b>Course:</b> " + course + "</p>");

        out.println("<br>");

        out.println("<a class='btn' href='student_dashboard.html'>Back to Dashboard</a>");

        out.println("</div>");

        out.println("</body></html>");

    }
}
