/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import modelo.Tomada;

/**
 *
 * @author Eduardo
 * Modified by: rebonatto
 */

public class TomadaDAO {

    private String mensagem;
    private SalaCirurgiaDAO sl = new SalaCirurgiaDAO();

    public boolean Insere(Tomada t) {
        String sql = "insert into tomada "
                + "(`codSala`, `indice`, `modulo`, `desc`) "
                + "values (?, ?, ?, ?)";
        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        try {            
            ps.setInt(1, t.getCodSala().getCodSala());
            ps.setInt(2, t.getIndice());
            ps.setInt(3, t.getModulo());
            ps.setString(4, t.getDesc());

            ps.execute();
            setMensagem("Inseriu UsoSala com sucesso");
            return true;
        } catch (SQLException e) {
            setMensagem("Problemas ao inserir Sala" + e.getMessage());
        }finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                setMensagem("Problemas ao Fechar PrepareStatement " + ex.getMessage());
            }
        }
        return false;
    }

    public boolean altera(Tomada t) {

        String sql = "update tomada set "
                + "`codSala`            = ?,"
                + "`indice`              = ?,"
                + "`modulo`                 = ?,"
                + "`desc`              = ?"
                + "where `codTomada`     =?";

        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        try {
            ps.setInt(1, t.getCodSala().getCodSala());
            ps.setInt(2, t.getIndice());
            ps.setInt(3, t.getModulo());
            ps.setString(4, t.getDesc());
            ps.setInt(5, t.getCodTomada());

            if (ps.executeUpdate() > 0) {
                setMensagem("Alterou usalaamento com sucesso");
                return true;
            }
            setMensagem("Não encontrou usalaamento");
        } catch (SQLException e) {
            setMensagem("Problemas ao alterar usalaamento" + e.getMessage());
        }finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                setMensagem("Problemas ao Fechar PrepareStatement " + ex.getMessage());
            }
        }
        return false;
    }

    public boolean exclui(Tomada t) {
        // Preparação do SQL para interagir com o banco
        String sql = "delete from tomada "
                + "where codTomada = ?";
        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        try {
            ps.setInt(1, t.getCodTomada());

            if (ps.executeUpdate() > 0) {
                setMensagem("Excluiu usalaamento com sucesso");
                return true;
            }

            setMensagem("Não encontrou usalaamento");
        } catch (SQLException e) {
            setMensagem("Problemas ao excluir usalaamento" + e.getMessage());
        }finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                setMensagem("Problemas ao Fechar PrepareStatement " + ex.getMessage());
            }
        }
        return false;
    }

    public Tomada localiza(int codTomada) {
        String sql = "select * from tomada "
                + "where codTomada = ? ";

        Tomada t = new Tomada();        
        
        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        try {           
            ps.setInt(1, codTomada);

            ResultSet rs = ps.executeQuery();
            if (rs.next() == true) {
                // mapear os dados recuperados do BD para o Objeto
                t.setCodTomada(rs.getInt("codTomada"));
                t.setCodSala(sl.localiza(rs.getInt("codSala")));
                t.setIndice(rs.getInt("Indice"));
                t.setModulo(rs.getInt("modulo"));
                t.setDesc(rs.getString("desc"));

                mensagem = "usoSala localizado";
                return t;
            }
            mensagem = "Não localizado !";
        } catch (SQLException e) {
            mensagem = "Erro de SQL: " + e.getMessage();
        }finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                setMensagem("Problemas ao Fechar PrepareStatement " + ex.getMessage());
            }
        }

        return null;
    }

    public Collection<Tomada> lista() {
        String sql = "select * from tomada";

        // Ajuste da coleção de objetos
        Collection<Tomada> lista = new ArrayList();
        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        
        try {            
            ResultSet rs = ps.executeQuery();
            while (rs.next() == true) {

                Tomada t = new Tomada();
                SalaCirurgiaDAO sala = new SalaCirurgiaDAO();

                // mapear os dados recuperados do BD para o Objeto
                t.setCodTomada(rs.getInt("codTomada"));
                t.setCodSala(sala.localiza(rs.getInt("codSala")));
                t.setIndice(rs.getInt("indice"));
                t.setModulo(rs.getInt("modulo"));
                t.setDesc(rs.getString("desc"));

                lista.add(t);
            }
            if (!lista.isEmpty()) {
                setMensagem("Relação de equipamentos gerada com sucesso");
                return lista;
            }
            setMensagem("Não existem registros para a pesquisa");
        } catch (SQLException e) {
            mensagem = "Erro de SQL: " + e.getMessage();
        }finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                setMensagem("Problemas ao Fechar PrepareStatement " + ex.getMessage());
            }
        }
        return lista;
    }

    public int ultimaChave() {
        String sql = "select LAST_INSERT_ID() as ultima from tomada ";

        PreparedStatement ps = Conexao.getPreparedStatement(sql);

        try {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int ret = rs.getInt("ultima");
                setMensagem("Chave localizada com sucesso");
                return (ret);
            }
            setMensagem("Chave não localizada");
        } catch (SQLException ex) {
            setMensagem("Problemas ao localizar Chave em usosala. Erro de SQL " + ex.getMessage());
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
