import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class SendEmergencyAlertServlet extends HttpServlet {

protected void doPost(HttpServletRequest req, HttpServletResponse res)
throws ServletException, IOException {

String name = req.getParameter("student_name");
String location = req.getParameter("location");
String type = req.getParameter("alert_type");
String message = req.getParameter("message");

res.setContentType("text/html");
PrintWriter out = res.getWriter();

try{

Class.forName("oracle.jdbc.driver.OracleDriver");

Connection con = DriverManager.getConnection(
"jdbc:oracle:thin:@localhost:1521:xe","system","system");

/* Generate ID */
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("SELECT NVL(MAX(ID),0)+1 FROM EMERGENCY_ALERTS");

int id = 1;
if(rs.next()){
id = rs.getInt(1);
}

PreparedStatement ps = con.prepareStatement(
"INSERT INTO EMERGENCY_ALERTS VALUES(?,?,?,?,?,?,SYSDATE)");

ps.setInt(1,id);
ps.setString(2,name);
ps.setString(3,location);
ps.setString(4,type);
ps.setString(5,message);
ps.setString(6,"NEW");

ps.executeUpdate();

out.println("<script>alert('Alert Sent');location='student_dashboard.html';</script>");

con.close();

}catch(Exception e){
out.println(e);
}

}
}
