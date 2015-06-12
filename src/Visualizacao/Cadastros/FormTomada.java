/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FormTomada.java
 *
 * Created on Nov 4, 2011, 8:12:28 AM
 *
 * @author rebonatto
 */

package Visualizacao.Cadastros;

import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import modelo.SalaCirurgia;
import modelo.Tomada;
import persistencia.SalaCirurgiaDAO;
import persistencia.TomadaDAO;

/**
 *
 * @author cliente
 */
public class FormTomada extends javax.swing.JDialog {
    private Tomada bean = new Tomada();
    private TomadaDAO dao = new TomadaDAO();
    private SalaCirurgiaDAO saladao = new SalaCirurgiaDAO();
    private boolean editando;
    private char acao;
    Vector titulos = new Vector();
    Vector linhas = new Vector();

    /** Creates new form FormEquipamento */
    public FormTomada(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        //preenche o combobox
        comboSala.removeAllItems();
        for (SalaCirurgia sla : saladao.lista()) {
            comboSala.addItem((SalaCirurgia) sla);
        }

        setTitle("Cadastro de Tomadas");
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
        comboSala = new javax.swing.JComboBox();
        btnSala = new javax.swing.JButton();
        jtfSala = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jtfIndice = new javax.swing.JTextField();
        jtfModulo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
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
        tabCadastro.add(btnIncluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, -1, -1));

        btnAlterar.setText("Alterar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });
        tabCadastro.add(btnAlterar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 260, -1, -1));

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        tabCadastro.add(btnExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 260, -1, -1));

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        tabCadastro.add(btnSalvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 260, -1, -1));

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        tabCadastro.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 260, -1, -1));

        jLabel3.setText("Sala:");
        tabCadastro.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 89, 39, -1));

        comboSala.setEditable(true);
        comboSala.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboSalaActionPerformed(evt);
            }
        });
        tabCadastro.add(comboSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 84, 234, -1));

        btnSala.setText("Sala");
        btnSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalaActionPerformed(evt);
            }
        });
        tabCadastro.add(btnSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 83, -1, -1));

        jtfSala.setEditable(false);
        jtfSala.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfSalaFocusLost(evt);
            }
        });
        tabCadastro.add(jtfSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 84, 62, -1));

        jLabel4.setText("Índice:");
        tabCadastro.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        jtfIndice.setEditable(false);
        tabCadastro.add(jtfIndice, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 100, -1));

        jtfModulo.setEditable(false);
        tabCadastro.add(jtfModulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 92, -1));

        jLabel5.setText("Módulo: ");
        tabCadastro.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));

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
                .addContainerGap(77, Short.MAX_VALUE))
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

    private void tabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseClicked
        // TODO add your handling code here:
        int linha = tabela.getSelectedRow();
        int coluna = 0;

        bean.setCodTomada((Integer) tabela.getValueAt(linha, coluna++));
        bean.setDesc((String) tabela.getValueAt(linha, coluna++));
        bean.setCodSala((SalaCirurgia) tabela.getValueAt(linha, coluna++));
        bean.setIndice((Integer) tabela.getValueAt(linha, coluna++));
        bean.setModulo((Integer) tabela.getValueAt(linha, coluna++));

        atualizaForm();
}//GEN-LAST:event_tabelaMouseClicked

    private void jtfSalaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfSalaFocusLost
        // TODO add your handling code here:
        if (jtfSala.getText().isEmpty()){
            comboSala.setSelectedIndex(-1);
            return;
        }
        int codSala = Integer.parseInt(jtfSala.getText());
        SalaCirurgia sla = saladao.localiza(codSala);
        if (sla == null) {
            JOptionPane.showMessageDialog(null, "Sala não encontrada");
            if(comboSala.getSelectedIndex() != -1){
                sla = (SalaCirurgia) comboSala.getSelectedItem();
                jtfSala.setText(String.valueOf(sla.getCodSala()));
            }
        } else {
            comboSala.setSelectedItem((SalaCirurgia) sla);
        }
    }//GEN-LAST:event_jtfSalaFocusLost

    private void btnSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalaActionPerformed
        // TODO add your handling code here:
        FormMarca painel = new FormMarca(new JFrame(), true);
        painel.setVisible(true);
        comboSala.removeAllItems();
        for (SalaCirurgia sla : saladao.lista()) {
            comboSala.addItem(sla);
        }
    }//GEN-LAST:event_btnSalaActionPerformed

    private void comboSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboSalaActionPerformed
        // TODO add your handing code here:
        if (comboSala.getSelectedIndex() != -1) {
            SalaCirurgia sla = (SalaCirurgia) comboSala.getSelectedItem();
            jtfSala.setText(String.valueOf(sla.getCodSala()));
        }
    }//GEN-LAST:event_comboSalaActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        editando = false;
        atualizaForm();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // TODO add your handling code here:
        if (! ValidaCampos())
        return;

        if (acao == 'I') { // Inclusão
            atualizaBean(false);
            if (dao.Insere(bean)) {
                bean.setCodTomada(dao.ultimaChave());
                jtfCodigo.setText(String.valueOf(bean.getCodTomada()));
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

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // TODO add your handling code here:
        atualizaBean(true);
        if (JOptionPane.showConfirmDialog(null, "Confirma exclusão",
            "Cadastro de Tomadas", JOptionPane.YES_OPTION,
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

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        // TODO add your handling code here:
        editando = true;
        atualizaBotoes();
        acao = 'A';
        jtfDescricao.requestFocus();
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluirActionPerformed
        // TODO add your handling code here:
        editando = true;
        atualizaBotoes();
        limpaCampos();
        acao = 'I';
        jtfDescricao.requestFocus();
    }//GEN-LAST:event_btnIncluirActionPerformed

    
    private void tabelaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelaKeyReleased
        // TODO add your handling code here:
        if (tabela.getSelectedRow() != -1) {         
            System.out.println(tabela.getSelectedRow());
            this.tabelaMouseClicked(null);
        }
    }//GEN-LAST:event_tabelaKeyReleased
    

//  public void stateChanged(ChangeEvent evt)  
//  {  
//         System.out.println(jTabbedPane1.getSelectedIndex());
//       if (jTabbedPane1.getSelectedIndex() == 1) // Esta na aba Consulta
//          tabela.requestFocus();
//  }   
//    
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                FormTomada dialog = new FormTomada(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
        
 
        
//      ChangeListener changeListener = new ChangeListener() {
//      public void stateChanged(ChangeEvent changeEvent) {
//          JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
//          int index = sourceTabbedPane.getSelectedIndex();
//          System.out.println("Tab changed to: " + sourceTabbedPane.getTitleAt(index));
//      }
//    };
    
    }

    private int montaTabela() {
        //Ajusta titulos da tabela
        titulos.add("Código");
        titulos.add("Descricao");
        titulos.add("Cod Sala");        
        titulos.add("Índice");
        titulos.add("Modulo");

        // inicializa os dados da tabela
        for (Tomada p : dao.lista()) {
            addTabela(p);
        }

        tabela.setModel(new DefaultTableModel(linhas, titulos));

        return (linhas.size());
    }

    private void addTabela(Tomada p) {
        Vector colunas = new Vector();

        colunas.add(p.getCodTomada());
        colunas.add(p.getDesc());    // vai adicionar o objeto e
        colunas.add(p.getCodSala());
        colunas.add(p.getIndice());
        colunas.add(p.getModulo());

        linhas.add(colunas);
    }

    private void updateTabela(Tomada p) {
        for (int i = 0; i < linhas.size(); i++) {
            // Achou o mesmo código
            if (p.getCodTomada()== (Integer) tabela.getValueAt(i, 0)) {
                // adiciona o objeto e mostra o toString()
                tabela.setValueAt(p.getDesc(), i, 1);
                tabela.setValueAt(p.getCodSala(), i, 2);               
                tabela.setValueAt(p.getIndice(), i, 3);
                tabela.setValueAt(p.getModulo(), i, 4);

                break;
            }
        }
        tabela.revalidate();
    }

    private void removeTabela(Tomada p) {
        for (int i = 0; i < linhas.size(); i++) {
            // Achou o mesmo código
            if (p.getCodTomada() == (Integer) tabela.getValueAt(i, 0)) {
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
        jtfCodigo.setText(String.valueOf(bean.getCodTomada()));
        jtfDescricao.setText(bean.getDesc());
        comboSala.setSelectedItem((SalaCirurgia) bean.getCodSala());
        jtfSala.setText(String.valueOf(bean.getCodSala().getCodSala()));
        jtfIndice.setText(String.valueOf(bean.getIndice()));
        jtfModulo.setText(String.valueOf(bean.getModulo()));
    }

    private void atualizaBotoes() {
        //atualiza os campos de edicao
        jtfDescricao.setEditable(editando);
        comboSala.setEnabled(editando);
        jtfSala.setEditable(editando);
        jtfIndice.setEditable(editando);
        jtfModulo.setEditable(editando);

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
        comboSala.setSelectedIndex(-1); //não selecionado
        jtfSala.setText("");
        jtfIndice.setText("");
        jtfModulo.setText("");
    }

    private void atualizaBean(boolean codigo) {
        // Atualiza os campos de edição
        if (codigo) {
            bean.setCodTomada(Integer.parseInt(jtfCodigo.getText()));
        }
        bean.setDesc(jtfDescricao.getText());
        bean.setCodSala((SalaCirurgia) comboSala.getSelectedItem());
        bean.setIndice(Integer.parseInt(jtfIndice.getText()));
        bean.setModulo(Integer.parseInt(jtfModulo.getText()));
    }

    private void atualizaFormTabela() {
        int linha = tabela.getRowCount() - 1;
        int coluna = 0;        
        
        bean.setCodTomada((Integer) tabela.getValueAt(linha, coluna++));
        bean.setDesc((String) tabela.getValueAt(linha, coluna++));
        bean.setCodSala((SalaCirurgia) tabela.getValueAt(linha, coluna++));       
        bean.setIndice((Integer) tabela.getValueAt(linha, coluna++));
        bean.setModulo((Integer) tabela.getValueAt(linha, coluna++));
        
        atualizaForm();
    }

    private boolean ValidaCampos(){
        if (comboSala.getSelectedItem() == null){
            JOptionPane.showMessageDialog(null, "Sala deve ser selecionada" ,
                    "Cadastro de Equipamentos", JOptionPane.OK_OPTION);
            comboSala.requestFocus();
            return (false);
        }

        if (jtfDescricao.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Descrição não pode ser vazia" ,
                    "Cadastro de Equipamentos", JOptionPane.OK_OPTION);
            jtfDescricao.requestFocus();
            return (false);
        }

        if (jtfModulo.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Patrimonio não pode ser vazio" ,
                    "Cadastro de Equipamentos", JOptionPane.OK_OPTION);
            jtfModulo.requestFocus();
            return (false);
        }

        if (jtfIndice.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Rfid não pode ser vazia" ,
                    "Cadastro de Equipamentos", JOptionPane.OK_OPTION);
            jtfIndice.requestFocus();
            return (false);
        }
        
        return (true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnIncluir;
    private javax.swing.JButton btnSala;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox comboSala;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jtfCodigo;
    private javax.swing.JTextField jtfDescricao;
    private javax.swing.JTextField jtfIndice;
    private javax.swing.JTextField jtfModulo;
    private javax.swing.JTextField jtfSala;
    private javax.swing.JPanel tabCadastro;
    private javax.swing.JPanel tabConsulta;
    private javax.swing.JTable tabela;
    // End of variables declaration//GEN-END:variables

}
