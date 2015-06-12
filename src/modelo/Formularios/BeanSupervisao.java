/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.Formularios;

import modelo.CapturaAtual;
import modelo.Equipamento;
import modelo.Evento;

/**
 *
 * @author rebonatto
 */
public class BeanSupervisao {
    private Evento       codEvento;
    private int          numTomada;
    private Equipamento  codEquip;
    private CapturaAtual codCaptura;

    public Evento getCodEvento() {
        return codEvento;
    }

    public void setCodEvento(Evento codEvento) {
        this.codEvento = codEvento;
    }

    public int getNumTomada() {
        return numTomada;
    }

    public void setNumTomada(int numTomada) {
        this.numTomada = numTomada;
    }

    public Equipamento getCodEquip() {
        return codEquip;
    }

    public void setCodEquip(Equipamento codEquip) {
        this.codEquip = codEquip;
    }         

    public CapturaAtual getCodCaptura() {
        return codCaptura;
    }

    public void setCodCaptura(CapturaAtual codCaptura) {
        this.codCaptura = codCaptura;
    }        
}
