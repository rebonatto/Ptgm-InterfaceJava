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

public class Tomada {
    private int codTomada;
    private SalaCirurgia codSala;
    private int indice;
    private int modulo;
    private String desc;

    public int getCodTomada() {
        return codTomada;
    }

    public void setCodTomada(int codTomada) {
        this.codTomada = codTomada;
    }

    public SalaCirurgia getCodSala() {
        return codSala;
    }

    public void setCodSala(SalaCirurgia codSala) {
        this.codSala = codSala;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public int getModulo() {
        return modulo;
    }

    public void setModulo(int modulo) {
        this.modulo = modulo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString(){
        return this.desc;
    }
}
