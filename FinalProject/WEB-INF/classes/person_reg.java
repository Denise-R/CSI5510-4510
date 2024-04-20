import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class person_reg extends HttpServlet 
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException,IOException
    {       
		// declare variables  
			Statement state4 = null;
			ResultSet result = null;      

			String query1="";      
			String query2="";      
			Connection con=null;   

          // get parameters
			String p_fname = (request.getParameter("FirstName").toString());
			String p_lname = (request.getParameter("LastName").toString());
			String p_pay = (request.getParameter("PayK").toString());
			String p_type = (request.getParameter("PersonType").toString());

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

		if (p_type.equals("Select")) {
			// If "Select" is chosen, display an error message or handle accordingly
			out.println("<script>function showAlertOnLoad() {alert(\"Error: Please choose a valid option other than 'Select' for the person type.\");}</script>");
		}
		else{
			// build query
			query1 = "INSERT INTO PERSON(PersonID, LastName, FirstName, PayK, PersonType) " +
			" VALUES(seq_per_id.nextval, '" + p_lname + "', '" + p_fname + "', " + p_pay + ", '" + p_type + "')";

			//exec query
			try 
			{ 
				result=state4.executeQuery(query1);
				out.println("<script>function showAlertOnLoad() {alert(\"You have added the person " + p_fname + " " + p_lname + "\");}</script>");

			}
			catch (SQLException e) 
			{
				System.err.println("SQLException while executing SQL Statement."); 
			}
		}
		
		

		query2 = "SELECT PersonID, FirstName,  LastName, PayK, PersonType FROM person order by PersonID";

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
		out.println("</p></div><h2 id=\"pageTitle\">Person Table</h2></head>");
		out.println("<center><table>"); 
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
                out.println("     <td>"+result.getString(1)+"</td>");
		    	out.println("     <td>"+result.getString(2)+"</td>");
				out.println("     <td>"+result.getString(3)+"</td>");
				out.println("     <td>"+result.getString(4)+"</td>");
				out.println("     <td>"+result.getString(5)+"</td>");
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
