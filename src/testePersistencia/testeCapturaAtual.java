/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 * @author Eduardo
 * Modified by: rebonatto
 */
 
package testePersistencia;

import Uteis.Conversoes;
import java.util.Calendar;
import modelo.CapturaAtual;
import modelo.Equipamento;
import modelo.HarmAtual;
import persistencia.CapturaAtualDAO;
import persistencia.EquipamentoDAO;
import persistencia.MarcaDAO;
import persistencia.TipoOndaDAO;

/**
 *
 * @author Eduardo
 * Modified by: rebonatto
 */
 
public class testeCapturaAtual {

    public static void main(String[] args) {
        // TODO code application logic here
        CapturaAtualDAO dao = new CapturaAtualDAO();
        EquipamentoDAO eqdao = new EquipamentoDAO();
        Equipamento equip = new Equipamento();
        TipoOndaDAO tpdao = new TipoOndaDAO();
        MarcaDAO mcdao = new MarcaDAO();

//        EventosDAO evdao = new EventosDAO();
//        
//        System.out.println(evdao.localiza(1).getCodEvento() +" " + evdao.localiza(1).getDesc());
//        System.out.println(evdao.getMensagem());
//        
//        System.out.println();
        equip.setCodEquip(2);
        
        Calendar d = Calendar.getInstance();
        d.set(2013, 02, 11);
        
        for(CapturaAtual coa : dao.listaFoFaseFuga(6, true, true)){
            System.out.println("Codigo " + coa.getCodCaptura());
            System.out.println("Tomada: " + coa.getCodTomada().getCodTomada() + " " + coa.getCodTomada().getDesc() );
            System.out.println("TipoOnda " + coa.getCodTipoOnda().getCodTipoOnda() + " " + coa.getCodTipoOnda().getDesc() );
            System.out.println("Equipamento " + coa.getCodEquip().getCodEquip()+ " " + coa.getCodEquip().getDesc() );
            System.out.println("Evento " + coa.getCodEvento().getCodEvento()+ " " + coa.getCodEvento().getDesc() );
            System.out.println("ValorMedio " + coa.getValorMedio()+ " Offset " + coa.getOffset() );
            System.out.println("gain " + coa.getGain()+ " eficaz " + coa.getEficaz());
            System.out.println("Data " + Conversoes.CalendarToDate(coa.getDataAtual() ));
            System.out.println("Hora " + Conversoes.CalendarToTime(coa.getDataAtual()));
            /*
            for(HarmAtual oa : coa.getHarmAtual())
                System.out.println("Harmonica " + oa.getHarmonica() + " Sen " + oa.getSen() + " Cos " + oa.getCos());
            */
            System.out.println("----------------------------------");
        }
        System.out.println(dao.getMensagem());
        
        /*
        System.out.println("\nOnda 1");
        CapturaOndaAtual coa = dao.localiza(1);
        System.out.println(dao.getMensagem());
        System.out.println("Codigo " + coa.getCodOndaAtual() );
        System.out.println("Tomada " + coa.getCodTomada().getCodTomada() + " " + coa.getCodTomada().getDesc() );
        System.out.println("Tipo " + coa.getCodTipoOnda().getCodTponda()+ " " + coa.getCodTipoOnda().getDesc() );
        System.out.println("Equipamento " + coa.getCodEquipamento().getCodEquip()+ " " + coa.getCodEquipamento().getDesc() );
        System.out.println("Evento " + coa.getCodEvento().getCodEvento()+ " " + coa.getCodEvento().getDesc() );
        System.out.println("ValorMedio " + coa.getValorMedio()+ " Offset " + coa.getOffset() );
        System.out.println("gain " + coa.getGain()+ " eficaz " + coa.getEficaz());
        System.out.println("Data " + coa.getDataAtual() );
        for(OndaAtual oa : coa.getHarmAtual())
            System.out.println("Harmonica " + oa.getHarmonica() + " Sen " + oa.getSen() + " Cos " + oa.getCoss());
                
        */        
        
//        equip.setDesc("TESTEEEEE");
//        mdl.setCodModelo(5);
//        tp.setCodTipo(7);
//        mca.setCodMarca(31);
//        
//        equip.setCodMarca(mca);
//        equip.setCodModelo(mdl);
//        equip.setCodTipo(tp);
//        equip.setDataUltimaFalha(Calendar.getInstance());
//        equip.setDataUltimaManutencao(Calendar.getInstance());   
//        equip.setRfid("123456");
//        equip.setCodPatrimonio("Meu");
//
////        if (eqdao.Insere(equip)){
////            System.out.println("inserido");
////            int chave = dao.ultimaChave();
////            System.out.println(chave);
////          }
////
////        else{
////            System.out.println("deu erro" + eqdao.getMensagem());
////
////        }
//
//        for(Equipamento eq : eqdao.lista())
//             System.out.println(eq.getCodEquip() + "  " + eq.getDesc() + "  " + eq.getCodMarca().getCodMarca());

        System.out.println("Lista");
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
