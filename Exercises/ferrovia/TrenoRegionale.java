import java.util.ArrayList;

public class TrenoRegionale extends Treno{
    int maxWagons = 15;

    public TrenoRegionale(ArrayList<Binari> binaryList){
        super(140,15, binaryList);
    }
}
