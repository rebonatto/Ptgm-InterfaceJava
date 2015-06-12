package Visualizacao;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FormMostraUsoSala
 *
 * Created on 30/07/2012, 16:10:10
 */

import Uteis.Configuracoes;
import Graficos.CriaGrafico;
import Uteis.Conversoes;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Collection;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.CapturaAtual;
import modelo.UsoSala;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import persistencia.CapturaAtualDAO;
import persistencia.UsoSalaDAO;
import persistencia.UsoSalaExtrasDAO;

/**
 *
 * @author rebonatto
 */
public class FormMostraUsoSala extends javax.swing.JDialog {
    private UsoSalaDAO dao = new UsoSalaDAO();
    private UsoSalaExtrasDAO extradao = new UsoSalaExtrasDAO();
    private CapturaAtualDAO capdao = new CapturaAtualDAO();
    private UsoSala bean   = new UsoSala();
    private Collection <CapturaAtual> lista;
    
    int tomada[] = new int[Configuracoes.MAXTOMADAS];

    Vector titUso = new Vector();
    Vector linUso = new Vector();
    
    Vector titOnda = new Vector();
    Vector linOnda01 = new Vector();
    Vector linOnda02 = new Vector();
    Vector linOnda03 = new Vector();
    Vector linOnda04 = new Vector();
    Vector linOnda05 = new Vector();
    Vector linOnda06 = new Vector();
    Vector linOnda07 = new Vector();
    Vector linOnda08 = new Vector();
    Vector linOnda09 = new Vector();
    Vector linOnda10 = new Vector();
    Vector linOnda11 = new Vector();
    Vector linOnda12 = new Vector();
    
//    DefaultTableModel modelOnda = new DefaultTableModel(linOnda, titOnda);
    
    DefaultTableModel modelUso = new DefaultTableModel(linUso, titUso);
    
    /** Creates new form FormMostraOndas */
    public FormMostraUsoSala(java.awt.Frame parent, boolean modal) {        
        super(parent, modal);
        initComponents();
        //iniciaTabela();
        this.setTitle("UsoSalas");

      //  add(new JScrollPane(this.jPanel1));
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        
        /* adiciona os titulos da tabela de equipamentos */
        titUso.add("CodUsoSala");
        titUso.add("Sala");
        titUso.add("Procedimento");
        titUso.add("Responsavel");        
        titUso.add("Inicio");
        titUso.add("Fim");
        
        
        titOnda.add("Captura");
        titOnda.add("Equipamento");
        titOnda.add("Hora");
        titOnda.add("Evento");
        titOnda.add("Eficaz");                
        
        atuTabelaUso();
        // adiciono a resolu��o da tela                
        this.setSize(dimension.width, (dimension.height ));
        
        System.out.println(dimension.width);
        System.out.println(dimension.height);
        
        preparaTabelas();
        
        tabUsoSala.setEnabledAt(1, false);
        tabUsoSala.setEnabledAt(2, false);       
    }
        
    private void atuTabelaUso(){
        int i=0;
        
        tabelaUsoSala.removeAll(); /* limpa a tabela */
        linUso.removeAllElements();
        for(UsoSala bean : dao.Lista()){
            Vector colunas = new Vector();
            
            colunas.add(bean.getCodUsoSala());
            colunas.add(bean.getCodSala().getCodSala());
            colunas.add(bean.getCodProced().getCodProced());
            colunas.add(bean.getCodResp().getDesc());
            colunas.add(Conversoes.CalendarToTimeStamp(bean.getHoraInicio()));
            if (bean.getHoraFinal() == null)
                colunas.add(new String(""));
            else
                colunas.add(Conversoes.CalendarToTimeStamp(bean.getHoraFinal()));            
            
            linUso.add(colunas);          
        }

        tabelaUsoSala.setModel(modelUso);    
        tabelaUsoSala.setRowSelectionInterval(0, 0);
    }
    
    private void limpaTabelas(JTable tabela, Vector linhas){
        tabela.removeAll(); /* limpa a tabela */
        linhas.removeAllElements();
    }
    
    private void formataTabelas(JTable tabela, Vector linhas, int indice, JLabel label, JPanel panelOnda, JPanel panelHarmonicas ){
        
        label.setText("Tomada " + tomada[indice]);                    
                
        tabela.setModel(new DefaultTableModel(linhas, titOnda));
        tabela.setRowSelectionInterval(0, 0) ;
        
        /* monta os graficos */        
        int linha = tabela.getSelectedRow();
       
        CapturaAtual cap = capdao.localiza((Integer) tabela.getValueAt(linha, 0));
        
        atualizaGraficos(cap, panelOnda, panelHarmonicas);        
    }
    
    private void atualizaGraficos(CapturaAtual cap, JPanel panelOnda, JPanel panelHarmonicas){                
        final JFreeChart chart = CriaGrafico.createChartLinhas(cap, false);
        final ChartPanel crtPanelOnda = new ChartPanel(chart);
        crtPanelOnda.setPreferredSize(new java.awt.Dimension(295, 260));
        
//        crtPanelOnda.setVisible(true);
//        crtPanelOnda.setSize(panelOnda.getWidth(), panelOnda.getHeight());
                
        panelOnda.removeAll();
        panelOnda.add(crtPanelOnda);
        panelOnda.revalidate();
        panelOnda.repaint();
                        
        // monta grafico de barras
        ChartPanel crtPanelBarras = new ChartPanel(CriaGrafico.createChartBarras(cap, false, false));
        crtPanelBarras.setPreferredSize(new java.awt.Dimension(295, 260));
        
        //crtPanelBarras.setVisible(true);
        //crtPanelBarras.setSize(panelHarmonicas.getWidth(), panelHarmonicas.getHeight());
        panelHarmonicas.removeAll();
        panelHarmonicas.add(crtPanelBarras);
        panelHarmonicas.revalidate();
        panelHarmonicas.repaint();
    }
    
    private void preparaTabelas(){
        limpaTabelas(tabOnda01, linOnda01);
        limpaTabelas(tabOnda02, linOnda02);
        limpaTabelas(tabOnda03, linOnda03);
        limpaTabelas(tabOnda04, linOnda04);
        limpaTabelas(tabOnda05, linOnda05);
        limpaTabelas(tabOnda06, linOnda06);
        limpaTabelas(tabOnda07, linOnda07);
        limpaTabelas(tabOnda08, linOnda08);
        limpaTabelas(tabOnda09, linOnda09);
        limpaTabelas(tabOnda10, linOnda10);
        limpaTabelas(tabOnda11, linOnda11);
        limpaTabelas(tabOnda12, linOnda12);
    }
               
    private void atualizaTabelas(CapturaAtual cap, Vector linha){
        Vector colunas = new Vector();            
        
        colunas.add(cap.getCodCaptura());
        colunas.add(cap.getCodEquip().getDesc());
        colunas.add(Conversoes.CalendarToTimeStamp(cap.getDataAtual()));
        colunas.add(cap.getCodEvento().getDesc());
        colunas.add(cap.getEficaz());

        linha.add(colunas);
    }
    
    private void atualizaPanel(CapturaAtual cap, int indice){

        switch (indice){
            case  0: atualizaTabelas(cap, linOnda01); break;
            case  1: atualizaTabelas(cap, linOnda02); break;
            case  2: atualizaTabelas(cap, linOnda03); break;    
            case  3: atualizaTabelas(cap, linOnda04); break;
            case  4: atualizaTabelas(cap, linOnda05); break;
            case  5: atualizaTabelas(cap, linOnda06); break;
            case  6: atualizaTabelas(cap, linOnda07); break;
            case  7: atualizaTabelas(cap, linOnda08); break;
            case  8: atualizaTabelas(cap, linOnda09); break;    
            case  9: atualizaTabelas(cap, linOnda10); break;
            case 10: atualizaTabelas(cap, linOnda11); break;
            case 11: atualizaTabelas(cap, linOnda12); break;
            default : JOptionPane.showMessageDialog(null, "Excedido numero de tomadas");
        }                 
    }
    
    private void mostraTabelas(int indice){
        switch (indice){
            case  0: formataTabelas(tabOnda01, linOnda01, indice, labelTomada01, panelOnda01, PanelBarra01); break;
            case  1: formataTabelas(tabOnda02, linOnda02, indice, labelTomada02, panelOnda02, PanelBarra02); break;
            case  2: formataTabelas(tabOnda03, linOnda03, indice, labelTomada03, panelOnda03, PanelBarra03); break;    
            case  3: formataTabelas(tabOnda04, linOnda04, indice, labelTomada04, panelOnda04, PanelBarra04); break;
            case  4: formataTabelas(tabOnda05, linOnda05, indice, labelTomada05, panelOnda05, PanelBarra05); break;
            case  5: formataTabelas(tabOnda06, linOnda06, indice, labelTomada06, panelOnda06, PanelBarra06); break;
            case  6: formataTabelas(tabOnda07, linOnda07, indice, labelTomada07, panelOnda07, PanelBarra07); break;
            case  7: formataTabelas(tabOnda08, linOnda08, indice, labelTomada08, panelOnda08, PanelBarra08); break;
            case  8: formataTabelas(tabOnda09, linOnda09, indice, labelTomada09, panelOnda09, PanelBarra09); break;    
            case  9: formataTabelas(tabOnda10, linOnda10, indice, labelTomada10, panelOnda10, PanelBarra10); break;
            case 10: formataTabelas(tabOnda11, linOnda11, indice, labelTomada11, panelOnda11, PanelBarra11); break;
            case 11: formataTabelas(tabOnda12, linOnda12, indice, labelTomada12, panelOnda12, PanelBarra12); break;
            default : JOptionPane.showMessageDialog(null, "Excedido numero de tomadas");
        }
    }
        
    private void atualizaOndas(){
        int i = 0, atual = 0, tom;
                
        /* limpa o vetor de tomadas */
        for(i = 0; i < Configuracoes.MAXTOMADAS; i++)
            tomada[i] = 0;
        
        i=0;
        /* atualiza o vetor de tomadas e a interface grafica */
        for(CapturaAtual cap : lista){
            tom = cap.getCodTomada().getCodTomada();
            if ( (tom != tomada[i]) && (atual != tom) ){
                tomada[i] = tom;
                atual = tom ;                                
                i++;
            }   
            atualizaPanel(cap, i-1);
        }             
        
        for(i=0; i < Configuracoes.MAXTOMADAS; i++){
            if (tomada[i] != 0){
               mostraTabelas(i);
               if (i == 0)
                   tabUsoSala.setEnabledAt(1, true);
               if (i > 6)                           
                  tabUsoSala.setEnabledAt(2, true);
            }
            else                
                switch (i){
                    case  0: limpaPanel(labelTomada01, panelOnda01, PanelBarra01); break;
                    case  1: limpaPanel(labelTomada02, panelOnda02, PanelBarra02); break;
                    case  2: limpaPanel(labelTomada03, panelOnda03, PanelBarra03); break;
                    case  3: limpaPanel(labelTomada04, panelOnda04, PanelBarra04); break;
                    case  4: limpaPanel(labelTomada05, panelOnda05, PanelBarra05); break;
                    case  5: limpaPanel(labelTomada06, panelOnda06, PanelBarra06); break;
                    case  6: limpaPanel(labelTomada07, panelOnda07, PanelBarra07); break;
                    case  7: limpaPanel(labelTomada08, panelOnda08, PanelBarra08); break;
                    case  8: limpaPanel(labelTomada09, panelOnda09, PanelBarra09); break;
                    case  9: limpaPanel(labelTomada10, panelOnda10, PanelBarra10); break;
                    case 10: limpaPanel(labelTomada11, panelOnda11, PanelBarra11); break;
                    case 11: limpaPanel(labelTomada12, panelOnda12, PanelBarra12); break;                        
                }                                                                                               
        }   
    }

    private void limpaPanel(JLabel label, JPanel onda, JPanel barra){
        label.setText("TOMADA");
        onda.removeAll();
        onda.repaint();
        barra.removeAll();
        barra.repaint();                
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
        tabUsoSala = new javax.swing.JTabbedPane();
        panelUsoSala = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaUsoSala = new javax.swing.JTable();
        btnSel = new javax.swing.JButton();
        panelOndas1 = new javax.swing.JPanel();
        panelOnda01 = new javax.swing.JPanel();
        labelTomada01 = new javax.swing.JLabel();
        PanelBarra01 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabOnda01 = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        tabOnda02 = new javax.swing.JTable();
        panelOnda02 = new javax.swing.JPanel();
        PanelBarra02 = new javax.swing.JPanel();
        labelTomada02 = new javax.swing.JLabel();
        labelTomada03 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tabOnda03 = new javax.swing.JTable();
        panelOnda03 = new javax.swing.JPanel();
        PanelBarra03 = new javax.swing.JPanel();
        labelTomada04 = new javax.swing.JLabel();
        labelTomada05 = new javax.swing.JLabel();
        labelTomada06 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tabOnda04 = new javax.swing.JTable();
        jScrollPane9 = new javax.swing.JScrollPane();
        tabOnda05 = new javax.swing.JTable();
        jScrollPane10 = new javax.swing.JScrollPane();
        tabOnda06 = new javax.swing.JTable();
        panelOnda04 = new javax.swing.JPanel();
        PanelBarra04 = new javax.swing.JPanel();
        panelOnda05 = new javax.swing.JPanel();
        PanelBarra05 = new javax.swing.JPanel();
        panelOnda06 = new javax.swing.JPanel();
        PanelBarra06 = new javax.swing.JPanel();
        lblUsoSala = new javax.swing.JLabel();
        panelOndas2 = new javax.swing.JPanel();
        panelOnda07 = new javax.swing.JPanel();
        labelTomada07 = new javax.swing.JLabel();
        PanelBarra07 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tabOnda07 = new javax.swing.JTable();
        jScrollPane12 = new javax.swing.JScrollPane();
        tabOnda08 = new javax.swing.JTable();
        panelOnda08 = new javax.swing.JPanel();
        PanelBarra08 = new javax.swing.JPanel();
        labelTomada08 = new javax.swing.JLabel();
        labelTomada09 = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        tabOnda09 = new javax.swing.JTable();
        panelOnda09 = new javax.swing.JPanel();
        PanelBarra09 = new javax.swing.JPanel();
        labelTomada10 = new javax.swing.JLabel();
        labelTomada11 = new javax.swing.JLabel();
        labelTomada12 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        tabOnda10 = new javax.swing.JTable();
        jScrollPane15 = new javax.swing.JScrollPane();
        tabOnda11 = new javax.swing.JTable();
        jScrollPane16 = new javax.swing.JScrollPane();
        tabOnda12 = new javax.swing.JTable();
        panelOnda10 = new javax.swing.JPanel();
        PanelBarra10 = new javax.swing.JPanel();
        panelOnda11 = new javax.swing.JPanel();
        PanelBarra11 = new javax.swing.JPanel();
        panelOnda12 = new javax.swing.JPanel();
        PanelBarra12 = new javax.swing.JPanel();

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelUsoSala.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelUsoSala.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabelaUsoSala.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tabelaUsoSala);

        panelUsoSala.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 810, 210));

        btnSel.setText("Seleciona");
        btnSel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelActionPerformed(evt);
            }
        });
        panelUsoSala.add(btnSel, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 120, -1, -1));

        tabUsoSala.addTab("UsoSala", panelUsoSala);

        panelOndas1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelOndas1.setEnabled(false);
        panelOndas1.setFocusable(false);
        panelOndas1.setLayout(null);

        panelOnda01.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelOnda01.setPreferredSize(new java.awt.Dimension(240, 220));
        panelOndas1.add(panelOnda01);
        panelOnda01.setBounds(10, 220, 300, 260);

        labelTomada01.setText("TOMADA");
        panelOndas1.add(labelTomada01);
        labelTomada01.setBounds(10, 10, 110, 17);

        PanelBarra01.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelOndas1.add(PanelBarra01);
        PanelBarra01.setBounds(310, 220, 300, 260);

        tabOnda01.setModel(new javax.swing.table.DefaultTableModel(
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
        tabOnda01.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabOnda01MouseClicked(evt);
            }
        });
        tabOnda01.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tabOnda01KeyReleased(evt);
            }
        });
        jScrollPane5.setViewportView(tabOnda01);

        panelOndas1.add(jScrollPane5);
        jScrollPane5.setBounds(10, 30, 600, 180);

        tabOnda02.setModel(new javax.swing.table.DefaultTableModel(
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
        tabOnda02.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabOnda02MouseClicked(evt);
            }
        });
        tabOnda02.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tabOnda02KeyReleased(evt);
            }
        });
        jScrollPane6.setViewportView(tabOnda02);

        panelOndas1.add(jScrollPane6);
        jScrollPane6.setBounds(620, 30, 600, 180);

        panelOnda02.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelOnda02.setPreferredSize(new java.awt.Dimension(240, 220));
        panelOndas1.add(panelOnda02);
        panelOnda02.setBounds(620, 220, 300, 260);

        PanelBarra02.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelOndas1.add(PanelBarra02);
        PanelBarra02.setBounds(930, 220, 300, 260);

        labelTomada02.setText("TOMADA");
        panelOndas1.add(labelTomada02);
        labelTomada02.setBounds(620, 10, 110, 17);

        labelTomada03.setText("TOMADA");
        panelOndas1.add(labelTomada03);
        labelTomada03.setBounds(1230, 10, 110, 17);

        tabOnda03.setModel(new javax.swing.table.DefaultTableModel(
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
        tabOnda03.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabOnda03MouseClicked(evt);
            }
        });
        tabOnda03.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tabOnda03KeyReleased(evt);
            }
        });
        jScrollPane7.setViewportView(tabOnda03);

        panelOndas1.add(jScrollPane7);
        jScrollPane7.setBounds(1230, 30, 600, 180);

        panelOnda03.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelOnda03.setPreferredSize(new java.awt.Dimension(240, 220));
        panelOndas1.add(panelOnda03);
        panelOnda03.setBounds(1230, 220, 300, 260);

        PanelBarra03.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelOndas1.add(PanelBarra03);
        PanelBarra03.setBounds(1530, 220, 300, 260);

        labelTomada04.setText("TOMADA");
        panelOndas1.add(labelTomada04);
        labelTomada04.setBounds(10, 500, 110, 17);

        labelTomada05.setText("TOMADA");
        panelOndas1.add(labelTomada05);
        labelTomada05.setBounds(620, 500, 110, 17);

        labelTomada06.setText("TOMADA");
        panelOndas1.add(labelTomada06);
        labelTomada06.setBounds(1230, 500, 110, 17);

        tabOnda04.setModel(new javax.swing.table.DefaultTableModel(
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
        tabOnda04.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabOnda04MouseClicked(evt);
            }
        });
        tabOnda04.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tabOnda04KeyReleased(evt);
            }
        });
        jScrollPane8.setViewportView(tabOnda04);

        panelOndas1.add(jScrollPane8);
        jScrollPane8.setBounds(10, 520, 600, 180);

        tabOnda05.setModel(new javax.swing.table.DefaultTableModel(
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
        tabOnda05.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabOnda05MouseClicked(evt);
            }
        });
        tabOnda05.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tabOnda05KeyReleased(evt);
            }
        });
        jScrollPane9.setViewportView(tabOnda05);

        panelOndas1.add(jScrollPane9);
        jScrollPane9.setBounds(620, 520, 600, 180);

        tabOnda06.setModel(new javax.swing.table.DefaultTableModel(
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
        tabOnda06.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabOnda06MouseClicked(evt);
            }
        });
        tabOnda06.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tabOnda06KeyReleased(evt);
            }
        });
        jScrollPane10.setViewportView(tabOnda06);

        panelOndas1.add(jScrollPane10);
        jScrollPane10.setBounds(1230, 520, 600, 180);

        panelOnda04.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelOnda04.setPreferredSize(new java.awt.Dimension(240, 220));
        panelOndas1.add(panelOnda04);
        panelOnda04.setBounds(10, 710, 300, 260);

        PanelBarra04.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelOndas1.add(PanelBarra04);
        PanelBarra04.setBounds(310, 710, 300, 260);

        panelOnda05.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelOnda05.setPreferredSize(new java.awt.Dimension(240, 220));
        panelOndas1.add(panelOnda05);
        panelOnda05.setBounds(620, 710, 300, 260);

        PanelBarra05.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelOndas1.add(PanelBarra05);
        PanelBarra05.setBounds(920, 710, 300, 260);

        panelOnda06.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelOnda06.setPreferredSize(new java.awt.Dimension(240, 220));
        panelOndas1.add(panelOnda06);
        panelOnda06.setBounds(1230, 710, 300, 260);

        PanelBarra06.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelOndas1.add(PanelBarra06);
        PanelBarra06.setBounds(1530, 710, 300, 260);

        lblUsoSala.setText("UsoSala");
        panelOndas1.add(lblUsoSala);
        lblUsoSala.setBounds(250, 10, 100, 17);

        tabUsoSala.addTab("Ondas Capturadas 1-6", panelOndas1);

        panelOndas2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelOndas2.setEnabled(false);
        panelOndas2.setLayout(null);

        panelOnda07.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelOnda07.setPreferredSize(new java.awt.Dimension(240, 220));

        javax.swing.GroupLayout panelOnda07Layout = new javax.swing.GroupLayout(panelOnda07);
        panelOnda07.setLayout(panelOnda07Layout);
        panelOnda07Layout.setHorizontalGroup(
            panelOnda07Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 294, Short.MAX_VALUE)
        );
        panelOnda07Layout.setVerticalGroup(
            panelOnda07Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 254, Short.MAX_VALUE)
        );

        panelOndas2.add(panelOnda07);
        panelOnda07.setBounds(10, 220, 300, 260);

        labelTomada07.setText("TOMADA");
        panelOndas2.add(labelTomada07);
        labelTomada07.setBounds(10, 10, 110, 17);

        PanelBarra07.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout PanelBarra07Layout = new javax.swing.GroupLayout(PanelBarra07);
        PanelBarra07.setLayout(PanelBarra07Layout);
        PanelBarra07Layout.setHorizontalGroup(
            PanelBarra07Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 296, Short.MAX_VALUE)
        );
        PanelBarra07Layout.setVerticalGroup(
            PanelBarra07Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 256, Short.MAX_VALUE)
        );

        panelOndas2.add(PanelBarra07);
        PanelBarra07.setBounds(310, 220, 300, 260);

        tabOnda07.setModel(new javax.swing.table.DefaultTableModel(
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
        tabOnda07.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabOnda07MouseClicked(evt);
            }
        });
        tabOnda07.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tabOnda07KeyReleased(evt);
            }
        });
        jScrollPane11.setViewportView(tabOnda07);

        panelOndas2.add(jScrollPane11);
        jScrollPane11.setBounds(10, 30, 600, 180);

        tabOnda08.setModel(new javax.swing.table.DefaultTableModel(
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
        tabOnda08.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabOnda08MouseClicked(evt);
            }
        });
        tabOnda08.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tabOnda08KeyReleased(evt);
            }
        });
        jScrollPane12.setViewportView(tabOnda08);

        panelOndas2.add(jScrollPane12);
        jScrollPane12.setBounds(620, 30, 600, 180);

        panelOnda08.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelOnda08.setPreferredSize(new java.awt.Dimension(240, 220));

        javax.swing.GroupLayout panelOnda08Layout = new javax.swing.GroupLayout(panelOnda08);
        panelOnda08.setLayout(panelOnda08Layout);
        panelOnda08Layout.setHorizontalGroup(
            panelOnda08Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 294, Short.MAX_VALUE)
        );
        panelOnda08Layout.setVerticalGroup(
            panelOnda08Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 254, Short.MAX_VALUE)
        );

        panelOndas2.add(panelOnda08);
        panelOnda08.setBounds(620, 220, 300, 260);

        PanelBarra08.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout PanelBarra08Layout = new javax.swing.GroupLayout(PanelBarra08);
        PanelBarra08.setLayout(PanelBarra08Layout);
        PanelBarra08Layout.setHorizontalGroup(
            PanelBarra08Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 296, Short.MAX_VALUE)
        );
        PanelBarra08Layout.setVerticalGroup(
            PanelBarra08Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 256, Short.MAX_VALUE)
        );

        panelOndas2.add(PanelBarra08);
        PanelBarra08.setBounds(920, 220, 300, 260);

        labelTomada08.setText("TOMADA");
        panelOndas2.add(labelTomada08);
        labelTomada08.setBounds(620, 10, 110, 17);

        labelTomada09.setText("TOMADA");
        panelOndas2.add(labelTomada09);
        labelTomada09.setBounds(1230, 10, 110, 17);

        tabOnda09.setModel(new javax.swing.table.DefaultTableModel(
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
        tabOnda09.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabOnda09MouseClicked(evt);
            }
        });
        tabOnda09.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tabOnda09KeyReleased(evt);
            }
        });
        jScrollPane13.setViewportView(tabOnda09);

        panelOndas2.add(jScrollPane13);
        jScrollPane13.setBounds(1230, 30, 600, 180);

        panelOnda09.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelOnda09.setPreferredSize(new java.awt.Dimension(240, 220));

        javax.swing.GroupLayout panelOnda09Layout = new javax.swing.GroupLayout(panelOnda09);
        panelOnda09.setLayout(panelOnda09Layout);
        panelOnda09Layout.setHorizontalGroup(
            panelOnda09Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 294, Short.MAX_VALUE)
        );
        panelOnda09Layout.setVerticalGroup(
            panelOnda09Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 254, Short.MAX_VALUE)
        );

        panelOndas2.add(panelOnda09);
        panelOnda09.setBounds(1230, 220, 300, 260);

        PanelBarra09.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout PanelBarra09Layout = new javax.swing.GroupLayout(PanelBarra09);
        PanelBarra09.setLayout(PanelBarra09Layout);
        PanelBarra09Layout.setHorizontalGroup(
            PanelBarra09Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 296, Short.MAX_VALUE)
        );
        PanelBarra09Layout.setVerticalGroup(
            PanelBarra09Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 256, Short.MAX_VALUE)
        );

        panelOndas2.add(PanelBarra09);
        PanelBarra09.setBounds(1530, 220, 300, 260);

        labelTomada10.setText("TOMADA");
        panelOndas2.add(labelTomada10);
        labelTomada10.setBounds(10, 500, 110, 17);

        labelTomada11.setText("TOMADA");
        panelOndas2.add(labelTomada11);
        labelTomada11.setBounds(620, 500, 110, 17);

        labelTomada12.setText("TOMADA");
        panelOndas2.add(labelTomada12);
        labelTomada12.setBounds(1230, 500, 110, 17);

        tabOnda10.setModel(new javax.swing.table.DefaultTableModel(
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
        tabOnda10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabOnda10MouseClicked(evt);
            }
        });
        tabOnda10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tabOnda10KeyReleased(evt);
            }
        });
        jScrollPane14.setViewportView(tabOnda10);

        panelOndas2.add(jScrollPane14);
        jScrollPane14.setBounds(10, 520, 600, 180);

        tabOnda11.setModel(new javax.swing.table.DefaultTableModel(
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
        tabOnda11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabOnda11MouseClicked(evt);
            }
        });
        tabOnda11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tabOnda11KeyReleased(evt);
            }
        });
        jScrollPane15.setViewportView(tabOnda11);

        panelOndas2.add(jScrollPane15);
        jScrollPane15.setBounds(620, 520, 600, 180);

        tabOnda12.setModel(new javax.swing.table.DefaultTableModel(
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
        tabOnda12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabOnda12MouseClicked(evt);
            }
        });
        tabOnda12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tabOnda12KeyReleased(evt);
            }
        });
        jScrollPane16.setViewportView(tabOnda12);

        panelOndas2.add(jScrollPane16);
        jScrollPane16.setBounds(1230, 520, 600, 180);

        panelOnda10.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelOnda10.setPreferredSize(new java.awt.Dimension(240, 220));

        javax.swing.GroupLayout panelOnda10Layout = new javax.swing.GroupLayout(panelOnda10);
        panelOnda10.setLayout(panelOnda10Layout);
        panelOnda10Layout.setHorizontalGroup(
            panelOnda10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 294, Short.MAX_VALUE)
        );
        panelOnda10Layout.setVerticalGroup(
            panelOnda10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 254, Short.MAX_VALUE)
        );

        panelOndas2.add(panelOnda10);
        panelOnda10.setBounds(10, 710, 300, 260);

        PanelBarra10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout PanelBarra10Layout = new javax.swing.GroupLayout(PanelBarra10);
        PanelBarra10.setLayout(PanelBarra10Layout);
        PanelBarra10Layout.setHorizontalGroup(
            PanelBarra10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 296, Short.MAX_VALUE)
        );
        PanelBarra10Layout.setVerticalGroup(
            PanelBarra10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 256, Short.MAX_VALUE)
        );

        panelOndas2.add(PanelBarra10);
        PanelBarra10.setBounds(310, 710, 300, 260);

        panelOnda11.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelOnda11.setPreferredSize(new java.awt.Dimension(240, 220));

        javax.swing.GroupLayout panelOnda11Layout = new javax.swing.GroupLayout(panelOnda11);
        panelOnda11.setLayout(panelOnda11Layout);
        panelOnda11Layout.setHorizontalGroup(
            panelOnda11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 294, Short.MAX_VALUE)
        );
        panelOnda11Layout.setVerticalGroup(
            panelOnda11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 254, Short.MAX_VALUE)
        );

        panelOndas2.add(panelOnda11);
        panelOnda11.setBounds(620, 710, 300, 260);

        PanelBarra11.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout PanelBarra11Layout = new javax.swing.GroupLayout(PanelBarra11);
        PanelBarra11.setLayout(PanelBarra11Layout);
        PanelBarra11Layout.setHorizontalGroup(
            PanelBarra11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 296, Short.MAX_VALUE)
        );
        PanelBarra11Layout.setVerticalGroup(
            PanelBarra11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 256, Short.MAX_VALUE)
        );

        panelOndas2.add(PanelBarra11);
        PanelBarra11.setBounds(920, 710, 300, 260);

        panelOnda12.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelOnda12.setPreferredSize(new java.awt.Dimension(240, 220));

        javax.swing.GroupLayout panelOnda12Layout = new javax.swing.GroupLayout(panelOnda12);
        panelOnda12.setLayout(panelOnda12Layout);
        panelOnda12Layout.setHorizontalGroup(
            panelOnda12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 294, Short.MAX_VALUE)
        );
        panelOnda12Layout.setVerticalGroup(
            panelOnda12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 254, Short.MAX_VALUE)
        );

        panelOndas2.add(panelOnda12);
        panelOnda12.setBounds(1230, 710, 300, 260);

        PanelBarra12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout PanelBarra12Layout = new javax.swing.GroupLayout(PanelBarra12);
        PanelBarra12.setLayout(PanelBarra12Layout);
        PanelBarra12Layout.setHorizontalGroup(
            PanelBarra12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 296, Short.MAX_VALUE)
        );
        PanelBarra12Layout.setVerticalGroup(
            PanelBarra12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 256, Short.MAX_VALUE)
        );

        panelOndas2.add(PanelBarra12);
        PanelBarra12.setBounds(1530, 710, 300, 260);

        tabUsoSala.addTab("Ondas Capturadas 7-12", panelOndas2);

        jPanel1.add(tabUsoSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1930, 1150));

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabOnda01MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabOnda01MouseClicked
        // TODO add your handling code here:
        int linha = tabOnda01.getSelectedRow();        
        CapturaAtual cap  = capdao.localiza((Integer) tabOnda01.getValueAt(linha, 0));        
        atualizaGraficos(cap, panelOnda01, PanelBarra01);       
    }//GEN-LAST:event_tabOnda01MouseClicked

    private void tabOnda02MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabOnda02MouseClicked
        // TODO add your handling code here:
        int linha = tabOnda02.getSelectedRow();        
        CapturaAtual cap  = capdao.localiza((Integer) tabOnda02.getValueAt(linha, 0));        
        atualizaGraficos(cap, panelOnda02, PanelBarra02);       
    }//GEN-LAST:event_tabOnda02MouseClicked

    private void tabOnda03MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabOnda03MouseClicked
        // TODO add your handling code here:
        int linha = tabOnda03.getSelectedRow();        
        CapturaAtual cap  = capdao.localiza((Integer) tabOnda03.getValueAt(linha, 0));        
        atualizaGraficos(cap, panelOnda03, PanelBarra03);       
    }//GEN-LAST:event_tabOnda03MouseClicked

    private void tabOnda04MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabOnda04MouseClicked
        // TODO add your handling code here:
        int linha = tabOnda04.getSelectedRow();        
        CapturaAtual cap  = capdao.localiza((Integer) tabOnda04.getValueAt(linha, 0));        
        atualizaGraficos(cap, panelOnda04, PanelBarra04);       
    }//GEN-LAST:event_tabOnda04MouseClicked

    private void tabOnda05MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabOnda05MouseClicked
        // TODO add your handling code here:
        int linha = tabOnda05.getSelectedRow();        
        CapturaAtual cap  = capdao.localiza((Integer) tabOnda05.getValueAt(linha, 0));        
        atualizaGraficos(cap, panelOnda05, PanelBarra05);
    }//GEN-LAST:event_tabOnda05MouseClicked

    private void tabOnda06MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabOnda06MouseClicked
        // TODO add your handling code here:
        int linha = tabOnda06.getSelectedRow();        
        CapturaAtual cap  = capdao.localiza((Integer) tabOnda06.getValueAt(linha, 0));        
        atualizaGraficos(cap, panelOnda06, PanelBarra06);
    }//GEN-LAST:event_tabOnda06MouseClicked

    private void tabOnda07MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabOnda07MouseClicked
        // TODO add your handling code here:
        int linha = tabOnda07.getSelectedRow();        
        CapturaAtual cap  = capdao.localiza((Integer) tabOnda07.getValueAt(linha, 0));        
        atualizaGraficos(cap, panelOnda07, PanelBarra07);
    }//GEN-LAST:event_tabOnda07MouseClicked

    private void tabOnda08MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabOnda08MouseClicked
        // TODO add your handling code here:
        int linha = tabOnda08.getSelectedRow();        
        CapturaAtual cap  = capdao.localiza((Integer) tabOnda08.getValueAt(linha, 0));        
        atualizaGraficos(cap, panelOnda08, PanelBarra08);
    }//GEN-LAST:event_tabOnda08MouseClicked

    private void tabOnda09MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabOnda09MouseClicked
        // TODO add your handling code here:
        int linha = tabOnda09.getSelectedRow();        
        CapturaAtual cap  = capdao.localiza((Integer) tabOnda09.getValueAt(linha, 0));        
        atualizaGraficos(cap, panelOnda09, PanelBarra09);
    }//GEN-LAST:event_tabOnda09MouseClicked

    private void tabOnda10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabOnda10MouseClicked
        // TODO add your handling code here:
        int linha = tabOnda10.getSelectedRow();        
        CapturaAtual cap  = capdao.localiza((Integer) tabOnda10.getValueAt(linha, 0));        
        atualizaGraficos(cap, panelOnda10, PanelBarra10);
    }//GEN-LAST:event_tabOnda10MouseClicked

    private void tabOnda11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabOnda11MouseClicked
        // TODO add your handling code here:
        int linha = tabOnda11.getSelectedRow();        
        CapturaAtual cap  = capdao.localiza((Integer) tabOnda11.getValueAt(linha, 0));        
        atualizaGraficos(cap, panelOnda11, PanelBarra11);
    }//GEN-LAST:event_tabOnda11MouseClicked

    private void tabOnda12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabOnda12MouseClicked
        // TODO add your handling code here:
        int linha = tabOnda12.getSelectedRow();        
        CapturaAtual cap  = capdao.localiza((Integer) tabOnda12.getValueAt(linha, 0));        
        atualizaGraficos(cap, panelOnda12, PanelBarra12);
    }//GEN-LAST:event_tabOnda12MouseClicked

    private void btnSelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelActionPerformed
        // TODO add your handling code here:
        tabUsoSala.setEnabledAt(1, false);
        tabUsoSala.setEnabledAt(2, false);
        
        preparaTabelas();
        
        int linha = tabelaUsoSala.getSelectedRow();
                
        bean = dao.localiza( (Integer) tabelaUsoSala.getValueAt(linha, 0) );
        
        lblUsoSala.setText("UsoSala: " + bean.getCodUsoSala());
        
        lista = extradao.listaCapturas(bean);        
        atualizaOndas();                
    }//GEN-LAST:event_btnSelActionPerformed

    private void tabOnda01KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabOnda01KeyReleased
        // TODO add your handling code here:
        this.tabOnda01MouseClicked(null);
    }//GEN-LAST:event_tabOnda01KeyReleased

    private void tabOnda02KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabOnda02KeyReleased
        // TODO add your handling code here:
        this.tabOnda02MouseClicked(null);
    }//GEN-LAST:event_tabOnda02KeyReleased

    private void tabOnda03KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabOnda03KeyReleased
        // TODO add your handling code here:
        this.tabOnda03MouseClicked(null);
    }//GEN-LAST:event_tabOnda03KeyReleased

    private void tabOnda04KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabOnda04KeyReleased
        // TODO add your handling code here:
        this.tabOnda04MouseClicked(null);
    }//GEN-LAST:event_tabOnda04KeyReleased

    private void tabOnda05KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabOnda05KeyReleased
        // TODO add your handling code here:
        this.tabOnda05MouseClicked(null);
    }//GEN-LAST:event_tabOnda05KeyReleased

    private void tabOnda06KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabOnda06KeyReleased
        // TODO add your handling code here:
        this.tabOnda06MouseClicked(null);
    }//GEN-LAST:event_tabOnda06KeyReleased

    private void tabOnda07KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabOnda07KeyReleased
        // TODO add your handling code here:
        this.tabOnda07MouseClicked(null);
    }//GEN-LAST:event_tabOnda07KeyReleased

    private void tabOnda08KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabOnda08KeyReleased
        // TODO add your handling code here:
        this.tabOnda08MouseClicked(null);
    }//GEN-LAST:event_tabOnda08KeyReleased

    private void tabOnda09KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabOnda09KeyReleased
        // TODO add your handling code here:
        this.tabOnda09MouseClicked(null);
    }//GEN-LAST:event_tabOnda09KeyReleased

    private void tabOnda10KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabOnda10KeyReleased
        // TODO add your handling code here:
        this.tabOnda10MouseClicked(null);
    }//GEN-LAST:event_tabOnda10KeyReleased

    private void tabOnda11KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabOnda11KeyReleased
        // TODO add your handling code here:
        this.tabOnda11MouseClicked(null);
    }//GEN-LAST:event_tabOnda11KeyReleased

    private void tabOnda12KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabOnda12KeyReleased
        // TODO add your handling code here:
        this.tabOnda12MouseClicked(null);
    }//GEN-LAST:event_tabOnda12KeyReleased


    /**
     * @param args the command line arguments
     */    
    public static void main(String args[]) {
        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormMostraUsoSala dialog = new FormMostraUsoSala(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
                

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelBarra01;
    private javax.swing.JPanel PanelBarra02;
    private javax.swing.JPanel PanelBarra03;
    private javax.swing.JPanel PanelBarra04;
    private javax.swing.JPanel PanelBarra05;
    private javax.swing.JPanel PanelBarra06;
    private javax.swing.JPanel PanelBarra07;
    private javax.swing.JPanel PanelBarra08;
    private javax.swing.JPanel PanelBarra09;
    private javax.swing.JPanel PanelBarra10;
    private javax.swing.JPanel PanelBarra11;
    private javax.swing.JPanel PanelBarra12;
    private javax.swing.JButton btnSel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel labelTomada01;
    private javax.swing.JLabel labelTomada02;
    private javax.swing.JLabel labelTomada03;
    private javax.swing.JLabel labelTomada04;
    private javax.swing.JLabel labelTomada05;
    private javax.swing.JLabel labelTomada06;
    private javax.swing.JLabel labelTomada07;
    private javax.swing.JLabel labelTomada08;
    private javax.swing.JLabel labelTomada09;
    private javax.swing.JLabel labelTomada10;
    private javax.swing.JLabel labelTomada11;
    private javax.swing.JLabel labelTomada12;
    private javax.swing.JLabel lblUsoSala;
    private javax.swing.JPanel panelOnda01;
    private javax.swing.JPanel panelOnda02;
    private javax.swing.JPanel panelOnda03;
    private javax.swing.JPanel panelOnda04;
    private javax.swing.JPanel panelOnda05;
    private javax.swing.JPanel panelOnda06;
    private javax.swing.JPanel panelOnda07;
    private javax.swing.JPanel panelOnda08;
    private javax.swing.JPanel panelOnda09;
    private javax.swing.JPanel panelOnda10;
    private javax.swing.JPanel panelOnda11;
    private javax.swing.JPanel panelOnda12;
    private javax.swing.JPanel panelOndas1;
    private javax.swing.JPanel panelOndas2;
    private javax.swing.JPanel panelUsoSala;
    private javax.swing.JTable tabOnda01;
    private javax.swing.JTable tabOnda02;
    private javax.swing.JTable tabOnda03;
    private javax.swing.JTable tabOnda04;
    private javax.swing.JTable tabOnda05;
    private javax.swing.JTable tabOnda06;
    private javax.swing.JTable tabOnda07;
    private javax.swing.JTable tabOnda08;
    private javax.swing.JTable tabOnda09;
    private javax.swing.JTable tabOnda10;
    private javax.swing.JTable tabOnda11;
    private javax.swing.JTable tabOnda12;
    private javax.swing.JTabbedPane tabUsoSala;
    private javax.swing.JTable tabelaUsoSala;
    // End of variables declaration//GEN-END:variables
}
