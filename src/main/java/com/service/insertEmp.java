
package com.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.employee;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.util.dbConnect;

public class insertEmp {
	
Connection conn = null;
	
	public void addEmployeeService(employee employee)
	{
		//Connect database
		conn = (Connection) dbConnect.conn();
		
		try {
			String query1 = "insert into employee(id,name,nic,bdate,contact,address,jdate) values(?,?,?,?,?,?,?)";
		    PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query1);
		    
		    int s = employee.getId();
		    
		    ps.setInt(1, employee.getId());
		    ps.setString(2, employee.getName());
		    ps.setString(3, employee.getNic());
		   
		    ps.setString(6, employee.getBdate());
		  
		    ps.setInt(8, employee.getContact());
		   
		    ps.setString(10, employee.getAddress());
		 
		    ps.setString(12, employee.getJoindate());
		 
			
			ps.executeUpdate();
			ResultSet rs = ps.executeQuery();
			System.out.println("Successfuly added employee");
			conn.close();
			System.out.println("Disconnected from database");

		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
