/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FormPadrao.java
 *
 * Created on Jun 28, 2012, 1:49:32 PM
 */
package olds;

import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.CombinedDomainCategoryPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import persistencia.Conexao;

/**
 *
 * @author cliente
 */
public class FormPadrao_Old extends javax.swing.JDialog {

    int codondadif = 0;
    int codondafase = 0;
    int codonda = 0;
    

    /** Creates new form FormPadrao */
    public FormPadrao_Old(java.awt.Frame parent, boolean modal) throws SQLException {
        super(parent, modal);
        initComponents();

        iniciaTabela();

    }

    public void iniciaTabela() throws SQLException {
        int equip = 0;
        String desc = "";

        String sql = "select u.codequip as eq, eq.desc as dsc from usosalaequip u, equipamento eq where u.codequip = eq.codequip";

        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        ResultSet rs = ps.executeQuery();

        Vector titulos = new Vector();
        titulos.add("Equipamento");
        titulos.add("Descricao");
        Vector linhas = new Vector();
        Vector colunas;

        while (rs.next()) {
            colunas = new Vector();
            equip = rs.getInt("eq");
            desc = rs.getString("dsc");
            colunas.add(equip);
            colunas.add(desc);
            linhas.add(colunas);
        }
        tabela.setModel(new DefaultTableModel(linhas, titulos));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabFase = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabDif = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        panelFase = new javax.swing.JPanel();
        panelDif = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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

        tabFase.setModel(new javax.swing.table.DefaultTableModel(
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
        tabFase.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabFaseMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabFase);

        tabDif.setModel(new javax.swing.table.DefaultTableModel(
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
        tabDif.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabDifMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabDif);

        jButton1.setText("Seleciona");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        panelFase.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelFase.setPreferredSize(new java.awt.Dimension(240, 220));

        javax.swing.GroupLayout panelFaseLayout = new javax.swing.GroupLayout(panelFase);
        panelFase.setLayout(panelFaseLayout);
        panelFaseLayout.setHorizontalGroup(
            panelFaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 394, Short.MAX_VALUE)
        );
        panelFaseLayout.setVerticalGroup(
            panelFaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 287, Short.MAX_VALUE)
        );

        panelDif.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelDif.setPreferredSize(new java.awt.Dimension(240, 220));

        javax.swing.GroupLayout panelDifLayout = new javax.swing.GroupLayout(panelDif);
        panelDif.setLayout(panelDifLayout);
        panelDifLayout.setHorizontalGroup(
            panelDifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 394, Short.MAX_VALUE)
        );
        panelDifLayout.setVerticalGroup(
            panelDifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 287, Short.MAX_VALUE)
        );

        jLabel1.setText("FASE - ONDA ATUAL");

        jLabel2.setText("DIFERENCIAL - ONDA ATUAL");

        jButton2.setText("SET AS PADRAO");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("SET AS PADRAO");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(panelFase, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(panelDif, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel1)
                        .addGap(116, 116, 116)
                        .addComponent(jButton1)
                        .addGap(120, 120, 120)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(jButton2)
                        .addGap(282, 282, 282)
                        .addComponent(jButton3)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(17, 17, 17))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panelFase, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                    .addComponent(panelDif, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Object equipObj = 0;
        int codfase, coddif = 0;
        int countF = 0;
        int countD = 0;
        equipObj = (tabela.getModel().getValueAt(tabela.getSelectedRow(), 0));
        String recebe = String.valueOf(equipObj);
        int equip = Integer.parseInt(recebe);

        String sqlFase = "select codondaatual as cod from capturaondaatual where codequipamento = ? and codtipoonda = 1 group by codondaatual desc limit 100";
        String sqlDif = "select codondaatual as cod from capturaondaatual where codequipamento = ? and codtipoonda = 2 group by codondaatual desc limit 100";

        PreparedStatement psF = Conexao.getPreparedStatement(sqlFase);
        PreparedStatement psD = Conexao.getPreparedStatement(sqlDif);

        Vector titulos = new Vector();
        titulos.add("Codonda");

        Vector linhasD = new Vector();
        Vector linhasF = new Vector();
        Vector colunasF;
        Vector colunasD;
        try {
            psF.setInt(1, equip);
            ResultSet rsF = psF.executeQuery();
            while (rsF.next()) {
            
                colunasF = new Vector();
                codfase = rsF.getInt("cod");
                colunasF.add(codfase);
                linhasF.add(colunasF);
        }
            tabFase.setModel(new DefaultTableModel(linhasF, titulos));

            psD.setInt(1, equip);
            ResultSet rsD = psD.executeQuery();
            while (rsD.next()) {
        
                colunasD = new Vector();
                coddif = rsD.getInt("cod");
                colunasD.add(coddif);
                linhasD.add(colunasD);
           
            }
            tabDif.setModel(new DefaultTableModel(linhasD, titulos));

        } catch (SQLException ex) {
            Logger.getLogger(FormPadrao_Old.class.getName()).log(Level.SEVERE, null, ex);
        }
        panelDif.removeAll();
        panelFase.removeAll();
        panelFase.repaint();
        panelDif.repaint();



    }//GEN-LAST:event_jButton1ActionPerformed

    private void tabFaseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabFaseMouseClicked
        // TODO add your handling code here:
        Object codOndaOBJ = 0;
        codOndaOBJ = (tabFase.getModel().getValueAt(tabFase.getSelectedRow(), 0));
        String recebe = String.valueOf(codOndaOBJ);
        codondafase = Integer.parseInt(recebe);



        ChartPanel chartPanel = null;
        try {
            chartPanel = new ChartPanel(createChart1(codondafase));
        } catch (SQLException ex) {
            Logger.getLogger(FormPadrao_Old.class.getName()).log(Level.SEVERE, null, ex);
        }
        chartPanel.setVisible(true);
        chartPanel.setSize(panelFase.getWidth(), panelFase.getHeight());
        panelFase.removeAll();
        panelFase.add(chartPanel);
        panelFase.revalidate();
        panelFase.repaint();


    }//GEN-LAST:event_tabFaseMouseClicked

    private void tabDifMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabDifMouseClicked
        // TODO add your handling code here:
        Object codOndaOBJ = 0;
        codOndaOBJ = (tabDif.getModel().getValueAt(tabDif.getSelectedRow(), 0));
        String recebe = String.valueOf(codOndaOBJ);
        codondadif = Integer.parseInt(recebe);



        ChartPanel chartPanel = null;
        try {
            chartPanel = new ChartPanel(createChart1(codondadif));
        } catch (SQLException ex) {
            Logger.getLogger(FormPadrao_Old.class.getName()).log(Level.SEVERE, null, ex);
        }
        chartPanel.setVisible(true);
        chartPanel.setSize(panelDif.getWidth(), panelDif.getHeight());
        panelDif.removeAll();
        panelDif.add(chartPanel);
        panelDif.revalidate();
        panelDif.repaint();
    }//GEN-LAST:event_tabDifMouseClicked

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

        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        try {
            ps.setInt(1, equip2);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                padrao = rs.getInt("cod");
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormPadrao_Old.class.getName()).log(Level.SEVERE, null, ex);
        }


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

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        Object equip3Obj = 0;
        int padrao = 0, ativo = 0;
        String consulta = "";
        String sql = "select codondapadrao as cod from capturaondapadrao where codequipamento = ? and codtipoonda =2";

        equip3Obj = (tabela.getModel().getValueAt(tabela.getSelectedRow(), 0));
        String recebe = String.valueOf(equip3Obj);
        int equip2 = Integer.parseInt(recebe);
        System.out.println("EQUIP - " + equip2);

        PreparedStatement ps2 = Conexao.getPreparedStatement(sql);
        try {
            ps2.setInt(1, equip2);
            ResultSet rs = ps2.executeQuery();
            while (rs.next()) {
                padrao = rs.getInt("cod");
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormPadrao_Old.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("COD - " + padrao);

        if (padrao != 0) {
            consulta = JOptionPane.showInputDialog("Onda ja possui padrao! Sobreescrever?? <0=nao / 1=sim>");
            ativo = Integer.parseInt(consulta);
            //System.out.println("consulta " + ativo);
            if (ativo != 0) {
            }
        }

        String sqlConsulta = "select cap.valormedio as vm, cap.gain as gain, cap.eficaz as eficaz, cap.offset as offset, "
                + "sen as sen, coss as cos, codharmonica as cod "
                + "from capturaondaatual cap, ondaatual "
                + "where cap.codondaatual = ? "
                + "and cap.codondaatual = codcapturaatual;";

        PreparedStatement ps3 = Conexao.getPreparedStatement(sqlConsulta);
    }//GEN-LAST:event_jButton3ActionPerformed

    public JFreeChart createChart1(int _codonda) throws SQLException {

        final CategoryDataset dataset1 = createDataset2(_codonda);
        final NumberAxis rangeAxis1 = new NumberAxis("Value");
        rangeAxis1.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis1.setAutoRangeIncludesZero(false);
        final LineAndShapeRenderer renderer1 = new LineAndShapeRenderer();
        //renderer1.
        renderer1.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
        final CategoryPlot subplot1 = new CategoryPlot(dataset1, null, rangeAxis1, renderer1);
        subplot1.setDomainGridlinesVisible(true);


        final CategoryAxis domainAxis = new CategoryAxis("");
        final CombinedDomainCategoryPlot plot = new CombinedDomainCategoryPlot(domainAxis);
        plot.add(subplot1, 2);



        final JFreeChart result = new JFreeChart(
                "",
                new Font("SansSerif", Font.BOLD, 12),
                plot,
                true);
        //      result.getLegend().setAnchor(Legend.SOUTH);
        return result;


    }

    public CategoryDataset createDataset2(int _codonda) throws SQLException {
        int val[] = new int[256];
        double sen[] = new double[12];
        double cos[] = new double[12];
        int codharmonica = 0;
        double vm = 0;


        this.codonda = _codonda;

        float tempo[] = new float[256];
        String type[] = new String[256];

        DefaultCategoryDataset result = new DefaultCategoryDataset();

        for (int i = 0; i < 256; i++) {
            val[i] = 0;
        }

        for (int i = 0; i < 12; i++) {
            sen[i] = 0;
            cos[i] = 0;
        }

        String sqlAtual = "select cap.valormedio as vm, "
                + "oa.sen as senn, oa.coss as cos, oa.codharmonica as cod "
                + "from capturaondaatual cap, ondaatual oa "
                + "where cap.codondaatual = ? "
                + "and cap.codondaatual = oa.codcapturaatual;";

        // row keys...
        final String series1 = "Atual";

        PreparedStatement ps = Conexao.getPreparedStatement(sqlAtual);

        System.out.println("codOndA "+ _codonda);

        ps.setInt(1, codonda);

        ResultSet rs = ps.executeQuery();

        try {
            while (rs.next()) {
                codharmonica = rs.getInt("cod");

                cos[codharmonica] = rs.getDouble("cos");
                sen[codharmonica] = rs.getDouble("senn");
            
                vm = rs.getDouble("vm");
            }
        } catch (Exception e) {
            System.out.println("Erro generico: " + e.getMessage());
            e.printStackTrace();
        }

            //System.out.println("sen -"+sen[codharmonica - 1]);

        float s, t = 0;
        for (int i = 0; i < 256; i++) {
            if (i == 0) {
                tempo[i] = t = 0;
            } else {
                tempo[i] = (t + (float) (1.0 / (60 * 256)));
            }
            type[i] = String.valueOf(i);
            //System.out.println("num "+sen[0]+" seno "+Math.sin(sen[0]));
            //System.out.printf("%f\n ", tempo[i]);
            s = (float) (vm / 2);

            for (int x = 0; x < 12; x++) {
                s += (double) (cos[x] * Math.cos(2 * Math.PI * ((x + 1) * 60) * tempo[i])) + (sen[x] * Math.sin(-2 * Math.PI * ((x + 1) * 60) * tempo[i]));

            }
            val[i] = (int) ((s * (2.0)) / 256.0);

            t = tempo[i];
        }

        for (int i = 0; i < 256; i++) {
            result.addValue(val[i], series1, type[i]);
            //System.out.println("val "+val[i]+ "type "+type[i]);
        }

        return result;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                FormPadrao_Old dialog = null;
                try {
                    dialog = new FormPadrao_Old(new javax.swing.JFrame(), true);
                } catch (SQLException ex) {
                    Logger.getLogger(FormPadrao_Old.class.getName()).log(Level.SEVERE, null, ex);
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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel panelDif;
    private javax.swing.JPanel panelFase;
    private javax.swing.JTable tabDif;
    private javax.swing.JTable tabFase;
    private javax.swing.JTable tabela;
    // End of variables declaration//GEN-END:variables
}
