package DictionaryProject;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONAware;

public class UserHandler extends UnicastRemoteObject implements  DictionaryRMI {
    private IOManager ioManager;
    public UserHandler(IOManager ioManager) throws RemoteException{
        super();
        this.ioManager=ioManager;
    }


    @Override
    public Word getWord(String key) throws RemoteException {
        return ioManager.getWord(key);
    }

    @Override
    public boolean addWord(Word word) throws RemoteException {
        boolean ans= ioManager.addWord(word);
        ioManager.saveChanges();
        return ans;
    }

    @Override
    public boolean removeWord(String key) throws RemoteException {
        boolean ans= ioManager.removeWord(key);
        ioManager.saveChanges();
        return ans;
    }

    @Override
    public boolean replaceWord(Word word) throws RemoteException {
        boolean ans= ioManager.replaceWord(word);
        ioManager.saveChanges();
        return ans;
    }

    @Override
    public List getSuggestion(String key) throws RemoteException {
        return ioManager.getSuggestion(key);
    }
    

}
