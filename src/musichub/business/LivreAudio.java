/**
 * Definition du package buisness
 */
package src.musichub.business;
/**
 * Import des packages dom pour utiliser des elements et des documents
 */
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Class LivreAudio
 * Class fille de MotherClass
 * Elle implemente l'interface Audio
 * Elle permet de définir les attributs d'un LivreAudio
 */
public class LivreAudio extends MotherClass implements Audio {
    private String Contenu;
    private String Auteur;
    private Langues Langue;
    private Categories Categorie;
    private int Duree;

    /**
     * Constructeur de notre Class LivreAudio
     * Il permet de Set tous les attributs d'un LivreAudio
     * @param Title
     * @param ID
     * @param Auteur
     * @param Contenu
     * @param Langue
     * @param Categorie
     * @param Duree
     */
    public LivreAudio(String Title, int ID, String Auteur,String Contenu,Langues Langue,Categories Categorie,int Duree){
        super(Title, ID);
        this.Auteur = Auteur;
        this.Contenu = Contenu;
        this.Langue = Langue;
        this.Categorie = Categorie;
        this.Duree = Duree;
    }

    /**
     * Accesseur de Contenu
     * @return
     */
    public String getContenu(){
        return this.Contenu;
    }

    /**
     * Accesseur d'Auteur
     * @return
     */
    public String getAuteur(){
        return this.Auteur;
    }

    /**
     * Accesseur de Langue
     * @return
     */
    public Langues getLangue(){
        return this.Langue;
    }

    /**
     * Accesseur de Categorie
     * @return
     */
    public Categories getCategorie(){
        return this.Categorie;
    }

    /**
     * Accesseur de Duree
     * @return
     */
    public int getDuree(){
        return this.Duree;
    }

    /**
     * Methode toString de notre class
     * Elle permet d'afficher un LivreAudio avec tous ses attributs
     * @return
     */
    public String toString(){
        return "Titre " + this.getTitle() + " ID " + this.getID()  + " Auteur " + this.getAuteur() + " Contenu " + this.getContenu()
        + " Langue " + this.getLangue() + " Categorie " + this.getCategorie() + " Duree " + this.getDuree();
    }

    /**
     * Accesseur d'Element
     * Elle est utilisé lors de l'écriture d'un LivreAudio dans un fichier XML
     * @param document
     * Elle renvoie un Element qui contient les informations d'un LivreAudio
     * @return
     */
    public Element getElement(Document document){

        Element LivreAudioElement = document.createElement("LivreAudio");

        Element title = document.createElement("Title");
        title.appendChild(document.createTextNode(getTitle()));
        LivreAudioElement.appendChild(title);

        Element id = document.createElement("ID");
        id.appendChild(document.createTextNode(String.valueOf(getID())));
        LivreAudioElement.appendChild(id);

        Element auteur = document.createElement("Auteur");
        auteur.appendChild(document.createTextNode(getAuteur()));
        LivreAudioElement.appendChild(auteur);

        Element contenu = document.createElement("Contenu");
        contenu.appendChild(document.createTextNode(getContenu()));
        LivreAudioElement.appendChild(contenu);

        Element langue = document.createElement("Langue");
        langue.appendChild(document.createTextNode(String.valueOf(getLangue())));
        LivreAudioElement.appendChild(langue);

        Element categorie = document.createElement("Categorie");
        categorie.appendChild(document.createTextNode(String.valueOf(getCategorie())));
        LivreAudioElement.appendChild(categorie);

        Element duree = document.createElement("Duree");
        duree.appendChild(document.createTextNode(Integer.toString(getDuree())));
        LivreAudioElement.appendChild(duree);

        return LivreAudioElement;
    }
}