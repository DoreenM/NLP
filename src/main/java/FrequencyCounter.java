/**
 * Created by Doreen on 2015-04-11.
 */
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

interface IFrequencyCounter{
    public String[] getMostFrequenctWords(String content, int numberOfwords );
}

public class FrequencyCounter  implements IFrequencyCounter{

    /**
     * This is the Frequency Class
     * which has only one property count.
     * This is used to save autoboxing/unboxing
     * overhead when using maps.
     */
    private class Frequency{

        private Frequency(){
            this.count = 1;
        }

        private int count;

        public int getFrequency(){
            return this.count;
        }

        public void incrementFrequency(){
            this.count++;
        }

        public void setCount(int count){
            this.count = count;
        }
    }

    /**
     * Token class extends Frequency. This is
     * used to maintain word frequency relationship.
     * (Just like a pair.)
     */
    private class Token extends Frequency{

        private String word;

        private Token(String word, int count){
            this.word = word;
            setCount(count);
        }

    }



    @Override
    public String[] getMostFrequenctWords(String content, int numberOfwords) {
        // basic validations
        if( null == content) content = "";
        if(numberOfwords <= 0) return new String[0];
        int maxSofar = 0;
        HashMap<String,Frequency> wordMap = new HashMap<String,Frequency>();

        String[] contentArray = content.split("\\s+");
        addWordsToMap(contentArray, wordMap);
        return getSortedArray(numberOfwords, maxSofar, wordMap);
    }



    private String[] getSortedArray(int numberOfwords, int maxSofar, Map<String, Frequency> wordMap) {
        String[] mostFreqWordsArr;
        int i =0;
        Token[] wordsArr = new Token[wordMap.keySet().size()];

        for (String key : wordMap.keySet()) {
            wordsArr[i++] = new Token(key, wordMap.get(key).getFrequency());
            if(maxSofar < wordMap.get(key).getFrequency()){
                maxSofar = wordMap.get(key).getFrequency();
            }
        }

        wordMap = null; // just to free memory in case input is a very large string
        int[] frequencyArr = new int[maxSofar+1];
        String[] stringArr = new String[wordsArr.length];

        for(i =0; i<wordsArr.length; i++)       frequencyArr[wordsArr[i].getFrequency()] += 1;
        for(i =1; i<frequencyArr.length; i++)   frequencyArr[i] += frequencyArr[i-1];
        for(i= 0; i<wordsArr.length; i++)   {
            stringArr[frequencyArr[wordsArr[i].getFrequency()]-1] =  wordsArr[i].word;
            frequencyArr[wordsArr[i].getFrequency()] -=1;
        }

        if(stringArr.length-numberOfwords >= 0)
            mostFreqWordsArr = Arrays.copyOfRange(stringArr, stringArr.length-numberOfwords, stringArr.length);
        else
            mostFreqWordsArr = Arrays.copyOfRange(stringArr, 0, stringArr.length);

// reverse the string so most frequent words come first
        for(i = 0; i < mostFreqWordsArr.length / 2; i++){
            String temp = mostFreqWordsArr[i];
            mostFreqWordsArr[i] = mostFreqWordsArr[mostFreqWordsArr.length-1 - i];
            mostFreqWordsArr[mostFreqWordsArr.length-1 - i] = temp;
        }
        return mostFreqWordsArr;
    }

    /**
     * @param contentArray
     * @param wordMap
     */
    private void addWordsToMap(String[] contentArray, Map<String, Frequency> wordMap) {
        for (String string : contentArray) {
            Frequency token = wordMap.get(string);
            if(token == null) {
                wordMap.put(string,new Frequency());
            }else {
                token.incrementFrequency();
            }
        }
    }
}
