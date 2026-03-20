import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class ForgotPasswordServlet extends HttpServlet {

protected void doPost(HttpServletRequest req, HttpServletResponse res)
throws ServletException, IOException {

String username = req.getParameter("username");
String email = req.getParameter("email");

try{

Class.forName("oracle.jdbc.driver.OracleDriver");

Connection con = DriverManager.getConnection(
"jdbc:oracle:thin:@localhost:1521:xe","system","system");

PreparedStatement ps = con.prepareStatement(
"SELECT * FROM STUDENT_REGISTER WHERE USERNAME=? AND EMAIL=?");

ps.setString(1, username);
ps.setString(2, email);

ResultSet rs = ps.executeQuery();

if(rs.next()){
res.sendRedirect("reset_password.html?username=" + username);
}else{
res.getWriter().println("<script>alert('Invalid Details');location='forgot_password.html';</script>");
}

con.close();

}catch(Exception e){
e.printStackTrace();
}

}
}
