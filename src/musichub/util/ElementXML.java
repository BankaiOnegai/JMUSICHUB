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
import java.util.*;

/**
 * Class ElementXML
 * Elle permet de lire et ecrire dans un fichier XML
 */
public class ElementXML {
    private TransformerFactory transformerFactory;
    private Transformer transformer;
    private DocumentBuilderFactory documentFactory;
    private DocumentBuilder documentBuilder;

    /**
     * Le nom du fichier XML qu'on lit
     */
    private static String XML_INPUT_FILE = "files/Element.xml";

    public ElementXML() {
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
        System.out.println("Done creating Element XML File");
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
     * Methode qui permet de lire plusieurs Chansons contenue dans le fichier Element.xml
     * Elle return une ChansonTemp qui contient une list de Chansons
     * @return
     */
    public ChansonTemp readXMLChanson(){
        NodeList nodes = this.parseXMLFile(XML_INPUT_FILE);
        ChansonTemp chansonTemp = new ChansonTemp();

        if (nodes == null) return null;

        for (int i = 0; i<nodes.getLength(); i++) {
            if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE){
                Element currentElement = (Element) nodes.item(i);
                System.out.println(currentElement.getNodeName());
                if (currentElement.getNodeName().equals("Chanson")) {
                    try {
                        String title = currentElement.getElementsByTagName("Title").item(0).getTextContent();
                        String idString = currentElement.getElementsByTagName("ID").item(0).getTextContent();
                        int id = Integer.parseInt(idString);
                        String artiste = currentElement.getElementsByTagName("Artiste").item(0).getTextContent();
                        String contenu = currentElement.getElementsByTagName("Contenu").item(0).getTextContent();
                        Genres genre = Genres.valueOf(currentElement.getElementsByTagName("Genre").item(0).getTextContent());
                        String dureeString = currentElement.getElementsByTagName("Duree").item(0).getTextContent();
                        int duree = Integer.parseInt(dureeString);

                        Chanson chanson = new Chanson(title, id, artiste, contenu, genre, duree);
                        chansonTemp.addChanson(chanson);
                    } catch (Exception ex) {
                        System.out.println("Something is wrong with the XML client element");
                    }
                }
            }
        }
        return chansonTemp;
    }

    /**
     * Methode qui permet de lire plusieurs LvireAudio contenue dans le fichier Element.xml
     * Elle return un LivreAudioTemp qui contient une liste de LivreAudio
     * @return
     */
    public LivreAudioTemp readXMLLivreAudio(){
        NodeList nodes = this.parseXMLFile(XML_INPUT_FILE);
        LivreAudioTemp livreAudioTemp = new LivreAudioTemp();

        if (nodes == null) return null;

        for (int i = 0; i<nodes.getLength(); i++) {
            if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE){
                Element currentElement = (Element) nodes.item(i);
                if (currentElement.getNodeName().equals("LivreAudio")){
                    try {
                        String title = currentElement.getElementsByTagName("Title").item(0).getTextContent();
                        String idString = currentElement.getElementsByTagName("ID").item(0).getTextContent();
                        int id = Integer.parseInt(idString);
                        String auteur = currentElement.getElementsByTagName("Auteur").item(0).getTextContent();
                        String contenu = currentElement.getElementsByTagName("Contenu").item(0).getTextContent();
                        Langues langue = Langues.valueOf(currentElement.getElementsByTagName("Langue").item(0).getTextContent());
                        Categories categorie = Categories.valueOf(currentElement.getElementsByTagName("Categorie").item(0).getTextContent());
                        String dureeString = currentElement.getElementsByTagName("Duree").item(0).getTextContent();
                        int duree = Integer.parseInt(dureeString);

                        LivreAudio livreAudio = new LivreAudio(title, id, auteur, contenu, langue, categorie, duree);
                        livreAudioTemp.addLivreAudio(livreAudio);

                    } catch (Exception ex) {
                        System.out.println("Something is wrong with the XML client element");
                    }
                }
            }
        }
        return livreAudioTemp;
    }

    /**
     * Methode pour démontrer l'écriture d'un fichier XML avec un seul élément
     */
    public void writeXMLElement(ChansonTemp chansonTemp, LivreAudioTemp livreAudioTemp) {
        Document document = this.createXMLDocument();
        List<Audio> listAudio = new ArrayList<>();
        if (document == null) return;

        for(Audio audio : chansonTemp.getListChanson()){ //boucle pour recuperer les chansons de chansonTemp et les mettre dans listAudio
            listAudio.add(audio);
        }
        for(Audio audio : livreAudioTemp.getListLivreAudio()){ //boucle pour recuperer les livreAudio et les mettre dans listAudio
            listAudio.add(audio);
        }

        Element root = document.createElement("Elements");
        document.appendChild(root);

        for(Audio audio : listAudio){ //boucle pour mettre tout les elements dans les elements dans root
            root.appendChild(audio.getElement(document));
        }
        this.createXMLFile(document, XML_INPUT_FILE);
    }
}
