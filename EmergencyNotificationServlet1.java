import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/Emergencysnotificatonservlt1")
public class EmergencyNotificationServlet1 extends HttpServlet {

protected void doGet(HttpServletRequest request,HttpServletResponse response)
throws ServletException,IOException{

response.setContentType("text/plain");

PrintWriter out=response.getWriter();

Connection con=null;

try{

Class.forName("oracle.jdbc.driver.OracleDriver");

con=DriverManager.getConnection(
"jdbc:oracle:thin:@localhost:1521:XE",
"system",
"system"
);

Statement stmt=con.createStatement();

ResultSet rs=stmt.executeQuery(
"SELECT COUNT(*) FROM EMERGENCY_ALERTS WHERE STATUS='Active'"
);

int count=0;

if(rs.next()){
count=rs.getInt(1);
}

if(count==0){
out.print("NONE");
}
else{
out.print(count);
}

con.close();

}
catch(Exception e){

out.print("NONE");

}

}
}
