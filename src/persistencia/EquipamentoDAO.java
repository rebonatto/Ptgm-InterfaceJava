package persistencia;

import Uteis.Conversoes;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import modelo.Equipamento;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.CapturaAtual;
import modelo.Marca;
import modelo.Modelo;
import modelo.Tipo;

/*
 * @author Eduardo
 * Modified by: rebonatto
 */

public class EquipamentoDAO {

    private String      mensagem;    
    
    private MarcaDAO  mcadao = new MarcaDAO();
    private TipoDAO    tpdao = new TipoDAO();
    private ModeloDAO moddao = new ModeloDAO();

    public boolean Insere(Equipamento equip) {
        String sql = "insert into equipamento "
                + "(`codMarca`, `codModelo`, `codTipo`, `rfid`, `codPatrimonio`, "
                + " `tempoUso`, `desc`, `dataUltimaFalha`, `dataUltimaManutencao`)"
                + "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        
        try {            
            ps.setInt(1, equip.getCodMarca().getCodMarca());
            ps.setInt(2, equip.getCodModelo().getCodModelo());
            ps.setInt(3, equip.getCodTipo().getCodTipo());
            ps.setString(4, equip.getRfid());
            ps.setString(5, equip.getCodPatrimonio());
            ps.setInt(6, equip.getTempoUso());
            ps.setString(7, equip.getDesc());
            ps.setDate(8, (Date) Conversoes.CalendarToDate(equip.getDataUltimaFalha()));
            ps.setDate(9, (Date) Conversoes.CalendarToDate(equip.getDataUltimaManutencao()));

            ps.execute();
            setMensagem("Inseriu Equipamento com sucesso");
            return true;
        } catch (SQLException e) {
            setMensagem("Problemas ao inserir Equipamento" + e.getMessage());
        } finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                setMensagem("Problemas ao Fechar PrepareStatement " + ex.getMessage());
            }
        }
            
        return false;
    }

    public boolean altera(Equipamento equip) {

        String sql = "update equipamento set "
                + "`codMarca`              = ?, "
                + "`codModelo`             = ?, "
                + "`codTipo`               = ?, "
                + "`rfid`                   = ?, "
                + "`codPatrimonio`         = ?, "
                + "`tempoUso`              = ?, "
                + "`desc`                   = ?, "
                + "`dataUltimaFalha`      = ?, "
                + "`dataUltimaManutencao`  = ?"
                + "where `codEquip`        = ? ";
        
        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        
        try {            
            ps.setInt(1, equip.getCodMarca().getCodMarca());
            ps.setInt(2, equip.getCodModelo().getCodModelo());
            ps.setInt(3, equip.getCodTipo().getCodTipo());
            ps.setString(4, equip.getRfid());
            ps.setString(5, equip.getCodPatrimonio());
            ps.setInt(6, equip.getTempoUso());
            ps.setString(7, equip.getDesc());
            ps.setDate(8, (Date) Conversoes.CalendarToDate(equip.getDataUltimaFalha()));
            ps.setDate(9, (Date) Conversoes.CalendarToDate(equip.getDataUltimaManutencao()));
            ps.setInt(10, equip.getCodEquip());

            if (ps.executeUpdate() > 0) {
                setMensagem("Alterou Equipamento com sucesso");
                return true;
            }
            setMensagem("Não encontrou Equipamento");
        } catch (SQLException e) {
            setMensagem("Problemas ao alterar Equipamento" + e.getMessage());
        } finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                setMensagem("Problemas ao Fechar PrepareStatement " + ex.getMessage());
            }
        }        
        return false;
    }

    public boolean exclui(Equipamento equip) {
        // Preparação do SQL para interagir com o banco
        String sql = "delete from equipamento "
                + "where codEquip = ?";
        
        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        
        try {            
            ps.setInt(1, equip.getCodEquip());

            if (ps.executeUpdate() > 0) {
                setMensagem("Excluiu Equipamento com sucesso");
                return true;
            }

            setMensagem("Não encontrou Equipamento");
        } catch (SQLException e) {
            setMensagem("Problemas ao excluir Equipamento" + e.getMessage());
        } finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                setMensagem("Problemas ao Fechar PrepareStatement " + ex.getMessage());
            }
        }        
        return false;
    }
           
    public Equipamento localiza(int codEquip) {
        String sql = "select * from equipamento "
                + "where `codEquip` = ? ";

        Equipamento equip = new Equipamento();

        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        try {            
            ps.setInt(1, codEquip);

            ResultSet rs = ps.executeQuery();
            if (rs.next() == true) {
                // mapear os dados recuperados do BD para o Objeto
                equip.setCodEquip(rs.getInt("codEquip"));
                equip.setCodMarca(mcadao.localiza(rs.getInt("codMarca")));
                equip.setCodModelo(moddao.localiza(rs.getInt("codModelo")));
                equip.setCodTipo(tpdao.localiza(rs.getInt("codTipo")));
                equip.setRfid(rs.getString("Rfid"));
                equip.setCodPatrimonio(rs.getString("codPatrimonio"));
                equip.setTempoUso(rs.getInt("tempoUso"));
                equip.setDesc(rs.getString("desc"));

                java.sql.Date dt = new Date(0);
                dt = rs.getDate("dataUltimaFalha");
                equip.setDataUltimaFalha(Conversoes.DateToCalendar(dt));

                java.sql.Date dts = new Date(0);
                dt = rs.getDate("dataUltimaManutencao");
                equip.setDataUltimaManutencao(Conversoes.DateToCalendar(dt));                

                mensagem = "Equipamento localizado";
                return equip;
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
    
    
    private Collection<Equipamento> montaLista(PreparedStatement ps) throws Exception{        
        Collection<Equipamento> lista = new ArrayList();
         
         try {
            ResultSet rs = ps.executeQuery();
            while (rs.next() == true) {
                // definição do objeto
                Equipamento equip = new Equipamento();                

                // mapear os dados recuperados do BD para o Objeto
                equip.setCodEquip(rs.getInt("codEquip"));
                equip.setCodMarca(mcadao.localiza(rs.getInt("codMarca")));                
                equip.setCodModelo(moddao.localiza(rs.getInt("codModelo")));
                equip.setCodTipo(tpdao.localiza(rs.getInt("codTipo")));
                equip.setRfid(rs.getString("rfid"));
                equip.setCodPatrimonio(rs.getString("codPatrimonio"));
                equip.setTempoUso(rs.getInt("tempoUso"));
                equip.setDesc(rs.getString("desc"));

                java.sql.Date dt = new Date(0);
                dt = rs.getDate("dataUltimaFalha");
                equip.setDataUltimaFalha(Conversoes.DateToCalendar(dt));


                dt = rs.getDate("dataUltimaManutencao");
                equip.setDataUltimaManutencao(Conversoes.DateToCalendar(dt));
                

                lista.add(equip);
            }
            if (!lista.isEmpty()) {
                setMensagem("Relação de equipamentos gerada com sucesso");
                return lista;
            }
            setMensagem("Não existem registros para a pesquisa");
        } catch (SQLException e) {
             mensagem = "Erro de SQL: " + e.getMessage();
            throw e;
        }
         return lista;
    }

    public Collection<Equipamento> lista() {
        String sql = "select * from equipamento";

        // Ajuste da coleção de objetos
        Collection<Equipamento> lista = new ArrayList();

        try {
            PreparedStatement ps = Conexao.getPreparedStatement(sql);            

            lista = montaLista(ps); 
            if (!lista.isEmpty()) {
                setMensagem("Relação de equipamentos gerada com sucesso");
                return lista;
            }            
        } catch (Exception e) {
            mensagem = "Erro de SQL: " + e.getMessage();
        }
        return lista;
    }
    
    public Collection<Equipamento> lista(Tipo tipo, Marca mca, Modelo mdo ) {
        int params = 1;
        String sql = "select * from equipamento where codEquip > 0 ";
        
        if (tipo != null) 
            sql = sql + " and codTipo = ? ";
        if (mca != null)
            sql = sql + " and codMarca = ? ";
        if (mdo != null)
            sql = sql + " and codModelo = ? ";     
        
        // Ajuste da coleção de objetos
        Collection<Equipamento> lista = new ArrayList();

        try {
            PreparedStatement ps = Conexao.getPreparedStatement(sql);
            if (tipo != null) 
                ps.setInt(params++, tipo.getCodTipo());
            if (mca != null)
                ps.setInt(params++, mca.getCodMarca());
            if (mdo != null)
                ps.setInt(params++, mdo.getCodModelo());    
            
            lista = montaLista(ps);
            
            if (!lista.isEmpty()) {
                setMensagem("Relação de equipamentos gerada com sucesso");
                return lista;
            }
            setMensagem("Não existem registros para a pesquisa");
        } catch (Exception e) {
            mensagem = "Erro de SQL: " + e.getMessage();
        }
        return lista;
    }

    public int ultimaChave() {
        String sql = "select LAST_INSERT_ID() as ultima from equipamento ";

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
            setMensagem("Problemas ao localizar Chave em Equipamento. Erro de SQL " + ex.getMessage());
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
