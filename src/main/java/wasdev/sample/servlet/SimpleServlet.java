package wasdev.sample.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
/**
 * Servlet implementation class SimpleServlet
 */
@WebServlet("/SimpleServlet")
public class SimpleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           response.setContentType("text/html");
 //       response.getWriter().print("Hello World!");
        
        Context initContext;
		try {
			initContext = new InitialContext();
			DataSource ds = (DataSource)initContext.lookup("jdbc/kim-dashDB for Analytics-7n");
			Connection conn = ds.getConnection();
		//	System.out.println("yes");
			 response.getWriter().print("DashDB is connected now!</br>");
		    Statement stmt = conn.createStatement();
          
                    stmt.execute("DELETE FROM DOGS WHERE dalmatian = 'husky'");
		    stmt.execute("INSERT INTO DOGS VALUES ('meo1', 'husky' )");
		    stmt.execute("INSERT INTO DOGS VALUES ('meo2', 'husky' )");
            
		    stmt.executeQuery("SELECT * FROM DOGS WHERE dalmatian = 'husky'");
		    ResultSet rs = stmt.getResultSet();
		    System.out.println("yes");
	           while(rs.next()) {
                         response.getWriter().print(rs.getString(1)+"  "+rs.getString(2)+"</br>");
			} 
			stmt.close();
			conn.close();
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

    }

}
