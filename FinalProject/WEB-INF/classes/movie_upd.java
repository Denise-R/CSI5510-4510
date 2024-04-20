import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class movie_upd extends HttpServlet 
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException,IOException
    {        // declare variables  
			Statement state4 = null;
			ResultSet result = null;
			ResultSet alertResult = null;        
			String query1="";        
			String alertQuery="";   
			String m_date="";
			String query2="";        
			Connection con=null; 

			String m_id = (request.getParameter("MovieID")).toString();
			String m_title = request.getParameter("Title");
			m_date = request.getParameter("ReleaseDate");
			String m_synopsis = request.getParameter("Synopsis");
			String m_length = (request.getParameter("LengthMin")).toString();
			String m_rating = request.getParameter("Rating");
			String m_cat = request.getParameter("Category");
			String m_cost = (request.getParameter("CostMil")).toString();
			String m_sType = request.getParameter("ScreeningType");

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
		if (m_cat.equals("Select") || m_rating.equals("Select") || m_sType.equals("Select")) {
			// If "Select" is chosen, display an error message or handle accordingly
			out.println("<script>function showAlertOnLoad() {alert(\"Error: Please choose a valid option other than 'Select' for the movie rating, category, and screening type.\");}</script>");
		}
		else if (m_date == null || m_date.trim().isEmpty()) {
			out.println("<script>function showAlertOnLoad() {alert(\"Error: Please enter a value for the movie release date.\");}</script>");
        }
		else{
			// alert query to notify that movie has been deleted
			try 
			{ 
				// check if movie id is valid
				alertQuery="SELECT title FROM movie where movieID = '" + m_id + "'";
				PreparedStatement pstmt1 = con.prepareStatement(alertQuery);
				alertResult = pstmt1.executeQuery();

				// displaying alert if movie id is not valid
				if(!alertResult.next()){
					out.println("<script>function showAlertOnLoad() {alert(\"Error: The movie you tried to update does not exist. Check to make sure the movie ID you entered is the one you intended.\");}</script>");
				}
				else
				{
					// exec queries to delete movie
					try{
						// creating alert that movie was deleted
						String title = alertResult.getString("title");
						out.println("<script>function showAlertOnLoad() {alert(\"You have updated the movie " + title + "\");}</script>");

							// building and executing delete queries
							query1 = "update MOVIE set Title = '" + m_title + "', ReleaseDate = TO_DATE('" + m_date + "', 'YYYY-MM-DD'), Synopsis = '" + m_synopsis + "', Rating = '" + m_rating + "', LengthMin = '" + m_length + "', Category = '" + m_cat + "', CostMil = '" + m_cost + "', ScreeningType = '" + m_sType + "' where MovieID = '" + m_id + "'";
							result=state4.executeQuery(query1);
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
		}
		

		
		// build query
		query2 = "SELECT MovieID, Title, ReleaseDate, Synopsis, Rating, LengthMin, Category, CostMil, ScreeningType FROM movie order by MovieID";
		
		//write to html file
		out.println("<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"utf-8\"><title>FinalProject</title>");
		out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=windows-1252\">");
		out.println("<link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en\"> ");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"\\FinalProject\\html\\CSS\\base.css\">");
       	
		//exec query
		try 
		{ 
			result=state4.executeQuery(query2);
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
		out.println("</p></div><h2 id=\"pageTitle\">Movie Table</h2></head>");
		out.println("<center><table>"); 
		out.println("<tr>");
        out.println("<th>Movie ID</th>");
        out.println("<th>Title</th>");
        out.println("<th>Release Date</th>");
        out.println("<th>Synopsis</th>");
		out.println("<th>Rating</th>");
		out.println("<th>Duration (min)</th>");
        out.println("<th>Category</th>");
		out.println("<th>Cost (mil)</th>");
		out.println("<th>Screening Type</th>");
        out.println("</tr>");

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
