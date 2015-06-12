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

public class HarmPadrao {
    private int codHarmonica;
    private int codOndaPadrao;
    private float sen;
    private float cos;

    public int getHarmonica() {
        return codHarmonica;
    }

    public void setHarmonica(int harmonica) {
        this.codHarmonica = harmonica;
    }

    public int getCodOndaPadrao() {
        return codOndaPadrao;
    }

    public void setCodOndaPadrao(int codOndaPadrao) {
        this.codOndaPadrao = codOndaPadrao;
    }

    public float  getSen() {
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

