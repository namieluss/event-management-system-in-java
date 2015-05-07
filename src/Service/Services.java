/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.util.ArrayList;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Suleiman
 */
public class Services {

    public String filepath = "files//Event_file.xml";
    public String filepath_users = "files//User_file.xml";
    public String filepath_programs = "files//Program_file.xml";
    public String filepath_event_attend = "files//Attendance.xml";

    ArrayList<String> event_list = new ArrayList<>();
    ArrayList<String> user_list = new ArrayList<>();
    ArrayList<String> program_list = new ArrayList<>();
    
    public Services() {

    }

    public boolean check_program(String name) {
        return false;
    }

    public void list_all_lecturers() {

    }

    public void list_all_users() {
    }
    
    public static String getTagValue(String sTag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        return nValue.getNodeValue();
    }
}
