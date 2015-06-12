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

public class Responsavel {
    private int codResp;
    private String desc;    

    public int getCodResp(){
        return codResp;
    }

    public void setCodResp(int codResp){
        this.codResp = codResp;
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
