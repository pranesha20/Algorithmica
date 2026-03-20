import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class AdminRegisterServlet extends HttpServlet {

protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {

response.setContentType("text/html");
PrintWriter out = response.getWriter();

String fullName = request.getParameter("full_name");
String phone = request.getParameter("phone");
String email = request.getParameter("email");
String username = request.getParameter("username");
String password = request.getParameter("password");

try{

Class.forName("oracle.jdbc.driver.OracleDriver");

Connection con = DriverManager.getConnection(
"jdbc:oracle:thin:@localhost:1521:XE",
"system",
"system"
);

PreparedStatement ps = con.prepareStatement(
"INSERT INTO ADMIN_MODULE2 VALUES(ADMIN_SEQ.NEXTVAL,?,?,?,?,?)"
);

ps.setString(1, fullName);
ps.setString(2, phone);
ps.setString(3, email);
ps.setString(4, username);
ps.setString(5, password);

int i = ps.executeUpdate();

if(i > 0){

out.println("<h2>Admin Registered Successfully</h2>");
out.println("<a href='admin_login.html'>Login Here</a>");

}else{

out.println("Registration Failed");

}

con.close();

}catch(Exception e){

out.println(e);

}

}
}
