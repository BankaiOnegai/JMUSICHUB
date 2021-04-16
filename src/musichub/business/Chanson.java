/**
 * Definition de notre package buisness
 */
package src.musichub.business;

import org.w3c.dom.*;//importe les packages dom

/**
 * Class Chanson qui est une classe fille de MotherClass
 * Elle implemente l'interface Audio
 * Elle permet de definir les attributs d'une Chansons
 */
public class Chanson extends MotherClass implements Audio {
    
    private String Artiste;
    private String Contenu;
    private Genres Genre;
    private int Duree;

    /**
     * Constructeur de notre classe qui permet de Set tous les attributs d'une chanson
     * @param Title
     * @param ID
     * @param Artiste
     * @param Contenu
     * @param Genre
     * @param Duree
     */
    public Chanson(String Title, int ID,String Artiste, String Contenu, Genres Genre, int Duree){
        super(Title, ID);
        this.Artiste = Artiste;
        this.Contenu = Contenu;
        this.Genre = Genre;
        this.Duree = Duree;
    }

    /**
     * Accesseur d'artiste
     * @return
     */
    public String getArtiste(){
        return this.Artiste;
    }

    /**
     * Accesseur de Contenu
     * @return
     */
    public String getContenu(){
        return this.Contenu;
    }

    /**
     * Accesseur de Genre
     * @return
     */
    public Genres getGenre(){
        return this.Genre;
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
     * Elle permet d'afficher une chanson avec tous ses attributs
     * @return
     */
    public String toString(){
        return "Titre   " + this.getTitle() + " ID " + this.getID() + " Artiste " + this.getArtiste() + " Contenu " 
            + this.getContenu() + " Genre " + this.getGenre() + " Duree " + this.getDuree();
    }

    /**
     * Accesseur d'Element
     * Elle est utilisé lors de l'écriture d'une Chanson dans un fichier XML
     * @param document
     * Elle renvoie un Element qui contient les informations d'une Chanson
     * @return
     */
    public Element getElement(Document document){

        Element ChansonElement = document.createElement("Chanson");

        Element title = document.createElement("Title");
        title.appendChild(document.createTextNode(getTitle()));
        ChansonElement.appendChild(title);

        Element id = document.createElement("ID");
        id.appendChild(document.createTextNode(String.valueOf(getID())));
        ChansonElement.appendChild(id);

        Element artiste = document.createElement("Artiste");
        artiste.appendChild(document.createTextNode(getArtiste()));
        ChansonElement.appendChild(artiste);

        Element contenu = document.createElement("Contenu");
        contenu.appendChild(document.createTextNode(getContenu()));
        ChansonElement.appendChild(contenu);

        Element genre = document.createElement("Genre");
        genre.appendChild(document.createTextNode(String.valueOf(getGenre())));
        ChansonElement.appendChild(genre);

        Element duree = document.createElement("Duree");
        duree.appendChild(document.createTextNode(Integer.toString(getDuree())));
        ChansonElement.appendChild(duree);

        return ChansonElement;
    }
   
}
