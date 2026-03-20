import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class VerifyStudentServlet extends HttpServlet {

protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {

String student_id = request.getParameter("student_id");
String student_name = request.getParameter("student_name");

response.setContentType("text/html");
PrintWriter out = response.getWriter();

try{

Class.forName("oracle.jdbc.driver.OracleDriver");

Connection con = DriverManager.getConnection(
"jdbc:oracle:thin:@localhost:1521:xe",
"system",
"system"
);

PreparedStatement ps = con.prepareStatement(
"SELECT * FROM STUDENT_REGISTER WHERE  ID=? AND NAME=?"
);

ps.setString(1, student_id);
ps.setString(2, student_name);

ResultSet rs = ps.executeQuery();

if(rs.next()){

out.println("<h2 style='color:green'>Student Verified Successfully</h2>");
out.println("<a href='student_qr.html'>Generate QR Pass</a>");

}

else{

out.println("<h2 style='color:red'>Verification Failed</h2>");
out.println("Student ID not found");

}

con.close();

}

catch(Exception e){
out.println(e);
}

}
}
