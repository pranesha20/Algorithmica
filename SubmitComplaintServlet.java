import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class SubmitComplaintServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("student_name");
        String category = request.getParameter("category");
        String subject = request.getParameter("subject");
        String description = request.getParameter("description");
        String dateStr = request.getParameter("date");

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:XE",
                "system",
                "system"
            );

            //  Get next sequence value
            Statement seqStmt = con.createStatement();
            ResultSet seqRs = seqStmt.executeQuery("SELECT QUERIES_SEQ.NEXTVAL FROM DUAL");
            int complaintId = 0;
            if(seqRs.next()) {
                complaintId = seqRs.getInt(1);
            }
            seqRs.close();
            seqStmt.close();

            //  Insert complaint using that ID
            String sql = "INSERT INTO QUERIES (ID, STUDENT_NAME, CATEGORY, SUBJECT, DESCRIPTION, STATUS, COMPLAINT_DATE) " +
                         "VALUES (?, ?, ?, ?, ?, 'Pending', ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, complaintId);
            ps.setString(2, name);
            ps.setString(3, category);
            ps.setString(4, subject);
            ps.setString(5, description);
            ps.setDate(6, java.sql.Date.valueOf(dateStr));

            int i = ps.executeUpdate();

            if(i > 0){
                out.println("<h2>Complaint Submitted Successfully</h2>");
                out.println("<p>Your Complaint ID is: <strong>" + complaintId + "</strong></p>");
                out.println("<a href='student_dashboard.html'>Go to Dashboard</a>");
            } else {
                out.println("<h3>Error submitting complaint</h3>");
            }

            con.close();

        } catch(Exception e){
            out.println("<h3>Error: </h3>" + e);
        }
    }
}
