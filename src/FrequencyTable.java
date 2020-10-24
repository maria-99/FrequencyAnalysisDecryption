import java.util.ArrayList;
import java.util.List;

public class FrequencyTable {

    private List<Letter> alphabet = new ArrayList<>();
    private int nbOfLetters=0;

    public FrequencyTable(String text){
        setNbOfLetters(text);
        fillTable(text);
        sortByFrequency();
    }

    public List<Letter> getAlphabet(){
        return alphabet;
    }

    public int getNbOfLetters (){
        return nbOfLetters;
    }


    private void fillTable(String text) {
        for (int unicode = 0x0410; unicode<0x0430; unicode++){
            Letter currentLetter = new Letter(unicode);
            int upperReps = 0, lowerReps = 0;
            for (int i = 0; i < text.length(); i++) {
                if (text.charAt(i) == unicode) {
                    upperReps++;
                }
                if (text.charAt(i) == unicode + 32) {
                    lowerReps++;
                }
            }
            currentLetter.setReps(upperReps, lowerReps);
            currentLetter.setFrequency(nbOfLetters);
            alphabet.add(currentLetter);
        }
    }

    private void setNbOfLetters(String text) {
        for (int i = 0; i<text.length(); i++){
            if (0x0410<=text.charAt(i) && text.charAt(i)<=0x044f){
                nbOfLetters++;
            }
        }
    }

    private void sortByFrequency (){
        boolean sorted = false;
        while(!sorted){
            sorted = true;
            for(int i = 0; i < alphabet.size() - 1; i++){
                if (alphabet.get(i).getFrequency()<alphabet.get(i+1).getFrequency()){
                    alphabet.add(i+2, alphabet.get(i));
                    alphabet.remove(i);
                    sorted = false;
                }
            }
        }
    }

    public void printFrequencyTable(){
        for(Letter letter : alphabet){
            System.out.println(letter.getUpperCase() +""+ letter.getLowerCase() + " = " + letter.getNbOfRepetitions()
                    + ", " + letter.getFrequency());
        }
    }


}
