
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (Anotida G Chigunwe)
 * @version (01/16/2019)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
 
    abstract public String getRandomText(int numChars);
    
    protected ArrayList<String> getFollows(String key) {
        int index = myText.indexOf(key);
        ArrayList<String> answer = new ArrayList<String>();
        while (!(index ==-1)&& index<myText.length()-key.length()) {
            answer.add(myText.substring(index+key.length(),index+key.length()+1));
            index = myText.indexOf(key,index+1);
        }
        return answer;
    }
}
