import java.util.ArrayList;
import java.util.List;

public class Decoder {
    private final FrequencyTable encodedTable, sampleTable;
    private List<Key> possibleKeys = new ArrayList<>();

    public Decoder(FrequencyTable encodedTable, FrequencyTable sampleTable){
        this.encodedTable = encodedTable;
        this.sampleTable = sampleTable;
        findPossibleKeys();
    }

    //Compares the letters by frequency and calculates the shift between them
    //Saves the value of the shift (key) and the nbs of its repetitions
    private void findPossibleKeys(){
        int possibleKey;
        boolean keyCounted;
        for(int i = 0; i<encodedTable.getAlphabet().size() ; i++){
            keyCounted=false;
            possibleKey = encodedTable.getAlphabet().get(i).getUpperCase() - sampleTable.getAlphabet().get(i).getUpperCase();
            for (Key currentKey : possibleKeys) {
                if (currentKey.getValue() == possibleKey) {
                    currentKey.incrementReps();
                    keyCounted = true;
                }
            }
            if(!keyCounted){
                Key newKey = new Key(possibleKey);
                possibleKeys.add(newKey);
            }
        }
    }

    //Finds the most frequent key from all possible keys
    public Key getProbableKey(){
        Key probableKey = possibleKeys.get(0);
        for (Key currentKey : possibleKeys) {
            if (currentKey.getRepetitions() > probableKey.getValue()) {
                probableKey = currentKey;
            }
        }
        return probableKey;
    }

    public List<Key> getPossibleKeys(){
        return possibleKeys;
    }

    public void printPossibleKeys(){
        for(Key key : possibleKeys){
            System.out.println("Key " + key.getValue() + " : " + key.getRepetitions());
        }
    }

    //Decoding an encrypted text by a key
    //Useful in case all the letters are shifted by a constant value
    public String decodeTextByKey (String encodedText) {
        Key key = getProbableKey();
        char currentChar, newChar;
        String decodedText = "";
        for (int i = 0; i < encodedText.length(); i++) {
            currentChar = encodedText.charAt(i);
            if (0x0410 <= currentChar && currentChar < 0x0430) {
                newChar = (char) (currentChar - key.getValue());
                if (newChar < 0x0410) {
                    newChar += 32;
                }
            } else if (0x0430 <= currentChar && currentChar < 0x0450) {
                newChar = (char) (currentChar - key.getValue());
                if (newChar < 0x0430) {
                    newChar += 32;
                }
            } else {
                newChar = encodedText.charAt(i);
            }
            //noinspection StringConcatenationInLoop
            decodedText += newChar;

        }
        return decodedText;
    }

    //Decoding letter by letter
    //Decrypts less percentage of the text but is useful for when the letters are changed randomly
    public String decodeTextByLetter (String encodedText){
        char currentChar, newChar;
        int positionInFreqTable;
        String decodedText = "";
        for (int i = 0; i < encodedText.length(); i++) {
            currentChar = encodedText.charAt(i);
            positionInFreqTable = 0;

            if(0x0410 <= currentChar && currentChar < 0x0430){
                while (currentChar != encodedTable.getAlphabet().get(positionInFreqTable).getUpperCase()){
                    positionInFreqTable++;
                }
                newChar = sampleTable.getAlphabet().get(positionInFreqTable).getUpperCase();
            }
            else if(0x0430 <= currentChar && currentChar < 0x0450){
                while (currentChar != encodedTable.getAlphabet().get(positionInFreqTable).getLowerCase()){
                    positionInFreqTable++;
                }
                newChar = sampleTable.getAlphabet().get(positionInFreqTable).getLowerCase();
            }
            else{
                newChar = encodedText.charAt(i);
            }
            decodedText += newChar;
        }
        return decodedText;

    }

}
