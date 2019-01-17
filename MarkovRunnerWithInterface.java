
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 
import java.util.*;

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        System.out.println("running with " + markov);
        markov.setRandom(seed);
        for(int k=0; k < 3; k++){
            String st= markov.getRandomText(size);
            printOut(st);
        }
    }
    
    public void runMarkov() {
        //FileResource fr = new FileResource();
        //String st = fr.asString();
        String st = "yes-this-is-a-thin-pretty-pink-thistle";
        //st = st.replace('\n', ' ');
        //int size = 50;
        
        //MarkovZero mz = new MarkovZero();
        //runModel(mz, st, size,5);
    
        //MarkovOne mOne = new MarkovOne();
        //runModel(mOne, st, size, 5);
        
        IMarkovModel mThree = new EfficientMarkovModel(2,42);
        mThree.setRandom(2);
        mThree.setTraining(st);
        HashMap<String,ArrayList<String>> map = mThree.buildMap();
        /*MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size);*/

    }

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }
    public void compareMethods () {
        
        FileResource fr = new FileResource();
        String st = fr.asString();
        //String st = "yes-this-is-a-thin-pretty-pink-thistle";
        st = st.replace('\n', ' ');
        int size = 1000;
        IMarkovModel EfficientMarkovModel = new EfficientMarkovModel(5,615);
        EfficientMarkovModel.setRandom(2);
        EfficientMarkovModel.setTraining(st);
        HashMap<String,ArrayList<String>> map = EfficientMarkovModel.buildMap();
        EfficientMarkovModel.getRandomText(size);
        
        
        /*FileResource fr2 = new FileResource();
        String st2 = fr2.asString();
        //String st2 = "yes-this-is-a-thin-pretty-pink-thistle";
        st2 = st2.replace('\n', ' ');
        IMarkovModel MarkovModel = new MarkovModel(2);
        MarkovModel.setRandom(42);
        MarkovModel.setTraining(st2);
        MarkovModel.getRandomText(1000);*/
    }    
}
