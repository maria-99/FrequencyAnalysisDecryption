public class Encoder {

    private final String initialText;
    private String encodedText="";
    private final int key;

    public Encoder(String text, int key){
        initialText = text;
        this.key = key;
        encode();
    }

    private void encode (){
        char currentChar, newChar;
        for (int i=0; i<initialText.length(); i++){
            currentChar = initialText.charAt(i);
            if(0x0410<=currentChar && currentChar<0x0430){
                newChar = (char) (currentChar + key);
                if(newChar>=0x0430){
                    newChar -= 32;
                }
            }
            else if(0x0430<=currentChar && currentChar<0x0450){
                newChar = (char) (currentChar + key);
                if(newChar>=0x0450){
                    newChar -= 32;
                }
            }
            else{
                newChar = initialText.charAt(i);
            }
            //noinspection StringConcatenationInLoop
            encodedText += newChar;
        }

    }

    public String getEncodedText(){
        return encodedText;
    }

}
