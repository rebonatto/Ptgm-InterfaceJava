/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

/**
 *
 * @author Eduardo
 * Modified by rebonatto
 */

public class Marca {
    private int codMarca;
    private String desc;

    public int getCodMarca(){
        return codMarca;
    }
    
    public void setCodMarca(int codMarca){
        this.codMarca = codMarca;
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
