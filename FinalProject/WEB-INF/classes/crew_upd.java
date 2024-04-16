import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class crew_upd extends HttpServlet 
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException,IOException
    {       
		// declare variables  
			Statement state4 = null;
			ResultSet result = null;
			ResultSet alertResult = null;        
			ResultSet alertResult2 = null;        
			ResultSet alertResult3 = null;        
			String query1="";        
			String query2="";        
			String alertQuery="";   
			String alertQuery2="";   
			String alertQuery3="";   
			Connection con=null; 
          
			// get parameters
			String c_id = (request.getParameter("CrewID").toString());
			String c_contr = (request.getParameter("Contribution").toString());
			String m_id = (request.getParameter("MovieID").toString());
			String p_id = (request.getParameter("PersonID").toString());

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

		// alert query to notify that crew has been deleted
		try 
		{ 
			//Queries to check if user entries are valid
			alertQuery="SELECT FirstName, LastName FROM person where PersonID = '" + p_id + "'";
			alertQuery2="SELECT Contribution FROM crew where CrewID = '" + c_id + "'";
			alertQuery3="SELECT title from movie where movieID = '" + m_id + "'";
			PreparedStatement pstmt1 = con.prepareStatement(alertQuery);
			alertResult = pstmt1.executeQuery();
			PreparedStatement pstmt2 = con.prepareStatement(alertQuery2);
			alertResult2 = pstmt2.executeQuery();
			PreparedStatement pstmt3 = con.prepareStatement(alertQuery3);
			alertResult3 = pstmt3.executeQuery();

			// Checking if person_id is valid
			if(!alertResult.next()){
				out.println("<script>function showAlertOnLoad() {alert(\"Error: The person ID you entered is not valid.\");}</script>");
			}
			// Checking if crew_id is valid
			else if(!alertResult2.next()){
				out.println("<script>function showAlertOnLoad() {alert(\"Error: The crew ID you entered is not valid.\");}</script>");
			}
			// Checking if movie_id is valid
			else if(!alertResult3.next()){
				out.println("<script>function showAlertOnLoad() {alert(\"Error: The movie ID you entered is not valid.\");}</script>");
			}
			else
			{
				//exec query to delete
				try{
					// creating delete alert
					String fname = alertResult.getString("FirstName");
					String lname = alertResult.getString("LastName");
					out.println("<script>function showAlertOnLoad() {alert(\"You have updated one record for the crew member " + fname + " " + lname + "\");}</script>");
					try 
					{ 
						// build query
						query1 = "update  crew set Contribution = '"+c_contr+"' where CrewID = '"+c_id+"' and PersonID = '"+p_id+"' and MovieID = '"+m_id+"'";
						result=state4.executeQuery(query1);
							
					}
					catch (SQLException e) 
					{
						System.err.println("SQLException while executing SQL Statement."); 
					}
				}
				catch (SQLException e) 
				{
					System.err.println("SQLException while executing SQL Statement."); 
				}
			}

	  	}
		catch (SQLException e) 
		{
			System.err.println("SQLException while executing SQL Statement."); 
		}



		// build query
		
		query2 = "select c.CrewID, m.MovieID, m.title, p.PersonID,  p.FirstName, p.LastName, c.Contribution, p.PayK, p.PersonType FROM MOVIE m, PERSON p, CREW c WHERE m.MovieID = c.MovieID AND p.personID = c.personID order by c.CrewID";
		
		//write to html file
		out.println("<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"utf-8\"><title>FinalProject</title>");
		out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=windows-1252\">");
		out.println("<link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en\"> ");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"\\FinalProject\\html\\CSS\\base.css\">");
	
		//write to html
		out.println("</head><body onload=\"showAlertOnLoad()\"><br/><br/><br/><br/><br/><br/><br/><section id=\"javaSection\">");
		out.println("<head><div style=\"float: right;\">");
		out.println("<p><a href=\"\\FinalProject\\html\\main_page.html\">");
		out.println("<img border=\"0\" src=\"\\FinalProject\\html\\CSS\\Images\\homeIcon.png\" width=\"30\" height=\"30\"></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
		out.println("</p></div><h2 id=\"pageTitle\">Crew Table</h2></head>");
		out.println("<center><table>"); 
		out.println("<tr>");
        out.println("<th>Crew ID</th>");
        out.println("<th>Movie ID</th>");
        out.println("<th>Movie Title</th>");
        out.println("<th>Person ID</th>");
		out.println("<th>First Name</th>");
        out.println("<th>Last Name</th>");
		out.println("<th>Contribution</th>");
		out.println("<th>Pay (K)</th>");
		out.println("<th>Person Type</th>");
        out.println("</tr>");

		//exec query
       	try 
		{ 
			result=state4.executeQuery(query2);
	  	}
		catch (SQLException e) 
		{
			System.err.println("SQLException while executing SQL Statement."); 
		}
		//get table data from query executed
		try 
		{ 
            while(result.next()) 
			{ 
		    	out.println("<tr>");
                out.println("     <td>"+result.getString(1)+"</td>");
		    	out.println("     <td>"+result.getString(2)+"</td>");
				out.println("     <td>"+result.getString(3)+"</td>");
				out.println("     <td>"+result.getString(4)+"</td>");
				out.println("     <td>"+result.getString(5)+"</td>");
				out.println("     <td>"+result.getString(6)+"</td>");
				out.println("     <td>"+result.getString(7)+"</td>");
				out.println("     <td>"+result.getString(8)+"</td>");
				out.println("     <td>"+result.getString(9)+"</td>");
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
