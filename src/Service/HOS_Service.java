package Service;

/**
 *
 * @author Suleiman
 */
import EMS.HOS;
import java.io.File;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class HOS_Service extends Services{
    /* Head of school services */

    public HOS hos;

    public HOS_Service(HOS hos) {
        this.hos = hos;
    }

    public void add_new_program(String name, String details) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(filepath_programs);

            Node data = doc.getFirstChild();

            Element newprogram = doc.createElement("program");

            Element newprogramname = doc.createElement("name");
            newprogramname.setTextContent(name);
            Element newprogramdetails = doc.createElement("details");
            newprogramdetails.setTextContent(details);
            Element newcoordinator = doc.createElement("coordinator");
            newcoordinator.setTextContent("");

            newprogram.appendChild(newprogramname);
            newprogram.appendChild(newprogramdetails);
            newprogram.appendChild(newcoordinator);

            data.appendChild(newprogram);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filepath_programs));
            transformer.transform(source, result);

        } catch (Exception ex) {
            System.out.println("Exceptionmodify xml");
        }
    }

    @Override
    public boolean check_program(String name) {
        try {
            File fXmlFile = new File(filepath_programs);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("program");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    if (getTagValue("name", eElement).equals(name)) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception ex) {
            System.out.println("Database exception : check_programs()");
            return false;
        }
    }

    @Override
    public void list_all_lecturers() {
        try {
            File fXmlFile = new File(filepath_users);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("user");
            hos.lecturers.removeAllItems();
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                Element eElement = (Element) nNode;
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    if (getTagValue("role", eElement).equals("lecturer")) {

                        hos.lecturers.addItem(getTagValue("name", eElement));
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("Database exception : checkLogin()");
        }
    }

    public void list_all_event() {
        try {
            File fXmlFile = new File(filepath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("event");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                Element eElement = (Element) nNode;
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    if (getTagValue("status", eElement).equals("pending")) {
                        event_list.add(getTagValue("name", eElement));
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("Database exception : list_all_event()");
        }

        hos.event_list.setListData(new String[0]);
        hos.event_list.setModel(new javax.swing.AbstractListModel() {
            String[] strings = event_list.toArray(new String[event_list.size()]);

            @Override
            public int getSize() {
                return strings.length;
            }

            @Override
            public Object getElementAt(int i) {
                return strings[i];
            }
        });
    }

    public void list_all_program() {
        try {
            File fXmlFile = new File(filepath_programs);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("program");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                Element eElement = (Element) nNode;
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    program_list.add(getTagValue("name", eElement));
                }
            }
        } catch (Exception ex) {
            System.out.println("Database exception : checkListAllProgram()");
        }
        hos.program_list.setListData(new String[0]);
        hos.program_list.setModel(new javax.swing.AbstractListModel() {
            String[] strings = program_list.toArray(new String[program_list.size()]);

            @Override
            public int getSize() {
                return strings.length;
            }

            @Override
            public Object getElementAt(int i) {
                return strings[i];
            }
        });
    }

    public void add_program_coordinator(String program_name, String Lecturer) {
        try {

            File xmlFile = new File(filepath_programs);

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("program");

            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    if (eElement.getElementsByTagName("name").item(0).getTextContent().equals(program_name)) {
                        eElement.getElementsByTagName("coordinator").item(0).setTextContent(Lecturer);
                    }
                }
            }

            doc.getDocumentElement().normalize();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("programs.xml"));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
            System.out.println("Program Coordinator - XML file updated successfully");
            
            JOptionPane.showMessageDialog(null, "Add Successful!");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void event_management(String name, String type){
        try {

            File xmlFile = new File(filepath);

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("event");

            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    if (eElement.getElementsByTagName("name").item(0).getTextContent().equals(name)) {
                        eElement.getElementsByTagName("status").item(0).setTextContent(type);
                        JOptionPane.showMessageDialog(null, "Event "+type+"d Successfully!");
                    }
                }
            }

            doc.getDocumentElement().normalize();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("events.xml"));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
            System.out.println("XML file updated successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getTagValue(String sTag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        return nValue.getNodeValue();
    }
}
