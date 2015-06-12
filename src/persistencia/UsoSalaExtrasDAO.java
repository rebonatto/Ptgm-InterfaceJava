/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia;

import Uteis.Conversoes;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import modelo.Formularios.BeanSupervisao;
import modelo.CapturaAtual;
import modelo.Equipamento;
import modelo.UsoSala;

/**
 *
 * @author rebonatto
 */

public class UsoSalaExtrasDAO {
    private String mensagem;        

    /* retorna as capturas de um usosala */
    public Collection <CapturaAtual> listaCapturas(UsoSala uso){
        String sql = "select cap.* " +
                     "from capturaatual cap, usosalacaptura usc " +
                     "where usc.codcaptura = cap.codcaptura " +
                     "and usc.codusosala = ? " +
                     "order by codTomada, cap.codCaptura;"; 
        
        CapturaAtualDAO capdao = new CapturaAtualDAO();
                
        Collection<CapturaAtual> lista = new ArrayList();
        try {
            PreparedStatement ps = Conexao.getPreparedStatement(sql);
            
            ps.setInt(1, uso.getCodUsoSala());                        
                   
            lista = capdao.montaLista(ps);
            if (!lista.isEmpty()) {
                setMensagem("Relação de Formas de onda gerada com sucesso");
                return lista;
            }
        } catch (Exception ex) {
            setMensagem("Erro de SQL " + ex.getMessage());       
           
        }        
        return lista;
            
    }
    
    /* retorna os equipamentos usados em um uso sala */
    public Collection <BeanSupervisao> listaUltimasFO(int sala){
        String sql = "select cap.codcaptura, cap.codevento, evt.desc, cap.codtomada, eqp.codequip, eqp.desc, eqp.rfid, eqp.tempouso " +
                     "from capturaatual cap, usosalacaptura usc, equipamento eqp, eventos evt " +
                     "where usc.codusosala = f_ultUsoSalaAtiva(?) " +
                     "and   usc.codcaptura = cap.codcaptura " +
                     "and   cap.codcaptura = f_ultCodCaptura(?, cap.codequip) " +
                     "and   cap.codequip = eqp.codequip " +
                     "and   cap.codevento = evt.codevento " +
                     "and   evt.formaOnda = 1 " +
                     "order by cap.codEquip; ";
        
        EquipamentoDAO  eqpdao = new EquipamentoDAO();
        EventoDAO       evtdao = new EventoDAO();
        CapturaAtualDAO capdao = new CapturaAtualDAO();
                
        Collection<BeanSupervisao> lista = new ArrayList();
        try {
            PreparedStatement ps = Conexao.getPreparedStatement(sql);
            
            ps.setInt(1, sala);
            ps.setInt(2, sala);
            
            ResultSet rs = ps.executeQuery();            

            while (rs.next() == true) {          
                BeanSupervisao bean = new BeanSupervisao();
                
                bean.setNumTomada(rs.getInt("codTomada"));
                bean.setCodEquip(eqpdao.localiza(rs.getInt("codEquip")));
                bean.setCodEvento(evtdao.localiza(rs.getInt("codEvento")));
                bean.setCodCaptura(capdao.localiza(rs.getInt("codCaptura")));
                
                lista.add(bean);                
            }
            if (!lista.isEmpty()) {
                setMensagem("Relação de Equipamentos e ultimos eventos numa sala gerada com sucesso");
                return lista;
            }
        } catch (SQLException e) {
            mensagem = "Erro de SQL: " + e.getMessage();
        }
        return lista;    
    }
    
    public int ultimoUsoSalaAtivo(int sala){
        String sql = "select f_ultUsoSalaAtiva(?) as ultima;";

        PreparedStatement ps = Conexao.getPreparedStatement(sql);

        try {
            ps.setInt(1, sala);
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
    
    public Collection <Equipamento> listaEquipUsoSala(int usosala){
        String sql = "select eqp.* " +
                     "from usosalaequip uso, equipamento eqp " +
                     "where uso.codequip = eqp.codequip " +
                     "and codusosala = ? " +
                     "order by eqp.codequip;";

        MarcaDAO mcadao = new MarcaDAO();
        ModeloDAO mdldao = new ModeloDAO();
        TipoDAO tpdao = new TipoDAO();
        
        Collection<Equipamento> lista = new ArrayList();
        try {
            PreparedStatement ps = Conexao.getPreparedStatement(sql);
            
            ps.setInt(1, usosala);
            
            ResultSet rs = ps.executeQuery();            

            while (rs.next() == true) {          
                Equipamento equip = new Equipamento();
                
                // mapear os dados recuperados do BD para o Objeto
                equip.setCodEquip(rs.getInt("codEquip"));
                equip.setCodMarca(mcadao.localiza(rs.getInt("codMarca")));                
                equip.setCodModelo(mdldao.localiza(rs.getInt("codModelo")));
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
                setMensagem("Relação de Equipamentos gerada com sucesso");
                return lista;
            }
        } catch (SQLException e) {
            mensagem = "Erro de SQL: " + e.getMessage();
        }
        return lista;    
    }
    
    public Collection <BeanSupervisao> listaUltimasFO(Collection <Equipamento> listaeqp, String alias){
        String sql = "select max(codcaptura) as maximo " +
                     "from capturaatual cap ";
        
        sql = sql + "where " + EventosCFormaOnda(alias) + "and cap.codequip = ?; ";
        
        CapturaAtualDAO capdao = new CapturaAtualDAO();
        
        int codcap;
                        
        Collection<BeanSupervisao> lista = new ArrayList();
        try {
            for(Equipamento eqp : listaeqp){
                PreparedStatement ps = Conexao.getPreparedStatement(sql);
            
                ps.setInt(1, eqp.getCodEquip());
            
                ResultSet rs = ps.executeQuery();            

                if (rs.next() ) {          
                    codcap = rs.getInt("maximo");
                    if (codcap != 0){
                        BeanSupervisao bean = new BeanSupervisao();
                        CapturaAtual cap = capdao.localiza(codcap);

                        bean.setCodCaptura(cap);
                        bean.setNumTomada(cap.getCodTomada().getCodTomada());
                        bean.setCodEquip(eqp);
                        bean.setCodEvento(cap.getCodEvento());

                        lista.add(bean);                
                    }
                }
            }
            if (!lista.isEmpty()) {
                setMensagem("Relação de Equipamentos e ultimos eventos numa sala gerada com sucesso");
                return lista;
            }
        } catch (SQLException e) {
            mensagem = "Erro de SQL: " + e.getMessage();
        }
        return lista;    
    }
    
    public String EventosCFormaOnda(String alias){
        String sql = "select codEvento " +
                     "from eventos " +
                     "where formaonda = 1; ";
        boolean flag = false;
        
        String retorno = new String("(");
        try {
            PreparedStatement ps = Conexao.getPreparedStatement(sql);
                       
            ResultSet rs = ps.executeQuery();            

            while (rs.next() == true) {          
                if (flag)
                    retorno = retorno + " or ";
                
                retorno = retorno + alias + ".codEvento = " + String.valueOf(rs.getInt("codEvento"));
                
                flag = true;
            }

            if (flag) {
                setMensagem("Relação de Codigos de eventos com formadeonda gerada com sucesso");
                retorno = retorno + " )";
                return retorno;
            }
        } catch (SQLException e) {
            mensagem = "Erro de SQL: " + e.getMessage();
        }
        return null;    
    }
    /* retorna se tem evento de fuga no ultimo usosala das salas ativas */
    public Collection <Integer> listaFugaSalas(){
        String sql = "select distinct usc.codusosala, uso.codsala, tom.codtomada " +
                     "from capturaatual cap, tomada tom, usosalacaptura usc, usosala uso " +
                     "where cap.codcaptura = usc.codcaptura " +
                     "and usc.codusosala = uso.codusosala " +
                     "and uso.ativa = 1 " + /* Uso sala esta ativo */
                     "and usc.codusosala = f_ultUsoSala(uso.codsala) " +
                     "and tom.codtomada = cap.codtomada  " +
                     "and cap.codevento = 1 "; /* evento de fuga */
                
        Collection<Integer> lista = new ArrayList();
        try {
            PreparedStatement ps = Conexao.getPreparedStatement(sql);
            
            ResultSet rs = ps.executeQuery();            

            while (rs.next() == true) {                                                              
                lista.add(rs.getInt("codsala"));                
            }
            if (!lista.isEmpty()) {
                setMensagem("Relação de sala com fuga gerada com sucesso");
                return lista;
            }
        } catch (SQLException e) {
            mensagem = "Erro de SQL: " + e.getMessage();
        }
        return lista;    
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
