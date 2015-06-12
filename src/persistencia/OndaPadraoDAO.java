/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia;

import Uteis.Conversoes;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JOptionPane;
import modelo.CapturaAtual;
import modelo.OndaPadrao;
import modelo.Equipamento;
import modelo.HarmAtual;
import modelo.HarmPadrao;
import modelo.TipoOnda;
import modelo.TipoPadrao;

/**
 *
 * @author Eduardo
 * Rewrite by: rebonatto
 * 
 */

public class OndaPadraoDAO {
    private String mensagem;
    TipoOndaDAO tpdao = new TipoOndaDAO();
    EquipamentoDAO eqdao = new EquipamentoDAO();
    EventoDAO evdao = new EventoDAO();
    TomadaDAO tmdao = new TomadaDAO();
    TipoPadraoDAO tppadrodao = new TipoPadraoDAO();
    
    private Collection<OndaPadrao> montaLista(PreparedStatement ps) throws Exception{       
        Collection<OndaPadrao> lista = new ArrayList<>();
        
        try {            
            ResultSet rs = ps.executeQuery();
            while (rs.next() == true) {

                OndaPadrao cop = obtemObjeto(rs);
                
                lista.add(cop);
            }
            if (!lista.isEmpty()) {
                setMensagem("Relação de ondas padrao gerada com sucesso");
                return lista;
            }
            setMensagem("Não existem registros para a pesquisa");
        } catch (SQLException e) {            
            mensagem = "Erro de SQL: " + e.getMessage();
            throw e;
        }
        return lista;
    }

    public Collection<OndaPadrao> lista(Equipamento equip, TipoOnda tip) {
        String sql = "select * from ondapadrao "
                   + "where codEquip = ? "
                   + "and codTipoOnda = ? ";

        // Ajuste da coleção de objetos
        Collection<OndaPadrao> lista = new ArrayList();              
       
        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        try {
            ps.setInt(1, equip.getCodEquip());
            ps.setInt(2, tip.getCodTipoOnda());
            lista = montaLista(ps);

            if (!lista.isEmpty()) {
                setMensagem("Relação de Formas Padrao de onda gerada com sucesso");
                return lista;
            }
        } catch (Exception ex) {
            setMensagem("Erro de SQL " + ex.getMessage());
        }
        
        return lista;
    }
    
    public Collection<OndaPadrao> lista(Equipamento equip) {
        String sql = "select * from ondapadrao "
                   + "where codEquip = ? ";

        // Ajuste da coleção de objetos
        Collection<OndaPadrao> lista = new ArrayList();              
       
        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        try {
            ps.setInt(1, equip.getCodEquip());
            lista = montaLista(ps);

            if (!lista.isEmpty()) {
                setMensagem("Relação de Formas Padrao de onda gerada com sucesso");
                return lista;
            }
        } catch (Exception ex) {
            setMensagem("Erro de SQL " + ex.getMessage());
        }
        
        return lista;
    }
    
    public Collection<OndaPadrao> lista() {
        String sql = "select * from ondapadrao ";

         // Ajuste da coleção de objetos
        Collection<OndaPadrao> lista = new ArrayList();              
       
        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        try {
            lista = montaLista(ps);
            if (!lista.isEmpty()) {
                setMensagem("Relação de Formas de onda Padrao gerada com sucesso");
                return lista;
            }
        } catch (Exception ex) {
            setMensagem("Erro de SQL " + ex.getMessage());       
           
        }        
        return lista;
    }

    public OndaPadrao localiza(int codOndaPadrao) {
        String sql = "select * from ondapadrao "
                   + "where codOndaPadrao = ? ";                        
              
        try {
            PreparedStatement ps = Conexao.getPreparedStatement(sql);

            ps.setInt(1, codOndaPadrao);
        
            ResultSet rs = ps.executeQuery();

            while (rs.next() == true) {
                OndaPadrao cop = obtemObjeto(rs);             
            
                mensagem = "Captura de forma de onda localizada";
                return cop;
            }
            mensagem = "Forma de onda Não localizada !";
        } catch (SQLException e) {
            mensagem = "Erro de SQL: " + e.getMessage();
        }
        return null;
    }
    
    private OndaPadrao obtemObjeto(ResultSet rs){
        String sqldet = "select * from harmpadrao "
                      + "where codOndaPadrao = ? "
                      + "order by codHarmonica";    
    
        OndaPadrao op = new OndaPadrao();
        try {
            // mapear os dados recuperados do BD para o Objeto
            op.setCodOndaPadrao(rs.getInt("codOndaPadrao"));
            op.setCodTipoOnda(tpdao.localiza(rs.getInt("codTipoOnda")));
            op.setCodEquip(eqdao.localiza(rs.getInt("codEquip")));
            op.setCodTomada(tmdao.localiza(rs.getInt("codTomada")));
            op.setValorMedio(rs.getFloat("valorMedio"));
            op.setOffset(rs.getFloat("offset"));
            op.setGain(rs.getFloat("gain"));
            op.setEficaz(rs.getFloat("eficaz"));
            op.setDataPadrao(Conversoes.DateToCalendar(rs.getDate("dataPadrao")));
            op.setCodTipoPadrao(tppadrodao.localiza(rs.getInt("codTipoPadrao")));
                
            Collection <HarmPadrao> listadet = new ArrayList<>();
            PreparedStatement psdet = Conexao.getPreparedStatement(sqldet); 
            psdet.setInt(1, op.getCodOndaPadrao() );
            ResultSet rsdet = psdet.executeQuery();
            
            while (rsdet.next() == true) {
                HarmPadrao hp = new HarmPadrao();

                // mapear os dados recuperados do BD para o Objeto
                hp.setCodOndaPadrao(op.getCodOndaPadrao());
                hp.setHarmonica(rsdet.getInt("codHarmonica"));
                hp.setSen(rsdet.getFloat("sen"));
                hp.setCos(rsdet.getFloat("cos"));

                listadet.add(hp);
             }
             op.setHarmPadrao(listadet);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro em obtemObjeto de OndaPadraolDAO ");
        }
        return op;
    }
    
    public boolean Insere(CapturaAtual cap, TipoPadrao tip) {
        String sql = "INSERT INTO ondapadrao (`codTipoOnda`, `codTomada`, `codEquip`, `valorMedio`, `offset`, `gain`, `eficaz`, " +
                     "`dataPadrao`, `codTipoPadrao`) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?); ";
        
        String sqldet = "INSERT INTO harmpadrao (`codHarmonica`, `codOndaPadrao`, `sen`, `cos`) VALUES ( ?, ?, ?, ? );";
                
        try {
            Conexao.getConexao().setAutoCommit(false);                        
            
            PreparedStatement ps = Conexao.getPreparedStatement(sql);

            ps.setInt(1, cap.getCodTipoOnda().getCodTipoOnda());
            ps.setInt(2, cap.getCodTomada().getCodTomada());            
            ps.setInt(3, cap.getCodEquip().getCodEquip());
            ps.setFloat(4, cap.getValorMedio());
            ps.setFloat(5, cap.getOffset());
            ps.setFloat(6, cap.getGain());
            ps.setFloat(7, cap.getEficaz());
            ps.setTimestamp(8, Conversoes.CalendarToTimeStamp(cap.getDataAtual()));
            ps.setInt(9, tip.getCodTipoPadrao());
            
            ps.execute();
            
            PreparedStatement psdet = Conexao.getPreparedStatement(sqldet);
            int codOndapadrao = this.ultimaChave() ;
            
            for(HarmAtual ha : cap.getHarmAtual()){
                psdet.setInt(1, ha.getHarmonica() );
                psdet.setInt(2, codOndapadrao);                                
                psdet.setFloat(3, ha.getSen());
                psdet.setFloat(4, ha.getCos());
                
                psdet.execute();
            }
            
            Conexao.getConexao().commit();
            setMensagem("Inseriu OndaPAdrao com sucesso");
            Conexao.getConexao().setAutoCommit(true);
            return true;
        } catch (SQLException e) {
            setMensagem("Problemas ao inserir OndaPadrao " + e.getMessage());
            
           try {
                Conexao.getConexao().setAutoCommit(true);
            } catch (SQLException ex) {
                setMensagem(" Problemas ao inserir UsoSala Erro ao realizar setAutoCommit(true)");
            }
             
        } 
        
        return false;
    }

    public int ultimaChave() {
        String sql = "select LAST_INSERT_ID() as ultima from ondapadrao ";

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
        }
        return (0);
    }            
    
    // -- Final, só get e set (mensagem) para baixo

//    public boolean Insere(CapturaOndaPadrao coa) {
//        String sql = "insert into CapturaOndaPadrao "
//                + "(`codOndaPadrao`, `codTomada`, `codTipoOnda`, `codEquipamento`, `valorMedio`, `offset`, `gain`, `eficaz`, `DataPadrao`) "
//                + "values (?, ?, ?, ?,?,?,?,?,?)";
//        try {
//            PreparedStatement ps = Conexao.getPreparedStatement(sql);
//
//            ps.setInt(1, coa.getCodOndaPadrao());
//            ps.setInt(2, coa.getCodTomada().getCodTomada());
//            ps.setInt(3, coa.getCodTipoOnda().getCodTponda());
//            ps.setInt(4, coa.getCodEquipamento().getCodEquip());
//            ps.setFloat(5, coa.getValorMedio());
//            ps.setFloat(6, coa.getOffset());
//            ps.setFloat(7, coa.getGain());
//            ps.setFloat(8, coa.getEficaz());
//            ps.setDate(9, coa.getDataPadrao());
//
//
//            ps.execute();
//            setMensagem("Inseriu UsoSala com sucesso");
//            return true;
//        } catch (SQLException e) {
//            setMensagem("Problemas ao inserir UsoSala" + e.getMessage());
//        }
//        return false;
//    }
//
//    public boolean altera(CapturaOndaPadrao t) {
//
//        String sql = "update CapturaOndaPadrao set "
//                + "`codTomada`            = ?,"
//                + "`codTipoOnda`              = ?,"
//                + "`codEquipamento`                 = ?,"
//                + "`valorMedio`              = ?,"
//                + "`offset`              = ?,"
//                + "`gain`              = ?,"
//                + "`eficaz`              = ?,"
//                + "`dataPadrao`              = ?"
//                + "where `codOndaPadrao`     =?;";
//
//        try {
//            PreparedStatement ps = Conexao.getPreparedStatement(sql);
//
//            ps.setInt(1, t.getCodTomada().getCodTomada());
//            ps.setInt(2, t.getCodTipoOnda().getCodTponda());
//            ps.setInt(3, t.getCodEquipamento().getCodEquip());
//            ps.setFloat(5, t.getValorMedio());
//            ps.setFloat(6, t.getOffset());
//            ps.setFloat(7, t.getGain());
//            ps.setFloat(8, t.getEficaz());
//            ps.setDate(9, t.getDataPadrao());
//            ps.setInt(10, t.getCodOndaPadrao());
//
//
//            if (ps.executeUpdate() > 0) {
//                setMensagem("Alterou usalaamento com sucesso");
//                return true;
//            }
//            setMensagem("Não encontrou usalaamento");
//        } catch (SQLException e) {
//            setMensagem("Problemas ao alterar usalaamento" + e.getMessage());
//        }
//        return false;
//    }
//
//    public boolean exclui(CapturaOndaPadrao t) {
//        // Preparação do SQL para interagir com o banco
//        String sql = "delete from capturaOndaPadrao "
//                + "where codOndaPadrao = ?";
//        try {
//            PreparedStatement ps = Conexao.getPreparedStatement(sql);
//
//            ps.setInt(1, t.getCodOndaPadrao());
//
//            if (ps.executeUpdate() > 0) {
//                setMensagem("Excluiu usalaamento com sucesso");
//                return true;
//            }
//
//            setMensagem("Não encontrou usalaamento");
//        } catch (SQLException e) {
//            setMensagem("Problemas ao excluir usalaamento" + e.getMessage());
//        }
//        return false;
//    }


     public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

}
