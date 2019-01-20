
/**
 * Write a description of Client here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class ClientTest {
    public void testrunModel() {
        MarkovRunner testRunModel = new MarkovRunner ();
        FileResource fr = new FileResource();
        String st = fr.asString();
        IMarkovModel wordTwo = new MarkovWordTwo(); 
        testRunModel .runModel(wordTwo,st,120,832);        
    } 
}
