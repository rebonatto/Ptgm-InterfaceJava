/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Marca;


/**
 *
 * @author Eduardo
 * Modified by: rebonatto
 */
public class MarcaDAO{

    private String mensagem;
//com auto increment
    public boolean Insere (Marca mca){
        String sql = "insert into marca (`desc`) values (?)";
//        INSERT INTO `tcc`.`marca`     (`codMarca`, `desc`) VALUES (4, 'Oi');
        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        try {
            //System.out.println("Pegou conexao 2");

            //ps.setInt(1, mca.getCodMarca());
            ps.setString(1, mca.getDesc());
            
            int x = ps.executeUpdate();
            if (x > 0){
                setMensagem("Inseriu Marca com sucesso");
                return true;
            }
        } catch (SQLException e) {
            setMensagem("Problemas ao inserir marca" + e.getMessage());
        }finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                setMensagem("Problemas ao Fechar PrepareStatement " + ex.getMessage());
            }
        }
        return false;
    }    

     public boolean Altera (Marca mca){

         String sql = "update marca set "+
                      "`desc` = ?" +
                      "where `codMarca` = ? ";
         
         PreparedStatement ps = Conexao.getPreparedStatement(sql);
         try {           
            ps.setString(1, mca.getDesc());
            ps.setInt(2, mca.getCodMarca());

            ps.execute();
            setMensagem("Inseriu Marca com sucesso");
            return true;
        } catch (SQLException e) {
            setMensagem("Problemas ao alterar Marca\n" + e.getMessage());
        }finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                setMensagem("Problemas ao Fechar PrepareStatement " + ex.getMessage());
            }
        }
        return false;
     }

     public boolean exclui (Marca mca) {
        // Preparação do SQL para interagir com o banco
        String sql = "delete from marca where codMarca = ?";

        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        try {
            ps.setInt(1, mca.getCodMarca());

            if (ps.executeUpdate() > 0){
                setMensagem("Excluiu marca com sucesso") ;
                return true;
            }

            setMensagem("Não encontrou marca");
        }catch (SQLException e){
           setMensagem("Problemas ao excluir Marca" + e.getMessage());
       }finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                setMensagem("Problemas ao Fechar PrepareStatement " + ex.getMessage());
            }
        }
       return false;
    }

    // Receber por parametro a chave primária
    public Marca localiza(int codMarca){
        String sql = "select * from marca " +
                     "where codMarca = ?";

        PreparedStatement ps = Conexao.getPreparedStatement(sql);

        try {
            ps.setInt(1, codMarca);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                Marca mca = new Marca();
                mca.setCodMarca(rs.getInt("codMarca"));
                mca.setDesc(rs.getString("desc"));
                setMensagem("Marca localizado com sucesso");
                return (mca);
            }
            setMensagem("Marca não encontrado");
        } catch (SQLException ex) {
            setMensagem("Problemas ao localizar Marca. Erro de SQL " + ex.getMessage());
        }finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                setMensagem("Problemas ao Fechar PrepareStatement " + ex.getMessage());
            }
        }

        return (null);
    }

    public ArrayList<Marca> lista(){
        String sql = "select * from marca ";

        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        ArrayList <Marca> ls = new ArrayList<Marca>();

        try {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Marca mca = new Marca();
                mca.setCodMarca(rs.getInt("codMarca"));
                mca.setDesc(rs.getString("desc"));
                ls.add(mca);
            }
            if (ls.isEmpty()){
                setMensagem("Relação de Marca vazia");
                return (ls);
            }
            setMensagem("Relação de Marca gerada com sucesso");
            return (ls);
        } catch (SQLException ex) {
            setMensagem("Problemas ao gerar relação de Marca. Erro de SQL " + ex.getMessage());
        }finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                setMensagem("Problemas ao Fechar PrepareStatement " + ex.getMessage());
            }
        }
        return (null);
    }

     public int ultimaChave(){
        String sql = "select LAST_INSERT_ID() as ultima from marca ";

        PreparedStatement ps = Conexao.getPreparedStatement(sql);

        try {
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                int ret = rs.getInt("ultima");
                setMensagem("Chave localizada com sucesso");
                return (ret);
            }
            setMensagem("Chave não localizada");
        } catch (SQLException ex) {
            setMensagem("Problemas ao localizar Chave em Marca. Erro de SQL " + ex.getMessage());
        }finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                setMensagem("Problemas ao Fechar PrepareStatement " + ex.getMessage());
            }
        }
        return (0);
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}

