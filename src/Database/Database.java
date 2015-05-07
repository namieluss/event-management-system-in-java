package Database;

/**
 *
 * @author Suleiman
 */

import java.io.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

public class Database {
    public String filepath = "files//user_file.xml";
    
    public String name, email, role;
    
    public boolean userExists(String username) {
        try {
            File fXmlFile = new File(filepath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("user");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    if (getTagValue("email", eElement).equals(username)) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception ex) {
            System.out.println("Database exception : userExists()");
            return false;
        }
    }

    public boolean checkLogin(String email, String password) {
        
        if (!userExists(email)) { return false; }
        
        try {
            File fXmlFile = new File(filepath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("user");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    if (getTagValue("email", eElement).equals(email) && getTagValue("pass", eElement).equals(password)) {
                        this.email = getTagValue("email", eElement);
                        this.role = getTagValue("role", eElement);
                        this.name = getTagValue("name", eElement);
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception ex) {
            System.out.println("Database exception : checkLogin()");
            return false;
        }
    }

    public void addUser(String username, String password) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(filepath);

            Node data = doc.getFirstChild();

            String[] parts = password.split("#");

            String pass = parts[0];
            String email = parts[1];

            Element newuser = doc.createElement("user");
            Element newusername = doc.createElement("username");
            newusername.setTextContent(username);
            Element newpassword = doc.createElement("password");
            newpassword.setTextContent(pass);
            Element newemail = doc.createElement("email");
            newemail.setTextContent(email);

            newuser.appendChild(newusername);
            newuser.appendChild(newpassword);
            newuser.appendChild(newemail);

            data.appendChild(newuser);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("login.xml"));
            transformer.transform(source, result);

        } catch (Exception ex) {
            System.out.println("Exceptionmodify xml");
        }
    }

    public static String getTagValue(String sTag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        return nValue.getNodeValue();
    }
}
