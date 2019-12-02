package DictionaryProject;

import javax.swing.*;
import java.io.IOException;
import java.net.*;
import java.rmi.Naming;

public class DictionaryServer {
    private IOManager ioManager;
    private String filename="dictionary.json";
    private String hostName="localhost";
    private int    PORT= 5000;

    public DictionaryServer(){}
    public DictionaryServer(int port,String filename, String hostName){
        this.hostName=hostName;
        this.PORT=port;
        this.filename=filename;
    }

    public void initialize_server(){
        try{
            this.ioManager=new IOManager(this.filename);
            this.ioManager.populateDictionary();
            DictionaryRMI userHandler=new UserHandler(ioManager);
            Naming.rebind("AddService",userHandler); //rmi://127.0.0.1:5000/
            this.ioManager.saveChanges();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] arg) {
        DictionaryServer server=null;

        try{
            server =new DictionaryServer();
        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null," Please fill a valid Port number, host name then filename. ");
            return;
        }

       server.initialize_server();
    }

}
