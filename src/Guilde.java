import java.util.ArrayList;
import java.util.List;

public class Guilde {
    protected double montant;
    protected int nbArmures;
    protected List<Hero> heros;

    public Guilde(double montant, int nbArmures) {
        this.montant = montant;
        this.nbArmures = nbArmures;
        this.heros = new ArrayList<>();

    }

    public double getMontant() {return montant;}

    public int getNbArmures() {return nbArmures;}

    public List<Hero> getHeros() {return heros;}

    public void ajouterHero(Hero hero){this.heros.add(hero);}

    public void ajouterArmure(int nbArms) {this.nbArmures+= nbArms;}

    public Hero choisir(int categorie){
        return heros.stream()
                .filter(hero -> hero.getCategorie() == categorie)
                .findFirst()
                .orElse(null);
    }

    public Hero chercherHero(String nom){

        for (Hero hero : heros) {
            if (hero.getNom().equals(nom)) {
                return hero;
            }
        }
        return null;
    }

    public ArrayList buyHero(Hero hero, ArrayList<String> erreur) {
        if (this.montant < hero.getCoutArgent() || this.nbArmures < hero.getCoutArmure()) {
            String txt = "-Il vous manque de l'argent et/ou des armures pour acheter " + hero.getNom();
            erreur.add(txt);
            return erreur;
        }
        this.montant -= hero.getCoutArgent();
        this.nbArmures -= hero.getCoutArmure();
        this.ajouterHero(hero);
        return erreur;
    }

    public ArrayList acheterArmure(int nbArms, int prix, ArrayList<String> erreur) {
        if (this.montant < nbArms * prix) {
            String txt = "-Il vous manque de l'argent pour acheter les armures";
            erreur.add(txt);
            return erreur;
        }
        this.montant -= nbArms * prix;
        this.ajouterArmure(nbArms);
        return erreur;
    }

    public ArrayList faireQuete(Quete quete, ArrayList<String> erreur) {
        if (heros.isEmpty()) {
            String txt = "Il n'y a pas des heros pour faire la quete";
            erreur.add(txt);
            return erreur;
        }
        Hero hero = null ;
        int queteNiveau = quete.getCategorie();
        while (queteNiveau <=4 && hero == null){
            hero = choisir(queteNiveau);
            queteNiveau++;
        }
        while (0 <= queteNiveau  && hero == null){
            hero = choisir(queteNiveau);
            queteNiveau--;
        }
        if (hero.getPointVie() < (quete.getCoutPointsVie() - (queteNiveau - quete.getCategorie()))){
            String s = String.format("Votre héros %s manque de points de vie pour faire la quete", hero.getNom());
            erreur.add(s + "\n");
            heros.remove(hero);
            return erreur;
        }
        int niveau = hero.getCategorie();
        hero.pointVie -= quete.getCoutPointsVie() - (niveau - quete.getCategorie());
        this.montant += quete.getRecompenseArgent();
        this.nbArmures += quete.getRecompenseArmures();
        return erreur;
    }
    public ArrayList entrainerHero(String nom, ArrayList<String> erreur) {
        Hero hero = chercherHero(nom);
        if (hero == null) {
            String txt = String.format("-Le héros au nom de %s n'apparêt pas dans la liste", nom);
            erreur.add(txt + "\n");
            return erreur;
        }
        int niveau = hero.categorie;
        if (this.montant >= 20 * Math.log(niveau + 10) && this.nbArmures >= Math.ceil(Math.log(niveau + 10))){
            heros.remove(hero);
            if (hero instanceof  Hero0) {
                Hero1 heroo = new Hero1(hero.getNom(), 1,hero.getCoutArgent(), hero.getCoutArmure(), hero.getPointVie() * 1.5);
                heros.add(heroo);
            } else if (hero instanceof  Hero1) {
                Hero2 heroo = new Hero2(hero.getNom(), 2,hero.getCoutArgent(), hero.getCoutArmure(), hero.getPointVie() * 1.5);
                heros.add(heroo);
            } else if (hero instanceof  Hero2) {
                Hero3 heroo = new Hero3(hero.getNom(), 3 ,hero.getCoutArgent(), hero.getCoutArmure(), hero.getPointVie() * 1.5);
                heros.add(heroo);
            } else if (hero instanceof  Hero3) {
                Hero4 heroo = new Hero4(hero.getNom(), 4,hero.getCoutArgent(), hero.getCoutArmure(), hero.getPointVie() * 1.5);
                heros.add(heroo);
            } else {
                String s = String.format("votre héros %s atteint le niveau maximal","nom");
                erreur.add(s);
                return erreur;
            }
            this.montant -= 20 * Math.log(niveau + 10);
            this.nbArmures -= Math.ceil(Math.log(niveau + 10));
            return erreur;
        }
        else {
            String txt = String.format("-Il vous manque de l'argent et/ou des armures pour améliorer %s.", nom);
            erreur.add(txt);
            return erreur;
        }
    }

}