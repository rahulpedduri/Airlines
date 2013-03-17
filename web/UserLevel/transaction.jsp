<%-- 
    Document   : transaction
    Created on : Feb 12, 2013, 4:02:06 PM
    Author     : Phani Rahul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Transaction Page</title>
        <link href="../resources/css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        
        <form name ="form" action="reg" method="POST">
        <a href="../login.jsp"><button type="button" value="logout" name="logout" align="right">Logout</button></a>    
        <table border="1" cellpadding="2" align="center" bgcolor="#FFFAF0">
            <thead>
                <tr>
                    <th>Flight Number</th>
                    <th>Flight Date</th>
                    <th>Departure</th>
                    <th>Arrival</th>
                    <th>Stops</th>
                    <th>Cost</th>
                   
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>UA83</td>
                    <td>20 Feb</td>
                    <td>Charlotte</td>
                    <td>New Ark</td>
                    <td>0</td>
                    <td>$125</td>
                  
                </tr>
            </tbody>
        </table>
                
        
             
             <div>
             <label for="Source">Account Holder Name</label>
             <input type="text" name="name" class ="required"/>
             </div>
            
             <div> 
             <label for="Destination">Routing Number</label>
             <input type="text" name="dest" class ="required"/>
             </div>
            
            <div>
            <label for="date">Account Number</label>
            <input type="text"  name="number" class ="required"/>
            </div>
            
            <a href="transaction_confirmation.jsp"><button type="button" value="confirm" name="confirm">confirm</button></a>
            <a href="flight_search_query.jsp"><button type="button" value="cancel" name="cancel">cancel</button></a>
            
            
        </form>
        
    </body>
</html>
