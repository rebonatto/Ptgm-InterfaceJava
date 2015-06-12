/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

/**
 *
 * @author Eduardo
 * Modified by: rebonatto
 */

public class TipoOnda {
    private int codTipoOnda;
    private String desc;

    public int getCodTipoOnda() {
        return codTipoOnda;
    }

    public void setCodTipoOnda(int codTipoOnda) {
        this.codTipoOnda = codTipoOnda;
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
