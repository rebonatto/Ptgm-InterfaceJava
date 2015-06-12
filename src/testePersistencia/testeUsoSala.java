/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testePersistencia;

import Uteis.Conversoes;
import java.sql.Time;
import java.util.Calendar;
import modelo.Formularios.BeanSupervisao;
import modelo.CapturaAtual;
import modelo.HarmAtual;
import modelo.UsoSala;
import persistencia.UsoSalaDAO;
import persistencia.UsoSalaExtrasDAO;

/**
 *
 * @author rebonato
 */
public class testeUsoSala {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        UsoSalaDAO dao = new UsoSalaDAO();
        UsoSalaExtrasDAO extradao = new UsoSalaExtrasDAO();
        Calendar c = Calendar.getInstance();
        
        System.out.println(Conversoes.CalendarToTime(c));
        
        UsoSala u = dao.localiza(38);
        System.out.println(dao.getMensagem());
        
        for(CapturaAtual cap : extradao.listaCapturas(u)){
            System.out.print(" Captura " + cap.getCodCaptura() );
            System.out.print(" Tomada " + cap.getCodTomada().getCodTomada());
            System.out.print(" Equipamento " + cap.getCodEquip().getCodEquip());
            System.out.print(" Evento " + cap.getCodEvento().getCodEvento());
            System.out.println(" tempo " + Conversoes.CalendarToTimeStamp(cap.getDataAtual()));
        }
               
        
        /*
        System.out.println("Salas ativas");
        for(UsoSala s : dao.Lista()){
            System.out.print  (s.getCodUsoSala() + "  " );
            System.out.print  (s.getCodSala().getCodSala()+ "  " );
            System.out.print  (s.getCodProced().getDesc() + "  " );
            System.out.print  (s.getCodResp().getDesc() + "  " );
            System.out.print  (Conversoes.CalendarToDate(s.getHoraInicio()) + "  ");
            System.out.print  (Conversoes.CalendarToTime(s.getHoraInicio()) + "  ");                                    
            
            System.out.println("--------------------------------------");
        }
        System.out.println(dao.getMensagem());
        
        System.out.println("Form Supervisao");
        for(BeanSupervisao bean : extradao.listaUltimasFO(1)){
            System.out.print  (bean.getNumTomada() + " ");
            System.out.print  (bean.getCodEquip().getCodEquip() + " " + bean.getCodEquip().getRfid() + " ");
            System.out.println(bean.getCodEvento().getCodEvento() + " " + bean.getCodEvento().getDesc());      
            System.out.println(bean.getCodCaptura().getCodCaptura());
            
            for(HarmAtual oa : bean.getCodCaptura().getHarmAtual())
                System.out.println("Harmonica " + oa.getHarmonica() + " Sen " + oa.getSen() + " Cos " + oa.getCos());
            
            System.out.println("--------------------------------------");
        }
        */
        
        System.out.println(dao.getMensagem());
    }
    

}
