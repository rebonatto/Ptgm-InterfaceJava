/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FormPControle.java
 *
 * Created on Mar 19, 2012, 9:52:42 PM
 */
package Visualizacao;

import olds.Threads.TAtualizaForm;
import olds.Threads.TFormControle;
import Uteis.Configuracoes;
import Uteis.Conversoes;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Time;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import modelo.UsoSala;
import persistencia.UsoSalaDAO;
import persistencia.UsoSalaExtrasDAO;

/**
 *
 * @author Eduardo
 * rewrite by rebonatto
 */

public class FormPControle extends javax.swing.JDialog {
    private String mensagem;
    
    private UsoSalaDAO usosaladao = new UsoSalaDAO();
    private UsoSalaExtrasDAO extrasdao = new UsoSalaExtrasDAO();        
    
    int sala[]    = new int[Configuracoes.MAXSALAS];
    int auxsala[] = new int[Configuracoes.MAXSALAS];
    int alerta[] = new int[Configuracoes.MAXSALAS];
    
    Calendar horasInicio[] = new Calendar[Configuracoes.MAXSALAS];
    Calendar cal = Calendar.getInstance();
    Time tem = new Time(0);                      
    
    private TFormControle thread1;
    private TAtualizaForm thAtualiza;
    
    int testeLigaDesl = 0;    
    

    /** Creates new form FormPControle */
    public FormPControle(java.awt.Frame parent, boolean modal)  {
        super(parent, modal);
        initComponents();

        /* Ajusta tamanho do panel principal */
        add(new JScrollPane(jPP));
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        jPP.setSize(dimension.width, (dimension.height - 40));
        this.setSize(dimension.width, dimension.height - 40);
        setTitle("Painel de Controle");

        //AtualizaInterface();        

        for(int i = 0; i < Configuracoes.MAXSALAS; i++)
            auxsala[i] = -1;
               
        /* thread para atualizar os relogios, as salas ativas e os alertas */
        new Thread() {

            public void run() {                
                while (true) {
                    AtualizaInterface();
                    try {
                         Thread.sleep(Configuracoes.TEMPOATUALIZARELOGIOS);
                     } catch (InterruptedException ex) {
                         System.out.println("Erro " + ex.getMessage());
                     }                                
                 }
            }
        }.start();
    }

    public void AtualizaInterface() {          
        int s;                  
        
        for(s=0; s < Configuracoes.MAXTOMADAS; s++)
            sala[s] = alerta[s] = 0 ;
        
        /* verifica as salas ativas e atualiza a hora de inicio */
        for(UsoSala us : usosaladao.getSalasAtivas()){
            s = us.getCodSala().getCodSala();
            sala[s - 1] = s; 
            horasInicio[s - 1] = us.getHoraInicio();     
        }
        
        for (Integer salaAlerta : extrasdao.listaFugaSalas()){
            s = salaAlerta.intValue();
            alerta[s - 1] = s;                    
        }
        cal = Calendar.getInstance();                                        
        
        if(auxsala[0] != sala[0])
            auxsala[0] = atualizaSala(0, Sala01Jtf, Sala01BtnOn, Sala01BtnOff, Sala01BtnAlerta, Sala01btnDetalhes);        
        if(auxsala[1] != sala[1])
            auxsala[1] = atualizaSala(1, Sala02Jtf, Sala02BtnOn, Sala02BtnOff, Sala02BtnAlerta, Sala02btnDetalhes);        
        if(auxsala[2] != sala[2])
            auxsala[2] = atualizaSala(2, Sala03Jtf, Sala03BtnOn, Sala03BtnOff, Sala03BtnAlerta, Sala03btnDetalhes);        
        if(auxsala[3] != sala[3])
            auxsala[3] = atualizaSala(3, Sala04Jtf, Sala04BtnOn, Sala04BtnOff, Sala04BtnAlerta, Sala04btnDetalhes);        
        
        if(auxsala[4] != sala[4])
            auxsala[4] = atualizaSala(4, Sala05Jtf, Sala05BtnOn, Sala05BtnOff, Sala05BtnAlerta, Sala05btnDetalhes);        
        if(auxsala[5] != sala[5])
            auxsala[5] = atualizaSala(5, Sala06Jtf, Sala06BtnOn, Sala06BtnOff, Sala06BtnAlerta, Sala06btnDetalhes);        
        if(auxsala[6] != sala[6])
            auxsala[6] = atualizaSala(6, Sala07Jtf, Sala07BtnOn, Sala07BtnOff, Sala07BtnAlerta, Sala07btnDetalhes);        
        if(auxsala[7] != sala[7])
            auxsala[7] = atualizaSala(7, Sala08Jtf, Sala08BtnOn, Sala08BtnOff, Sala08BtnAlerta, Sala08btnDetalhes);        
            
        if(auxsala[8] != sala[8])
            auxsala[8] = atualizaSala(8, Sala09Jtf, Sala09BtnOn, Sala09BtnOff, Sala09BtnAlerta, Sala09btnDetalhes);        
        if(auxsala[9] != sala[9])
            auxsala[9] = atualizaSala(9, Sala10Jtf, Sala10BtnOn, Sala10BtnOff, Sala10BtnAlerta, Sala10btnDetalhes);        
        if(auxsala[10] != sala[10])
            auxsala[10] = atualizaSala(10, Sala11Jtf, Sala11BtnOn, Sala11BtnOff, Sala11BtnAlerta, Sala11btnDetalhes);        
        if(auxsala[11] != sala[11])
            auxsala[11] = atualizaSala(11, Sala12Jtf, Sala12BtnOn, Sala12BtnOff, Sala12BtnAlerta, Sala12btnDetalhes);        

        if(auxsala[12] != sala[12])
            auxsala[12] = atualizaSala(12, Sala13Jtf, Sala13BtnOn, Sala13BtnOff, Sala13BtnAlerta, Sala13btnDetalhes);        
        if(auxsala[13] != sala[13])
            auxsala[13] = atualizaSala(13, Sala14Jtf, Sala14BtnOn, Sala14BtnOff, Sala14BtnAlerta, Sala14btnDetalhes);        
        if(auxsala[14] != sala[14])
            auxsala[14] = atualizaSala(14, Sala15Jtf, Sala15BtnOn, Sala15BtnOff, Sala15BtnAlerta, Sala15btnDetalhes);        
        if(auxsala[15] != sala[15])
            auxsala[15] = atualizaSala(15, Sala16Jtf, Sala16BtnOn, Sala16BtnOff, Sala16BtnAlerta, Sala16btnDetalhes);                                               
    }

    private int atualizaSala(int indice, JLabel tempo, JTextField BtnOn, JTextField BtnOff, JToggleButton btnAlerta, JButton btnDetalhes){
        if (sala[indice] != 0) {                  
//            System.out.println(Conversoes.CalendarToTime(cal) + " " + Conversoes.CalendarToTime(horasInicio[indice]))
//           tem.setTime(cal.getTime().getTime() - horasInicio[indice].getTime().getTime());                    
           tempo.setText(Uteis.DiffTempo.DiferencaTempo(horasInicio[indice], cal));
           BtnOn.setBackground(Color.green);
           BtnOff.setBackground(Color.LIGHT_GRAY);
           btnDetalhes.setEnabled(true);
           if (alerta[indice] != 0){ /* Sala com alerta ligado */
               btnAlerta.setEnabled(true);
           }
           else {
               btnAlerta.setEnabled(false);           
           }
        } else {
           tempo.setText("hh:mm:ss");                    
           BtnOn.setBackground(Color.LIGHT_GRAY);
           BtnOff.setBackground(Color.RED);
           btnDetalhes.setEnabled(false);
        }
        return auxsala[indice];        
    }        

    private void ChamaFormSupervisao(int sala){
        FormPSupervisao painel = null;

        painel = new FormPSupervisao(new JFrame(), true, sala);        
        painel.setVisible(true);
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPP = new javax.swing.JPanel();
        sala01 = new javax.swing.JPanel();
        Sala01BtnOff = new javax.swing.JTextField();
        Sala01BtnOn = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        Sala01btnDetalhes = new javax.swing.JButton();
        Sala01BtnAlerta = new javax.swing.JToggleButton();
        jrbSala1 = new javax.swing.JRadioButton();
        Sala01Jtf = new javax.swing.JLabel();
        sala02 = new javax.swing.JPanel();
        Sala02BtnOff = new javax.swing.JTextField();
        Sala02BtnOn = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        Sala02btnDetalhes = new javax.swing.JButton();
        Sala02BtnAlerta = new javax.swing.JToggleButton();
        Sala02Jtf = new javax.swing.JLabel();
        sala03 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        Sala03btnDetalhes = new javax.swing.JButton();
        Sala03BtnAlerta = new javax.swing.JToggleButton();
        Sala03BtnOn = new javax.swing.JTextField();
        Sala03BtnOff = new javax.swing.JTextField();
        Sala03Jtf = new javax.swing.JLabel();
        sala04 = new javax.swing.JPanel();
        Sala04BtnOn = new javax.swing.JTextField();
        Sala04BtnOff = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        Sala04btnDetalhes = new javax.swing.JButton();
        Sala04BtnAlerta = new javax.swing.JToggleButton();
        Sala04Jtf = new javax.swing.JLabel();
        sala05 = new javax.swing.JPanel();
        Sala05BtnOff = new javax.swing.JTextField();
        Sala05BtnOn = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        Sala05btnDetalhes = new javax.swing.JButton();
        Sala05BtnAlerta = new javax.swing.JToggleButton();
        Sala05Jtf = new javax.swing.JLabel();
        sala06 = new javax.swing.JPanel();
        Sala06BtnOff = new javax.swing.JTextField();
        Sala06BtnOn = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        Sala06btnDetalhes = new javax.swing.JButton();
        Sala06BtnAlerta = new javax.swing.JToggleButton();
        Sala06Jtf = new javax.swing.JLabel();
        sala07 = new javax.swing.JPanel();
        Sala07BtnOff = new javax.swing.JTextField();
        Sala07BtnOn = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        Sala07btnDetalhes = new javax.swing.JButton();
        Sala07BtnAlerta = new javax.swing.JToggleButton();
        Sala07Jtf = new javax.swing.JLabel();
        sala08 = new javax.swing.JPanel();
        Sala08BtnOff = new javax.swing.JTextField();
        Sala08BtnOn = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        Sala08btnDetalhes = new javax.swing.JButton();
        Sala08BtnAlerta = new javax.swing.JToggleButton();
        Sala08Jtf = new javax.swing.JLabel();
        sala09 = new javax.swing.JPanel();
        Sala09BtnOff = new javax.swing.JTextField();
        Sala09BtnOn = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        Sala09btnDetalhes = new javax.swing.JButton();
        Sala09BtnAlerta = new javax.swing.JToggleButton();
        Sala09Jtf = new javax.swing.JLabel();
        sala10 = new javax.swing.JPanel();
        Sala10BtnOff = new javax.swing.JTextField();
        Sala10BtnOn = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        Sala10btnDetalhes = new javax.swing.JButton();
        Sala10BtnAlerta = new javax.swing.JToggleButton();
        Sala10Jtf = new javax.swing.JLabel();
        sala11 = new javax.swing.JPanel();
        Sala11BtnOff = new javax.swing.JTextField();
        Sala11BtnOn = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        Sala11btnDetalhes = new javax.swing.JButton();
        Sala11BtnAlerta = new javax.swing.JToggleButton();
        Sala11Jtf = new javax.swing.JLabel();
        sala12 = new javax.swing.JPanel();
        Sala12BtnOff = new javax.swing.JTextField();
        Sala12BtnOn = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        Sala12btnDetalhes = new javax.swing.JButton();
        Sala12BtnAlerta = new javax.swing.JToggleButton();
        Sala12Jtf = new javax.swing.JLabel();
        sala13 = new javax.swing.JPanel();
        Sala13BtnOff = new javax.swing.JTextField();
        Sala13BtnOn = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        Sala13btnDetalhes = new javax.swing.JButton();
        Sala13BtnAlerta = new javax.swing.JToggleButton();
        Sala13Jtf = new javax.swing.JLabel();
        sala14 = new javax.swing.JPanel();
        Sala14BtnOff = new javax.swing.JTextField();
        Sala14BtnOn = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        Sala14btnDetalhes = new javax.swing.JButton();
        Sala14BtnAlerta = new javax.swing.JToggleButton();
        Sala14Jtf = new javax.swing.JLabel();
        sala15 = new javax.swing.JPanel();
        Sala15BtnOff = new javax.swing.JTextField();
        Sala15BtnOn = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        Sala15btnDetalhes = new javax.swing.JButton();
        Sala15BtnAlerta = new javax.swing.JToggleButton();
        Sala15Jtf = new javax.swing.JLabel();
        sala16 = new javax.swing.JPanel();
        Sala16BtnOff = new javax.swing.JTextField();
        Sala16BtnOn = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        Sala16btnDetalhes = new javax.swing.JButton();
        Sala16BtnAlerta = new javax.swing.JToggleButton();
        Sala16Jtf = new javax.swing.JLabel();
        lbltempos2 = new javax.swing.JLabel();
        lblTempoS5 = new javax.swing.JLabel();
        lblTempoS6 = new javax.swing.JLabel();
        lblTempoS7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPP.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sala01.setBorder(javax.swing.BorderFactory.createTitledBorder("Sala 1"));
        sala01.setMaximumSize(new java.awt.Dimension(160, 270));
        sala01.setMinimumSize(new java.awt.Dimension(160, 270));
        sala01.setName(""); // NOI18N
        sala01.setPreferredSize(new java.awt.Dimension(160, 270));

        Sala01BtnOff.setBackground(new java.awt.Color(240, 240, 240));
        Sala01BtnOff.setText("  Off");

        Sala01BtnOn.setBackground(new java.awt.Color(255, 51, 102));
        Sala01BtnOn.setText("  On");

        jLabel7.setText("Em funcionamento");

        Sala01btnDetalhes.setText("Detalhes");
        Sala01btnDetalhes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sala01btnDetalhesActionPerformed(evt);
            }
        });

        Sala01BtnAlerta.setText("Alerta");
        Sala01BtnAlerta.setEnabled(false);
        Sala01BtnAlerta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sala01BtnAlertaActionPerformed(evt);
            }
        });

        Sala01Jtf.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        Sala01Jtf.setText("hh:mm:ss");

        javax.swing.GroupLayout sala01Layout = new javax.swing.GroupLayout(sala01);
        sala01.setLayout(sala01Layout);
        sala01Layout.setHorizontalGroup(
            sala01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sala01Layout.createSequentialGroup()
                .addGroup(sala01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sala01Layout.createSequentialGroup()
                        .addGroup(sala01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, sala01Layout.createSequentialGroup()
                                .addComponent(Sala01btnDetalhes)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jrbSala1)
                                .addGap(11, 11, 11))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, sala01Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(Sala01BtnOn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Sala01BtnOff, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, sala01Layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(Sala01BtnAlerta)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(Sala01Jtf, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        sala01Layout.setVerticalGroup(
            sala01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sala01Layout.createSequentialGroup()
                .addGroup(sala01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Sala01BtnOff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Sala01BtnOn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addGap(10, 10, 10)
                .addComponent(Sala01Jtf)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(sala01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Sala01btnDetalhes)
                    .addComponent(jrbSala1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Sala01BtnAlerta)
                .addContainerGap(91, Short.MAX_VALUE))
        );

        jPP.add(sala01, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 180));

        sala02.setBorder(javax.swing.BorderFactory.createTitledBorder("Sala 2"));
        sala02.setMaximumSize(new java.awt.Dimension(160, 270));
        sala02.setMinimumSize(new java.awt.Dimension(160, 270));
        sala02.setName(""); // NOI18N
        sala02.setPreferredSize(new java.awt.Dimension(160, 270));

        Sala02BtnOff.setBackground(new java.awt.Color(240, 240, 240));
        Sala02BtnOff.setText("  Off");

        Sala02BtnOn.setBackground(new java.awt.Color(0, 204, 0));
        Sala02BtnOn.setText("  On");

        jLabel8.setText("Em funcionamento");

        Sala02btnDetalhes.setText("Detalhes");
        Sala02btnDetalhes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sala02btnDetalhesActionPerformed(evt);
            }
        });

        Sala02BtnAlerta.setText("Alerta");
        Sala02BtnAlerta.setEnabled(false);

        Sala02Jtf.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        Sala02Jtf.setText("hh:mm:ss");

        javax.swing.GroupLayout sala02Layout = new javax.swing.GroupLayout(sala02);
        sala02.setLayout(sala02Layout);
        sala02Layout.setHorizontalGroup(
            sala02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sala02Layout.createSequentialGroup()
                .addGroup(sala02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Sala02Jtf, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sala02Layout.createSequentialGroup()
                        .addGap(0, 1, Short.MAX_VALUE)
                        .addComponent(Sala02btnDetalhes)
                        .addGap(60, 60, 60))
                    .addGroup(sala02Layout.createSequentialGroup()
                        .addGroup(sala02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(sala02Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(Sala02BtnOn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Sala02BtnOff, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel8)
                            .addGroup(sala02Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(Sala02BtnAlerta)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        sala02Layout.setVerticalGroup(
            sala02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sala02Layout.createSequentialGroup()
                .addGroup(sala02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Sala02BtnOff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Sala02BtnOn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addGap(10, 10, 10)
                .addComponent(Sala02Jtf)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Sala02btnDetalhes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Sala02BtnAlerta)
                .addContainerGap(91, Short.MAX_VALUE))
        );

        jPP.add(sala02, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 160, 180));

        sala03.setBorder(javax.swing.BorderFactory.createTitledBorder("Sala 3"));
        sala03.setMaximumSize(new java.awt.Dimension(160, 270));
        sala03.setMinimumSize(new java.awt.Dimension(160, 270));
        sala03.setName(""); // NOI18N
        sala03.setPreferredSize(new java.awt.Dimension(160, 270));

        jLabel9.setText("Em funcionamento");

        Sala03btnDetalhes.setText("Detalhes");
        Sala03btnDetalhes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sala03btnDetalhesActionPerformed(evt);
            }
        });

        Sala03BtnAlerta.setText("Alerta");
        Sala03BtnAlerta.setEnabled(false);

        Sala03BtnOn.setBackground(new java.awt.Color(240, 240, 240));
        Sala03BtnOn.setText("  On");

        Sala03BtnOff.setBackground(new java.awt.Color(255, 0, 51));
        Sala03BtnOff.setText("  Off");

        Sala03Jtf.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        Sala03Jtf.setText("hh:mm:ss");

        javax.swing.GroupLayout sala03Layout = new javax.swing.GroupLayout(sala03);
        sala03.setLayout(sala03Layout);
        sala03Layout.setHorizontalGroup(
            sala03Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sala03Layout.createSequentialGroup()
                .addGroup(sala03Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Sala03Jtf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(sala03Layout.createSequentialGroup()
                        .addGroup(sala03Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(sala03Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel9))
                            .addGroup(sala03Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(Sala03BtnOn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Sala03BtnOff, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Sala03btnDetalhes)
                            .addGroup(sala03Layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(Sala03BtnAlerta)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        sala03Layout.setVerticalGroup(
            sala03Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sala03Layout.createSequentialGroup()
                .addGroup(sala03Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Sala03BtnOff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Sala03BtnOn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addGap(9, 9, 9)
                .addComponent(Sala03Jtf)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Sala03btnDetalhes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Sala03BtnAlerta)
                .addContainerGap(92, Short.MAX_VALUE))
        );

        jPP.add(sala03, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 160, 180));

        sala04.setBorder(javax.swing.BorderFactory.createTitledBorder("Sala 4"));
        sala04.setName(""); // NOI18N
        sala04.setPreferredSize(new java.awt.Dimension(160, 270));

        Sala04BtnOn.setBackground(new java.awt.Color(240, 240, 240));
        Sala04BtnOn.setText("  On");

        Sala04BtnOff.setBackground(new java.awt.Color(240, 240, 240));
        Sala04BtnOff.setText("  Off");

        jLabel5.setText("Em funcionamento");

        Sala04btnDetalhes.setText("Detalhes");
        Sala04btnDetalhes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sala04btnDetalhesActionPerformed(evt);
            }
        });

        Sala04BtnAlerta.setText("Alerta");
        Sala04BtnAlerta.setEnabled(false);

        Sala04Jtf.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        Sala04Jtf.setText("hh:mm:ss");

        javax.swing.GroupLayout sala04Layout = new javax.swing.GroupLayout(sala04);
        sala04.setLayout(sala04Layout);
        sala04Layout.setHorizontalGroup(
            sala04Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sala04Layout.createSequentialGroup()
                .addGroup(sala04Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Sala04Jtf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(sala04Layout.createSequentialGroup()
                        .addGroup(sala04Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(sala04Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel5))
                            .addGroup(sala04Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(Sala04BtnOn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Sala04BtnOff, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(sala04Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(Sala04btnDetalhes)))
                        .addGap(12, 12, 12)))
                .addGap(0, 1, Short.MAX_VALUE))
            .addGroup(sala04Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(Sala04BtnAlerta)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        sala04Layout.setVerticalGroup(
            sala04Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sala04Layout.createSequentialGroup()
                .addGroup(sala04Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Sala04BtnOff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Sala04BtnOn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Sala04Jtf)
                .addGap(9, 9, 9)
                .addComponent(Sala04btnDetalhes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Sala04BtnAlerta)
                .addGap(34, 34, 34))
        );

        jPP.add(sala04, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 0, 160, 180));

        sala05.setBorder(javax.swing.BorderFactory.createTitledBorder("Sala 5"));
        sala05.setMaximumSize(new java.awt.Dimension(160, 270));
        sala05.setMinimumSize(new java.awt.Dimension(160, 270));
        sala05.setName(""); // NOI18N
        sala05.setPreferredSize(new java.awt.Dimension(160, 270));

        Sala05BtnOff.setBackground(new java.awt.Color(240, 240, 240));
        Sala05BtnOff.setText("  Off");

        Sala05BtnOn.setBackground(new java.awt.Color(0, 204, 0));
        Sala05BtnOn.setText("  On");

        jLabel11.setText("Em funcionamento");

        Sala05btnDetalhes.setText("Detalhes");
        Sala05btnDetalhes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sala05btnDetalhesActionPerformed(evt);
            }
        });

        Sala05BtnAlerta.setText("Alerta");
        Sala05BtnAlerta.setEnabled(false);

        Sala05Jtf.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        Sala05Jtf.setText("hh:mm:ss");

        javax.swing.GroupLayout sala05Layout = new javax.swing.GroupLayout(sala05);
        sala05.setLayout(sala05Layout);
        sala05Layout.setHorizontalGroup(
            sala05Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sala05Layout.createSequentialGroup()
                .addGroup(sala05Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sala05Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(Sala05BtnOn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Sala05BtnOff, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sala05Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(sala05Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Sala05btnDetalhes)
                            .addComponent(jLabel11)
                            .addComponent(Sala05Jtf, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(sala05Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(Sala05BtnAlerta)))))
                .addContainerGap())
        );
        sala05Layout.setVerticalGroup(
            sala05Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sala05Layout.createSequentialGroup()
                .addGroup(sala05Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Sala05BtnOff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Sala05BtnOn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addGap(9, 9, 9)
                .addComponent(Sala05Jtf)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Sala05btnDetalhes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Sala05BtnAlerta)
                .addContainerGap(92, Short.MAX_VALUE))
        );

        jPP.add(sala05, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 160, 180));

        sala06.setBorder(javax.swing.BorderFactory.createTitledBorder("Sala 6"));
        sala06.setMaximumSize(new java.awt.Dimension(160, 270));
        sala06.setMinimumSize(new java.awt.Dimension(160, 270));
        sala06.setName(""); // NOI18N
        sala06.setPreferredSize(new java.awt.Dimension(160, 270));

        Sala06BtnOff.setBackground(new java.awt.Color(240, 240, 240));
        Sala06BtnOff.setText("  Off");

        Sala06BtnOn.setBackground(new java.awt.Color(0, 204, 0));
        Sala06BtnOn.setText("  On");

        jLabel12.setText("Em funcionamento");

        Sala06btnDetalhes.setText("Detalhes");
        Sala06btnDetalhes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sala06btnDetalhesActionPerformed(evt);
            }
        });

        Sala06BtnAlerta.setText("Alerta");
        Sala06BtnAlerta.setEnabled(false);

        Sala06Jtf.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        Sala06Jtf.setText("hh:mm:ss");

        javax.swing.GroupLayout sala06Layout = new javax.swing.GroupLayout(sala06);
        sala06.setLayout(sala06Layout);
        sala06Layout.setHorizontalGroup(
            sala06Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sala06Layout.createSequentialGroup()
                .addGroup(sala06Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Sala06Jtf, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(sala06Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(Sala06BtnOn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Sala06BtnOff, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sala06Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(sala06Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Sala06btnDetalhes)
                            .addGroup(sala06Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel12)))))
                .addContainerGap())
            .addGroup(sala06Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(Sala06BtnAlerta)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        sala06Layout.setVerticalGroup(
            sala06Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sala06Layout.createSequentialGroup()
                .addGroup(sala06Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Sala06BtnOff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Sala06BtnOn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Sala06Jtf)
                .addGap(9, 9, 9)
                .addComponent(Sala06btnDetalhes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Sala06BtnAlerta)
                .addContainerGap(92, Short.MAX_VALUE))
        );

        jPP.add(sala06, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 180, 160, 180));

        sala07.setBorder(javax.swing.BorderFactory.createTitledBorder("Sala 7"));
        sala07.setMaximumSize(new java.awt.Dimension(160, 270));
        sala07.setMinimumSize(new java.awt.Dimension(160, 270));
        sala07.setName(""); // NOI18N
        sala07.setPreferredSize(new java.awt.Dimension(160, 270));

        Sala07BtnOff.setBackground(new java.awt.Color(240, 240, 240));
        Sala07BtnOff.setText("  Off");

        Sala07BtnOn.setBackground(new java.awt.Color(0, 204, 0));
        Sala07BtnOn.setText("  On");

        jLabel13.setText("Em funcionamento");

        Sala07btnDetalhes.setText("Detalhes");
        Sala07btnDetalhes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sala07btnDetalhesActionPerformed(evt);
            }
        });

        Sala07BtnAlerta.setText("Alerta");
        Sala07BtnAlerta.setEnabled(false);
        Sala07BtnAlerta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sala07BtnAlertaActionPerformed(evt);
            }
        });

        Sala07Jtf.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        Sala07Jtf.setText("hh:mm:ss");

        javax.swing.GroupLayout sala07Layout = new javax.swing.GroupLayout(sala07);
        sala07.setLayout(sala07Layout);
        sala07Layout.setHorizontalGroup(
            sala07Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sala07Layout.createSequentialGroup()
                .addGroup(sala07Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Sala07Jtf, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(sala07Layout.createSequentialGroup()
                        .addGroup(sala07Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(sala07Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(Sala07BtnOn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Sala07BtnOff, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel13)
                            .addGroup(sala07Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(sala07Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(Sala07BtnAlerta)
                                    .addComponent(Sala07btnDetalhes))))
                        .addGap(0, 4, Short.MAX_VALUE)))
                .addContainerGap())
        );
        sala07Layout.setVerticalGroup(
            sala07Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sala07Layout.createSequentialGroup()
                .addGroup(sala07Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Sala07BtnOff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Sala07BtnOn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Sala07Jtf)
                .addGap(9, 9, 9)
                .addComponent(Sala07btnDetalhes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Sala07BtnAlerta)
                .addContainerGap(92, Short.MAX_VALUE))
        );

        jPP.add(sala07, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 180, 160, 180));

        sala08.setBorder(javax.swing.BorderFactory.createTitledBorder("Sala 8"));
        sala08.setMaximumSize(new java.awt.Dimension(160, 270));
        sala08.setMinimumSize(new java.awt.Dimension(160, 270));
        sala08.setName(""); // NOI18N
        sala08.setPreferredSize(new java.awt.Dimension(160, 270));

        Sala08BtnOff.setBackground(new java.awt.Color(240, 240, 240));
        Sala08BtnOff.setText("  Off");

        Sala08BtnOn.setBackground(new java.awt.Color(0, 204, 0));
        Sala08BtnOn.setText("  On");

        jLabel14.setText("Em funcionamento");

        Sala08btnDetalhes.setText("Detalhes");
        Sala08btnDetalhes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sala08btnDetalhesActionPerformed(evt);
            }
        });

        Sala08BtnAlerta.setText("Alerta");
        Sala08BtnAlerta.setEnabled(false);

        Sala08Jtf.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        Sala08Jtf.setText("hh:mm:ss");

        javax.swing.GroupLayout sala08Layout = new javax.swing.GroupLayout(sala08);
        sala08.setLayout(sala08Layout);
        sala08Layout.setHorizontalGroup(
            sala08Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sala08Layout.createSequentialGroup()
                .addGroup(sala08Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Sala08Jtf, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(sala08Layout.createSequentialGroup()
                        .addGroup(sala08Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(sala08Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(Sala08BtnOn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Sala08BtnOff, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(sala08Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(Sala08btnDetalhes)))
                        .addGap(0, 26, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sala08Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel14)))
                .addContainerGap())
            .addGroup(sala08Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(Sala08BtnAlerta)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        sala08Layout.setVerticalGroup(
            sala08Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sala08Layout.createSequentialGroup()
                .addGroup(sala08Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Sala08BtnOff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Sala08BtnOn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Sala08Jtf)
                .addGap(9, 9, 9)
                .addComponent(Sala08btnDetalhes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Sala08BtnAlerta)
                .addContainerGap(92, Short.MAX_VALUE))
        );

        jPP.add(sala08, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 180, 160, 180));

        sala09.setBorder(javax.swing.BorderFactory.createTitledBorder("Sala 9"));
        sala09.setMaximumSize(new java.awt.Dimension(160, 270));
        sala09.setMinimumSize(new java.awt.Dimension(160, 270));
        sala09.setPreferredSize(new java.awt.Dimension(160, 270));

        Sala09BtnOff.setBackground(new java.awt.Color(240, 240, 240));
        Sala09BtnOff.setText("  Off");

        Sala09BtnOn.setBackground(new java.awt.Color(0, 204, 0));
        Sala09BtnOn.setText("  On");

        jLabel15.setText("Em funcionamento");

        Sala09btnDetalhes.setText("Detalhes");
        Sala09btnDetalhes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sala09btnDetalhesActionPerformed(evt);
            }
        });

        Sala09BtnAlerta.setText("Alerta");
        Sala09BtnAlerta.setEnabled(false);

        Sala09Jtf.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        Sala09Jtf.setText("hh:mm:ss");

        javax.swing.GroupLayout sala09Layout = new javax.swing.GroupLayout(sala09);
        sala09.setLayout(sala09Layout);
        sala09Layout.setHorizontalGroup(
            sala09Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sala09Layout.createSequentialGroup()
                .addGroup(sala09Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sala09Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15))
                    .addGroup(sala09Layout.createSequentialGroup()
                        .addGroup(sala09Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(sala09Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(Sala09BtnOn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Sala09BtnOff, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Sala09btnDetalhes))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(Sala09Jtf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(sala09Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(Sala09BtnAlerta)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        sala09Layout.setVerticalGroup(
            sala09Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sala09Layout.createSequentialGroup()
                .addGroup(sala09Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Sala09BtnOff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Sala09BtnOn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Sala09Jtf)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Sala09btnDetalhes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Sala09BtnAlerta)
                .addContainerGap(89, Short.MAX_VALUE))
        );

        jPP.add(sala09, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 160, 180));

        sala10.setBorder(javax.swing.BorderFactory.createTitledBorder("Sala 10"));
        sala10.setMaximumSize(new java.awt.Dimension(160, 270));
        sala10.setMinimumSize(new java.awt.Dimension(160, 270));
        sala10.setPreferredSize(new java.awt.Dimension(160, 270));

        Sala10BtnOff.setBackground(new java.awt.Color(240, 240, 240));
        Sala10BtnOff.setText("  Off");

        Sala10BtnOn.setBackground(new java.awt.Color(0, 204, 0));
        Sala10BtnOn.setText("  On");

        jLabel16.setText("Em funcionamento");

        Sala10btnDetalhes.setText("Detalhes");
        Sala10btnDetalhes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sala10btnDetalhesActionPerformed(evt);
            }
        });

        Sala10BtnAlerta.setText("Alerta");
        Sala10BtnAlerta.setEnabled(false);

        Sala10Jtf.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        Sala10Jtf.setText("hh:mm:ss");

        javax.swing.GroupLayout sala10Layout = new javax.swing.GroupLayout(sala10);
        sala10.setLayout(sala10Layout);
        sala10Layout.setHorizontalGroup(
            sala10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Sala10Jtf, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(sala10Layout.createSequentialGroup()
                .addGroup(sala10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sala10Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel16))
                    .addGroup(sala10Layout.createSequentialGroup()
                        .addGroup(sala10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(sala10Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(Sala10BtnOn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Sala10BtnOff, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Sala10btnDetalhes))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sala10Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Sala10BtnAlerta)
                .addGap(49, 49, 49))
        );
        sala10Layout.setVerticalGroup(
            sala10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sala10Layout.createSequentialGroup()
                .addGroup(sala10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Sala10BtnOff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Sala10BtnOn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Sala10Jtf)
                .addGap(9, 9, 9)
                .addComponent(Sala10btnDetalhes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Sala10BtnAlerta)
                .addContainerGap(92, Short.MAX_VALUE))
        );

        jPP.add(sala10, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 360, 160, 180));

        sala11.setBorder(javax.swing.BorderFactory.createTitledBorder("Sala 11"));
        sala11.setMaximumSize(new java.awt.Dimension(160, 270));
        sala11.setMinimumSize(new java.awt.Dimension(160, 270));
        sala11.setPreferredSize(new java.awt.Dimension(160, 270));

        Sala11BtnOff.setBackground(new java.awt.Color(240, 240, 240));
        Sala11BtnOff.setText("  Off");

        Sala11BtnOn.setBackground(new java.awt.Color(0, 204, 0));
        Sala11BtnOn.setText("  On");

        jLabel17.setText("Em funcionamento");

        Sala11btnDetalhes.setText("Detalhes");
        Sala11btnDetalhes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sala11btnDetalhesActionPerformed(evt);
            }
        });

        Sala11BtnAlerta.setText("Alerta");
        Sala11BtnAlerta.setEnabled(false);

        Sala11Jtf.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        Sala11Jtf.setText("hh:mm:ss");

        javax.swing.GroupLayout sala11Layout = new javax.swing.GroupLayout(sala11);
        sala11.setLayout(sala11Layout);
        sala11Layout.setHorizontalGroup(
            sala11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sala11Layout.createSequentialGroup()
                .addGroup(sala11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sala11Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(Sala11BtnOn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Sala11BtnOff, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sala11Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel17))
                    .addGroup(sala11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Sala11Jtf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(sala11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Sala11btnDetalhes)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sala11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Sala11BtnAlerta)
                .addGap(45, 45, 45))
        );
        sala11Layout.setVerticalGroup(
            sala11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sala11Layout.createSequentialGroup()
                .addGroup(sala11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Sala11BtnOff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Sala11BtnOn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Sala11Jtf)
                .addGap(9, 9, 9)
                .addComponent(Sala11btnDetalhes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Sala11BtnAlerta)
                .addContainerGap(92, Short.MAX_VALUE))
        );

        jPP.add(sala11, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 360, 160, 180));

        sala12.setBorder(javax.swing.BorderFactory.createTitledBorder("Sala 12"));
        sala12.setMaximumSize(new java.awt.Dimension(160, 270));
        sala12.setMinimumSize(new java.awt.Dimension(160, 270));
        sala12.setPreferredSize(new java.awt.Dimension(160, 270));

        Sala12BtnOff.setBackground(new java.awt.Color(240, 240, 240));
        Sala12BtnOff.setText("  Off");

        Sala12BtnOn.setBackground(new java.awt.Color(0, 204, 0));
        Sala12BtnOn.setText("  On");

        jLabel18.setText("Em funcionamento");

        Sala12btnDetalhes.setText("Detalhes");
        Sala12btnDetalhes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sala12btnDetalhesActionPerformed(evt);
            }
        });

        Sala12BtnAlerta.setText("Alerta");
        Sala12BtnAlerta.setEnabled(false);

        Sala12Jtf.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        Sala12Jtf.setText("hh:mm:ss");

        javax.swing.GroupLayout sala12Layout = new javax.swing.GroupLayout(sala12);
        sala12.setLayout(sala12Layout);
        sala12Layout.setHorizontalGroup(
            sala12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sala12Layout.createSequentialGroup()
                .addGroup(sala12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sala12Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(Sala12BtnOn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Sala12BtnOff, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sala12Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sala12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Sala12Jtf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(sala12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Sala12btnDetalhes)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sala12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Sala12BtnAlerta)
                .addGap(43, 43, 43))
        );
        sala12Layout.setVerticalGroup(
            sala12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sala12Layout.createSequentialGroup()
                .addGroup(sala12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Sala12BtnOff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Sala12BtnOn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Sala12Jtf)
                .addGap(9, 9, 9)
                .addComponent(Sala12btnDetalhes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Sala12BtnAlerta)
                .addContainerGap(92, Short.MAX_VALUE))
        );

        jPP.add(sala12, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 360, 160, 180));

        sala13.setBorder(javax.swing.BorderFactory.createTitledBorder("Sala 13"));

        Sala13BtnOff.setBackground(new java.awt.Color(240, 240, 240));
        Sala13BtnOff.setText("  Off");

        Sala13BtnOn.setBackground(new java.awt.Color(0, 204, 0));
        Sala13BtnOn.setText("  On");

        jLabel19.setText("Em funcionamento");

        Sala13btnDetalhes.setText("Detalhes");
        Sala13btnDetalhes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sala13btnDetalhesActionPerformed(evt);
            }
        });

        Sala13BtnAlerta.setText("Alerta");
        Sala13BtnAlerta.setEnabled(false);

        Sala13Jtf.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        Sala13Jtf.setText("hh:mm:ss");

        javax.swing.GroupLayout sala13Layout = new javax.swing.GroupLayout(sala13);
        sala13.setLayout(sala13Layout);
        sala13Layout.setHorizontalGroup(
            sala13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sala13Layout.createSequentialGroup()
                .addGroup(sala13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sala13Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(Sala13BtnOn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Sala13BtnOff, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(sala13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Sala13Jtf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sala13Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(sala13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addGroup(sala13Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(Sala13BtnAlerta)))))
                .addContainerGap())
            .addGroup(sala13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Sala13btnDetalhes)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        sala13Layout.setVerticalGroup(
            sala13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sala13Layout.createSequentialGroup()
                .addGroup(sala13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Sala13BtnOff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Sala13BtnOn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Sala13Jtf)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(Sala13btnDetalhes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Sala13BtnAlerta))
        );

        jPP.add(sala13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 540, 160, 180));

        sala14.setBorder(javax.swing.BorderFactory.createTitledBorder("Sala 14"));

        Sala14BtnOff.setBackground(new java.awt.Color(240, 240, 240));
        Sala14BtnOff.setText("  Off");

        Sala14BtnOn.setBackground(new java.awt.Color(0, 204, 0));
        Sala14BtnOn.setText("  On");

        jLabel20.setText("Em funcionamento:");

        Sala14btnDetalhes.setText("Detalhes");
        Sala14btnDetalhes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sala14btnDetalhesActionPerformed(evt);
            }
        });

        Sala14BtnAlerta.setText("Alerta");
        Sala14BtnAlerta.setEnabled(false);

        Sala14Jtf.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        Sala14Jtf.setText("hh:mm:ss");

        javax.swing.GroupLayout sala14Layout = new javax.swing.GroupLayout(sala14);
        sala14.setLayout(sala14Layout);
        sala14Layout.setHorizontalGroup(
            sala14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sala14Layout.createSequentialGroup()
                .addGroup(sala14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sala14Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(Sala14BtnOn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Sala14BtnOff, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sala14Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(sala14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addGroup(sala14Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(Sala14BtnAlerta))))
                    .addGroup(sala14Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Sala14Jtf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(sala14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Sala14btnDetalhes)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        sala14Layout.setVerticalGroup(
            sala14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sala14Layout.createSequentialGroup()
                .addGroup(sala14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Sala14BtnOff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Sala14BtnOn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Sala14Jtf)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(Sala14btnDetalhes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Sala14BtnAlerta))
        );

        jPP.add(sala14, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 540, 160, 180));

        sala15.setBorder(javax.swing.BorderFactory.createTitledBorder("Sala 15"));

        Sala15BtnOff.setBackground(new java.awt.Color(240, 240, 240));
        Sala15BtnOff.setText("  Off");

        Sala15BtnOn.setBackground(new java.awt.Color(0, 204, 0));
        Sala15BtnOn.setText("  On");

        jLabel21.setText("Em funcionamento:");

        Sala15btnDetalhes.setText("Detalhes");
        Sala15btnDetalhes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sala15btnDetalhesActionPerformed(evt);
            }
        });

        Sala15BtnAlerta.setText("Alerta");
        Sala15BtnAlerta.setEnabled(false);

        Sala15Jtf.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        Sala15Jtf.setText("hh:mm:ss");

        javax.swing.GroupLayout sala15Layout = new javax.swing.GroupLayout(sala15);
        sala15.setLayout(sala15Layout);
        sala15Layout.setHorizontalGroup(
            sala15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sala15Layout.createSequentialGroup()
                .addGroup(sala15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sala15Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(Sala15BtnOn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Sala15BtnOff, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sala15Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(sala15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addGroup(sala15Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(Sala15BtnAlerta))))
                    .addGroup(sala15Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Sala15Jtf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(sala15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Sala15btnDetalhes)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        sala15Layout.setVerticalGroup(
            sala15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sala15Layout.createSequentialGroup()
                .addGroup(sala15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Sala15BtnOff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Sala15BtnOn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Sala15Jtf)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(Sala15btnDetalhes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Sala15BtnAlerta))
        );

        jPP.add(sala15, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 540, 160, 180));

        sala16.setBorder(javax.swing.BorderFactory.createTitledBorder("Sala 16"));

        Sala16BtnOff.setBackground(new java.awt.Color(240, 240, 240));
        Sala16BtnOff.setText("  Off");

        Sala16BtnOn.setBackground(new java.awt.Color(0, 204, 0));
        Sala16BtnOn.setText("  On");

        jLabel22.setText("Em funcionamento:");

        Sala16btnDetalhes.setText("Detalhes");
        Sala16btnDetalhes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sala16btnDetalhesActionPerformed(evt);
            }
        });

        Sala16BtnAlerta.setText("Alerta");
        Sala16BtnAlerta.setEnabled(false);

        Sala16Jtf.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        Sala16Jtf.setText("hh:mm:ss");

        javax.swing.GroupLayout sala16Layout = new javax.swing.GroupLayout(sala16);
        sala16.setLayout(sala16Layout);
        sala16Layout.setHorizontalGroup(
            sala16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sala16Layout.createSequentialGroup()
                .addGroup(sala16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sala16Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(Sala16BtnOn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Sala16BtnOff, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sala16Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(sala16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addGroup(sala16Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(Sala16BtnAlerta))))
                    .addGroup(sala16Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Sala16Jtf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(sala16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Sala16btnDetalhes)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        sala16Layout.setVerticalGroup(
            sala16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sala16Layout.createSequentialGroup()
                .addGroup(sala16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Sala16BtnOff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Sala16BtnOn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Sala16Jtf)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(Sala16btnDetalhes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Sala16BtnAlerta))
        );

        jPP.add(sala16, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 540, 160, 180));

        lbltempos2.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lbltempos2.setText("hh:mm:ss");
        jPP.add(lbltempos2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        lblTempoS5.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblTempoS5.setText("hh:mm:ss");
        jPP.add(lblTempoS5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        lblTempoS6.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblTempoS6.setText("hh:mm:ss");
        jPP.add(lblTempoS6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        lblTempoS7.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblTempoS7.setText("hh:mm:ss");
        jPP.add(lblTempoS7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(jPP, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Sala01btnDetalhesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sala01btnDetalhesActionPerformed
        // TODO add your handling code here:
        ChamaFormSupervisao(1);
    }//GEN-LAST:event_Sala01btnDetalhesActionPerformed

    private void Sala02btnDetalhesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sala02btnDetalhesActionPerformed
        // TODO add your handling code here:
       ChamaFormSupervisao(2);
}//GEN-LAST:event_Sala02btnDetalhesActionPerformed

    private void Sala03btnDetalhesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sala03btnDetalhesActionPerformed
        // TODO add your handling code here:
        ChamaFormSupervisao(3);
    }//GEN-LAST:event_Sala03btnDetalhesActionPerformed

    private void Sala04btnDetalhesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sala04btnDetalhesActionPerformed
        // TODO add your handling code here:
        ChamaFormSupervisao(4);
    }//GEN-LAST:event_Sala04btnDetalhesActionPerformed

    private void Sala01BtnAlertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sala01BtnAlertaActionPerformed
        // TODO add your handling code here:
        /* Falta corrigir esse codigo do Alerta */
        int sala = 1;
        if (testeLigaDesl == 1) {
            FormPSupervisao painel = null;            
            painel = new FormPSupervisao(new JFrame(), true, sala);
            
            painel.setTitle("sala 01");
            painel.setVisible(true);
            Sala01BtnAlerta.setEnabled(false);
            testeLigaDesl = 0;
        } else {
            FormPSupervisao painel = null;            
            painel = new FormPSupervisao(new JFrame(), true, sala);
            
            painel.setTitle("sala 01");
            painel.setVisible(true);

        }

    }//GEN-LAST:event_Sala01BtnAlertaActionPerformed

    private void Sala11btnDetalhesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sala11btnDetalhesActionPerformed
        // TODO add your handling code here:
        ChamaFormSupervisao(11);
    }//GEN-LAST:event_Sala11btnDetalhesActionPerformed

    private void Sala07BtnAlertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sala07BtnAlertaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Sala07BtnAlertaActionPerformed

    private void Sala05btnDetalhesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sala05btnDetalhesActionPerformed
        // TODO add your handling code here:
        ChamaFormSupervisao(5);
    }//GEN-LAST:event_Sala05btnDetalhesActionPerformed

    private void Sala06btnDetalhesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sala06btnDetalhesActionPerformed
        // TODO add your handling code here:
        ChamaFormSupervisao(6);
    }//GEN-LAST:event_Sala06btnDetalhesActionPerformed

    private void Sala07btnDetalhesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sala07btnDetalhesActionPerformed
        // TODO add your handling code here:
        ChamaFormSupervisao(7);
    }//GEN-LAST:event_Sala07btnDetalhesActionPerformed

    private void Sala08btnDetalhesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sala08btnDetalhesActionPerformed
        // TODO add your handling code here:
        ChamaFormSupervisao(8);
    }//GEN-LAST:event_Sala08btnDetalhesActionPerformed

    private void Sala09btnDetalhesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sala09btnDetalhesActionPerformed
        // TODO add your handling code here:
        ChamaFormSupervisao(9);
    }//GEN-LAST:event_Sala09btnDetalhesActionPerformed

    private void Sala10btnDetalhesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sala10btnDetalhesActionPerformed
        // TODO add your handling code here:
        ChamaFormSupervisao(10);
    }//GEN-LAST:event_Sala10btnDetalhesActionPerformed

    private void Sala12btnDetalhesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sala12btnDetalhesActionPerformed
        // TODO add your handling code here:
        ChamaFormSupervisao(12);
    }//GEN-LAST:event_Sala12btnDetalhesActionPerformed

    private void Sala13btnDetalhesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sala13btnDetalhesActionPerformed
        // TODO add your handling code here:
        ChamaFormSupervisao(13);
    }//GEN-LAST:event_Sala13btnDetalhesActionPerformed

    private void Sala14btnDetalhesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sala14btnDetalhesActionPerformed
        // TODO add your handling code here:
        ChamaFormSupervisao(14);
    }//GEN-LAST:event_Sala14btnDetalhesActionPerformed

    private void Sala15btnDetalhesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sala15btnDetalhesActionPerformed
        // TODO add your handling code here:
        ChamaFormSupervisao(15);
    }//GEN-LAST:event_Sala15btnDetalhesActionPerformed

    private void Sala16btnDetalhesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sala16btnDetalhesActionPerformed
        // TODO add your handling code here:
        ChamaFormSupervisao(16);
    }//GEN-LAST:event_Sala16btnDetalhesActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                FormPControle dialog = null;
                try {
                    dialog = new FormPControle(new javax.swing.JFrame(), true);
                } catch (Exception ex) {
                    Logger.getLogger(FormPControle.class.getName()).log(Level.SEVERE, null, ex);
                }
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton Sala01BtnAlerta;
    private javax.swing.JTextField Sala01BtnOff;
    private javax.swing.JTextField Sala01BtnOn;
    private javax.swing.JLabel Sala01Jtf;
    private javax.swing.JButton Sala01btnDetalhes;
    private javax.swing.JToggleButton Sala02BtnAlerta;
    private javax.swing.JTextField Sala02BtnOff;
    private javax.swing.JTextField Sala02BtnOn;
    private javax.swing.JLabel Sala02Jtf;
    private javax.swing.JButton Sala02btnDetalhes;
    private javax.swing.JToggleButton Sala03BtnAlerta;
    private javax.swing.JTextField Sala03BtnOff;
    private javax.swing.JTextField Sala03BtnOn;
    private javax.swing.JLabel Sala03Jtf;
    private javax.swing.JButton Sala03btnDetalhes;
    private javax.swing.JToggleButton Sala04BtnAlerta;
    private javax.swing.JTextField Sala04BtnOff;
    private javax.swing.JTextField Sala04BtnOn;
    private javax.swing.JLabel Sala04Jtf;
    private javax.swing.JButton Sala04btnDetalhes;
    private javax.swing.JToggleButton Sala05BtnAlerta;
    private javax.swing.JTextField Sala05BtnOff;
    private javax.swing.JTextField Sala05BtnOn;
    private javax.swing.JLabel Sala05Jtf;
    private javax.swing.JButton Sala05btnDetalhes;
    private javax.swing.JToggleButton Sala06BtnAlerta;
    private javax.swing.JTextField Sala06BtnOff;
    private javax.swing.JTextField Sala06BtnOn;
    private javax.swing.JLabel Sala06Jtf;
    private javax.swing.JButton Sala06btnDetalhes;
    private javax.swing.JToggleButton Sala07BtnAlerta;
    private javax.swing.JTextField Sala07BtnOff;
    private javax.swing.JTextField Sala07BtnOn;
    private javax.swing.JLabel Sala07Jtf;
    private javax.swing.JButton Sala07btnDetalhes;
    private javax.swing.JToggleButton Sala08BtnAlerta;
    private javax.swing.JTextField Sala08BtnOff;
    private javax.swing.JTextField Sala08BtnOn;
    private javax.swing.JLabel Sala08Jtf;
    private javax.swing.JButton Sala08btnDetalhes;
    private javax.swing.JToggleButton Sala09BtnAlerta;
    private javax.swing.JTextField Sala09BtnOff;
    private javax.swing.JTextField Sala09BtnOn;
    private javax.swing.JLabel Sala09Jtf;
    private javax.swing.JButton Sala09btnDetalhes;
    private javax.swing.JToggleButton Sala10BtnAlerta;
    private javax.swing.JTextField Sala10BtnOff;
    private javax.swing.JTextField Sala10BtnOn;
    private javax.swing.JLabel Sala10Jtf;
    private javax.swing.JButton Sala10btnDetalhes;
    private javax.swing.JToggleButton Sala11BtnAlerta;
    private javax.swing.JTextField Sala11BtnOff;
    private javax.swing.JTextField Sala11BtnOn;
    private javax.swing.JLabel Sala11Jtf;
    private javax.swing.JButton Sala11btnDetalhes;
    private javax.swing.JToggleButton Sala12BtnAlerta;
    private javax.swing.JTextField Sala12BtnOff;
    private javax.swing.JTextField Sala12BtnOn;
    private javax.swing.JLabel Sala12Jtf;
    private javax.swing.JButton Sala12btnDetalhes;
    private javax.swing.JToggleButton Sala13BtnAlerta;
    private javax.swing.JTextField Sala13BtnOff;
    private javax.swing.JTextField Sala13BtnOn;
    private javax.swing.JLabel Sala13Jtf;
    private javax.swing.JButton Sala13btnDetalhes;
    private javax.swing.JToggleButton Sala14BtnAlerta;
    private javax.swing.JTextField Sala14BtnOff;
    private javax.swing.JTextField Sala14BtnOn;
    private javax.swing.JLabel Sala14Jtf;
    private javax.swing.JButton Sala14btnDetalhes;
    private javax.swing.JToggleButton Sala15BtnAlerta;
    private javax.swing.JTextField Sala15BtnOff;
    private javax.swing.JTextField Sala15BtnOn;
    private javax.swing.JLabel Sala15Jtf;
    private javax.swing.JButton Sala15btnDetalhes;
    private javax.swing.JToggleButton Sala16BtnAlerta;
    private javax.swing.JTextField Sala16BtnOff;
    private javax.swing.JTextField Sala16BtnOn;
    private javax.swing.JLabel Sala16Jtf;
    private javax.swing.JButton Sala16btnDetalhes;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPP;
    private javax.swing.JRadioButton jrbSala1;
    private javax.swing.JLabel lblTempoS5;
    private javax.swing.JLabel lblTempoS6;
    private javax.swing.JLabel lblTempoS7;
    private javax.swing.JLabel lbltempos2;
    private javax.swing.JPanel sala01;
    private javax.swing.JPanel sala02;
    private javax.swing.JPanel sala03;
    private javax.swing.JPanel sala04;
    private javax.swing.JPanel sala05;
    private javax.swing.JPanel sala06;
    private javax.swing.JPanel sala07;
    private javax.swing.JPanel sala08;
    private javax.swing.JPanel sala09;
    private javax.swing.JPanel sala10;
    private javax.swing.JPanel sala11;
    private javax.swing.JPanel sala12;
    private javax.swing.JPanel sala13;
    private javax.swing.JPanel sala14;
    private javax.swing.JPanel sala15;
    private javax.swing.JPanel sala16;
    // End of variables declaration//GEN-END:variables

//    public javax.swing.JLabel getSala01Jtf() {
//        return Sala01Jtf;
//    }
//
//    public void setSala01Jtf(javax.swing.JLabel Sala01Jtf) {
//        this.Sala01Jtf = Sala01Jtf;
//    }
//
//    public javax.swing.JLabel getSala02Jtf() {
//        return Sala02Jtf;
//    }
//
//    public void setSala02Jtf(javax.swing.JLabel Sala02Jtf) {
//        this.Sala02Jtf = Sala02Jtf;
//    }
//
//    public javax.swing.JLabel getSala03Jtf() {
//        return Sala03Jtf;
//    }
//
//    public void setSala03Jtf(javax.swing.JLabel Sala03Jtf) {
//        this.Sala03Jtf = Sala03Jtf;
//    }
//
//    public javax.swing.JLabel getSala04Jtf() {
//        return Sala04Jtf;
//    }
//
//    public void setSala04Jtf(javax.swing.JLabel Sala04Jtf) {
//        this.Sala04Jtf = Sala04Jtf;
//    }
//
//    public javax.swing.JLabel getSala05Jtf() {
//        return Sala05Jtf;
//    }
//
//    public void setSala05Jtf(javax.swing.JLabel Sala05Jtf) {
//        this.Sala05Jtf = Sala05Jtf;
//    }
//
//    public javax.swing.JLabel getSala06Jtf() {
//        return Sala06Jtf;
//    }
//
//    public void setSala06Jtf(javax.swing.JLabel Sala06Jtf) {
//        this.Sala06Jtf = Sala06Jtf;
//    }
//
//    public javax.swing.JLabel getSala07Jtf() {
//        return Sala07Jtf;
//    }
//
//    public void setSala07Jtf(javax.swing.JLabel Sala07Jtf) {
//        this.Sala07Jtf = Sala07Jtf;
//    }
//
//    public javax.swing.JLabel getSala08Jtf() {
//        return Sala08Jtf;
//    }
//
//    public void setSala08Jtf(javax.swing.JLabel Sala08Jtf) {
//        this.Sala08Jtf = Sala08Jtf;
//    }
//
//    public javax.swing.JLabel getSala09Jtf() {
//        return Sala09Jtf;
//    }
//
//    public void setSala09Jtf(javax.swing.JLabel Sala09Jtf) {
//        this.Sala09Jtf = Sala09Jtf;
//    }
//
//    public javax.swing.JLabel getSala10Jtf() {
//        return Sala10Jtf;
//    }
//
//    public void setSala10Jtf(javax.swing.JLabel Sala10Jtf) {
//        this.Sala10Jtf = Sala10Jtf;
//    }
//
//    public javax.swing.JLabel getSala11Jtf() {
//        return Sala11Jtf;
//    }
//
//    public void setSala11Jtf(javax.swing.JLabel Sala11Jtf) {
//        this.Sala11Jtf = Sala11Jtf;
//    }
//
//    public javax.swing.JLabel getSala12Jtf() {
//        return Sala12Jtf;
//    }
//
//    public void setSala12Jtf(javax.swing.JLabel Sala12Jtf) {
//        this.Sala12Jtf = Sala12Jtf;
//    }
//
//    public javax.swing.JLabel getSala13Jtf() {
//        return Sala13Jtf;
//    }
//
//    public void setSala13Jtf(javax.swing.JLabel Sala13Jtf) {
//        this.Sala13Jtf = Sala13Jtf;
//    }
//
//    public javax.swing.JLabel getSala14Jtf() {
//        return Sala14Jtf;
//    }
//
//    public void setSala14Jtf(javax.swing.JLabel Sala14Jtf) {
//        this.Sala14Jtf = Sala14Jtf;
//    }
//
//    public javax.swing.JLabel getSala15Jtf() {
//        return Sala15Jtf;
//    }
//
//    public void setSala15Jtf(javax.swing.JLabel Sala15Jtf) {
//        this.Sala15Jtf = Sala15Jtf;
//    }
//
//    public javax.swing.JLabel getSala16Jtf() {
//        return Sala16Jtf;
//    }
//
//    public void setSala16Jtf(javax.swing.JLabel Sala16Jtf) {
//        this.Sala16Jtf = Sala16Jtf;
//    }   

}
