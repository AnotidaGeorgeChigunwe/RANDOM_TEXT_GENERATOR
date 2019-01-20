
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class MarkovWordOne implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordOne() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        text.trim();
        myText = text.split("\\s+");
    }
    
    /*This method starts looking at the start position and returns the first index location in words that matches target. 
     * If no word is found,method returns -1.  */
     
    private int indexOf(String[] words, String target,int start) {
        int answer = -1;
        for (int k = start ; k<words.length; k++) {
            if(words[k].equals(target)){
                answer=k;
                break;
            }
        }
        return answer;
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-1);  // random word to start with
        String key = myText[index];
        sb.append(key);
        sb.append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = next;
        }
        
        return sb.toString().trim();
    }
    
    //This method returns an ArrayList of all the single words that immediately follow the key in the training text.  
    private ArrayList<String> getFollows(String key) {
        ArrayList<String> answer = new ArrayList<String>();
        int prevIndex = indexOf(myText, key,0);        
        while (!(prevIndex==-1) && prevIndex < myText.length-1) {   
            answer.add(myText[prevIndex + 1]);
            int currIndex = indexOf(myText, key,prevIndex +1);          
            prevIndex = currIndex; 
        }
        return answer;
    }
    
    public void testIndexOf() {
        String tes = "anotida i love you i love anotida anotida love me yes he love me so me and anotida we both love eachother";
        String[] test = tes.split("\\s+");
        //“this”  0, “this”  3, “frog”  0, “frog”5, “simple”  2 and “test”  5. 
        int answer =  indexOf(test,"anotida",1);
        System.out.println(answer);
    }
    //FROM IMARKOV MODEL
    public HashMap<String,ArrayList<String>> buildMap(){
        HashMap<String,ArrayList<String>>  map = new HashMap<String,ArrayList<String>> ();
        return map;        
    }   
    
    public String toString(){
        return "NO STRING";
    }
    
    
    public void testGetFollows() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        setTraining(st);
        setRandom(175);
        getRandomText(120);      
    }
}
