import java.io.IOException;
import java.io.PrintWriter;

import com.mysql.*;
import java.sql.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/logic")
public class LogicServlet extends HttpServlet 
{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
//		System.out.println(1);
		PrintWriter out = res.getWriter();
		String url = "jdbc:mysql://localhost:3306/test";
		String username = "root";
		String password = "";
		String query = "select * from students where deptname = " + req.getParameter("dept_name");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection con = null;
		Statement st = null;
		try {
			con = DriverManager.getConnection(url,username,password);
//			System.out.println(con);
			st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next())
			{
				out.print(rs.getString("name") + " : ");
				out.print(rs.getString("id") + "\n");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				con.close();
				st.close();
			} 
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
