import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
public class TableData extends HttpServlet
{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String names[] = request.getParameterValues("tables");
		String html = "<html><head><title>List of Tables</title><link rel='stylesheet' type='text/css' href='css/style.css'/></head>"+"<body>Table Data</body>";
		out.println(html);
		for(String name : names)
		{
			try
			{
				out.println("<table><thead>"+ name +"</thead>");
				Class.forName(driver);
				Connection connection = DriverManager.getConnection(database_url,username,password);
				Statement statement = connection.createStatement();
				ResultSet result_set = st.executeQuery("select * from "+ name);
				ResultSetMetaData rsmd = result_set.getMetaData();
				int num = rsmd.getColumnCount();
				for(int i = 1 ; i <= num; i++)
				{
					out.println("<th>"+ rsmd.getcolumnName(i) +"</th>");
				}
				out.print("</tr>");
				while(result_set.next())
				{
					out.print("<tr>");
					for(int i=1;i<=num;i++)
					{
						out.print("<td>"+ result_set.getString(i)+"</td>");
					}
					out.print("</tr>");
					out.println("</table></body></html>");					
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
}
