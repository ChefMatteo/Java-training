import java.util.ArrayList;

public class Magazzino extends Entità {
    private ArrayList<ProdottoFisico> listaPacchi;
    int capienza;

    public Magazzino(int capienza) {
        listaPacchi = new ArrayList<>();
        this.capienza = capienza;
    }

    public int sizeListaPacchi() {
        return listaPacchi.size();
    }

    public ArrayList<ProdottoFisico> getListaPacchi() {
        return listaPacchi;
    }

    public boolean addPack(ProdottoFisico pacco) {
        if (listaPacchi.size() < capienza) {
            listaPacchi.add(pacco);
            return true;
        } else {
            System.out.println("Capienza massima del magazzino raggiunta");
            return false;
        }
    }

}
