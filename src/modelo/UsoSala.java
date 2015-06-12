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

public class UsoSala {
    private int codUsoSala;
    private SalaCirurgia codSala;
    private Procedimento codProced;
    private Responsavel codResp;        
    private Calendar horaInicio;
    private Calendar horaFinal;
    private boolean ativa;

    public int getCodUsoSala(){
        return codUsoSala;
    }

    public void setCodUsoSala(int codUsoSala){
        this.codUsoSala = codUsoSala;
    }

    public Responsavel getCodResp(){
        return codResp;
    }

    public void setCodResp (Responsavel codMedico){
        this.codResp = codMedico;
    }

     public Procedimento getCodProced(){
        return codProced;
    }

    public void setCodProced (Procedimento codProced){
        this.codProced = codProced;
    }

     public SalaCirurgia getCodSala(){
        return codSala;
    }

    public void setCodSala (SalaCirurgia codSala){
        this.codSala = codSala;
    }

    public Calendar getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Calendar horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Calendar getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(Calendar horaFinal) {
        this.horaFinal = horaFinal;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }
       
}
