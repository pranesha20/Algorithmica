import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class ResetPasswordServlet extends HttpServlet {

protected void doPost(HttpServletRequest req, HttpServletResponse res)
throws ServletException, IOException {

String username = req.getParameter("username");
String newPassword = req.getParameter("new_password");

try{

Class.forName("oracle.jdbc.driver.OracleDriver");

Connection con = DriverManager.getConnection(
"jdbc:oracle:thin:@localhost:1521:xe","system","system");

PreparedStatement ps = con.prepareStatement(
"UPDATE STUDENT_REGISTER SET PASSWORD=? WHERE USERNAME=?");

ps.setString(1, newPassword);
ps.setString(2, username);

int i = ps.executeUpdate();

if(i > 0){
res.getWriter().println("<script>alert('Password Updated Successfully');location='student_login.html';</script>");
}else{
res.getWriter().println("<script>alert('Error');location='forgot_password.html';</script>");
}

con.close();

}catch(Exception e){
e.printStackTrace();
}

}
}
