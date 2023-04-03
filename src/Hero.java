public class Hero {
    protected String nom;
    protected int categorie;
    protected double coutAchat;
    protected int coutArmure;
    protected double pointVie;
    public Hero(String nom, int categorie, double coutAchat, int coutArmure,double pointVie){
        this.coutAchat = coutAchat;
        this.nom = nom;
        this.pointVie = pointVie;
        this.coutArmure = coutArmure;
        this.categorie = categorie;
    }
    public double getCoutArgent() {
        return coutAchat;
    }

    public double getPointVie() {
        return pointVie;
    }

    public int getCoutArmure() {
        return coutArmure;
    }

    public String getNom() {
        return nom;
    }

    public int getCategorie() {
        return categorie;
    }
}
