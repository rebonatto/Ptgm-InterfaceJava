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
public class CorrenteRMS {
    private static float normal     = 0.06F;
    private static float atention    = 0.1F ;
    private static float intervention = 0.5F;
       
    public CorrenteRMS(float _normal, float _atention, float _intervention){        
        this.normal       = _normal;
        this.atention     = _atention;
        this.intervention = _intervention;       
    }

    public float getNormal() {
        return normal;
    }

    public float getAtention() {
        return atention;
    }

    public float getIntervention() {
        return intervention;
    }        
    
    public static String StatusCorrente(CapturaAtual cap){        
        if (cap.getEficaz() > intervention)
            return ("Dangerous");
        else if (cap.getEficaz() > atention)
            return ("Atention");
        return "Normal";     
    }
}
