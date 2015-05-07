/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import EMS.Coordinator;
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

/**
 *
 * @author Suleiman
 */
public class Coordinator_Service extends Services {

    public Coordinator cord;

    public Coordinator_Service(Coordinator cord) {
        this.cord = cord;
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
                    event_list.add(getTagValue("name", eElement));
                }
            }
        } catch (Exception ex) {
            System.out.println("Database exception : checkListAllProgram()");
        }
        cord.event_list.setListData(new String[0]);
        cord.event_list.setModel(new javax.swing.AbstractListModel() {
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

    @Override
    public void list_all_users() {
        try {
            File fXmlFile = new File(filepath_users);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("user");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                Element eElement = (Element) nNode;
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    user_list.add(getTagValue("email", eElement));
                }
            }
        } catch (Exception ex) {
            System.out.println("Database exception : checkListAllUserEmail()");
        }
        cord.list_all_email.setListData(new String[0]);
        cord.list_all_email.setModel(new javax.swing.AbstractListModel() {
            String[] strings = user_list.toArray(new String[user_list.size()]);

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

    public boolean search_event(String name) {
        System.out.print(name);
        int flag = 0;
        try {
            File fXmlFile = new File(filepath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("event");
            cord.event_details.setText("");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    if (getTagValue("name", eElement).equals(name)) {
                        cord.event_name.setText(getTagValue("name", eElement));
                        cord.event_attend.setText(getTagValue("group", eElement));
                        cord.event_details.append("Event Date: " + getTagValue("date", eElement) + "\n");
                        cord.event_details.append("Event Status : " + getTagValue("status", eElement) + "\n");
                        cord.event_details.append(getTagValue("detail", eElement));
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception ex) {
            System.out.println("Database exception : search_event(): \n" + ex.getMessage());
        }
        return false;
    }

    public int check_event(String name) {
        System.out.print(name);
        int flag = 0;
        try {
            File fXmlFile = new File(filepath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("event");
            cord.event_details.setText("");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    if (getTagValue("name", eElement).equals(name)) {
                        return flag = 1;
                    }
                }
            }
            return flag;
        } catch (Exception ex) {
            System.out.println("Database exception : Check_event(): \n" + ex.getMessage());
        }
        return flag;
    }

    public int delete_event(String name) {
        System.out.print(name);
        int flag = 0;
        try {
            File fXmlFile = new File(filepath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("event");
            cord.event_details.setText("");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    if (getTagValue("name", eElement).equals(name)) {                                                
                        nNode.getParentNode().removeChild(nNode);
                    }
                }
            }
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filepath));
 
            transformer.transform(source, result);
            return flag;
        } catch (Exception ex) {
            System.out.println("Database exception : Check_event(): \n" + ex.getMessage());
        }
        return flag;
    }

    public int add_new_event(String name, String details, String group, String date) {
        JOptionPane.showMessageDialog(null, name);
        int flag = 0;
        if (check_event(name) != 1) {
            try {
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                Document doc = docBuilder.parse(filepath);

                Node data = doc.getFirstChild();
                Element newevent = doc.createElement("event");

                Element neweventname = doc.createElement("name");
                neweventname.setTextContent(name);
                Element neweventdetails = doc.createElement("detail");
                neweventdetails.setTextContent(details);
                Element neweventdate = doc.createElement("date");
                neweventdate.setTextContent(date);
                Element newgroup = doc.createElement("group");
                newgroup.setTextContent(group);
                Element neweventstatus = doc.createElement("group");
                neweventstatus.setTextContent("pending");

                newevent.appendChild(neweventname);
                newevent.appendChild(newgroup);
                newevent.appendChild(neweventdate);
                newevent.appendChild(neweventdetails);
                newevent.appendChild(neweventstatus);

                data.appendChild(newevent);

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File(filepath));
                transformer.transform(source, result);
                list_all_event();
                flag = 1;
            } catch (Exception ex) {
                System.out.println("New Event added - Succssfully updated events.xml");
            }
        } else {
            return flag;
        }
        return flag;
    }

    public void update_event(String old_name, String event_new_name, String date, String detail, String group) {
        if (check_event(old_name) == 1) {
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
                        if (eElement.getElementsByTagName("name").item(0).getTextContent().equals(old_name)) {
                            eElement.getElementsByTagName("name").item(0).setTextContent(event_new_name);
                            eElement.getElementsByTagName("group").item(0).setTextContent(group);
                            eElement.getElementsByTagName("date").item(0).setTextContent(date);
                            eElement.getElementsByTagName("detail").item(0).setTextContent(detail);
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
                System.out.println("Event - XML file updated successfully");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
