/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FormFugas.java
 *
 * Created on Nov 4, 2011, 8:12:28 AM
 *
 * @author rebonatto
 */

package Visualizacao;

import Uteis.Conversoes;
import Uteis.CalendarComboBox;
import java.util.Calendar;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.CapturaAtual;
import modelo.Formularios.SumarizaEventos;
import persistencia.CapturaEventosDAO;

/**
 *
 * @author rebonatto
 */
public class FormSumarizaEventos extends javax.swing.JDialog {    
    private CapturaEventosDAO capdao = new CapturaEventosDAO();    
        
    private CapturaAtual bean = new CapturaAtual();            

    Vector titulosArquivadas = new Vector();
    Vector linhasArquivadas = new Vector();
    
    /** Creates new form FormEquipamento */
    public FormSumarizaEventos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
                
        setTitle("Events Summary");
        
        Calendar hoje = Calendar.getInstance();
        comboDataFim.setSelectedItem(Conversoes.CalendarToString(hoje));
        comboDataIni.setSelectedItem(Conversoes.CalendarToString(hoje));
        
        montaTabelaArquivadas() ;         
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        GrupoOndas = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        comboDataIni = new CalendarComboBox();
        jLabel1 = new javax.swing.JLabel();
        comboDataFim = new CalendarComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaArquivadas = new javax.swing.JTable();
        btnCarregaOndas = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(1250, 720));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setText("From:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        comboDataIni.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(comboDataIni, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 124, -1));

        jLabel1.setText("To:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, -1, -1));

        comboDataFim.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(comboDataFim, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, 124, -1));

        tabelaArquivadas.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelaArquivadas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaArquivadasMouseClicked(evt);
            }
        });
        tabelaArquivadas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tabelaArquivadasKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tabelaArquivadas);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 600, 170));

        btnCarregaOndas.setText("Refresh");
        btnCarregaOndas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCarregaOndasActionPerformed(evt);
            }
        });
        jPanel1.add(btnCarregaOndas, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, -1, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 651, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 37, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCarregaOndasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCarregaOndasActionPerformed
        // TODO add your handling code here:
        atualizaTabelaArquivadas();
    }//GEN-LAST:event_btnCarregaOndasActionPerformed

    private void tabelaArquivadasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelaArquivadasKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_tabelaArquivadasKeyReleased

    private void tabelaArquivadasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaArquivadasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabelaArquivadasMouseClicked
           

    
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                FormSumarizaEventos dialog = new FormSumarizaEventos(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    
    private int montaTabelaArquivadas() {
        //Ajusta titulos da tabela
        titulosArquivadas.add("Date");        
        titulosArquivadas.add("Event");         
        titulosArquivadas.add("Count");        
        titulosArquivadas.add("Last Event");        
                
        tabelaArquivadas.setModel(new DefaultTableModel(linhasArquivadas, titulosArquivadas));
        tabelaArquivadas.setAutoCreateRowSorter(true);

        return (linhasArquivadas.size());
    }

    private void atualizaTabelaArquivadas(){
        // inicializa os dados da tabela                        
        linhasArquivadas.clear();
        tabelaArquivadas.removeAll();    
               
        String  sIni = (String) comboDataIni.getSelectedItem();        
        String  sFim = (String) comboDataFim.getSelectedItem();                             
            
        for (SumarizaEventos s : capdao.listaSumarizaEventos(Conversoes.StringToCalendar(sIni), Conversoes.StringToCalendar(sFim) ) ) 
             addTabelaArquivadas(s);            
        
        JOptionPane.showMessageDialog(null, "Events updated" );
 
        tabelaArquivadas.revalidate();     
        tabelaArquivadas.repaint();
    }    
    
    private void addTabelaArquivadas(SumarizaEventos s) {                
              
        Vector colunas = new Vector();

        colunas.add(Conversoes.CalendarToString(s.getData()));                                
        colunas.add(s.getCodEvento());
        colunas.add(s.getQuantidade());
        colunas.add(Conversoes.CalendarToTime(s.getHorafinal()));                                

        linhasArquivadas.add(colunas);
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup GrupoOndas;
    private javax.swing.JButton btnCarregaOndas;
    private javax.swing.JComboBox comboDataFim;
    private javax.swing.JComboBox comboDataIni;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabelaArquivadas;
    // End of variables declaration//GEN-END:variables

}