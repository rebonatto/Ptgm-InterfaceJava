/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Periculosidade;

import modelo.CapturaAtual;

/**
 *
 * @author rebonatto
 */
public class StatusPericulosidade {
    public static String Status(CapturaAtual c){
        String corrente   = CorrenteRMS.StatusCorrente(c);
        String frequencia = FrequenciaCorrente.StatusFrequencia(c);
        String similaridade = new String(); // Completar a similaridade
        
        //System.out.println("Corrente: " + corrente + " Freq: " + frequencia + " " + frequencia.charAt(0));
        
        if (corrente.charAt(0) == 'D' ) // Dangerous
            return corrente;
        
        if (frequencia.charAt(0) == 'D') // Dangerous
            return frequencia;
        
        
        if (corrente.charAt(0) == 'A' ) // Atention
            return corrente;        
        
        if (frequencia.charAt(0) == 'A' ) // Atention
            return frequencia;
            
        
        return "Normal";
    }
}
