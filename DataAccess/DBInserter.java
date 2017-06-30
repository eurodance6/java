/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import java.util.ArrayList;

/**
 *
 * @author alulab14
 */
public class DBInserter extends DBQuery {

    private final String tableName;
    private final ArrayList<String> columns = new ArrayList<>();
    private final ArrayList<String> values = new ArrayList<>();
    
    public DBInserter(String tableName) {
        this.tableName = tableName;
    }
    
    public DBInserter addColumn(String column) {
        columns.add(column);
        return this;
    }
    
    public DBInserter addColumns(String... columns) {
        for(String column: columns) addColumn(column);
        return this;
    }
    
    public boolean hasColumn(String colName) {
        return columns.contains(colName);
    }
    
    public DBInserter addValue(String value) {
        values.add(value);
        return this;
    }
    
    public DBInserter addValues(String... values) {
        for(String value: values) addValue(value);
        return this;
    }
    
    public boolean hasValue(String value) {
        return values.contains(value);
    }
    
    @Override
    public String formatAttribute(String attr) {
        return attr;
    }
    
    public String generateQuery() {
        String sql = "INSERT INTO " + tableName;
        
        if(!columns.isEmpty()) {
            sql += " (";
            for(int i=0;i<columns.size();i++) {
                String column = columns.get(i);
                if(i>0) sql += ", ";
                sql += column;
            }
            sql += ")";
        }
        
        sql += " VALUES (";
        for(int i=0;i<values.size();i++) {
            String val = values.get(i);
            if(i>0) sql += ", ";
            sql += val;
        }
        sql += ")";
        
        return sql;
    } 
    
}
