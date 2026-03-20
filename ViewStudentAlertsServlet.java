import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class ViewStudentAlertsServlet extends HttpServlet {

protected void doGet(HttpServletRequest req, HttpServletResponse res)
throws ServletException, IOException {

res.setContentType("text/html");
PrintWriter out = res.getWriter();

out.println("<html><head><title>Alerts</title>");

out.println("<style>");
out.println("body{font-family:Arial;background:#f4f0ff;padding:30px;}");
out.println("table{width:80%;margin:auto;border-collapse:collapse;background:white;}");
out.println("th,td{padding:10px;border:1px solid #ddd;}");
out.println("th{background:#6a0dad;color:white;}");
out.println("</style>");

out.println("</head><body>");

out.println("<h2 align='center'>Emergency Alerts</h2>");

out.println("<table>");
out.println("<tr><th>Name</th><th>Location</th><th>Type</th><th>Message</th><th>Status</th></tr>");

try{

Class.forName("oracle.jdbc.driver.OracleDriver");

Connection con = DriverManager.getConnection(
"jdbc:oracle:thin:@localhost:1521:xe","system","system");

PreparedStatement ps = con.prepareStatement(
"SELECT * FROM EMERGENCY_ALERTS ORDER BY ALERT_TIME DESC");

ResultSet rs = ps.executeQuery();

while(rs.next()){

out.println("<tr>");
out.println("<td>"+rs.getString("STUDENT_NAME")+"</td>");
out.println("<td>"+rs.getString("LOCATION")+"</td>");
out.println("<td>"+rs.getString("ALERT_TYPE")+"</td>");
out.println("<td>"+rs.getString("MESSAGE")+"</td>");
out.println("<td>"+rs.getString("STATUS")+"</td>");
out.println("</tr>");

}

con.close();

}catch(Exception e){
out.println(e);
}

out.println("</table>");

out.println("<br><center><a href='admin_dashboard.html'>Back</a></center>");

out.println("</body></html>");
}
}
