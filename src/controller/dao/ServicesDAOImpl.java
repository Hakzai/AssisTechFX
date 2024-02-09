/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dao;

import controller.dao.crud.DatabaseWriteDAO;
import controller.dao.crud.ReadOnlyDAO;
import javafx.collections.ObservableList;
import model.ModelObject;
import controller.model.ServicesDAO;


public class ServicesDAOImpl extends ServicesDAO {

    private DatabaseWriteDAO dbWriteDAO = new DatabaseWriteDAO();
    private ReadOnlyDAO dbReadDAO = new ReadOnlyDAO();
    private boolean statusCode = false;
    
    @Override
    public void databaseUpdate(ModelObject object, String sql) 
    {  
        if(null != sql || !"".equals(sql))
        {
            statusCode = dbWriteDAO.execute(object, sql);
        }
    }

    @Override
    public void databaseRead(ObservableList<Object> objectList, String sql) 
    {
        if(null != sql || !"".equals(sql))
        {
            statusCode = dbReadDAO.execute(objectList, sql);
        }
    }

    @Override
    public void loadFormData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void getDataFromTable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object setDataOnTable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean getStatusCode()
    {
        return statusCode;
    }
    
}
