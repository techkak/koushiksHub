import java.util.Scanner;

public class findMaxSumCombination {

    public static void findCombination(java.lang.String input){
        char[] inputString=input.toCharArray();
        StringBuilder result=new StringBuilder();
        for(int i=0;i<input.length();i++) {
               result.append(inputString[i]);
               if(inputString[i]=='-'){
                   i++;
                   result.append(inputString[i]);
                   i++;
                   while(i<inputString.length && inputString[i]=='0'){
                       result.append("+0");
                       i++;
                   }
                   if(i<inputString.length && inputString[i]>='1' && inputString[i]<='9'){
                       result.append("+");
                   }
                   i--;
               }
           }
           System.out.println(result);
        }

    public static void main(java.lang.String[]args){

        System.out.println("Please enter a combination:");
        Scanner in=new Scanner(System.in);
        java.lang.String s=in.nextLine();
        findCombination(s);
    }
}
