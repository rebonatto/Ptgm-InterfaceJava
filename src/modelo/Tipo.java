/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

/**
 *
 * @author Eduardo
 * Modified by: rebonatto
 * Tipo de Equipamento
 */

public class Tipo {
    private int codTipo;
    private String desc;

    public int getCodTipo(){
        return codTipo;
    }

    public void setCodTipo(int codTipo){
        this.codTipo = codTipo;
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
