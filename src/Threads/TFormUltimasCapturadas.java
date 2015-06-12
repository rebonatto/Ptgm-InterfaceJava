/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Threads;

import javax.swing.JDialog;
import javax.swing.JFrame;
import Visualizacao.FormUltimasCapturadas;

/**
 *
 * @author rebonatto
 */
public class TFormUltimasCapturadas extends Thread{         
    private boolean acaba = false;
    private FormUltimasCapturadas form;

    public TFormUltimasCapturadas(FormUltimasCapturadas form) {
        this.form = form;
    }
        
    
    public void run() {
        
        while (true) {

            try {
                Thread.sleep(1000);                        
            } catch (Exception e) {
                System.out.println("Erro na Thread: " + e.getMessage());
            }
            //System.out.println("Bytes livres: " + Runtime.getRuntime().freeMemory());
            form.atualizaTabela();
            if (acaba)
                break;
        }
    }

    public void setAcaba(){
        acaba = true;
    }
            
    
}
