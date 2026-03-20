import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class ViewSupportServlet extends HttpServlet {

Connection con;

public void init() throws ServletException{

try{

Class.forName("oracle.jdbc.driver.OracleDriver");

con = DriverManager.getConnection(
"jdbc:oracle:thin:@localhost:1521:XE",
"system",
"system"
);

}catch(Exception e){
e.printStackTrace();
}

}

protected void doGet(HttpServletRequest req,HttpServletResponse res)
throws ServletException,IOException{

res.setContentType("text/html");

PrintWriter out = res.getWriter();

out.println("<html><head><title>Support Requests</title>");

out.println("<style>");

out.println("body{font-family:Arial;background:#f4f0ff;margin:0;}");

out.println(".header{background:#6a0dad;color:white;padding:18px;text-align:center;font-size:24px;}");

out.println(".container{width:95%;margin:auto;margin-top:35px;}");

out.println("table{width:100%;border-collapse:collapse;background:white;"
+"box-shadow:0 4px 12px rgba(0,0,0,0.2);}");


out.println("th{background:#6a0dad;color:white;padding:12px;}");

out.println("td{padding:10px;text-align:center;border-bottom:1px solid #ddd;}");

out.println("tr:hover{background:#f1e6ff;}");

out.println(".back{margin-top:20px;display:inline-block;"
+"padding:10px 20px;background:#6a0dad;color:white;"
+"text-decoration:none;border-radius:6px;}");

out.println(".back:hover{background:#4b0082;}");

out.println("</style>");

out.println("</head><body>");

out.println("<div class='header'>Student Support Requests</div>");

out.println("<div class='container'>");

out.println("<table>");

out.println("<tr>");
out.println("<th>ID</th>");
out.println("<th>Name</th>");
out.println("<th>Email</th>");
out.println("<th>Subject</th>");
out.println("<th>Message</th>");
out.println("</tr>");

try{

Statement stmt = con.createStatement();

ResultSet rs = stmt.executeQuery(
"SELECT * FROM SUPPORT ORDER BY ID DESC"
);

while(rs.next()){

out.println("<tr>");

out.println("<td>"+rs.getInt("ID")+"</td>");
out.println("<td>"+rs.getString("NAME")+"</td>");
out.println("<td>"+rs.getString("EMAIL")+"</td>");
out.println("<td>"+rs.getString("SUBJECT")+"</td>");
out.println("<td>"+rs.getString("MESSAGE")+"</td>");

out.println("</tr>");

}

}catch(Exception e){

out.println("<h3>Error: "+e.getMessage()+"</h3>");

}

out.println("</table>");

out.println("<br>");

out.println("<a class='back' href='admin_dashboard.html'>Back to Dashboard</a>");

out.println("</div>");

out.println("</body></html>");

}

}
