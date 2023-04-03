import java.util.ArrayList;
public class Main {
    /**
     * Args: array with
     * <ol>
     *     <li>guild:&lt;montant initial&gt;,&lt;armures initiales&gt;</li>
     * </ol>
     *
     * @param args
     */
    public static void main(String[] args) {
        GuildCommandSystem guildCommandSystem = new GuildCommandSystem(args);

        Guilde maGuilde = makeGuilde(guildCommandSystem.actualCommand());
        ArrayList<String> erreur = new ArrayList<>();

        while (guildCommandSystem.hasNextCommand()) {
            GuildCommand command = guildCommandSystem.nextCommand();
            switch (command.getName()) {
                case "buy-hero" -> {
                    String nom = command.nextString();
                    int categorie = command.nextInt();
                    double coutArgent = command.nextDouble();
                    int coutArmure = command.nextInt();
                    double PointsVie = command.nextDouble();

                    if (categorie == 0){
                        Hero0 hero = new Hero0(nom, categorie, coutArgent, coutArmure, PointsVie);
                        erreur = maGuilde.buyHero(hero, erreur);
                    }
                    if (categorie == 1){
                        Hero1 hero = new Hero1(nom, categorie, coutArgent, coutArmure, PointsVie);
                        erreur = maGuilde.buyHero(hero, erreur);
                    }
                    if (categorie == 2){
                        Hero2 hero = new Hero2(nom, categorie, coutArgent, coutArmure, PointsVie);
                        erreur = maGuilde.buyHero(hero, erreur);
                    }
                    if (categorie == 3){
                        Hero3 hero = new Hero3(nom, categorie, coutArgent, coutArmure, PointsVie);
                        erreur = maGuilde.buyHero(hero, erreur);
                    }
                    if (categorie == 4) {
                        Hero4 hero = new Hero4(nom, categorie, coutArgent, coutArmure, PointsVie);
                        erreur = maGuilde.buyHero(hero, erreur);
                    }
                }
                case "buy-armor" ->{
                    int nbArmuresAch = command.nextInt();
                    int prixArmure = command.nextInt();
                    erreur = maGuilde.acheterArmure(nbArmuresAch, prixArmure, erreur);
                }
                case "do-quest" -> {
                    int categorie = command.nextInt();
                    double coutPointsVie = command.nextDouble();
                    int recompenseArgent = command.nextInt();
                    int recompenseArmures = command.nextInt();
                    Quete quete = new Quete( categorie , coutPointsVie,recompenseArgent, recompenseArmures);
                    erreur = maGuilde.faireQuete(quete, erreur);
                }
                case "train-hero" -> {
                    String nom = command.nextString();
                    erreur = maGuilde.entrainerHero(nom , erreur);
                }
            }
        }//end while
        System.out.printf("Guild Bank account: %.1f gold & %d armours\n", maGuilde.getMontant(), maGuilde.getNbArmures());
        System.out.println("Heros:");
        for (Hero hero: maGuilde.getHeros()){
            System.out.printf("-%s: level=%d, HP=%.1f\n",hero.getNom(), hero.getCategorie(), hero.getPointVie());
        }
        if (erreur.size() != 0){
            System.out.println("-Erreur:");
            System.out.println(String.join("\n", erreur));
        }
    }

    private static Guilde makeGuilde(GuildCommand command) {
        double montantInitial = command.nextDouble();
        int nbArmures = command.nextInt();
        return new Guilde(montantInitial, nbArmures);
    }
}
