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
import java.util.Calendar;
import java.util.Collection;
import modelo.UsoSala;

/**
 *
 * @author Eduardo
 * Modified by: rebonatto
 */

public class UsoSalaDAO {
    private String mensagem;
    private SalaCirurgiaDAO salaCirurdao = new SalaCirurgiaDAO();        
    
    public Collection<UsoSala> getSalasAtivas(){
        String sql = "select codsala, horaInicio " +
                     "from usosala " +
                     "where ativa = 1 ";
                
        Collection<UsoSala> lista = new ArrayList();
        try {
            PreparedStatement ps = Conexao.getPreparedStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next() == true) {
                UsoSala us = new UsoSala();
                
                us.setCodSala(salaCirurdao.localiza(rs.getInt("codsala")));
                                
                us.setHoraInicio(Conversoes.TimeStampToCalendar(rs.getTimestamp("horaInicio")));                

                lista.add(us);
            }
            if (!lista.isEmpty()) {
                setMensagem("Relação de usosala gerada com sucesso");
                return lista;
            }
            setMensagem("Não existem registros para a pesquisa");
        } catch (SQLException e) {
            mensagem = "Erro de SQL: " + e.getMessage();
        }
        return lista;    
    }
    
    public Collection<UsoSala> Lista(){
        String sql = "select * " +
                     "from usosala " ;
        
        ProcedimentoDAO proced = new ProcedimentoDAO();
        ResponsavelDAO resp = new ResponsavelDAO();
        SalaCirurgiaDAO sala = new SalaCirurgiaDAO();
                
        Collection<UsoSala> lista = new ArrayList();
        try {
            PreparedStatement ps = Conexao.getPreparedStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next() == true) {
                UsoSala us = new UsoSala();
                
                us.setCodUsoSala(rs.getInt("codUsoSala"));
                us.setCodProced(proced.localiza(rs.getInt("codProced")));
                us.setCodResp(resp.localiza(rs.getInt("codResp")));
                us.setCodSala(sala.localiza(rs.getInt("codSala")));
                
                us.setHoraInicio(Conversoes.TimeStampToCalendar(rs.getTimestamp("horaInicio"))) ;                
                if (rs.getTimestamp("horaFinal") != null)
                    us.setHoraFinal(Conversoes.TimeStampToCalendar(rs.getTimestamp("horaFinal")));
                else
                    us.setHoraFinal(null);

                lista.add(us);
            }
            if (!lista.isEmpty()) {
                setMensagem("Relação de usosala gerada com sucesso");
                return lista;
            }
            setMensagem("Não existem registros para a pesquisa");
        } catch (SQLException e) {
            mensagem = "Erro de SQL: " + e.getMessage();
        }
        return lista;    
    }          

     public UsoSala localiza(int codUsoSala) {
        String sql = "select * from usosala "
                + "where codUsoSala = ? ";

        UsoSala usala = new UsoSala();
        ProcedimentoDAO proced = new ProcedimentoDAO();
        ResponsavelDAO resp = new ResponsavelDAO();
        SalaCirurgiaDAO sala = new SalaCirurgiaDAO();

        try {
            PreparedStatement ps = Conexao.getPreparedStatement(sql);

            ps.setInt(1, codUsoSala);

            ResultSet rs = ps.executeQuery();
            if (rs.next() == true) {
                // mapear os dados recuperados do BD para o Objeto
                usala.setCodUsoSala(rs.getInt("codUsoSala"));
                usala.setCodProced(proced.localiza(rs.getInt("codProced")));
                usala.setCodResp(resp.localiza(rs.getInt("codResp")));
                usala.setCodSala(sala.localiza(rs.getInt("codSala")));
                
                usala.setHoraInicio(Conversoes.TimeStampToCalendar(rs.getTimestamp("horaInicio"))) ;      
                if (rs.getInt("ativa") == 0) //usosala nao ativo
                    usala.setHoraFinal(Conversoes.TimeStampToCalendar(rs.getTimestamp("horaFinal")));

                mensagem = "usoSala localizado";
                return usala;
            }
            mensagem = "Não localizado !";
        } catch (SQLException e) {
            mensagem = "Erro de SQL: " + e.getMessage();
        }
        return null;
    }

     public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

        /*
    public boolean Insere(UsoSala usala) {
        String sql = "insert into usosala "
                + "(`codResp`, `codProced`, `codSala`, `data`, `horaInicio`, `horaFinal`) "
                + "values (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = Conexao.getPreparedStatement(sql);

            ps.setInt(1, usala.getCodResp().getCodResp());
            ps.setInt(2, usala.getCodProced().getCodProced());
            ps.setInt(3, usala.getCodSala().getCodSala());
            ps.setDate(4, Conversoes.CalendarToDate(usala.getData()));
            ps.setTime(5, usala.getHoraInicio());
            ps.setTime(6, usala.getHoraFinal());


            ps.execute();
            setMensagem("Inseriu UsoSala com sucesso");
            return true;
        } catch (SQLException e) {
            setMensagem("Problemas ao inserir UsoSala" + e.getMessage());
        }
        return false;
    }

    public boolean altera(UsoSala usala) {

        String sql = "update usosala set "
                + "`codResp`              = ?,"
                + "`codProced`            = ?,"
                + "`codSala`              = ?,"
                + "`data`                 = ?,"
                + "`horaInicio`              = ?,"
                + "`horaFinal`            = ?"
                + "where `codUsoSala`     =?";

        try {
            PreparedStatement ps = Conexao.getPreparedStatement(sql);

            ps.setInt(1, usala.getCodResp().getCodResp());
            ps.setInt(2, usala.getCodProced().getCodProced());
            ps.setInt(3, usala.getCodSala().getCodSala());
            ps.setDate(4, Conversoes.CalendarToDate(usala.getData()));
            ps.setTime(5, usala.getHoraInicio());
            ps.setTime(6, usala.getHoraFinal());
            ps.setInt(7, usala.getCodUsoSala());


            if (ps.executeUpdate() > 0) {
                setMensagem("Alterou usalaamento com sucesso");
                return true;
            }
            setMensagem("Não encontrou usalaamento");
        } catch (SQLException e) {
            setMensagem("Problemas ao alterar usalaamento" + e.getMessage());
        }
        return false;
    }

    public boolean exclui(UsoSala usala) {
        // Preparação do SQL para interagir com o banco
        String sql = "delete from usosala "
                + "where codUsoSala = ?";
        try {
            PreparedStatement ps = Conexao.getPreparedStatement(sql);

            ps.setInt(1, usala.getCodUsoSala());

            if (ps.executeUpdate() > 0) {
                setMensagem("Excluiu usalaamento com sucesso");
                return true;
            }

            setMensagem("Não encontrou usalaamento");
        } catch (SQLException e) {
            setMensagem("Problemas ao excluir usalaamento" + e.getMessage());
        }
        return false;
    }

*/ 

    
    /*
    public Collection<UsoSala> lista() {
        String sql = "select * from usosala";

        // Ajuste da coleção de objetos
        Collection<UsoSala> lista = new ArrayList();

        try {
            PreparedStatement ps = Conexao.getPreparedStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next() == true) {

                UsoSala usala = new UsoSala();

                ProcedimentoDAO proced = new ProcedimentoDAO();
                ResponsavelDAO resp = new ResponsavelDAO();
                SalaCirurgiaDAO sala = new SalaCirurgiaDAO();

                // mapear os dados recuperados do BD para o Objeto
                usala.setCodUsoSala(rs.getInt("codUsoSala"));
                usala.setCodProced(proced.localiza(rs.getInt("codProced")));
                System.out.println(rs.getInt("codResp"));
                usala.setCodResp(resp.localiza(rs.getInt("codResp")));
                usala.setCodSala(sala.localiza(rs.getInt("codSala")));

                java.sql.Date dt = new Date(0);
                dt = rs.getDate("data");
                usala.setData(Conversoes.DateToCalendar(dt));

                usala.setHoraInicio(rs.getTime("horaInicio"));
                usala.setHoraFinal(rs.getTime("horaFinal"));


                lista.add(usala);
            }
            if (!lista.isEmpty()) {
                setMensagem("Relação de equipamentos gerada com sucesso");
                return lista;
            }
            setMensagem("Não existem registros para a pesquisa");
        } catch (SQLException e) {
            mensagem = "Erro de SQL: " + e.getMessage();
        }
        return lista;
    }

    public int ultimaChave() {
        String sql = "select LAST_INSERT_ID() as ultima from usosala ";

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
        }
        return (0);
    }
*/
}
