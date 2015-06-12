/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Uteis;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import javax.swing.JOptionPane;

/**
 *
 * @author rebonatto
 */

public class Conversoes {
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private static SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
    private static SimpleDateFormat sdtf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

   
    public static Time StringToTime(String s){    
        java.util.Date data = null;  
        try {
            data = stf.parse(s);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Erro converter tempo");  
        }
        Time time = new Time(data.getTime());  
        return time;
    }
    
    public static Calendar StringToCalendar(String s){
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(s));
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Erro converter data");
            c = null;
        }
        return (c);
    }
    
//    public static String CalendarToTimeString(Calendar c){
//        return(String.valueOf(c.HOUR) + ":" + String.valueOf(c.MINUTE) + ":" + String.valueOf(c.SECOND));
//    }

    public static String CalendarToString(Calendar c){
        Date d = new Date(c.getTimeInMillis());
        return (sdf.format(d));
    }
    
    public static String CalendarToStringDH(Calendar c){
        
        Timestamp t = new Timestamp(c.getTimeInMillis());        
        return (sdtf.format(t));
    }

    public static Date CalendarToDate(Calendar c){
        Date d = new Date(c.getTimeInMillis());
        return (d);
    }

    public static Calendar DateToCalendar(Date  d){
        Calendar cal = Calendar.getInstance();
        TimeZone.getDefault();
        cal.setTimeZone(TimeZone.getDefault() );
        cal.setTime(d);
        return (cal);
    }
    
    public static Time CalendarToTime(Calendar c){
        
        if (c == null){
            return (new Time(0));
        }
        Time t = new Time(c.getTimeInMillis());
        return t;
    }
    
    public static Calendar TimeToCalendar(Time t){
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(t.getTime());
        return c;
    }

    public static Timestamp CalendarToTimeStamp(Calendar c){
        Timestamp t = new Timestamp(c.getTimeInMillis());
        return t;
    }
    
    public static Calendar TimeStampToCalendar(Timestamp t){
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(t.getTime());
        return c;
    }
    
    public static String IntSegundosToTempo(int uso){
        //int anos, meses;
        int dias, horas, minutos, segundos;
        int sobra;
        String ret;
        
        dias = uso / 86400; 
        sobra = uso - dias * 86400;
        horas = sobra / 3600;
        sobra = sobra - horas * 3600;
        minutos = sobra / 60;
        segundos = sobra - minutos * 60;
        
        ret = String.valueOf(dias) + "D " + String.valueOf(horas) + "H " + String.valueOf(minutos)+":"+ String.valueOf(segundos);
        return ret;
    }
    
    public static int TempoToIntSegundos(String tempo){
        //int anos, meses;
        int dias, horas, minutos;
        int sobra;
        int ret = 0;
        String straux;
        int aux=0;
                
        dias = tempo.lastIndexOf("D");
        straux = tempo.substring(0, dias);        
        aux = Integer.parseInt(straux.trim());
        ret += aux * 86400;
        
        horas = tempo.lastIndexOf("H");
        straux = tempo.substring(dias+1, horas);
        aux = Integer.parseInt(straux.trim());        
        ret += aux * 3600;
        
        minutos = tempo.lastIndexOf(":");
        straux = tempo.substring(horas+1, minutos);
        aux = Integer.parseInt(straux.trim());
        ret += aux * 60;
        
        straux = tempo.substring(minutos+1);
        aux = Integer.parseInt(straux.trim());
        ret += aux;
        
        return ret;
    }
}
