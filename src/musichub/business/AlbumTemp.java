/**
 * Declaration de notre package buisness
 */
package src.musichub.business;

/**
 * Import des packages essentiel pour l'utilisation de notre class
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class AlbumTemp
 * Cette Classe permet d'effectuer toutes les opérations sur les Albums
 * Elle contient notamment une list d'Albums
 */
public class AlbumTemp{
    private List<Album> listAlbum = new ArrayList<>();

    /**
     * Accesseur de listAlbum
     * @return
     */
    public List<Album> getListAlbum(){
        return this.listAlbum;
    }

    /**
     * Methode addAlbum
     * Elle permet d'ajouter crée un album et de l'ajouter dans listAlbum
     * Elle renvoie l'album crée
     * @return
     */
    public Album addAlbum(){
        System.out.println("Ajout d'un nouvel Album ");
        try{
            Scanner scan = new Scanner(System.in);
            System.out.println("Titre : ");
            String title = scan.nextLine();
            System.out.println("ID : ");
            int id = scan.nextInt();
            scan.nextLine();
            System.out.println("Artiste : ");
            String artiste = scan.nextLine();
            System.out.println("Date : ");
            int date = scan.nextInt();
            System.out.println("Duree : ");
            int duree = scan.nextInt();

            Album album = new Album(title,id,artiste, date, duree);
            this.listAlbum.add(album);
            return album;
        }catch(Exception e){
            System.out.println("Erreur d'entrée");
        }
        return null;
    }

    /**
     * Methode choixChanson
     * Elle prend en parametres une list de Chanson et un AlbumTemp
     * Elle permet de choisir qu'elle chanson ajouter a quel album parmis une list de chanson
     * Si la liste de Chanson est vide, elle genre une execption
     * Si la liste d'album est vide, la methode appelle addAlbum
     * @param listChanson
     * @param albumTemp
     * @return
     */
    public Album choixChanson(List<Chanson> listChanson,AlbumTemp albumTemp) {
        Scanner scan = new Scanner(System.in);

        try{
            for (int i = 0; i < listChanson.size(); i++) {
                System.out.println(String.format("{%d} - {%s}", i, listChanson.get(i).getTitle()));
            }
            System.out.println("Quelle chanson voulez vous ajouter : ");
            int choixUtilisateur = scan.nextInt();
            Chanson chanson = listChanson.get(choixUtilisateur);

            if(albumTemp.getListAlbum() != null && !albumTemp.getListAlbum().isEmpty()){

                Album album = albumTemp.choixAlbum(getListAlbum());
                album.addChanson(chanson);

                System.out.println("Test ajout album");
                System.out.println(album);

                System.out.println("Test ajout chanson");
                album.afficheChanson();

                return album;
            }
            else {
                System.out.println("Vous n'avez pas d'album\n Rajouter en un :");
                Album newAlbum = albumTemp.addAlbum();
                newAlbum.addChanson(chanson);

                System.out.println("Test ajout album");
                System.out.println(newAlbum);

                System.out.println("Test ajout chanson");
                newAlbum.afficheChanson();

                return newAlbum;
            }

        }catch (Exception e){
            System.out.println("Il n'y a pas de chanson existante");
            System.out.println("Veuillez en rajouter une au préalable");
        }
        return null;
    }

    /**
     * Methode choixAlbum
     * Elle est utiliser notamment dans la methode choixChanson
     * Elle permet a l'utilisateur de choisir un Album parmis la listAlbums
     * Elle renvoie l'album choisi
     * @param listAlbum
     * @return
     */
    public Album choixAlbum(List<Album> listAlbum) {
        Scanner scan = new Scanner(System.in);

        try{
            for (int i = 0; i < listAlbum.size(); i++) {
                System.out.println(String.format("{%d} - {%s}", i, listAlbum.get(i).getTitle()));
            }
            System.out.println("Choix de l'album : ");
            int choixUtilisateur = scan.nextInt();

            Album album = listAlbum.get(choixUtilisateur);

            return album;
        }catch (Exception e){
            System.out.println("Erreur lors du choix de l'album");
        }
        return null;
    }

    /**
     * Methode afficheAlbum
     * Elle permet d'afficher les Albums contenues dans la listAlbums
     * Elle affiche également les chansons contenues dans chaque Album
     */
    public void afficheAlbum(){
        int i = 0;
        for(Album a : this.listAlbum){
            System.out.println( "(" + i + ")" +"[" + a + "]");
            for(Chanson c : a.getListChansons()){
                System.out.println("\t\t└>["+c+"]");
            }
            i++;
        } 
    }

    /**
     * Methode toString de la Classe
     * Elle permet d'afficher les elements de notre listAlbums
     * @return
     */
    public String toString(){
        String sentence = " ";
        for(Album a : this.listAlbum){
            sentence = sentence + a + "\n";
        }
        return sentence;
    }

    /**
     * Surcharge de la Methode addAlbum mais avec cette fois un Album en parametre
     * Elle permet d'ajouter un Album existant a la listAlbum
     * @param album
     */
    public void addAlbum(Album album){
        this.listAlbum.add(album);
    }

    /**
     * Methode pour trier une list d'Album par date
     * On utilise pour cela les expressions lambda
     * On return ensuite la list triée
     * @param listAlbum
     * @return
     */
    public static List<Album> SortByDate(List<Album> listAlbum){
        listAlbum.sort(
                (Album a1, Album a2) -> Integer.compare(a1.getDate(), a2.getDate())
        );
        return listAlbum;
    }

}
