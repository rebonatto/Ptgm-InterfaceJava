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
import javax.swing.JOptionPane;
import modelo.Formularios.BeanFuga;
import modelo.CapturaAtual;
import modelo.Equipamento;
import modelo.Formularios.FugasSimultaneas;
import modelo.HarmAtual;
import modelo.Tomada;

/**
 *
 * @author rebonatto
 */
public class FugasDAO {
    private String mensagem;
    private TipoOndaDAO tpdao = new TipoOndaDAO();
    private EventoDAO evtdao = new EventoDAO();
    private EquipamentoDAO eqdao = new EquipamentoDAO();
    private EventoDAO evdao = new EventoDAO();
    private TomadaDAO tmdao = new TomadaDAO();
    
    
    public Collection<FugasSimultaneas> listaSimultaneas(){
        String sql = "select dataatual, count(*) qtd, max(eficaz) maximo " +
                     "from capturaatual cap " +
                     "where cap.codEvento = 1 " + // Fugas
                     "group by dataatual " +
                     "having count(*) >= 2 " + 
                     "limit 1000; ";

         // Ajuste da coleção de objetos
        Collection<FugasSimultaneas> lista = new ArrayList();              
       
        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        try {
            lista = montaListaSimultaneas(ps);
            if (!lista.isEmpty()) {
                setMensagem("Relação de Fugas gerada com sucesso");
                return lista;
            }
        } catch (Exception ex) {
            setMensagem("Erro de SQL " + ex.getMessage());       
           
        }        
        return lista;        
    }
    
    public Collection<FugasSimultaneas> lista(Calendar dataIni, Calendar dataFim) {
        String sql = "select dataatual, count(*) qtd, max(eficaz) maximo " +
             "from capturaatual cap " +
             "where cap.codEvento = 1 " + // Fugas
             "and date(cap.dataatual) >= ? " +
             "and date(cap.dataatual) <= ? " +
             "group by dataatual " +
             "having count(*) >= 2 " + 
             "limit 1000; ";
         // Ajuste da coleção de objetos
        Collection<FugasSimultaneas> lista = new ArrayList();              
       
        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        try {
            ps.setDate(1, Conversoes.CalendarToDate(dataIni));
            ps.setDate(2, Conversoes.CalendarToDate(dataFim));
            lista = montaListaSimultaneas(ps);
            if (!lista.isEmpty()) {
                setMensagem("Relação de Fugas gerada com sucesso");
                return lista;
            }
        } catch (Exception ex) {
            setMensagem("Erro de SQL " + ex.getMessage());       
           
        }        
        return lista;        
    }
    
    
    public Collection<FugasSimultaneas> montaListaSimultaneas(PreparedStatement ps) throws Exception{        
        Collection<FugasSimultaneas> lista = new ArrayList<>();
        
        try {            
            ResultSet rs = ps.executeQuery();
            while (rs.next() == true) {

                FugasSimultaneas fug = obtemObjetoSimulataneas(rs);
                
                lista.add(fug);
            }
            if (!lista.isEmpty()) {
                setMensagem("Relação de fugas gerada com sucesso");
                return lista;
            }
            setMensagem("Não existem registros para a pesquisa");
        } catch (SQLException e) {            
            mensagem = "Erro de SQL: " + e.getMessage();
            throw e;
        }
        return lista;
    }
    
     private FugasSimultaneas obtemObjetoSimulataneas(ResultSet rs){                                     
        FugasSimultaneas  bean = new FugasSimultaneas();
        try {
            // mapear os dados recuperados do BD para o Objeto
            bean.setDataAtual(Conversoes.TimeStampToCalendar(rs.getTimestamp("dataAtual")));
            bean.setQuantidade(rs.getInt("qtd"));
            bean.setMaxEficaz(rs.getFloat("maximo"));                        
        } catch (SQLException ex) {
            //System.out.println("CapturaAtualDAO");
            //System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Erro em obtemObjeto de CapturaOndaAtualDAO ");
        }
        return bean;
    }                    

    
    public Collection<BeanFuga> lista() {
        String sql = "select * "
                   + "from capturaatual cap "
                   + "where cap.codEvento = 1 " // fuga
                   + "limit 1000; ";

         // Ajuste da coleção de objetos
        Collection<BeanFuga> lista = new ArrayList();              
       
        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        try {
            lista = montaLista(ps);
            if (!lista.isEmpty()) {
                setMensagem("Relação de Fugas gerada com sucesso");
                return lista;
            }
        } catch (Exception ex) {
            setMensagem("Erro de SQL " + ex.getMessage());       
           
        }        
        return lista;
    }
    
    public Collection<BeanFuga> lista(Equipamento equip){
        String sql = "select * "
                   + "from capturaatual cap "
                   + "where cap.codEvento = 1 " // fuga
                   + "and cap.codEquip = ? "                
                   + "limit 1000; ";

         // Ajuste da coleção de objetos
        Collection<BeanFuga> lista = new ArrayList();              
       
        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        try {
            ps.setInt(1, equip.getCodEquip());
            lista = montaLista(ps);
            if (!lista.isEmpty()) {
                setMensagem("Relação de Fugas gerada com sucesso");
                return lista;
            }
        } catch (Exception ex) {
            setMensagem("Erro de SQL " + ex.getMessage());       
           
        }        
        return lista;
    }
    
    public Collection<BeanFuga> lista(Tomada tom){
        String sql = "select * "
                   + "from capturaatual cap "
                   + "where cap.codEvento = 1 " // fuga
                   + "and cap.codTomada = ? "                
                   + "limit 1000; ";

         // Ajuste da coleção de objetos
        Collection<BeanFuga> lista = new ArrayList();              
       
        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        try {
            ps.setInt(1, tom.getCodTomada());
            lista = montaLista(ps);
            if (!lista.isEmpty()) {
                setMensagem("Relação de Fugas gerada com sucesso");
                return lista;
            }
        } catch (Exception ex) {
            setMensagem("Erro de SQL " + ex.getMessage());       
           
        }        
        return lista;
    }
    
    public Collection<BeanFuga> lista(Equipamento equip, Calendar dataIni, Calendar dataFim) {
        String sql = "select * "
                   + "from capturaatual cap "
                   + "where cap.codEvento = 1 " // fuga
                   + "and cap.codEquip = ? "            
                   + "and date(cap.dataatual) >= ? "
                   + "and date(cap.dataatual) <= ?"
                   + "limit 1000; ";

         // Ajuste da coleção de objetos
        Collection<BeanFuga> lista = new ArrayList();              
       
        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        try {
            ps.setInt(1, equip.getCodEquip());
            ps.setDate(2, Conversoes.CalendarToDate(dataIni));
            ps.setDate(3, Conversoes.CalendarToDate(dataFim));
            lista = montaLista(ps);
            if (!lista.isEmpty()) {
                setMensagem("Relação de Fugas gerada com sucesso");
                return lista;
            }
        } catch (Exception ex) {
            setMensagem("Erro de SQL " + ex.getMessage());       
           
        }        
        return lista;        
    }
    
    public Collection<BeanFuga> lista(Tomada tom, Calendar data) {
        String sql = "select * "
                   + "from capturaatual cap "
                   + "where cap.codEvento = 1 " // fuga
                   + "and cap.codTomada = ? "            
                   + "and date(cap.dataatual) = ? "
                   + "limit 10000; ";

         // Ajuste da coleção de objetos
        Collection<BeanFuga> lista = new ArrayList();              
       
        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        try {
            ps.setInt(1, tom.getCodTomada());
            ps.setDate(2, Conversoes.CalendarToDate(data));
            lista = montaLista(ps);
            if (!lista.isEmpty()) {
                setMensagem("Relação de Fugas gerada com sucesso");
                return lista;
            }
        } catch (Exception ex) {
            setMensagem("Erro de SQL " + ex.getMessage());       
           
        }        
        return lista;        
    }
    
    public Collection<BeanFuga> lista(Calendar data) {
        String sql = "select * "
                   + "from capturaatual cap "
                   + "where cap.codEvento = 1 " // fuga
                   + "and date(cap.dataatual) = ? "
                   + "limit 1000; ";

         // Ajuste da coleção de objetos
        Collection<BeanFuga> lista = new ArrayList();              
       
        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        try {
            ps.setDate(1, Conversoes.CalendarToDate(data));
            lista = montaLista(ps);
            if (!lista.isEmpty()) {
                setMensagem("Relação de Fugas gerada com sucesso");
                return lista;
            }
        } catch (Exception ex) {
            setMensagem("Erro de SQL " + ex.getMessage());       
           
        }        
        return lista;        
    }

    public Collection<BeanFuga> montaLista(PreparedStatement ps) throws Exception{        
        Collection<BeanFuga> lista = new ArrayList<>();
        
        try {            
            ResultSet rs = ps.executeQuery();
            while (rs.next() == true) {

                BeanFuga fug = obtemObjeto(rs);
                
                lista.add(fug);
            }
            if (!lista.isEmpty()) {
                setMensagem("Relação de fugas gerada com sucesso");
                return lista;
            }
            setMensagem("Não existem registros para a pesquisa");
        } catch (SQLException e) {            
            mensagem = "Erro de SQL: " + e.getMessage();
            throw e;
        }
        return lista;
    }
    
    public BeanFuga localiza(int codCapturaAtual) {
        String sql = "select * from capturaatual "
                   + "where codCaptura = ? ";                
              
        try {
            PreparedStatement ps = Conexao.getPreparedStatement(sql);

            ps.setInt(1, codCapturaAtual);
        
            ResultSet rs = ps.executeQuery();
            while (rs.next() == true) {
                BeanFuga fug = obtemObjeto(rs);
            
                mensagem = "Fuga localizada";
                return fug;
            }
            mensagem = "Fuga Não localizada !";
        } catch (SQLException e) {
            mensagem = "Erro de SQL: " + e.getMessage();
        }
        return null;
    }
       
    private BeanFuga obtemObjeto(ResultSet rs){
        String sql    = "select dataAtual " +
                        "from capturaatual " +
                        "where codcaptura = (select min(codcaptura) " +
                        "   		     from capturaatual cap " +
                        "		     where cap.codcaptura > ?" +
                        "		     and cap.codEvento = 6 " +
                        "                    and cap.codTomada = ?); "; 
       //                 "                    and cap.codEquip = ?); ";
                      
        String sqldet = "select * from harmatual "
                      + "where codCaptura = ? "
                      + "order by codHarmonica";    
    
        CapturaAtual coa  = new CapturaAtual();
        BeanFuga     bean = new BeanFuga();
        try {
            // mapear os dados recuperados do BD para o Objeto
            coa.setCodCaptura(rs.getInt("codCaptura"));
            coa.setCodTipoOnda(tpdao.localiza(rs.getInt("codTipoOnda")));
            coa.setCodEquip(eqdao.localiza(rs.getInt("codEquip")));
            coa.setCodEvento(evdao.localiza(rs.getInt("codEvento")));
            coa.setCodTomada(tmdao.localiza(rs.getInt("codTomada")));
            coa.setValorMedio(rs.getFloat("valorMedio"));
            coa.setOffset(rs.getFloat("offset"));
            coa.setGain(rs.getFloat("gain"));
            coa.setEficaz(rs.getFloat("eficaz"));
            coa.setDataAtual(Conversoes.TimeStampToCalendar(rs.getTimestamp("dataAtual")));
                
            Collection <HarmAtual> listadet = new ArrayList<>();
            PreparedStatement psdet = Conexao.getPreparedStatement(sqldet); 
            psdet.setInt(1, coa.getCodCaptura() );
            ResultSet rsdet = psdet.executeQuery();
            
            while (rsdet.next() == true) {
                HarmAtual oa = new HarmAtual();

                // mapear os dados recuperados do BD para o Objeto
                oa.setCodCaptura(coa.getCodCaptura());
                oa.setHarmonica(rsdet.getInt("codHarmonica"));
                oa.setSen(rsdet.getFloat("sen"));
                oa.setCos(rsdet.getFloat("cos"));

                listadet.add(oa);
             }
             coa.setHarmAtual(listadet);
             bean.setCaptura(coa);
             
             // buscar dos dados do termino da fuga
             PreparedStatement ps = Conexao.getPreparedStatement(sql);
             try {
                 ps.setInt(1, coa.getCodCaptura());
                 ps.setInt(2, coa.getCodTomada().getCodTomada());
                 // ps.setInt(3, coa.getCodEquip().getCodEquip());
                 ResultSet rsfug = ps.executeQuery();
                 if (rsfug.next() == true) {
                     
                     bean.setTermino(Conversoes.TimeStampToCalendar(rsfug.getTimestamp("dataAtual")));
                     setMensagem("Fugas gerada com sucesso");
                     return bean;
                }
                setMensagem("Não existe termino da fuga");
            } catch (SQLException e) {            
                mensagem = "Erro de SQL: " + e.getMessage();
                throw e;
            }         
        } catch (SQLException ex) {
            //System.out.println("CapturaAtualDAO");
            //System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Erro em obtemObjeto de CapturaOndaAtualDAO ");
        }
        return bean;
    }                    

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }    
}
