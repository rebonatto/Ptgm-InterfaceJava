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

public class Modelo {
    private int codModelo;
    private String desc;

    public int getCodModelo(){
        return codModelo;
    }

    public void setCodModelo(int codModelo){
        this.codModelo = codModelo;
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
