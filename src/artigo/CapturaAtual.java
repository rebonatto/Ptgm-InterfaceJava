/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package artigo;

import java.util.Calendar;



/**
 *
 * @author Eduardo
 * Modified by: rebonatto
 */

public class CapturaAtual {
    private int codCaptura;
    private int codTomada;
    private int codTipoOnda;
    private int codEquip;    
    private int codEvento;
    private float valorMedio;
    private float offset;
    private float gain;
    private float eficaz;
    private Calendar dataAtual;   

    public int getCodCaptura() {
        return codCaptura;
    }

    public void setCodCaptura(int codCaptura) {
        this.codCaptura = codCaptura;
    }

    public int getCodTomada() {
        return codTomada;
    }

    public void setCodTomada(int codTomada) {
        this.codTomada = codTomada;
    }

    public int getCodTipoOnda() {
        return codTipoOnda;
    }

    public void setCodTipoOnda(int codTipoOnda) {
        this.codTipoOnda = codTipoOnda;
    }

    public int getCodEquip() {
        return codEquip;
    }

    public void setCodEquip(int codEquip) {
        this.codEquip = codEquip;
    }

    public int getCodEvento() {
        return codEvento;
    }

    public void setCodEvento(int codEvento) {
        this.codEvento = codEvento;
    }

    public float getValorMedio() {
        return valorMedio;
    }

    public void setValorMedio(float valorMedio) {
        this.valorMedio = valorMedio;
    }

    public float getOffset() {
        return offset;
    }

    public void setOffset(float offset) {
        this.offset = offset;
    }

    public float getGain() {
        return gain;
    }

    public void setGain(float gain) {
        this.gain = gain;
    }

    public float getEficaz() {
        return eficaz;
    }

    public void setEficaz(float eficaz) {
        this.eficaz = eficaz;
    }

    public Calendar getDataAtual() {
        return dataAtual;
    }

    public void setDataAtual(Calendar dataAtual) {
        this.dataAtual = dataAtual;
    }
    
}
