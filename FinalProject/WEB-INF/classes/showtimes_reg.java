import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class showtimes_reg extends HttpServlet 
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException,IOException
    {        
			Statement state4 = null;
			ResultSet result = null;
			String query1="";       
			//String query2="";  
			String show_date="";
			Connection con=null; 
          
            String theaterName = (request.getParameter("TheaterName").toString());
            String m_id = (request.getParameter("MovieID").toString());
            String s_id = (request.getParameter("ScreenID").toString());
            show_date = request.getParameter("Showtime");

		try
		{			
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver()); 
            con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "finalProject", "finalProject");
			System.out.println("Congratulations! You are connected successfully.");      
			System.out.println(show_date);      
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
		
		
		PrintWriter out = null ;
		try
		{
			out =  response.getWriter();
		}
		catch (IOException e) 
		{
  			e.printStackTrace();
		}


		

		query1 = "INSERT INTO SHOWTIMES (ShowtimeID, TheaterName, MovieID, ScreenID, Showtime) " +
		" VALUES (seq_show_id.nextval, '" + theaterName + "', " + m_id + ", " + s_id + ", TO_DATE('" + show_date + "', 'YYYY/MM/DD hh24:mi:ss'))";

       	try 
		{ 
			result=state4.executeQuery(query1);
	  	}
		catch (SQLException e) 
		{
			System.err.println("SQLException while executing SQL Statement."); 
		}

		
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

  		
    } 
}
