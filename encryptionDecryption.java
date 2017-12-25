public class encryptionDecryption {

    public static float[][] findEncryptedKey(float [][]A,float [][] encryptionKey){
         float [][] encryptedKey=new float[encryptionKey.length][3];
         for(int k=0;k<encryptionKey.length;k++) {
             for (int i = 0; i < 3; i++) {
                 for (int j = 0; j < 3; j++) {
                     encryptedKey[k][i] += A[i][j] * encryptionKey[k][j];
                 }
             }
         }
        return encryptedKey;
    }
    public static float[][] inverseA(float [][]A){
        //find determinant
        float detArr[]=new float[4];
        float det=0;
        boolean visitedDet[][]=new boolean[A.length][A.length];
        boolean visitedCoFactor[][]=new boolean[A.length][A.length];
        float coFactorArr[]=new float[4];
        for(int i=0;i<A.length;i++){
            int incA=-1;
            for (int j=0;j<A.length;j++){
                for(int k=0;k<A.length;k++){
                    if(j!=0 && k!=i && visitedDet[j][k]==false){
                        detArr[++incA]=A[j][k];
                        visitedDet[j][k]=true;
                    }
                }
            }
            for(int l=0;l<A.length;l++){
                for(int m=0;m<A.length;m++){
                   visitedDet[l][m]=false;
                }
            }
           if(i==1){
                det-=A[0][i]*(detArr[incA]*detArr[incA-incA] -detArr[incA-2]*detArr[incA-1]);
           }
           else{
               det+=A[0][i]*(detArr[incA]*detArr[incA-incA] -detArr[incA-2]*detArr[incA-1]);
           }
        }
        float [][]inverseA=new float[A.length][A.length];
       //find co-factor
        for (int i=0;i<A.length;i++){
            for(int j=0;j<A.length;j++){
                int incC=-1;
                for (int k=0;k<A.length;k++){
                    for(int l=0;l<A.length;l++){
                        if(i!=k && j!=l && visitedCoFactor[k][l]==false){
                            coFactorArr[++incC]=A[k][l];
                            visitedCoFactor[k][l]=true;
                        }
                    }
                }
                for(int m=0;m<A.length;m++){
                    for(int n=0;n<A.length;n++){
                        visitedCoFactor[m][n]=false;
                    }
                }
                if((i+j)%2==0) {
                    inverseA[i][j] = coFactorArr[incC] * coFactorArr[incC - incC] - coFactorArr[incC - 2] * coFactorArr[incC - 1];
                }
                else{
                    inverseA[i][j] = -(coFactorArr[incC] * coFactorArr[incC - incC] - coFactorArr[incC - 2] * coFactorArr[incC - 1]);
                }
            }
        }
        //divide by determinant
        for(int i=0;i<A.length;i++){
            for(int j=0;j<A.length;j++){
                inverseA[i][j]*=1/det;
            }
        }
        //transpose your matrix
        boolean [][]visited=new boolean[A.length][A.length];

        for(int i=0;i<A.length;i++){
            for(int j=0;j<A.length;j++){
                if(i!=j &&(visited[i][j]==false || visited[j][i]==false)){
                   float temp=inverseA[i][j];
                   inverseA[i][j]=inverseA[j][i];
                   inverseA[j][i]=temp;
                   visited[i][j]=true;
                   visited[j][i]=true;
                }
            }
        }
        return inverseA;
    }
    public static float[][] findDecryptedKey(float [][]InverseMatrixA,float [][]encryptionKey ,float [][]encryptedKey){
        float[][] decryptedKey=new float[encryptionKey.length][3];
        for(int k=0;k<encryptionKey.length;k++) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    decryptedKey[k][i] += InverseMatrixA[i][j] * encryptedKey[k][j];
                }
            }
        }
        return decryptedKey;
    }
    public static  void printDecryptedValue(float [][]decryptedKey){
        System.out.println("\nDecrypted Key");
        for(int i=0;i<decryptedKey.length;i++){
            for (int j=0;j<3;j++){
                System.out.printf(" %.0f ",decryptedKey[i][j]);
            }
        }
    }
    public static  void printEncryptedValue(float [][]encryptedKey){
        System.out.println("\nEncrypted Key");
        for(int i=0;i<encryptedKey.length;i++){
            for (int j=0;j<3;j++){
                System.out.printf(" %.0f ",encryptedKey[i][j]);
            }
        }
    }
    public static  void printEncryptionKey(float [][]encryptionKey){
        System.out.println("Encryption Key");
        for(int i=0;i<encryptionKey.length;i++){
            for (int j=0;j<3;j++){
                System.out.printf(" %.0f ",encryptionKey[i][j]);
            }
        }
    }
    public static  void printMessage(float [][]decryptedKey){
        System.out.println("\nMessage");
        for(int i=0;i<decryptedKey.length;i++){
            for(int j=0;j<3;j++){
                System.out.print((char)(decryptedKey[i][j]));
            }
        }
    }
    public static void main(String[] args){
        //declare a matrix 3*3
        float A[][]={{3,2,4},
                    {4,8,6},
                    {7,8,9}};
        //encryption key array
        float encryptionKey[][]={{77,69,69},
                               {84,32,77},
                               {69,32,84},
                               {79,77,79},
                               {82,82,79},
                               {87,32,32}};
       float [][] encryptedKey=findEncryptedKey(A,encryptionKey);
       // find inverse matrix of A matrix
       float [][] InverseMatrixA=inverseA(A);
       //find decrypted key
        float [][] decryptedKey=findDecryptedKey(InverseMatrixA,encryptionKey,encryptedKey);
        //print encryption Key
        printEncryptionKey(encryptionKey);
        //print encrypted key
        printEncryptedValue(encryptedKey);
        //print decrypted key
        printDecryptedValue(decryptedKey);
        //print message
        printMessage(decryptedKey);

    }
}
