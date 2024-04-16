import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class OS1Assignment {
    
    public static ArrayList<String> readfile(String filename) throws IOException {
        ArrayList<String> arrayList = new ArrayList<>() ;
        try {
            FileInputStream fileInputStream = new FileInputStream(filename);
            long byt;
            int remaining = fileInputStream.available();
            for (int j = 0; j < (remaining/8);j++) {
                String referenceString = "";
                for (int i = 0 ; i <8 ; i++){
                    byt = fileInputStream.read();
                    referenceString = "" + byt + referenceString;
                }
                arrayList.add(referenceString);
                // break;
            }
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public static String Hex_to_binary(String HexString){

        return null;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(readfile("OS1testsequence"));
    }

}