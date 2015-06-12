/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import Uteis.Configuracoes;
import Uteis.Conversoes;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import javax.swing.JOptionPane;
import modelo.CapturaAtual;
import modelo.Equipamento;
import modelo.Formularios.SumarizaEventos;
import modelo.HarmAtual;

/**
 *
 * @author rebonatto
 */
public class CapturaEventosDAO {
    private EventoDAO evdao = new EventoDAO();
    private String mensagem;

    private Collection<CapturaAtual> montaListacFO(PreparedStatement ps) throws Exception {

        Collection<CapturaAtual> lista = new ArrayList();
        // limpa a lista para uso
        //lista.clear();

        return lista;
    }

    public Collection<SumarizaEventos> listaSumarizaEventos(Calendar dataIni, Calendar dataFim) {
        String sql = "select date(dataatual) as dataAtual, codevento as codEvento, count(*) as quantidade, max(time(dataatual)) as horaFinal " +
                     "from capturaatual " +
                     "where date(dataatual) >= ? " +
                     "and date(dataatual) <= ? " +
                     "group by date(dataatual), codevento " +
                     "order by dataatual desc; ";

        Collection<SumarizaEventos> lista = new ArrayList();
        // Ajuste da coleção de objetos        

        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        try {
            
            ps.setDate(1, Conversoes.CalendarToDate(dataIni));
            ps.setDate(2, Conversoes.CalendarToDate(dataFim));
            
            ResultSet rs = ps.executeQuery();
            while (rs.next() == true) {

                SumarizaEventos evt = new SumarizaEventos();

                // mapear os dados recuperados do BD para o Objeto
                evt.setData(Conversoes.TimeStampToCalendar(rs.getTimestamp("dataAtual")));                        
                evt.setCodEvento(evdao.localiza(rs.getInt("codEvento")));
                evt.setQuantidade(rs.getInt("quantidade"));     
                evt.setHorafinal(Conversoes.TimeStampToCalendar(rs.getTimestamp("horaFinal")));

                lista.add(evt);
            }
            if (!lista.isEmpty()) {
                setMensagem("Relação de eventos gerada com sucesso");
                return lista;
            }
            setMensagem("Não existem registros para a pesquisa");
        } catch (SQLException e) {
            mensagem = "Erro de SQL: " + e.getMessage();
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                setMensagem("Problemas ao Fechar PrepareStatement " + ex.getMessage());
            }
        }
        return lista;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    
    
}
