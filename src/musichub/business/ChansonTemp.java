/**
 * Definition du package buisness
 */
package src.musichub.business;

/**
 * Import des packages necessaires a notre Class
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class ChansonTemp
 * Cette Classe permet d'effectuer toutes les opérations sur des Chansons
 * Elle contient notamment une list de Chansons
 */
public class ChansonTemp {
    private List<Chanson> listChanson = new ArrayList<>();

    /**
     * Accesseur de listChanson
     * @return
     */
    public List<Chanson> getListChanson(){
        return this.listChanson;
    }

    /**
     * Methode qui permet d'afficher les Chansons contenue dans listChanson
     */
    public void afficheChanson(){
        int i = 0;
        for(Chanson c : this.listChanson){
            System.out.println( "(" + i + ")" +"[" + c + "]");
            i++;
        }
    }

    /**
     * Methode addChanson qui permet de crée une nouvelle chanson
     * Elle genere une exeception si l'utilisateur fait une erreur de saisie d'un attribue
     * @throws Exception
     */
    public void addChanson() throws Exception{
        System.out.println("Ajout d'une nouvelle chanson ");
        try {
            Scanner scan = new Scanner(System.in);
            System.out.println("Titre : ");
            String title = scan.nextLine();
            System.out.println("ID : ");
            int id = scan.nextInt();
            scan.nextLine();
            System.out.println("Artiste : ");
            String artiste = scan.nextLine();
            System.out.println("Contenu : ");
            String contenu = scan.nextLine();
            System.out.println("Genre : ");
            String retour = scan.nextLine();
            Genres genre = Genres.valueOf(retour);
            System.out.println("Duree : ");
            int duree = scan.nextInt();

            Chanson chanson = new Chanson(title, id, artiste, contenu, genre, duree);
            this.listChanson.add(chanson);
        }catch (Exception e){
            System.out.println("Erreur d'entrée");
        }
    }

    /**
     * Methode pour ajouter une chanson donnée en parametre a notre listChanson
     * C'est une surcharge de la methode addChanson
     * @param chanson
     */
    public void addChanson(Chanson chanson){
        this.listChanson.add(chanson);
    }

    /**
     * Methode qui permet de trié une list de chanson par genre
     * On utilise notamment les expressions lambda pour ce faire
     * @param listChanson
     * @return
     */
    public static List<Chanson> SortByGenre(List<Chanson> listChanson){
        listChanson.sort(
                (Chanson a1, Chanson a2) -> a1.getGenre().toString().compareTo(a2.getGenre().toString())
        );
        return listChanson;
    }
}
