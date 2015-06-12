package Visualizacao;


import Graficos.CriaGrafico;
import Threads.TFormUltimasCapturadas;
import Uteis.Configuracoes;
import Uteis.Conversoes;
import Uteis.FormataNumeros;
import java.util.ArrayList;
import java.util.Collection;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import modelo.CapturaAtual;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import persistencia.CapturaAtualDAO;
import persistencia.UsoSalaExtrasDAO;

public class FormMostraCapturadas extends javax.swing.JDialog {
    private String mensagem;
        
    CapturaAtualDAO capdao = new CapturaAtualDAO();
    UsoSalaExtrasDAO extradao = new UsoSalaExtrasDAO();    
    
    CapturaAtual bean1 = new CapturaAtual();
    CapturaAtual bean2 = new CapturaAtual();
    CapturaAtual bean3 = new CapturaAtual();
    CapturaAtual bean4 = new CapturaAtual();
                  
    
    /** Creates new form FormPSupervisaoX */
    public FormMostraCapturadas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

        this.setTitle("Show Stored Waveforms");        

        add(new JScrollPane(this.jPanel1));        
               
        /* thread para atualizar a tabela de equipamentos numa sala */     
        
        initComponents();        
    }
    
    private void limpaGrafico(JPanel panelOnda, JPanel panelBarra, 
                                 JTextField codigo, JLabel tipo, JTextArea det){                
        
        

        panelOnda.removeAll();                       
        panelOnda.revalidate();
        panelOnda.repaint();
       
        panelBarra.removeAll();               
        panelBarra.revalidate();
        panelBarra.repaint();

        codigo.setText("");        
        tipo.setText("Capture");
        det.setText("");
        
    }      
    
    
           
    private void atualizaGrafico(CapturaAtual evento, JPanel panelOnda, JPanel panelBarra, 
                                 JTextField codigo, JLabel tipo, JTextArea det){                
        
        

        final JFreeChart chart1 = CriaGrafico.createChartLinhas(evento, false);
        final ChartPanel crtPanelOnda = new ChartPanel(chart1);
        
        crtPanelOnda.setPreferredSize(new java.awt.Dimension(295, 260));       
        panelOnda.removeAll();               
        panelOnda.add(crtPanelOnda);     
        panelOnda.revalidate();
        panelOnda.repaint();


        final JFreeChart chart2 = CriaGrafico.createChartBarras(evento, false, false);       
        final ChartPanel crtPanelBarra = new ChartPanel(chart2);

        crtPanelBarra.setPreferredSize(new java.awt.Dimension(295, 260));        
       
        panelBarra.removeAll();               
        panelBarra.add(crtPanelBarra);       
        panelBarra.revalidate();
        panelBarra.repaint();

        codigo.setText(Integer.toString(evento.getCodCaptura()));        
        tipo.setText(evento.getCodTipoOnda().getCodTipoOnda() == 1 ? "Phase" : "Leakage");
        det.setText("");
        
        det.append("Time: " + Conversoes.CalendarToStringDH(evento.getDataAtual()) + " ");        
        det.append("RMS: " + FormataNumeros.FormataSignificativos(evento.getEficaz(), Configuracoes.DIGITOSSIGNIFICATIVOS)+ "\n");
        det.append("Equipment: " + evento.getCodEquip().getDesc() + "\n" + 
                         evento.getCodEquip().getCodMarca().getDesc().trim() + " - " + 
                         evento.getCodEquip().getCodModelo().getDesc().trim() +  "\n"+
                         "RFID: " + evento.getCodEquip().getRfid());
    }      
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panelOnda02 = new javax.swing.JPanel();
        panelOnda03 = new javax.swing.JPanel();
        panelOnda01 = new javax.swing.JPanel();
        panelBarra01 = new javax.swing.JPanel();
        panelBarra02 = new javax.swing.JPanel();
        panelBarra03 = new javax.swing.JPanel();
        Tipo03 = new javax.swing.JLabel();
        Tipo01 = new javax.swing.JLabel();
        Tipo02 = new javax.swing.JLabel();
        Codigo01 = new javax.swing.JTextField();
        Codigo02 = new javax.swing.JTextField();
        Codigo03 = new javax.swing.JTextField();
        Tipo04 = new javax.swing.JLabel();
        Codigo04 = new javax.swing.JTextField();
        panelOnda04 = new javax.swing.JPanel();
        panelBarra04 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Detalhes2 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        Detalhes1 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        Detalhes3 = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        Detalhes4 = new javax.swing.JTextArea();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        Limpa1 = new javax.swing.JButton();
        Limpa2 = new javax.swing.JButton();
        Limpa3 = new javax.swing.JButton();
        Limpa4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setToolTipText("");
        jPanel1.setPreferredSize(new java.awt.Dimension(1250, 720));
        jPanel1.setLayout(null);

        panelOnda02.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelOnda02.setToolTipText("Equipamento 2");
        panelOnda02.setPreferredSize(new java.awt.Dimension(250, 250));
        jPanel1.add(panelOnda02);
        panelOnda02.setBounds(310, 170, 300, 260);

        panelOnda03.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelOnda03.setToolTipText("");
        panelOnda03.setPreferredSize(new java.awt.Dimension(250, 250));
        jPanel1.add(panelOnda03);
        panelOnda03.setBounds(610, 170, 300, 260);

        panelOnda01.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelOnda01.setToolTipText("");
        panelOnda01.setPreferredSize(new java.awt.Dimension(250, 250));
        jPanel1.add(panelOnda01);
        panelOnda01.setBounds(10, 170, 300, 260);

        panelBarra01.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelBarra01.setPreferredSize(new java.awt.Dimension(250, 250));
        jPanel1.add(panelBarra01);
        panelBarra01.setBounds(10, 430, 300, 260);

        panelBarra02.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelBarra02.setPreferredSize(new java.awt.Dimension(250, 250));
        jPanel1.add(panelBarra02);
        panelBarra02.setBounds(310, 430, 300, 260);

        panelBarra03.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelBarra03.setPreferredSize(new java.awt.Dimension(250, 250));
        jPanel1.add(panelBarra03);
        panelBarra03.setBounds(610, 430, 300, 260);

        Tipo03.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        Tipo03.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Tipo03.setText("Capture:");
        Tipo03.setPreferredSize(new java.awt.Dimension(77, 17));
        Tipo03.setVerifyInputWhenFocusTarget(false);
        jPanel1.add(Tipo03);
        Tipo03.setBounds(630, 50, 50, 20);

        Tipo01.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        Tipo01.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Tipo01.setText("Capture:");
        Tipo01.setPreferredSize(new java.awt.Dimension(77, 17));
        Tipo01.setVerifyInputWhenFocusTarget(false);
        jPanel1.add(Tipo01);
        Tipo01.setBounds(10, 50, 50, 17);

        Tipo02.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        Tipo02.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Tipo02.setText("Capture:");
        Tipo02.setPreferredSize(new java.awt.Dimension(77, 17));
        Tipo02.setVerifyInputWhenFocusTarget(false);
        jPanel1.add(Tipo02);
        Tipo02.setBounds(310, 50, 50, 17);

        Codigo01.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        Codigo01.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Codigo01ActionPerformed(evt);
            }
        });
        jPanel1.add(Codigo01);
        Codigo01.setBounds(60, 50, 90, 25);

        Codigo02.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jPanel1.add(Codigo02);
        Codigo02.setBounds(360, 50, 90, 25);

        Codigo03.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jPanel1.add(Codigo03);
        Codigo03.setBounds(680, 50, 90, 27);

        Tipo04.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        Tipo04.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Tipo04.setText("Capture:");
        Tipo04.setPreferredSize(new java.awt.Dimension(77, 17));
        Tipo04.setVerifyInputWhenFocusTarget(false);
        jPanel1.add(Tipo04);
        Tipo04.setBounds(940, 50, 50, 17);

        Codigo04.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jPanel1.add(Codigo04);
        Codigo04.setBounds(990, 50, 90, 25);

        panelOnda04.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelOnda04.setToolTipText("");
        panelOnda04.setPreferredSize(new java.awt.Dimension(250, 250));
        jPanel1.add(panelOnda04);
        panelOnda04.setBounds(910, 170, 300, 260);

        panelBarra04.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelBarra04.setPreferredSize(new java.awt.Dimension(250, 250));
        jPanel1.add(panelBarra04);
        panelBarra04.setBounds(910, 430, 300, 260);

        Detalhes2.setColumns(20);
        Detalhes2.setRows(5);
        jScrollPane1.setViewportView(Detalhes2);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(310, 80, 290, 80);

        Detalhes1.setColumns(20);
        Detalhes1.setRows(5);
        jScrollPane2.setViewportView(Detalhes1);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(10, 80, 290, 80);

        Detalhes3.setColumns(20);
        Detalhes3.setRows(5);
        jScrollPane3.setViewportView(Detalhes3);

        jPanel1.add(jScrollPane3);
        jScrollPane3.setBounds(610, 80, 290, 80);

        Detalhes4.setColumns(20);
        Detalhes4.setRows(5);
        jScrollPane4.setViewportView(Detalhes4);

        jPanel1.add(jScrollPane4);
        jScrollPane4.setBounds(920, 80, 290, 80);

        jButton4.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jButton4.setText("Ok");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4);
        jButton4.setBounds(1080, 50, 50, 27);

        jButton1.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jButton1.setText("Ok");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(150, 50, 50, 27);

        jButton2.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jButton2.setText("Ok");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(460, 50, 50, 27);

        jButton3.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jButton3.setText("Ok");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3);
        jButton3.setBounds(780, 50, 50, 27);

        Limpa1.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        Limpa1.setText("Clean");
        Limpa1.setToolTipText("");
        Limpa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Limpa1ActionPerformed(evt);
            }
        });
        jPanel1.add(Limpa1);
        Limpa1.setBounds(210, 50, 80, 27);

        Limpa2.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        Limpa2.setText("Clean");
        Limpa2.setToolTipText("");
        Limpa2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Limpa2ActionPerformed(evt);
            }
        });
        jPanel1.add(Limpa2);
        Limpa2.setBounds(520, 50, 80, 27);

        Limpa3.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        Limpa3.setText("Clean");
        Limpa3.setToolTipText("");
        Limpa3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Limpa3ActionPerformed(evt);
            }
        });
        jPanel1.add(Limpa3);
        Limpa3.setBounds(840, 50, 80, 27);

        Limpa4.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        Limpa4.setText("Clean");
        Limpa4.setToolTipText("");
        Limpa4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Limpa4ActionPerformed(evt);
            }
        });
        jPanel1.add(Limpa4);
        Limpa4.setBounds(1140, 50, 80, 27);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Codigo01ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Codigo01ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Codigo01ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:        
        this.dispose();
    }//GEN-LAST:event_formWindowClosing

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int captura1 = 0;
        
        try{
            captura1 = Integer.parseInt(Codigo01.getText());
            bean1 = capdao.localiza(captura1);
            if (bean1 != null)
                atualizaGrafico(bean1, panelOnda01, panelBarra01, Codigo01, Tipo01, Detalhes1);
            else{
                JOptionPane.showMessageDialog(rootPane, "Capture not Found !");
                Codigo01.requestFocus();
            }
        } catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(rootPane, "Please, type a valid capture !");
            Codigo01.requestFocus();
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int captura2 = 0;
        
        try{
            captura2 = Integer.parseInt(Codigo02.getText());
            bean2 = capdao.localiza(captura2);
            if (bean1 != null)
                atualizaGrafico(bean2, panelOnda02, panelBarra02, Codigo02, Tipo02, Detalhes2);
            else{
                JOptionPane.showMessageDialog(rootPane, "Capture not Found !");
                Codigo02.requestFocus();
            }
        } catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(rootPane, "Please, type a valid capture !");
            Codigo02.requestFocus();
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int captura3 = 0;
        
        try{
            captura3 = Integer.parseInt(Codigo03.getText());
            bean3 = capdao.localiza(captura3);
            if (bean3 != null)
                atualizaGrafico(bean3, panelOnda03, panelBarra03, Codigo03, Tipo03, Detalhes3);
            else{
                JOptionPane.showMessageDialog(rootPane, "Capture not Found !");
                Codigo03.requestFocus();
            }            
        } catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(rootPane, "Please, type a valid capture !");
            Codigo03.requestFocus();
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        int captura4 = 0;
        
        try{
            captura4 = Integer.parseInt(Codigo04.getText());
            bean4 = capdao.localiza(captura4);
            if (bean4 != null)
                atualizaGrafico(bean4, panelOnda04, panelBarra04, Codigo04, Tipo04, Detalhes4);
            else{
                JOptionPane.showMessageDialog(rootPane, "Capture not Found !");
                Codigo04.requestFocus();
            }       
        } catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(rootPane, "Please, type a valid capture !");
            Codigo04.requestFocus();
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void Limpa3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Limpa3ActionPerformed
        // TODO add your handling code here:
        limpaGrafico(panelOnda03, panelBarra03, Codigo03, Tipo03, Detalhes3);
    }//GEN-LAST:event_Limpa3ActionPerformed

    private void Limpa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Limpa1ActionPerformed
        // TODO add your handling code here:
        limpaGrafico(panelOnda01, panelBarra01, Codigo01, Tipo01, Detalhes1);
    }//GEN-LAST:event_Limpa1ActionPerformed

    private void Limpa2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Limpa2ActionPerformed
        // TODO add your handling code here:
        limpaGrafico(panelOnda02, panelBarra02, Codigo02, Tipo02, Detalhes2);
    }//GEN-LAST:event_Limpa2ActionPerformed

    private void Limpa4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Limpa4ActionPerformed
        // TODO add your handling code here:
        limpaGrafico(panelOnda04, panelBarra04, Codigo04, Tipo04, Detalhes4);
    }//GEN-LAST:event_Limpa4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                FormMostraCapturadas dialog = null;
                try {
                    dialog = new FormMostraCapturadas(new javax.swing.JFrame(), true);
                    //dialog = new FormPSupervisao(new javax.swing.JFrame(), true, 1);
                } catch (Exception ex) {
                    Logger.getLogger(FormMostraCapturadas.class.getName()).log(Level.SEVERE, null, ex);
                }
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);                                                                
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Codigo01;
    private javax.swing.JTextField Codigo02;
    private javax.swing.JTextField Codigo03;
    private javax.swing.JTextField Codigo04;
    private javax.swing.JTextArea Detalhes1;
    private javax.swing.JTextArea Detalhes2;
    private javax.swing.JTextArea Detalhes3;
    private javax.swing.JTextArea Detalhes4;
    private javax.swing.JButton Limpa1;
    private javax.swing.JButton Limpa2;
    private javax.swing.JButton Limpa3;
    private javax.swing.JButton Limpa4;
    private javax.swing.JLabel Tipo01;
    private javax.swing.JLabel Tipo02;
    private javax.swing.JLabel Tipo03;
    private javax.swing.JLabel Tipo04;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel panelBarra01;
    private javax.swing.JPanel panelBarra02;
    private javax.swing.JPanel panelBarra03;
    private javax.swing.JPanel panelBarra04;
    private javax.swing.JPanel panelOnda01;
    private javax.swing.JPanel panelOnda02;
    private javax.swing.JPanel panelOnda03;
    private javax.swing.JPanel panelOnda04;
    // End of variables declaration//GEN-END:variables
}
