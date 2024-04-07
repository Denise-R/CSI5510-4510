import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class filter_nine extends HttpServlet 
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException,IOException
    {        
			Statement state4 = null;
			ResultSet result = null;
			String query="";        
			Connection con=null; 
          
            String m_date = (request.getParameter("ReleaseDate")).toString();

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
		
		query = "select m.MovieID, m.title, p.FirstName, p.LastName, p.personType FROM MOVIE m, PERSON p, Crew c WHERE m.MovieID = c.MovieID AND p.personID = c.personID AND m.movieID = (select m.MovieID FROM MOVIE m WHERE m.ReleaseDate like '%" + m_date + "' AND m.CostMil = (SELECT MAX(m.CostMil) FROM MOVIE m WHERE m.MovieID in (select m.MovieID FROM MOVIE m WHERE m.ReleaseDate like '%" + m_date + "')))";
		
		out.println("<html><head><title>FinalProject</title>");	 
		out.println("</head><body>");
		
		out.print( "<br /><b><center><font color=\"RED\"><H2>Most expensive Movie per Year</H2></font>");
        out.println( "</center><br />" );
       	try 
		{ 
			result=state4.executeQuery(query);
				
	  	}
		catch (SQLException e) 
		{
			System.err.println("SQLException while executing SQL Statement."); 
		}
		out.println("<center><table border=\"1\">"); 
		out.println("<tr BGCOLOR=\"#cccccc\">");
          out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">Movie ID</td>");
          out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">Movie Title</td>");
          out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">First Name</td>");
          out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">Last Name</td>");
          out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">Cast/Crew Type</td>");
        out.println("</tr>");
		try 
		{ 
            while(result.next()) 
			{ 
		    		out.println("<tr>");
                		out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">"+result.getString(1)+"</td>");
                		out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">"+result.getString(2)+"</td>");
                		out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">"+result.getString(3)+"</td>");
                		out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">"+result.getString(4)+"</td>");
                		out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">"+result.getString(5)+"</td>");
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
