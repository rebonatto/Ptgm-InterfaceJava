/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testePersistencia;

import static Uteis.Configuracoes.DATALIMITEVM;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Calendar;
import modelo.Marca;
import modelo.Evento;
import persistencia.MarcaDAO;
import persistencia.Conexao;


/**
 *
 * @author rebonatto
 */
public class testaConexao {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//       String sql = "SELECT * from Cidade;";
//       PreparedStatement ps = Conexao.getPreparedStatement(sql);
        
        float v = 3.14F;
        
        System.out.println((int) v);
        
       Calendar base = Calendar.getInstance();
       base.set(2015, 0, 1); // 01/01/2014
       Calendar hoje = Calendar.getInstance();
       
        System.out.println("Base " + base.getTime());
        System.out.println("Hoje " + hoje.getTime());
        
       if (base.compareTo(hoje) > 0) 
            System.out.println("base Maior");
       else
            System.out.println("base Menor");

       MarcaDAO dao = new MarcaDAO();
       Marca mca = dao.localiza(3);

       System.out.println(dao.getMensagem());
       for(Marca mrca : dao.lista()){
            System.out.println("Objeto: codigo " + mrca.getCodMarca() + " " +
                            "desc: " + mrca.getDesc() + " " );
        }
                            
    }

}
