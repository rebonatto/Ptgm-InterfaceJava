 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia;

import Uteis.Configuracoes;
import Uteis.Conversoes;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Formularios.BeanFuga;
import modelo.CapturaAtual;
import modelo.Equipamento;
import modelo.HarmAtual;

/**
 *
 * @author Eduardo
 * Modified by: rebonatto
 * 
 */

public class CapturaAtualDAO {
    private String mensagem;
    private TipoOndaDAO tpdao = new TipoOndaDAO();
    private EventoDAO evtdao = new EventoDAO();
    private EquipamentoDAO eqdao = new EquipamentoDAO();
    private EventoDAO evdao = new EventoDAO();
    private TomadaDAO tmdao = new TomadaDAO();
    private Collection<CapturaAtual> lista = new ArrayList();              
    
    private Collection<CapturaAtual> montaListacFO(PreparedStatement ps) throws Exception{      
        
        Collection<CapturaAtual> lista = new ArrayList();              
        // limpa a lista para uso
        //lista.clear();
        
        try {            
            ResultSet rs = ps.executeQuery();
            while (rs.next() == true) {

                CapturaAtual coa = obtemObjeto(rs) ;
               
                lista.add(coa);
            }
            if (!lista.isEmpty()) {
                setMensagem("Relação de FO gerada com sucesso");
                return lista;
            }
            setMensagem("Não existem registros para a pesquisa");
        } catch (SQLException e) {            
            mensagem = "Erro de SQL: " + e.getMessage();
            throw e;
        }
        return lista;
    }
    //private Collection<CapturaAtual> listanew = new ArrayList();
    
    public Collection<CapturaAtual> listaFoFaseFuga(int limit, boolean fase, boolean fuga) {        
        String sql = "select cap.* " +
             "from capturaatual cap " +
             "where " ;
        if (fase && fuga)        
            sql = sql + "(cap.codevento = 1 or cap.codevento = 4) ";
        else
            if (fase)
                sql = sql + "cap.codevento = 4 ";
            else
                sql = sql + "cap.codevento = 1 ";
        sql = sql + 
             //"and cap.codcaptura >= 6500520 " +
             //"and cap.codcaptura <= 6500527 " +   
             "order by cap.codcaptura desc " +
             "limit ?" ;
                
        //Collection<CapturaAtual> lista = new ArrayList();              
        // limpa a lista para uso
        lista.clear();        
               
        PreparedStatement ps = Conexao.getPreparedStatement(sql);
               
        try {
            ps.setInt(1, limit);            
            
            lista = montaLista(ps);

            if (!lista.isEmpty()) {
                setMensagem("Relação de Formas de onda gerada com sucesso");
                return lista;
            }
        } catch (Exception ex) {
            setMensagem("Erro de SQL " + ex.getMessage());
        } finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                setMensagem("Problemas ao Fechar PrepareStatement " + ex.getMessage());
            }
        }
        
        return lista;
    }
    
    public Collection<CapturaAtual> listacFO(Equipamento equip, boolean fase, boolean fuga) {
        String sql = "select cap.* " +
             "from capturaatual cap " +
             "where ";
        if (fuga && fase)
            sql = sql + "(cap.codEvento = 1 or cap.codEvento = 4) ";
        else if (fuga)
            sql = sql + "(cap.codEvento = 1) "; // fuga
        else
            sql = sql + "(cap.codEvento = 4) "; // fase
             
        if (equip != null)
           sql += "and cap.codEquip = ? ";
        sql = sql +"order by cap.codcaptura " +
                   "limit 1000 ";        
        
        //Collection<CapturaAtual> lista = new ArrayList();
        // Ajuste da coleção de objetos
        lista.clear();
       
        PreparedStatement ps = Conexao.getPreparedStatement(sql);
               
        try {
            if (equip != null)
                ps.setInt(1, equip.getCodEquip());            
            
            lista = montaLista(ps);

            if (!lista.isEmpty()) {
                setMensagem("Relação de Formas de onda gerada com sucesso");
                return lista;
            }
        } catch (Exception ex) {
            setMensagem("Erro de SQL " + ex.getMessage());
        } finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                setMensagem("Problemas ao Fechar PrepareStatement " + ex.getMessage());
            }
        }
        
        return lista;
    }
    
    public Collection<CapturaAtual> listacFO(Equipamento equip, Calendar data, boolean fase, boolean fuga) {
        String sql = "select cap.* " +
                     "from capturaatual cap, eventos ev " +
                     "where cap.codEvento = ev.codEvento " +
                     "and ev.formaOnda = 1 " +
                     "and cap.codEquip = ? " +
                     "and date(dataatual) = ? ";
        
             if (fase != fuga){                    
                if (fase)
                    sql = sql + "and cap.codTipoOnda = 1 "; //tipo de onda de fase
                else
                    sql = sql + "and cap.codTipoOnda = 2 "; //tipo de onda de fuga
             }
             sql = sql +"order by cap.codcaptura " +
                        "limit 1000 ";
        
        //Collection<CapturaAtual> lista = new ArrayList();
        // Ajuste da coleção de objetos
        lista.clear();
       
        PreparedStatement ps = Conexao.getPreparedStatement(sql);
               
        try {
            ps.setInt(1, equip.getCodEquip());
            ps.setDate(2, Conversoes.CalendarToDate(data));
            
            lista = montaLista(ps);

            if (!lista.isEmpty()) {
                setMensagem("Relação de Formas de onda gerada com sucesso");
                return lista;
            }
        } catch (Exception ex) {
            setMensagem("Erro de SQL " + ex.getMessage());
        } finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                setMensagem("Problemas ao Fechar PrepareStatement " + ex.getMessage());
            }
        }
        
        return lista;
    }
    
    public Collection<CapturaAtual> listacFO(Equipamento equip, Calendar dataIni, Calendar dataFim, boolean fase, boolean fuga) {
        String sql = "select * "
                   + "from capturaatual cap "
                   + "where ";
        if (fuga && fase)
            sql = sql + "(cap.codEvento = 1 or cap.codEvento = 4) ";
        else if (fuga)
            sql = sql + "(cap.codEvento = 1) "; // fuga
        else
            sql = sql + "(cap.codEvento = 4) "; // fase
        
        sql = sql + "and date(cap.dataatual) >= ? "
                  + "and date(cap.dataatual) <= ? ";
        if (equip != null)
            sql = sql +"and cap.codEquip = ? ";
        sql += "limit 1000; ";
                
        //Collection<CapturaAtual> lista = new ArrayList();
        // Ajuste da coleção de objetos
        lista.clear();
       
        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        try {
            ps.setDate(1, Conversoes.CalendarToDate(dataIni));
            ps.setDate(2, Conversoes.CalendarToDate(dataFim));
            if (equip != null)
                ps.setInt(3, equip.getCodEquip());
            lista = montaLista(ps);
            if (! lista.isEmpty()) {
                setMensagem("Relação de Fugas gerada com sucesso");
                return lista;
            }
        } catch (Exception ex) {
            setMensagem("Erro de SQL " + ex.getMessage());       
           
        } finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                setMensagem("Problemas ao Fechar PrepareStatement " + ex.getMessage());
            }
        }        
        return lista;        
    }
    
    public Collection<CapturaAtual> listacFO(Equipamento equip) {
        String sql = "select cap.* " +
                     "from capturaatual cap, eventos ev " +
                     "where cap.codEvento = ev.codEvento " +
                     "and ev.formaOnda = 1 " +
                     "and cap.codEquip = ? " +
                     "order by cap.codcaptura " +
                     " limit 1000";

        //Collection<CapturaAtual> lista = new ArrayList();
        // Ajuste da coleção de objetos
        lista.clear();
       
        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        try {
            ps.setInt(1, equip.getCodEquip());
            lista = montaLista(ps);

            if (!lista.isEmpty()) {
                setMensagem("Relação de Formas de onda gerada com sucesso");
                return lista;
            }
        } catch (Exception ex) {
            setMensagem("Erro de SQL " + ex.getMessage());
        } finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                setMensagem("Problemas ao Fechar PrepareStatement " + ex.getMessage());
            }
        }
        
        return lista;
    }
    
    public Collection<CapturaAtual> listacFO() {
        String sql = "select cap.* " +
                     "from capturaatual cap, eventos ev " +
                     "where cap.codEvento = ev.codEvento " +
                     "and ev.formaOnda = 1 " +
                     "order by cap.codcaptura " +
                     " limit 1000";

        //Collection<CapturaAtual> lista = new ArrayList();
        // Ajuste da coleção de objetos
        lista.clear();
       
        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        try {
            lista = montaLista(ps);
            if (!lista.isEmpty()) {
                setMensagem("Relação de Formas de onda gerada com sucesso");
                return lista;
            }
        } catch (Exception ex) {
            setMensagem("Erro de SQL " + ex.getMessage());       
           
        }  finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                setMensagem("Problemas ao Fechar PrepareStatement " + ex.getMessage());
            }
        }       
        return lista;
    }                              
    
    public Collection<CapturaAtual> listaFugas(Equipamento equip, Calendar dataIni, Calendar dataFim) {
        String sql = "select * "
                   + "from capturaatual cap "
                   + "where cap.codEvento = 1 " // fuga
                   + "and cap.codEquip = ? "            
                   + "and date(cap.dataatual) >= ? "
                   + "and date(cap.dataatual) <= ?"
                   + "limit 1000; ";

        //Collection<CapturaAtual> lista = new ArrayList();
        // Ajuste da coleção de objetos
        lista.clear();
       
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
           
        }  finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                setMensagem("Problemas ao Fechar PrepareStatement " + ex.getMessage());
            }
        }        
        return lista;        
    }
    
    public Collection<CapturaAtual> listaFugas(Date dta) {
        String sql = "select * " +
                     "from capturaatual " +
                     "where date(dataatual) = ? " +
                     "and codevento = 1 " +                
                     "limit 1000; ";

        lista.clear();
       
        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        try {
            ps.setDate(1, dta);
            
            
            lista = montaLista(ps);
            
            
            if (!lista.isEmpty()) {
                setMensagem("Relação de Fugas gerada com sucesso");
                return lista;
            }
        } catch (Exception ex) {
            setMensagem("Erro de SQL " + ex.getMessage());       
           
        }  finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                setMensagem("Problemas ao Fechar PrepareStatement " + ex.getMessage());
            }
        }        
        return lista;        
    }

    
    
    public Collection<CapturaAtual> listaFugas(Timestamp dta) {
        String sql = "select * " +
                     "from capturaatual cap " +
                     "where dataatual = ? " +
                     "and codevento = 1 " +                
                     "limit 1000; ";

        lista.clear();
       
        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        try {
            ps.setTimestamp(1, dta);
            
            //System.out.println(ps.toString());
            lista = montaLista(ps);
            
            if (!lista.isEmpty()) {
                setMensagem("Relação de Fugas gerada com sucesso");
                return lista;
            }
        } catch (Exception ex) {
            setMensagem("Erro de SQL " + ex.getMessage());       
           
        }  finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                setMensagem("Problemas ao Fechar PrepareStatement " + ex.getMessage());
            }
        }        
        return lista;        
    }
    
    public Collection<CapturaAtual> montaLista(PreparedStatement ps) throws Exception{               
        
        //Collection<CapturaAtual> lista = new ArrayList();
        lista.clear();
        
        ResultSet rs = ps.executeQuery();
        
        try {                        
            while (rs.next() == true) {
                //obtemObjeto(rs);
                CapturaAtual coa = obtemObjeto(rs);
                
                lista.add(coa);
            }
            if (!lista.isEmpty()) {
                setMensagem("Relação de equipamentos gerada com sucesso");
                return lista;
            }
            setMensagem("Não existem registros para a pesquisa");
        } catch (SQLException e) {            
            mensagem = "Erro de SQL: " + e.getMessage();
            throw e;
        } finally{
            rs.close();
        }
            
        return lista;
    }
    
    public Collection<CapturaAtual> lista(Equipamento equip) {
        String sql = "select * from capturaatual "
                   + "where codEquip = ? " 
                   + " limit 1000";

        // Ajuste da coleção de objetos
        Collection<CapturaAtual> lista = new ArrayList();              
       
        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        try {
            ps.setInt(1, equip.getCodEquip());
            lista = montaLista(ps);

            if (!lista.isEmpty()) {
                setMensagem("Relação de Formas de onda gerada com sucesso");
                return lista;
            }
        } catch (Exception ex) {
            setMensagem("Erro de SQL " + ex.getMessage());
        }
        
        return lista;
    }
    
    public Collection<CapturaAtual> lista(int codusosala, int tomada ) {
        String sql = "select * \n" +
                     "from capturaatual cap, usosala uso, usosalacaptura usc " +
                     "where uso.codusosala = usc.codusosala " +
                     "and uso.codusosala = ? " +
                     "and usc.codcaptura = cap.codcaptura " +
                     "and cap.codtomada = ? " +
                     " limit 1000";

        // Ajuste da coleção de objetos
        Collection<CapturaAtual> lista = new ArrayList();              
       
        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        try {
            ps.setInt(1, codusosala);
            ps.setInt(2, tomada);
            lista = montaLista(ps);

            if (!lista.isEmpty()) {
                setMensagem("Relação de Formas de onda gerada com sucesso");
                return lista;
            }
        } catch (Exception ex) {
            setMensagem("Erro de SQL " + ex.getMessage());
        }
        
        return lista;
    }        
    
    public Collection<CapturaAtual> lista() {
        String sql = "select * from capturaatual "
                   + " limit 1000";

         // Ajuste da coleção de objetos
        Collection<CapturaAtual> lista = new ArrayList();              
       
        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        try {
            lista = montaLista(ps);
            if (!lista.isEmpty()) {
                setMensagem("Relação de Formas de onda gerada com sucesso");
                return lista;
            }
        } catch (Exception ex) {
            setMensagem("Erro de SQL " + ex.getMessage());       
           
        }        
        return lista;
    }

    public CapturaAtual localiza(int codCapturaAtual) {
        String sql = "select * from capturaatual "
                   + "where codCaptura = ? ";                
              
        try {
            PreparedStatement ps = Conexao.getPreparedStatement(sql);

            ps.setInt(1, codCapturaAtual);
        
            ResultSet rs = ps.executeQuery();
            while (rs.next() == true) {
                CapturaAtual coa = obtemObjeto(rs);
            
                mensagem = "Captura de forma de onda localizada";
                return coa;
            }
            mensagem = "Forma de onda Não localizada !";
        } catch (Exception e) {
            mensagem = "Erro de SQL: " + e.getMessage();
        }
        return null;
    }
    
    private CapturaAtual obtemObjeto(ResultSet rs){
        String sqldet = "select * from harmatual "
                      + "where codCaptura = ? "
                      + "order by codHarmonica";    
        String sqldetcount = "select count(*) as number from harmatual "
                      + "where codCaptura = ? ; ";
        int harmonicas=0;
    
        CapturaAtual coa = new CapturaAtual();
        try {
            // mapear os dados recuperados do BD para o Objeto
            coa.setCodCaptura(rs.getInt("codCaptura"));
            coa.setCodTipoOnda(tpdao.localiza(rs.getInt("codTipoOnda")));
            coa.setCodEquip(eqdao.localiza(rs.getInt("codEquip")));
            coa.setCodEvento(evdao.localiza(rs.getInt("codEvento")));
            coa.setCodTomada(tmdao.localiza(rs.getInt("codTomada")));
            coa.setValorMedio(rs.getFloat("valorMedio"));
            coa.setVM2(rs.getFloat("VM2"));
            coa.setOffset(rs.getFloat("offset"));
            coa.setGain(rs.getFloat("gain"));
            coa.setEficaz(rs.getFloat("eficaz"));
            coa.setDataAtual(Conversoes.TimeStampToCalendar(rs.getTimestamp("dataAtual")));
            coa.setUnder(rs.getInt("under"));
            coa.setOver(rs.getInt("over"));
            coa.setDuration(rs.getInt("duration"));
            
            Collection <HarmAtual> listadet = new ArrayList<>();
            
            PreparedStatement psdet = Conexao.getPreparedStatement(sqldetcount);             
            psdet.setInt(1, coa.getCodCaptura() );
            ResultSet rsdet = null;
            /* pega as harmonicas. garanten que irão vir o número determinado, nem mais nem menos 
               tentar uma vez, se não der, espera 1/2s e tenta mais duas. Se não dar, da erro            
            
            for(int i=0; i < 3; i++){
                rsdet = psdet.executeQuery();
            
                if (rsdet.next() == true){
                    harmonicas = rsdet.getInt("number");
                    //System.out.println("Number " + harmonicas);
                    if (harmonicas == Configuracoes.HARMONICAS + 1)
                        break;
                    else
                        try {
                            Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            
            rsdet.close();
            psdet.close();
            */
            
            psdet = Conexao.getPreparedStatement(sqldet);             
            psdet.setInt(1, coa.getCodCaptura() );
            rsdet = psdet.executeQuery();                                    
            
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

        } catch (SQLException ex) {
            System.out.println("CapturaAtualDAO");
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Erro em obtemObjeto de CapturaOndaAtualDAO ");
        }
        return coa;
    }                


    /* Verificar que está muito lento
    private CapturaAtual obtemObjeto(ResultSet rs) throws Exception{
        String sqldet = "select * from harmatual "
                      + "where codCaptura = ? "
                      + "order by codHarmonica";    
        String sqldetcount = "select count(*) as number from harmatual "
                      + "where codCaptura = ? ; ";
        int harmonicas=0;
    
        CapturaAtual coa = new CapturaAtual();
        try {
            // mapear os dados recuperados do BD para o Objeto
            coa.setCodCaptura(rs.getInt("codCaptura"));
            coa.setCodTipoOnda(tpdao.localiza(rs.getInt("codTipoOnda")));
            coa.setCodEquip(eqdao.localiza(rs.getInt("codEquip")));
            coa.setCodEvento(evdao.localiza(rs.getInt("codEvento")));
            coa.setCodTomada(tmdao.localiza(rs.getInt("codTomada")));
            coa.setValorMedio(rs.getFloat("valorMedio"));
            coa.setVM2(rs.getFloat("VM2"));
            coa.setOffset(rs.getFloat("offset"));
            coa.setGain(rs.getFloat("gain"));
            coa.setEficaz(rs.getFloat("eficaz"));
            coa.setDataAtual(Conversoes.TimeStampToCalendar(rs.getTimestamp("dataAtual")));
            coa.setUnder(rs.getInt("under"));
            coa.setOver(rs.getInt("over"));
            coa.setDuration(rs.getInt("duration"));
            
            Collection <HarmAtual> listadet = new ArrayList<>();
            
            PreparedStatement psdet = Conexao.getPreparedStatement(sqldetcount);             
            psdet.setInt(1, coa.getCodCaptura() );
            /* pega as harmonicas. garanten que irão vir o número determinado, nem mais nem menos 
               tentar uma vez, se não der, espera 1/2s e tenta mais duas. Se não dar, da erro
            
            ResultSet rsdet = null;
            boolean flag = false;
            for(int i=0; i < 3; i++){
                rsdet = psdet.executeQuery();
            
                if (rsdet.next() == true){
                    harmonicas = rsdet.getInt("number");
                    if (harmonicas == Configuracoes.HARMONICAS){
                        flag = true;
                        break;
                    }
                        
                    else
                        try {
                            Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }                    
            }            
            
            rsdet.close();
            psdet.close();
            
            if (flag){ // conseguiu as 12 harmonicas
                psdet = Conexao.getPreparedStatement(sqldet);             
                psdet.setInt(1, coa.getCodCaptura() );
                rsdet = psdet.executeQuery();                                    

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

                 rsdet.close();
                 psdet.close();
            }
            else{
                JOptionPane.showMessageDialog(null, "Onda " + String.valueOf(coa.getCodCaptura()) + " Nao conseguiu ter todas harmonicas selecionadas");
                System.exit(1);
//                throw (new Exception(new Exception("Não capturou todas as Harmonicas da onda " + coa.getCodCaptura())));
            }                             

        } catch (SQLException ex) {
            System.out.println("CapturaAtualDAO");
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Erro em obtemObjeto de CapturaOndaAtualDAO ");
        }
        return coa;
    }                
*/
     public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

}
