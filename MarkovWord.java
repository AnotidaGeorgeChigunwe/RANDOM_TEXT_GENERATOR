
/**
 * Write a description of MarkovWord here.
 * 
 * @author (Anotida G Chigunwe) 
 * @version (01/20/2019)
 */
import java.util.*;
import edu.duke.*;

public class MarkovWord implements IMarkovModel{
    private String[] myText;
    private int myOrder;
    private Random myRandom;
    private HashMap<String,ArrayList<String>>  map;
    public MarkovWord (int order) {
        myOrder=order;
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        text.trim();
        myText = text.split("\\s+");
        System.out.println("myText size :" + myText.length);
        map = buildMap();
        printHashMapInfo(); 
    }

    private int indexOf(String[] words, WordGram target,int start) {
        int answer = -1;
        String firstWord = target.wordAt(0);
        for (int k = start ; k<words.length; k++) {
            if(words[k].equals(firstWord)){
                for (int y = 1; y < target.length(); y++){
                    if(k+y<words.length &&!words[k+y].equals(target.wordAt(y))){
                        answer = -1;                        
                        break;
                    }
                    else{
                        answer = k;
                    }
                }
            }
            if (answer==k) {
                break;
            }
         }          
        return answer;        
    }
    
    //method returns an ArrayList of all the single words that immediately
    //follow an instance of the WordGram parameter somewhere in the training text.  
    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> answer = map.get(kGram.toString());
        return answer;
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram key = new WordGram(myText,index,myOrder);
        sb.append(key.toString());
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = key.shiftAdd(next);
        }
        
        return sb.toString().trim();
    }
    
    //FROM IMARKOV MODEL
    public HashMap<String,ArrayList<String>> buildMap(){
        HashMap<String,ArrayList<String>>  map = new HashMap<String,ArrayList<String>> ();
        for (int k =0; k< myText.length-myOrder;k++) {
            ArrayList<String> answer = new ArrayList<String>();
            WordGram kGram = new WordGram(myText,k,myOrder);
            int prevIndex = indexOf(myText, kGram,0);        
            while (!(prevIndex==-1) && prevIndex < myText.length-kGram.length()) {   
                answer.add(myText[prevIndex + kGram.length()]);
                int currIndex = indexOf(myText, kGram,prevIndex +1);          
                prevIndex = currIndex; 
            }
            if (!map.containsKey(kGram.toString())) {
                map.put(kGram.toString(),answer);
            }
        } 
        ArrayList<String> answer1 = new ArrayList<String>();
        WordGram kGram1 = new WordGram(myText,myText.length-myOrder,myOrder);
        map.put(kGram1.toString(),answer1);
        return map;       
    }
    
    public String toString(){
        return "MarkovWord  " +myOrder;
    }
    
    public void printHashMapInfo() {
        //System.out.println("The Keys Are");
        //for(String s: map.keySet()) {
        //    System.out.println(s);
        //}
        System.out.println("The number of keys is : " + map.size());
        int value=0;
        for (ArrayList<String> k : map.values()) {  
            if (k.size()>value) {
                value = k.size();
            }
        }
        System.out.println("The largest value : " + value +"\n");
        System.out.println("The keys with largest value is");
        for (String k : map.keySet()) {
            if (map.get(k).size()==value) {
                System.out.println(k);
            }
        }
    }
}
