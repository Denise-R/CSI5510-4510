import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class filter_three extends HttpServlet 
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException,IOException
    {        
			Statement state4 = null;
			ResultSet result = null;
			String query="";        
			Connection con=null; 
          
            String p_type = request.getParameter("PersonType");
            String p_id = (request.getParameter("PersonID")).toString();

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
		
		query = "SELECT m.MovieID, m.Title, m.ReleaseDate, m.Rating, m.LengthMin, m.Category, m.CostMil, m.ScreeningType " +
		" FROM MOVIE m, (SELECT distinct m.MovieID FROM MOVIE m, PERSON p, CREW c WHERE m.MovieID = c.MovieID AND p.personID = c.personID AND p.PersonID = '" + p_id + "' AND p.PersonType = '" + p_type + "') MOVIESOFINTEREST " +
		" WHERE m.MovieID = MOVIESOFINTEREST.MovieID and m.CostMil = (SELECT MAX(m.CostMil) FROM MOVIE m, PERSON p, CREW c WHERE m.MovieID = c.MovieID AND p.personID = c.personID AND p.PersonID = '" + p_id + "' AND p.PersonType = '" + p_type + "') order by m.MovieID";
		
		out.println("<html><head><title>FinalProject</title>");	 
		out.println("</head><body>");
		
		out.print( "<br /><b><center><font color=\"RED\"><H2>Most Expensive Movie by Crew Member</H2></font>");
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
          out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">Release Date</td>");
          out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">Rating</td>");
          out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">Duration (min)</td>");
          out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">Category</td>");
          out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">Cost (mil)</td>");
          out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">Screening Type</td>");
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
                		out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">"+result.getString(6)+"</td>");
                		out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">"+result.getString(7)+"</td>");
                		out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">"+result.getString(8)+"</td>");
                		out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">"+result.getString(9)+"</td>");
                		out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">"+result.getString(10)+"</td>");
                		out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">"+result.getString(11)+"</td>");
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
