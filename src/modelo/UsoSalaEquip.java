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

public class UsoSalaEquip {
    private Equipamento codEquip;
    private UsoSala codUsoSala;

    public UsoSalaEquip(Equipamento codEquip, UsoSala codUsoSala){
        this.codEquip = codEquip;
        this.codUsoSala = codUsoSala;
    }

    public Equipamento getCodEquip(){
        return codEquip;
    }

    public void setCodEquip(Equipamento codEquip){
        this.codEquip = codEquip;
    }

    public UsoSala getCodUsoSala(){
        return codUsoSala;
    }

    public void setCodUsoSala (UsoSala codUsoSala){
        this.codUsoSala = codUsoSala;
    }

}
