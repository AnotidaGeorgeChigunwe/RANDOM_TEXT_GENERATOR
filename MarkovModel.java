
/**
 * Write a description of MarkovModel here.
 * 
 * @author (Anotida G Chigunwe) 
 * @version (01/16/2019)
 */
import java.util.*;

public class MarkovModel extends AbstractMarkovModel{
    private int n;
    
    public MarkovModel(int characters) {
        myRandom = new Random();
        n=characters;
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public String getRandomText(int numChars){
        double start = System.nanoTime();
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-n);
        String key = myText.substring(index,index+n);
        sb.append(key);
        for(int k=0; k < numChars-1; k=k+1){
            ArrayList<String> follows = getFollows(key);  
            if (follows.size()==0){
                break;
            }
            index = myRandom.nextInt(follows.size());
            sb.append(follows.get(index));
            key = key.substring(1)+follows.get(index);
        }
        double end = System.nanoTime();
        System.out.println("Time taken markovModel is : " + (end-start));
        return sb.toString();
    }  
    
    public String toString(){
        return  "MarkovModel of order  " + n;
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
