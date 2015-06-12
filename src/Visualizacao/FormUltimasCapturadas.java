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

public class FormUltimasCapturadas extends javax.swing.JDialog {
    private String mensagem;
    
    private static int MAXONDAS = 4;    
    CapturaAtualDAO capdao = new CapturaAtualDAO();
    UsoSalaExtrasDAO extradao = new UsoSalaExtrasDAO();    
    
    CapturaAtual ondas[]  = new CapturaAtual[MAXONDAS];
    int       codondas[]  = new int[MAXONDAS];               
    
    private Collection<CapturaAtual> lista = new ArrayList<CapturaAtual>();
    
    private TFormUltimasCapturadas thread = new TFormUltimasCapturadas(this);
    
    /** Creates new form FormPSupervisaoX */
    public FormUltimasCapturadas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

        this.setTitle("Last Waveform Stored");        

        add(new JScrollPane(this.jPanel1));        
               
        /* thread para atualizar a tabela de equipamentos numa sala */     
        
        initComponents();
        thread.start();        
    }
    
    public void atualizaTabela(){
        int i=0;        
        
        for (CapturaAtual bean : capdao.listaFoFaseFuga(MAXONDAS, CheckFase.isSelected(), CheckFuga.isSelected())){
        //for(BeanSupervisao bean : extradao.listaUltimasFO(sala)){ muito lento         
            ondas[i]    = bean;
            //System.out.println("i " + i + " Captura: " + ondas[i].getCodCaptura());
            /*
            if (ondas[i] == null){
                ondas[i]    = bean;
                //codondas[i] = bean.getCodCaptura();
                flag = true;
            }
            else if (codondas[i] != ondas[i].getCodCaptura()){
                    ondas[i]    = bean;
                    //codondas[i] = bean.getCodCaptura();
                    flag = true;
            }
            */
            i++;            
        }        
        
        // System.out.println("Vai montar os graficos");
        MontaGrafico();
       
        
       // System.out.println("Bytes livres: " + Runtime.getRuntime().freeMemory());
    }
    
    public void MontaGrafico()  {
        int i=0; // [0]

        if (codondas[i] != ondas[i].getCodCaptura()){ /* primeiro espaço de grafico */
            codondas[i] = ondas[i].getCodCaptura();        
            atualizaGrafico(i, panelOnda01, panelBarra01, Codigo01, Tipo01, Detalhes1);                  
        }
       
        i++; // [1]
        if (codondas[i] != ondas[i].getCodCaptura()){ /* segundo espaço de grafico */
            codondas[i] = ondas[i].getCodCaptura();        
            atualizaGrafico(i, panelOnda02, panelBarra02, Codigo02, Tipo02, Detalhes2);
        }        
        
        i++; // [2]
        if (codondas[i] != ondas[i].getCodCaptura()){ /* terceiro espaço de grafico */
            codondas[i] = ondas[i].getCodCaptura();        
            atualizaGrafico(i, panelOnda03, panelBarra03, Codigo03, Tipo03, Detalhes3            );            
        }

        i++; //[3]
        if (codondas[i] != ondas[i].getCodCaptura()){ /* terceiro espaço de grafico */
            codondas[i] = ondas[i].getCodCaptura();        
            atualizaGrafico(i, panelOnda04, panelBarra04, Codigo04, Tipo04,  Detalhes4);            
        }        
        i++; //[4]        
    }
       
    private void atualizaGrafico(int indice, JPanel panelOnda, JPanel panelBarra, 
                                 JTextField codigo, JLabel tipo, JTextArea det){                
        
        

        final JFreeChart chart1 = CriaGrafico.createChartLinhas(ondas[indice], false);
        final ChartPanel crtPanelOnda = new ChartPanel(chart1);
        
        crtPanelOnda.setPreferredSize(new java.awt.Dimension(295, 260));       
        panelOnda.removeAll();               
        panelOnda.add(crtPanelOnda);     
        panelOnda.revalidate();
        panelOnda.repaint();


        final JFreeChart chart2 = CriaGrafico.createChartBarras(ondas[indice], false, false);       
        final ChartPanel crtPanelBarra = new ChartPanel(chart2);

        crtPanelBarra.setPreferredSize(new java.awt.Dimension(295, 260));        
       
        panelBarra.removeAll();               
        panelBarra.add(crtPanelBarra);       
        panelBarra.revalidate();
        panelBarra.repaint();

        codigo.setText(Integer.toString(codondas[indice]));        
        tipo.setText(ondas[indice].getCodTipoOnda().getCodTipoOnda() == 1 ? "Phase" : "Leakage");
        det.setText("");
        
        det.append("Time: " + Conversoes.CalendarToStringDH(ondas[indice].getDataAtual()) + " ");
        det.append("RMS: " + FormataNumeros.FormataSignificativos(ondas[indice].getEficaz(), Configuracoes.DIGITOSSIGNIFICATIVOS)+ "\n");
        det.append("Equipment: " + ondas[indice].getCodEquip().getDesc() + "\n" + 
                         ondas[indice].getCodEquip().getCodMarca().getDesc().trim() + " - " + 
                         ondas[indice].getCodEquip().getCodModelo().getDesc().trim() +  "\n"+
                         "RFID: " + ondas[indice].getCodEquip().getRfid());
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
        CheckFase = new javax.swing.JCheckBox();
        CheckFuga = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

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

        Tipo03.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Tipo03.setText("Capture:");
        Tipo03.setPreferredSize(new java.awt.Dimension(77, 17));
        jPanel1.add(Tipo03);
        Tipo03.setBounds(620, 50, 77, 17);

        Tipo01.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Tipo01.setText("Capture:");
        Tipo01.setPreferredSize(new java.awt.Dimension(77, 17));
        jPanel1.add(Tipo01);
        Tipo01.setBounds(10, 50, 70, 17);

        Tipo02.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Tipo02.setText("Capture:");
        Tipo02.setPreferredSize(new java.awt.Dimension(77, 17));
        jPanel1.add(Tipo02);
        Tipo02.setBounds(320, 50, 77, 17);

        Codigo01.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        Codigo01.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Codigo01ActionPerformed(evt);
            }
        });
        jPanel1.add(Codigo01);
        Codigo01.setBounds(90, 50, 90, 25);

        Codigo02.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jPanel1.add(Codigo02);
        Codigo02.setBounds(400, 50, 90, 25);

        Codigo03.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jPanel1.add(Codigo03);
        Codigo03.setBounds(700, 50, 90, 25);

        Tipo04.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Tipo04.setText("Capture:");
        Tipo04.setPreferredSize(new java.awt.Dimension(77, 17));
        jPanel1.add(Tipo04);
        Tipo04.setBounds(920, 50, 77, 17);

        Codigo04.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jPanel1.add(Codigo04);
        Codigo04.setBounds(1000, 50, 90, 25);

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

        CheckFase.setText("Show Turn on (phase)");
        CheckFase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckFaseActionPerformed(evt);
            }
        });
        jPanel1.add(CheckFase);
        CheckFase.setBounds(790, 10, 290, 24);

        CheckFuga.setSelected(true);
        CheckFuga.setText("Show Diferential (leakage)");
        CheckFuga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckFugaActionPerformed(evt);
            }
        });
        jPanel1.add(CheckFuga);
        CheckFuga.setBounds(190, 10, 330, 24);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Codigo01ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Codigo01ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Codigo01ActionPerformed

    private void CheckFugaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckFugaActionPerformed
        // TODO add your handling code here:
        if (! CheckFase.isSelected())
            if (! CheckFuga.isSelected()){
                JOptionPane.showMessageDialog(rootPane, "Must be select one waveform type at least");
                CheckFuga.setSelected(true);
            }
    }//GEN-LAST:event_CheckFugaActionPerformed

    private void CheckFaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckFaseActionPerformed
        // TODO add your handling code here:
        if (! CheckFuga.isSelected())
            if (! CheckFase.isSelected()){
                JOptionPane.showMessageDialog(rootPane, "Must be select one waveform type at least");
                CheckFase.setSelected(true);
            }
    }//GEN-LAST:event_CheckFaseActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        thread.setAcaba();
        this.dispose();
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                FormUltimasCapturadas dialog = null;
                try {
                    dialog = new FormUltimasCapturadas(new javax.swing.JFrame(), true);
                    //dialog = new FormPSupervisao(new javax.swing.JFrame(), true, 1);
                } catch (Exception ex) {
                    Logger.getLogger(FormUltimasCapturadas.class.getName()).log(Level.SEVERE, null, ex);
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
    private javax.swing.JCheckBox CheckFase;
    private javax.swing.JCheckBox CheckFuga;
    private javax.swing.JTextField Codigo01;
    private javax.swing.JTextField Codigo02;
    private javax.swing.JTextField Codigo03;
    private javax.swing.JTextField Codigo04;
    private javax.swing.JTextArea Detalhes1;
    private javax.swing.JTextArea Detalhes2;
    private javax.swing.JTextArea Detalhes3;
    private javax.swing.JTextArea Detalhes4;
    private javax.swing.JLabel Tipo01;
    private javax.swing.JLabel Tipo02;
    private javax.swing.JLabel Tipo03;
    private javax.swing.JLabel Tipo04;
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
