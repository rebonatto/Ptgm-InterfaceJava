/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

/**
 *
 * @author rebonatto
 */

public class TipoPadrao {
    private int codTipoPadrao;
    private String desc;

    public int getCodTipoPadrao() {
        return codTipoPadrao;
    }

    public void setCodTipoPadrao(int codTipoPadrao) {
        this.codTipoPadrao = codTipoPadrao;
    }

    public String getDesc(){
        return desc;
    }

    public void setDesc(String desc){
        this.desc = desc;
    }

    @Override
    public String toString(){
        return this.desc;
    }

}
