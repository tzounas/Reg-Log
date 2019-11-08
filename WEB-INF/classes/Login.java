import javax.servlet.*;
import java.io.*;
import java.sql.*;
public class Login extends GenericServlet
{
    public void service(ServletRequest req,ServletResponse res) throws ServletException,IOException
    {
        res.setContentType("text/html");
        PrintWriter out=res.getWriter();
       
        Connection con=null;
        Statement stm=null;
        ResultSet rs=null;
        String username=req.getParameter("username");
        String pass=req.getParameter("pass");
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/users","root","1234");
            String query = "select * from users_db where username='"+username+"' and Password='"+pass+"'";
            stm= con.createStatement();
            rs=stm.executeQuery(query);
            if(rs.next()){
                out.println("<h1>Welcome</h1>");
                out.println("<p>Username - "+rs.getString("username")+"</p>");
            }
            else
                out.println("<h1 style='background-color: #eb1616'>Invalid email or password !</h1>");
        }
        catch(ClassNotFoundException e){
            out.println(e);
        }
        catch(SQLException e){
            out.println(e);
        }
        finally
        {
            try
            {
                rs.close();
                stm.close();
                con.close();
            }
            catch(SQLException e){
            out.println(e);
            }
        }
        
    }
}