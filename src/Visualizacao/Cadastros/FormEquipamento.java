/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FormEquipamento.java
 *
 * Created on Nov 4, 2011, 8:12:28 AM
 *
 * @author Eduardo
 * Modified by: rebonatto
 */

package Visualizacao.Cadastros;

import Uteis.Conversoes;
import Uteis.CalendarComboBox;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import modelo.Equipamento;
import modelo.Marca;
import modelo.Modelo;
import modelo.Tipo;
import persistencia.EquipamentoDAO;
import persistencia.MarcaDAO;
import persistencia.ModeloDAO;
import persistencia.TipoDAO;

/**
 *
 * @author cliente
 */
public class FormEquipamento extends javax.swing.JDialog {
    private Equipamento bean = new Equipamento();
    private EquipamentoDAO dao = new EquipamentoDAO();
    private MarcaDAO mcadao = new MarcaDAO();
    private ModeloDAO mdldao = new ModeloDAO();
    private TipoDAO tpdao = new TipoDAO();
    private boolean editando;
    private char acao;
    Vector titulos = new Vector();
    Vector linhas = new Vector();

    /** Creates new form FormEquipamento */
    public FormEquipamento(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        //preenche o combobox
        comboMarca.removeAllItems();
        for (Marca mca : mcadao.lista()) {
            comboMarca.addItem((Marca) mca);
        }

        comboModelo.removeAllItems();
        for (Modelo mdl : mdldao.lista()) {
            comboModelo.addItem((Modelo) mdl);
        }

        comboTipo.removeAllItems();
        for (Tipo tp : tpdao.lista()) {
            comboTipo.addItem((Tipo) tp);
        }

        setTitle("Cadastro de Equipamentos");
        if (montaTabela() == 0) {
            JOptionPane.showMessageDialog(this, "Tabela Vazia");
            limpaCampos();
        } else {
            atualizaFormTabela();
        }
        editando = false;
        
        jTabbedPane1.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (jTabbedPane1.getSelectedIndex() == 1) // Esta na aba consulta
                tabela.requestFocus();
                
            }
        });
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        tabCadastro = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jtfCodigo = new javax.swing.JTextField();
        jtfDescricao = new javax.swing.JTextField();
        btnIncluir = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        comboMarca = new javax.swing.JComboBox();
        btnMarca = new javax.swing.JButton();
        jtfMarca = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jtfRfid = new javax.swing.JTextField();
        jtfCodPatrimonio = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jtfModelo = new javax.swing.JTextField();
        comboModelo = new javax.swing.JComboBox();
        btnModelo = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jtfTipo = new javax.swing.JTextField();
        comboTipo = new javax.swing.JComboBox();
        btnTipo = new javax.swing.JButton();
        comboUltimaFalha = new CalendarComboBox();
        comboUltimaManutencao = new CalendarComboBox(true);
        jLabel11 = new javax.swing.JLabel();
        jtfTempoUso = new javax.swing.JTextField();
        tabConsulta = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tabCadastro.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Código:");
        tabCadastro.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 22, -1, -1));

        jLabel2.setText("Descrição: ");
        tabCadastro.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 49, -1, -1));

        jtfCodigo.setEditable(false);
        tabCadastro.add(jtfCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(104, 12, 84, -1));

        jtfDescricao.setEditable(false);
        tabCadastro.add(jtfDescricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 44, 290, -1));

        btnIncluir.setText("Incluir");
        btnIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluirActionPerformed(evt);
            }
        });
        tabCadastro.add(btnIncluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 381, -1, -1));

        btnAlterar.setText("Alterar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });
        tabCadastro.add(btnAlterar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 380, -1, -1));

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        tabCadastro.add(btnExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 380, -1, -1));

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        tabCadastro.add(btnSalvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 380, -1, -1));

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        tabCadastro.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(401, 381, -1, -1));

        jLabel3.setText("Marca");
        tabCadastro.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 89, 39, -1));

        comboMarca.setEditable(true);
        comboMarca.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboMarcaActionPerformed(evt);
            }
        });
        tabCadastro.add(comboMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 84, 234, -1));

        btnMarca.setText("Marca");
        btnMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMarcaActionPerformed(evt);
            }
        });
        tabCadastro.add(btnMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 83, -1, -1));

        jtfMarca.setEditable(false);
        jtfMarca.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfMarcaFocusLost(evt);
            }
        });
        tabCadastro.add(jtfMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 84, 62, -1));

        jLabel4.setText("Rfid");
        tabCadastro.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 197, -1, -1));

        jtfRfid.setEditable(false);
        tabCadastro.add(jtfRfid, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 192, 54, -1));

        jtfCodPatrimonio.setEditable(false);
        tabCadastro.add(jtfCodPatrimonio, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 225, 92, -1));

        jLabel5.setText("Cod Patrimonio");
        tabCadastro.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 230, -1, -1));

        jLabel6.setText("Tempo de Uso");
        tabCadastro.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 263, -1, -1));

        jLabel7.setText("Data Ultima Falha");
        tabCadastro.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 302, -1, -1));

        jLabel8.setText("Data ultima Manutenção");
        tabCadastro.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 341, -1, -1));

        jLabel9.setText("Modelo");
        tabCadastro.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 124, 39, -1));

        jtfModelo.setEditable(false);
        jtfModelo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfModeloFocusLost(evt);
            }
        });
        tabCadastro.add(jtfModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 119, 62, -1));

        comboModelo.setEditable(true);
        comboModelo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboModelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboModeloActionPerformed(evt);
            }
        });
        tabCadastro.add(comboModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 119, 234, -1));

        btnModelo.setText("Modelo");
        btnModelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModeloActionPerformed(evt);
            }
        });
        tabCadastro.add(btnModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 118, -1, -1));

        jLabel10.setText("Tipo");
        tabCadastro.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 159, 39, -1));

        jtfTipo.setEditable(false);
        jtfTipo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfTipoFocusLost(evt);
            }
        });
        tabCadastro.add(jtfTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 154, 62, -1));

        comboTipo.setEditable(true);
        comboTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTipoActionPerformed(evt);
            }
        });
        tabCadastro.add(comboTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 154, 234, -1));

        btnTipo.setText("Tipo");
        btnTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTipoActionPerformed(evt);
            }
        });
        tabCadastro.add(btnTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 153, -1, -1));

        comboUltimaFalha.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        tabCadastro.add(comboUltimaFalha, new org.netbeans.lib.awtextra.AbsoluteConstraints(194, 297, 124, -1));

        comboUltimaManutencao.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        tabCadastro.add(comboUltimaManutencao, new org.netbeans.lib.awtextra.AbsoluteConstraints(194, 336, 124, -1));

        jLabel11.setText("Dias Horas minutos:segundos");
        tabCadastro.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 260, -1, 20));

        jtfTempoUso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfTempoUsoActionPerformed(evt);
            }
        });
        tabCadastro.add(jtfTempoUso, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 260, 150, -1));

        jTabbedPane1.addTab("Cadastro", tabCadastro);

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

        javax.swing.GroupLayout tabConsultaLayout = new javax.swing.GroupLayout(tabConsulta);
        tabConsulta.setLayout(tabConsultaLayout);
        tabConsultaLayout.setHorizontalGroup(
            tabConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabConsultaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE)
                .addContainerGap())
        );
        tabConsultaLayout.setVerticalGroup(
            tabConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabConsultaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(193, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Consulta", tabConsulta);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluirActionPerformed
        // TODO add your handling code here:
        editando = true;
        atualizaBotoes();
        limpaCampos();
        acao = 'I';
        jtfDescricao.requestFocus();
}//GEN-LAST:event_btnIncluirActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        // TODO add your handling code here:
        editando = true;
        atualizaBotoes();
        acao = 'A';
        jtfDescricao.requestFocus();
}//GEN-LAST:event_btnAlterarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // TODO add your handling code here:
        atualizaBean(true);
        if (JOptionPane.showConfirmDialog(null, "Confirma exclusão",
                "Cadastro de Equipamento", JOptionPane.YES_OPTION,
                JOptionPane.NO_OPTION) == JOptionPane.YES_OPTION) {
            if (dao.exclui(bean)) {
                JOptionPane.showMessageDialog(null, "Excluido com sucesso");
                removeTabela(bean);
            } else {
                JOptionPane.showMessageDialog(null, dao.getMensagem(),
                        "Problemas ao Excluir", JOptionPane.ERROR_MESSAGE);
            }
        }
}//GEN-LAST:event_btnExcluirActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // TODO add your handling code here:
        if (! ValidaCampos())
            return;

        if (acao == 'I') { // Inclusão
            atualizaBean(false);
            if (dao.Insere(bean)) {
                bean.setCodEquip(dao.ultimaChave());
                jtfCodigo.setText(String.valueOf(bean.getCodEquip()));
                JOptionPane.showMessageDialog(null, "Incluido com sucesso");
                addTabela(bean);
                //para atualizar a tabela
                tabela.revalidate();
            } else {
                JOptionPane.showMessageDialog(null, dao.getMensagem(),
                        "Problemas ao incluir", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            atualizaBean(true);
            if (dao.altera(bean)) {
                JOptionPane.showMessageDialog(null, "Alterado com sucesso");
                //para atualizar a tabela. Já chama o revalidate
                updateTabela(bean);
            } else {
                JOptionPane.showMessageDialog(null, dao.getMensagem(),
                        "Problemas ao Alterar", JOptionPane.ERROR_MESSAGE);
            }
        }
        editando = false;
        atualizaBotoes();
}//GEN-LAST:event_btnSalvarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        editando = false;
        atualizaForm();
}//GEN-LAST:event_btnCancelarActionPerformed

    private void comboMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboMarcaActionPerformed
        // TODO add your handing code here:
        if (comboMarca.getSelectedIndex() != -1) {
            Marca mca = (Marca) comboMarca.getSelectedItem();
            jtfMarca.setText(String.valueOf(mca.getCodMarca()));
        }
}//GEN-LAST:event_comboMarcaActionPerformed

    private void btnMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMarcaActionPerformed
        // TODO add your handling code here:
        FormMarca painel = new FormMarca(new JFrame(), true);
        painel.setVisible(true);
        comboMarca.removeAllItems();
        for (Marca mca : mcadao.lista()) {
            comboMarca.addItem(mca);
        }
}//GEN-LAST:event_btnMarcaActionPerformed

    private void jtfMarcaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfMarcaFocusLost
        // TODO add your handling code here:
        if (jtfMarca.getText().isEmpty()){
            comboMarca.setSelectedIndex(-1);
            return;
        }
        int codMarca = Integer.parseInt(jtfMarca.getText());
        Marca mca = mcadao.localiza(codMarca);
        if (mca == null) {
            JOptionPane.showMessageDialog(null, "marca não encontrado");
            if(comboMarca.getSelectedIndex() != -1){
                mca = (Marca) comboMarca.getSelectedItem();
                jtfMarca.setText(String.valueOf(mca.getCodMarca()));
            }
            return;
        } else {
            comboMarca.setSelectedItem((Marca) mca);
        }
    }//GEN-LAST:event_jtfMarcaFocusLost

    private void tabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseClicked
        // TODO add your handling code here:
        int linha = tabela.getSelectedRow();
        int coluna = 0;

        bean.setCodEquip((Integer) tabela.getValueAt(linha, coluna++));
        bean.setCodMarca((Marca) tabela.getValueAt(linha, coluna++));
        bean.setCodModelo((Modelo) tabela.getValueAt(linha, coluna++));
        bean.setCodTipo((Tipo) tabela.getValueAt(linha, coluna++));
        bean.setDesc((String) tabela.getValueAt(linha, coluna++));
        bean.setRfid((String) tabela.getValueAt(linha, coluna++));
        bean.setCodPatrimonio((String) tabela.getValueAt(linha, coluna++));
        bean.setTempoUso(Conversoes.TempoToIntSegundos((String) tabela.getValueAt(linha, coluna++)));        
        String s = (String) tabela.getValueAt(linha, coluna++);
        bean.setDataUltimaFalha(Conversoes.StringToCalendar(s));
        String ss = (String) tabela.getValueAt(linha, coluna++);
        bean.setDataUltimaManutencao(Conversoes.StringToCalendar(ss));


        atualizaForm();
}//GEN-LAST:event_tabelaMouseClicked

    private void jtfModeloFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfModeloFocusLost
        // TODO add your handling code here:
        if (jtfModelo.getText().isEmpty()){
            comboModelo.setSelectedIndex(-1);
            return;
        }
        int codModelo = Integer.parseInt(jtfModelo.getText());
        Modelo mdl = mdldao.localiza(codModelo);
        if (mdl == null) {
            JOptionPane.showMessageDialog(null, "modelo não encontrado");
            if(comboModelo.getSelectedIndex() != -1){
                mdl = (Modelo) comboModelo.getSelectedItem();
                jtfMarca.setText(String.valueOf(mdl.getCodModelo()));
            }
            return;
        } else {
            comboModelo.setSelectedItem((Modelo) mdl);
        }
    }//GEN-LAST:event_jtfModeloFocusLost

    private void comboModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboModeloActionPerformed
        // TODO add your handling code here:
         if (comboModelo.getSelectedIndex() != -1) {
            Modelo mdl = (Modelo) comboModelo.getSelectedItem();
            jtfModelo.setText(String.valueOf(mdl.getCodModelo()));
        }


    }//GEN-LAST:event_comboModeloActionPerformed

    private void btnModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModeloActionPerformed
        // TODO add your handling code here:
        FormModelo painel = new FormModelo(new JFrame(), true);
        painel.setVisible(true);
        comboModelo.removeAllItems();
        for (Modelo mdl : mdldao.lista()) {
            comboModelo.addItem(mdl);
        }
    }//GEN-LAST:event_btnModeloActionPerformed

    private void jtfTipoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfTipoFocusLost
        // TODO add your handling code here:
        if (jtfTipo.getText().isEmpty()){
            comboTipo.setSelectedIndex(-1);
            return;
        }
        int codTipo = Integer.parseInt(jtfTipo.getText());
        Tipo tp = tpdao.localiza(codTipo);
        if (tp == null) {
            JOptionPane.showMessageDialog(null, "Tipo não encontrado");
            if(comboTipo.getSelectedIndex() != -1){
                tp = (Tipo) comboTipo.getSelectedItem();
                jtfTipo.setText(String.valueOf(tp.getCodTipo()));
            }
            return;
        } else {
            comboTipo.setSelectedItem((Tipo) tp);
        }
    
    }//GEN-LAST:event_jtfTipoFocusLost

    private void comboTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTipoActionPerformed
        // TODO add your handling code here:
        if (comboTipo.getSelectedIndex() != -1) {
            Tipo tp = (Tipo) comboTipo.getSelectedItem();
            jtfTipo.setText(String.valueOf(tp.getCodTipo()));
        }
    }//GEN-LAST:event_comboTipoActionPerformed

    private void btnTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTipoActionPerformed
        // TODO add your handling code here:
        FormTipo painel = new FormTipo(new JFrame(), true);
        painel.setVisible(true);
        comboTipo.removeAllItems();
        for (Tipo tp : tpdao.lista()) {
            comboModelo.addItem(tp);
        }
    }//GEN-LAST:event_btnTipoActionPerformed

    private void tabelaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelaKeyReleased
        // TODO add your handling code here:
         if (tabela.getSelectedRow() != -1) {         
            System.out.println(tabela.getSelectedRow());
            this.tabelaMouseClicked(null);
        }
    }//GEN-LAST:event_tabelaKeyReleased

    private void jtfTempoUsoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfTempoUsoActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jtfTempoUsoActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                FormEquipamento dialog = new FormEquipamento(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    private int montaTabela() {
        //Ajusta titulos da tabela
        titulos.add("Código");
        titulos.add("Cod Marca");
        titulos.add("Cod Modelo");
        titulos.add("Cod Tipo");
        titulos.add("Descricao");
        titulos.add("Rfid");
        titulos.add("Codigo patrimonio");
        titulos.add("Tempo de uso");
        titulos.add("Data ultima falha");
        titulos.add("Data ultima manutencao");


        // inicializa os dados da tabela
        for (Equipamento p : dao.lista()) {
            addTabela(p);
        }

        tabela.setModel(new DefaultTableModel(linhas, titulos));

        return (linhas.size());
    }

    private void addTabela(Equipamento p) {
        Vector colunas = new Vector();

        colunas.add(p.getCodEquip());
        colunas.add(p.getCodMarca());
        colunas.add(p.getCodModelo());
        colunas.add(p.getCodTipo());
        colunas.add(p.getDesc());    // vai adicionar o objeto e
        colunas.add(p.getRfid());
        colunas.add(p.getCodPatrimonio());
        colunas.add(Conversoes.IntSegundosToTempo(p.getTempoUso()));
        colunas.add(Conversoes.CalendarToString(p.getDataUltimaFalha()));
        colunas.add(Conversoes.CalendarToString(p.getDataUltimaManutencao()));

        linhas.add(colunas);
    }

    private void updateTabela(Equipamento p) {
        for (int i = 0; i < linhas.size(); i++) {
            // Achou o mesmo código
            if (p.getCodEquip() == (Integer) tabela.getValueAt(i, 0)) {
                // adiciona o objeto e mostra o toString()
                tabela.setValueAt(p.getCodMarca(), i, 1);
                tabela.setValueAt(p.getCodModelo(), i, 2);
                tabela.setValueAt(p.getCodTipo(), i, 3);
                tabela.setValueAt(p.getDesc(), i, 4);
                tabela.setValueAt(p.getRfid(), i, 5);
                tabela.setValueAt(p.getCodPatrimonio(), i, 6);
                tabela.setValueAt(Conversoes.IntSegundosToTempo(p.getTempoUso()), i, 7);
                tabela.setValueAt(Conversoes.CalendarToString(p.getDataUltimaFalha()), i, 8);
                tabela.setValueAt(Conversoes.CalendarToString(p.getDataUltimaManutencao()), i, 9);

                break;
            }
        }
        tabela.revalidate();
    }

    private void removeTabela(Equipamento p) {
        for (int i = 0; i < linhas.size(); i++) {
            // Achou o mesmo código
            if (p.getCodEquip() == (Integer) tabela.getValueAt(i, 0)) {
                linhas.remove(i);
                break;
            }
        }
        tabela.revalidate();
        if (linhas.size() == 0) {
            JOptionPane.showMessageDialog(this, "Tabela Vazia");
            limpaCampos();
        } else {
            atualizaFormTabela();
        }
    }

    private void atualizaForm() {
        atualizaBotoes();
        atualizaCampos();
    }

    private void atualizaCampos() {
        // Atualiza os campos de edição
        jtfCodigo.setText(String.valueOf(bean.getCodEquip()));
        jtfDescricao.setText(bean.getDesc());
        comboMarca.setSelectedItem((Marca) bean.getCodMarca());
        jtfMarca.setText(String.valueOf(bean.getCodMarca().getCodMarca()));
        comboModelo.setSelectedItem((Modelo) bean.getCodModelo());
        jtfModelo.setText(String.valueOf(bean.getCodModelo().getCodModelo()));
        comboTipo.setSelectedItem((Tipo) bean.getCodTipo());
        jtfTipo.setText(String.valueOf(bean.getCodTipo().getCodTipo()));
        jtfRfid.setText(String.valueOf(bean.getRfid()));
        jtfCodPatrimonio.setText(String.valueOf(bean.getCodPatrimonio()));
        jtfTempoUso.setText(Conversoes.IntSegundosToTempo(bean.getTempoUso()));
        comboUltimaFalha.setSelectedItem(Conversoes.CalendarToString(bean.getDataUltimaFalha()));
        comboUltimaManutencao.setSelectedItem(Conversoes.CalendarToString(bean.getDataUltimaManutencao()));
    }

    private void atualizaBotoes() {
        //atualiza os campos de edicao
        jtfDescricao.setEditable(editando);
        comboMarca.setEnabled(editando);
        comboModelo.setEnabled(editando);
        comboTipo.setEnabled(editando);
        jtfMarca.setEditable(editando);
        jtfModelo.setEditable(editando);
        jtfTipo.setEditable(editando);
        jtfRfid.setEditable(editando);
        jtfCodPatrimonio.setEditable(editando);
        jtfTempoUso.setEditable(false);
        comboUltimaFalha.setEnabled(editando);
        comboUltimaManutencao.setEnabled(editando);

        // atualiza os botões
        btnAlterar.setEnabled(!editando);
        btnIncluir.setEnabled(!editando);
        btnExcluir.setEnabled(!editando);
        btnSalvar.setEnabled(editando);
        btnCancelar.setEnabled(editando);
    }

    private void limpaCampos() {
        // Atualiza os campos de edição
        jtfCodigo.setText("");
        jtfDescricao.setText("");
        comboMarca.setSelectedIndex(-1); //não selecionado
        jtfMarca.setText("");
        comboModelo.setSelectedIndex(-1); //não selecionado
        jtfModelo.setText("");
        comboTipo.setSelectedIndex(-1); //não selecionado
        jtfTipo.setText("");
        jtfRfid.setText("");
        jtfTempoUso.setText("0D 0H 0:0");
        jtfCodPatrimonio.setText("");
        comboUltimaFalha.setSelectedIndex(-1);
        comboUltimaManutencao.setSelectedIndex(-1);
    }

    private void atualizaBean(boolean codigo) {
        // Atualiza os campos de edição
        if (codigo) {
            bean.setCodEquip(Integer.parseInt(jtfCodigo.getText()));
        }
        bean.setDesc(jtfDescricao.getText());
        bean.setCodMarca((Marca) comboMarca.getSelectedItem());
        bean.setCodModelo((Modelo) comboModelo.getSelectedItem());
        bean.setCodTipo((Tipo) comboTipo.getSelectedItem());
        bean.setRfid(jtfRfid.getText());
        bean.setCodPatrimonio(jtfCodPatrimonio.getText());
        bean.setTempoUso(Conversoes.TempoToIntSegundos(jtfTempoUso.getText()));
        String s = comboUltimaFalha.getSelectedItem().toString();
        bean.setDataUltimaFalha(Conversoes.StringToCalendar(s));
        String ss = comboUltimaManutencao.getSelectedItem().toString();
        bean.setDataUltimaManutencao(Conversoes.StringToCalendar(ss));       
    }

    private void atualizaFormTabela() {
        int linha = tabela.getRowCount() - 1;
        int coluna = 0;        
                
        bean.setCodEquip((Integer) tabela.getValueAt(linha, coluna++));
        bean.setCodMarca((Marca) tabela.getValueAt(linha, coluna++));
        bean.setCodModelo((Modelo) tabela.getValueAt(linha, coluna++));
        bean.setCodTipo((Tipo) tabela.getValueAt(linha, coluna++));
        bean.setDesc((String) tabela.getValueAt(linha, coluna++));
        bean.setRfid((String) tabela.getValueAt(linha, coluna++));
        bean.setCodPatrimonio((String) tabela.getValueAt(linha, coluna++));
        bean.setTempoUso(Conversoes.TempoToIntSegundos((String) tabela.getValueAt(linha, coluna++)));
        bean.setDataUltimaFalha(Conversoes.StringToCalendar((String) tabela.getValueAt(linha, coluna++)));
        bean.setDataUltimaManutencao(Conversoes.StringToCalendar((String) tabela. getValueAt(linha, coluna++)));
        
        atualizaForm();
    }

    private boolean ValidaCampos(){
        if (comboMarca.getSelectedItem() == null){
            JOptionPane.showMessageDialog(null, "Marca deve ser selecionado" ,
                    "Cadastro de Equipamentos", JOptionPane.OK_OPTION);
            comboMarca.requestFocus();
            return (false);
        }

        if (comboModelo.getSelectedItem() == null){
            JOptionPane.showMessageDialog(null, "Modelo deve ser selecionado" ,
                    "Cadastro de Equipamentos", JOptionPane.OK_OPTION);
            comboModelo.requestFocus();
            return (false);
        }

        if (comboTipo.getSelectedItem() == null){
            JOptionPane.showMessageDialog(null, "Tipo deve ser selecionado" ,
                    "Cadastro de Equipamentos", JOptionPane.OK_OPTION);
            comboTipo.requestFocus();
            return (false);
        }

        if (jtfDescricao.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Descrição não pode ser vazia" ,
                    "Cadastro de Equipamentos", JOptionPane.OK_OPTION);
            jtfDescricao.requestFocus();
            return (false);
        }

        if (jtfCodPatrimonio.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Patrimonio não pode ser vazio" ,
                    "Cadastro de Equipamentos", JOptionPane.OK_OPTION);
            jtfCodPatrimonio.requestFocus();
            return (false);
        }

        if (jtfRfid.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Rfid não pode ser vazia" ,
                    "Cadastro de Equipamentos", JOptionPane.OK_OPTION);
            jtfRfid.requestFocus();
            return (false);
        }
        /*
        if (jtfTempoUso.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Tempo de uso não pode ser vazio" ,
                    "Cadastro de Equipamentos", JOptionPane.OK_OPTION);
            jtfTempoUso.requestFocus();
            return (false);
        }*/
        return (true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnIncluir;
    private javax.swing.JButton btnMarca;
    private javax.swing.JButton btnModelo;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnTipo;
    private javax.swing.JComboBox comboMarca;
    private javax.swing.JComboBox comboModelo;
    private javax.swing.JComboBox comboTipo;
    private javax.swing.JComboBox comboUltimaFalha;
    private javax.swing.JComboBox comboUltimaManutencao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jtfCodPatrimonio;
    private javax.swing.JTextField jtfCodigo;
    private javax.swing.JTextField jtfDescricao;
    private javax.swing.JTextField jtfMarca;
    private javax.swing.JTextField jtfModelo;
    private javax.swing.JTextField jtfRfid;
    private javax.swing.JTextField jtfTempoUso;
    private javax.swing.JTextField jtfTipo;
    private javax.swing.JPanel tabCadastro;
    private javax.swing.JPanel tabConsulta;
    private javax.swing.JTable tabela;
    // End of variables declaration//GEN-END:variables

}