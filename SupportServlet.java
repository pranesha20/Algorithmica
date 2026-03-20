import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class SupportServlet extends HttpServlet {

protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {

response.setContentType("text/html");
PrintWriter out=response.getWriter();

String name=request.getParameter("name");
String email=request.getParameter("email");
String subject=request.getParameter("subject");
String message=request.getParameter("message");

try{

Class.forName("oracle.jdbc.driver.OracleDriver");

Connection con=DriverManager.getConnection(
"jdbc:oracle:thin:@localhost:1521:XE",
"system",
"system"
);

PreparedStatement ps=con.prepareStatement(
"INSERT INTO SUPPORT VALUES(SUPPORT_SEQ.NEXTVAL,?,?,?,?)"
);

ps.setString(1,name);
ps.setString(2,email);
ps.setString(3,subject);
ps.setString(4,message);

int i=ps.executeUpdate();

if(i>0){

out.println("<h2>Support Request Submitted Successfully</h2>");
out.println("<a href='student_dashboard.html'>Back to Dashboard</a>");

}
else{

out.println("Error submitting request");

}

con.close();

}
catch(Exception e){

out.println(e);

}

}
}
