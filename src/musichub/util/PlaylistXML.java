/**
 * Definition du package util
 */
package src.musichub.util;

/**
 * Import des packages necessaires a l'utilisation de notre Class
 */
import src.musichub.business.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.SAXException;

import org.w3c.dom.*;

import java.io.IOException;
import java.io.File;

/**
 * Class PlaylistXML
 * Elle permet de lire et ecrire dans un fichier XML
 */
public class PlaylistXML {
    private TransformerFactory transformerFactory;
    private Transformer transformer;
    private DocumentBuilderFactory documentFactory;
    private DocumentBuilder documentBuilder;

    /**
     * Le nom du fichier XML qu'on lit
     */
    private static String XML_INPUT_FILE = "files/Playlist.xml";

    public PlaylistXML() {
        try {
            transformerFactory = TransformerFactory.newInstance();
            transformer = transformerFactory.newTransformer();
            documentFactory = DocumentBuilderFactory.newInstance();
            documentBuilder = documentFactory.newDocumentBuilder();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        }
    }

    /**
     * La methode qui transforme le document en memoire en fichier XML sur le disque dur
     * @param document le document à transformer
     * @param filePath le chemin (répértoire et nom) du fichier XML à créer
     */
    public void createXMLFile(Document document, String filePath)
    {
        try {
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(filePath));

            //transform the DOM Object to an XML File
            transformer.transform(domSource, streamResult);

        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
        System.out.println("Done creating Playlist XML File");
    }

    /**
     * La methode qui crée le document en memoire
     * @return le document créé
     */
    public Document createXMLDocument()
    {
        return documentBuilder.newDocument();
    }

    /**
     * La methode qui lit un fichier XML et le transforme en liste de noeuds en mémoire
     * @param filePath le chemin (répértoire et nom) du fichier XML à lire
     * @return la liste des noeuds lus
     */
    public NodeList parseXMLFile (String filePath) {
        NodeList elementNodes = null;
        try {
            Document document= documentBuilder.parse(new File(filePath));
            Element root = document.getDocumentElement();

            elementNodes = root.getChildNodes();
        }
        catch (SAXException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return elementNodes;
    }

    /**
     * Methode pour lire un fichier XML qui contient des Playlist
     * @return Elle renvoie une PlaylistsTemp
     */
    public PlaylistsTemp readXMLPlaylist() {
        NodeList nodes = this.parseXMLFile(XML_INPUT_FILE); //creation d'une nodeList du fichier Playlist.xml

        PlaylistsTemp playlistsTemp = new PlaylistsTemp();//creation d'une PlaylistTemp

        if (nodes == null) return null;

        for (int i = 0; i < nodes.getLength(); i++) {
            if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE){
                Element currentElement = (Element) nodes.item(i);
                if (currentElement.getNodeName().equals("Playlist")){
                    try {
                        String title = currentElement.getElementsByTagName("Title").item(0).getTextContent();
                        String idString = currentElement.getElementsByTagName("ID").item(0).getTextContent();
                        int id = Integer.parseInt(idString);

                        Playlists playlists = new Playlists(title,id);

                        try {
                            NodeList nodesChild = currentElement.getChildNodes();
                            ChansonTemp chansonTemp = new ChansonTemp();
                            LivreAudioTemp livreAudioTemp = new LivreAudioTemp();

                            for (int j = 0; j < nodesChild.getLength(); j++) {
                                if (nodesChild.item(j).getNodeType() == Node.ELEMENT_NODE) {

                                    Element childElement = (Element) nodesChild.item(j);

                                    if (childElement.getNodeName().equals("Elements")) {

                                        try {
                                            NodeList finalChild = childElement.getChildNodes();

                                            for (int x = 0; x < finalChild.getLength(); x++) {
                                                if (finalChild.item(x).getNodeType() == Node.ELEMENT_NODE) {
                                                    Element finalElement = (Element) finalChild.item(x);

                                                    if (finalElement.getNodeName().equals("Chanson")) {

                                                        String title2 = finalElement.getElementsByTagName("Title").item(0).getTextContent();
                                                        String idString2 = finalElement.getElementsByTagName("ID").item(0).getTextContent();
                                                        int id2 = Integer.parseInt(idString2);
                                                        String artiste = finalElement.getElementsByTagName("Artiste").item(0).getTextContent();
                                                        String contenu1 = finalElement.getElementsByTagName("Contenu").item(0).getTextContent();
                                                        Genres genre = Genres.valueOf(finalElement.getElementsByTagName("Genre").item(0).getTextContent());
                                                        String dureeString = finalElement.getElementsByTagName("Duree").item(0).getTextContent();
                                                        int duree1 = Integer.parseInt(dureeString);

                                                        Chanson chanson = new Chanson(title2, id2, artiste, contenu1, genre, duree1);
                                                        playlists.addPlayListChanson(chanson);
                                                        playlists.affichePlaylist();
                                                    }
                                                    else if (finalElement.getNodeName().equals("Chanson")) {
                                                        String title3 = finalElement.getElementsByTagName("Title").item(0).getTextContent();
                                                        String idString3 = finalElement.getElementsByTagName("ID").item(0).getTextContent();
                                                        int id3 = Integer.parseInt(idString3);
                                                        String auteur = finalElement.getElementsByTagName("Auteur").item(0).getTextContent();
                                                        String contenu2 = finalElement.getElementsByTagName("Contenu").item(0).getTextContent();
                                                        Langues langue = Langues.valueOf(finalElement.getElementsByTagName("Langue").item(0).getTextContent());
                                                        Categories categorie = Categories.valueOf(finalElement.getElementsByTagName("Categorie").item(0).getTextContent());
                                                        String dureeString = finalElement.getElementsByTagName("Duree").item(0).getTextContent();
                                                        int duree2 = Integer.parseInt(dureeString);

                                                        LivreAudio livreAudio = new LivreAudio(title3, id3, auteur, contenu2, langue, categorie, duree2);
                                                        playlists.addPlayListLivreAudio(livreAudio);
                                                    }
                                                }
                                                playlistsTemp.addPlaylist(playlists);
                                            }
                                            } catch(Exception e){
                                                System.out.println("Something is wrong with the XML client element");
                                            }
                                        }
                                    }
                            }
                        }catch(Exception e){
                            System.out.println("Probleme de lecture des chansons");
                        }

                    } catch (Exception ex) {
                        System.out.println("Something is wrong with the XML client element");
                    }
                }
            }
        }
        return playlistsTemp;
    }

    /**
     * Methode pour démontrer l'écriture d'un fichier XML avec plusieurs element
     */
    public void writeXMLPlaylist(PlaylistsTemp playlistTemp) {
        Document document = this.createXMLDocument();
        if (document == null) return;

        Element root = document.createElement("Playlists");
        document.appendChild(root);

        for(Playlists playlist : playlistTemp.getListPlaylist()){
            root.appendChild(playlist.getElement(document));
        }
        this.createXMLFile(document, XML_INPUT_FILE);
    }
}