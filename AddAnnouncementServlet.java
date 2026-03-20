import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class AddAnnouncementServlet extends HttpServlet {

protected void doPost(HttpServletRequest req,HttpServletResponse res)
throws ServletException,IOException{

res.setContentType("text/html");
PrintWriter out = res.getWriter();

String title = req.getParameter("title");
String message = req.getParameter("message");

try{

Class.forName("oracle.jdbc.driver.OracleDriver");

Connection con = DriverManager.getConnection(
"jdbc:oracle:thin:@localhost:1521:",
"system",
"system"
);

PreparedStatement ps = con.prepareStatement(
"INSERT INTO ANNOUNCEMENTS VALUES(ANN_SEQ.NEXTVAL,?,?,SYSDATE)"
);

ps.setString(1,title);
ps.setString(2,message);

ps.executeUpdate();

con.close();

out.println("<html><head><title>Announcement Added</title>");

out.println("<style>");

out.println("body{font-family:Arial;background:#f4f0ff;text-align:center;}");

out.println(".box{margin:120px auto;width:400px;background:white;padding:40px;border-radius:10px;box-shadow:0 4px 12px rgba(0,0,0,0.2);}");


out.println("h2{color:#6a0dad;}");

out.println("a{display:inline-block;margin-top:20px;background:#6a0dad;color:white;padding:10px 15px;text-decoration:none;border-radius:5px;}");

out.println("</style>");

out.println("</head><body>");

out.println("<div class='box'>");

out.println("<h2>Announcement Published Successfully</h2>");

out.println("<a href='admin_dashboard.html'>Back to Dashboard</a>");

out.println("</div>");

out.println("</body></html>");

}
catch(Exception e){

out.println(e);

}

}

}
