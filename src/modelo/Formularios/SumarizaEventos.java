/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.Formularios;

import java.sql.Time;
import java.util.Calendar;
import modelo.Evento;

/**
 *
 * @author rebonatto
 */
public class SumarizaEventos {
    private Calendar data;
    private Evento   codEvento;
    private int      quantidade;
    private Calendar horafinal;

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public Evento getCodEvento() {
        return codEvento;
    }

    public void setCodEvento(Evento codEvento) {
        this.codEvento = codEvento;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Calendar getHorafinal() {
        return horafinal;
    }

    public void setHorafinal(Calendar horafinal) {
        this.horafinal = horafinal;
    }        
}
