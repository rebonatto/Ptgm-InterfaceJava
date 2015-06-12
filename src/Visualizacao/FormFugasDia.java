
package Visualizacao;


import Graficos.CriaGrafico;
import Periculosidade.CorrenteRMS;
import Periculosidade.FrequenciaCorrente;
import Periculosidade.StatusPericulosidade;
import Threads.TFormFugasDia;
import Uteis.CalendarComboBox;
import Uteis.Conversoes;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import modelo.CapturaAtual;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import persistencia.CapturaAtualDAO;

/*
Melhorar o tratamento da falha na sala

Criar Objeto com classificação do nivel de periculosidade:
Dangerous Similaridade
Dangerous Corrente
Dangerous Frequencia

Atention Similaridade
Atention Corrente
Atention Frequencia

Normal

*/



public class FormFugasDia extends javax.swing.JDialog {
    private String mensagem;
        
    private CapturaAtualDAO dao = new CapturaAtualDAO();    
    private CapturaAtual bean = new CapturaAtual();
    
    private Collection<CapturaAtual> lista = new ArrayList<CapturaAtual>();
    
    private Vector titulos = new Vector();
    private Vector linhas = new Vector();
    
    private Calendar c = Calendar.getInstance();
    private Date datatmp = new Date(1);
    private int cont = 0;
    private String status = new String();
    private Timer t;
    private boolean pisca = false;
    
    
    private TFormFugasDia thread = new TFormFugasDia(this);
    
    /** Creates new form FormPSupervisaoX */
    public FormFugasDia(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setTitle("Últimas Ondas Capturadas");        

        add(new JScrollPane(this.jPanel1));                       

        thread.start();                                    
        
        c = Conversoes.DateToCalendar(new Date(System.currentTimeMillis()));
        c.set(2014, 11, 16);
        System.out.println(c.getTime());
        datatmp = Conversoes.CalendarToDate(c);
        
        comboData.setSelectedItem(Conversoes.CalendarToString(c));
        
        MontaTabela();
        /*
        t = new javax.swing.Timer(300, new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                if (jbl_status.isVisible() == true) {  
                    jbl_status.setVisible(false);  
                }else{  
                    jbl_status.setVisible(true);  
                }     
            }  
        });
        */
    }
        
    private void MontaTabela(){
        titulos.add("Captura");
        titulos.add("Tomada");
        titulos.add("Equipamento");
        titulos.add("Eficaz");
        titulos.add("Status Corrente");
        titulos.add("Status RMS na Frequencia");
        titulos.add("Status Similaridade");                                  
        
        tabela.setModel(new DefaultTableModel(linhas, titulos));
        tabela.setAutoCreateRowSorter(true);
        cont = 0;
        
        for (CapturaAtual bean : dao.listaFugas(datatmp)){
            addTabela(bean);
            cont++;
        }
        
    }
    
    public void atualizaTabela(boolean mudou){
        int i=0;        
        
        lista = dao.listaFugas(datatmp);
        
        if (cont != lista.size() || mudou){
            linhas.clear();
            tabela.removeAll();
            for (CapturaAtual bean : lista ){    
                addTabela(bean);
            }
            tabela.repaint();
            tabela.revalidate();
        }
        
        //MontaGrafico();                      
    }

    
     private void addTabela(CapturaAtual c) {
         // Verifica o status para decidir se inclui ou não
         status = StatusPericulosidade.Status(c);
         
         //System.out.println(c.getCodCaptura() + " " + status);
         
         if (status.charAt(0) == 'D' ){    
             if (! jbl_status.getText().equalsIgnoreCase("Dangerous") )
                jbl_status.setText(status);
            jbl_status.setForeground(java.awt.Color.RED);
         }
         
         if (status.charAt(0) == 'A' && 
             jbl_status.getText().charAt(0) != 'D')
             jbl_status.setText(status);
         
         if ((chkNormal.isSelected() && status.charAt(0) == 'N')   ||
             (chkAtencao.isSelected() && status.charAt(0) == 'A') ||
            status.charAt(0) == 'D') {
                      
            Vector colunas = new Vector();                    
            colunas.add(c.getCodCaptura());
            colunas.add(c.getCodTomada());
            colunas.add(c.getCodEquip());
            colunas.add(c.getEficaz());
            colunas.add(CorrenteRMS.StatusCorrente(c));
            colunas.add(FrequenciaCorrente.StatusFrequencia(c));
            if (c.getCodCaptura() % 3 == 0)
                colunas.add("No concurrent Waveforms");
            else
                if (c.getCodCaptura() % 2 == 0)
                    colunas.add("Atention");
                else
                    colunas.add("Normal");

            linhas.add(colunas);
         }
    }
    
    private void atualizaGrafico(){                
                
        final JFreeChart chart1 = CriaGrafico.createChartLinhas(bean, false);
        final ChartPanel crtPanelOnda = new ChartPanel(chart1);
        
        crtPanelOnda.setPreferredSize(new java.awt.Dimension(500, 370));       
        panelOnda.removeAll();               
        panelOnda.add(crtPanelOnda);     
        panelOnda.revalidate();
        panelOnda.repaint();


        final JFreeChart chart2 = CriaGrafico.createChartBarras(bean, false, false);       
        final ChartPanel crtPanelBarra = new ChartPanel(chart2);

        crtPanelBarra.setPreferredSize(new java.awt.Dimension(500, 370));        
       
        panelBarras.removeAll();               
        panelBarras.add(crtPanelBarra);       
        panelBarras.revalidate();
        panelBarras.repaint();

    }      
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        comboData = new CalendarComboBox();
        jLabel6 = new javax.swing.JLabel();
        panelOnda = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        panelBarras = new javax.swing.JPanel();
        chkNormal = new javax.swing.JCheckBox();
        chkAtencao = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        jbl_status = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setPreferredSize(new java.awt.Dimension(1250, 720));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Data:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        comboData.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboData.setEnabled(false);
        jPanel1.add(comboData, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 124, -1));

        jLabel6.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Waveform");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 480, -1));

        panelOnda.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelOnda.setPreferredSize(new java.awt.Dimension(240, 220));
        jPanel1.add(panelOnda, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 500, 370));

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("DC and Harmonics");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 290, 480, -1));

        panelBarras.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelBarras.setPreferredSize(new java.awt.Dimension(240, 220));
        jPanel1.add(panelBarras, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 310, 500, 370));

        chkNormal.setText("Normal");
        chkNormal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkNormalActionPerformed(evt);
            }
        });
        jPanel1.add(chkNormal, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, -1, -1));

        chkAtencao.setText("Atention");
        chkAtencao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAtencaoActionPerformed(evt);
            }
        });
        jPanel1.add(chkAtencao, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, -1, -1));

        jLabel3.setText("Situação da Sala:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 20, -1, -1));

        jbl_status.setFont(new java.awt.Font("Ubuntu", 0, 36)); // NOI18N
        jbl_status.setText("Normal");
        jPanel1.add(jbl_status, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 0, 360, -1));

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaMouseClicked(evt);
            }
        });
        tabela.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tabelaKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabela);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 990, 230));

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        thread.setAcaba();
        this.dispose();
    }//GEN-LAST:event_formWindowClosing

    private void chkNormalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkNormalActionPerformed
        // TODO add your handling code here:
        atualizaTabela(true);
    }//GEN-LAST:event_chkNormalActionPerformed

    private void chkAtencaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAtencaoActionPerformed
        // TODO add your handling code here:
        atualizaTabela(true);
    }//GEN-LAST:event_chkAtencaoActionPerformed

    private void tabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseClicked
        // TODO add your handling code here:
                // TODO add your handling code here:
        int linha = tabela.getSelectedRow();

        if (linha == -1){
            JOptionPane.showMessageDialog(null, "Não Existem Fugas");
            return;
        }

        bean = dao.localiza((Integer) tabela.getValueAt(linha, 0));

        atualizaGrafico();
    }//GEN-LAST:event_tabelaMouseClicked

    private void tabelaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelaKeyReleased
        // TODO add your handling code here:
        if (tabela.getSelectedRow() != -1) {
            this.tabelaMouseClicked(null);
        }
    }//GEN-LAST:event_tabelaKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                FormFugasDia dialog = null;
                try {
                    dialog = new FormFugasDia(new javax.swing.JFrame(), true);
                    //dialog = new FormPSupervisao(new javax.swing.JFrame(), true, 1);
                } catch (Exception ex) {
                    Logger.getLogger(FormFugasDia.class.getName()).log(Level.SEVERE, null, ex);
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
    private javax.swing.JCheckBox chkAtencao;
    private javax.swing.JCheckBox chkNormal;
    private javax.swing.JComboBox comboData;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jbl_status;
    private javax.swing.JPanel panelBarras;
    private javax.swing.JPanel panelOnda;
    private javax.swing.JTable tabela;
    // End of variables declaration//GEN-END:variables
}
