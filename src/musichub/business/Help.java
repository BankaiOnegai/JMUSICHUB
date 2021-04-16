/**
 * Definition du package buisness
 */
package src.musichub.business;

/**
 * Class Help
 * Elle permet lors de la création d'un objet d'afficher une aide plus detailler des différentes commandes
 * L'affichage se fait directement dans le constructeur
 */
public class Help {
    public Help(){
        System.out.println("\t\t------------Help------------");
        System.out.println("l: Affichage de toutes les listes ");
        System.out.println("c : Creation d'une nouvelle Chanson et ajout dans une liste de Chansons");
        System.out.println("a : Creation d'un nouvel Album et ajout dans une liste d'Albums");
        System.out.println("+ : Ajout d'une chanson crée au préalable ou contenue dans le fichier xml dans un album existant");
        System.out.println("| : Création d’un nouveau livre audio et ajout dans une liste de LivreAudio");
        System.out.println("p : creation d’une nouvelle playlist à partir de chansons et livres audio existants");
        System.out.println("- : suppression d’une playlist parmis une liste de playlist existantes");
        System.out.println("s : Sauvegarde des lists dans leurs fichier xml respectif ");
        System.out.println("h : Appel de la Commande help");
        System.out.println("x: Quitter ");
    }
}
