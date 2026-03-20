import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class TrackComplaintServlet extends HttpServlet {

protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {

response.setContentType("text/html");
PrintWriter out = response.getWriter();

String complaint_id = request.getParameter("complaint_id");

try{

Class.forName("oracle.jdbc.driver.OracleDriver");

Connection con = DriverManager.getConnection(
"jdbc:oracle:thin:@localhost:1521:XE",
"system",
"manager"
);

PreparedStatement ps = con.prepareStatement(
"SELECT STATUS FROM QUERIES WHERE ID=?"
);

ps.setString(1, complaint_id);

ResultSet rs = ps.executeQuery();

out.println("<h2>Complaint Status</h2>");

if(rs.next()){

out.println("<h3>Status : "+rs.getString("STATUS")+"</h3>");

}else{

out.println("<h3>Complaint ID Not Found</h3>");

}

con.close();

}catch(Exception e){

out.println(e);

}

}
}
