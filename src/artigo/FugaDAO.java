/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package artigo;

import Uteis.Conversoes;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import javax.swing.JOptionPane;
import persistencia.Conexao;


/**
 *
 * @author rebonatto
 */
public class FugaDAO {
    private String mensagem;
    private Collection<BeanFuga> listaTermino = new ArrayList();
    
    public FugaDAO(){
        String sql = "select * "
                   + "from fugas "
                   + "where codevento = 6 " // termino de fuga
                   + "order by codcaptura "
                   + "limit 120000; ";
                   //+ "limit 1000; ";
      
        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        try {
            listaTermino = montaLista(ps, false);

            if (!listaTermino.isEmpty()) {
                setMensagem("Relação de Fugas gerada com sucesso");
            }
        } catch (Exception ex) {
            setMensagem("Erro de SQL " + ex.getMessage());                  
        }                
    }
            
    
    public Collection<artigo.BeanFuga> lista() {

        String sql = "select * "
                   + "from fugas "
                   + "where codevento = 1 " // fuga
                   + "order by codcaptura "
                   + "limit 120000; ";
                   //+ "limit 1000; ";

         // Ajuste da coleção de objetos
        Collection<artigo.BeanFuga> lista = new ArrayList();              
       
        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        try {
            lista = montaLista(ps, true);

            if (!lista.isEmpty()) {
                setMensagem("Relação de Fugas gerada com sucesso");
                return lista;
            }
        } catch (Exception ex) {

            setMensagem("Erro de SQL " + ex.getMessage());       
           
        }        
        return lista;
    }        

    public Collection<artigo.BeanFuga> montaLista(PreparedStatement ps, boolean termino) throws Exception{        
        Collection<artigo.BeanFuga> lista = new ArrayList<>();
        int i=0;
              
        try {            
            ResultSet rs = ps.executeQuery();
            while (rs.next() == true) {
                i++;
                if( (i % 1000 == 0) && termino){
                    System.out.println(i);
                }
                    
                artigo.BeanFuga fug = obtemObjeto(rs, termino);
                
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
       
    private BeanFuga obtemObjeto(ResultSet rs, boolean termino){
        String sql    = "select dataAtual " +
                        "from fugas " +
                        "where codcaptura = (select min(codcaptura) " +
                        "   		     from fugas cap " +
                        "		     where cap.codcaptura > ?" +
                        "		     and cap.codEvento = 6 " +
                        "                    and cap.codTomada = ?); ";                            
    
        CapturaAtual coa  = new artigo.CapturaAtual();
        BeanFuga     bean = new artigo.BeanFuga();
        try {
            // mapear os dados recuperados do BD para o Objeto
            coa.setCodCaptura(rs.getInt("codCaptura"));
            //System.out.println(rs.getInt("codCaptura"));
            
            coa.setCodTipoOnda(rs.getInt("codTipoOnda"));
            coa.setCodEquip(rs.getInt("codEquipamento"));
            coa.setCodEvento(rs.getInt("codEvento"));
            coa.setCodTomada(rs.getInt("codTomada"));
            coa.setValorMedio(rs.getFloat("valorMedio"));
            coa.setOffset(rs.getFloat("offset"));
            coa.setGain(rs.getFloat("gain"));
            coa.setEficaz(rs.getFloat("eficaz"));
            coa.setDataAtual(Conversoes.TimeStampToCalendar(rs.getTimestamp("dataAtual")));
            
            bean.setCaptura(coa);                        
                                     
            if (termino){
                // buscar dos dados do termino da fuga
                 for(BeanFuga t : listaTermino){
                     if ( t.getCaptura().getCodCaptura() <= bean.getCaptura().getCodCaptura() 
                        || t.getCaptura().getCodTomada() != bean.getCaptura().getCodTomada())              
                        continue;
                     if (t.getCaptura().getCodCaptura() > bean.getCaptura().getCodCaptura()
                        && t.getCaptura().getCodTomada() == bean.getCaptura().getCodTomada()){
                         bean.setTermino(t.getCaptura().getDataAtual());
                         break;
                     }                 
                 }
                 /*
                 PreparedStatement ps = Conexao.getPreparedStatement(sql);
                 try {
                     ps.setInt(1, coa.getCodCaptura());
                     ps.setInt(2, coa.getCodTomada());

                     ResultSet rsfug = ps.executeQuery();
                     if (rsfug.next() == true) {                     
                         bean.setTermino(Conversoes.TimeStampToCalendar(rsfug.getTimestamp("dataAtual")));
                         setMensagem("Fugas gerada com sucesso");                     
                         return bean;
                     }
                     else
                         System.out.println("Não existe termino da fuga");
                    setMensagem("Não existe termino da fuga");

                } catch (SQLException e) {            
                    mensagem = "Erro de SQL: " + e.getMessage();
                    throw e;
                } 
                */ 
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
    /*
    public artigo.BeanFuga localiza(int codCapturaAtual) {
        String sql = "select * from capturaatual "
                   + "where codCaptura = ? ";                
              
        try {
            PreparedStatement ps = Conexao.getPreparedStatement(sql);

            ps.setInt(1, codCapturaAtual);
        
            ResultSet rs = ps.executeQuery();
            while (rs.next() == true) {
                artigo.BeanFuga fug = obtemObjeto(rs);
            
                mensagem = "Fuga localizada";
                return fug;
            }
            mensagem = "Fuga Não localizada !";
        } catch (SQLException e) {
            mensagem = "Erro de SQL: " + e.getMessage();
        }
        return null;
    }
    
    public Collection<BeanFuga> listacEquip(int equip){
        String sql = "select * "
                   + "from fugas cap "
                   + "where cap.codEvento = 1 " // fuga
                   + "and cap.codEquip = ? "                
                   + "limit 1000; ";

         // Ajuste da coleção de objetos
        Collection<BeanFuga> lista = new ArrayList();              
       
        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        try {
            ps.setInt(1, equip);
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
    
    public Collection<BeanFuga> listacTom(int tom){
        String sql = "select * "
                   + "from fugas cap "
                   + "where cap.codEvento = 1 " // fuga
                   + "and cap.codTomada = ? "                
                   + "limit 1000; ";

         // Ajuste da coleção de objetos
        Collection<BeanFuga> lista = new ArrayList();              
       
        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        try {
            ps.setInt(1, tom);
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
    
    public Collection<BeanFuga> listacEquipDatas(int equip, Calendar dataIni, Calendar dataFim) {
        String sql = "select * "
                   + "from fugas cap "
                   + "where cap.codEvento = 1 " // fuga
                   + "and cap.codEquip = ? "            
                   + "and date(cap.dataatual) >= ? "
                   + "and date(cap.dataatual) <= ?"
                   + "limit 1000; ";

         // Ajuste da coleção de objetos
        Collection<BeanFuga> lista = new ArrayList();              
       
        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        try {
            ps.setInt(1, equip);
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
    
    public Collection<BeanFuga> listacTomdadaData(int tom, Calendar data) {
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
            ps.setInt(1, tom);
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
    
    public Collection<BeanFuga> listacData(Calendar data) {
        String sql = "select * "
                   + "from fugas cap "
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
    */ 
}
