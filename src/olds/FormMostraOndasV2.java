package olds;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FormMostraOndasV2.java
 *
 * Created on 02/08/2012, 15:35:21
 */



import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartPanel;
import persistencia.Conexao;

/**
 *
 * @author Administrator
 */
public class FormMostraOndasV2 extends javax.swing.JFrame {

    /** Creates new form FormMostraOndasV2 */
    public FormMostraOndasV2() {
        initComponents();
        int codonda = 0;
        int codondafase = 0;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabOnda = new javax.swing.JTable();
        panelFase = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jbRefresh = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(null);

        tabOnda.setModel(new javax.swing.table.DefaultTableModel(
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
        tabOnda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabOndaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabOnda);

        jPanel2.add(jScrollPane2);
        jScrollPane2.setBounds(10, 20, 419, 189);

        panelFase.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelFase.setPreferredSize(new java.awt.Dimension(240, 220));

        javax.swing.GroupLayout panelFaseLayout = new javax.swing.GroupLayout(panelFase);
        panelFase.setLayout(panelFaseLayout);
        panelFaseLayout.setHorizontalGroup(
            panelFaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 614, Short.MAX_VALUE)
        );
        panelFaseLayout.setVerticalGroup(
            panelFaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 304, Short.MAX_VALUE)
        );

        jPanel2.add(panelFase);
        panelFase.setBounds(10, 220, 620, 310);

        jLabel1.setText("Tomada 1");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(0, 0, 67, 17);

        jbRefresh.setText("Refresh");
        jbRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRefreshActionPerformed(evt);
            }
        });
        jPanel2.add(jbRefresh);
        jbRefresh.setBounds(440, 20, 93, 57);

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
        jScrollPane1.setViewportView(tabela);

        jButton1.setText("Seleciona");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("SET AS PADRAO");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(jButton2)))
                .addContainerGap(1270, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(318, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabOndaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabOndaMouseClicked
        // TODO add your handling code here:
        // TODO add your handling code here:
        Object codOndaOBJ = 0;
        codOndaOBJ = (tabOnda.getModel().getValueAt(tabOnda.getSelectedRow(), 3));
        String recebe = String.valueOf(codOndaOBJ);
        //codondafase = Integer.parseInt(recebe);



        ChartPanel chartPanel = null;
//        try {
//            chartPanel = new ChartPanel(createChart1(codondafase));
//        } catch (SQLException ex) {
//            Logger.getLogger(FormPadrao.class.getName()).log(Level.SEVERE, null, ex);
//        }
        chartPanel.setVisible(true);
        chartPanel.setSize(panelFase.getWidth(), panelFase.getHeight());
        panelFase.removeAll();
        panelFase.add(chartPanel);
        panelFase.revalidate();
        panelFase.repaint();
}//GEN-LAST:event_tabOndaMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Object equip2Obj = 0;
        int padrao = 0, ativo = 0;
        String consulta = "";

        double eficaz = 0;
        double gain = 0;
        double offset = 0;
        String sql = "select codondapadrao as cod from capturaondapadrao where codequipamento = ? and codtipoonda = 1";
        //String sqlAtualiza = "insert into capturaondapadrao (codondapadrao, codtipoonda, codtomada, codequipamento, valormedio, offset, gain, eficaz, datapadrao)"

        equip2Obj = (tabela.getModel().getValueAt(tabela.getSelectedRow(), 0));
        String recebe = String.valueOf(equip2Obj);
        int equip2 = Integer.parseInt(recebe);
        System.out.println("EQUIP - " + equip2);

        //PreparedStatement ps = Conexao.getPreparedStatement(sql);
//        try {
            //ps.setInt(1, equip2);
            //ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                padrao = rs.getInt("cod");
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(FormPadrao.class.getName()).log(Level.SEVERE, null, ex);
//        }


        //        for(int i=0; i<12; i++){
        //            System.out.println("Onda numero "+codondafase+ "codHarm - "+i+"Sen - "+sen[i]+ "Cos - "+cos[i] );
        //
        //        }

        System.out.println("COD - " + padrao);

        if (padrao != 0) {
            consulta = JOptionPane.showInputDialog("Onda ja possui padrao! Sobreescrever?? <0=nao / 1=sim>");
            ativo = Integer.parseInt(consulta);
            System.out.println("consulta " + ativo);
            if (ativo != 0) {

            }
        }


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jbRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRefreshActionPerformed
        // TODO add your handling code here:
        //jButton1.doClick();
        jButton1ActionPerformed(evt);
}//GEN-LAST:event_jbRefreshActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Object equipObj = 0;

        String data = "";
        double eficaz = 0;
        int codevt = 0;
        String evto = "";

        equipObj = (tabela.getModel().getValueAt(tabela.getSelectedRow(), 0));
        String recebe = String.valueOf(equipObj);
        int tom = Integer.parseInt(recebe);
        System.out.println("tom - "+tom);

        String sqlOnda = "select c.dataatual as data, c.codevento as evt, c.eficaz as efz, "
                + "codondaatual as cod, e.descricao as descc from capturaondaatual c, eventos e "
                + "where (c.codevento = 1 or c.codevento = 6 or c.codevento = 2 or c.codevento = 3) and codtomada = ? "
                + "and c.codevento = e.codevento "
                + "order by c.dataatual desc ;";


        //PreparedStatement psO = Conexao.getPreparedStatement(sqlOnda);

        Vector titulos = new Vector();
        titulos.add("Data e Hora");
        titulos.add("Evento");
        titulos.add("Eficaz");
        titulos.add("Cod Onda");

        Vector linhas = new Vector();
        Vector colunas;

        //try {
            //psO.setInt(1, tom);
            //ResultSet rsO = psO.executeQuery();
//            while (rsO.next()) {
//
//                colunas = new Vector();
//                data = rsO.getString("data");
//                codevt = rsO.getInt("evt");
//                eficaz = rsO.getDouble("efz");
//                codonda = rsO.getInt("cod");
//                evto = rsO.getString("descc");
//                colunas.add(data);
//                colunas.add(evto);
//                colunas.add(eficaz);
//                colunas.add(codonda);
//                linhas.add(colunas);
//            }
            tabOnda.setModel(new DefaultTableModel(linhas, titulos));



        //} catch (SQLException ex) {
            //Logger.getLogger(FormPadrao.class.getName()).log(Level.SEVERE, null, ex);
        //}

        panelFase.removeAll();
        panelFase.repaint();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormMostraOndasV2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbRefresh;
    private javax.swing.JPanel panelFase;
    private javax.swing.JTable tabOnda;
    private javax.swing.JTable tabela;
    // End of variables declaration//GEN-END:variables

}
