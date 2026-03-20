import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class EmergencyAlertServlet extends HttpServlet {

protected void doPost(HttpServletRequest req, HttpServletResponse res)
throws ServletException, IOException {

res.setContentType("text/html");
PrintWriter out = res.getWriter();

String name = req.getParameter("name");
String location = req.getParameter("location");
String type = req.getParameter("type");
String message = req.getParameter("message");

try{

Class.forName("oracle.jdbc.driver.OracleDriver");

Connection con = DriverManager.getConnection(
"jdbc:oracle:thin:@localhost:1521:XE",
"system",
"system"
);

/* Get next ID from sequence */
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("SELECT ALERT_SEQ.NEXTVAL FROM DUAL");

int id = 0;
if(rs.next()){
id = rs.getInt(1);
}

/* Insert data */
PreparedStatement ps = con.prepareStatement(
"INSERT INTO EMERGENCY_ALERTS (ID, STUDENT_NAME, LOCATION, ALERT_TYPE, MESSAGE, STATUS, ALERT_TIME) VALUES (?,?,?,?,?,'Active',SYSDATE)"
);

ps.setInt(1, id);
ps.setString(2, name);
ps.setString(3, location);
ps.setString(4, type);
ps.setString(5, message);

int i = ps.executeUpdate();

if(i > 0){

out.println("<html><body style='font-family:Arial;text-align:center;background:#fff3f3'>");
out.println("<h2 style='color:#d32f2f'>Emergency Alert Sent Successfully</h2>");
out.println("<p>Your Alert ID: <b>"+id+"</b></p>");
out.println("<a href='student_dashboard.html'>Back to Dashboard</a>");
out.println("</body></html>");

}else{
out.println("<h3>Error sending alert</h3>");
}

con.close();

}catch(Exception e){
out.println("<h3>Error:</h3>"+e);
}

}
}
