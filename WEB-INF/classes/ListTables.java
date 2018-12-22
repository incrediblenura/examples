import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
public class ListTables extends HttpServlet
{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String html = "<html><head><title>List of Tables</title><link rel='stylesheet' type='text/css' href='css/style.css'/></head>"					+ "<body>List of Tables</body>";
		String driver = "oracle.jdbc.driver.OracleDriver";
		String database_url = "url_to_your_database";
		String username = "yourusername";
		String password = "ypurpassword";
		try
		{
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(database_url,username,password);
			Statement statement = connection.createStatement();
			ResultSet result_set = st.executeQuery("select table_name from all_tables where owner='incrediblenura'");
			
			out.println(html+"<form action='TableData' method='post'><table><tr><th>CHECKBOX</th><th>TBALE NAME</th></tr>");
			while(result_set.next())
			{
				String name_of_table = result_set.getString(1);
				out.println("<tr><td><input type='checkbox' name='tables' value='" + name_of_table + "'> </td><td>" + name_of_table + "</td></tr>");
			}
			out.println("</table><input type='submit' value='Show Data'/></form></body></html>");
			result_set.close();
			connection.close();
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
			connection.close();
		}
		finally
		{
			connection.close();
		}
	}
}
