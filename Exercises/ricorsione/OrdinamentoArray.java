import java.util.Arrays;

public class OrdinamentoArray {
    public static void main(String[] args) {
        int[] arrayInt = {25,2,3,45,10, 22, 100};

        System.out.println(Arrays.toString(mergeSort(arrayInt, 0, arrayInt.length-1)));
    }

    public static int[] mergeSort(int[] arrayInt, int left, int right){
        int[] arrayOrdinato = new int[arrayInt.length];

        if(right == left){
            arrayOrdinato = new int[1];
            arrayOrdinato[0] = arrayInt[left];
            return arrayOrdinato;
        }

        //if(right > 1) {

            //First Step
            int middle = (left + right)/2;

            //Second step
            int[] leftHalf = mergeSort(arrayInt, left, middle);

            //third step
            int[] rightHalf = mergeSort(arrayInt, middle+1, right);

            //Fourth step
            arrayOrdinato = merge(leftHalf, rightHalf);
       // }
        return arrayOrdinato;
    }

    public static int[] merge(int[] leftHalf, int[] rightHalf){
        int[] unArrayOrd = new int[leftHalf.length + rightHalf.length];
        //int i = 0;
        int indiceArray = 0;
        int minimo = Math.min(leftHalf.length, rightHalf.length);
        int indexRight = 0;
        int indexLeft=0;
        int count = 0;

        for(int i = 0; i < minimo*2; i++ ){
            if(indexLeft != leftHalf.length && indexRight != rightHalf.length){
                if(leftHalf[indexLeft] > rightHalf[indexRight]){
                    unArrayOrd[i]= rightHalf[indexRight];
                    indexRight++;
                    count++;

                }else{
                    unArrayOrd[i]= leftHalf[indexLeft];
                    indexLeft++;
                    count++;
                }
            }

        }

        if(indexLeft != leftHalf.length){

            for(int i =  indexLeft; i< leftHalf.length; i++){
                unArrayOrd[count] = leftHalf[i];
            }
        }
        if(indexRight != rightHalf.length){

            for(int i = indexRight; i< rightHalf.length; i++){
                unArrayOrd[count]= rightHalf[i];
            }
        }
        return unArrayOrd;
    }
}
