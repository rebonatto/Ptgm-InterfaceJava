/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

/**
 *
 * @author rebonatto
 */

public class UsoSalaCaptura {
    private CapturaAtual CodCapturaAtual;
    private UsoSala CodUsoSala;

    public CapturaAtual getCodOndaAtual() {
        return CodCapturaAtual;
    }

    public void setCodOndaAtual(CapturaAtual CodOndaAtual) {
        this.CodCapturaAtual = CodOndaAtual;
    }

    public UsoSala getCodUsoSala() {
        return CodUsoSala;
    }

    public void setCodUsoSala(UsoSala CodUsoSala) {
        this.CodUsoSala = CodUsoSala;
    }    
}
