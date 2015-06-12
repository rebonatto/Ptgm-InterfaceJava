/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olds;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Jorge Luis Boeira Bavaresco
 */
public class Uteis2 {        
    /**
     * Método que retorna a diferença em horas minutos e segundos entre o
     * Calendar informado e a data atual do sistema
     *
     * @param horaInicio Um Calendar com a data de inicio (informar dia mes ano
     * hora minuto e segundo)
     * @return string no formato hh:mm:ss contendo a diferença entre a data
     * informada e a data atual
     */    
    public static String getDiferencaTempo(Calendar horaInicio) {
        String retorno = "";
        String horaString = "";
        String minString = "";
        String secString = "";
        Calendar horaFim = Calendar.getInstance();
        long milisegundos = horaFim.getTimeInMillis() - horaInicio.getTimeInMillis();
        long diferencaSegundos = milisegundos / 1000;
        long horas = diferencaSegundos / 60 / 60;
        long segundos = diferencaSegundos;
        long minutos = (diferencaSegundos / 60);

        if (horas < 10) {//se hora for menor que 10 precisa colocar um 0 à esquerda
            horaString = "0" + horas;
        } else {
            horaString = "" + horas;
        }
        if ((minutos - (horas * 60)) < 10) {//se minuto for menor que 10 precisa colocar um 0 à esquerda
            minString = "0" + (minutos - (horas * 60));
        } else {
            minString = "" + (minutos - (horas * 60));
        }
        if ((segundos - (minutos * 60)) < 10) {//se segundo for menor que 10 precisa colocar um 0 à esquerda
            secString = "0" + (segundos - (minutos * 60));
        } else {
            secString = "" + (segundos - (minutos * 60));
        }


        String aux = horaString + ":" + minString  + ":" + secString;

        retorno = horas + ":" + (minutos - (horas * 60)) + ":" + (segundos - (minutos * 60));
        return aux;
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
    public static String getDiferencaTempoDiasHorasMinutosSegundos(Calendar horaInicio) {
        String retorno = "";
        Calendar horaFim = Calendar.getInstance();
        long milisegundos = horaFim.getTimeInMillis() - horaInicio.getTimeInMillis();
        long diferencaSegundos = milisegundos / 1000;
        long dias = diferencaSegundos / 60 / 60 / 24;
        long horas = diferencaSegundos / 60 / 60;
        long minutos = (diferencaSegundos / 60);
        long segundos = diferencaSegundos;
        retorno = dias + " dias, "
                + (horas - (dias * 24)) + " horas, "
                + (minutos - (horas * 60)) + " minutos, "
                + (segundos - (minutos * 60)) + " segundos";
        return retorno;
    }
//    public static void main(String[] args) throws ParseException {
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        Calendar horaInicio = Calendar.getInstance();
//        horaInicio.setTime(sdf.parse("02/05/2011 08:21:20"));
//        System.out.println("Diferença: " + Uteis.getDiferencaTempo(horaInicio));
//   //     System.out.println("Diferença com dias: " + Uteis.getDiferencaTempoDiasHorasMinutosSegundos(horaInicio));
//    }
}
