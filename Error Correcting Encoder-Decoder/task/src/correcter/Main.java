package correcter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;


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
        //expand in grp of 4, 1st at pos 3, 3 at pos 5-7
        //remove the spaces
        String[] sArr = s.split(" ");
        s = String.join("", Arrays.asList(sArr));
        //System.out.println(s);

        String exp = "";
        for(int i = 0; i < s.length(); i+=4){
            exp += "..";
            exp += s.charAt(i);
            exp += ".";
            exp += s.charAt(i + 1);
            exp += s.charAt(i + 2);
            exp += s.charAt(i + 3);
            exp += ". ";
        }
        return exp;
    }
    static String parity(String exp){
        //p1 check one bit, skip one bit
        //p2 check 2 bit, skip 2
        //p4 check 4 bit, skip 4
        //1 if total num of 1 is odd
        //0 if even
        //p8 is always 0
        String[] sArr = exp.split(" ");
        String[] newArr = new String[sArr.length];

        for(int i = 0; i < sArr.length; i++){
            char[] ch = new char[8];

            //for p1 -> charAt(0)
            int numof1 = 0;

            if(sArr[i].charAt(2) == '1'){
                numof1++;
            }
            if(sArr[i].charAt(4) == '1'){
                numof1++;
            }
            if(sArr[i].charAt(6) == '1'){
                numof1++;
            }

            if(numof1 % 2 ==0){
                ch[0] = '0';
            }else{
                ch[0] = '1';
            }

            //for p2
            numof1 = 0;

            if(sArr[i].charAt(2) == '1'){
                numof1++;
            }
            if(sArr[i].charAt(5) == '1'){
                numof1++;
            }
            if(sArr[i].charAt(6) == '1'){
                numof1++;
            }

            if(numof1 % 2 ==0){
                ch[1] = '0';
            }else{
                ch[1] = '1';
            }
            //p3
            ch[2] += sArr[i].charAt(2);

            //for p4
            numof1 = 0;
            if(sArr[i].charAt(4) == '1'){
                numof1++;
            }
            if(sArr[i].charAt(5) == '1'){
                numof1++;
            }
            if(sArr[i].charAt(6) == '1'){
                numof1++;
            }
            if(numof1 % 2 ==0){
                ch[3] = '0';
            }else{
                ch[3] = '1';
            }

            //p5,6,7.. the rest
            ch[4] = sArr[i].charAt(4);
            ch[5] = sArr[i].charAt(5);
            ch[6] = sArr[i].charAt(6);
            ch[7] = '0';
            String CH = new String(ch);
            newArr[i] = CH;
        }
        exp = String.join(" ", Arrays.asList(newArr));
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
        //p1 check one bit, skip one bit
        //p2 check 2 bit, skip 2
        //p4 check 4 bit, skip 4
        //1 if total num of 1 is odd
        //0 if even
        //bad bit = when p1 or p2 doesn't add up -> p1 + p2 == bad bit pos
        //p8 is always 0
        String[] sArr = data.split(" ");
        String[] correctArr = new String[sArr.length];

        for(int i = 0; i < sArr.length; i++){
            char[] c = sArr[i].toCharArray();
            List<Integer> ch = new ArrayList<Integer>();
            //p1
            int numof1 = 0;
            if(sArr[i].charAt(2) == '1'){
                numof1++;
            }
            if(sArr[i].charAt(4) == '1'){
                numof1++;
            }
            if(sArr[i].charAt(6) == '1'){
                numof1++;
            }
            if(numof1 % 2 == 0 && sArr[i].charAt(0) == '0'){

            }else if(numof1 % 2 != 0 && sArr[i].charAt(0) == '1'){

            }else{
                ch.add(1);
            }
            numof1 = 0;
            //p2
            if(sArr[i].charAt(2) == '1'){
                numof1++;
            }
            if(sArr[i].charAt(5) == '1'){
                numof1++;
            }
            if(sArr[i].charAt(6) == '1'){
                numof1++;
            }
            if(numof1 % 2 == 0 && sArr[i].charAt(1) == '0'){

            }else if(numof1 % 2 != 0 && sArr[i].charAt(1) == '1'){

            }else{
                ch.add(2);
            }
            numof1 = 0;
            //p4
            if(sArr[i].charAt(4) == '1'){
                numof1++;
            }
            if(sArr[i].charAt(5) == '1'){
                numof1++;
            }
            if(sArr[i].charAt(6) == '1'){
                numof1++;
            }
            if(numof1 % 2 == 0 && sArr[i].charAt(3) == '0'){

            }else if(numof1 % 2 != 0 && sArr[i].charAt(3) == '1'){

            }else{
                ch.add(4);
            }
            //p8
            if(sArr[i].charAt(7) != '0'){
                c[7] = '0';
            }
            //find bad arr:
            int[] arr = ch.stream().mapToInt(n->n).toArray();
            System.out.println(Arrays.toString(arr));
            if(arr.length == 3) {
                int badbitidx = arr[0] + arr[1] + arr[2] - 1;
                if(sArr[i].charAt(badbitidx) == '0'){
                    c[badbitidx] = '1';
                }else if(sArr[i].charAt(badbitidx) == '1'){
                    c[badbitidx] = '0';
                }
            }
            if(arr.length == 2) {
                int badbitidx = arr[0] + arr[1] - 1;
                if(sArr[i].charAt(badbitidx) == '0'){
                    c[badbitidx] = '1';
                }else if(sArr[i].charAt(badbitidx) == '1'){
                    c[badbitidx] = '0';
                }
            }
            else if(arr.length == 1){
                int badbitidx = arr[0] - 1;
                if(sArr[i].charAt(badbitidx) == '0'){
                    c[badbitidx] = '1';
                }else if(sArr[i].charAt(badbitidx) == '1'){
                    c[badbitidx] = '0';
                }
            }

            String cc = new String(c);
            correctArr[i] = cc;
        }
        String correct = String.join(" ", Arrays.asList(correctArr));
        return correct;
    }
    static String decod(String correct){
        String[] correctArr = correct.split(" ");
        String[] decode = new String[correctArr.length/2];
        int count = 0;
        for(int i = 0; i < correctArr.length; i+=2){
            String s = "";
            s += correctArr[i].charAt(2);
            s += correctArr[i].charAt(4);
            s += correctArr[i].charAt(5);
            s += correctArr[i].charAt(6);
            s += correctArr[i + 1].charAt(2);
            s += correctArr[i + 1].charAt(4);
            s += correctArr[i + 1].charAt(5);
            s += correctArr[i + 1].charAt(6);
            decode[count] = s;
            count++;
        }
        String c = String.join(" ", Arrays.asList(decode));
        return c;
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

        //String remove = remove(decode);
        //System.out.println("remove: " + remove);

        hex = bintoHex(decode);
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
