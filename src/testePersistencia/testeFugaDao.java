/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testePersistencia;

import Uteis.Conversoes;
import java.sql.Time;
import java.util.Calendar;
import modelo.Formularios.BeanFuga;
import modelo.Equipamento;
import modelo.Tomada;
import persistencia.FugasDAO;

/**
 *
 * @author rebonatto
 */
public class testeFugaDao {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        FugasDAO fugadao = new FugasDAO();
        Equipamento eqp = new Equipamento();
        Tomada tom = new Tomada();
        tom.setCodTomada(1);
        eqp.setCodEquip(2);
        Calendar c = Calendar.getInstance();
        Calendar p = Calendar.getInstance();
        p.set(2013, 02, 16);
        c.set(2013, 02, 20);
        
        System.out.println(Conversoes.CalendarToTime(c));
        //fugadao.lista();
        for(BeanFuga bf : fugadao.lista(eqp, p, c)){            
            if (bf.getTermino() != null){
//                System.out.println(bf.getTermino());
//                System.out.println(bf.getCaptura().getDataAtual());
                String s = Uteis.DiffTempo.DiferencaTempo(bf.getCaptura().getDataAtual(), bf.getTermino());
                //Integer i = new Integer(s.substring(5, 7));
                //if (i > 5)
                //System.out.println(i);
                    System.out.println("Fuga " + bf.getCaptura().getCodCaptura() + " " + bf.getCaptura().getCodTomada().getCodTomada() + " Ini " + Conversoes.CalendarToTimeStamp(bf.getCaptura().getDataAtual()) + " fim " + Conversoes.CalendarToTimeStamp(bf.getTermino()) + " dif " + Uteis.DiffTempo.DiferencaTempo(bf.getCaptura().getDataAtual(), bf.getTermino()));
            }
            else
                System.out.println("Fuga sem termino " + bf.getCaptura().getCodCaptura() );
            
        }
        System.out.println(Conversoes.CalendarToTime(c));
        c = Calendar.getInstance();
        System.out.println(Conversoes.CalendarToTime(c));

        System.out.println(fugadao.getMensagem());
    }
}
