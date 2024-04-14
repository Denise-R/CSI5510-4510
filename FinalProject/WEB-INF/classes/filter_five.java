import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class filter_five extends HttpServlet 
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException,IOException
    {        
			// declare variables 
			Statement state4 = null;
			ResultSet result = null;
			String query="";        
			Connection con=null; 
          
			// get parameters
            String crew_type = request.getParameter("CrewType");
            String cast_type = request.getParameter("CastType");
            String p_id = (request.getParameter("PersonID")).toString();


		try
		{	
			// connect to SQLPlus database			
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
		
		// build query
		query = "SELECT PERSON.PersonID, PERSON.FirstName, PERSON.LastName, PERSON.PayK, PERSON.PersonType FROM PERSON WHERE PERSON.PersonID NOT IN (SELECT  p.PersonID FROM MOVIE m, PERSON p, CAST c WHERE m.MovieID = c.MovieID AND p.personID = c.personID" +
		" And m.MovieID in (SELECT m.movieID FROM MOVIE m, PERSON p, CREW c WHERE m.MovieID = c.MovieID AND p.personID = c.personID AND p.PersonID = '" + p_id + "' AND p.PersonType = '" + crew_type + "') AND p.PersonType = '" + cast_type + "') AND PERSON.PersonType = '" + cast_type + "'";
		
		//write to html file
		out.println("<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"utf-8\"><title>FinalProject</title>");
		out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=windows-1252\">");
		out.println("<link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en\"> ");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"\\FinalProject\\html\\CSS\\base.css\">");
		
		//exec query
       	try 
		{ 
			result=state4.executeQuery(query);
				
	  	}
		catch (SQLException e) 
		{
			System.err.println("SQLException while executing SQL Statement."); 
		}

		//write to html
		out.println("</head><body onload=\"showAlertOnLoad()\"><br/><br/><br/><br/><br/><br/><br/><section id=\"javaSection\">");
		out.println("<head><div style=\"float: right;\">");
		out.println("<p><a href=\"\\FinalProject\\index.html\">");
		out.println("<img border=\"0\" src=\"\\FinalProject\\html\\CSS\\Images\\homeIcon.png\" width=\"30\" height=\"30\"></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
		
		// displaying filter settings
		out.println("</p></div><h2 id=\"pageTitle\">Filter Cast Members That Do not Work With Crew Members</h2><p>Crew Type: " + crew_type + "; Person ID (Crew): " + p_id + "; Cast Type: " + cast_type + "</p></head>");
		out.println("<center><br/><table>"); 
		out.println("<tr>");
        out.println("<th>Person ID</th>");
        out.println("<th>First Name</th>");
        out.println("<th>Last Name</th>");
        out.println("<th>Pay (K)</th>");
		out.println("<th>Person Type</th>");
        out.println("</tr>");
          
		//get table data from query executed
		try 
		{ 
            while(result.next()) 
			{ 
		    		out.println("<tr>");
                		out.println("<td>"+result.getString(1)+"</td>");
                		out.println("<td>"+result.getString(2)+"</td>");
                		out.println("<td>"+result.getString(3)+"</td>");
                		out.println("<td>"+result.getString(4)+"</td>");
                		out.println("<td>"+result.getString(5)+"</td>");
                		out.println("</tr>");
			} 
	    }
		catch (SQLException e) 
		{
			System.out.println("Resutset is not connected"); 
		}

		out.println("</table></CENTER>");

		// close connection
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

		//finish html document
		out.println("<center><br/><br/><p><b>Created By: Guohuan Feng, Edie Harvey, Kevin Karafili, Allison Offer, Denise Rauschendorfer</b></p></section>");
  		out.println("</body></html>");
    } 
}
