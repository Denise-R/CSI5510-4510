import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class showtimes_reg extends HttpServlet 
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException,IOException
    {        // declare variables  
			Statement state4 = null;
			ResultSet alertResult = null;       
			ResultSet alertResult2 = null;       
			ResultSet alertResult3 = null;       
			ResultSet result = null;
			String query1=""; 
			String query2="";       
			String s_time="";
			String alertQuery="";  
			String alertQuery2="";  
			String alertQuery3="";  
			Connection con=null; 

			String m_id = (request.getParameter("MovieID")).toString();
			String s_theater = request.getParameter("TheaterName");
			s_time = request.getParameter("Showtime");
			String screen_id = (request.getParameter("ScreenID")).toString();

			String outputFormat = "yyyy/MM/dd HH:mm:ss";
			String formattedDate = "";
			// Define input date format
			DateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
			// Define output date format
			DateFormat outputDateFormat = new SimpleDateFormat(outputFormat);

			try {
				// Parse input date string
				Date date = inputDateFormat.parse(s_time);
				// Format the parsed date into the desired format
				formattedDate = outputDateFormat.format(date);
				System.out.println("Formatted Date: " + formattedDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}

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

		// alert query to notify that person has been updated
		try 
		{ 
			// creating alert queries
			alertQuery="SELECT title FROM movie where movieID = '" + m_id + "'";
			PreparedStatement pstmt1 = con.prepareStatement(alertQuery);
			alertResult = pstmt1.executeQuery();
			alertQuery2="SELECT title FROM movie where movieID = '" + m_id + "' and ScreeningType = 'Showing'";
			PreparedStatement pstmt2 = con.prepareStatement(alertQuery2);
			alertResult2 = pstmt2.executeQuery();
			alertQuery3="SELECT TheaterName FROM THEATER where TheaterName = '" + s_theater + "' and ScreenID = '" + screen_id + "'";
			PreparedStatement pstmt3 = con.prepareStatement(alertQuery3);
			alertResult3 = pstmt3.executeQuery();

			if (s_theater.equals("Select")) {
				// If "Select" is chosen, display an error message or handle accordingly
				out.println("<script>function showAlertOnLoad() {alert(\"Error: Please choose a valid option other than 'Select' for the theater name.\");}</script>");
			}
			else if (s_time == null || s_time.trim().isEmpty()) {
				out.println("<script>function showAlertOnLoad() {alert(\"Error: Please enter a value for the showtime.\");}</script>");
			}
			// checking if movie exists
			else if(!alertResult.next()){
				out.println("<script>function showAlertOnLoad() {alert(\"Error: The movie ID you entered is not valid. Please reference the movie table.\");}</script>");
			}
			// Checking if movie is showing
			else if(!alertResult2.next()){
				String a_title = alertResult.getString("title");
				out.println("<script>function showAlertOnLoad() {alert(\"Error: The movie " + a_title + " you entered is not listed as 'Showing'. To add a new showtime for this movie, update the Screening Type in the under the movie update page.\");}</script>");
			}
			// Checking if movieID is valid
			else if(!alertResult3.next()){
				out.println("<script>function showAlertOnLoad() {alert(\"Error: The screen ID you entered is not valid for the theater '" + s_theater +"'. Please reference the theater table.\");}</script>");
			}
			else{
				try{


					// creating alert query that person was updated
					String title = alertResult.getString("title");
					out.println("<script>function showAlertOnLoad() {alert(\"You have added a showtime for the movie " + title + "\");}</script>");
					try 
					{ 
						// building and exec update query
						query1 = "INSERT INTO SHOWTIMES (ShowtimeID, TheaterName, MovieID, ScreenID, Showtime) VALUES (seq_show_id.nextval, '" + s_theater + "', '" + m_id + "', '" + screen_id + "', TO_DATE('" + formattedDate + "', 'yyyy/mm/dd hh24:mi:ss'))";			
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
		query2 = "select s.ShowtimeID, s.TheaterName, s.MovieID, m.title, s.ScreenID, s.Showtime, m.LengthMin FROM MOVIE m, SHOWTIMES s where m.movieID = s.MovieID order by s.ShowtimeID";
		
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
		out.println("</p></div><h2 id=\"pageTitle\">Showtimes Table</h2></head>");
		out.println("<center><table>"); 
		out.println("<tr>");
        out.println("<th>Showtime ID</th>");
        out.println("<th>Theater Name</th>");
        out.println("<th>Movie ID</th>");
        out.println("<th>Title</th>");
		out.println("<th>Screen No.</th>");
		out.println("<th>Showtime</th>");
        out.println("<th>Duration (min)</th>");
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
