/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Threads;

import javax.swing.JDialog;
import javax.swing.JFrame;
import Visualizacao.FormUltimasCapturadas;
import Visualizacao.Menu;

/**
 *
 * @author rebonatto
 */
public class TMenu extends Thread{         
    private boolean acaba = false;
    private Menu form;

    public TMenu(Menu form) {
        this.form = form;
    }
        
    
    public void run() {
        
        while (true) {

            try {
                Thread.sleep(300000);    // atualização a cada 5 minutos
            } catch (Exception e) {
                System.out.println("Erro na Thread: " + e.getMessage());
            }
            //System.out.println("Bytes livres: " + Runtime.getRuntime().freeMemory());
            form.AtualizaMemoria();
            if (acaba)
                break;
        }
    }

    public void setAcaba(){
        acaba = true;
    }
            
    
}
