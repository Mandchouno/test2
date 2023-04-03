public class Quete {
    int categorie;
    double coutPointsVie;
    double recompenseArgent;
    int recompenseArmures;

    public Quete(int categorie, double coutPointsVie, double recompenseArgent, int recompenseArmures) {
        this.categorie = categorie;
        this.coutPointsVie = coutPointsVie;
        this.recompenseArgent = recompenseArgent;
        this.recompenseArmures = recompenseArmures;
    }

    public int getCategorie() {
        return categorie;
    }

    public double getCoutPointsVie() {
        return coutPointsVie;
    }

    public double getRecompenseArgent() {
        return recompenseArgent;
    }

    public int getRecompenseArmures() {
        return recompenseArmures;
    }
}