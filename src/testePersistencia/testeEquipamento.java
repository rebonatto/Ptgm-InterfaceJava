/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 * @author Eduardo
 * Modified by: rebonatto
 */
 
package testePersistencia;

import java.util.Calendar;
import modelo.Equipamento;
import modelo.Marca;
import modelo.Modelo;
import modelo.Tipo;
import persistencia.EquipamentoDAO;
import persistencia.MarcaDAO;
import persistencia.ModeloDAO;
import persistencia.TipoDAO;

/**
 *
 * @author Eduardo
 * Modified by: rebonatto
 */
 
public class testeEquipamento {

    public static void main(String[] args) {
        // TODO code application logic here
        EquipamentoDAO eqdao = new EquipamentoDAO();
        Equipamento equip = new Equipamento();
        TipoDAO tpdao = new TipoDAO();
        Tipo tp = new Tipo();
        MarcaDAO mcdao = new MarcaDAO();
        Marca mca = new Marca();
        ModeloDAO dao = new ModeloDAO();
        Modelo mdl = new Modelo();
/*
        equip.setDesc("TESTEEEEE");
        mdl.setCodModelo(5);
        tp.setCodTipo(7);
        mca.setCodMarca(31);
        
        equip.setCodMarca(mca);
        equip.setCodModelo(mdl);
        equip.setCodTipo(tp);
        equip.setDataUltimaFalha(Calendar.getInstance());
        equip.setDataUltimaManutencao(Calendar.getInstance());   
        equip.setRfid("123456");
        equip.setCodPatrimonio("Meu");
*/
        if (eqdao.Insere(equip)){
            System.out.println("inserido");
            int chave = dao.ultimaChave();
            System.out.println(chave);
          }
        else{
            System.out.println("deu erro" + eqdao.getMensagem());

        }

        for(Equipamento eqp : eqdao.lista())
             System.out.println(eqp.getCodEquip() + "  " + eqp.getDesc() + "  " + eqp.getCodMarca().getCodMarca() + " "
                     + eqp.getCodModelo().getCodModelo() + " "
                     + eqp.getCodTipo().getCodTipo() + " "
                     + eqp.getRfid() + " " + eqp.getCodPatrimonio() + " " + eqp.getDataUltimaFalha() + " "
                     + eqp.getDataUltimaManutencao() + " " + eqp.getTempoUso());

        
        System.out.println(eqdao.getMensagem());
//        for(Equipamento equip : eqdao.lista()){
//            System.out.println(equip.getCodEquip());
//            System.out.println(equip.getCodMarca().getCodMarca());
//            System.out.println(equip.getCodModelo().getCodModelo());
//            System.out.println(equip.getCodTipo().getCodTipo());
//            System.out.println(equip.getDesc());
//
//
//            System.out.println("--------------------------------------");
//        }

    }

   }
