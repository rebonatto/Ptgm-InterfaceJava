

package testePersistencia;

import modelo.Responsavel;
import persistencia.ResponsavelDAO;

/**
 *
 * @author cliente
 */
public class testeResp {

     public static void main(String[] args) {
        // TODO code application logic here
        ResponsavelDAO dao = new ResponsavelDAO();
        Responsavel obj = new Responsavel();

        obj.setDesc("jose");
        //obj.setCodResp(78);
       

        if (dao.Insere(obj)){
            System.out.println("inserido");
            int chave = dao.ultimaChave();
            System.out.println(chave);
          }

        else{
            System.out.println("deu erro" + dao.getMensagem());

        }

        for(Responsavel m : dao.lista())
             System.out.println(m.getCodResp() + "  " + m.getDesc());

    }


}
