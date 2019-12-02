/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DictionaryProject;

import java.rmi.Naming;
import java.util.LinkedList;
import java.util.Scanner;
import javaapplication1.AddServerInterface;

/**
 *
 * @author maroc
 */
public class RMIClient {
    private DictionaryRMI dictionaryRMI;
    
    public RMIClient(String address){
        try{
            dictionaryRMI= (DictionaryRMI)Naming.lookup("rmi://"+address+"/AddService");
        }
        catch(Exception e) {
                e.printStackTrace();
        }
    }
   
    public void addWord() throws Exception{
        Word word=inputWord();
        boolean result=this.dictionaryRMI.addWord(word);
        
        if(result) System.out.println("Word submittd successfully");      
        else System.out.println(" unable to submit word successfully");
    }
    
    public void removeWord() throws Exception {
        String val=getInput(" Enter the word "); 
           
        boolean result=this.dictionaryRMI.removeWord(val);
        
        if(result) System.out.println("Word deleted successfully");      
        else System.out.println("Unable to deleted Word successfully");
    
    }
    
    public void getWord() throws Exception {
        String val=getInput(" Enter the word "); 
           
        Word result=this.dictionaryRMI.getWord(val);
        printWord(result);
        if(result==null) System.out.println("Word not Found!!");    
    
    }
    
    public void replaceWord() throws Exception{
        Word word=inputWord();
        boolean result=this.dictionaryRMI.replaceWord(word);
        
        if(result) System.out.println("Word replaced successfully");      
        else System.out.println(" unable to replace word");   
    }
    
    private void printWord(Word word){
        System.out.println("-----------------------------------------------");        
        System.out.println("Word :"+word.getWord());        
        System.out.println("First Used :"+word.getfirst_used());
        System.out.println("Origin :"+word.getOrigin());
        for(String meaning:word.getMeanings())
            System.out.println("    Meaning :"+meaning);
        System.out.println("-----------------------------------------------");
    }
    
    private Word inputWord(){
        System.out.println("-----------------------------------------------");
        String val=getInput(" Enter the word ");       
        String firt_used=getInput(" Enter when first used ");
        String origin=getInput(" Enter its origin ");
        
        LinkedList linkedlist= new LinkedList<String>();
        System.out.println("Enter its meanings ( enter exit when you are done..)");
        String ans=getInput("Enter meaning ");
        while(!ans.equals("exit")){
            linkedlist.add(ans);
            ans=getInput("Enter meaning ");
        }
        Word word=new Word(val,firt_used,origin,linkedlist);  
        System.out.println("-----------------------------------------------");

        return word;
    }
    
    
    public String getInput(String prompt){
        System.out.print(prompt+" >> ");
        Scanner scan=new Scanner(System.in);
        return scan.nextLine();
    }
    
    
    public static void main(String args[]) {
        RMIClient rmiClient=new RMIClient("127.0.1.1");
        System.out.println(" Enter exit to exit or");
        System.out.println(" 1 to get word ");
        System.out.println(" 2 to add word");
        System.out.println(" 3 to replace word");
        System.out.println(" 4 to remove word");
        try{
        String input=rmiClient.getInput("");
            while(!input.equals("exit")){
                switch(input){
                    case "1":
                        rmiClient.getWord();
                        break;
                    case "2":
                        rmiClient.addWord();
                        break;
                    case "3":
                        rmiClient.replaceWord();
                        break;
                    case "4":
                        rmiClient.removeWord();
                        break;
                } 
                input=rmiClient.getInput("");
            }
        }catch(Exception e){}
    }
    
    
   
}
