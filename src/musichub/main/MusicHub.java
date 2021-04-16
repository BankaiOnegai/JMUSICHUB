/**
 * Declaration du package main
 */
package src.musichub.main;

import src.musichub.util.*;
import src.musichub.business.*;

import java.util.*;

/**
 * Declaration de la classe MusicHub
 */
public class MusicHub{
    /**
     * Methode qui permet d'afficher un menu sur la sortie standar
     */
    public static void menu(){
        System.out.println("\t\t---------- MENU ---------");
        System.out.println("\tl : Affichage des listes");
        System.out.println("\tc : Rajout d'une nouvelle chanson");
        System.out.println("\ta : Rajout d'un nouvel Album");
        System.out.println("\t+ : Rajout d'une chanson a un Album existant");
        System.out.println("\t| : Rajout d’un nouveau livre audio");
        System.out.println("\tp : Creation playlist avec Chanson/LivreAudio");
        System.out.println("\t- : suppression d’une playlist");
        System.out.println("\ts : Sauvegarde dans les fichiers xml respectif");
        System.out.println("\th : Commande help");

        System.out.println("\tx: Quitter ");
    }

    /**
     * Main de notre projet
     * @param args
     * @throws Exception
     */
    public static void main(String [] args) throws Exception {
        /**
         * On declare des objets afin d'utiliser la lecture et ecritures sur des fichiers xml
         * On declare nos objets qui vont nous permettres de faire toutes les opérations du programmes
         */


        AlbumXML sauvegardeAlbum = new AlbumXML();
        PlaylistXML sauvegardePlaylist = new PlaylistXML();
        ElementXML sauvegardeElement = new ElementXML();

        ChansonTemp c = sauvegardeElement.readXMLChanson();
        LivreAudioTemp l = sauvegardeElement.readXMLLivreAudio();
        AlbumTemp a = sauvegardeAlbum.readXMLAlbum();
        PlaylistsTemp p = sauvegardePlaylist.readXMLPlaylist();

        //on crée un nouveau scanner
        Scanner scan = new Scanner(System.in);
        String choice;
        //on cree un nouvelle objet menu de type Affichage
        Affichage menu = new Affichage();
        //on appel sa méthode MusicHub
        menu.MusicHub();

        try{//On essaie de faire les instructions suivantes
            do{
                menu();

                choice = scan.next();
                switch (choice.charAt(0)) {
                    case 'l':
                        System.out.println("\n\t\t----------  Liste des Albums  ----------");
                        a.SortByDate(a.getListAlbum());
                        a.afficheAlbum();

                        System.out.println("\n\t\t---------- Liste des Chansons ----------");
                        c.SortByGenre(c.getListChanson());
                        c.afficheChanson();

                        System.out.println("\n\t\t--------- Liste des LivreAudio ---------");
                        l.SortByAutor(l.getListLivreAudio());
                        l.afficheLivreAudio();

                        System.out.println("\n\t\t---------- Liste des PlayList ----------");
                        p.afficheListPlaylist();
                        break;

                    case 'c' :
                        System.out.println("Rajout d'une nouvelle chanson");
                        c.addChanson();
                        break;

                    case 'a' :
                        System.out.println("Rajout d'un nouvel Album");
                        a.addAlbum();
                        break;

                    case '+':
                        System.out.println("Rajout d'une chanson existante dans un album");
                        a.choixChanson(c.getListChanson(),a);
                        break;

                    case '|':
                        System.out.println("Rajout d’un nouveau livre audio");
                        l.addLivreAudio();
                        l.afficheLivreAudio();
                        break;

                    case 'p':
                        System.out.println("creation d’une nouvelle playlist à partir de chansons et livres audio existants");
                        p.addChansonLivreAudio(c,l);
                        break;

                    case '-':
                        System.out.println("suppression d’une playlist");
                        p.deletePlaylist();
                        break;

                    case 's':
                        System.out.println("Sauvegarde fichier xml respectif");
                        sauvegardeAlbum.writeXMLAlbum(a);
                        sauvegardePlaylist.writeXMLPlaylist(p);
                        sauvegardeElement.writeXMLElement(c,l);
                        break;

                    case 'h':
                        Help help = new Help();
                        break;

                    case 'x':
                        break;

                    default :
                        System.out.println("Bad choice . Try again . ");
                        break;
                }

            }while(choice.charAt(0) != 'x');

        }catch (Exception e){//On gere les exceptions grace a au catch
            System.out.println("Erreur");
        }
    }
}