

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletConnection
 */
@WebServlet("/ServletConnection")
public class ServletConnection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletConnection() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		try
		{
		
		 Class.forName("com.mysql.jdbc.Driver");
		 System.out.println("DRIVER HAS LOADED SUCCESSFULLY");
		 Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz_portal_authentication","root","ram@DBconnection");
		 System.out.println("CONNECTED SUCCESSFULLY");
         Statement stm=conn.createStatement();
         ResultSet rs=stm.executeQuery("select * from login"); 
         while(rs.next()) 
         {
        	 String name=req.getParameter("uname");
    		 String pwd=req.getParameter("pwd");
    		 if(name.equals(rs.getString(1)) && pwd.contentEquals(rs.getNString(2)))
    		 {
    			 System.out.println("WELCOME "+name);
    			 res.sendRedirect("quiz_home.html");
    			 return;
    		 }
         }
         
         //System.out.println("INSERTED DATA SUCCESSFULLY");
         System.out.println("PLEASE ENTER VALID CREDENTIALS");
         stm.close();
         conn.close();
         
       }
       catch(Exception e)
       {
          System.out.println("Unable to Connect");
       }
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
