
package com.model;

	import java.io.Serializable;
	import javax.persistence.*;
	import javax.validation.constraints.NotNull;
	import javax.validation.constraints.Past;
	import javax.validation.constraints.Size;

	import org.hibernate.validator.constraints.Email;
	import org.hibernate.validator.constraints.NotEmpty;
	import org.springframework.format.annotation.DateTimeFormat;

	import java.util.ArrayList;
	import java.util.Date;
	import java.util.List;


	/**
	 * The persistent class for the student database table.
	 * 
	 */
	//@Entity
	//@NamedQuery(name="Student.findAll", query="SELECT s FROM Student s")
	public class employee implements Serializable {
		private static final long serialVersionUID = 1L;

		
		@NotEmpty
		private String address;
		
		@NotEmpty
		private String nic;

		//@DateTimeFormat(pattern="yyyy-MM-dd")
	    @NotNull
		private String date;
		
		

		@NotEmpty
		@Size(min=3, max=30)
		private String name;

		@Id
		@NotEmpty
		private int id;

		@Size(min=10, max=10)
		@NotEmpty
		private int contact;
		
	

		@NotEmpty
		private String bdate;

		public String getBdate() {
			return bdate;
		}

		public void setBdate(String bdate) {
			this.bdate = bdate;
		}

		

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getNic() {
			return nic;
		}

		public void setNic(String nic) {
			this.nic = nic;
		}

		public String getJoindate() {
			return date;
		}

		public void setJoindate(String date) {
			this.date = date;
		}

		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getContact() {
			return contact;
		}

		public void setContact(int contact) {
			this.contact = contact;
		}






	


}
