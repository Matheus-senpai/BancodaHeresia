
package Heresia;

import Class.Conta;
import caddao.Connect;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Matheus16
 */
@WebServlet(name = "Cad", urlPatterns = {"/Cad"})
public class Cad extends HttpServlet {

    private PreparedStatement ps = null;
    private final ResultSet rs = null;
    private final Connect Driver = new Connect();
    private final  Connection getConnection;
    
     
    
    
public Cad() throws SQLException, ClassNotFoundException{
    this.getConnection = Connect.getConnection();
}
    
    
        @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
            request.setCharacterEncoding("UTF8");
            response.setContentType("html");
           
            Conta c = new Conta();
            c.setCPF(request.getParameter("CPF"));
            c.setNumConta(request.getParameter("NumConta"));
            
             Connection con = Connect.getConnection();
            
           try { 
            String sql = ("INSERT INTO  Conta (idContaCorrente, NumeroConta, CPF) "
                    + "VALUES (NULL, '" + c.getNumConta()+ "', '" + c.getCPF() + "')");
       
            ps = con.prepareStatement(sql);
            ps.execute(sql);
            ps.close();
            PrintWriter out = response.getWriter();  
            out.println("<b> Sucesso ao cadastrar!</b>");
            out.println("<a href='/BancodaHeresia'>back</b>");
        } catch (SQLException ex) {
            Logger.getLogger(Cad.class.getName()).log(Level.SEVERE, null, ex);
        }
    
   {
     
    }

    }

    
    }
