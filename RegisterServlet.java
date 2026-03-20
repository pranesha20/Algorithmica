import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String course = request.getParameter("course");
        String password = request.getParameter("password");

        try {

            // Load Oracle Driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Database Connection
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:XE",
                    "system",
                    "system"
            );

            // Insert Query
            String query = "INSERT INTO STUDENT_REGISTER VALUES (STUDENT_SEQ.NEXTVAL,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, name);
            ps.setString(2, username);
            ps.setString(3, email);
            ps.setString(4, course);
            ps.setString(5, password);

            int result = ps.executeUpdate();

            if (result > 0) {

                response.sendRedirect("student_dashboard.html");

            } else {

                out.println("Registration Failed");

            }

            con.close();

        } catch (Exception e) {

            out.println("Error: " + e);

        }

    }
}
