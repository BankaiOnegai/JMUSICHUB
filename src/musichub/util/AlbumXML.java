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
 * Class AlbumXML
 * Elle permet de lire et ecrire dans un fichier XML
 */
public class AlbumXML {
    private TransformerFactory transformerFactory;
    private Transformer transformer;
    private DocumentBuilderFactory documentFactory;
    private DocumentBuilder documentBuilder;

    /**
     * Le nom du fichier XML qu'on lit
     */
    private static String XML_INPUT_FILE = "files/Album.xml";

    public AlbumXML() {
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
        System.out.println("Done creating Album XML File");
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
     * Methode pour démontrer la lecture d'un fichier XML qui contient plusieurs éléments
     */
    public AlbumTemp readXMLAlbum(){
        NodeList nodes = this.parseXMLFile(XML_INPUT_FILE);//creation d'une nodeList
        AlbumTemp albumTemp = new AlbumTemp();

        if (nodes == null) return null;

        for (int i = 0; i<nodes.getLength(); i++) {
            if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE)   {
                Element currentElement = (Element) nodes.item(i); //creation d'un element
                if (currentElement.getNodeName().equals("Album")) { //on test pour voir si on est sur Album
                    try {
                        String title = currentElement.getElementsByTagName("Title").item(0).getTextContent();
                        String idString = currentElement.getElementsByTagName("ID").item(0).getTextContent();
                        int id = Integer.parseInt(idString);
                        String artiste = currentElement.getElementsByTagName("Artiste").item(0).getTextContent();
                        String dateString = currentElement.getElementsByTagName("Date").item(0).getTextContent();
                        int date = Integer.parseInt(dateString);
                        String dureeString = currentElement.getElementsByTagName("Duree").item(0).getTextContent();
                        int duree = Integer.parseInt(dureeString);

                        Album album = new Album(title, id, artiste, date, duree); //on cree un Album

                        NodeList childNodes = currentElement.getChildNodes(); //on cree une nodeList de CurrentElement
                        for (int j = 0; j<childNodes.getLength();j++) { //on boucle pour tout les element enfant
                            if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
                                Element childElement = (Element) childNodes.item(j); //on cree un nouvel element
                                if (childElement.getNodeName().equals("Chansons")) { //on verifie si le noeud est bien chansons
                                    NodeList finalChild = childElement.getChildNodes(); // on cree une novelle nodeList qui prend les noeuds enfants de childElement

                                    for (int x = 0; x < finalChild.getLength(); x++) { //on boucle pour tout les element enfant
                                        if (finalChild.item(x).getNodeType() == Node.ELEMENT_NODE) {
                                            Element finalElement = (Element) finalChild.item(x); //on cree un nouvel element finalElement qui prend les noeuds enfant de finalChilds

                                            if (finalElement.getNodeName().equals("Chanson")) {

                                                String title2 = finalElement.getElementsByTagName("Title").item(0).getTextContent();
                                                String idString2 = finalElement.getElementsByTagName("ID").item(0).getTextContent();
                                                int id2 = Integer.parseInt(idString2);
                                                String artiste2 = finalElement.getElementsByTagName("Artiste").item(0).getTextContent();
                                                String contenu2 = finalElement.getElementsByTagName("Contenu").item(0).getTextContent();
                                                Genres genre = Genres.valueOf(finalElement.getElementsByTagName("Genre").item(0).getTextContent());
                                                String dureeString2 = finalElement.getElementsByTagName("Duree").item(0).getTextContent();
                                                int duree2 = Integer.parseInt(dureeString);


                                                Chanson chanson = new Chanson(title2, id2, artiste2, contenu2, genre, duree2); //on crée une nouvelle Chanson et on lui donne les attributs que l'on a lu
                                                album.addChanson(chanson); //on ajoute la chanson dans l'album
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        albumTemp.addAlbum(album); //on ajoute Album dans AlbumTemp
                    } catch (Exception ex) {
                        System.out.println("Something is wrong with the XML client element");
                    }
                }
            }
        }
        return albumTemp;
    }

    /**
     * Methode pour démontrer l'écriture d'un fichier XML avec plusieurs elements
     */
    public void writeXMLAlbum(AlbumTemp albumTemp) {
        Document document = this.createXMLDocument(); //on cree un nouveau Document
        if (document == null) return;

        Element root = document.createElement("Albums");
        document.appendChild(root);

        for(Album album : albumTemp.getListAlbum()){ //on boucle pour crée toutes les chansons
            root.appendChild(album.getElement(document));
        }
        this.createXMLFile(document, XML_INPUT_FILE);
    }
}