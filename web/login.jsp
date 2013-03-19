<%-- 
    Document   : login
    Created on : Jan 31, 2013, 12:50:01 PM
    Author     : Phani Rahul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" type="text/css" href="resources/css/style.css">
        <script type="text/javascript" src="resources/scripts/validation.js"></script>
    </head>
    <body>
        
        <h3 class="error login">${message["login.fail"]}</h3>
        <form action="login" method="POST">
            
            <div>
            <label for="username">Username</label>
            <input type="text" name="username" class="required" value="${param["username"]}"/>
            </div>

            <div>
            <label for="password">Password</label>
            <input type="password" name="password" class="required"/>
            </div>

            
            <div class="submit">
            <input type="submit" name="login_submit" value="Login" />
            <a href="registration.jsp" title="Not Registered?"> Register </a>
            </div>
            <h3 class="error other">${message["login.unknown"]}</h3> 
        </form>
        

    </body>
</html>
