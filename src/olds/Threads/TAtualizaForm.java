/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olds.Threads;

import Uteis.Configuracoes;

import Visualizacao.FormPControle;

/**
 *
 * @author Eduardo
 * Modified by: rebonatto
 */

public class TAtualizaForm extends Thread {     
    private String mensagem;
    private FormPControle obj;        

    public TAtualizaForm(FormPControle _obj) {
        //recebe Salas
        this.obj = _obj;                
    }

    public void run() {
        
        while (true) {
            try {
                Thread.sleep(Configuracoes.TEMPOATUALIZARELOGIOS);
            } catch (InterruptedException ex) {
                System.out.println("Erro");
            }
            
            /* aqui busca do banco e atualiza interfaces */
            obj.AtualizaInterface();            
        }

    }    

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
