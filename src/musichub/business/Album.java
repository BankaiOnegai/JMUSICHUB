/**
 * Definition du package business
 */
package src.musichub.business;

/**
 * Import des packages necessaires a l'utilisation de notre Class
 */
import java.util.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Declaration de notre classe Album
 * C'est une classe fille de MotherClass
 * Elle contient tous les attributs d'un Album
 * Et aussi une list de chanson
 */
public class Album extends MotherClass {
    private String Artiste;
    private int Date;
    private int Duree;

    private List<Chanson> listChansons = new ArrayList<>();

    /**
     * Methode pour afficher notre liste de chanson qui est defini en private
     */
    public void afficheChanson(){
        for (int i = 0; i < this.listChansons.size(); i++) {
            System.out.println(String.format("{%d} - {%s}", i, listChansons.get(i).getTitle()));
        }
    }

    /**
     * Constructeur de notre Classe Album
     * Elle permet de Set tout les attributs d'un Album
     * @param Title
     * @param ID
     * @param Artiste
     * @param Date
     * @param Duree
     */
    public Album(String Title,int ID, String Artiste, int Date, int Duree){
        super(Title, ID);
        this.Artiste = Artiste;
        this.Date = Date;
        this.Duree = Duree;
    }

    /**
     * Accesseur de listChansons
     * @return
     */
    public List<Chanson> getListChansons(){
        return this.listChansons;
    }

    /**
     * Accesseur de Artiste
     * @return
     */
    public String getArtiste(){
        return this.Artiste;
    }

    /**
     * Accesseur de Date
     * @return
     */
    public int getDate(){
        return this.Date;
    }

    /**
     * Accesseur de Duree
     * @return
     */
    public int getDuree(){
        return this.Duree;
    }

    /**
     * Methode toString
     * Elle permet d'afficher un album
     * @return
     */
    public String toString(){
        return "Titre :" + this.getTitle() + " ID :" + this.getID() + " Artiste :" + this.getArtiste()
        + " Date :" + this.getDate() + " Duree :" + this.getDuree();
    }

    /**
     * Methode addChanson qui prend en parametre une chanson
     * Cette méthode ajoute a la liste listChansons la Chanson donnée en parametres
     * @param chanson
     */
    public void addChanson(Chanson chanson){
        this.listChansons.add(chanson);
    }

    /**
     * Accesseur d'Element
     * Elle est utilisé lors de l'écriture d'un Album dans un fichier XML
     * @param document
     * Elle renvoie un Element qui contient les informations d'un Album
     * @return
     */
    public Element getElement(Document document){

        Element AlbumElement = document.createElement("Album");

        Element title = document.createElement("Title");
        title.appendChild(document.createTextNode(getTitle()));
        AlbumElement.appendChild(title);

        Element id = document.createElement("ID");
        id.appendChild(document.createTextNode(String.valueOf(getID())));
        AlbumElement.appendChild(id);

        Element artiste = document.createElement("Artiste");
        artiste.appendChild(document.createTextNode(getArtiste()));
        AlbumElement.appendChild(artiste);

        Element date = document.createElement("Date");
        date.appendChild(document.createTextNode(String.valueOf(getDate())));
        AlbumElement.appendChild(date);

        Element duree = document.createElement("Duree");
        duree.appendChild(document.createTextNode(Integer.toString(getDuree())));
        AlbumElement.appendChild(duree);

        Element listDeChanson = document.createElement("Chansons");
        for (Chanson chanson : this.listChansons){
            listDeChanson.appendChild(chanson.getElement(document));
        }
        AlbumElement.appendChild(listDeChanson);

        return AlbumElement;
    }
}
