/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testePersistencia;

import Uteis.Conversoes;
import java.util.Calendar;
import modelo.Formularios.BeanSupervisao;
import modelo.Equipamento;
import persistencia.UsoSalaExtrasDAO;

/**
 *
 * @author rebonatto
 */
public class testeUsoSalaExtras {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        UsoSalaExtrasDAO dao = new UsoSalaExtrasDAO();
        
        Calendar c = Calendar.getInstance();
        System.out.println(Conversoes.CalendarToTime(c));
        int ultUsoSala = dao.ultimoUsoSalaAtivo(1);
        /*
        for (Equipamento e : dao.listaEquipUsoSala(ultUsoSala)){
            System.out.println(e.getCodEquip() + " " + e.getCodModelo().getDesc() + " " + e.getTempoUso());
        }
        */
        
        for (BeanSupervisao bean : dao.listaUltimasFO(dao.listaEquipUsoSala(ultUsoSala), "cap")){
            System.out.println("Captura: " + bean.getCodCaptura().getCodCaptura());
            System.out.println("Tomada: " + bean.getNumTomada());
            System.out.println("Evento: " + bean.getCodEvento().getCodEvento());
            System.out.println("Equipamento: " + bean.getCodEquip().getCodEquip());
            System.out.println("------------------------------");
        }
        
        Calendar c1 = Calendar.getInstance();
        System.out.println(Conversoes.CalendarToTime(c1));
        
        for(BeanSupervisao bean : dao.listaUltimasFO(1)){
            System.out.println("Captura: " + bean.getCodCaptura().getCodCaptura());
            System.out.println("Tomada: " + bean.getNumTomada());
            System.out.println("Evento: " + bean.getCodEvento().getCodEvento());
            System.out.println("Equipamento: " + bean.getCodEquip().getCodEquip());
            System.out.println("------------------------------");
        }
        Calendar c2 = Calendar.getInstance();
        System.out.println(Conversoes.CalendarToTime(c2));
        
        System.out.println(dao.getMensagem());
    }
}
