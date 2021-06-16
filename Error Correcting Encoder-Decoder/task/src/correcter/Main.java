package correcter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
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
    static String bitcorruption(String binary){
        //byte[] byteArr = str.getBytes();
        //String binary = toBinary(byteArr);
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
        String s = String.join(" ", newarr);
        //byte[] byteArr = fromBinary(s);
        //s = new String(byteArr, StandardCharsets.UTF_8);
        return s;
    }
    static byte[] fileExtract(String filename){
        //to read file
        byte[] data = new byte[4096];
        try {
            File rFile = new File(filename);
            data = Files.readAllBytes(rFile.toPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
    static String textfileExtract(String filename){
        //to read file
        String data = "";
        try {
            File rFile = new File(filename);
            Scanner scanner = new Scanner(rFile);
            while(scanner.hasNextLine()){
                data = scanner.nextLine();
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }
    static String strtoHex(String data){
        byte[] dataArr = data.getBytes(StandardCharsets.UTF_8);
        StringBuilder sb = new StringBuilder();
        for(byte b: dataArr){
            sb.append(String.format("%02X ", b));
        }
        return sb.toString();
    }
    static String strtoBin(String data){
        byte[] dataArr = data.getBytes(StandardCharsets.UTF_8);
        String s = "";
        for(int i = 0; i < dataArr.length; i++){
            s += String.format("%8s", Integer.toBinaryString(dataArr[i] & 0xFF)).replace(' ', '0') + " ";
        }
        return s;
    }
    static String expand(String s){
        //remove the spaces
        String[] sArr = s.split(" ");
        s = String.join("", Arrays.asList(sArr));
        //System.out.println(s);

        String exp = "";
        for(int i = 1; i <= s.length(); i++){
            int idx = i - 1;
            if(i % 3 == 0){
                exp += s.charAt(idx);
                exp += s.charAt(idx);
                exp += ".. ";
            }else{
                exp += s.charAt(idx);
                exp += s.charAt(idx);
            }
        }
        sArr = exp.split(" ");
        //last arr
        int n = sArr[sArr.length - 1].length();
        if(n < 8){
            for(int i = 0; i < 8 - n; i++){
                sArr[sArr.length - 1] += ".";
            }
        }
        exp = String.join(" ", Arrays.asList(sArr));
        return exp;
    }
    static String parity(String exp){
        String[] sArr = exp.split(" ");
        //for all except last
        for(int i = 0; i < sArr.length - 1; i++){
            for(int j = 0; j < 8; j+=2){
                if(sArr[i].charAt(j) == '.'){
                    int char1 = Character.getNumericValue(sArr[i].charAt(j - 2));
                    int char2 = Character.getNumericValue(sArr[i].charAt(j - 4));
                    int char3 = Character.getNumericValue(sArr[i].charAt(j - 6));
                    if((char1 + char2 + char3) % 2 == 0) {
                        sArr[i] = sArr[i].replace('.', '0');
                    }else if((char1 + char2 + char3) % 2 == 1){
                        sArr[i] = sArr[i].replace('.', '1');
                    }
                }
            }
        }
        //for last
        if(sArr[sArr.length - 1].charAt(4) == '.') {
            int char1 = Character.getNumericValue(sArr[sArr.length - 1].charAt(0));
            int char2 = Character.getNumericValue(sArr[sArr.length - 1].charAt(2));
            if((char1 + char2) % 2 == 0){
                sArr[sArr.length - 1] = sArr[sArr.length - 1].replace('.', '0');
            }else{
                sArr[sArr.length - 1] = "00110011";
            }

        }else{
            int char1 = Character.getNumericValue(sArr[sArr.length - 1].charAt(0));
            int char2 = Character.getNumericValue(sArr[sArr.length - 1].charAt(2));
            int char3 = Character.getNumericValue(sArr[sArr.length - 1].charAt(4));
            if((char1 + char2 + char3) % 2 == 0) {
                sArr[sArr.length - 1] = sArr[sArr.length - 1].replace('.', '0');
            }else if((char1 + char2 + char3) % 2 == 1){
                sArr[sArr.length - 1] = sArr[sArr.length - 1].replace('.', '1');
            }
        }
        exp = String.join(" ", Arrays.asList(sArr));
        return exp;
    }
    static String bintoHex(String bin){
        String[] sArr = bin.split(" ");
        String hex = "";
        for(int i = 0; i < sArr.length; i++){
            int dec = Integer.parseInt(sArr[i], 2);
            hex += String.format("%02X ", dec);
        }
        return hex;
    }
    static void fileWrite(String filename, byte[] bytes){
        try {

            // Initialize a pointer
            // in file using OutputStream
            OutputStream
                    os
                    = new FileOutputStream(filename);

            // Starts writing the bytes in it
            os.write(bytes);
            //System.out.println("Successfully"
            //        + " byte inserted");

            // Close the file
            os.close();
        }

        catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
    static byte[] binToByte(String bin){
        String[] binArr = bin.split(" ");
        byte[] byteArr = new byte[binArr.length];
        for(int i = 0; i < binArr.length; i++){
            int b = Integer.parseInt(binArr[i], 2);
            byteArr[i] = (byte) b;
        }

        return byteArr;
    }
    static String byteToBin(byte[] byteArr){
        String[] binArr = new String[byteArr.length];
        for(int i = 0; i <byteArr.length; i++){
            int n = Integer.parseInt(String.valueOf(byteArr[i]));
            String s1 = String.format("%8s", Integer.toBinaryString(n & 0xFF)).replace(' ', '0');
            binArr[i] = s1;
        }
        return String.join(" ", Arrays.asList(binArr));
    }
    static void encode(){
        String data = textfileExtract("send.txt");
        System.out.println("send.txt:");
        System.out.println("text view: " + data);

        String sb = strtoHex(data);
        System.out.println("hex view: " + sb);

        String s = strtoBin(data);
        System.out.println("bin view: " + s);
        System.out.println();

        System.out.println("encoded.txt:");
        String exp = expand(s);
        System.out.println("expand: " + exp);

        String par = parity(exp);
        System.out.println("parity: " + par);

        String hex = bintoHex(par);
        System.out.println("hex view: " + hex);

        byte[] str = binToByte(par); //convert to byte
        fileWrite("encoded.txt", str);
    }
    static void send(){
        byte[] bata = fileExtract("encoded.txt");
        String data = byteToBin(bata);

        System.out.println("encoded.txt:");
        String hex = bintoHex(data);
        System.out.println("hex view: " + hex);
        System.out.println("bin view: " + data);
        System.out.println();

        data = bitcorruption(data);
        System.out.println("received.txt:");
        System.out.println("bin view: " + data);

        hex = bintoHex(data);
        System.out.println("hex view: " + hex);

        bata = binToByte(data);
        fileWrite("received.txt", bata);
    }
    static String correction(String data){
        String[] sArr = data.split(" ");
        String[] correctArr = new String[sArr.length];

        for(int i = 0; i < sArr.length; i++){
            for(int j = 0; j < 8; j+=2){
                //find pair with error
                char[] cArr = new char[8];
                for(int k = 0; k < 8; k++){
                    cArr[k] = sArr[i].charAt(k);
                }
                int errIdx = 0;
                boolean found = false;
                if(sArr[i].charAt(j) != sArr[i].charAt(j+1)){
                    errIdx = j;
                    found = true;
                }
                //if error inside data pairs aka 0 <= j < 6, change
                if(errIdx < 6 && found){
                    // if x = even, x = 0, x = odd, x = 1
                    int charend = Character.getNumericValue(sArr[i].charAt(6));
                    int char1, char2;
                    if(errIdx < 2) {
                        char1 = Character.getNumericValue(sArr[i].charAt(2));
                        char2 = Character.getNumericValue(sArr[i].charAt(4));
                    }else if(errIdx < 4) {
                        char1 = Character.getNumericValue(sArr[i].charAt(0));
                        char2 = Character.getNumericValue(sArr[i].charAt(4));
                    }else{
                        char1 = Character.getNumericValue(sArr[i].charAt(0));
                        char2 = Character.getNumericValue(sArr[i].charAt(2));
                    }
                    int num = char1 + char2;
                    //System.out.println(charend == 0);
                    if(charend == 0){
                        if(num % 2 == 0){
                            cArr[errIdx] = '0';
                            cArr[errIdx + 1] = '0';
                        }else{
                            cArr[errIdx] = '1';
                            cArr[errIdx + 1] = '1';
                        }
                    }else{
                        if(num % 2 == 0){
                            cArr[errIdx] = '1';
                            cArr[errIdx + 1] = '1';
                        }else{
                            cArr[errIdx] = '0';
                            cArr[errIdx + 1] = '0';
                        }
                    }
                    correctArr[i] = new String(cArr);
                }else if(errIdx >= 6 && found){ //ignore parity pair
                    correctArr[i] = sArr[i];
                }
            }
        }
        String correct = String.join(" ", Arrays.asList(correctArr));
        return correct;
    }
    static String decod(String correct){
        String[] correctArr = correct.split(" ");
        String c = String.join("", Arrays.asList(correctArr));
        String decode = "";
        int count = 0, addedcount = 0;
        for(int i = 0; i < c.length(); i+=2){
            count++;
            if(count < 4) {
                decode += c.charAt(i);
                addedcount++;
            }else{
                count = 0;
            }
            if(addedcount == 8){
                decode += " ";
                addedcount = 0;
            }
        }
        return decode;
    }
    static String remove(String decode){
        String[] arr = decode.split(" ");
        String remove = "";
        for(int i = 0; i < arr.length; i++){
            if(arr[i].length() == 8){
                remove += arr[i] + " ";
            }
        }
        return remove;
    }
    static String hextoText(String hex){
        String[] rArr = hex.split(" ");
        hex = String.join("", rArr);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hex.length(); i += 2) {
            String str = hex.substring(i, i + 2);
            sb.append((char) Integer.parseInt(str, 16));
        }
        return sb.toString();
    }
    static void decode(){
        byte[] bata = fileExtract("received.txt");
        String data = byteToBin(bata);

        System.out.println("received.txt:");
        String hex = bintoHex(data);
        System.out.println("hex view: " + hex);
        System.out.println("bin view: " + data);
        System.out.println();

        System.out.println("decoded.txt:");

        String correct = correction(data);
        System.out.println("correct: " + correct);

        String decode = decod(correct);
        System.out.println("decode: " + decode);

        String remove = remove(decode);
        System.out.println("remove: " + remove);

        hex = bintoHex(remove);
        System.out.println("hex view: " + hex);

        String text = hextoText(hex);
        System.out.println("text view: " + text);

        textfileWrite("decoded.txt", text);
    }
    static void textfileWrite(String filename, String str){
        try{
            FileWriter wFile = new FileWriter(filename);
            wFile.write(str);
            wFile.close();
        }catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        //while(true) {
            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();

            if (input.equalsIgnoreCase("encode")) {
                encode();
            }
            if (input.equalsIgnoreCase("send")) {
                send();
            }
            if (input.equalsIgnoreCase("decode")) {
                decode();
            }
            //if(input.equalsIgnoreCase("stop")){
            //    break;
            //}
        //}
    }
}
