import java.util.ArrayList;
import java.util.Random;

public class FrecciaRossa extends Treno {


    public FrecciaRossa(ArrayList<Binari> binaryList){
        super(240, 20, binaryList);
    }

    @Override
    public void enterStation() {
        super.enterStation();
        for(Wagon wagon : super.wagonList){
            wagon.openD();
        }
    }
}
