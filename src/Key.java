public class Key {
    private int value, repetitions;

    public Key(int value){
        this.value = value;
        repetitions = 1;
    }

    public void incrementReps (){
        repetitions++;
    }

    public int getValue(){
        return value;
    }

    public int getRepetitions(){
        return repetitions;
    }
}
