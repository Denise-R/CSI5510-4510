import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class movie_del extends HttpServlet 
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException,IOException
    {        
			Statement state4 = null;
			ResultSet result = null;
			String query1="";        
			String query2="";        
			String query3="";        
			String query4="";        
			Connection con=null; 
          
            String m_id = (request.getParameter("MovieID").toString());
			String m_title = request.getParameter("Title");

		try
		{			
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver()); 
            con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "finalProject", "finalProject");
	       	System.out.println("Congratulations! You are connected successfully.");      
     	}
        catch(SQLException e)
		{	
			System.out.println("Error: "+e);	
		}
		catch(Exception e) 
		{
			System.err.println("Exception while loading  driver");		
		}
	    try 
		{
        	state4 = con.createStatement();
		} 
		catch (SQLException e) 	
		{
			System.err.println("SQLException while creating statement");			
		}
		
		response.setContentType("text/html");
		PrintWriter out = null ;
		try
		{
			out =  response.getWriter();
		}
		catch (IOException e) 
		{
  			e.printStackTrace();
		}
		
		query1 = "delete from cast where MovieID = '" + m_id + "'";
		query2 = "delete from crew where MovieID = '" + m_id + "'";
		query3 = "delete from showtimes where MovieID = '" + m_id + "'";
		query4 = "delete from movie where MovieID = '" + m_id + "' and Title = '" + m_title + "'";

		out.println("<html><head><title>Movie has been deleted</title>");	 
		out.println("</head><body>");
		
		out.print( "<br /><b><center><font color=\"BLACK\"><H2>One Record has deleted</H2></font>");
        out.println( "</center><br />" );
       	try 
		{ 
			result=state4.executeQuery(query1);
			result=state4.executeQuery(query2);
			result=state4.executeQuery(query3);
			result=state4.executeQuery(query4);
				
	  	}
		catch (SQLException e) 
		{
			System.err.println("SQLException while executing SQL Statement."); 
		}
		out.println("<center><table border=\"1\">"); 
		out.println("<tr BGCOLOR=\"#cccccc\">");
          out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\"> </td>");
       // out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">movtitle</td>");
        out.println("</tr>");
		try 
		{ 
            while(result.next()) 
			{ 
		    		out.println("<tr>");
                		out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">"+result.getString(1)+"</td>");
		    		//out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">"+result.getString(2)+"</td>");
                		out.println("</tr>");
			} 
	    }
		catch (SQLException e) 
		{
			System.out.println("Resutset is not connected"); 
		}

		out.println("</table></CENTER>");
		try 
		{ 
   			result.close(); 
			state4.close(); 	
			con.close();
    		System.out.println("Connection is closed successfully.");
 	   	}
		catch (SQLException e) 
		{
			e.printStackTrace();	
		}

  		out.println("</body></html>");
    } 
}
