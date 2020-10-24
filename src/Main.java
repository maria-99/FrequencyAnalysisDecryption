import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class Main {
    public static void main(String[] args) throws IOException {

        //Reading and encoding a chapter of the book
        Path pathChapter = Paths.get("C:/",  "chapter.txt") ;
        String chapter = Files.readString(pathChapter);
        Encoder encoder = new Encoder(chapter, 8);
        String encodedText = encoder.getEncodedText();

        //Creating a table of letters and their frequency for the encoded text
        FrequencyTable tableForEncodedText = new FrequencyTable(encodedText);
        tableForEncodedText.printFrequencyTable();

        //Reading a sample text (War and Peace)
        Path pathVIM = Paths.get("C:/",  "vim.txt") ;
        String sampleText = Files.readString(pathVIM);

        //Creating a table of letters and their frequency for the sample text
        FrequencyTable tableForSample = new FrequencyTable(sampleText);
        tableForSample.printFrequencyTable();

        //Decoding the encoded chapter by letter or by keys
        Decoder decoder = new Decoder(tableForEncodedText, tableForSample);
        String decodedTextByLetter = decoder.decodeTextByLetter(encodedText);       //Decoding by letter
        System.out.println(decodedTextByLetter);

        String decodedTextByKey = decoder.decodeTextByKey(encodedText);       //Decoding by Key
        //System.out.println(decodedTextByKey);
        decoder.printPossibleKeys();
    }

}
