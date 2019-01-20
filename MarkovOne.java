
/**
 * Write a description of MarkovOne here.
 * 
 * @Anotida George Chigunwe
 * @version (01/14/2019)
 */
import java.util.*;

public class MarkovOne extends AbstractMarkovModel{
    private String myText;
    private Random myRandom;
    
    public MarkovOne() {
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
        int index = myRandom.nextInt(myText.length()-1);
        String key = myText.substring(index,index+1); 
        sb.append(key);
        for(int k=0; k < numChars-1; k=k+1){
            ArrayList<String> follows = getFollows(key);  
            if (follows.size()==0){
                break;
            }
            index = myRandom.nextInt(follows.size());
            sb.append(follows.get(index));
            key = follows.get(index);
        }
        
        return sb.toString();
    }
    
    public String toString(){
        return  "MarkovModel of order  1";
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
