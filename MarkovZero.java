
/**
 * Write a description of class MarkovZero here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import java.util.*;

public class MarkovZero extends AbstractMarkovModel{
    private String myText;
    private Random myRandom;
    
    public MarkovZero() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for(int k=0; k < numChars; k++){
            int index = myRandom.nextInt(myText.length());
            sb.append(myText.charAt(index));
        }
        
        return sb.toString();
    }
    
    public String toString(){
        return  "MarkovModel of order  0";
    }
    
    public void test() {
       Random test = new Random();
       int x = test.nextInt(4);
       for (int k=0;k<2;k++) {
        System.out.println(x+"\n");
        x = test.nextInt(4);
        }
     }
     
        public HashMap<String,ArrayList<String>> buildMap() {
        HashMap<String,ArrayList<String>> buildMap = new HashMap<String,ArrayList<String>> ();
        return buildMap;
    }
}
