/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.sql.Time;
import java.util.Calendar;


/**
 *
 * @author Eduardo
 * Modified by: rebonatto
 */

public class Equipamento {
    private int codEquip;
    private Marca codMarca;
    private Modelo codModelo;
    private Tipo codTipo;
    private String rfid;
    private String codPatrimonio;
    private String desc;
    private Calendar dataUltimaFalha;
    private Calendar dataUltimaManutencao;  
    private int tempoUso;

    public int getCodEquip(){
        return codEquip;
    }

    public void setCodEquip(int codEquip) {
        this.codEquip = codEquip;
    }

    public Tipo getCodTipo(){
        return codTipo;
    }

    public void setCodTipo (Tipo codTipo){
        this.codTipo = codTipo;
    }

    public Modelo getCodModelo(){
        return codModelo;
    }

    public void setCodModelo (Modelo codModelo){
        this.codModelo = codModelo;
    }

    public Marca getCodMarca(){
        return codMarca;
    }

    public void setCodMarca (Marca codMarca){
        this.codMarca = codMarca;
    }

    public String getRfid(){
        return rfid;
    }

    public void setRfid(String rfid){
        this.rfid = rfid;
    }

    public String getCodPatrimonio(){
        return codPatrimonio;
    }

    public void setCodPatrimonio (String codPatrimonio){
        this.codPatrimonio = codPatrimonio;
    }

    public int getTempoUso(){
        return tempoUso;
    }

    public void setTempoUso (int tempoUso){
        this.tempoUso = tempoUso;
    }

    public String getDesc(){
        return desc;
    }

    public void setDesc(String desc){
        this.desc = desc;
    }

    public Calendar getDataUltimaFalha() {
        return dataUltimaFalha;
    }

    public void setDataUltimaFalha(Calendar dataUltimaFalha) {
        this.dataUltimaFalha = dataUltimaFalha;
    }

    public Calendar getDataUltimaManutencao() {
        return dataUltimaManutencao;
    }

    public void setDataUltimaManutencao(Calendar dataUltimaManutencao) {
        this.dataUltimaManutencao = dataUltimaManutencao;
    }

    public String toString(){
        return this.desc;
    }
}
