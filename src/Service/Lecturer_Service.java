/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Service;

import EMS.Lecturer;
import static Service.HOS_Service.getTagValue;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Suleiman
 */


public class Lecturer_Service extends Services {
    
    public Lecturer lec;
    
    
    public Lecturer_Service(Lecturer lec){        
        this.lec = lec;    
    }
    
    public boolean view_details_event(String name) {
        System.out.print(name);
        int flag = 0;
        try {
            File fXmlFile = new File(filepath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("event");
            lec.event_details.setText("");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    if (getTagValue("name", eElement).equals(name)) {
                        lec.event_details.append(getTagValue("name", eElement));
                        lec.event_details.append("Event Date: " + getTagValue("date", eElement) + "\n");
                        lec.event_details.append("Event Status : " + getTagValue("status", eElement) + "\n");
                        lec.event_details.append("\nEvent Detail\n"+getTagValue("detail", eElement));
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

        lec.event_list.setListData(new String[0]);
        lec.event_list.setModel(new javax.swing.AbstractListModel() {
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
    
}
