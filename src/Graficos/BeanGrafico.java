/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graficos;

import java.util.Calendar;
import java.util.Collection;
import modelo.Equipamento;
import modelo.Evento;
import modelo.HarmAtual;
import modelo.TipoOnda;
import modelo.TipoPadrao;
import modelo.Tomada;

/**
 *
 * @author rebonatto
 */
public class BeanGrafico {
    private int codigo;
    private Tomada codTomada;
    private TipoOnda codTipoOnda;
    private Equipamento codEquip;    
    private Evento codEvento;
    private TipoPadrao codTipoPadrao;
    private float valorMedio;
    private float offset;
    private float gain;
    private float eficaz;
    private Calendar data;
    private Collection<BeanHarmonica> harm;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    public TipoPadrao getCodTipoPadrao() {
        return codTipoPadrao;
    }

    public void setCodTipoPadrao(TipoPadrao codTipoPadrao) {
        this.codTipoPadrao = codTipoPadrao;
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

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public Collection<BeanHarmonica> getHarm() {
        return harm;
    }

    public void setHarm(Collection<BeanHarmonica> harm) {
        this.harm = harm;
    }        
}
