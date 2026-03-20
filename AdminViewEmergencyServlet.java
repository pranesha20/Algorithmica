import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/ViewEmergencyServlet")
public class AdminViewEmergencyServlet extends HttpServlet {

protected void doGet(HttpServletRequest req,HttpServletResponse res)
throws ServletException,IOException{

res.setContentType("text/html");

PrintWriter out=res.getWriter();

Connection con=null;

try{

Class.forName("oracle.jdbc.driver.OracleDriver");

con=DriverManager.getConnection(
"jdbc:oracle:thin:@localhost:1521:XE",
"system",
"system"
);

/* MARK ALL ALERTS AS VIEWED */

Statement updateStmt=con.createStatement();

updateStmt.executeUpdate(
"UPDATE EMERGENCY_ALERTS SET STATUS='Viewed' WHERE STATUS='Active'"
);

con.commit();

/* SHOW ALERT TABLE */

Statement stmt=con.createStatement();

ResultSet rs=stmt.executeQuery(
"SELECT * FROM EMERGENCY_ALERTS ORDER BY ID DESC"
);

out.println("<html><head><title>Emergency Alerts</title>");

out.println("<style>");

out.println("body{font-family:Arial;background:#fff5f5;margin:0;}");

out.println(".header{background:#c0392b;color:white;padding:18px;text-align:center;font-size:24px;}");

out.println(".container{width:95%;margin:auto;margin-top:30px;}");

out.println("table{width:100%;border-collapse:collapse;background:white;box-shadow:0 4px 12px rgba(0,0,0,0.2);}");


out.println("th{background:#e74c3c;color:white;padding:12px;}");

out.println("td{padding:10px;text-align:center;border-bottom:1px solid #ddd;}");

out.println("</style>");

out.println("</head><body>");

out.println("<div class='header'>Emergency Alerts</div>");

out.println("<div class='container'>");

out.println("<table>");

out.println("<tr>");
out.println("<th>ID</th>");
out.println("<th>Student</th>");
out.println("<th>Location</th>");
out.println("<th>Alert Type</th>");
out.println("<th>Message</th>");
out.println("<th>Status</th>");
out.println("<th>Time</th>");
out.println("</tr>");

while(rs.next()){

out.println("<tr>");

out.println("<td>"+rs.getInt("ID")+"</td>");
out.println("<td>"+rs.getString("STUDENT_NAME")+"</td>");
out.println("<td>"+rs.getString("LOCATION")+"</td>");
out.println("<td>"+rs.getString("ALERT_TYPE")+"</td>");
out.println("<td>"+rs.getString("MESSAGE")+"</td>");
out.println("<td>"+rs.getString("STATUS")+"</td>");
out.println("<td>"+rs.getTimestamp("ALERT_TIME")+"</td>");

out.println("</tr>");

}

out.println("</table>");

out.println("<br>");

out.println("<a href='admin_dashboard.html'>Back to Dashboard</a>");

out.println("</div>");

out.println("</body></html>");

con.close();

}
catch(Exception e){

out.println(e);

}

}
}
