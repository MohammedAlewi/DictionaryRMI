package DictionaryProject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class IOManager {
    private Map words=null;
    private String filename="dictionary.json";


    public IOManager(){}

    public IOManager(String filename){
        this.filename=filename;
    }


    public synchronized void populateDictionary(){
        JSONParser parser = new JSONParser();
        try (Reader reader = new FileReader(this.filename)) {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            ContainerFactory containerFactory = new ContainerFactory() {
                @Override
                public Map createObjectContainer() {
                    return new LinkedHashMap<>();
                }
                @Override
                public List creatArrayContainer() {
                    return new LinkedList<>();
                }
            };
            this.words= (Map)parser.parse(jsonObject.toJSONString(), containerFactory);
            System.out.println("Populate dict");

        }
        catch (Exception e){
            if(this.words==null) this.words=new LinkedHashMap();
            try {
                new File(this.filename).createNewFile();
            } catch (IOException e1) {
            }
            System.out.println(e.getMessage());
        }
    }

    public synchronized void saveChanges(){
        JSONObject dictionary= new JSONObject();
        this.words.forEach((key,object)->{ dictionary.put(key,object);});

        try (FileWriter file = new FileWriter(this.filename)) {
            file.write(dictionary.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized Word getWord(String key){
        Word word = new Word();
        try{
            LinkedHashMap map=(LinkedHashMap) this.words.get(key.toLowerCase());
            word.setfirst_used((String)map.get("first_used"));
            word.setMeanings((LinkedList)map.get("meanings"));
            word.setOrigin((String)map.get("origin"));
            word.setWord((String)map.get("word"));
        }catch (NullPointerException ex){
            word=null;
        }

        return  word;
    }

    public synchronized boolean addWord(Word word){
        if(this.words==null) return false;
        word.setWord(word.getWord().toLowerCase());
        LinkedHashMap map= this.convert(word);
        this.words.put(word.getWord(),map);

        return true;
    }

    public synchronized boolean removeWord(String key){
        Object obj= this.words.remove(key.toLowerCase());
        return obj!=null;
    }

    public synchronized boolean replaceWord(Word word){
        word.setWord(word.getWord().toLowerCase());
        LinkedHashMap map= this.convert(word);
        Object obj= this.words.replace(word.getWord(),map);

        return obj!=null;
    }

    public synchronized List getSuggestion(String key){
        List suggest= new ArrayList<String>();
        if(key.equals("")) return suggest;
        this.words.keySet().forEach((k)->{ if(k.toString().startsWith(key.toLowerCase())) suggest.add(k);});
        return suggest;
    }

    private LinkedHashMap convert(Word word){
        LinkedHashMap map=new LinkedHashMap();

        map.put("first_used",word.getfirst_used());
        map.put("meanings",word.getMeanings());
        map.put("origin",word.getOrigin());
        map.put("word",word.getWord());

        return map;
    }
    
       
    public String toJSONString(Word word) {
        ObjectMapper objectMapper=new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(word);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

}
