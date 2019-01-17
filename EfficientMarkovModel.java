
/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author (Anotida G Chigunwe) 
 * @version (01/16/2019)
 */
import java.util.*;

public class EfficientMarkovModel implements IMarkovModel{
    private String myText;
    private Random myRandom;
    private int n;
    private HashMap<String,ArrayList<String>> followsList;
    private int seedRan;
    
    public EfficientMarkovModel(int characters, int seed) {
        myRandom = new Random(seed);
        n=characters;
        seedRan = seed;
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seedRan);
    }
    
        public void setTraining(String s){
        myText = s.trim();        
        followsList = buildMap();
    }
    
    public HashMap<String,ArrayList<String>> buildMap(){
        HashMap<String,ArrayList<String>>  map = new HashMap<String,ArrayList<String>> ();
        for (int k=0;k< myText.length()-n; k++ ) {
            String key = myText.substring(k,k+n);
            if (!map.containsKey(key)){
                int index = myText.indexOf(key);
                ArrayList<String> answer = new ArrayList<String>();
                while (!(index ==-1)&& index<myText.length()-key.length()) {
                    answer.add(myText.substring(index+key.length(),index+key.length()+1));
                    index = myText.indexOf(key,index+1);
                }
                map.put(key,answer);
            }
        }
        ArrayList<String> last = new ArrayList<String>();
        map.put(myText.substring(myText.length()-n),last);
        System.out.println("Number of keys : " + map.size() +"\n");
        /*for (String k : map.keySet()) {
            System.out.println(k);
        }
        int value=0;
        for (ArrayList<String> k : map.values()) {  
            if (k.size()>value) {
                value = k.size();
            }
        }
        System.out.println("The largest value : " + value +"\n");
        /*for (String k : map.keySet()) {
            if (map.get(k).size()==value) {
                System.out.println(k);
            }
        }*/
        return map;        
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
            ArrayList<String> follows = followsList.get(key);
            if (follows.size()==0){
                break;
            }
            index = myRandom.nextInt(follows.size());
            sb.append(follows.get(index));
            key = key.substring(1)+follows.get(index);
        }
        double end = System.nanoTime();
        System.out.println("Time taken Efficient markov Model is : " + (end-start));
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
}
