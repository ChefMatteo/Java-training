import java.util.ArrayList;

public class SoftwareRepository extends Entità {
    public ArrayList<ProdottoSoftware> listaSoftware;

    public SoftwareRepository() {
        listaSoftware = new ArrayList<>();
    }

    public int[] getListaId() {
        int[] listaId = new int[listaSoftware.size()];
        for (int i = 0; i < listaSoftware.size(); i++) {
            listaId[i] = listaSoftware.get(i).id;
        }
        return listaId;
    }
}
