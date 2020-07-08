

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletRegisterConnection
 */
@WebServlet("/ServletRegisterConnection")
public class ServletRegisterConnection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRegisterConnection() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		try
		{
		 String name=req.getParameter("uname");
		 String pwd=req.getParameter("pwd");
		 Class.forName("com.mysql.jdbc.Driver");
		 System.out.println("DRIVER HAS LOADED SUCCESSFULLY");
		 Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz_portal_authentication","root","ram@DBconnection");
		 System.out.println("CONNECTED SUCCESSFULLY");
         PreparedStatement stm=conn.prepareStatement("insert into login values(?,?)");
         stm.setString(1, name);
         stm.setString(2,pwd);
         System.out.println("INSERTED DATA SUCCESSFULLY");
         res.sendRedirect("index.html");
         stm.execute();
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		doGet(request, response);
	}

}
