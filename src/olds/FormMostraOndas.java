package olds;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FormMostraOndas.java
 *
 * Created on 30/07/2012, 16:10:10
 */


import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.CombinedDomainCategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import persistencia.Conexao;

/**
 *
 * @author Administrator
 */
public class FormMostraOndas extends javax.swing.JFrame {

    int codondadif = 0;
    int codondafase = 0;
    int codonda = 0;
    double sen[] = new double[12];
    double cos[] = new double[12];
    int codharmonica = 0;
    String type[] = new String[256];
    double vm = 0;

    /** Creates new form FormMostraOndas */
    public FormMostraOndas() throws SQLException {
        initComponents();
        iniciaTabela();

        add(new JScrollPane(this.jPanel1));
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        // adiciono a resolu��o da tela
        this.setSize(dimension.width, (dimension.height - 40));
    }

    public void iniciaTabela() throws SQLException {
        int tomada = 0;
        String desc = "";
        String data = "";
        double eficaz = 0;
        int codevt = 0;
        String evto = "";
        int tomadas[] = new int[6];

        for (int a = 0; a < 6; a++) {
            tomadas[a] = 0;
        }

        String sql = "select codtomada as tom from tomada";

        String sqlOnda = "select c.dataatual as data, c.codevento as evt, c.eficaz as efz, "
                + "codondaatual as cod, e.descricao as descc "
                + "from capturaondaatual c, eventos e "
                + "where (c.codevento = 1 or c.codevento = 6 or c.codevento = 2 or c.codevento = 3 or c.codevento = 4 or c.codevento = 5) and codtomada = ? "
                + "and c.codevento = e.codevento "
//                + "order by c.dataatual desc, c.codondaatual desc limit 100;";
                + "order by c.dataatual , c.codondaatual limit 500;";        

        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        ResultSet rs = ps.executeQuery();
        int i = 0;
        while (rs.next()) {
            tomadas[i] = rs.getInt("tom");
            //System.out.println("Tomada - " + tomadas[i] + "  No indice -  " + i);
            i++;
        }


        if (tomadas[0] != 0) {
            PreparedStatement psO = Conexao.getPreparedStatement(sqlOnda);
            Vector titulos = new Vector();
            titulos.add("Data e Hora");
            titulos.add("Evento");
            titulos.add("Eficaz");
            titulos.add("Cod Onda");

            Vector linhas = new Vector();
            Vector colunas;

            try {
                psO.setInt(1, tomadas[0]);
                ResultSet rsO = psO.executeQuery();
                while (rsO.next()) {

                    colunas = new Vector();
                    data = rsO.getString("data");
                    codevt = rsO.getInt("evt");
                    eficaz = rsO.getDouble("efz");
                    codonda = rsO.getInt("cod");
                    evto = rsO.getString("descc");
                    colunas.add(data);
                    colunas.add(evto);
                    colunas.add(eficaz);
                    colunas.add(codonda);
                    linhas.add(colunas);
                }
                tabOnda1.setModel(new DefaultTableModel(linhas, titulos));
            } catch (SQLException ex) {
                
            }
        }

        if (tomadas[1] != 0) {
            PreparedStatement psO = Conexao.getPreparedStatement(sqlOnda);
            Vector titulos = new Vector();
            titulos.add("Data e Hora");
            titulos.add("Evento");
            titulos.add("Eficaz");
            titulos.add("Cod Onda");

            Vector linhas = new Vector();
            Vector colunas;

            try {
                psO.setInt(1, tomadas[1]);
                ResultSet rsO = psO.executeQuery();
                while (rsO.next()) {

                    colunas = new Vector();
                    data = rsO.getString("data");
                    codevt = rsO.getInt("evt");
                    eficaz = rsO.getDouble("efz");
                    codonda = rsO.getInt("cod");
                    evto = rsO.getString("descc");
                    colunas.add(data);
                    colunas.add(evto);
                    colunas.add(eficaz);
                    colunas.add(codonda);
                    linhas.add(colunas);
                }
                tabOnda2.setModel(new DefaultTableModel(linhas, titulos));
            } catch (SQLException ex) {
                
            }
        }
        if (tomadas[2] != 0) {
            PreparedStatement psO = Conexao.getPreparedStatement(sqlOnda);
            Vector titulos = new Vector();
            titulos.add("Data e Hora");
            titulos.add("Evento");
            titulos.add("Eficaz");
            titulos.add("Cod Onda");

            Vector linhas = new Vector();
            Vector colunas;

            try {
                psO.setInt(1, tomadas[2]);
                ResultSet rsO = psO.executeQuery();
                while (rsO.next()) {

                    colunas = new Vector();
                    data = rsO.getString("data");
                    codevt = rsO.getInt("evt");
                    eficaz = rsO.getDouble("efz");
                    codonda = rsO.getInt("cod");
                    evto = rsO.getString("descc");
                    colunas.add(data);
                    colunas.add(evto);
                    colunas.add(eficaz);
                    colunas.add(codonda);
                    linhas.add(colunas);
                }
                tabOnda3.setModel(new DefaultTableModel(linhas, titulos));
            } catch (SQLException ex) {
                
            }
        }
        if (tomadas[3] != 0) {
            PreparedStatement psO = Conexao.getPreparedStatement(sqlOnda);
            Vector titulos = new Vector();
            titulos.add("Data e Hora");
            titulos.add("Evento");
            titulos.add("Eficaz");
            titulos.add("Cod Onda");

            Vector linhas = new Vector();
            Vector colunas;

            try {
                psO.setInt(1, tomadas[3]);
                ResultSet rsO = psO.executeQuery();
                while (rsO.next()) {

                    colunas = new Vector();
                    data = rsO.getString("data");
                    codevt = rsO.getInt("evt");
                    eficaz = rsO.getDouble("efz");
                    codonda = rsO.getInt("cod");
                    evto = rsO.getString("descc");
                    colunas.add(data);
                    colunas.add(evto);
                    colunas.add(eficaz);
                    colunas.add(codonda);
                    linhas.add(colunas);
                }
                tabOnda4.setModel(new DefaultTableModel(linhas, titulos));
            } catch (SQLException ex) {
                
            }
        }
        if (tomadas[4] != 0) {
            PreparedStatement psO = Conexao.getPreparedStatement(sqlOnda);
            Vector titulos = new Vector();
            titulos.add("Data e Hora");
            titulos.add("Evento");
            titulos.add("Eficaz");
            titulos.add("Cod Onda");

            Vector linhas = new Vector();
            Vector colunas;

            try {
                psO.setInt(1, tomadas[4]);
                ResultSet rsO = psO.executeQuery();
                while (rsO.next()) {

                    colunas = new Vector();
                    data = rsO.getString("data");
                    codevt = rsO.getInt("evt");
                    eficaz = rsO.getDouble("efz");
                    codonda = rsO.getInt("cod");
                    evto = rsO.getString("descc");
                    colunas.add(data);
                    colunas.add(evto);
                    colunas.add(eficaz);
                    colunas.add(codonda);
                    linhas.add(colunas);
                }
                tabOnda5.setModel(new DefaultTableModel(linhas, titulos));
            } catch (SQLException ex) {
                
            }
        }
        if (tomadas[5] != 0) {
            PreparedStatement psO = Conexao.getPreparedStatement(sqlOnda);
            Vector titulos = new Vector();
            titulos.add("Data e Hora");
            titulos.add("Evento");
            titulos.add("Eficaz");
            titulos.add("Cod Onda");

            Vector linhas = new Vector();
            Vector colunas;

            try {
                psO.setInt(1, tomadas[5]);
                ResultSet rsO = psO.executeQuery();
                while (rsO.next()) {

                    colunas = new Vector();
                    data = rsO.getString("data");
                    codevt = rsO.getInt("evt");
                    eficaz = rsO.getDouble("efz");
                    codonda = rsO.getInt("cod");
                    evto = rsO.getString("descc");
                    colunas.add(data);
                    colunas.add(evto);
                    colunas.add(eficaz);
                    colunas.add(codonda);
                    linhas.add(colunas);
                }
                tabOnda6.setModel(new DefaultTableModel(linhas, titulos));
            } catch (SQLException ex) {
                
            }
        }


    }

    //DefaultTableCellRender alinhamento = new DefaultTableCellRender
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
        tabOnda1 = new javax.swing.JTable();
        panelFase1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        PanelBarra1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabOnda2 = new javax.swing.JTable();
        panelFase2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        PanelBarra2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabOnda3 = new javax.swing.JTable();
        panelFase3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        PanelBarra3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabOnda4 = new javax.swing.JTable();
        panelFase4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        PanelBarra4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tabOnda5 = new javax.swing.JTable();
        panelFase5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        PanelBarra5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tabOnda6 = new javax.swing.JTable();
        panelFase6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        PanelBarra6 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(null);

        tabOnda1.setModel(new javax.swing.table.DefaultTableModel(
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
        tabOnda1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabOnda1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabOnda1);

        jPanel2.add(jScrollPane2);
        jScrollPane2.setBounds(10, 20, 620, 189);

        panelFase1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelFase1.setPreferredSize(new java.awt.Dimension(240, 220));

        javax.swing.GroupLayout panelFase1Layout = new javax.swing.GroupLayout(panelFase1);
        panelFase1.setLayout(panelFase1Layout);
        panelFase1Layout.setHorizontalGroup(
            panelFase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 334, Short.MAX_VALUE)
        );
        panelFase1Layout.setVerticalGroup(
            panelFase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 304, Short.MAX_VALUE)
        );

        jPanel2.add(panelFase1);
        panelFase1.setBounds(10, 220, 340, 310);

        jLabel1.setText("Tomada 1");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(10, 0, 110, 17);

        PanelBarra1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout PanelBarra1Layout = new javax.swing.GroupLayout(PanelBarra1);
        PanelBarra1.setLayout(PanelBarra1Layout);
        PanelBarra1Layout.setHorizontalGroup(
            PanelBarra1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 266, Short.MAX_VALUE)
        );
        PanelBarra1Layout.setVerticalGroup(
            PanelBarra1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 306, Short.MAX_VALUE)
        );

        jPanel2.add(PanelBarra1);
        PanelBarra1.setBounds(360, 220, 270, 310);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(null);

        tabOnda2.setModel(new javax.swing.table.DefaultTableModel(
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
        tabOnda2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabOnda2MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabOnda2);

        jPanel3.add(jScrollPane3);
        jScrollPane3.setBounds(10, 20, 620, 189);

        panelFase2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelFase2.setPreferredSize(new java.awt.Dimension(240, 220));

        javax.swing.GroupLayout panelFase2Layout = new javax.swing.GroupLayout(panelFase2);
        panelFase2.setLayout(panelFase2Layout);
        panelFase2Layout.setHorizontalGroup(
            panelFase2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 334, Short.MAX_VALUE)
        );
        panelFase2Layout.setVerticalGroup(
            panelFase2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 304, Short.MAX_VALUE)
        );

        jPanel3.add(panelFase2);
        panelFase2.setBounds(10, 220, 340, 310);

        jLabel2.setText("Tomada 2");
        jPanel3.add(jLabel2);
        jLabel2.setBounds(10, 0, 110, 17);

        PanelBarra2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout PanelBarra2Layout = new javax.swing.GroupLayout(PanelBarra2);
        PanelBarra2.setLayout(PanelBarra2Layout);
        PanelBarra2Layout.setHorizontalGroup(
            PanelBarra2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 266, Short.MAX_VALUE)
        );
        PanelBarra2Layout.setVerticalGroup(
            PanelBarra2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 306, Short.MAX_VALUE)
        );

        jPanel3.add(PanelBarra2);
        PanelBarra2.setBounds(360, 220, 270, 310);

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setLayout(null);

        tabOnda3.setModel(new javax.swing.table.DefaultTableModel(
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
        tabOnda3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabOnda3MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tabOnda3);

        jPanel4.add(jScrollPane4);
        jScrollPane4.setBounds(10, 20, 620, 189);

        panelFase3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelFase3.setPreferredSize(new java.awt.Dimension(240, 220));

        javax.swing.GroupLayout panelFase3Layout = new javax.swing.GroupLayout(panelFase3);
        panelFase3.setLayout(panelFase3Layout);
        panelFase3Layout.setHorizontalGroup(
            panelFase3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 334, Short.MAX_VALUE)
        );
        panelFase3Layout.setVerticalGroup(
            panelFase3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 304, Short.MAX_VALUE)
        );

        jPanel4.add(panelFase3);
        panelFase3.setBounds(10, 220, 340, 310);

        jLabel3.setText("Tomada 3");
        jPanel4.add(jLabel3);
        jLabel3.setBounds(10, 0, 60, 17);

        PanelBarra3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout PanelBarra3Layout = new javax.swing.GroupLayout(PanelBarra3);
        PanelBarra3.setLayout(PanelBarra3Layout);
        PanelBarra3Layout.setHorizontalGroup(
            PanelBarra3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 266, Short.MAX_VALUE)
        );
        PanelBarra3Layout.setVerticalGroup(
            PanelBarra3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 306, Short.MAX_VALUE)
        );

        jPanel4.add(PanelBarra3);
        PanelBarra3.setBounds(360, 220, 270, 310);

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setLayout(null);

        tabOnda4.setModel(new javax.swing.table.DefaultTableModel(
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
        tabOnda4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabOnda4MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tabOnda4);

        jPanel5.add(jScrollPane5);
        jScrollPane5.setBounds(10, 20, 620, 189);

        panelFase4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelFase4.setPreferredSize(new java.awt.Dimension(240, 220));

        javax.swing.GroupLayout panelFase4Layout = new javax.swing.GroupLayout(panelFase4);
        panelFase4.setLayout(panelFase4Layout);
        panelFase4Layout.setHorizontalGroup(
            panelFase4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 334, Short.MAX_VALUE)
        );
        panelFase4Layout.setVerticalGroup(
            panelFase4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 304, Short.MAX_VALUE)
        );

        jPanel5.add(panelFase4);
        panelFase4.setBounds(10, 220, 340, 310);

        jLabel4.setText("Tomada 4");
        jPanel5.add(jLabel4);
        jLabel4.setBounds(10, 0, 60, 17);

        PanelBarra4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout PanelBarra4Layout = new javax.swing.GroupLayout(PanelBarra4);
        PanelBarra4.setLayout(PanelBarra4Layout);
        PanelBarra4Layout.setHorizontalGroup(
            PanelBarra4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 266, Short.MAX_VALUE)
        );
        PanelBarra4Layout.setVerticalGroup(
            PanelBarra4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 306, Short.MAX_VALUE)
        );

        jPanel5.add(PanelBarra4);
        PanelBarra4.setBounds(360, 220, 270, 310);

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel6.setLayout(null);

        tabOnda5.setModel(new javax.swing.table.DefaultTableModel(
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
        tabOnda5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabOnda5MouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tabOnda5);

        jPanel6.add(jScrollPane6);
        jScrollPane6.setBounds(10, 20, 620, 189);

        panelFase5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelFase5.setPreferredSize(new java.awt.Dimension(240, 220));

        javax.swing.GroupLayout panelFase5Layout = new javax.swing.GroupLayout(panelFase5);
        panelFase5.setLayout(panelFase5Layout);
        panelFase5Layout.setHorizontalGroup(
            panelFase5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 334, Short.MAX_VALUE)
        );
        panelFase5Layout.setVerticalGroup(
            panelFase5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 304, Short.MAX_VALUE)
        );

        jPanel6.add(panelFase5);
        panelFase5.setBounds(10, 220, 340, 310);

        jLabel5.setText("Tomada 5");
        jPanel6.add(jLabel5);
        jLabel5.setBounds(10, 0, 60, 17);

        PanelBarra5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout PanelBarra5Layout = new javax.swing.GroupLayout(PanelBarra5);
        PanelBarra5.setLayout(PanelBarra5Layout);
        PanelBarra5Layout.setHorizontalGroup(
            PanelBarra5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 266, Short.MAX_VALUE)
        );
        PanelBarra5Layout.setVerticalGroup(
            PanelBarra5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 306, Short.MAX_VALUE)
        );

        jPanel6.add(PanelBarra5);
        PanelBarra5.setBounds(360, 220, 270, 310);

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel7.setLayout(null);

        tabOnda6.setModel(new javax.swing.table.DefaultTableModel(
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
        tabOnda6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabOnda6MouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tabOnda6);

        jPanel7.add(jScrollPane7);
        jScrollPane7.setBounds(10, 20, 620, 189);

        panelFase6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelFase6.setPreferredSize(new java.awt.Dimension(240, 220));

        javax.swing.GroupLayout panelFase6Layout = new javax.swing.GroupLayout(panelFase6);
        panelFase6.setLayout(panelFase6Layout);
        panelFase6Layout.setHorizontalGroup(
            panelFase6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 334, Short.MAX_VALUE)
        );
        panelFase6Layout.setVerticalGroup(
            panelFase6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 304, Short.MAX_VALUE)
        );

        jPanel7.add(panelFase6);
        panelFase6.setBounds(10, 220, 340, 310);

        jLabel6.setText("Tomada 6");
        jPanel7.add(jLabel6);
        jLabel6.setBounds(10, 0, 60, 17);

        PanelBarra6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout PanelBarra6Layout = new javax.swing.GroupLayout(PanelBarra6);
        PanelBarra6.setLayout(PanelBarra6Layout);
        PanelBarra6Layout.setHorizontalGroup(
            PanelBarra6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 266, Short.MAX_VALUE)
        );
        PanelBarra6Layout.setVerticalGroup(
            PanelBarra6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 306, Short.MAX_VALUE)
        );

        jPanel7.add(PanelBarra6);
        PanelBarra6.setBounds(360, 220, 270, 310);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 635, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 635, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 635, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 635, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 635, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 635, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabOnda1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabOnda1MouseClicked

        Object codOndaOBJ = 0;
        codOndaOBJ = (tabOnda1.getModel().getValueAt(tabOnda1.getSelectedRow(), 3));
        String recebe = String.valueOf(codOndaOBJ);
        codondafase = Integer.parseInt(recebe);

        ChartPanel chartPanel = null;
        try {
            chartPanel = new ChartPanel(createChart1(codondafase));
        } catch (SQLException ex) {
            
        }
        chartPanel.setVisible(true);
        chartPanel.setSize(panelFase1.getWidth(), panelFase1.getHeight());
        panelFase1.removeAll();
        panelFase1.add(chartPanel);
        panelFase1.revalidate();
        panelFase1.repaint();

        ChartPanel chartPanel2 = null;
        try {
            chartPanel2 = new ChartPanel(createChart2(codondafase));
        } catch (SQLException ex) {
            
        }
        chartPanel2.setVisible(true);
        chartPanel2.setSize(PanelBarra1.getWidth(), PanelBarra1.getHeight());
        PanelBarra1.removeAll();
        PanelBarra1.add(chartPanel2);
        PanelBarra1.revalidate();
        PanelBarra1.repaint();
    }//GEN-LAST:event_tabOnda1MouseClicked

    private void tabOnda2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabOnda2MouseClicked
        // TODO add your handling code here:
        Object codOndaOBJ = 0;
        codOndaOBJ = (tabOnda2.getModel().getValueAt(tabOnda2.getSelectedRow(), 3));
        String recebe = String.valueOf(codOndaOBJ);
        codondafase = Integer.parseInt(recebe);

        ChartPanel chartPanel = null;
        try {
            chartPanel = new ChartPanel(createChart1(codondafase));
        } catch (SQLException ex) {
            
        }
        chartPanel.setVisible(true);
        chartPanel.setSize(panelFase2.getWidth(), panelFase2.getHeight());
        panelFase2.removeAll();
        panelFase2.add(chartPanel);
        panelFase2.revalidate();
        panelFase2.repaint();

        ChartPanel chartPanel2 = null;
        try {
            chartPanel2 = new ChartPanel(createChart2(codondafase));
        } catch (SQLException ex) {
            
        }
        chartPanel2.setVisible(true);
        chartPanel2.setSize(PanelBarra2.getWidth(), PanelBarra2.getHeight());
        PanelBarra2.removeAll();
        PanelBarra2.add(chartPanel2);
        PanelBarra2.revalidate();
        PanelBarra2.repaint();
    }//GEN-LAST:event_tabOnda2MouseClicked

    private void tabOnda3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabOnda3MouseClicked
        // TODO add your handling code here:
        Object codOndaOBJ = 0;
        codOndaOBJ = (tabOnda3.getModel().getValueAt(tabOnda3.getSelectedRow(), 3));
        String recebe = String.valueOf(codOndaOBJ);
        codondafase = Integer.parseInt(recebe);

        ChartPanel chartPanel = null;
        try {
            chartPanel = new ChartPanel(createChart1(codondafase));
        } catch (SQLException ex) {
            
        }
        chartPanel.setVisible(true);
        chartPanel.setSize(panelFase3.getWidth(), panelFase3.getHeight());
        panelFase3.removeAll();
        panelFase3.add(chartPanel);
        panelFase3.revalidate();
        panelFase3.repaint();

        ChartPanel chartPanel2 = null;
        try {
            chartPanel2 = new ChartPanel(createChart2(codondafase));
        } catch (SQLException ex) {
            
        }
        chartPanel2.setVisible(true);
        chartPanel2.setSize(PanelBarra3.getWidth(), PanelBarra3.getHeight());
        PanelBarra3.removeAll();
        PanelBarra3.add(chartPanel2);
        PanelBarra3.revalidate();
        PanelBarra3.repaint();

    }//GEN-LAST:event_tabOnda3MouseClicked

    private void tabOnda4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabOnda4MouseClicked
        // TODO add your handling code here:
        Object codOndaOBJ = 0;
        codOndaOBJ = (tabOnda4.getModel().getValueAt(tabOnda4.getSelectedRow(), 3));
        String recebe = String.valueOf(codOndaOBJ);
        codondafase = Integer.parseInt(recebe);

        ChartPanel chartPanel = null;
        try {
            chartPanel = new ChartPanel(createChart1(codondafase));
        } catch (SQLException ex) {
            
        }
        chartPanel.setVisible(true);
        chartPanel.setSize(panelFase4.getWidth(), panelFase4.getHeight());
        panelFase4.removeAll();
        panelFase4.add(chartPanel);
        panelFase4.revalidate();
        panelFase4.repaint();

        ChartPanel chartPanel2 = null;
        try {
            chartPanel2 = new ChartPanel(createChart2(codondafase));
        } catch (SQLException ex) {
            
        }
        chartPanel2.setVisible(true);
        chartPanel2.setSize(PanelBarra4.getWidth(), PanelBarra4.getHeight());
        PanelBarra4.removeAll();
        PanelBarra4.add(chartPanel2);
        PanelBarra4.revalidate();
        PanelBarra4.repaint();
    }//GEN-LAST:event_tabOnda4MouseClicked

    private void tabOnda5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabOnda5MouseClicked
        // TODO add your handling code here:
        Object codOndaOBJ = 0;
        codOndaOBJ = (tabOnda5.getModel().getValueAt(tabOnda5.getSelectedRow(), 3));
        String recebe = String.valueOf(codOndaOBJ);
        codondafase = Integer.parseInt(recebe);

        ChartPanel chartPanel = null;
        try {
            chartPanel = new ChartPanel(createChart1(codondafase));
        } catch (SQLException ex) {
            
        }
        chartPanel.setVisible(true);
        chartPanel.setSize(panelFase5.getWidth(), panelFase5.getHeight());
        panelFase5.removeAll();
        panelFase5.add(chartPanel);
        panelFase5.revalidate();
        panelFase5.repaint();

        ChartPanel chartPanel2 = null;
        try {
            chartPanel2 = new ChartPanel(createChart2(codondafase));
        } catch (SQLException ex) {
            
        }
        chartPanel2.setVisible(true);
        chartPanel2.setSize(PanelBarra5.getWidth(), PanelBarra5.getHeight());
        PanelBarra5.removeAll();
        PanelBarra5.add(chartPanel2);
        PanelBarra5.revalidate();
        PanelBarra5.repaint();

    }//GEN-LAST:event_tabOnda5MouseClicked

    private void tabOnda6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabOnda6MouseClicked
        // TODO add your handling code here:
        Object codOndaOBJ = 0;
        codOndaOBJ = (tabOnda6.getModel().getValueAt(tabOnda6.getSelectedRow(), 3));
        String recebe = String.valueOf(codOndaOBJ);
        codondafase = Integer.parseInt(recebe);

        ChartPanel chartPanel = null;
        try {
            chartPanel = new ChartPanel(createChart1(codondafase));
        } catch (SQLException ex) {
            
        }
        chartPanel.setVisible(true);
        chartPanel.setSize(panelFase6.getWidth(), panelFase6.getHeight());
        panelFase6.removeAll();
        panelFase6.add(chartPanel);
        panelFase6.revalidate();
        panelFase6.repaint();

        ChartPanel chartPanel2 = null;
        try {
            chartPanel2 = new ChartPanel(createChart2(codondafase));
        } catch (SQLException ex) {
            
        }
        chartPanel2.setVisible(true);
        chartPanel2.setSize(PanelBarra6.getWidth(), PanelBarra6.getHeight());
        PanelBarra6.removeAll();
        PanelBarra6.add(chartPanel2);
        PanelBarra6.revalidate();
        PanelBarra6.repaint();
    }//GEN-LAST:event_tabOnda6MouseClicked

    public JFreeChart createChart1(int _codonda) throws SQLException {

        final CategoryDataset dataset1 = createDataset1(_codonda);
        final NumberAxis rangeAxis1 = new NumberAxis("Value");
        rangeAxis1.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis1.setAutoRangeIncludesZero(false);
        //rangeAxis1.setAutoRange(true);
        final LineAndShapeRenderer renderer1 = new LineAndShapeRenderer();
        //renderer1.setBaseItemLabelsVisible(false);
        //renderer1.setBaseShapesFilled(false);
        renderer1.setBaseShapesVisible(false);
        renderer1.setStroke(new BasicStroke(3));
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

    public JFreeChart createChart2(int _codonda) throws SQLException {

        final CategoryDataset dataset2 = createDataset2(_codonda);
        final NumberAxis rangeAxis2 = new NumberAxis("Value");
        rangeAxis2.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        final BarRenderer renderer2 = new BarRenderer();
        renderer2.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
        final CategoryPlot subplot2 = new CategoryPlot(dataset2, null, rangeAxis2, renderer2);
        subplot2.setDomainGridlinesVisible(true);
        final CategoryAxis domainAxis = new CategoryAxis("");
        final CombinedDomainCategoryPlot plot = new CombinedDomainCategoryPlot(domainAxis);

        plot.add(subplot2, 1);

        final JFreeChart result = new JFreeChart(
                "",
                new Font("SansSerif", Font.BOLD, 12),
                plot,
                true);
        //      result.getLegend().setAnchor(Legend.SOUTH);
        return result;
    }

    public CategoryDataset createDataset1(int _codonda) throws SQLException {
        double val[] = new double[256];
        
        int offset = 0;
        double gain = 0;


        this.codonda = _codonda;

        float tempo[] = new float[256];


        DefaultCategoryDataset result = new DefaultCategoryDataset();

        for (int i = 0; i < 256; i++) {
            val[i] = 0;
        }

        for (int i = 0; i < 12; i++) {
            sen[i] = 0;
            cos[i] = 0;
        }

        String sqlAtual = "select cap.valormedio as vm, cap.offset as offset, cap.gain as gain, "
                + "oa.sen as senn, oa.coss as cos, oa.codharmonica as cod "
                + "from capturaondaatual cap, ondaatual oa "
                + "where cap.codondaatual = ? "
                + "and cap.codondaatual = oa.codcapturaatual;";

        // row keys...
        final String series1 = "Atual";


        PreparedStatement ps = Conexao.getPreparedStatement(sqlAtual);

        System.out.println("codOndA " + _codonda);

        ps.setInt(1, codonda);

        ResultSet rs = ps.executeQuery();

        try {
            while (rs.next()) {
                codharmonica = rs.getInt("cod");
                cos[codharmonica] = rs.getDouble("cos");
                sen[codharmonica] = rs.getDouble("senn");
                gain = rs.getDouble("gain");
                offset = rs.getInt("offset");
                vm = rs.getDouble("vm");


            }
        } catch (Exception e) {
            System.out.println("Erro generico: " + e.getMessage());
            e.printStackTrace();
        }

        float s, t = 0;
        for (int i = 0; i < 256; i++) {
            if (i == 0) {
                tempo[i] = t = 0;
            } else {
                tempo[i] = (t + (float) (1.0 / (60 * 256)));
            }
            type[i] = String.valueOf(i*60);
            //System.out.println("num "+sen[0]+" seno "+Math.sin(sen[0]));
            //System.out.printf("%f\n ", tempo[i]);
            s = (float) (vm / 2);

            for (int x = 0; x < 12; x++) {
                s += (double) (cos[x] * Math.cos(2 * Math.PI * ((x + 1) * 60) * tempo[i])) + (sen[x] * Math.sin(-2 * Math.PI * ((x + 1) * 60) * tempo[i]));

            }
            val[i] = (int) ((s * (2.0)) / 256.0);
            val[i] = ((val[i] - offset) / gain);
            t = tempo[i];
        }

        for (int i = 0; i < 256; i++) {
            result.addValue(val[i], series1, type[i]);
            //System.out.println("val "+val[i]+ "type "+type[i]);
        }

        return result;
    }

    public CategoryDataset createDataset2(int _codonda) throws SQLException {
        final DefaultCategoryDataset result = new DefaultCategoryDataset();

        // row keys...
        final String series1 = "Sen Onda Atual";
        final String series2 = "Cos Onda Atual";

        double val[] = new double[13];

//        String sql = "select sen as seno, coss as cosseno, codharmonica as cod "
//                + "from ondaatual "
//                + "where codcapturaatual = ?";
//
//        PreparedStatement ps = Conexao.getPreparedStatement(sql);
//        ps.setInt(1, _codonda);
//
//        ResultSet rs = ps.executeQuery();
//        while (rs.next()) {
//            codharmonica = rs.getInt("cod");
//            sen[codharmonica] = rs.getDouble("seno");
//            cos[codharmonica] = rs.getDouble("cosseno");
//        }

        val[0] = vm/256;
        for (int i = 0; i < 12; i++) {

            val[i+1] = Math.pow(sen[i], 2)+Math.pow(cos[i], 2);
            val[i+1] = Math.sqrt(val[i+1]);
            val[i+1] = val[i+1]/256;

            result.addValue(val[i], series1, type[i]);
            //result.addValue(cos[i], series2, type[i]);
            //System.out.println("sen "+sen[i] + " senp "+senp[i]);

        }

        return result;

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    new FormMostraOndas().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(FormMostraOndas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelBarra1;
    private javax.swing.JPanel PanelBarra2;
    private javax.swing.JPanel PanelBarra3;
    private javax.swing.JPanel PanelBarra4;
    private javax.swing.JPanel PanelBarra5;
    private javax.swing.JPanel PanelBarra6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JPanel panelFase1;
    private javax.swing.JPanel panelFase2;
    private javax.swing.JPanel panelFase3;
    private javax.swing.JPanel panelFase4;
    private javax.swing.JPanel panelFase5;
    private javax.swing.JPanel panelFase6;
    private javax.swing.JTable tabOnda1;
    private javax.swing.JTable tabOnda2;
    private javax.swing.JTable tabOnda3;
    private javax.swing.JTable tabOnda4;
    private javax.swing.JTable tabOnda5;
    private javax.swing.JTable tabOnda6;
    // End of variables declaration//GEN-END:variables
}
