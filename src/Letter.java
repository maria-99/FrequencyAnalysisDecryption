public class Letter {
    private final char lowerCase, upperCase;
    private int nbOfRepetitionsLower, nbOfRepetitionsUpper, nbOfRepetitions;
    private double frequency;

    public Letter(int unicodeUpperCase){
        upperCase = (char) unicodeUpperCase;
        lowerCase = (char) (unicodeUpperCase + 32);
    }

    public char getUpperCase(){
        return upperCase;
    }

    public char getLowerCase(){
        return lowerCase;
    }

    public void setReps(int upperReps, int lowerReps){
        this.nbOfRepetitionsUpper = upperReps;
        this.nbOfRepetitionsLower = lowerReps;
        nbOfRepetitions = nbOfRepetitionsLower + nbOfRepetitionsUpper;
    }

    public int getNbOfRepetitions(){
        return nbOfRepetitions;
    }

    public void setFrequency(int totalNbOfLetters){
        frequency = (double)nbOfRepetitions/totalNbOfLetters;
    }

    public double getFrequency(){
        return frequency;
    }

}
