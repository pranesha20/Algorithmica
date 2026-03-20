import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class UpdateComplaintServlet extends HttpServlet {

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

        out.println("<html><head><title>Update Complaint</title>");

        out.println("<style>");

        out.println("body{font-family:Arial;background:#f4f0ff;margin:0;}");

        out.println(".header{background:#6a0dad;color:white;padding:15px;text-align:center;font-size:24px;}");

        out.println(".container{width:95%;margin:auto;margin-top:30px;}");

        out.println("table{width:100%;border-collapse:collapse;background:white;box-shadow:0 4px 10px rgba(0,0,0,0.1);}");


        out.println("th{background:#6a0dad;color:white;padding:12px;}");

        out.println("td{padding:10px;text-align:center;border-bottom:1px solid #ddd;}");

        out.println("tr:hover{background:#f1e6ff;}");

        out.println("select{padding:5px;}");

        out.println(".btn{background:#6a0dad;color:white;border:none;padding:6px 10px;border-radius:5px;cursor:pointer;}");

        out.println(".btn:hover{background:#4b0082;}");

        out.println(".back{margin-top:20px;display:inline-block;padding:10px 15px;background:#6a0dad;color:white;text-decoration:none;border-radius:5px;}");

        out.println("</style>");

        out.println("</head><body>");

        out.println("<div class='header'>Update Complaint Status</div>");

        out.println("<div class='container'>");

        out.println("<table>");

        out.println("<tr>");
        out.println("<th>ID</th>");
        out.println("<th>Student</th>");
        out.println("<th>Category</th>");
        out.println("<th>Subject</th>");
        out.println("<th>Description</th>");
        out.println("<th>Status</th>");
        out.println("<th>Update</th>");
        out.println("</tr>");

        try {

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select * from QUERIES order by id desc");

            while (rs.next()) {

                int id = rs.getInt("ID");

                out.println("<tr>");

                out.println("<form method='post' action='UpdateComplaintServlet'>");

                out.println("<td>" + id + "<input type='hidden' name='id' value='" + id + "'></td>");

                out.println("<td>" + rs.getString("STUDENT_NAME") + "</td>");

                out.println("<td>" + rs.getString("CATEGORY") + "</td>");

                out.println("<td>" + rs.getString("SUBJECT") + "</td>");

                out.println("<td>" + rs.getString("DESCRIPTION") + "</td>");

                out.println("<td>");

                out.println("<select name='status'>");
                out.println("<option>Pending</option>");
                out.println("<option>In Progress</option>");
                out.println("<option>Resolved</option>");
                out.println("</select>");

                out.println("</td>");

                out.println("<td>");
                out.println("<input type='submit' value='Update' class='btn'>");
                out.println("</td>");

                out.println("</form>");

                out.println("</tr>");

            }

        } catch (Exception e) {
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        }

        out.println("</table>");

        out.println("<a class='back' href='admin_dashboard.html'> Back to Dashboard</a>");

        out.println("</div>");

        out.println("</body></html>");

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        String status = request.getParameter("status");

        try {

            PreparedStatement ps = con.prepareStatement(
                    "update queries set status=? where id=?"
            );

            ps.setString(1, status);
            ps.setInt(2, id);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("UpdateComplaintServlet");
    }
}
