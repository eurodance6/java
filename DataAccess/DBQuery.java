/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import java.util.ArrayList;

/**
 *
 * @author franc
 */
public abstract class DBQuery {
    
    protected static final boolean DEBUG = true;
    
    protected final ArrayList<String> conditions = new ArrayList<>();
    
    public abstract String formatAttribute(String attr);
    
    public DBQuery addCondition(String cond) {
        if(cond!=null) conditions.add(cond);
        return this;
    }
    
    public DBQuery addConditions(String... conds) {
        for(String cond:conds) addCondition(cond);
        return this;
    }
    
    public boolean hasCondition(String cond) {
        return conditions.contains(cond);
    }
    
    
}
