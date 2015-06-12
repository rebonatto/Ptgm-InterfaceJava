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

public class HarmAtual {
    private int codCaptura;
    private int codHarmonica;
    private float sen;
    private float cos;

    public int getHarmonica() {
        return codHarmonica;
    }

    public void setHarmonica(int harmonica) {
        this.codHarmonica = harmonica;
    }

    public int getCodCaptura() {
        return codCaptura;
    }

    public void setCodCaptura(int codCaptura) {
        this.codCaptura = codCaptura;
    }

    public float getSen() {
        return sen;
    }


    public void setSen(float sen) {
        this.sen = sen;
    }


    public float getCos() {
        return cos;
    }

  
    public void setCos(float cos) {
        this.cos = cos;
    }
}

