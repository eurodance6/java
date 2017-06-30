/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author franc
 */
public class DBUpdater extends DBQuery {
    
    private final String tableName;
    private final String setter;
    
    public DBUpdater(String tableName, String setter) {
        this.tableName = tableName;
        this.setter = setter;
    }
    
    public String generateQuery() throws SQLException {
        String sql = "UPDATE " + tableName + " SET " + setter + " WHERE ";
        if(conditions.isEmpty()) {
            throw new SQLException("No conditions in where clause");
        }
        
        for(int i=0;i<conditions.size();i++) {
            if(i>0) sql += " AND ";
            sql += conditions.get(i);
        }
        if(DEBUG)
            System.out.println(sql);
        return sql;
    }

    @Override
    public String formatAttribute(String attr) {
        return attr;
    }

}
