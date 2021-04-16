/**
 * Definition du package business
 */
package src.musichub.business;

/**
 * Import des packages java util
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class PlayListsTemp
 * Cette Classe permet d'effectuer toutes les opérations sur des PlayLists
 * Elle contient notamment une list de PlayLists
 */
public class PlaylistsTemp {
    private List<Playlists> listPlaylist = new ArrayList<>();

    /**
     * Accesseur de listPlaylist
     * @return
     */
    public List<Playlists> getListPlaylist(){
        return this.listPlaylist;
    }

    /**
     * Methode addPlaylist
     * Elle permet d'ajouter une playlist dans listPlaylist
     * @param playlist
     */
    public void addPlaylist(Playlists playlist){
        this.listPlaylist.add(playlist);
    }

    /**
     * Methode pour lire les element contenue dans listPlaylist
     * Elle permet egalement d'afficher les LivreAudios et les Chansons contenue dans une Playlist
     */
    public void afficheListPlaylist(){
        int i = 0;
        for(Playlists p : listPlaylist){
            System.out.println( "(" + i + "°)" +"[" + p + "]");
            for(Audio a : p.getListAudio()){
                System.out.println("\t\t└>["+a+"]");
            }
            i++;
        }
    }

    /**
     * Methode toString
     * Elle permet d'afficher les elements listPlaylist
     * Elle stocke le resultat dans un String
     * @return On return tout l'affichage
     */
    public String toString(){
        String sentence = "";
        for(Playlists p : this.listPlaylist){
            sentence = sentence + p + "\n";
        }
        return sentence;
    }

    /**
     * Methode deletePlaylist
     * Elle permet de supprimer une playlist choisie par l'utilisateur dans listPlaylist
     */
    public void deletePlaylist(){
        Scanner scan = new Scanner(System.in);

        for (int i = 0; i < this.listPlaylist.size(); i++) {
            System.out.println(String.format("{%d} - {%s}", i, listPlaylist.get(i).getTitle()));
        }
        System.out.println("Numero de la playlist a supprimer");

        int choixUtilisateur = scan.nextInt();

        listPlaylist.remove(choixUtilisateur);
    }

    /**
     * Methode addChansonLivreAudio
     * Elle permet d'ajouter a une Playlist une Chanson ou un LivreAudio
     * Elle permet a l'utilisateur de choisir par rapport a une list de Chanson et une list de LivreAudio
     * une Chanson ou un LivreAudio
     * @param chansonTemp
     * @param livreAudioTemp
     * @return Elle return la Playlist créer
     */
    public Playlists addChansonLivreAudio(ChansonTemp chansonTemp, LivreAudioTemp livreAudioTemp){

        Scanner scan = new Scanner(System.in);
        PlaylistsTemp playlistsTemp = new PlaylistsTemp();
        List<Chanson> listChanson = chansonTemp.getListChanson();
        List<LivreAudio> listLivreAudio = livreAudioTemp.getListLivreAudio();

        System.out.println("1: Chanson 2: LivreAudio");
        int choixUtilisateur = scan.nextInt();
        int choix;
        try{

            if(choixUtilisateur == 1) {
                for (int i = 0; i < listChanson.size(); i++) {
                    System.out.println(String.format("{%d} - {%s}", i, listChanson.get(i).getTitle()));
                }
                System.out.println("Quelle chanson voulez vous ajouter : ");

                choix = scan.nextInt();

                try {
                    Chanson chanson = listChanson.get(choix);
                    Playlists p = playlistsTemp.addPlaylist();
                    p.addPlayListChanson(chanson);
                    this.listPlaylist.add(p);
                    return p;
                } catch (Exception e1) {
                    System.out.println("Il n'y a pas cette chanson d'existante");
                }
            }

            else if(choixUtilisateur == 2){
                for (int i = 0; i < listLivreAudio.size(); i++) {
                    System.out.println(String.format("{%d} - {%s}", i, listLivreAudio.get(i).getTitle()));
                }
                System.out.println("Quelle LivreAudio voulez vous ajouter : ");

                choix = scan.nextInt();

                try{
                    LivreAudio livreAudio = listLivreAudio.get(choix);

                    Playlists p2 = playlistsTemp.addPlaylist();
                    p2.addPlayListLivreAudio(livreAudio);
                    this.listPlaylist.add(p2);
                    return p2;
                }catch (Exception e2){
                    System.out.println("Il n'y a pas ce livreAudio d'existant");
                }
            }

        }catch (Exception e){

            System.out.println("Veuillez en rajouter une au préalable");
        }
        return null;
    }

    /**
     * Methode addPlaylist
     * @return Elle permet de crée une Playlist et de la renvoyer
     */
    public Playlists addPlaylist() {
        System.out.println("--------Rajout Playlist--------");
        Scanner scan = new Scanner(System.in);
        try {
            System.out.println("Titre : ");
            String title = scan.nextLine();
            System.out.println("ID : ");
            int id = scan.nextInt();
            Playlists p = new Playlists(title, id);

            return p;
        }catch (Exception e){
            System.out.println("Erreur de création de playlist");
        }
        return null;
    }
}