package correcter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

public class Main {
    static String tripleCharErrorEmulator(String input){
        String errormsg = new String();

        for(int i = 0; i < input.length() - 3; i+=3){
            int chooseChar = (int)(Math.random() * 2);
            int random = (int)(Math.random() * 8 + 1);

            for(int j = 0; j < 3; j++){
                if(j == chooseChar){
                    errormsg += (char)((int)input.charAt(i + j) + random);
                }else {
                    //System.out.println(i + j);
                    errormsg += input.charAt(i + j);
                }
            }
        }

        int diff = input.length() - errormsg.length();
        if(diff == 1){
            errormsg += input.charAt(input.length() - 1);
        }else if(diff == 2){
            errormsg += input.charAt(input.length() - 2);
            errormsg += input.charAt(input.length() - 1);
        }else if(diff == 3){
            int chooseChar = (int)(Math.random() * 3 + 1);
            int random = (int)(Math.random() * 8 + 1);
            if(chooseChar != 3){
                errormsg += input.charAt(input.length() - 3);
            }else{
                errormsg += (char)((int)input.charAt(input.length() - 3) + random);
            }
            if(chooseChar != 2){
                errormsg += input.charAt(input.length() - 2);
            }else{
                errormsg += (char)((int)input.charAt(input.length() - 2) + random);
            }
            if(chooseChar != 1){
                errormsg += input.charAt(input.length() - 1);
            }else{
                errormsg += (char)((int)input.charAt(input.length() - 1) + random);
            }
        }
        return errormsg;
    }
    static String tripleCh(String str){
        String newStr = new String();
        for(int i = 0; i < str.length(); i++){
            for(int n = 0; n < 3; n++){
                newStr += str.charAt(i);
            }
        }
        return newStr;
    }
    static String toBinary( byte[] bytes )
    {
        StringBuilder sb = new StringBuilder(bytes.length * Byte.SIZE);
        for( int i = 0; i < Byte.SIZE * bytes.length; i++ ) {
            sb.append((bytes[i / Byte.SIZE] << i % Byte.SIZE & 0x80) == 0 ? '0' : '1');
            if((i + 1) % 8 == 0) {
                sb.append(' ');
            }
        }
        return sb.toString();
    }
    static byte[] fromBinary( String s )
    {
        int sLen = s.length();
        byte[] toReturn = new byte[(sLen + Byte.SIZE - 1) / Byte.SIZE];
        char c;
        for( int i = 0; i < sLen; i++ )
            if( (c = s.charAt(i)) == '1' )
                toReturn[i / Byte.SIZE] = (byte) (toReturn[i / Byte.SIZE] | (0x80 >>> (i % Byte.SIZE)));
            else if ( c != '0' )
                throw new IllegalArgumentException();
        return toReturn;
    }
    static String bitcorruption(String str){
        byte[] byteArr = str.getBytes();
        String binary = toBinary(byteArr);
        //System.out.println(binary);
        String[] arr = binary.split(" ");
        String[] newarr = new String[arr.length];
        for(int i = 0; i < arr.length; i++){
            byte[] byteArr1 = arr[i].getBytes();
            int rand = (int)(Math.random() * 7 + 1);
            if(byteArr1[rand] == '0'){
                byteArr1[rand] = '1';
            }else{
                byteArr1[rand] = '0';
            }
            newarr[i] = new String(byteArr1);
        }
        String s = String.join("", newarr);
        byteArr = fromBinary(s);
        s = new String(byteArr, StandardCharsets.UTF_8);
        return s;
    }
    public static void main(String[] args) {
        String data = "";
        String errormsg = "";
        //to read file
        try {
            File rFile = new File("send.txt");
            Scanner scanner = new Scanner(rFile);
            while(scanner.hasNextLine()){
                data = scanner.nextLine();
                System.out.println(data);
                errormsg = bitcorruption(data);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //to write file
        try{
            FileWriter wFile = new FileWriter("received.txt");
            wFile.write(errormsg);
            wFile.close();
        }catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println(errormsg);
    }
}
