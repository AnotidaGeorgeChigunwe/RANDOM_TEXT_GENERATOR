
public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }
    //returns the length of the WordGram
    public int length(){
        return myWords.length;
    }
    
    //prints a WordGram out, showing all the words in the WordGram on one line separated by spaces 
    public String toString(){
        StringBuilder ret =  new StringBuilder();
        for (int k=0;k<myWords.length;k++) {
            ret.append(myWords[k]+" ");
        }
        return ret.toString();
    }
    
    //This method returns true if two WordGrams are equal and false otherwise. 
    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        if(myWords.length != other.length()) {
            return false;
        }
        for (int k =0;k<myWords.length ;k++){
            if(!myWords[k].equals(other.wordAt(k))){
                return false;
            }
        }
        return true;
    }
    
    //returns a new WordGram the same size as the original, consisting of each word shifted down one index and new add at end.
    public WordGram shiftAdd(String word) {
        String[] copy = new String[length()];
        for (int y=0;y<copy.length-1;y++) {
            copy[y] = wordAt(y+1);
        }
        copy[length()-1] = word;
        WordGram out = new WordGram(copy, 0, length());
        return out;
    }

}