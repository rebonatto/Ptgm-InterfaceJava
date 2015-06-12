/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testePersistencia;

import Graficos.CriaGrafico;
import Uteis.Conversoes;
import java.sql.Time;
import java.util.Calendar;
import modelo.CapturaAtual;

/**
 *
 * @author rebonatto
 */
public class testeConveersoes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Calendar c = Calendar.getInstance();
        System.out.println(Conversoes.CalendarToTime(c));
        Time t = new Time(c.getTimeInMillis());
        System.out.println(Conversoes.TimeToCalendar(t));
        
        CapturaAtual cap = new CapturaAtual();                        
               
    }
}
