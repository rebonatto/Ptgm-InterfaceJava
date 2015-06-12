/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Threads;

import Uteis.Configuracoes;
import Visualizacao.FormFugasDia;
import javax.swing.JDialog;
import javax.swing.JFrame;
import Visualizacao.FormUltimasCapturadas;

/**
 *
 * @author rebonatto
 */
public class TFormFugasDia extends Thread{         
    private boolean acaba = false;
    private FormFugasDia form;

    public TFormFugasDia(FormFugasDia form) {
        this.form = form;
    }
        
    
    public void run() {
        
        while (true) {
            
            try {
                Thread.sleep(Configuracoes.TEMPOATUALIZATABELA);                        
            } catch (Exception e) {
                System.out.println("Erro na Thread: " + e.getMessage());
            }
            //System.out.println("Bytes livres: " + Runtime.getRuntime().freeMemory());
            form.atualizaTabela(false);
            if (acaba)
                break;
        }
    }

    public void setAcaba(){
        acaba = true;
    }
            
    
}
