package Visualizacao;

import Uteis.Configuracoes;
import Graficos.CriaGrafico;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import modelo.Formularios.BeanSupervisao;
import modelo.CapturaAtual;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import persistencia.CapturaAtualDAO;
import persistencia.UsoSalaExtrasDAO;

public class FormPSupervisao extends javax.swing.JDialog {
    private String mensagem;
    
    CapturaAtualDAO capdao = new CapturaAtualDAO();
    UsoSalaExtrasDAO extradao = new UsoSalaExtrasDAO();
    Vector titulos = new Vector();
    Vector linhas = new Vector();
    
    DefaultTableModel model = new DefaultTableModel(linhas, titulos);
    
    int          tomada[] = new int[Configuracoes.MAXTOMADAS];
    CapturaAtual ondas[]  = new CapturaAtual[Configuracoes.MAXTOMADAS];
    int          codondas[] = new int[Configuracoes.MAXTOMADAS];               
    private static int sala = 1;       
    private static int usosala;

    /** Creates new form FormPSupervisaoX */
    public FormPSupervisao(java.awt.Frame parent, boolean modal, int _sala) {
        super(parent, modal);
        
        sala = _sala;
        this.setTitle("Sala " + String.valueOf(sala));
        System.out.println("sala" + sala);
        usosala = extradao.ultimoUsoSalaAtivo(sala);
        initComponents();

        add(new JScrollPane(this.jPanel1));
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        // adiciono a resolu��o da tela
        this.setSize(dimension.width, (dimension.height - 40));
        
        /* adiciona os titulos da tabela de equipamentos */
        titulos.add("Captura");
        titulos.add("Tomada");
        titulos.add("Cod.Equip");
        titulos.add("Equipamento");
        titulos.add("RFID");
        titulos.add("Status");
        titulos.add("Tempo de uso");   
        
        atualizaTabela();
        
        /* thread para atualizar a tabela de equipamentos numa sala */
        new Thread() {

            public void run() {
                while (true) {
                    atualizaTabela();
                    try {
                        Thread.sleep(Configuracoes.TEMPOATUALIZATABELA);
                    } catch (Exception e) {
                        System.out.println("Erro na Thread: " + e.getMessage());
                    }
                }
            }
        }.start();
    }
    
    private void atualizaTabela(){
        int i=0;
        
        tabela.removeAll(); /* limpa a tabela */
        linhas.removeAllElements();
        for (BeanSupervisao bean : extradao.listaUltimasFO(extradao.listaEquipUsoSala(usosala), "cap")){
        //for(BeanSupervisao bean : extradao.listaUltimasFO(sala)){ muito lento
            Vector colunas = new Vector();
            
            colunas.add(bean.getCodCaptura().getCodCaptura());
            colunas.add(bean.getNumTomada());
            colunas.add(bean.getCodEquip().getCodEquip());
            colunas.add(bean.getCodEquip().getDesc());
            colunas.add(bean.getCodEquip().getRfid());
            colunas.add(bean.getCodEvento().getDesc());
            colunas.add(bean.getCodEquip().getTempoUso());
            
            linhas.add(colunas);
          
            ondas[i]    = bean.getCodCaptura();
            tomada[i++] = bean.getNumTomada();
        }

        tabela.setModel(model);
        //tabela.setRowSelectionInterval(0, 0) ;
        
        // System.out.println("Vai montar os graficos");
        MontaGrafico();
    }
    
    public void MontaGrafico()  {
        int i=0; // [0]
        if (tomada[i] != i) /* primeiro espaço de grafico */
            if (ondas[i].getCodCaptura() != codondas[i]){ /* Mudou a FO ou o Equipamento  */                
                atualizaGrafico(i, jpeq01, jltomada01, jbAtualizaEq01);
                codondas[i] = ondas[i].getCodCaptura();
            }
        i++; // [1]
        if (tomada[i] != 0) /* segundo espaço de grafico */
            if (ondas[i].getCodCaptura() != codondas[i]){ /* Mudou a FO ou o Equipamento  */                
                atualizaGrafico(i, jpeq02, jltomada02, jbAtualizaEq02);
                codondas[i] = ondas[i].getCodCaptura();
            }        
        i++; // [2]
        if (tomada[i] != 0) /* terceiro espaço de grafico */
            if (ondas[i].getCodCaptura() != codondas[i]){ /* Mudou a FO ou o Equipamento  */                
                atualizaGrafico(i, jpeq03, jltomada03, jbAtualizaEq03);
                codondas[i] = ondas[i].getCodCaptura();
            }        
        i++; // [3]
        if (tomada[i] != 0) 
            if (ondas[i].getCodCaptura() != codondas[i]){ /* Mudou a FO ou o Equipamento  */                
                atualizaGrafico(i, jpeq04, jltomada04, jbAtualizaEq04);
                codondas[i] = ondas[i].getCodCaptura();
            }        
        i++; // [4]
        if (tomada[i] != 0) 
            if (ondas[i].getCodCaptura() != codondas[i]){ /* Mudou a FO ou o Equipamento  */                
                atualizaGrafico(i, jpeq05, jltomada05, jbAtualizaEq05);
                codondas[i] = ondas[i].getCodCaptura();
            }        
        i++; // [5]
        if (tomada[i] != 0) 
            if (ondas[i].getCodCaptura() != codondas[i]){ /* Mudou a FO ou o Equipamento  */                
                atualizaGrafico(i, jpeq06, jltomada06, jbAtualizaEq06);
                codondas[i] = ondas[i].getCodCaptura();
            }        
        i++; // [6]
        if (tomada[i] != 0) 
            if (ondas[i].getCodCaptura() != codondas[i]){ /* Mudou a FO ou o Equipamento  */                
                atualizaGrafico(i, jpeq07, jltomada07, jbAtualizaEq07);
                codondas[i] = ondas[i].getCodCaptura();
            }        
        i++; // [7]
        if (tomada[i] != 0) 
            if (ondas[i].getCodCaptura() != codondas[i]){ /* Mudou a FO ou o Equipamento  */                
                atualizaGrafico(i, jpeq08, jltomada08, jbAtualizaEq08);
                codondas[i] = ondas[i].getCodCaptura();
            }        
        i++; // [8]
        if (tomada[i] != 0) 
            if (ondas[i].getCodCaptura() != codondas[i]){ /* Mudou a FO ou o Equipamento  */                
                atualizaGrafico(i, jpeq09, jltomada09, jbAtualizaEq09);
                codondas[i] = ondas[i].getCodCaptura();
            }        
        i++; // [9]
        if (tomada[i] != 0) 
            if (ondas[i].getCodCaptura() != codondas[i]){ /* Mudou a FO ou o Equipamento  */                
                atualizaGrafico(i, jpeq10, jltomada10, jbAtualizaEq10);
                codondas[i] = ondas[i].getCodCaptura();
            }        
        i++; // [10]
        if (tomada[i] != 0) 
            if (ondas[i].getCodCaptura() != codondas[i]){ /* Mudou a FO ou o Equipamento  */                
                atualizaGrafico(i, jpeq11, jltomada11, jbAtualizaEq11);
                codondas[i] = ondas[i].getCodCaptura();
            }        
        i++; // [11]
        if (tomada[i] != 0) 
            if (ondas[i].getCodCaptura() != codondas[i]){ /* Mudou a FO ou o Equipamento  */                
                atualizaGrafico(i, jpeq12, jltomada12, jbAtualizaEq12);
                codondas[i] = ondas[i].getCodCaptura();
            }        
    }
    
    private void atualizaGrafico(int indice, JPanel panel, JLabel label, JButton btnDetalhes){                
        final JFreeChart chart = CriaGrafico.createChartLinhas(ondas[indice], false);
        final ChartPanel crtPanelOnda = new ChartPanel(chart);
        crtPanelOnda.setPreferredSize(new java.awt.Dimension(295, 260));
                
        panel.removeAll();        
        panel.add(crtPanelOnda);
        panel.revalidate();
        panel.repaint();
        
        label.setText("Tomada: " + Integer.toString(tomada[indice]));
        
        btnDetalhes.setEnabled(true);
    }      
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        jpeq02 = new javax.swing.JPanel();
        jpeq03 = new javax.swing.JPanel();
        jltomada03 = new javax.swing.JLabel();
        jltomada02 = new javax.swing.JLabel();
        jbAtualizaEq02 = new javax.swing.JButton();
        jbAtualizaEq03 = new javax.swing.JButton();
        jpeq01 = new javax.swing.JPanel();
        jbAtualizaEq01 = new javax.swing.JButton();
        jltomada01 = new javax.swing.JLabel();
        jpeq04 = new javax.swing.JPanel();
        jltomada04 = new javax.swing.JLabel();
        jbAtualizaEq04 = new javax.swing.JButton();
        jbAtualizaEq05 = new javax.swing.JButton();
        jpeq05 = new javax.swing.JPanel();
        jltomada05 = new javax.swing.JLabel();
        jltomada06 = new javax.swing.JLabel();
        jpeq06 = new javax.swing.JPanel();
        jbAtualizaEq06 = new javax.swing.JButton();
        jpeq07 = new javax.swing.JPanel();
        jbAtualizaEq07 = new javax.swing.JButton();
        jbAtualizaEq08 = new javax.swing.JButton();
        jpeq08 = new javax.swing.JPanel();
        jpeq09 = new javax.swing.JPanel();
        jbAtualizaEq09 = new javax.swing.JButton();
        jpeq10 = new javax.swing.JPanel();
        jbAtualizaEq10 = new javax.swing.JButton();
        jpeq11 = new javax.swing.JPanel();
        jbAtualizaEq11 = new javax.swing.JButton();
        jpeq12 = new javax.swing.JPanel();
        jbAtualizaEq12 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jltomada07 = new javax.swing.JLabel();
        jltomada08 = new javax.swing.JLabel();
        jltomada09 = new javax.swing.JLabel();
        jltomada10 = new javax.swing.JLabel();
        jltomada11 = new javax.swing.JLabel();
        jltomada12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabela.setAutoCreateRowSorter(true);
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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, 1420, 197));

        jpeq02.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jpeq02.setToolTipText("Equipamento 2");
        jpeq02.setPreferredSize(new java.awt.Dimension(250, 250));
        jPanel1.add(jpeq02, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 250, 300, 260));

        jpeq03.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jpeq03.setToolTipText("");
        jpeq03.setPreferredSize(new java.awt.Dimension(250, 250));
        jPanel1.add(jpeq03, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 250, 300, 260));

        jltomada03.setText("TOMADA");
        jPanel1.add(jltomada03, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 230, -1, -1));

        jltomada02.setText("TOMADA");
        jPanel1.add(jltomada02, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 230, -1, -1));

        jbAtualizaEq02.setText("Atualiza");
        jbAtualizaEq02.setEnabled(false);
        jbAtualizaEq02.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAtualizaEq02ActionPerformed(evt);
            }
        });
        jPanel1.add(jbAtualizaEq02, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 520, -1, -1));

        jbAtualizaEq03.setText("Atualiza");
        jbAtualizaEq03.setEnabled(false);
        jbAtualizaEq03.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAtualizaEq03ActionPerformed(evt);
            }
        });
        jPanel1.add(jbAtualizaEq03, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 520, -1, -1));

        jpeq01.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jpeq01.setToolTipText("");
        jpeq01.setPreferredSize(new java.awt.Dimension(250, 250));
        jPanel1.add(jpeq01, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 300, 260));

        jbAtualizaEq01.setText("Atualiza");
        jbAtualizaEq01.setEnabled(false);
        jbAtualizaEq01.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAtualizaEq01ActionPerformed(evt);
            }
        });
        jPanel1.add(jbAtualizaEq01, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 520, -1, -1));

        jltomada01.setText("TOMADA");
        jPanel1.add(jltomada01, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, -1));

        jpeq04.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jpeq04.setPreferredSize(new java.awt.Dimension(250, 250));
        jPanel1.add(jpeq04, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 250, 300, 260));

        jltomada04.setText("TOMADA");
        jPanel1.add(jltomada04, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 230, -1, 20));

        jbAtualizaEq04.setText("Atualiza");
        jbAtualizaEq04.setEnabled(false);
        jbAtualizaEq04.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAtualizaEq04ActionPerformed(evt);
            }
        });
        jPanel1.add(jbAtualizaEq04, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 520, -1, -1));

        jbAtualizaEq05.setText("Atualiza");
        jbAtualizaEq05.setEnabled(false);
        jbAtualizaEq05.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAtualizaEq05ActionPerformed(evt);
            }
        });
        jPanel1.add(jbAtualizaEq05, new org.netbeans.lib.awtextra.AbsoluteConstraints(1330, 520, -1, -1));

        jpeq05.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jpeq05.setPreferredSize(new java.awt.Dimension(250, 250));
        jPanel1.add(jpeq05, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 250, 300, 260));

        jltomada05.setText("TOMADA");
        jPanel1.add(jltomada05, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 230, -1, -1));

        jltomada06.setText("TOMADA");
        jPanel1.add(jltomada06, new org.netbeans.lib.awtextra.AbsoluteConstraints(1500, 230, -1, -1));

        jpeq06.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jpeq06.setPreferredSize(new java.awt.Dimension(250, 250));
        jPanel1.add(jpeq06, new org.netbeans.lib.awtextra.AbsoluteConstraints(1510, 250, 300, 260));

        jbAtualizaEq06.setText("Atualiza");
        jbAtualizaEq06.setEnabled(false);
        jbAtualizaEq06.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAtualizaEq06ActionPerformed(evt);
            }
        });
        jPanel1.add(jbAtualizaEq06, new org.netbeans.lib.awtextra.AbsoluteConstraints(1610, 520, -1, -1));

        jpeq07.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jpeq07.setPreferredSize(new java.awt.Dimension(250, 250));
        jPanel1.add(jpeq07, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 610, 300, 260));

        jbAtualizaEq07.setText("Atualiza");
        jbAtualizaEq07.setEnabled(false);
        jbAtualizaEq07.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAtualizaEq07ActionPerformed(evt);
            }
        });
        jPanel1.add(jbAtualizaEq07, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 880, -1, -1));

        jbAtualizaEq08.setText("Atualiza");
        jbAtualizaEq08.setEnabled(false);
        jbAtualizaEq08.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAtualizaEq08ActionPerformed(evt);
            }
        });
        jPanel1.add(jbAtualizaEq08, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 880, -1, -1));

        jpeq08.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jpeq08.setPreferredSize(new java.awt.Dimension(250, 250));
        jPanel1.add(jpeq08, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 610, 300, 260));

        jpeq09.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jpeq09.setPreferredSize(new java.awt.Dimension(250, 250));
        jPanel1.add(jpeq09, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 610, 300, 260));

        jbAtualizaEq09.setText("Atualiza");
        jbAtualizaEq09.setEnabled(false);
        jbAtualizaEq09.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAtualizaEq09ActionPerformed(evt);
            }
        });
        jPanel1.add(jbAtualizaEq09, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 880, -1, -1));

        jpeq10.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jpeq10.setPreferredSize(new java.awt.Dimension(250, 250));
        jPanel1.add(jpeq10, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 610, 300, 260));

        jbAtualizaEq10.setText("Atualiza");
        jbAtualizaEq10.setEnabled(false);
        jbAtualizaEq10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAtualizaEq10ActionPerformed(evt);
            }
        });
        jPanel1.add(jbAtualizaEq10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 880, -1, -1));

        jpeq11.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jpeq11.setPreferredSize(new java.awt.Dimension(250, 250));
        jPanel1.add(jpeq11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 610, 300, 260));

        jbAtualizaEq11.setText("Atualiza");
        jbAtualizaEq11.setEnabled(false);
        jbAtualizaEq11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAtualizaEq11ActionPerformed(evt);
            }
        });
        jPanel1.add(jbAtualizaEq11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1350, 880, -1, -1));

        jpeq12.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jpeq12.setPreferredSize(new java.awt.Dimension(250, 250));
        jPanel1.add(jpeq12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1510, 610, 300, 260));

        jbAtualizaEq12.setText("Atualiza");
        jbAtualizaEq12.setEnabled(false);
        jbAtualizaEq12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAtualizaEq12ActionPerformed(evt);
            }
        });
        jPanel1.add(jbAtualizaEq12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1630, 880, -1, -1));

        jButton1.setText("Seleciona");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1470, 80, 111, 68));

        jltomada07.setText("TOMADA");
        jPanel1.add(jltomada07, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 590, -1, -1));

        jltomada08.setText("TOMADA");
        jPanel1.add(jltomada08, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 590, -1, -1));

        jltomada09.setText("TOMADA");
        jPanel1.add(jltomada09, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 590, -1, -1));

        jltomada10.setText("TOMADA");
        jPanel1.add(jltomada10, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 590, -1, -1));

        jltomada11.setText("TOMADA");
        jPanel1.add(jltomada11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 590, -1, -1));

        jltomada12.setText("TOMADA");
        jPanel1.add(jltomada12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1520, 590, -1, -1));

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int cod = (Integer) tabela.getModel().getValueAt(tabela.getSelectedRow(), 0);
        CapturaAtual cap = capdao.localiza(cod);
                
        FormPComparacao painel = new FormPComparacao(new JFrame(), true, cap);
        painel.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jbAtualizaEq01ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAtualizaEq01ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Ainda não implementado ...");
    }//GEN-LAST:event_jbAtualizaEq01ActionPerformed

    private void jbAtualizaEq02ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAtualizaEq02ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Ainda não implementado ...");
    }//GEN-LAST:event_jbAtualizaEq02ActionPerformed

    private void jbAtualizaEq03ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAtualizaEq03ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Ainda não implementado ...");
    }//GEN-LAST:event_jbAtualizaEq03ActionPerformed

    private void jbAtualizaEq04ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAtualizaEq04ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Ainda não implementado ...");
    }//GEN-LAST:event_jbAtualizaEq04ActionPerformed

    private void jbAtualizaEq05ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAtualizaEq05ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Ainda não implementado ...");
    }//GEN-LAST:event_jbAtualizaEq05ActionPerformed

    private void jbAtualizaEq07ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAtualizaEq07ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Ainda não implementado ...");
    }//GEN-LAST:event_jbAtualizaEq07ActionPerformed

    private void jbAtualizaEq08ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAtualizaEq08ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Ainda não implementado ...");
    }//GEN-LAST:event_jbAtualizaEq08ActionPerformed

    private void jbAtualizaEq09ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAtualizaEq09ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Ainda não implementado ...");
    }//GEN-LAST:event_jbAtualizaEq09ActionPerformed

    private void jbAtualizaEq10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAtualizaEq10ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Ainda não implementado ...");
    }//GEN-LAST:event_jbAtualizaEq10ActionPerformed

    private void jbAtualizaEq11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAtualizaEq11ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Ainda não implementado ...");
    }//GEN-LAST:event_jbAtualizaEq11ActionPerformed

    private void jbAtualizaEq06ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAtualizaEq06ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Ainda não implementado ...");
    }//GEN-LAST:event_jbAtualizaEq06ActionPerformed

    private void jbAtualizaEq12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAtualizaEq12ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Ainda não implementado ...");
    }//GEN-LAST:event_jbAtualizaEq12ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                FormPSupervisao dialog = null;
                try {
                    dialog = new FormPSupervisao(new javax.swing.JFrame(), true, sala);
                    //dialog = new FormPSupervisao(new javax.swing.JFrame(), true, 1);
                } catch (Exception ex) {
                    Logger.getLogger(FormPSupervisao.class.getName()).log(Level.SEVERE, null, ex);
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbAtualizaEq01;
    private javax.swing.JButton jbAtualizaEq02;
    private javax.swing.JButton jbAtualizaEq03;
    private javax.swing.JButton jbAtualizaEq04;
    private javax.swing.JButton jbAtualizaEq05;
    private javax.swing.JButton jbAtualizaEq06;
    private javax.swing.JButton jbAtualizaEq07;
    private javax.swing.JButton jbAtualizaEq08;
    private javax.swing.JButton jbAtualizaEq09;
    private javax.swing.JButton jbAtualizaEq10;
    private javax.swing.JButton jbAtualizaEq11;
    private javax.swing.JButton jbAtualizaEq12;
    private javax.swing.JLabel jltomada01;
    private javax.swing.JLabel jltomada02;
    private javax.swing.JLabel jltomada03;
    private javax.swing.JLabel jltomada04;
    private javax.swing.JLabel jltomada05;
    private javax.swing.JLabel jltomada06;
    private javax.swing.JLabel jltomada07;
    private javax.swing.JLabel jltomada08;
    private javax.swing.JLabel jltomada09;
    private javax.swing.JLabel jltomada10;
    private javax.swing.JLabel jltomada11;
    private javax.swing.JLabel jltomada12;
    private javax.swing.JPanel jpeq01;
    private javax.swing.JPanel jpeq02;
    private javax.swing.JPanel jpeq03;
    private javax.swing.JPanel jpeq04;
    private javax.swing.JPanel jpeq05;
    private javax.swing.JPanel jpeq06;
    private javax.swing.JPanel jpeq07;
    private javax.swing.JPanel jpeq08;
    private javax.swing.JPanel jpeq09;
    private javax.swing.JPanel jpeq10;
    private javax.swing.JPanel jpeq11;
    private javax.swing.JPanel jpeq12;
    private javax.swing.JTable tabela;
    // End of variables declaration//GEN-END:variables
}
