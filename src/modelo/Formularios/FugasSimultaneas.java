/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.Formularios;

import java.util.Calendar;

/**
 *
 * @author rebonatto
 */
public class FugasSimultaneas {
    private Calendar dataAtual;
    private int quantidade;
    private float maxEficaz;

    public Calendar getDataAtual() {
        return dataAtual;
    }

    public void setDataAtual(Calendar dataAtual) {
        this.dataAtual = dataAtual;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public float getMaxEficaz() {
        return maxEficaz;
    }

    public void setMaxEficaz(float maxEficaz) {
        this.maxEficaz = maxEficaz;
    }
    
    
}
