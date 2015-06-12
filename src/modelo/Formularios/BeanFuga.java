/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.Formularios;

import java.util.Calendar;
import modelo.CapturaAtual;

/**
 *
 * @author rebonatto
 */
public class BeanFuga {
    private CapturaAtual captura;
    private Calendar     termino;

    public CapturaAtual getCaptura() {
        return captura;
    }

    public void setCaptura(CapturaAtual captura) {
        this.captura = captura;
    }

    public Calendar getTermino() {
        return termino;
    }

    public void setTermino(Calendar termino) {
        this.termino = termino;
    }
        
}
