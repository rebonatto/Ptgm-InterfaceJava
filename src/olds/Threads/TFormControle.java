/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olds.Threads;

import olds.Uteis2;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;
import modelo.Evento;
import persistencia.Conexao;
import persistencia.EventoDAO;
import Visualizacao.FormPControle;

/**
 *
 * @author cliente
 */
public class TFormControle extends Thread {

    EventoDAO dao = new EventoDAO();
    Evento evts = new Evento();
    
    // Consulta de evento e sala em uma soh sql
    String sql = "select u.codsala as sala, a.codevento as evt "
            + " from capturaondaatual a, usosala u, usosalacaptura usc "
            + " where a.codondaatual = usc.codondaatual "
            + " and u.codusosala = usc.codusosala "
            + " and a.codondaatual >= ?;";
    String sql_ini = "select max(codondaatual) as evt from capturaondaatual; ";
   
    private String mensagem;
    private int sala[] = new int[12];
    private int sala_at[] = new int [12];
    private FormPControle obj;
    private static int TEMPOTRIGGERATUALIZA = 3000;


    public TFormControle(int salaveio[], FormPControle _obj) {
        //recebe Salas
        this.sala = salaveio;
        obj = _obj;



    }

    public void run() {

        PreparedStatement ps = Conexao.getPreparedStatement(sql_ini);
        PreparedStatement ps2 = Conexao.getPreparedStatement(sql);
        int uso = 0;
        int evento = 0;
        int testeligado = 0;


        int ultevt = 0, ultevt_new = 0;
        ResultSet rs_ini;
        int cont = 0;

        try {
            rs_ini = ps.executeQuery();
            if (rs_ini.next()) {
                ultevt = rs_ini.getInt("evt");
            }

        } catch (Exception ex) {
            setMensagem("Erro de SQL " + ex.getMessage());
        }
        //ultevt = 9;

        while (true) {
            try {
                Thread.sleep(TEMPOTRIGGERATUALIZA);
                //System.out.println("Dormiu " + ++cont);

            } catch (InterruptedException ex) {
                System.out.println("Erro");
            }


            try {
                rs_ini = ps.executeQuery();
                if (rs_ini.next()) {
                    ultevt_new = rs_ini.getInt("evt");

                }
                //System.out.println("Ultimo " + ultevt + " novo " + ultevt_new);
                if (ultevt != ultevt_new) {
                    System.out.println(ultevt_new);
                    obj.AtualizaInterface();
//                    ps2.setInt(1, ultevt_new);
//                    ResultSet rs = ps2.executeQuery();
//                    int i = 0;
//                    while (rs.next()) {
//                        evento = rs.getInt("evt");
//                        uso = rs.getInt("sala");
//                        sala[uso - 1] = evento;
//                        try {
//                            obj.AtualizaRelogio(uso);
//                        } catch (ParseException ex) {
//                            Logger.getLogger(TFormControle.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                    System.out.println("Evento " + evento + " Sala " + uso);
//                    }
//                    obj.AtualizaOnOff();
                    ultevt = ultevt_new;

                }
            } catch (SQLException ex) {
                System.out.println("Pau na SQL segunda " + ex.getMessage());

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
