/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.classe;

import java.lang.reflect.Field;
import model.GlobalObject;
import model.ModelObject;

/**
 *
 * @author Codeiro
 */
public class CelularNew extends ModelObject{
    
    protected int id;
    protected String model;
    protected String manufacturer;
    protected String year;
    protected String serialNum;
    protected String useState;
    protected String isOurProperty;
    protected int idCliente;
    
    public CelularNew(){
        super();
    }
    
    public CelularNew(int id, String model, String manufacturer, String year, String serialNum, String useState, String isOurProperty, int idCliente){
       this.id = id;
       this.model = model;
       this.manufacturer = manufacturer;
       this.year = year;
       this.serialNum = serialNum;
       this.useState = useState;
       this.isOurProperty = isOurProperty;
       this.idCliente = idCliente;               
    }

    public int getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getYear() {
        return year;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public String getUseState() {
        return useState;
    }

    public String getIsOurProperty() {
        return isOurProperty;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setId(int id) {
        this.id = id;
        modelPK.put(CelularNew.class.getDeclaredFields()[0].getName(), String.valueOf(id));
    }

    public void setModel(String model) {
        this.model = model;
        modelVars.add(model);
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
        modelVars.add(manufacturer);
    }

    public void setYear(String year) {
        this.year = year;
        modelVars.add(year);
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
        modelVars.add(serialNum);
    }

    public void setUseState(String useState) {
        this.useState = useState;
        modelVars.add(useState);
    }

    public void setIsOurProperty(String isOurProperty) {
        this.isOurProperty = isOurProperty;
        modelVars.add(isOurProperty);
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
        modelVars.add(String.valueOf(idCliente));
    }
        
}
