public class SumThread extends Thread {
    int start;
    int finish;
    int sum = 0;
    int[] array;

    public SumThread(int start, int finish, int[] array){
        this.start = start;
        this.finish = finish;
        this.array = array;
    }

    @Override
    public void start() {
        for (int i = start; i < finish; i++) {
            sum += array[i];
        }

    }

}

