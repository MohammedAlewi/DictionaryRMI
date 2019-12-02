package DictionaryProject;

import java.io.IOException;
import java.rmi.Naming;
import java.util.List;

public class ClientManager {

    private  int PORT=7789;
    private  String ADDRESS="127.0.0.1";
    private  boolean connected=false;
    private  DictionaryRMI rmiStub;
    public ClientManager(){}

    public ClientManager(int PORT,String address){
        this.PORT=PORT;
        this.ADDRESS=address;
    }

    public String initialize_connection(){
        try {
            this.rmiStub=(DictionaryRMI) Naming.lookup("rmi://127.0.0.1:5000/AddService");

            this.connected=true;
            return "Connection established successfully.";
        } catch (Exception e) {
            this.connected=false;
            return "Unable to connect to the server please make sure the server is started and try again. ";
        }
    }

    public Word getWord(String key){
        Word word=null;
        try {
            word=this.rmiStub.getWord(key);
        }catch (Exception e) { this.connected=false;}

        return word;
    }

    public boolean addWord(Word word){
        if(this.getWord(word.getWord())!=null) return false;
        boolean answer=false;
        try {
            answer=this.rmiStub.addWord(word);
        }catch (Exception e) { this.connected=false;}
        return answer;
    }

    public boolean removeWord(String key){
        boolean answer=false;
        try {
            answer=this.rmiStub.removeWord(key);
        }catch (Exception e) { this.connected=false;}
        return answer;
    }

    public boolean replaceWord(Word word){
        boolean answer=false;
        try {
            answer=this.rmiStub.replaceWord(word);
        }catch (Exception e) { this.connected=false;}
        return answer;
    }

    public List getSuggestion(String key){
        List meanings=null;
        try {
            meanings= this.rmiStub.getSuggestion(key);
        } catch (Exception e) {this.connected=false;}
        return meanings;
    }

    public boolean isConnected(){
        return this.connected;
    }

}
