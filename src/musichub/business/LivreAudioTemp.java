/**
 * Definition du package buisness
 */
package src.musichub.business;

/**
 * Import des packages util
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class LivreAudioTemp
 * Cette Classe permet d'effectuer toutes les opérations sur des LivreAudios
 * Elle contient notamment une list de LivreAudio
 */
public class LivreAudioTemp{

    private List<LivreAudio> listAudioBook = new ArrayList<>();

    /**
     * Accesseur de listAudioBook
     * @return
     */
    public List<LivreAudio> getListLivreAudio(){
        return this.listAudioBook;
    }

    /**
     * Methode toString de notre Class
     * Elle permet d'afficher les elements contenue dans listAudioBook
     * @return
     */
    public String toString(){
        String sentence = "";
        for(LivreAudio p : this.listAudioBook){
            sentence = sentence + p + "\n";
        }
        return sentence;
    }

    /**
     * Methode addLivreAudio
     * Elle permet de crée et d'ajouter une LivreAudio dans listAudioBook
     * Une exception est crée si l'utilisateur fait une faute lors de la saisie des attribues
     * @throws Exception
     */
    public void addLivreAudio() throws Exception{
        System.out.println("Ajout d'un nouveau livre");

        try {
            Scanner sc = new Scanner(System.in);

            System.out.println("Titre : ");
            String title = sc.nextLine();
            System.out.println("ID : ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.println("Auteur : ");
            String auteur = sc.nextLine();
            System.out.println("Contenu : ");
            String contenu = sc.nextLine();
            System.out.println("Langue : ");
            String retour = sc.nextLine();
            Langues langue = Langues.valueOf(retour);
            System.out.println("Categorie : ");
            String retour2 = sc.nextLine();
            Categories categorie = Categories.valueOf(retour2);
            System.out.println("Duree : ");
            int duree = sc.nextInt();

            LivreAudio livreaudio = new LivreAudio(title,id, auteur,contenu, langue, categorie, duree);
            this.listAudioBook.add(livreaudio);

        }catch (Exception e){
            System.out.println("Erreur de saisie");
        }
    }

    /**
     * Methode addLivreAudio
     * Elle permet d'ajouter un LivreAudio donnée en parametre un et l'ajoute a listAudioBook
     * C'est une surcharge de la methode addLivreAudio
     * @param livreAudio
     */
    public void addLivreAudio(LivreAudio livreAudio){
        this.listAudioBook.add(livreAudio);
    }

    public void afficheLivreAudio(){
        int i = 0;
        for(LivreAudio l : listAudioBook){
            System.out.println( "(" + i + ")" +"[" + l + "]");
            i++;
        }
    }

    public static List<LivreAudio> SortByAutor(List<LivreAudio> listAudio){
        listAudio.sort(
                (LivreAudio a1, LivreAudio a2) -> a1.getAuteur().compareTo(a2.getAuteur())
        );
        return listAudio;
    }
}
