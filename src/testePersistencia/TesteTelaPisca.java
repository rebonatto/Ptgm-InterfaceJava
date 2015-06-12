/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package testePersistencia;

import Uteis.Conversoes;
import java.sql.Date;
import java.util.Calendar;
import modelo.CapturaAtual;
import persistencia.CapturaAtualDAO;

/**
 *
 * @author rebonatto
 */
public class TesteTelaPisca {
    public static void main(String[] args) {
        CapturaAtualDAO dao = new CapturaAtualDAO();
        Calendar c = Calendar.getInstance();
        Date datatmp = new Date(1);
        c = Conversoes.DateToCalendar(new Date(System.currentTimeMillis()));
        c.set(2014, 11, 16);
        System.out.println(c.getTime());
        
        datatmp = Conversoes.CalendarToDate(c);
        
        System.out.println(datatmp);
    
        for (CapturaAtual bean : dao.listaFugas(datatmp)){
            System.out.println(bean.getCodCaptura());
            
        }
    }
}
