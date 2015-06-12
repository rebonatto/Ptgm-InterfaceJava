/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Evento;


/**
 *
 * @author Eduardo
 * Modified by: rebonatto
 */
public class EventoDAO {

    private String mensagem;

    public boolean Insere (Evento evt){
        String sql = "insert into eventos ( `desc`, `formaonda`) values ( ?, ? )";

        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        try {            
            //ps.setInt(1, evt.getCodEvento());
            ps.setString(1, evt.getDesc());
            ps.setInt(2, evt.getFormaOnda());

            ps.execute();
            setMensagem("Inseriu Evento com sucesso");
            return true;
        } catch (SQLException e) {
            setMensagem("Problemas ao inserir Eventos" + e.getMessage());
        }finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                setMensagem("Problemas ao Fechar PrepareStatement " + ex.getMessage());
            }
        }
        return false;
    }

     public boolean Altera (Evento evt){

         String sql = "update eventos set "+
                      "desc = ?, formaonda = ? "
                    + "where codevento = ? ";

         PreparedStatement ps = Conexao.getPreparedStatement(sql);
         
         try {                       
            ps.setString(1, evt.getDesc());
            ps.setInt(2, evt.getFormaOnda());
            
            ps.setInt(3, evt.getCodEvento());

            ps.execute();
            setMensagem("Alterou Evento com sucesso");
            return true;
        } catch (SQLException e) {
            setMensagem("Problemas ao inserir Eventos" + e.getMessage());
        }finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                setMensagem("Problemas ao Fechar PrepareStatement " + ex.getMessage());
            }
        }
        return false;
     }

     public boolean exclui (Evento evt) {
        // Preparação do SQL para interagir com o banco
        String sql = "delete from eventos where codEvento = ?";

        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        
        try {            
            ps.setInt(1, evt.getCodEvento());

            if (ps.executeUpdate() > 0){
                setMensagem("Excluiu Evento com sucesso") ;
                return true;
            }

            setMensagem("Não encontrou Evento");
        }catch (SQLException e){
           setMensagem("Problemas ao excluir Evento" + e.getMessage());
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
    public Evento localiza(int codEvento){
        String sql = "select * from eventos " +
                     "where codEvento = ?";

        PreparedStatement ps = Conexao.getPreparedStatement(sql);

        try {
            ps.setInt(1, codEvento);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                Evento evt = new Evento();
                evt.setCodEvento(rs.getInt("codEvento"));
                evt.setDesc(rs.getString("desc"));
                evt.setFormaOnda(rs.getInt("FormaOnda"));
                setMensagem("evento localizado com sucesso");
                return (evt);
            }
            setMensagem("Evento não encontrado");          
        } catch (SQLException ex) {
            setMensagem("Problemas ao localizar Evento. Erro de SQL " + ex.getMessage());
        }finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                setMensagem("Problemas ao Fechar PrepareStatement " + ex.getMessage());
            }
        }

        return (null);
    }

    public ArrayList<Evento> lista(){
        String sql = "select * from eventos ";

        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        ArrayList <Evento> ls = new ArrayList<Evento>();

        try {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Evento evt = new Evento();
                evt.setCodEvento(rs.getInt("codEvento"));
                evt.setDesc(rs.getString("desc"));
                evt.setFormaOnda(rs.getInt("FormaOnda"));
                ls.add(evt);
            }
            if (ls.isEmpty()){
                setMensagem("Relação de Eventos vazia");
                return (ls);
            }
            setMensagem("Relação de Eventos gerada com sucesso");
            return (ls);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            setMensagem("Problemas ao gerar relação de Eventos. Erro de SQL " + ex.getMessage());
        } finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                setMensagem("Problemas ao Fechar PrepareStatement " + ex.getMessage());
            }
        }
        return (null);
    }

     public int ultimaChave(){
        String sql = "select LAST_INSERT_ID() as ultima from eventos ";

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
            setMensagem("Problemas ao localizar Chave em Evento. Erro de SQL " + ex.getMessage());
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
