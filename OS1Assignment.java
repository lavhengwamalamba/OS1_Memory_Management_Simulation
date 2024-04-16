import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class OS1Assignment {
    public static String readfile(String filename) throws IOException {
        String referenceString = "";
        try {
            FileInputStream fileInputStream = new FileInputStream(filename);
            long byt;
            while ((byt = fileInputStream.read()) != -1) {
                referenceString += byt;
            }
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return referenceString;
    }

    public static void main(String[] args) throws IOException {
        readfile("OS1testsequence");
    }

}