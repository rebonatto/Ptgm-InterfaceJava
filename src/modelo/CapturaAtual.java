/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.util.Calendar;
import java.util.Collection;


/**
 *
 * @author Eduardo
 * Modified by: rebonatto
 */

public class CapturaAtual {
    private int codCaptura;
    private Tomada codTomada;
    private TipoOnda codTipoOnda;
    private Equipamento codEquip;    
    private Evento codEvento;
    private float valorMedio;
    private float VM2;
    private float offset;
    private float gain;
    private float eficaz;
    private int under;
    private int over;
    private int duration;
    private Calendar dataAtual;
    private Collection<HarmAtual> harmAtual;

    public String toString(){
        if (this != null)
            return String.valueOf(this.codCaptura);
        return "Sem Codigo";
    }
    
    public int getCodCaptura() {
        return codCaptura;
    }

    public void setCodCaptura(int codCaptura) {
        this.codCaptura = codCaptura;
    }

    public Equipamento getCodEquip() {
        return codEquip;
    }

    public void setCodEquip(Equipamento codEquip) {
        this.codEquip = codEquip;
    }

    public Evento getCodEvento() {
        return codEvento;
    }

    public void setCodEvento(Evento codEvento) {
        this.codEvento = codEvento;
    }

    public Tomada getCodTomada() {
        return codTomada;
    }

    public void setCodTomada(Tomada codTomada) {
        this.codTomada = codTomada;
    }

    public TipoOnda getCodTipoOnda() {
        return codTipoOnda;
    }

    public void setCodTipoOnda(TipoOnda codTipoOnda) {
        this.codTipoOnda = codTipoOnda;
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

    public Collection<HarmAtual> getHarmAtual() {
        return harmAtual;
    }

    public void setHarmAtual(Collection<HarmAtual> harmAtual) {
        this.harmAtual = harmAtual;
    }

    public float getVM2() {
        return VM2;
    }

    public void setVM2(float VM2) {
        this.VM2 = VM2;
    }

    public int getUnder() {
        return under;
    }

    public void setUnder(int under) {
        this.under = under;
    }

    public int getOver() {
        return over;
    }

    public void setOver(int over) {
        this.over = over;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

}
