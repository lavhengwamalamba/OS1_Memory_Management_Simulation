import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class OS1Assignment {
    static HashMap<String, String> indecies = new HashMap<>();

    public static ArrayList<String> readfile(String filename) throws IOException {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(filename);
            long byt;
            int remaining = fileInputStream.available();
            for (int j = 0; j < (remaining / 8); j++) {
                String referenceString = "";
                for (int i = 0; i < 8; i++) {
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

    public static String Hex_to_binary(String hexstring) {
        String binaryValue = Long.toBinaryString(Long.parseLong(hexstring, 16));
        binaryValue = String.format("%64s", binaryValue).replace(' ', '0');
        return binaryValue;
    }

    public static String binary_to_new(String binaryValue) {
        // Slice the 64bit string into 12 bits
        String bit5 = binaryValue.substring(52, 57);
        String bit7 = binaryValue.substring(57);
        String newsString = indecies.get(bit5) + bit7;
        return newsString;
    }

    public static void fillmap() {
        indecies.put("00000", "00010");
        indecies.put("00001", "00100");
        indecies.put("00010", "00001");
        indecies.put("00011", "00111");
        indecies.put("00100", "00011");
        indecies.put("00101", "00101");
        indecies.put("00110", "00110");
    }

    public static void main(String[] args) throws IOException {
        fillmap();
        ArrayList<String> arrayList = readfile("OS1testsequence");
        System.out.println(arrayList);
        System.out.println(binary_to_new(Hex_to_binary("00000044")));
        System.out.println(binary_to_new(Hex_to_binary(arrayList.get(0))));
    }

}