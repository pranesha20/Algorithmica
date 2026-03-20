import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class ViewVerifiedStudentsServlet extends HttpServlet {

protected void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {

response.setContentType("text/html");
PrintWriter out = response.getWriter();

out.println("<html>");
out.println("<head>");
out.println("<title>Verified Students</title>");

out.println("<style>");
out.println("body{font-family:Arial;background:#f4f0ff;padding:40px;text-align:center;}");
out.println("table{border-collapse:collapse;width:60%;margin:auto;background:white;box-shadow:0 0 10px gray;}");
out.println("th,td{padding:12px;border:1px solid #ddd;}");
out.println("th{background:#6a0dad;color:white;}");
out.println("h2{color:#6a0dad;margin-bottom:20px;}");
out.println("a{display:inline-block;margin-top:20px;padding:10px 20px;background:#6a0dad;color:white;text-decoration:none;border-radius:5px;}");
out.println("a:hover{background:#4b0082;}");
out.println("</style>");

out.println("</head>");
out.println("<body>");

out.println("<h2>Verified Students List</h2>");

out.println("<table>");
out.println("<tr>");
out.println("<th>Student ID</th>");
out.println("<th>Student Name</th>");
out.println("</tr>");

try{

Class.forName("oracle.jdbc.driver.OracleDriver");

Connection con = DriverManager.getConnection(
"jdbc:oracle:thin:@localhost:1521:xe",
"system",
"system"
);

PreparedStatement ps = con.prepareStatement(
"SELECT ID, NAME FROM STUDENT_REGISTER"
);

ResultSet rs = ps.executeQuery();

while(rs.next()){

out.println("<tr>");
out.println("<td>"+rs.getString("ID")+"</td>");
out.println("<td>"+rs.getString("NAME")+"</td>");
out.println("</tr>");

}

con.close();

}

catch(Exception e){
out.println("<h3 style='color:red;'>Error: "+e.getMessage()+"</h3>");
}

out.println("</table>");

out.println("<br>");
out.println("<a href='admin_dashboard.html'>Back to Dashboard</a>");

out.println("</body>");
out.println("</html>");

}
}
