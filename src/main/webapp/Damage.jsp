<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="org.eclipse.jdt.internal.compiler.codegen.ObjectCache"%>
<%@page import="java.io.ObjectStreamException"%>
<%@ page import="java.util.*" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="com.model.StockItem" %>
<%@ page import="com.model.user" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="resources/css/bootstrap.css">
    <link rel="stylesheet" href="resources/css/all.css">
    <link rel="stylesheet" href="resources/css/mystyle.css">
    <link rel="stylesheet" href="resources/css/fonts.css">
    
    <script src="resources/js/jQuery.js"></script>
    <script src="resources/js/bootstrap.js"></script>
    <script src="resources/js/properjs.js"></script>
    <script src="resources/js/bootstrao.awsom.font.js"></script>
    <script src="resources/js/bootstrao.awsom.font.bundle.js"></script>
    <script src="resources/js/jquery3.3.1.min.js"></script>
    <script src="resources/js/bootstrap4.1.0.min.js"></script>
    
    <title>View Damage Item Information</title>
    
    <style>
	    #createbutton{
	        width: 150px;
	    }
	    #input1{
	        width:200px;
	    }
	    .input2{
	        width:400px;
	        padding-right: 50px;
	    }
	    input[type=text]{
	        width:200px;
	    }
	    .table{
	        border:2px solid #9fa8a3;
	        width: 100%;
	        
	    }
    </style>
    </head>
    <body>
        <div class="contaianer">
       <div class="row my-row1">
        <% user currentUser = (user)(session.getAttribute("currentSessionUser"));%>
            <div class="col-sm-10 "><h1 class="banner"><i class="fas fa-screwdriver"></i>MISUKE <i class="fas fa-gavel"></i> LIGHTNING<i class="fas fa-shipping-fast"></i></h1>
            
            	
            </div>
            <div class="col-sm-2 my-logout">
                    <div class="dropdown">
                            <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" id="button1">
                                    <i class="fas fa-user"></i>
                            </button>
                                <div class="dropdown-menu">
                                    
                                    <a class="dropdown-item" href="logOut" class="dropdown-logout"><i class="fas fa-sign-out-alt"></i>&ensp;Logout</a>
                                </div>
                                <br>
                                Welcome <%= currentUser.getUname() + " !!"%>	
                    </div>
            </div>
        </div>
        <div class="row my-row6">
                <div class="col-sm-2  my-col2 ">
                       <div class="row"><a href="index1.jsp" target="_self" class="link1"><i class="fas fa-server"></i>&ensp; DashBoard</a></div>
                       <div class="row"><a href="user.jsp" target="_self" class="link1"><i class="fas fa-user"></i>&ensp;User</a></div>
                       <div class="row"><a href="StockItemManagement.jsp" target="_self" class="link2"><i class="fas fa-database"></i>&ensp;Stock</a></div>
                       
                       <div class="row"><a href="Employee.jsp" target="_self" class="link1"><i class="fas fa-users"></i>&ensp;Employee</a></div>
                      
                      <div class="row"><a href="index2.jsp" target="_self" class="link1"><i class="fas fa-clipboard-list"></i>&ensp;Bill</a></div>
                       
                </div>
                <div class="col-sm-10  my-col3 ">
                        <br>
                    <h1 style="text-align: center">Damage Item Information</h1>
                    <br>
                    <table class="table table-striped">
					  <thead>
						<tr>
						  <th scope="col">No</th>
						  <th scope="col">Item ID</th>
						  <th scope="col">Name</th>
						
						  <th scope="col">Description</th>
						  <th scope="col">Purchase Price</th>
						  <th scope="col">Amount</th>
						  <th scope="col">Type</th>
						  <th scope="col">Cost</th>
						  <th scope="col"></th>
						  <th scope="col"></th>
						</tr>
					  </thead>
					  <tbody>
						<%
						  
	                      int count = 0;
	                      //StockItem s = new StockItem();
	                      //long id = s.getId();
							if (request.getAttribute("result") != null) {
								ArrayList al = (ArrayList) request.getAttribute("result");
	                            System.out.println(al);
								Iterator itr = al.iterator();
								while (itr.hasNext()) {          
									count++;
									ArrayList list = (ArrayList)itr.next();
                        %>
	                        <form action="readTable" method="post" >
								<tr>
								  <th scope="row"><%=count %></th>
								  <td><input type="hidden" name="Id" value="<%=list.get(0)%>"><%=list.get(0) %></td>
								  <td><input type="hidden" name="name" value="<%=list.get(1)%>"><%=list.get(1) %></td>
								  
								  <td><input type="hidden" name="description" value="<%=list.get(3)%>"><%=list.get(3) %></td>
								  <td><input type="hidden" name="purchaseprice" value="<%=list.get(4)%>"><%=list.get(4) %></td>  
								  <td><input type="hidden" name="amount" value="<%=list.get(5)%>"><%=list.get(5) %></td>
								  <td><input type="hidden" name="type" value="<%=list.get(6)%>"><%=list.get(6) %></td>
								  <td><input type="hidden" name="cost" value="<%=list.get(7)%>"><%=list.get(7) %></td>
								  <td><button type="Update" class="btn btn-btncolorPaple" id="createbutton" name="st" value="Update">Update</button>
								  	<input type="hidden" name="Id" value="<%=list.get(0)%>">
								  	<input type="hidden" name="type" value="<%=list.get(6)%>">
								  </td>
								  <td><button type="Delete" class="btn btn-btncolorbrown" id="createbutton" name="st" value="Delete">Deleted</button>
								  	<input type="hidden" name="Id" value="<%=list.get(0)%>">
								  	<input type="hidden" name="type" value="<%=list.get(6)%>">
								  </td>
								  
								</tr>
							</form>
						<%
								}
							}						
							else if (count == 0) {
						%>
							<tr>
							<td colspan=5 align="center"style="background-color:#F4F6F6"><b>No Record Found in database</b></td>
							</tr>
					<%    
							}
					%>
					  </tbody>
					</table>
					<form align="center" action="damage" method="post">
						<button type="submit" class="btn"  id="createbutton" name="st" Value="Add">Add</button>
						<button type="submit" class="btn" id="createbutton" name="st" Value="Stock">Stock</button>
						<button type="submit" class="btn" id="createbutton" name="st" Value="NonAvailable">Non-Available</button>
						<button type="submit" class="btn" id="createbutton" name="st" Value="Reports">Reports</button>
						<button type="submit" class="btn" id="createbutton" name="st" Value="Search">Search</button>
						
					</form>
					<br><br>
					 <form action="home" method="post">
									<button type="submit" class="btn btn-primary btn-block" name="st" Value="home">Home</button>
					</form>
                </div> 
        </div>
        
        <div class="row my-row2">
                <div class="col-lg-12 "><Footer>Group 1 @ All Right recerved</Footer></div>
        </div>
    </div>

</body>
</html>