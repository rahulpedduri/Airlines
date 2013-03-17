<%-- 
    Document   : registration
    Created on : Feb 5, 2013, 4:36:39 PM
    Author     : Phani Rahul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration Page</title>
        
        <link href="resources/css/style.css" rel="stylesheet" type="text/css" ></link>
        <script type="text/javascript" src="resources/scripts/validateform.js"></script>
        <script type="text/javascript" src="resources/jquery/jquery-ui-1.10.0.custom/js/jquery-1.9.0.js" ></script>
        <script type="text/javascript" src="resources/jquery/jquery-ui-1.10.0.custom/js/jquery-ui-1.10.0.custom.js" ></script>
        <script>
            $(document).ready(function(){
            $("#formreg").validate({
                rules: {
                    firstname: "required",
                    lastname: "required"
                    }
                });
              });
        </script>
    </head>
    <body>
        <form name ="formreg" id ="form" action="reg" method="POST" onSubmit="return requiredcheck()">
             
             <div>
             <label for="firstname">First Name</label>
             <em>*</em><input type="text" name="firstname"/>
             </div>
            
             <div> 
             <label for="lastname">Last Name</label>
             <em>*</em><input type="text" name="lastname"/>
             </div>
            
            <div>
            <label for="email">Email</label>
            <em>*</em><input type="text" name="email"/>
            </div>
            
            <div>
            <label for="phone">Phone</label>
            <input type="text" name="phone"/>
            </div>
            
            <div>
            <label for="username">Username</label>
            <em>*</em><input type="text" name="username"/>
            </div>
            
            <div>
            <label for="password">Password</label>
            <em>*</em><input type="password" name="password"/>
            </div>
            
            <div>
            <label for="password1">Retype Password</label>
            <em>*</em><input type="password" name="password1"/>
            </div>
            
            <div class ="submit">
            <input type="submit" name="registration_submit" value="Submit"/>
            </div>
        </form>
        
    </body>
</html>
