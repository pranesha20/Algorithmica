import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ViewComplaintServlet extends HttpServlet {

    Connection con;

    public void init() throws ServletException {
        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");

            con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe",
                    "system",
                    "system"
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>View Complaints</title>");

        out.println("<style>");

        out.println("body{font-family:Arial;background:#f4f0ff;margin:0;}");

        out.println(".header{background:#6a0dad;color:white;padding:15px;text-align:center;font-size:24px;}");

        out.println(".container{width:95%;margin:auto;margin-top:30px;}");

        out.println("table{width:100%;border-collapse:collapse;background:white;box-shadow:0 4px 10px rgba(0,0,0,0.1);}");


        out.println("th{background:#6a0dad;color:white;padding:12px;}");

        out.println("td{padding:10px;text-align:center;border-bottom:1px solid #ddd;}");

        out.println("tr:hover{background:#f1e6ff;}");

        out.println(".status{padding:5px 10px;border-radius:6px;color:white;}");

        out.println(".pending{background:#ff9800;}");

        out.println(".resolved{background:#4caf50;}");

        out.println(".progress{background:#2196f3;}");

        out.println(".back{margin-top:20px;display:inline-block;padding:10px 15px;background:#6a0dad;color:white;text-decoration:none;border-radius:5px;}");

        out.println("</style>");

        out.println("</head>");
        out.println("<body>");

        out.println("<div class='header'>Student Complaints - Admin Panel</div>");

        out.println("<div class='container'>");

        out.println("<table>");

        out.println("<tr>");
        out.println("<th>ID</th>");
        out.println("<th>Student Name</th>");
        out.println("<th>Category</th>");
        out.println("<th>Subject</th>");
        out.println("<th>Description</th>");
        out.println("<th>Status</th>");
        out.println("<th>Date</th>");
        out.println("</tr>");

        try {

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select * from QUERIES order by id desc");

            while (rs.next()) {

                String status = rs.getString("STATUS");

                String css = "pending";

                if ("Resolved".equalsIgnoreCase(status))
                    css = "resolved";
                else if ("In Progress".equalsIgnoreCase(status))
                    css = "progress";

                out.println("<tr>");

                out.println("<td>" + rs.getInt("ID") + "</td>");
                out.println("<td>" + rs.getString("STUDENT_NAME") + "</td>");
                out.println("<td>" + rs.getString("CATEGORY") + "</td>");
                out.println("<td>" + rs.getString("SUBJECT") + "</td>");
                out.println("<td>" + rs.getString("DESCRIPTION") + "</td>");

                out.println("<td><span class='status " + css + "'>" + status + "</span></td>");

                out.println("<td>" + rs.getDate("COMPLAINT_DATE") + "</td>");

                out.println("</tr>");

            }

        } catch (Exception e) {
            out.println("<h3>Error : " + e.getMessage() + "</h3>");
        }

        out.println("</table>");

        out.println("<a class='back' href='admin_dashboard.html'>Back to Dashboard</a>");

        out.println("</div>");

        out.println("</body>");
        out.println("</html>");
    }

}
