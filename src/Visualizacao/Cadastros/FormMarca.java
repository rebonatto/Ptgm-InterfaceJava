/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FormMarca.java
 *
 * Created on Nov 3, 2011, 10:35:44 PM
 */

package Visualizacao.Cadastros;

import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import modelo.Marca;
import persistencia.MarcaDAO;

/**
 *
 * @author Eduardo
 * Modified by: rebonatto
 */

public class FormMarca extends javax.swing.JDialog {
    private Marca bean = new Marca();
    private MarcaDAO dao = new MarcaDAO();
    private boolean editando;
    private char acao;
    Vector titulos = new Vector();
    Vector linhas  = new Vector();

    /** Creates new form FormMarca */
    public FormMarca(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        setTitle("Cadastro de Marcas");
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
                FormMarca dialog = new FormMarca(new javax.swing.JFrame(), true);
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

        // inicializa os dados da tabela
        for(Marca c: dao.lista()){
            addTabela(c);
        }

        tabela.setModel(new DefaultTableModel(linhas, titulos));

        return (linhas.size());
    }

    private void addTabela(Marca c){
        Vector colunas = new Vector();

        colunas.add(c.getCodMarca());
        colunas.add(c.getDesc());

        linhas.add(colunas);
    }

    private void updateTabela(Marca c){
        for(int i=0; i < linhas.size(); i++){
            // Achou o mesmo código
            if (c.getCodMarca() == (Integer) tabela.getValueAt(i, 0)){
                tabela.setValueAt(c.getDesc(), i, 1);

                break;
            }
        }
        tabela.revalidate();
    }

    private void removeTabela(Marca c){
        for(int i=0; i < linhas.size(); i++){
            // Achou o mesmo código
            if (c.getCodMarca() == (Integer) tabela.getValueAt(i, 0)){
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
        jtfCodigo.setText(String.valueOf(bean.getCodMarca()));
        jtfDescricao.setText(bean.getDesc());

    }

    private void atualizaBotoes(){
        //atualiza os campos de edicao
        jtfDescricao.setEditable(editando);


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

    }

    private void atualizaBean(boolean codigo){
        // Atualiza os campos de edição
        if (codigo)
            bean.setCodMarca(Integer.parseInt(jtfCodigo.getText()));
        bean.setDesc(jtfDescricao.getText());

    }

    private void atualizaFormTabela(){
        int linha = tabela.getRowCount()-1;
        int coluna = 0;

        bean.setCodMarca((Integer) tabela.getValueAt(linha, coluna++));
        bean.setDesc((String) tabela.getValueAt(linha, coluna));

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
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Código:");

        jtfCodigo.setEditable(false);

        jLabel2.setText("Descrição: ");

        jtfDescricao.setEditable(false);

        btnIncluir.setText("Incluir");
        btnIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluirActionPerformed(evt);
            }
        });

        btnAlterar.setText("Alterar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 469, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(19, 19, 19)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2))
                    .addGap(2, 2, 2)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jtfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jtfDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(189, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(36, 36, 36)
                    .addComponent(btnIncluir)
                    .addGap(18, 18, 18)
                    .addComponent(btnAlterar)
                    .addGap(18, 18, 18)
                    .addComponent(btnExcluir)
                    .addGap(18, 18, 18)
                    .addComponent(btnSalvar)
                    .addGap(18, 18, 18)
                    .addComponent(btnCancelar)
                    .addContainerGap(61, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 288, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(33, 33, 33)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel1)
                        .addComponent(jtfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(29, 29, 29)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jtfDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(172, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(137, 137, 137)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnIncluir)
                        .addComponent(btnAlterar)
                        .addComponent(btnExcluir)
                        .addComponent(btnSalvar)
                        .addComponent(btnCancelar))
                    .addContainerGap(122, Short.MAX_VALUE)))
        );

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
                "Cadastro de Marcas", JOptionPane.YES_OPTION,
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
                bean.setCodMarca(dao.ultimaChave());
                jtfCodigo.setText(String.valueOf(bean.getCodMarca()));
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

        bean.setCodMarca((Integer) tabela.getValueAt(linha, coluna++));
        bean.setDesc((String) tabela.getValueAt(linha, coluna));
        atualizaForm();
}//GEN-LAST:event_tabelaMouseClicked

    private void tabelaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelaKeyReleased
        // TODO add your handling code here:
        if (tabela.getSelectedRow() != -1) {         
            System.out.println(tabela.getSelectedRow());
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jtfCodigo;
    private javax.swing.JTextField jtfDescricao;
    private javax.swing.JTable tabela;
    // End of variables declaration//GEN-END:variables

}
