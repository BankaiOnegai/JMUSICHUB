/**
 * Definition du package buisness
 */
package src.musichub.business;

/**
 * import des packages utils a notre interface
 */

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Interface Audio
 * Elle permet de faire un lien entre nos classes Chansons et LivreAudio
 * Elle est utile dans notre classe Playlists qui doit contenir des LivreAudio et des Chansons
 * On met les accesseurs en communs entre les deux Classe Chanson et LivreAudio
 */
public interface Audio{
    public String getTitle();
    public int getDuree();
    public String getContenu();
    public Element getElement(Document document);

}
