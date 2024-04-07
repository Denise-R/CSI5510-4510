import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class person_reg extends HttpServlet 
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException,IOException
    {        
			Statement state4 = null;
			ResultSet result = null;
			String query1="";      
			String query2="";      
			Connection con=null; 
          
			String p_fname = (request.getParameter("FirstName").toString());
			String p_lname = (request.getParameter("LastName").toString());
			String p_pay = (request.getParameter("PayK").toString());
			String p_type = (request.getParameter("PersonType").toString());

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
		
		query1 = "INSERT INTO PERSON(PersonID, LastName, FirstName, PayK, PersonType) " +
        " VALUES(seq_per_id.nextval, '" + p_lname + "', '" + p_fname + "', " + p_pay + ", '" + p_type + "')";

		query2 = "SELECT PersonID, FirstName,  LastName, PayK, PersonType FROM person order by PersonID";

		out.println("<html><head><title>FinalProject</title>");	 
		out.println("</head><body>");
		
		out.print( "<br /><b><center><font color=\"RED\"><H2>Person Table</H2></font>");
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
                out.println("     <td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">"+result.getString(1)+"</td>");
		    	out.println("     <td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">"+result.getString(2)+"</td>");
				out.println("     <td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">"+result.getString(3)+"</td>");
				out.println("     <td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">"+result.getString(4)+"</td>");
				out.println("     <td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">"+result.getString(5)+"</td>");
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
