/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Codeiro
 */
public class GlobalObject {
    
    String varPK;
    String var1;
    String var2;
    String var3;
    String var4;
    String var5;
    String var6;
    String var7;
    String var8;
    String var9;
    String var10;
    String varFK;
    
    private ArrayList<String> allVars = new ArrayList<>();
    
    protected GlobalObject()
    {
        initializeGlobalObject();
    }

    protected void setVarPK(String varPK) {
        this.varPK = varPK;
    }

    protected void setVar1(String var1) {
        this.var1 = var1;
    }

    protected void setVar2(String var2) {
        this.var2 = var2;
    }

    protected void setVar3(String var3) {
        this.var3 = var3;
    }

    protected void setVar4(String var4) {
        this.var4 = var4;
    }

    protected void setVar5(String var5) {
        this.var5 = var5;
    }

    protected void setVar6(String var6) {
        this.var6 = var6;
    }

    protected void setVar7(String var7) {
        this.var7 = var7;
    }

    protected void setVar8(String var8) {
        this.var8 = var8;
    }

    protected void setVar9(String var9) {
        this.var9 = var9;
    }

    protected void setVar10(String var10) {
        this.var10 = var10;
    }

    protected void setVarFK(String varFK) {
        this.varFK = varFK;
    }

    public ArrayList<String> getAllVars() {
        return allVars;
    }
    
    public void setAllVars() {
        setEmptyVars();
    }
    
    private void setEmptyVars(){
        setVarPK("");
        setVar1("");
        setVar2("");
        setVar3("");
        setVar4("");
        setVar5("");
        setVar6("");
        setVar7("");
        setVar8("");
        setVar9("");
        setVar10("");
        setVarFK("");
    }
    
    private void setAllVarsList(){
        allVars.add(varPK);
        allVars.add(var1);
        allVars.add(var2);
        allVars.add(var3);
        allVars.add(var4);
        allVars.add(var5);
        allVars.add(var6);
        allVars.add(var7);
        allVars.add(var8);
        allVars.add(var9);
        allVars.add(var10);
        allVars.add(varFK);
    }

    private void initializeGlobalObject() 
    {
        setAllVars();
        setAllVarsList();
    }
}
