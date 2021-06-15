package correcter;

import java.util.Scanner;

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
    static String corr
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        System.out.println(input);

        String errormsg = tripleCh(input);
        System.out.println(errormsg);

        errormsg = tripleCharErrorEmulator(errormsg);
        System.out.println(errormsg);

        System.out.println(input);
    }
}
