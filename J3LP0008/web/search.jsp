<%-- 
    Document   : search
    Created on : Jun 7, 2020, 7:28:37 PM
    Author     : msi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">-->
        <link rel="stylesheet" href="css/bootstrap.css" type="text/css">
        <link rel="stylesheet" href="css/style.css" >
        <title>Search Page</title>
    </head>
    <body>
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
                            <c:if test="${empty sessionScope.TOTALAMOUNT}">
                                <c:set var="TOTALAMOUNT" value="0" scope="session" />
                            </c:if>
                            <c:if test="${empty sessionScope.ADMIN}">
                                <a class="nav-link" href="viewCartPage">Cart(${sessionScope.TOTALAMOUNT})</a>
                            </c:if>
                        </li>
                        <li class="nav-item">
                            <c:if test="${not empty sessionScope.ADMIN}">
                                <a class="nav-link" href="createNewTourPage">Create New Tour</a>
                            </c:if>
                        </li>
                    </ul>
                </div>  
            </div>
        </nav>
        <div class="container-fluid" style="margin: 20px 0 20px 0">
            <form action="search">
                <c:set var="fromPlaceList" value="${sessionScope.FROMPLACELIST}" />
                <c:if test="${not empty fromPlaceList}">
                    <label style="margin: 0 0 0 160px">From Place*</label>
                    <select name="cboFromPlace">
                        <option value="">--From Place-</option>
                        <c:forEach var="fromPlaceDto" items="${fromPlaceList}">
                            <c:if test="${fromPlaceDto.fromPlaceName == param.cboFromPlace}">
                                <option selected="selected">${fromPlaceDto.fromPlaceName}</option>
                            </c:if>
                            <c:if test="${fromPlaceDto.fromPlaceName != param.cboFromPlace}">
                                <option>${fromPlaceDto.fromPlaceName}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </c:if>
                <c:set var="toPlaceList" value="${sessionScope.TOPLACELIST}" />
                <c:if test="${not empty toPlaceList}">
                    <label style="margin: 0 0 0 15px">To Place*</label>
                    <select name="cboToPlace">
                        <option value="">--To Place-</option>
                        <c:forEach var="toPlaceDto" items="${toPlaceList}">
                            <c:if test="${toPlaceDto.toPlaceName == param.cboToPlace}">
                                <option selected="selected">${toPlaceDto.toPlaceName}</option>
                            </c:if>
                            <c:if test="${toPlaceDto.toPlaceName != param.cboToPlace}">
                                <option>${toPlaceDto.toPlaceName}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </c:if>
                <c:set var="priceLevelList" value="${sessionScope.PRICELIST}" />
                <c:if test="${not empty priceLevelList}">
                    <label style="margin: 0 0 0 15px">Price*</label>
                    <select name="cboPriceLevel">
                        <option value="">--Price--</option>
                        <c:forEach var="priceLevelDto" items="${priceLevelList}" varStatus="counter">
                            <c:if test="${priceLevelDto.priceLevel == param.cboPriceLevel}">
                                <option value="${counter.count}" selected="selected">${priceLevelDto.priceLevelName}</option>
                            </c:if>
                            <c:if test="${priceLevelDto.priceLevel != param.cboPriceLevel}">
                                <option value="${counter.count}">${priceLevelDto.priceLevelName}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </c:if>
                <label style="margin: 0 0 0 15px">Date From*</label>
                <input type="date" name="txtDateFrom" value=""/>
                <label style="margin: 0 0 0 15px">Date To*</label>
                <input type="date" name="txtDateTo" value=""/>
                <input type="submit" value="Search" />
                <input type="hidden" name="txtPageNo" value="1" />
            </form>
        </div>
        <c:if test="${not empty param.cboFromPlace || not empty param.cboPriceLevel 
                      || not empty param.txtDateFrom || not empty param.txtDateTo || not empty param.cboToPlace}">
            <c:if test="${not empty requestScope.LISTTOURPAGE}">
                <c:forEach var="dto" items="${requestScope.LISTTOURPAGE}" varStatus="counter">
                    <form action="addToCart">
                        <div class="container">
                            <div class="row text-left">
                                <div class="col-6">
                                    <div class="pos-relative">
                                        <img src="${dto.image}" width="100%"/>
                                        <input type="hidden" name="txtImage" value="${dto.image}" />
                                    </div>
                                </div>
                                <div class="col-3">
                                    <label>Tour Code: ${dto.tourID}</label>
                                    <input type="hidden" name="txtTourID" value="${dto.tourID}" /><br/>
                                    <label>Tour Name: ${dto.tourName}</label>
                                    <input type="hidden" name="txtTourName" value="${dto.tourName}" /><br/>
                                    <label>Tour Price: ${dto.price}</label>
                                    <input type="hidden" name="txtPrice" value="${dto.price}" /><br/>
                                    <label>From Date: ${dto.fromDate}</label>
                                    <input type="hidden" name="txtFromDate" value="${dto.fromDate}" /><br/>
                                    <label>To Date: ${dto.toDate}</label>
                                    <input type="hidden" name="txtToDate" value="${dto.toDate}" /><br/>
                                </div>
                                <div class="col-3">
                                    <c:forEach var="fromPlaceDto" items="${fromPlaceList}">
                                        <c:if test="${fromPlaceDto.fromPlaceID == dto.fromPlaceID}">
                                            <label>From Place: ${fromPlaceDto.fromPlaceName}</label>
                                        </c:if>
                                    </c:forEach>
                                    <input type="hidden" name="txtFromPlaceID" value="${dto.fromPlaceID}" /><br/>
                                    <c:forEach var="toPlaceDto" items="${toPlaceList}">
                                        <c:if test="${toPlaceDto.toPlaceID == dto.toPlaceID}">
                                            <label>To Place: ${toPlaceDto.toPlaceName}</label>
                                        </c:if>
                                    </c:forEach>
                                    <input type="hidden" name="txtToPlaceID" value="${dto.toPlaceID}" /><br/>
                                    <c:if test="${empty sessionScope.ADMIN}">
                                        <input class="btn btn-primary" type="submit" value="Add To Cart" style="color: black;"/>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </form>
                </c:forEach>           
            </c:if>
            <c:if test="${empty requestScope.LISTTOURPAGE}">
                <div class="container">
                    <h2 class="text-center">No record is matched !!!!</h2>
                </div>
            </c:if>
            <div class="container text-center" style="margin-bottom: 20px; margin-top: 20px">
                <c:if test="${requestScope.PAGENUMBER > 1}">
                    <c:forEach begin="1" end="${requestScope.PAGENUMBER}" var="i">
                        <a href="search?cboFromPlace=${param.cboFromPlace}&cboToPlace=${param.cboToPlace}&cboPriceLevel=${param.cboPriceLevel}&txtDateFrom=${param.txtDateFrom}&txtDateTo=${param.txtDateTo}&txtPageNo=${i}">${i}</a>
                    </c:forEach>
                </c:if>
            </div>
        </c:if>
        <script src="js/bootstrap.min.js"></script>
        <script src="jquery/jquery-3.3.1.min.js"></script>
    </body>
</html>




