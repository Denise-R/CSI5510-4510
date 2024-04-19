import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class movie_reg extends HttpServlet 
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException,IOException
    {        
			Statement state4 = null;
			ResultSet result = null;
			String query1=""; 
			String query2=""; 
			String m_date="";
			Connection con=null; 
          
			String m_title = request.getParameter("Title");
			var date = request.getParameter("ReleaseDate");
			String m_synopsis = request.getParameter("Synopsis");
			String m_length = (request.getParameter("LengthMin")).toString();
			String m_rating = request.getParameter("Rating");
			String m_cat = request.getParameter("Category");
			String m_cost = (request.getParameter("CostMil")).toString();
			String m_sType = request.getParameter("ScreeningType");

			var month = (('0' + (date.getMonth() + 1)).slice(-2)). toString(); // Adding 1 because January is 0
			var day = ('0' + date.getDate()).slice(-2);
			var year = date.getFullYear();

    		m_date = year + "-" + month +"-" + day;

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

		//TO_DATE('12-04-2024', 'DD-MM-YYYY')
		
		query1 = "INSERT INTO MOVIE (MovieID, Title, ReleaseDate, Synopsis, Rating, LengthMin, Category, CostMil, ScreeningType) " +
		" VALUES (seq_mov_id.nextval, '" + m_title + "', TO_DATE(''" + m_date + "'', 'YYYY-MM-DD'), ' " + m_synopsis + "', '" + m_rating + "', " + m_length + ", '" + m_cat + "', " + m_cost + ", '" + m_sType + "')";

		query2 = "SELECT MovieID, Title, ReleaseDate, Synopsis, Rating, LengthMin, Category, CostMil, ScreeningType FROM movie order by MovieID";


		out.println("<html><head><title>FinalProject</title>");	 
		out.println("</head><body>");
		
		out.print( "<br /><b><center><font color=\"RED\"><H2>Movie Table</H2></font>");
        out.println( "</center><br />" );
       	try 
		{ 
			result=state4.executeQuery(query1);
			result=state4.executeQuery(query2);
	  	}
		catch (SQLException e) 
		{
			System.err.println("SQLException while executing SQL Statement."); 
		}
		out.println("<center><table border=\"1\">"); 
		out.println("<tr BGCOLOR=\"#cccccc\">");
        out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">Movie ID</td>");
        out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">Title</td>");
        out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">Release Date</td>");
        out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">Synopsis</td>");
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
                out.println("     <td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">"+result.getString(1)+"</td>");
		    	out.println("     <td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">"+result.getString(2)+"</td>");
				out.println("     <td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">"+result.getString(3)+"</td>");
				out.println("     <td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">"+result.getString(4)+"</td>");
				out.println("     <td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">"+result.getString(5)+"</td>");
				out.println("     <td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">"+result.getString(6)+"</td>");
				out.println("     <td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">"+result.getString(7)+"</td>");
				out.println("     <td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">"+result.getString(8)+"</td>");
				out.println("     <td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">"+result.getString(9)+"</td>");
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
