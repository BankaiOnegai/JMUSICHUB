/**
 * On declare notre package
 */
package src.musichub.business;

import java.util.*;

/**
 * Classe mere de type abstract
 * Elle permet de liée nos classes Chansons/Albums/LivreAudio/Playlist
 */
abstract class MotherClass{
    
    private String Title;
    private int ID;

    /**
     * Constructeur de MotherClass qui ne return rien
     */
    public MotherClass(){
        return;
    }

    /**
     * Constructeur qui nous permet de set notre title et id
     * @param Title
     * @param ID
     */
    public MotherClass(String Title, int ID){
        this.Title = Title;
        this.ID = ID;
    }

    /**
     * Accesseur de Title
     * @return
     */
    public String getTitle(){
        return this.Title;
    }

    /**
     * Accesseur de ID
     * @return
     */
    public int getID(){
        return this.ID;
    }

    /**
     * Methode pour afficher une list donnée en parametre
     * @param list
     */
    public void affiche(List<MotherClass> list){
        for(int i = 0; i< list.size(); i++){
            System.out.println(list.get(i));
        }
    }
}
