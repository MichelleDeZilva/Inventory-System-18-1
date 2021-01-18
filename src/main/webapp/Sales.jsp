

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.model.user" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="resources/css/bootstrap.css">
    <link rel="stylesheet" href="resources/css/all.css">
    <link rel="stylesheet" href="resources/css/mystyle.css">

    <link rel="stylesheet" href="css/fonts.css">
    <script src="js/jQuery.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="js/properjs.js"></script>
    <script src="js/bootstrao.awsom.font.js"></script>
    <script src="js/bootstrao.awsom.font.bundle.js"></script>
    <script src="js/jquery3.3.1.min.js"></script>
    <script src="js/bootstrap4.1.0.min.js"></script>
    <title>Sales Report</title>
    <style>
        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }
        #input1{
            width:350px;
        }
        #input2{
            width:200px;
            padding-right: 50px;
        }
        #table1 {
            border-collapse: collapse;
        }
    </style>
    <title>Sales Report</title>
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
                       <div class="row"><a href="StockItemManagement.jsp" target="_self" class="link1"><i class="fas fa-database"></i>&ensp;Stock</a></div>
                      
                       <div class="row"><a href="Employee.jsp" target="_self" class="link1"><i class="fas fa-users"></i>&ensp;Employee</a></div>
                      
                      <div class="row"><a href="index2.jsp" target="_self" class="link2"><i class="fas fa-clipboard-list"></i>&ensp;Bill</a></div>
                      
                </div>
            <div class="col-sm-10  my-col3 ">
                <h1 class="text-center display-3">Sales Report</h1>
                <br>
                <input type="text" placeholder="Search the Date.." name="search"><button type="submit"><i class="fa fa-search"></i></button>
                <br><br>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="input1">Date</span>
                    </div>
                    <input type="text" class="form-control" placeholder="" id="input2">
                </div>
                    <div  class="input-group mb-3">
                        <table id="table1">
                            <tr>
                                <th>Item Code</th>
                                <th style="width: 300px">Description</th>
                                <th>Quentity</th>
                                <th style="width: 300px">Price</th>
                            </tr>
                            <tr>
                                <td style="height: 330px"></td>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>
                        </table>
                    </div>


                <div class="row my-row2">
                    <div class="col-lg-12 "><Footer>Group 1 @ All Right received</Footer></div>
                </div>
            </div>
                </head>
<body>

</body>
</html>