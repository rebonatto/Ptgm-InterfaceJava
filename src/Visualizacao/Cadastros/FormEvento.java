/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FormEvento.java
 *
 * Created on Nov 3, 2011, 10:35:44 PM
 */

package Visualizacao.Cadastros;

import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import modelo.Evento;
import persistencia.EventoDAO;

/**
 *
 * @author rebonatto
 */

public class FormEvento extends javax.swing.JDialog {
    private Evento bean = new Evento();
    private EventoDAO dao = new EventoDAO();
    private boolean editando;
    private char acao;
    Vector titulos = new Vector();
    Vector linhas  = new Vector();

    /** Creates new form FormMarca */
    public FormEvento(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        setTitle("Cadastro de Eventos");
        if (montaTabela() == 0){
            JOptionPane.showMessageDialog(this, "Tabela Vazia");
            limpaCampos();
        }
        else{
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

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormEvento dialog = new FormEvento(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    private int montaTabela(){
        //Ajusta titulos da tabela
        titulos.add("Código");
        titulos.add("Descricao");
        titulos.add("Forma de Onda");

        // inicializa os dados da tabela
        for(Evento e: dao.lista()){
            addTabela(e);
        }

        tabela.setModel(new DefaultTableModel(linhas, titulos));

        return (linhas.size());
    }

    private void addTabela(Evento e){
        Vector colunas = new Vector();

        colunas.add(e.getCodEvento());
        colunas.add(e.getDesc());
        colunas.add(e.getMontaFO());

        linhas.add(colunas);
    }

    private void updateTabela(Evento e){
        for(int i=0; i < linhas.size(); i++){
            // Achou o mesmo código
            if (e.getCodEvento()== (Integer) tabela.getValueAt(i, 0)){
                tabela.setValueAt(e.getDesc(), i, 1);
                tabela.setValueAt(e.getMontaFO(), i, 2);

                break;
            }
        }
        tabela.revalidate();
    }

    private void removeTabela(Evento e){
        for(int i=0; i < linhas.size(); i++){
            // Achou o mesmo código
            if (e.getCodEvento()== (Integer) tabela.getValueAt(i, 0)){
                linhas.remove(i);
                break;
            }
        }
        tabela.revalidate();
        if (linhas.size() == 0){
            JOptionPane.showMessageDialog(this, "Tabela Vazia");
            limpaCampos();
        }
        else
            atualizaFormTabela();
    }

    private void atualizaForm(){
        atualizaBotoes();
        atualizaCampos();
    }

    private void atualizaCampos(){
        // Atualiza os campos de edição
        jtfCodigo.setText(String.valueOf(bean.getCodEvento()));
        jtfDescricao.setText(bean.getDesc());
        radioSim.setSelected(bean.getFormaOnda() == 1);
        radioNao.setSelected(bean.getFormaOnda() != 1);
    }

    private void atualizaBotoes(){
        //atualiza os campos de edicao
        jtfDescricao.setEditable(editando);
        radioSim.setEnabled(editando);
        radioNao.setEnabled(editando);

        // atualiza os botões
        btnAlterar.setEnabled(! editando);
        btnIncluir.setEnabled(! editando);
        btnExcluir.setEnabled(! editando);
        btnSalvar.setEnabled(editando);
        btnCancelar.setEnabled(editando);
    }

    private void limpaCampos(){
        // Atualiza os campos de edição
        jtfCodigo.setText("");
        jtfDescricao.setText("");
        radioNao.setSelected(true);

    }

    private void atualizaBean(boolean codigo){
        // Atualiza os campos de edição
        if (codigo)
            bean.setCodEvento(Integer.parseInt(jtfCodigo.getText()));
        bean.setDesc(jtfDescricao.getText());
        bean.setFormaOnda(radioSim.isSelected() ? 1 : 0);
    }

    private void atualizaFormTabela(){
        int linha = tabela.getRowCount()-1;
        int coluna = 0;

        bean.setCodEvento((Integer) tabela.getValueAt(linha, coluna++));
        bean.setDesc((String) tabela.getValueAt(linha, coluna++));
        bean.setFormaOnda( ( (String) tabela.getValueAt(linha, coluna++)).equals("Sim") ? 1 : 0);

        atualizaForm();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoRadio = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtfCodigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jtfDescricao = new javax.swing.JTextField();
        btnIncluir = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        radioSim = new javax.swing.JRadioButton();
        radioNao = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Código:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 43, -1, -1));

        jtfCodigo.setEditable(false);
        jPanel1.add(jtfCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(97, 33, 84, -1));

        jLabel2.setText("Descrição: ");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 80, -1, -1));

        jtfDescricao.setEditable(false);
        jPanel1.add(jtfDescricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 75, 310, -1));

        btnIncluir.setText("Incluir");
        btnIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluirActionPerformed(evt);
            }
        });
        jPanel1.add(btnIncluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 180, -1, -1));

        btnAlterar.setText("Alterar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });
        jPanel1.add(btnAlterar, new org.netbeans.lib.awtextra.AbsoluteConstraints(109, 180, -1, -1));

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        jPanel1.add(btnExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(187, 180, -1, -1));

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        jPanel1.add(btnSalvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(264, 180, -1, -1));

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(335, 180, -1, -1));

        jLabel3.setText("F. Onda:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 124, -1, -1));

        grupoRadio.add(radioSim);
        radioSim.setText("Sim");
        jPanel1.add(radioSim, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 120, -1, -1));

        grupoRadio.add(radioNao);
        radioNao.setText("Não");
        jPanel1.add(radioNao, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 120, -1, -1));

        jTabbedPane1.addTab("Cadastro", jPanel1);

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Consulta", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
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
                "Cadastro de Eventos", JOptionPane.YES_OPTION,
                JOptionPane.NO_OPTION) == JOptionPane.YES_OPTION)
            if (dao.exclui(bean)){
                JOptionPane.showMessageDialog(null, "Excluido com sucesso");
                removeTabela(bean);
            } else
                JOptionPane.showMessageDialog(null, dao.getMensagem(),
                        "Problemas ao Excluir", JOptionPane.ERROR_MESSAGE);
}//GEN-LAST:event_btnExcluirActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // TODO add your handling code here:

        if (acao == 'I'){ // Inclusão
            atualizaBean(false);
            if (dao.Insere(bean)){
                bean.setCodEvento(dao.ultimaChave());
                jtfCodigo.setText(String.valueOf(bean.getCodEvento()));
                JOptionPane.showMessageDialog(null, "Incluido com sucesso");
                addTabela(bean);
                //para atualizar a tabela
                tabela.revalidate();
            } else
                JOptionPane.showMessageDialog(null, dao.getMensagem(),
                        "Problemas ao incluir", JOptionPane.ERROR_MESSAGE);
        } else{
            atualizaBean(true);
            if (dao.Altera(bean)){
                JOptionPane.showMessageDialog(null, "Alterado com sucesso");
                //para atualizar a tabela. Já chama o revalidate
                updateTabela(bean);
            } else
                JOptionPane.showMessageDialog(null, dao.getMensagem(),
                        "Problemas ao Alterar", JOptionPane.ERROR_MESSAGE);
        }
        editando = false;
        atualizaBotoes();
}//GEN-LAST:event_btnSalvarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        editando = false;
        atualizaForm();
}//GEN-LAST:event_btnCancelarActionPerformed

    private void tabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseClicked
        // TODO add your handling code here:
        int linha = tabela.getSelectedRow();
        int coluna = 0;

        bean.setCodEvento((Integer) tabela.getValueAt(linha, coluna++));
        bean.setDesc((String) tabela.getValueAt(linha, coluna++));
        bean.setFormaOnda( ( (String) tabela.getValueAt(linha, coluna++)).equals("Sim") ? 1 : 0);
        atualizaForm();
}//GEN-LAST:event_tabelaMouseClicked

    private void tabelaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelaKeyReleased
        // TODO add your handling code here:
        if (tabela.getSelectedRow() != -1) {         
            // System.out.println(tabela.getSelectedRow());
            this.tabelaMouseClicked(null);
        }        
    }//GEN-LAST:event_tabelaKeyReleased

    /**
    * @param args the command line arguments
    */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnIncluir;
    private javax.swing.JButton btnSalvar;
    private javax.swing.ButtonGroup grupoRadio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jtfCodigo;
    private javax.swing.JTextField jtfDescricao;
    private javax.swing.JRadioButton radioNao;
    private javax.swing.JRadioButton radioSim;
    private javax.swing.JTable tabela;
    // End of variables declaration//GEN-END:variables

}
