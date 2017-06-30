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
public class DBSelector extends DBQuery {
    
    private boolean isJoin;
    private String mainTable;
    private String tableName;
    private final ArrayList<String> columns = new ArrayList<>();
    private String tail;
    
    public DBSelector(String tableName) {
        this(tableName, tableName, false);
    }
    
    public DBSelector(String tableName, String mainTable, boolean isJoin) {
        this.tableName = tableName;
        this.mainTable = mainTable;
        this.isJoin = isJoin;
    }
    
    public String getMainTable() {
        return mainTable;
    }
    
    public boolean isJoin() {
        return isJoin;
    }
    
    public DBSelector addColumn(String colName) {
        columns.add(colName);
        return this;
    }
    
    public DBSelector setTail(String tail) {
        this.tail = tail;
        return this;
    }
    
    public DBSelector addColumns(String... cols) {
        for(String col: cols) addColumn(col);
        return this;
    }
    
    public boolean hasColumn(String colName) {
        return columns.contains(colName);
    }
    
    @Override
    public String formatAttribute(String colName) {
        return (isJoin()?mainTable + "." + colName:colName);
    }
    
    public String generateQuery() {
        String sql = "SELECT ";
        if(!columns.isEmpty()) {
            for(int i=0;i<columns.size();i++) {
                String col = columns.get(i);
                if(i>0) sql+=", ";
                sql += col;
            }
        } else sql += "*";
        
        sql += " FROM " + tableName;
        
        if(!conditions.isEmpty()) {
            sql += " WHERE ";
            for(int i=0;i<conditions.size();i++) {
                if(i>0) sql += " AND ";
                sql += conditions.get(i);
            }
        }
        
        if(tail!=null) sql += " " + tail;
        if(DEBUG) System.out.println(sql);
        return sql;
    }
    
}