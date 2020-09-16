<%-- 
    Document   : createNewTour
    Created on : Jun 19, 2020, 2:16:31 AM
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
        <title>Create New Tour Page</title>
    </head>
    <body>
        <c:if test="${empty ADMIN}">
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
        <div class="login-form">
            <form action="createNewTour" method="POST">
                <h2 class="text-center">Create New Tour</h2>
                <c:set var="errors" value="${requestScope.CREATEERROR}"/>
                <div class="form-group">
                    <input type="text" name="txtTourID" value="" class="form-control" placeholder="Enter Tour ID"/>
                    <c:if test="${not empty errors.tourIDLengthErr}">
                        <font color="red">
                        ${errors.tourIDLengthErr}
                        </font>
                    </c:if>
                    <c:if test="${not empty errors.tourIDIsExisted}">
                        <font color="red">
                        ${errors.tourIDIsExisted}
                        </font>
                    </c:if>
                </div>
                <div class="form-group">
                    <input type="text" name="txtTourName" value="" class="form-control" placeholder="Enter Tour Name"/>
                    <c:if test="${not empty errors.tourNameLengthErr}">
                        <font color="red">
                        ${errors.tourNameLengthErr}
                        </font>
                    </c:if>
                </div>
                <c:set var="fromPlaceList" value="${sessionScope.FROMPLACELIST}" />
                <div class="form-group">
                    <c:if test="${not empty fromPlaceList}">
                        From Place* <select name="cboFromPlace" class="form-control">
                            <c:forEach var="fromPlaceDto" items="${fromPlaceList}">
                                <option value="${fromPlaceDto.fromPlaceID}">${fromPlaceDto.fromPlaceName}</option>
                            </c:forEach>
                        </select>
                    </c:if>
                </div>
                <c:set var="toPlaceList" value="${sessionScope.TOPLACELIST}" />
                <div class="form-group">
                    <c:if test="${not empty toPlaceList}">
                        To Place* <select name="cboToPlace" class="form-control">
                            <c:forEach var="toPlaceDto" items="${toPlaceList}">
                                <option value="${toPlaceDto.toPlaceID}">${toPlaceDto.toPlaceName}</option>
                            </c:forEach>
                        </select>
                    </c:if>
                </div>
                <div class="form-group">
                    From Date* <input type="date" name="txtFromDate" value="" class="form-control"/>
                    <c:if test="${not empty errors.fromDateLengthErr}">
                        <font color="red">
                        ${errors.fromDateLengthErr}
                        </font>
                    </c:if>
                </div>
                <div class="form-group">
                    To Date* <input type="date" name="txtToDate" value="" class="form-control"/>
                    <c:if test="${not empty errors.toDateLengthErr}">
                        <font color="red">
                        ${errors.toDateLengthErr}
                        </font>
                    </c:if>
                </div>
                <div class="form-group">
                    <input type="text" name="txtPrice" value="" class="form-control" placeholder="Enter Price"/>
                    <c:if test="${not empty errors.priceFormatErr}">
                        <font color="red">
                        ${errors.priceFormatErr}
                        </font>
                    </c:if>
                </div>
                <div class="form-group">
                    <input type="text" name="txtQuota" value="" class="form-control" placeholder="Enter Quota"/>
                    <c:if test="${not empty errors.quotaFormatErr}">
                        <font color="red">
                        ${errors.quotaFormatErr}
                        </font>
                    </c:if>
                </div>
                <div class="form-group">
                    Image* <input type="file" name="txtImage" value="" class="form-control"/>
                </div>
                <div class="form-group">
                    <input type="submit" value="Create New Tour" class="btn btn-primary btn-block" style="color: black"/>
                </div>
                <div class="form-group">
                    <input type="reset" value="Reset" class="btn btn-primary btn-block" style="color: black"/>
                </div>
                <div class="form-group">
                    <input type="button" value="Cancel" onclick="window.location.href = 'InitResourceSearchServlet'" class="btn btn-primary btn-block" style="color: black"/>
                </div>
            </form>
        </div>
        <script src="js/bootstrap.min.js"></script>
        <script src="jquery/jquery-3.3.1.min.js"></script>
    </body>
</html>
