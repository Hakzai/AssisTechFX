/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.dao.ServicesDAOImpl;
import controller.view.common.FXMLControllerUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Codeiro
 */
public abstract class FXMLControllerBase {
    
    protected static final ServicesDAOImpl DAO = new ServicesDAOImpl();
    protected static final FXMLControllerUtils UTILS = new FXMLControllerUtils();
    
    @FXML
    protected abstract void handleBtnNovo(ActionEvent event);
    
    @FXML
    protected abstract void handleBtnSalvar(ActionEvent event);
    
    @FXML
    protected abstract void handleBtnApagar(ActionEvent event);
    
    @FXML
    protected abstract void tableMouseClicked(MouseEvent event);
}
