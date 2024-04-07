import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class filter_five extends HttpServlet 
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException,IOException
    {        
			Statement state4 = null;
			ResultSet result = null;
			String query="";        
			Connection con=null; 
          
            String crew_type = request.getParameter("CrewType");
            String cast_type = request.getParameter("CastType");
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
		
		query = "SELECT PERSON.PersonID, PERSON.FirstName, PERSON.LastName, PERSON.PayK, PERSON.PersonType FROM PERSON WHERE PERSON.PersonID NOT IN (SELECT  p.PersonID FROM MOVIE m, PERSON p, CAST c WHERE m.MovieID = c.MovieID AND p.personID = c.personID" +
		" And m.MovieID in (SELECT m.movieID FROM MOVIE m, PERSON p, CREW c WHERE m.MovieID = c.MovieID AND p.personID = c.personID AND p.PersonID = '" + p_id + "' AND p.PersonType = '" + crew_type + "') AND p.PersonType = '" + cast_type + "') AND PERSON.PersonType = '" + cast_type + "'";
		
		out.println("<html><head><title>FinalProject</title>");	 
		out.println("</head><body>");
		
		out.print( "<br /><b><center><font color=\"RED\"><H2>Cast Members That Do not Work With Crew Members</H2></font>");
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
        out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">Person ID</td>");
        out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">First Name</td>");
        out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">Last Name</td>");
        out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">Pay (K)</td>");
		out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">Person Type</td>");
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
