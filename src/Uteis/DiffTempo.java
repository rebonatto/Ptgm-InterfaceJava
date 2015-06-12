/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Uteis;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Jorge Luis Boeira Bavaresco
 * alterado por rebonatto
 */
public class DiffTempo {    
    
    /**
     * Método que retorna a diferença em horas minutos e segundos entre o Calendar informado
     * e a data atual do sistema
     * @param horaInicio
     * @return string no formato hh:mm:ss contendo a diferença
     */
    public static String DiferencaTempoAtual(Calendar horaInicio) {
        String retorno = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Calendar horaFim = Calendar.getInstance();
            long milisegundos = horaFim.getTimeInMillis() - horaInicio.getTimeInMillis();
            int segundos = (int) milisegundos / 1000;
            int minutos = (int) (milisegundos / 1000 / 60);
            int horas = (int) milisegundos / 1000 / 60 / 60;
            retorno = retorno + horas + ":" ;
            if (minutos - (horas * 60) < 10)
                retorno = retorno + "0";
            retorno = retorno + (minutos - (horas * 60)) + ":";
            if (segundos - (minutos * 60) < 10)
                retorno = retorno + "0";
            retorno = retorno + (segundos - (minutos * 60) );

//            System.out.println("Data inicio: "+sdf.format(horaInicio.getTime()));
//            System.out.println("Data fim: "+sdf.format(horaFim.getTime()));
        } catch (Exception e) {
            System.out.println("Erro na função que retorno a diferença de data em hora minuto e segundo");
        }
        return retorno;
    }
    
    /**
     * Método que retorna a diferença em horas minutos e segundos entre 2 Calendar informados 
     * @param horaInicio
     * @param horaFim
     * @return string no formato hh:mm:ss contendo a diferença
     */
    public static String DiferencaTempo(Calendar horaInicio, Calendar horaFim) {
        String retorno = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            
            long milisegundos = horaFim.getTimeInMillis() - horaInicio.getTimeInMillis();
            int segundos = (int) milisegundos / 1000;
            int minutos = (int) (milisegundos / 1000 / 60);
            int horas = (int) milisegundos / 1000 / 60 / 60;
            if (horas < 10)
               retorno = "0";
            retorno = retorno + horas + ":" ;
            if (minutos - (horas * 60) < 10)
                retorno = retorno + "0";
            retorno = retorno + (minutos - (horas * 60)) + ":";
            if (segundos - (minutos * 60) < 10)
                retorno = retorno + "0";
            retorno = retorno + (segundos - (minutos * 60) );
//            System.out.println("Data inicio: "+sdf.format(horaInicio.getTime()));
//            System.out.println("Data fim: "+sdf.format(horaFim.getTime()));
        } catch (Exception e) {
            System.out.println("Erro na função que retorno a diferença de data em hora minuto e segundo");
        }
        return retorno;
    }
    
     /**
     * Método que retorna a diferença em dias horas minutos e segundos entre o
     * Calendar informado e a data atual do sistema
     *
     * @param horaInicio Um Calendar com a data de inicio (informar dia mes ano
     * hora minuto e segundo)
     * @return string contendo a diferença entre a data informada e a data atual
     * no formato n dias, n horas, n minutos, n segundos
     */
    public static String DiferencaTempocDiasAtual(Calendar horaInicio) {
        String retorno = "";
        Calendar horaFim = Calendar.getInstance();
        long milisegundos = horaFim.getTimeInMillis() - horaInicio.getTimeInMillis();
        long diferencaSegundos = milisegundos / 1000;
        long dias = diferencaSegundos / 60 / 60 / 24;
        long horas = diferencaSegundos / 60 / 60;
        long minutos = (diferencaSegundos / 60);
        long segundos = diferencaSegundos;
        retorno = dias + " d,"
                + (horas - (dias * 24)) + ":"
                + (minutos - (horas * 60)) + ":"
                + (segundos - (minutos * 60)) ;
        return retorno;
    }
    
     /**
     * Método que retorna a diferença em dias horas minutos e segundos entre o
     * 2 Calendar informados
     *
     * @param horaInicio Um Calendar com a data de inicio (informar dia mes ano
     * hora minuto e segundo)
     * @return string contendo a diferença entre a data informada e a data atual
     * no formato n dias, n horas, n minutos, n segundos
     */
    public static String DiferencaTempocDias(Calendar horaInicio, Calendar horaFim) {
        String retorno = "";
//        Calendar horaFim = Calendar.getInstance();
        long milisegundos = horaFim.getTimeInMillis() - horaInicio.getTimeInMillis();
        long diferencaSegundos = milisegundos / 1000;
        long dias = diferencaSegundos / 60 / 60 / 24;
        long horas = diferencaSegundos / 60 / 60;
        long minutos = (diferencaSegundos / 60);
        long segundos = diferencaSegundos;
        retorno = dias + " d, "
                + (horas - (dias * 24)) + ":"
                + (minutos - (horas * 60)) + ":"
                + (segundos - (minutos * 60)) ;
        return retorno;
    }
    
}
