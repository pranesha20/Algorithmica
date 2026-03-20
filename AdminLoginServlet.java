import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class AdminLoginServlet extends HttpServlet {

protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {

response.setContentType("text/html");
PrintWriter out=response.getWriter();

String username=request.getParameter("username");
String password=request.getParameter("password");

try{

Class.forName("oracle.jdbc.driver.OracleDriver");

Connection con=DriverManager.getConnection(
"jdbc:oracle:thin:@localhost:1521:XE",
"system",
"system"
);

PreparedStatement ps=con.prepareStatement(
"SELECT * FROM ADMIN_MODULE2 WHERE USERNAME=? AND PASSWORD=?"
);

ps.setString(1,username);
ps.setString(2,password);

ResultSet rs=ps.executeQuery();

if(rs.next()){

// CREATE SESSION
HttpSession session=request.getSession();
session.setAttribute("username",username);

// LOGIN SUCCESS
response.sendRedirect("admin_dashboard.html");

}
else{

out.println("<h2>Invalid Username or Password</h2>");
out.println("<a href='admin_login.html'>Try Again</a>");

}

con.close();

}
catch(Exception e){

out.println(e);

}

}
}
