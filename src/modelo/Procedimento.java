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

public class Procedimento {
    private int codProced;
    private String desc;

    public int getCodProced(){
        return codProced;
    }

    public void setCodProced(int codProced){
        this.codProced = codProced;
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
