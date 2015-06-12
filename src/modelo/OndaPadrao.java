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
 * 
 */

public class OndaPadrao {
    private int codOndaPadrao;
    private TipoOnda codTipoOnda;
    private Tomada codTomada;
    private Equipamento codEquip;
    private float valorMedio;
    private float offset;
    private float gain;
    private float eficaz;
    private Calendar dataPadrao;
    private TipoPadrao codTipoPadrao;
    private Collection<HarmPadrao> harmPadrao;

    public int getCodOndaPadrao() {
        return codOndaPadrao;
    }

    public void setCodOndaPadrao(int codOndaPadrao) {
        this.codOndaPadrao = codOndaPadrao;
    }

    public TipoOnda getCodTipoOnda() {
        return codTipoOnda;
    }

    public void setCodTipoOnda(TipoOnda codTipoOnda) {
        this.codTipoOnda = codTipoOnda;
    }

    public Tomada getCodTomada() {
        return codTomada;
    }

    public void setCodTomada(Tomada codTomada) {
        this.codTomada = codTomada;
    }

    public Equipamento getCodEquip() {
        return codEquip;
    }

    public void setCodEquip(Equipamento codEquip) {
        this.codEquip = codEquip;
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

    public Calendar getDataPadrao() {
        return dataPadrao;
    }

    public TipoPadrao getCodTipoPadrao() {
        return codTipoPadrao;
    }

    public void setCodTipoPadrao(TipoPadrao codTipoPadrao) {
        this.codTipoPadrao = codTipoPadrao;
    }       

    public void setDataPadrao(Calendar dataPadrao) {
        this.dataPadrao = dataPadrao;
    }

    public Collection<HarmPadrao> getHarmPadrao() {
        return harmPadrao;
    }

    public void setHarmPadrao(Collection<HarmPadrao> harmPadrao) {
        this.harmPadrao = harmPadrao;
    }

    
    @Override
    public String toString() {
        return "visualizacao.Capturaondapadrao[codOndaPadrao=" + codOndaPadrao + "]";
    }
}

