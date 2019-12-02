package DictionaryProject;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface DictionaryRMI extends Remote {
     Word getWord(String key) throws RemoteException;
     boolean addWord(Word word)  throws RemoteException;
     boolean removeWord(String key) throws RemoteException;
     boolean replaceWord(Word word) throws RemoteException;
     List getSuggestion(String key) throws RemoteException;

}
