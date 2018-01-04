import java.util.Scanner;

public class findSubset {
    public static void subset(int []arr){
        int[] queryArr=new int[arr.length];

        for(int i=1;i<=Math.pow(2,arr.length);i++){
            StringBuilder s=new StringBuilder(Integer.toBinaryString(i));
            s=s.reverse();
           for(int j=0;j<arr.length;j++){
               try{
               if(s.charAt(j)!='0'){
                   queryArr[j]=arr[j];
               }
               }catch (Exception e){
               //got the exception
               }
           }
           System.out.print("{");
           for(int k=0;k<arr.length;k++){
               if(queryArr[k]!=0){
                   System.out.print(" "+queryArr[k]+" ");
               }

           }
            System.out.print("}");

           for(int l=0;l<arr.length;l++){
               queryArr[l]=0;
            }
        }

    }
    public static void main(String[] args){
        int n;
        Scanner in=new Scanner(System.in);
        System.out.println("Please enter your array size:");
        n=in.nextInt();
        int []arr=new int[n];
        System.out.println("Please enter your array item:");
        for(int i=0;i<n;i++){
            System.out.print("arr["+i+"]: ");
            arr[i]=in.nextInt();
        }
        subset(arr);
    }
}
