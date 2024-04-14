import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class showtimes_del extends HttpServlet 
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException,IOException
    {       
		 // declare variables   
			Statement state4 = null;
			ResultSet result = null;
			ResultSet alertResult = null;       

			String query1="";       
			String query2="";       
			Connection con=null; 
			String alertQuery="";  

          
			// getting parameters
            String show_id = (request.getParameter("ShowtimeID").toString());

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
		// alert query to notify that person has been deleted
		try 
		{ 
			alertQuery="select title FROM MOVIE m, SHOWTIMES s where m.movieID = s.MovieID and s.ShowtimeID = '" + show_id + "'";
			PreparedStatement pstmt1 = con.prepareStatement(alertQuery);
			alertResult = pstmt1.executeQuery();

	  	}
		catch (SQLException e) 
		{
			System.err.println("SQLException while executing SQL Statement."); 
		}

		//exec query
		try{
			while (alertResult.next()) {
			
				String title = alertResult.getString("title");
				out.println("<script>function showAlertOnLoad() {alert(\"You have deleted one showtime for the movie " + title + " \");}</script>");
			}
		}
		catch (SQLException e) 
		{
			System.err.println("SQLException while executing SQL Statement."); 
		}

		// build query
		query1 = "delete from SHOWTIMES where ShowtimeID = '" + show_id + "'";
		query2 = "select s.ShowtimeID, s.TheaterName, s.MovieID, m.title, s.ScreenID, s.Showtime, m.LengthMin FROM MOVIE m, SHOWTIMES s where m.movieID = s.MovieID order by s.ShowtimeID";
		
		//write to html file
		out.println("<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"utf-8\"><title>FinalProject</title>");
		out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=windows-1252\">");
		out.println("<link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en\"> ");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"\\FinalProject\\html\\CSS\\base.css\">");
       	
		//exec query
       	try 
		{ 
			result=state4.executeQuery(query1);
			result=state4.executeQuery(query2);
				
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
