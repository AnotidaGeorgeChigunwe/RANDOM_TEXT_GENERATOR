
/**
 * Write a description of interface IMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public interface IMarkovModel {
    public void setTraining(String text);
    
    public String getRandomText(int numChars);
    
    public void setRandom(int seed); 
    
    public String toString();
    
    public HashMap<String,ArrayList<String>> buildMap();
}
