import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class CheckStudentAlertStatusServlet extends HttpServlet {

protected void doGet(HttpServletRequest req, HttpServletResponse res)
throws ServletException, IOException {

res.setContentType("text/plain");
PrintWriter out = res.getWriter();

try{

Class.forName("oracle.jdbc.driver.OracleDriver");

Connection con = DriverManager.getConnection(
"jdbc:oracle:thin:@localhost:1521:xe","system","system");

/* Get latest viewed alert */
PreparedStatement ps = con.prepareStatement(
"SELECT MESSAGE FROM EMERGENCY_ALERTS WHERE STATUS='VIEWED' ORDER BY ALERT_TIME DESC");

ResultSet rs = ps.executeQuery();

if(rs.next()){
out.println("Admin viewed your alert: " + rs.getString("MESSAGE"));
}

con.close();

}catch(Exception e){
out.println("");
}

}
}
