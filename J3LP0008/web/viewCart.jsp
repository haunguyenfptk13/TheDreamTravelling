<%-- 
    Document   : viewCart
    Created on : Jun 9, 2020, 3:26:16 PM
    Author     : msi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/bootstrap.css" >
        <link rel="stylesheet" href="css/style.css" >
        <title>View Cart Page</title>
        <script>
            function changeAmount() {
                document.getElementById("update").click();
            }

            function removeConfirm() {
                var result = confirm("Do you want to continue?");
                if (result) {
                    document.getElementById("btn_Remove").type = 'submit';
                    document.getElementById("btn_Remove").onclick;
                }
            }
        </script>
    </head>
    <body>
        <c:if test="${empty CUSTOMER}">
            <c:redirect url="searchPage" />
        </c:if>
        <nav class="navbar navbar-expand-md navbar-light bg-light">
            <div class="container-fluid">
                <a class="navbar-branch" href="#" style="margin-right: 30px; margin-left: 160px">
                    <img src="images/fptlogo2.png" width="311" height="162" alt="fptlogo"/>
                </a>
                <div class="collapse navbar-collapse" style="margin-right: 160px;">
                    <ul class="navbar-nav ml-auto">
                        <c:if test="${not empty sessionScope.FULLNAME}">
                            <li class="nav-item" style="margin: auto 10px">
                                Welcome, <font color="blue">${sessionScope.FULLNAME}</font>
                                <a href="logout" style="margin-left: 10px">Logout</a>
                            </li>
                        </c:if>
                        <c:if test="${empty sessionScope.FULLNAME}">
                            <li class="nav-item">
                                <a class="nav-link" href="loginPage">Login</a>
                            </li>
                        </c:if>
                        <li class="nav-item">
                            <a class="nav-link" href="searchPage">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="viewCartPage">Cart(${sessionScope.TOTALAMOUNT})</a>
                        </li>
                    </ul>
                </div>  
            </div>
        </nav>
        <div class="container" style="background-color: white !important">
            <div class="row">
                <div class="col-6 text-left">
                    <label style="font-size: 50px">Cart(${sessionScope.TOTALAMOUNT})</label>
                </div>
                <div class="col-6 text-right" style="margin: auto 0">
                    <input type="button" value="Add Tour To Cart" class="btn btn-primary" onclick="window.location.href = 'InitResourceSearchServlet'" style="color: black"/>
                </div>
            </div>
        </div>
        <c:if test="${not empty sessionScope.CART}">                
            <form action="cartDispatcher">
                <div class="container">
                    <c:forEach var="map" items="${sessionScope.CART.items}" varStatus="counter">
                        <div class="row text-left">
                            <div class="col-12" style="margin: 25px auto">
                                ${map.key.tourName}
                                <input type="hidden" name="txtTourID" value="${map.key.tourID}" />
                            </div>
                            <div class="col-2">
                                <div class="pos-relative">
                                    <img src="${map.key.image}" width="100%">                                       
                                </div>
                            </div>
                            <div class="col-3" style="margin: auto 0; margin-left: 25px">
                                <label>Tour ID: ${map.key.tourID}</label><br/>
                                <label>Price: ${map.key.price}</label><br/>
                                <label>From Date: ${map.key.fromDate}</label>
                            </div>
                            <div class="col-2" style="margin: auto 0">
                                <input type="text" name="txtAmount" value="${map.value}" onchange="changeAmount()">
                            </div>
                            <div class="col-2"style="margin: auto 0; margin-left: 25px">
                                <label>Total: ${map.key.price * map.value}</label>
                            </div>
                            <div class="col-1"style="margin: auto 0">
                                <input type="checkbox" name="chkItem" value="${map.key.tourID}"/>
                            </div>
                        </div>
                        <hr class="my-4">
                    </c:forEach> 
                    <div style="margin: 25px; text-align: right; padding-bottom: 25px">
                        <input  class="btn btn-primary" type="button" value="Remove Selected Items" name="btAction" onclick="removeConfirm()" id="btn_Remove" style="margin-right: 70px; color: black"/>
                        <input class="btn btn-primary" type="submit" value="Update" name="btAction" id="update" style="display: none"/> 
                    </div>
                </div>
                <div class="container">
                    <div class="row text-left">
                        <div class="col-6">
                            <div style="width: 100%; background-color: greenyellow; text-align: center;">
                                <h3>Discount</h3>
                            </div>
                            <div style="padding: 25px; text-align: center">
                                Discount* <input type="text" name="txtDiscount" value="" />
                                <input type="submit" value="Use" name="btAction" class="btn btn-primary" style="color: black; margin-left: 25px"/><br/> 
                                <c:if test="${not empty requestScope.ERROR1}">
                                    <font color="red">
                                    ${requestScope.ERROR1}
                                    </font><br/>
                                </c:if>
                                <c:if test="${not empty requestScope.ERROR2}">
                                    <font color="red">
                                    ${requestScope.ERROR2}
                                    </font><br/>
                                </c:if>
                            </div>
                        </div>
                        <div class="col-6">
                            <div style="width: 100%; background-color: greenyellow; text-align: center">
                                <h3>Payment Information</h3>
                            </div>
                            <div class="row">
                                <div class="col-6"><label>Total Cart:</label></div>
                                <div class="col-6 text-right"><label class="text-right">${sessionScope.TOTALCARTS}</label></div>
                            </div>
                            <br/>
                            <c:if test="${not empty sessionScope.DISCOUNTPERCENT}">
                                <div class="row">
                                    <div class="col-6"><label>Discount Code: ${sessionScope.DISCOUNTID} <a href="cancelUseDiscount">x</a></label></div>
                                    <div class="col-6 text-right"><label class="text-right">-${sessionScope.DISCOUNTPERCENT * sessionScope.TOTALCARTS/100}</label></div>
                                </div>
                            </c:if>
                            <br/>
                            <div class="row">
                                <div class="col-6"><label>Total Payment Amount:</label></div>
                                <div class="col-6 text-right"><label class="text-right">${sessionScope.TOTALCARTS * ((100 - sessionScope.DISCOUNTPERCENT)/100)}</label></div>
                                <input type="hidden" name="txtTotalPay" value="${sessionScope.TOTALCARTS * ((100 - sessionScope.DISCOUNTPERCENT)/100)}" />
                            </div>
                            <br/>
                            <div class="row">
                                <div class="col-12 text-right" style="margin-bottom: 10px;">
                                    <c:if test="${not empty sessionScope.CUSTOMER}">
                                        <input type="submit" value="Booking" name="btAction" class="btn btn-primary" style="color: black"/>
                                    </c:if>
                                </div>
                                <c:if test="${not empty requestScope.ERRORSTOCK}">
                                    <font color="red">
                                    ${requestScope.ERRORSTOCK}
                                    </font>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </c:if>
        <c:if test="${empty sessionScope.CART}">
            <div class="container">                
                <h2 class="text-center">No record is matched !!!!</h2>
            </div>
        </c:if>
        <script src="js/bootstrap.min.js"></script>
        <script src="jquery/jquery-3.3.1.min.js"></script>
    </body>
</html>
