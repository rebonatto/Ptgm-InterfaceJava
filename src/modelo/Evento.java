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

public class Evento {
    private int codEvento;
    private String desc;
    private int formaOnda;

    public int getCodEvento(){
        return codEvento;
    }

    public void setCodEvento(int codEvento){
        this.codEvento = codEvento;
    }

    public String getDesc(){
        return desc;
    }

    public void setDesc(String desc){
        this.desc = desc;
    }

    public int getFormaOnda() {
        return formaOnda;
    }
    
    public void setFormaOnda(int formaOnda) {
        this.formaOnda = formaOnda;
    }
    
    public String getMontaFO(){
        return (formaOnda == 1 ? "Sim": "Não");
    }
    
    public void setMontaFO(String MontaFO){
        this.formaOnda = MontaFO.equals("Sim") ? 1 : 0;
    }

    @Override
    public String toString(){
        return this.desc;
    }
    
}
