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

public class SalaCirurgia {
    private int codSala;
    private String desc;

    public int getCodSala(){
        return codSala;
    }

    public void setCodSala(int codSala){
        this.codSala = codSala;
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
