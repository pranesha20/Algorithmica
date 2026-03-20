import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class UpdateAlertServlet extends HttpServlet {

protected void doGet(HttpServletRequest req, HttpServletResponse res)
throws ServletException, IOException {

String id = req.getParameter("id");

try{

Class.forName("oracle.jdbc.driver.OracleDriver");

Connection con = DriverManager.getConnection(
"jdbc:oracle:thin:@localhost:1521:xe","system","system");

PreparedStatement ps = con.prepareStatement(
"UPDATE EMERGENCY_ALERTS SET STATUS='VIEWED' WHERE ID=?");

ps.setInt(1, Integer.parseInt(id));

ps.executeUpdate();

con.close();

}catch(Exception e){
e.printStackTrace();
}

}
}
