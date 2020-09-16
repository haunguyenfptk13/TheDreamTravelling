<%-- 
    Document   : login
    Created on : Jun 7, 2020, 5:28:37 PM
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
        <title>Login Page</title>
    </head>
    <body>
        <div class="login-form">
            <form action="login" method="POST">
                <h2 class="text-center">Login</h2>
                <c:set var="errors" value="${requestScope.CREATEERROR}" />
                <div class="form-group">
                    <input type="text" name="txtUsername" value="${param.txtUsername}" class="form-control" placeholder="Enter username"/><br/>
                    <c:if test="${not empty errors.usernameLengthErr}">
                        <font color="red">
                        ${errors.usernameLengthErr}
                        </font><br/>
                    </c:if>
                    <c:if test="${not empty errors.usernameNotExist}">
                        <font color="red">
                        ${errors.usernameNotExist}  
                        </font><br/>
                    </c:if>
                </div>
                <div class="form-group">
                    <input type="password" name="txtPassword" value="" class="form-control" placeholder="Enter password"/><br/>
                    <c:if test="${not empty errors.passwordLengthErr}">
                        <font color="red">
                        ${errors.passwordLengthErr}
                        </font><br/>
                    </c:if>
                    <c:if test="${not empty errors.passwordIncorrect}">
                        <font color="red">
                        ${errors.passwordIncorrect}
                        </font><br/>
                    </c:if>
                </div>
                <div class="form-group">
                    <input type="submit" value="Login" class="btn btn-primary btn-block" style="color: black"/>
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
