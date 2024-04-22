import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class OS1Assignment {
    static HashMap<String, String> indecies = new HashMap<>();

    public static ArrayList<String> readfile(String filename) throws IOException {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(filename);
            int byt;
            int count = 0;
            String referenceString = "";

            while ((byt = fileInputStream.read()) != -1) {
                String bytstring = Integer.toHexString(byt);
                count++;
                referenceString = "" + bytstring + referenceString;
                if (count >= 8) {
                    arrayList.add(referenceString);
                    referenceString = "";
                    count = 0;
                }

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
        String newsString = indecies.get(bit5);

        if (newsString == null) {
            newsString = "00000";
        }
        newsString += bit7;
        int binaryint = Integer.parseInt(newsString, 2);
        String hexvalue = Integer.toHexString(binaryint);
        hexvalue = "0x" + hexvalue + "\n";
        return hexvalue;
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

    public static void write_to_file(ArrayList<String> arrayList) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("output-OS1");
        for (int i = 0; i < arrayList.size(); i++) {
            byte[] bytestring = binary_to_new(Hex_to_binary(arrayList.get(i))).getBytes();
            fileOutputStream.write(bytestring);

        }
        fileOutputStream.close();
    }

    public static void main(String[] args) throws IOException {
        fillmap();
        String userinput=args[0];
        ArrayList<String> arrayList = readfile(userinput);
        write_to_file(arrayList);
    }

}