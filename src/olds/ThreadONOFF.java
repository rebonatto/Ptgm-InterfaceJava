/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package olds;

import java.awt.Event;
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

/**
 *
 * @author cliente
 */
class Teste extends Thread{
    EventoDAO dao = new EventoDAO();
    Evento evts = new Evento();
//    String sql = "select  a.codevento as evt, s.codsala as sala "+
//                 "from usosalacaptura usc, capturaondaatual a, usosala u, salacirurgia s" +
//                 "where usc.codondaatual = a.codondaatual and usc.codusosala = u.codusosala "+
//                 "and s.codsala = u.codsala; ";
    String sql = "select s.codsala as sala, a.codevento as evt "+
                " from salacirurgia s, capturaondaatual a, usosala u, usosalacaptura usc "+
                " where s.codsala= u.codsala and a.codondaatual = usc.codondaatual and u.codusosala = usc.codusosala";
                

    private String mensagem;


    public void run(){
        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        int linhas = 0, l = 0, x = 0;
        int evento = 0;
        int sala[] = new int [13];

        while(true){
            try {
                
                ResultSet rs = ps.executeQuery(sql);
                System.out.println("hora atual: " +  new Date() );
                

                while (rs.next()) {
                    evento = rs.getInt("evt");
                    x = rs.getInt("sala");
                    sala[x] = evento;
                }

                for (int i = 1; i<13; i++){
                    System.out.println("SALA "+i+ " evento "+sala[i] );
                }

//                if (linhas != l ){
//                    System.out.println("lol");
//                    l = linhas;
//
//                }

                if (sala[1] == 1){
                    //FormPControle

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

public class ThreadONOFF{



    public static void main(String[] args) {



        Teste thread1 = new Teste();
        System.err.println( "\nStarting threads" );

        thread1.start();
        System.err.println( "Threads started\n" );

        

    }
}