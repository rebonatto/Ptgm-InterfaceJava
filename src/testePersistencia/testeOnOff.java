/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



package testePersistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JTextField;
import modelo.Evento;
import modelo.SalaCirurgia;
import modelo.UsoSala;
import persistencia.Conexao;
import persistencia.EventoDAO;
import persistencia.SalaCirurgiaDAO;
import persistencia.UsoSalaDAO;
import Visualizacao.FormPControle;


public class testeOnOff{
    public static void main(String[] args) {

        Teste2 sala1 = new Teste2();
        System.err.println( "\nStarting threads" );

        sala1.start();
        System.err.println( "Threads started\n" );
    }
}

class Teste2 extends Thread{
    UsoSalaDAO usdao = new UsoSalaDAO();
    UsoSala uso = new UsoSala();
    SalaCirurgiaDAO sdao = new SalaCirurgiaDAO();
    SalaCirurgia sala = new SalaCirurgia();
    //String sql = "select max(u.codusosala) as uso, u.ativa as atv from salacirurgia s, usosala u"+
    //             "where s.codsala=1 and u.codsala = s.codsala and u.ativa=1 ";
    String sql = " select max(u.codusosala) as uso, u.codsala as sala, u.ativa as atv"+
                 " from usosala u, salacirurgia s where s.codsala=1 and s.codsala=u.codsala and u.ativa=1; ";
    private String mensagem;


    public void run(){
        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        int ativo = 0, l = 0, l2 = 0, linhas = 0;

        while(true){
            try {

                ResultSet rs = ps.executeQuery(sql);
                //System.out.println("hora atual: " +  new Date() );


                if (rs.next()) {
                    linhas = rs.getInt("uso");
                    ativo = rs.getInt("atv");

                  
                }

                if (ativo == 1 && l==0){
                    System.out.println(" Ligado ");
                    l=1;
                    
                }

                if (ativo == 0 && l ==1 && l2==1){
                    System.out.println("OFF");
                    l=0;
                }

                if (ativo == 0 && l == 0 && l2==0){
                    System.out.println("OFF");
                    l2=1;
                }

                

                

                //System.out.println("--------------------------------------\n\n");
                setMensagem("Relação de Marca gerada com sucesso");


                setMensagem("Marca não encontrado");
            } catch (SQLException ex) {
                setMensagem("Problemas ao localizar Marca. Erro de SQL " + ex.getMessage());
            }

            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                System.out.println("Erro");
            }

        }

    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

}

