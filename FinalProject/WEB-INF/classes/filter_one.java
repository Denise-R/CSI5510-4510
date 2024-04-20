import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class filter_one extends HttpServlet 
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException,IOException
    {        
		// declare variables
			Statement state4 = null;
			ResultSet result = null;
			String query="";        
			Connection con=null; 
			ResultSet alertResult = null;     
			String alertQuery="";  
          
			// get parameters
            String p_type = request.getParameter("PersonType");
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

		// creating alerts
		try{
			// checking if person id is valid
			alertQuery="SELECT FirstName, LastName FROM person where PersonID = '" + p_id + "'";
			PreparedStatement pstmt1 = con.prepareStatement(alertQuery);
			alertResult = pstmt1.executeQuery();

			// checking if p_type has been entered
			if (p_type.equals("Select")) {
				// If "Select" is chosen, display an error message or handle accordingly
				out.println("<script>function showAlertOnLoad() {alert(\"Error: Please choose a valid option other than 'Select'\");}</script>");
			}
			// checking if person exists
			if(!alertResult.next()){
				out.println("<script>function showAlertOnLoad() {alert(\"Error: The person ID you entered is not valid. Please reference the Person table.\");}</script>");
			}
		}
		catch (SQLException e) 
		{
			System.err.println("SQLException while executing SQL Statement."); 
		}
		
		// build query
		query = "SELECT m.MovieID, m.Title, m.ReleaseDate, m.Rating, m.LengthMin, m.Category, m.CostMil, p.FirstName, p.LastName, p.PayK, c.Contribution FROM MOVIE m, PERSON p, CREW c WHERE m.MovieID = c.MovieID AND p.personID = c.personID AND p.PersonID = '" + p_id + "' AND p.PersonType = '" + p_type + "' Order by m.MovieID";
		
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
		out.println("<p><a href=\"\\FinalProject\\html\\main_page.html\">");
		out.println("<img border=\"0\" src=\"\\FinalProject\\html\\CSS\\Images\\homeIcon.png\" width=\"30\" height=\"30\"></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
		
		// displaying filter settings
		out.println("</p></div><h2 id=\"pageTitle\">Filter Movies By Budget</h2><p>Crew Type: " + p_type + "; Person ID: " + p_id + "</p></head>");
		out.println("<center><br/><table>"); 
		out.println("<tr>");
		out.println("<th>Movie ID</th>");
		out.println("<th>Movie Title</th>");
		out.println("<th>Release Date</th>");
		out.println("<th>Rating</th>");
		out.println("<th>Duration (min)</th>");
		out.println("<th>Category</th>");
		out.println("<th>Cost (mil)</th>");
		out.println("<th>First Name</th>");
		out.println("<th>Last Name</th>");
		out.println("<th>Pay (K)</th>");
		out.println("<th>Contribution</th>");
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
                		out.println("<td>"+result.getString(6)+"</td>");
                		out.println("<td>"+result.getString(7)+"</td>");
                		out.println("<td>"+result.getString(8)+"</td>");
                		out.println("<td>"+result.getString(9)+"</td>");
                		out.println("<td>"+result.getString(10)+"</td>");
                		out.println("<td>"+result.getString(11)+"</td>");
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
