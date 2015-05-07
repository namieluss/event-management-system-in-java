/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import EMS.Student;
import static Service.HOS_Service.getTagValue;
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
public class Student_Service extends Services {

    public Student std;

    public Student_Service(Student std) {
        this.std = std;
    }

    public boolean view_details_event(String name) {
        System.out.print(name);
        try {
            File fXmlFile = new File(filepath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("event");
            std.event_detail.setText("");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    if (getTagValue("name", eElement).equals(name)) {
                        std.view_event.setText(getTagValue("name", eElement));
                        std.event_detail.append("Event Date: " + getTagValue("date", eElement) + "\n");
                        std.event_detail.append("Event Status : " + getTagValue("status", eElement) + "\n");
                        std.event_detail.append("\nEvent Detail\n" + getTagValue("detail", eElement));
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
                    if (getTagValue("status", eElement).equals("approve")) {
                        event_list.add(getTagValue("name", eElement));
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("Database exception : list_all_event()");
        }

        std.event_list.setListData(new String[0]);
        std.event_list.setModel(new javax.swing.AbstractListModel() {
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

    public void attend_event(String name, String event) {

        if (check_student_event(name, event) != true) {
            try {

                File xmlFile = new File(filepath_event_attend);

                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                Document doc = documentBuilder.parse(xmlFile);

                doc.getDocumentElement().normalize();
                NodeList nodeList = doc.getElementsByTagName("event");

                for (int itr = 0; itr < nodeList.getLength(); itr++) {
                    Node node = nodeList.item(itr);

                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) node;
                        if (eElement.getElementsByTagName("name").item(0).getTextContent().equals(event)) {

                            Element newAttendeename = doc.createElement("attendee");
                            newAttendeename.setTextContent(name);

                            eElement.getElementsByTagName("attendees").item(0).appendChild(newAttendeename);

                            JOptionPane.showMessageDialog(null, "Registration Successful!");
                        }
                    }
                }

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File(filepath_event_attend));
                transformer.transform(source, result);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Registered for '"+event+"' Already ");
        }
    }

    public boolean check_student_event(String name, String event) {
        try {

            File xmlFile = new File(filepath_event_attend);

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("event");
            int count = 0 , flag = 0;
            for (int itr = 0; itr <= nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    System.out.print("\n"+event+": "+count);   
                    System.out.print("\nLength: "+nodeList.getLength()+"\n");   
                    if (getTagValue("name", eElement).equals(event)) {
                        NodeList attende_list = eElement.getElementsByTagName("attendees");
                        System.out.print(eElement.getElementsByTagName("name").item(0).getTextContent());
                        for (int ated = 0; ated < attende_list.getLength(); ated++) {
                            Node atted_node = attende_list.item(ated);
                            Element attendElement = (Element) atted_node;

                            if (getTagValue("attendee", attendElement).equals(name)) {
                                return true;
                            }
                            System.out.print(attendElement.getElementsByTagName("attendee").item(0).getTextContent());
                        }
                    }
                } count++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
