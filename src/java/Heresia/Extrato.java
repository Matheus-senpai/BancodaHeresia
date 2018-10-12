/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Heresia;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
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
import Class.Conta;
import caddao.Connect;
/**
 *
 * @author Matheus16
 */


   @WebServlet(name = "Extrato", urlPatterns = {"/Extrato"})
public class Extrato extends HttpServlet {

    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Connect con = new Connect();
    private  Connection conexao;
    
public Extrato() throws SQLException, ClassNotFoundException{
    this.conexao = con.getConnection();
}    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
           
            
            request.setCharacterEncoding("UTF8");
            response.setContentType("html");
            Conta cont = new Conta();
        try {
                try {
                    cont = conta_buscar(request.getParameter("Conta"));
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Extrato.class.getName()).log(Level.SEVERE, null, ex);
                }
        } catch (SQLException ex) {
            Logger.getLogger(Extrato.class.getName()).log(Level.SEVERE, null, ex);
        }
           
            if(cont.getId_con() <= 0){
            PrintWriter out = response.getWriter();  
            out.println("<b>Conta Inesistente</b>");
            out.println("<a href='/banco'>Voltar</b>");
            }else{
              
       String sql = "SELECT * FROM `Operacao` WHERE `idContaCorrente` ="+cont.getId_con()+
               " ORDER BY `Operacao`.`DataOperacao` ASC";
        try {
            ps = conexao.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            PrintWriter out = response.getWriter();

            String texto = " <p>CPF:"+cont.getCPF()+"<p><b>"+"<p>Número da conta: "
                    + ""+cont.getNumConta()+"</p><b>\""
                    + "<style type=\"text/css\">\n" +
".tg  {border-collapse:collapse;border-spacing:0;}\n" +
".tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;"
                    + "overflow:hidden;word-break:normal;border-color:black;}\n" +
".tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px "
                    + "5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;"
                    + "border-color:black;}\n" +
".tg .tg-0pky{border-color:inherit;text-align:left;vertical-align:top}\n" +
".tg .tg-0lax{text-align:left;vertical-align:top}\n" +
"</style><table class=\"tg\">\n" +
"  <tr>\n" +
"    <th class=\"tg-0pky\">Data</th>\n" +
"    <th class=\"tg-0pky\">Valor</th>\n" +
"    <th class=\"tg-0lax\">CPF</th>\n" +
"    <th class=\"tg-0lax\">id</th>\n" +
"    <th class=\"tg-0lax\">Tipo OP</th>\n" +
"  </tr>";
           
        
        while (rs.next()) {
            String op;
            BigDecimal b = new BigDecimal(0);
            BigDecimal a = rs.getBigDecimal("");
            if(a.compareTo(b) > 0 ){
                op = "C";
            }else{
                op = "D";    
            }
           //concaterna o html
             texto += "<tr>\n" +
"    <td class=\"tg-0lax\">"+rs.getDate("DataOperacao")+"</td>\n" +
"    <td class=\"tg-0lax\">"+rs.getBigDecimal("ValorOperacao")+"</td>\n" +
"    <td class=\"tg-0lax\">"+rs.getString("CPFResponsavelOperacao")+"</td>\n" +
"    <td class=\"tg-0lax\">"+rs.getInt("idContaCorrente")+"</td>\n" +
"    <td class=\"tg-0lax\">"+op+"</td>\n" +
"  </tr>\n";
           
        }
            out.println(texto+"\"</table><br>Saldo Final:"+total_soma(cont.getId_con())+
                    "<br><a href='/BancoHeresia'>Back</b>\"");
        } catch (SQLException ex) {
            Logger.getLogger(Extrato.class.getName()).log(Level.SEVERE, null, ex);
        }
       
            
            }
            }
              
     public Conta conta_buscar(String conta) throws SQLException, ClassNotFoundException {

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
    
      public String total_soma (int id) throws SQLException {

        String sql = "SELECT SUM(ValorOperacao) as soma FROM `Operacao` WHERE `idContaCorrente` = "
                + ""+id+"";
        ps = conexao.prepareStatement(sql);
        rs = ps.executeQuery();
        String soma = "";
        while (rs.next()) {
            soma = rs.getString("soma");
                       
        }

        ps.close();
        return soma;
    }
            
             
             
             
        }
        