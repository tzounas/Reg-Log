import javax.servlet.*;
import java.io.*;
import java.sql.*;
public class Register extends GenericServlet
{
    public void service(ServletRequest req,ServletResponse res) throws ServletException,IOException
    {
        res.setContentType("text/html");
        PrintWriter out=res.getWriter();
        
       
        Connection con=null;
        Statement stm=null;
        String email=req.getParameter("email");
        String username=req.getParameter("username");
        String pass=req.getParameter("pass");
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/users","root","1234");
            String query = "insert into users_db values('"+email+"','"+username+"','"+pass+"')";
            stm= con.createStatement();
            int count=stm.executeUpdate(query);
            if(count==0)
                out.println("<h1 style='background-color: #eb1616'>Registration Failed</h1>");
            else{
                out.println("<h1>Registration sucessfull</h1>");
            }
               
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
                stm.close();
                con.close();
            }
            catch(SQLException e){
            out.println(e);
            }
        }
        
        
    }
}