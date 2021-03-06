/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Menu.java
 *
 * Created on Mar 5, 2012, 8:45:46 PM
 * @author Eduardo
 * Modified by: rebonatto 
 */

package Visualizacao;

import Visualizacao.Cadastros.FormResponsavel;
import Visualizacao.Cadastros.FormSala;
import Visualizacao.Cadastros.FormEvento;
import Visualizacao.Cadastros.FormTipoOnda;
import Visualizacao.Cadastros.FormProcedimento;
import Visualizacao.Cadastros.FormModelo;
import Visualizacao.Cadastros.FormMarca;
import Visualizacao.Cadastros.FormEquipamento;
import Visualizacao.Cadastros.FormTipoPadrao;
import Visualizacao.Cadastros.FormTipo;
import Visualizacao.Cadastros.FormTomada;
import Threads.TMenu;
import Uteis.Configuracoes;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;


/**
 *
 * @author Eduardo
 * Modified by: rebonatto
 */

public class Menu extends javax.swing.JFrame {
    private TMenu thread = null;
    private long  usada = 0;

    /** Creates new form Menu */
    public Menu() {        
        initComponents();
        setTitle("Menu Principal - Projeto Protegemed");
        Configuracoes.LeAjustaParametros();        
        //Configuracoes.MostraParametros();
        thread = new TMenu(this);
        thread.start();
        AtualizaMemoria();        
    }
    
    public void AtualizaMemoria(){
        float perc;
        String setar = new String("Memory Usage: ");
        usada = Runtime.getRuntime().maxMemory() - Runtime.getRuntime().freeMemory();        
        Runtime.getRuntime().maxMemory();
        perc = ( usada * 100 / Runtime.getRuntime().maxMemory() );
        //System.out.println("% " + perc + " Max: " +  Runtime.getRuntime().maxMemory() + "Usada: " + usada);
        setar += String.valueOf((int) perc);
        setar += "% / Total: ";
        setar += String.valueOf(Configuracoes.MEMORIA);
        setar += " Bytes";
        
        txtMemoria.setText(setar);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        txtMemoria = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        grpMnuControle = new javax.swing.JMenu();
        jmPainel = new javax.swing.JMenuItem();
        mnuUltimas = new javax.swing.JMenuItem();
        mnuFoCapturadas = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        mnuUsoSala = new javax.swing.JMenuItem();
        jmPadrao = new javax.swing.JMenuItem();
        grpMnuCadastros = new javax.swing.JMenu();
        grpMnuEquipamentos = new javax.swing.JMenu();
        mnuEquip = new javax.swing.JMenuItem();
        mnuTipoEquip = new javax.swing.JMenuItem();
        mnuMarca = new javax.swing.JMenuItem();
        mnuModelo = new javax.swing.JMenuItem();
        grpMnuSalas = new javax.swing.JMenu();
        mnuSala = new javax.swing.JMenuItem();
        mntTomada = new javax.swing.JMenuItem();
        Procedimento = new javax.swing.JMenu();
        mnuProcedimento = new javax.swing.JMenuItem();
        mnuResponsavel = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        mnuTipoOnda = new javax.swing.JMenuItem();
        mnuPadroes = new javax.swing.JMenuItem();
        mnuAjuda = new javax.swing.JMenu();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtMemoria.setText("Memory Usage: xxx / xxxB");

        grpMnuControle.setText("Control");
        grpMnuControle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grpMnuControleActionPerformed(evt);
            }
        });

        jmPainel.setText("Painel de Controle");
        jmPainel.setEnabled(false);
        jmPainel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmPainelActionPerformed(evt);
            }
        });
        grpMnuControle.add(jmPainel);

        mnuUltimas.setText("Last Stored");
        mnuUltimas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuUltimasActionPerformed(evt);
            }
        });
        grpMnuControle.add(mnuUltimas);

        mnuFoCapturadas.setText("WF Stored");
        mnuFoCapturadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuFoCapturadasActionPerformed(evt);
            }
        });
        grpMnuControle.add(mnuFoCapturadas);

        jMenuItem3.setText("Show Stored");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        grpMnuControle.add(jMenuItem3);

        jMenuItem4.setText("Concurent Leakage");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        grpMnuControle.add(jMenuItem4);

        jMenuItem5.setText("Waveform comparasion");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        grpMnuControle.add(jMenuItem5);

        jMenuItem6.setText("Events Status");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        grpMnuControle.add(jMenuItem6);

        jMenuItem7.setText("Show Onde Waveform");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        grpMnuControle.add(jMenuItem7);

        jMenuItem8.setText("Status dangerousness");
        jMenuItem8.setEnabled(false);
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        grpMnuControle.add(jMenuItem8);

        mnuUsoSala.setText("Uso de Salas");
        mnuUsoSala.setEnabled(false);
        mnuUsoSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuUsoSalaActionPerformed(evt);
            }
        });
        grpMnuControle.add(mnuUsoSala);

        jmPadrao.setText("Padrao");
        jmPadrao.setEnabled(false);
        jmPadrao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmPadraoActionPerformed(evt);
            }
        });
        grpMnuControle.add(jmPadrao);

        jMenuBar1.add(grpMnuControle);

        grpMnuCadastros.setText("Records");

        grpMnuEquipamentos.setText("Equipamentos");

        mnuEquip.setText("Equipamento");
        mnuEquip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuEquipActionPerformed(evt);
            }
        });
        grpMnuEquipamentos.add(mnuEquip);

        mnuTipoEquip.setText("Tipo");
        mnuTipoEquip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuTipoEquipActionPerformed(evt);
            }
        });
        grpMnuEquipamentos.add(mnuTipoEquip);

        mnuMarca.setText("Marca");
        mnuMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuMarcaActionPerformed(evt);
            }
        });
        grpMnuEquipamentos.add(mnuMarca);

        mnuModelo.setText("Modelo");
        mnuModelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuModeloActionPerformed(evt);
            }
        });
        grpMnuEquipamentos.add(mnuModelo);

        grpMnuCadastros.add(grpMnuEquipamentos);

        grpMnuSalas.setText("Salas");

        mnuSala.setText("Sala");
        mnuSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuSalaActionPerformed(evt);
            }
        });
        grpMnuSalas.add(mnuSala);

        mntTomada.setText("Tomada");
        mntTomada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mntTomadaActionPerformed(evt);
            }
        });
        grpMnuSalas.add(mntTomada);

        grpMnuCadastros.add(grpMnuSalas);

        Procedimento.setText("Procecedimentos");

        mnuProcedimento.setText("Procedimento");
        mnuProcedimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuProcedimentoActionPerformed(evt);
            }
        });
        Procedimento.add(mnuProcedimento);

        mnuResponsavel.setText("Responsável");
        mnuResponsavel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuResponsavelActionPerformed(evt);
            }
        });
        Procedimento.add(mnuResponsavel);

        grpMnuCadastros.add(Procedimento);

        jMenu1.setText("Eventos");

        jMenuItem2.setText("Tipos de Eventos");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        mnuTipoOnda.setText("Tipo de Onda");
        mnuTipoOnda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuTipoOndaActionPerformed(evt);
            }
        });
        jMenu1.add(mnuTipoOnda);

        grpMnuCadastros.add(jMenu1);

        mnuPadroes.setText("Tipos de Padrões");
        mnuPadroes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuPadroesActionPerformed(evt);
            }
        });
        grpMnuCadastros.add(mnuPadroes);

        jMenuBar1.add(grpMnuCadastros);

        mnuAjuda.setText("Help");
        jMenuBar1.add(mnuAjuda);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(txtMemoria)
                .addGap(0, 321, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(336, Short.MAX_VALUE)
                .addComponent(txtMemoria)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void grpMnuControleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grpMnuControleActionPerformed
       
    }//GEN-LAST:event_grpMnuControleActionPerformed

    private void jmPadraoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmPadraoActionPerformed
        // TODO add your handling code here:
        FormPadrao painel = null;
        try {
            painel = new FormPadrao(new JFrame(), true);
        } catch (Exception ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        painel.setVisible(true);
}//GEN-LAST:event_jmPadraoActionPerformed

    private void mnuTipoEquipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuTipoEquipActionPerformed
        // TODO add your handling code here:
        FormTipo painel = new FormTipo(new JFrame(), true);
        painel.setVisible(true);
}//GEN-LAST:event_mnuTipoEquipActionPerformed

    private void mnuModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuModeloActionPerformed
        // TODO add your handling code here:
        FormModelo painel = new FormModelo(new JFrame(), true);
        painel.setVisible(true);
}//GEN-LAST:event_mnuModeloActionPerformed

    private void mnuMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuMarcaActionPerformed
        FormMarca painel = new FormMarca(new JFrame(), true);
        painel.setVisible(true);
}//GEN-LAST:event_mnuMarcaActionPerformed

    private void mnuEquipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuEquipActionPerformed
        FormEquipamento painel = new FormEquipamento(new JFrame(), true);
        painel.setVisible(true);
}//GEN-LAST:event_mnuEquipActionPerformed

    private void mnuTipoOndaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuTipoOndaActionPerformed
        // TODO add your handling code here:
        FormTipoOnda painel = new FormTipoOnda(new JFrame(), true);
        painel.setVisible(true);
    }//GEN-LAST:event_mnuTipoOndaActionPerformed

    private void mnuProcedimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuProcedimentoActionPerformed
        // TODO add your handling code here:
        FormProcedimento painel = new FormProcedimento(new JFrame(), true);
        painel.setVisible(true);
    }//GEN-LAST:event_mnuProcedimentoActionPerformed

    private void mnuResponsavelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuResponsavelActionPerformed
        // TODO add your handling code here:
        FormResponsavel painel = new FormResponsavel(new JFrame(), true);
        painel.setVisible(true);
    }//GEN-LAST:event_mnuResponsavelActionPerformed

    private void mnuSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSalaActionPerformed
        // TODO add your handling code here:
        FormSala painel = new FormSala(new JFrame(), true);
        painel.setVisible(true);    
    }//GEN-LAST:event_mnuSalaActionPerformed

    private void mntTomadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mntTomadaActionPerformed
        // TODO add your handling code here:
        FormTomada painel = new FormTomada(new JFrame(), true);
        painel.setVisible(true);  
    }//GEN-LAST:event_mntTomadaActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        FormEvento painel = new FormEvento(new JFrame(), true);
        painel.setVisible(true);  
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void mnuFoCapturadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuFoCapturadasActionPerformed
        // TODO add your handling code here:
        FormOndasArquivadas painel = new FormOndasArquivadas(new JFrame(), true);
        painel.setVisible(true);  
    }//GEN-LAST:event_mnuFoCapturadasActionPerformed

    private void jmPainelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmPainelActionPerformed
        // TODO add your handling code here:
        FormPControle painel = new FormPControle(new JFrame(), true);
        painel.setVisible(true);  
    }//GEN-LAST:event_jmPainelActionPerformed

    private void mnuUsoSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuUsoSalaActionPerformed
        // TODO add your handling code here:
        FormMostraUsoSala painel = new FormMostraUsoSala(new JFrame(), true);
        painel.setVisible(true);  
    }//GEN-LAST:event_mnuUsoSalaActionPerformed

    private void mnuPadroesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuPadroesActionPerformed
        // TODO add your handling code here:
        FormTipoPadrao painel = new FormTipoPadrao(new JFrame(), true);
        painel.setVisible(true);  
    }//GEN-LAST:event_mnuPadroesActionPerformed

    private void mnuUltimasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuUltimasActionPerformed
        // TODO add your handling code here:
        FormUltimasCapturadas painel = new FormUltimasCapturadas(new JFrame(), true);
        painel.setVisible(true);  
    }//GEN-LAST:event_mnuUltimasActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        FormMostraCapturadas painel = new FormMostraCapturadas(new JFrame(), true);
        painel.setVisible(true);  
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        FormFugasSimultaneas painel = new FormFugasSimultaneas(new JFrame(), true);
        painel.setVisible(true); 
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        FormComparaOndas painel = new FormComparaOndas(new JFrame(), true);
        painel.setVisible(true); 
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        FormSumarizaEventos painel = new FormSumarizaEventos(new JFrame(), true);
        painel.setVisible(true); 

    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        FormMostraUmaOnda painel = new FormMostraUmaOnda(new JFrame(), true);
        painel.setVisible(true); 
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
        FormFugasDia painel = new FormFugasDia(new JFrame(), true);
        painel.setVisible(true); 
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Procedimento;
    private javax.swing.JMenu grpMnuCadastros;
    private javax.swing.JMenu grpMnuControle;
    private javax.swing.JMenu grpMnuEquipamentos;
    private javax.swing.JMenu grpMnuSalas;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jmPadrao;
    private javax.swing.JMenuItem jmPainel;
    private javax.swing.JMenuItem mntTomada;
    private javax.swing.JMenu mnuAjuda;
    private javax.swing.JMenuItem mnuEquip;
    private javax.swing.JMenuItem mnuFoCapturadas;
    private javax.swing.JMenuItem mnuMarca;
    private javax.swing.JMenuItem mnuModelo;
    private javax.swing.JMenuItem mnuPadroes;
    private javax.swing.JMenuItem mnuProcedimento;
    private javax.swing.JMenuItem mnuResponsavel;
    private javax.swing.JMenuItem mnuSala;
    private javax.swing.JMenuItem mnuTipoEquip;
    private javax.swing.JMenuItem mnuTipoOnda;
    private javax.swing.JMenuItem mnuUltimas;
    private javax.swing.JMenuItem mnuUsoSala;
    private javax.swing.JLabel txtMemoria;
    // End of variables declaration//GEN-END:variables

}
