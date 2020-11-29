import java.util.Random;

public class Main {

    public static int[] generationArray(){
        Random r = new Random();
        int[] array = new int[10000000];
        for (int i = 0; i < array.length; i++) {
            boolean flag = r.nextBoolean();
            if(!flag)
                array[i] = r.nextInt(10)*-1;
            else
                array[i] = r.nextInt(10);
        }
        return array;
    }

    public static void main(String[] args) {
        int[] array = generationArray();
        int sum1 = 0;
        int sum2 = 0;
        int sum = 0;
        SumThread sumThread1 = new SumThread(0, array.length / 2, array);
        SumThread sumThread2 = new SumThread(array.length / 2, array.length / 2, array);
        sumThread1.start();
        sumThread2.start();
        try {
            sumThread1.join();
            sumThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            sum1 = sumThread1.sum;
            sum2 = sumThread2.sum;
            sum = sum1 + sum2;
            System.out.println(sum);
        }
    }
}


