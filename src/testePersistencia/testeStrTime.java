/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testePersistencia;

import Uteis.Conversoes;
import java.sql.Time;

/**
 *
 * @author rebonatto
 */
public class testeStrTime {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String tempo = "10:30:00";
        Time t = new Time(0);
        t = Conversoes.StringToTime(tempo);
        System.out.println("*"+t.toString()+"*");
    }
}
