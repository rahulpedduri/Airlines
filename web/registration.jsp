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
            \$(document).ready(function(){
            \$("#formreg").validate({
                rules: {
                    firstname: "required",
                    lastname: "required"
                    }
                });
              });
        </script>
    </head>
    <body>
        <h3 class="error reg">${message["registration.fail"]}</h3>
        <form name ="formreg" id ="form" action="reg" method="POST" onSubmit="return requiredcheck()">
             
             <div>
             <label for="firstname">First Name</label>
             <em>*</em><input type="text" name="firstname" value="${param["firstname"]}"/>
             </div>
            
             <div> 
             <label for="lastname">Last Name</label>
             <em>*</em><input type="text" name="lastname" value="${param["lastname"]}"/>
             </div>
            
            <div>
            <label for="email">Email</label>
            <em>*</em><input type="text" name="email" value="${param["email"]}"/>
            </div>
            
            <div>
            <label for="phone">Phone</label>
            <input type="text" name="phone" value="${param["phone"]}"/>
            </div>
            
            <div>
            <label for="username">Username</label>
            <em>*</em><input type="text" name="username" value="${param["username"]}"/>
            </div>
            
            <div>
            <label for="password">Password</label>
            <em>*</em><input type="password" name="password"/>
            </div>
            
            <div>
            <label for="password1">Retype Password</label>
            <em>*</em><input type="password" name="password1"/>
            </div>
            
            
            <div>
            <label for="account_no">Account Number</label>
           <em>*</em><input type="text" name="account_no" value="${param["account_no"]}"/>
            </div>
            
            <div>
            <label for="holder_name">Account Holder Name</label>
            <em>*</em><input type="text" name="holder_name" value="${param["holder_name"]}"/>
            </div>
            
            <div>
            <label for="routing_no">Routing Number</label>
            <em>*</em><input type="text" name="routing_no" value="${param["routing_no"]}"/>
            </div>
            
            <div class ="submit">
            <input type="submit" name="registration_submit" value="Submit"/>
            </div>
        </form>
        
    </body>
</html>
