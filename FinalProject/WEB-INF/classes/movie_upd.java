import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class movie_upd extends HttpServlet 
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException,IOException
    {        
		 // declare variables 
			Statement state4 = null;
			ResultSet alertResult = null;       
			ResultSet result = null;
			String query1="";        
			String query2="";    
			String alertQuery="";  
			Connection con=null; 
          
			// get parameters
            String m_id = request.getParameter("m_id");
			String m_title = request.getParameter("m_title");
			String m_date = request.getParameter("m_date");
			String m_synopsis = request.getParameter("m_synopsis");
			String m_length = request.getParameter("m_length");
			String rating_id = request.getParameter("rating_id");
			String cat_id = request.getParameter("cat_id");

		try
		{			
			// connect to SQLPlus database
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver()); 
            con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "project", "project");
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

			// checking if movie exists
			if(!alertResult.next()){
				out.println("<script>function showAlertOnLoad() {alert(\"Error: The movie ID you entered is not valid. Please reference the Movie table.\");}</script>");
			}
			else{
				try{
					// creating alert query that person was updated
					String old_title = alertResult.getString("title");
					out.println("<script>function showAlertOnLoad() {alert(\"You have updated the movie " + old_title + "\");}</script>");
					try 
					{ 
						// building and exec update query
						query1 = "update  movie set m_title = '"+m_title+"', m_date = TO_DATE('" + m_date + "', 'YYYY-MM-DD'), m_length = '"+m_length+"', rating_id = '"+rating_id+"', cat_id = '"+cat_id+"', m_synopsis = '"+m_synopsis+"'	where m_id = '"+m_id+"'";
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
