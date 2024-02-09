/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.view.common;

import java.util.logging.Level;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Codeiro
 */
public class FXMLControllerUtils {
    
    public void clearTextFields(ObservableList<Node> nodeList)
    {
        for(Node node : nodeList)
        {
            if(node instanceof TextField)
            {
                ((TextField) node).setText("");
            }
            else if (node instanceof ComboBox)
            {
                ((ComboBox) node).setValue(null);
            }
            else if(node instanceof GridPane)
            {
                clearTextFields(((GridPane)node).getChildren());
            }
        }
    }
    
    public boolean isTextFieldsEmpty(ObservableList<Node> nodeList)
    {
        for(Node node : nodeList)
        {
            if(node instanceof TextField)
            {
                if(((TextField) node).getText().isEmpty() && !"txtID".equals(((TextField) node).getId()))
                {
                    return true;
                }
            }
            else if (node instanceof ComboBox)
            {
                if(null == ((ComboBox) node).getValue())
                {
                    return true;
                }
            }
            else if(node instanceof GridPane)
            {
                if(isTextFieldsEmpty(((GridPane)node).getChildren()))
                {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public boolean isNewEntry(ObservableList<Node> nodeList)
    {
        for(Node node : nodeList)
        {
            if(node instanceof TextField)
            {
                if("txtID".equals(((TextField) node).getId()) && ((TextField) node).getText().isEmpty())
                {
                    return true;
                }
            }
            else if(node instanceof GridPane)
            {
                if(isNewEntry(((GridPane)node).getChildren()))
                {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public int stringToInt(String value)
    {
        try
        {
            return Integer.parseInt(value);
        } catch(NumberFormatException ex) {
            return 0;
        }        
    }
}
