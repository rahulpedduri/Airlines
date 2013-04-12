<%-- 
    Document   : transaction
    Created on : Feb 12, 2013, 4:02:06 PM
    Author     : Phani Rahul
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Transaction Page</title>
        <link href="../resources/css/style.css" rel="stylesheet" type="text/css"/>
        <script src="../resources/jquery/jquery-ui-1.10.0.custom/js/jquery-1.9.0.js">
                
                function confirm_func(){  

                    var email = document.nameForm.email.value;  
                    $.ajax({  
                      type: "GET",  
                      url: "../Banking",  
                      data: "email="+email,  
                      success: function(result){  
                        alert(result);
                      }                
                    }); 
        </script>
    </head>
    <body>
        
        <form name ="bank_form" action="../Transaction" method="POST">
       <a href="Login?logout=true">Logout</a>    
        <a href="UserLevel/flight_search_query.jsp">Home</a>  
             <a href="Handle?booking_history=true">Booking History</a> 
             
        <table border="1" cellpadding="2" align="center" bgcolor="#FFFAF0">
            <thead>
                <tr>
                    <th>Flight Number</th>
                    <th>Airlines</th>
                    <th>Origin</th>
                    <th>Destination</th>
                    <th>Cost</th>
                    <th>Class</th>
                   
                </tr>
            </thead>
           
           
            <tbody>
                 <c:set var="name" value="bookings" />
                <c:forEach items="${sessionScope[name]}" var="booking">
                <tr>
                    <td>${booking.flightNumber}</td>
                    <td>${booking.flight.operator}</td>
                    <td>${booking.flight.source}</td>
                    <td>${booking.flight.destination}</td>
                    <td>${booking.flight.cost}</td>
                    <td>${booking.flight.cls}</td>
                    <%-- TODO remaining values to be populated --%>
                </tr>
                </c:forEach>
            </tbody>
        </table>
                
        
             
             <div>
             <label for="account_holder_name">Account Holder Name</label>
             <input type="text" name="account_holder_name" class ="required"/>
             </div>
            
             <div> 
             <label for="routing_number">Routing Number</label>
             <input type="text" name="routing_number" class ="required"/>
             </div>
            
            <div>
            <label for="account_number">Account Number</label>
            <input type="text"  name="account_number" class ="required"/>
            </div>
            
           <button type="submit" value="confirm" name="transaction_submit1">confirm</button>
            <a href="flight_search_query.jsp"><button type="button" value="cancel" name="cancel">Continue shopping/button></a>
            
            
        </form>
        
    </body>
</html>
