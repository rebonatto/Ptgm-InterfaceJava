/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Uteis;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Calendar;

import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author rebonatto
 */
public class Configuracoes {
    
    public static int MEMORIA    = 256 * 1024 * 1024;
    
    public static int MAXSALAS   = 16;
    public static int MAXTOMADAS = 12;
    
    public static int HARMONICAS = 12;
    public static int PONTOSONDA = 256;
    
    public static int FREQBASE   = 60;

    public static int TEMPOATUALIZARELOGIOS = 1000;
    public static int TEMPOATUALIZATABELA = 3000;         
    
    public static Calendar DATALIMITEVM; 
    public static int DIGITOSSIGNIFICATIVOS = 3;
    
    private static Properties config = new Properties();
    private static String arquivo = "protegemed.ini";                
       
    /* Configurações relativa ao Banco de Dados */
    public static String banco   = "jdbc:mysql://localhost/protegemed";
    //public static String banco   = "jdbc:mysql://localhost/ptm_hsvp";
    //public static String banco   = "jdbc:mysql://10.8.0.1/protegemed";
    //private stati c String banco   = "jdbc:mysql://localhost/dbptm";
    //private static String banco   = "jdbc:mysql://localhost/protegemed";
    //private static String banco   = "jdbc:mysql://172.20.6.111/protegemed";
    
    //private static String driver  = "org.gjt.mm.mysql.Driver";
    public static String driver  = "com.mysql.jdbc.Driver";
    public static String usuario = "root";
    public static String senha   = "senha.123";
            
    public static void LeAjustaParametros() {        
        try {                                                
            //InputStream input = getClass().getClassLoader().getResourceAsStream(arquivo);                        
            //InputStream input = ClassLoader.getResourceAsStream(arquivo);
            //InputStream input = Class.getResourceAsStream (arquivo);
            //InputStream input = ResourceBundle.getBundle(arquivo);
            //InputStream input = getClass().getResourceAsStream(arquivo);     
//            String local = System.getProperty("user.dir"); 
//            URL u = getClass().getResource("");
            /*
            FileInputStream f = new FileInputStream
            InputStream i = new InputStream
            InputStream input = ConfigBase.class.getResourceAsStream(arquivo);            
            */
            config.load(new FileInputStream(arquivo));            
            
            banco    = config.getProperty("banco");
            driver    = config.getProperty("driver");
            usuario   = config.getProperty("usuario");
            senha    = config.getProperty("senha");  
            
            DATALIMITEVM = Calendar.getInstance();
            DATALIMITEVM.set(2016, 0, 1, 0, 0, 0 ); // 01/01/2014 00:00:00
            try{
                FREQBASE = Integer.parseInt(config.getProperty("FREQBASE"));     
            }catch (Exception e){
                System.out.println("Frequencia Base errada, assumindo 60Hz");
                FREQBASE = 60;
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Arquivo " + arquivo + " Não conseguiu ser aberto");
        }
        System.out.println(FREQBASE);
    }
    
    
    public static void MostraParametros() {
        System.out.println("banco   = " + banco);
        System.out.println("driver  = " + driver);
        System.out.println("usuario = " + usuario);
        System.out.println("senha   = " + senha);        
        
        JOptionPane.showMessageDialog(null, "Parametros:" +
                                   "\nBanco:   " + banco + 
                                   "\nDriver:  " + driver +
                                   "\nUsuario: " + usuario +
                                   "\nSenha:   " + senha);

    }    

    public Properties getConfig() {
        return config;
    }

    public void setConfig(Properties config) {
        this.config = config;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
            
            
}
