/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Codeiro
 */
public class ModelObject {
    
    protected List<String> modelVars = new ArrayList<>();
    protected HashMap<String, String> modelPK = new HashMap<>();
    
    public List<String> getModelVars(){
        return modelVars;
    }
    
    public HashMap<String, String> getModelPK(){
        return modelPK;
    }
}
