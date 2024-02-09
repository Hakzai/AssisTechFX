/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.pattern;

/**
 *
 * @author Codeiro
 */
public class Singleton {
    
    private static Singleton instance;
    
    private Singleton() {}
    
    public static synchronized Singleton getInstance()
    {
        if(null == instance)
            instance = new Singleton();
        
        return instance;
    }
}
