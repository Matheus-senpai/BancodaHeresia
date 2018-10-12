/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Heresia;

import Class.Conta;
import Class.extrato;
import caddao.Connect;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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


 @WebServlet(name = "operation", urlPatterns = {"/operation"})
public class operation extends HttpServlet {

    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Connect con = new Connect();
    private  Connection conexao;
    
public operation() throws SQLException, ClassNotFoundException{
    this.conexao = con.getConnection();
}    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
     
            request.setCharacterEncoding("UTF8");
            response.setContentType("html");
            Conta cont = new Conta();
        try {
          
            cont = conta_buscar(request.getParameter("Conta"));
        } catch (SQLException ex) {
            Logger.getLogger(operation.class.getName()).log(Level.SEVERE, null, ex);
        }
         
            if(cont.getId_con() <= 0){
            PrintWriter out = response.getWriter();  
            out.println("<b>Conta Inesistente</b>");
            out.println("<a href='/BancoHeresia'>Back</b>");
            }else{
            extrato o = new extrato();
            o.setCpf(request.getParameter(""));
            o.setValor(Float.parseFloat(request.getParameter("")));
           
        try {
                
                DateFormat formatter ; 
                Date date ; 
                formatter = new SimpleDateFormat("");
                date = (Date)formatter.parse(request.getParameter("")); 
                o.setData(date);
        } catch (ParseException ex) {
            Logger.getLogger(operation.class.getName()).log(Level.SEVERE, null, ex);
        }
            
      
       String sql = "INSERT INTO `teste`.`Operacao` (`idOperacao`, `DataOperacao`, `ValorOperacao`, "
               + "`CPFResponsavelOperacao`, `idContaCorrente`) VALUES"+
                    "(NULL, '" +  new java.sql.Date(o.getData().getTime())+ "', "
               + "'" + o.getValor()+ "','" + o.getCpf()+ "','" + cont.getId_con()+ "')";
        try {
            ps = conexao.prepareStatement(sql);
            ps.execute(sql);
            ps.close();
            PrintWriter out = response.getWriter();  
            out.println("<b>Cadastro realizado com sucesso!</b>");
            out.println("<a href='/BancoHeresia'>Back</b>");
        } catch (SQLException ex) {
            Logger.getLogger(operation.class.getName()).log(Level.SEVERE, null, ex);
        }
       
            
            }
            }
            
            
         
     public Conta conta_buscar(String conta) throws SQLException {

        String sql = "SELECT * FROM Conta WHERE `NumeroConta` = '" +conta+ "'";
        ps = conexao.prepareStatement(sql);
        rs = ps.executeQuery();
        Conta cc = new Conta();
        while (rs.next()) {
            cc.setCPF(rs.getString(""));
            cc.setNumConta(rs.getString(""));
            cc.setId_con(rs.getInt(""));
           
        }

        ps.close();
        return cc;
    }
            
             
             
             
        }

