
package com.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.model.ViewClass;
import com.util.dbConnect;

public class StockViewService {
	Connection conn = null;
	public ArrayList StockViewDisplay(ViewClass view)throws ClassNotFoundException, SQLException {
		conn = (Connection)dbConnect.conn();
		String query1 = "SELECT * FROM stockitem where type = 'Stock-item'";
		String st = view.getSt();
		ArrayList al = null;
		ArrayList listView = new ArrayList();
		
		Statement sta = (Statement) conn.createStatement();
		ResultSet  rs = sta.executeQuery(query1);
		
			while (rs.next()) {
            al = new ArrayList();

            al.add(rs.getString(1));
            al.add(rs.getString(2));
            al.add(rs.getString(3));
            al.add(rs.getString(4));
            al.add(rs.getString(5));
            al.add(rs.getString(6));
            al.add(rs.getString(7));
            al.add(rs.getString(8));


            listView.add(al);
			}
			
		conn.close();
		System.out.println("Disconnected from database");
		
		return listView;
		
	}

}
