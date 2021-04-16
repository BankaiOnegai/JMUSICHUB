/**
 * Definition du package buisness
 */
package src.musichub.business;

/**
 * Import des packages dom afin d'utiliser des documents et elements
 */
import org.w3c.dom.Document;
import org.w3c.dom.Element;
/**
 * Import de java util
 */
import java.util.*;

/**
 * Class Playlists
 * Classe fille de MotherClass
 * Elle permet de définir les attributs d'une Playlist
 */
public class Playlists extends MotherClass {
    private List<Audio> playlist = new ArrayList<>();

    /**
     * Constructeur de Playlists
     * Il permet notamment de Set tous les attributs d'une Playlists
     * @param Title
     * @param ID
     */
    public Playlists(String Title, int ID){
        super(Title, ID);
    }

    /**
     * Methode toString
     * Permet d'afficher une playlist avec ses attributs
     * @return
     */
    public String toString(){
        return "Titre " + this.getTitle() + " ID " + this.getID();
    }

    /**
     * Accesseur de playlist
     * @return
     */
    public List<Audio> getListAudio(){
        return this.playlist;
    }

    /**
     * Methode addPlayListChanson
     * Elle permet d'ajouter une chanson existante a playlist
     * @param chanson
     */
    public void addPlayListChanson(Chanson chanson){
        this.playlist.add(chanson);
    }

    /**
     * Methode addPlayListLivreAudio
     * Elle permet d'ajouter un LivreAudio existant a playlist
     * @param livreaudio
     */
    public void addPlayListLivreAudio(LivreAudio livreaudio) {
        this.playlist.add(livreaudio);
    }

    /**
     * Methode qui permet d'afficher les element de playlist
     */
    public void affichePlaylist(){
        for (int i = 0; i < this.playlist.size(); i++) {
            System.out.println(String.format("{%d} - {%s}", i, playlist.get(i).getTitle()));
        }
    }

    /**
     * Accesseur d'Element
     * Elle est utilisé lors de l'écriture d'une Playlist dans un fichier XML
     * @param document
     * Elle renvoie un Element qui contient les informations d'une PlayList
     * @return
     */
    public Element getElement(Document document){

        Element AlbumElement = document.createElement("Playlist");

        Element title = document.createElement("Title");
        title.appendChild(document.createTextNode(getTitle()));
        AlbumElement.appendChild(title);

        Element id = document.createElement("ID");
        id.appendChild(document.createTextNode(String.valueOf(getID())));
        AlbumElement.appendChild(id);

        Element listElement = document.createElement("Elements");
        for (Audio audio : this.playlist){
            listElement.appendChild(audio.getElement(document));
        }
        AlbumElement.appendChild(listElement);

        return AlbumElement;
    }
}
