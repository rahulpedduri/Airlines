<%-- 
    Document   : booking_history
    Created on : Feb 12, 2013, 4:02:44 PM
    Author     : Phani Rahul
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Booking History</title>
        <link href="../resources/css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        
        
        <form name ="form" action="reg" method="POST">
           <a href="Login?logout=true">Logout</a>    
            <a href="UserLevel/flight_search_query.jsp">Home</a>  
             <a href="Handle?booking_history=true">Booking History</a>    
            
            <h3>Booking History</h3>
            <table border="1" cellpadding="2" align="center">
            <thead>
                <tr>
                    <th>Flight Number</th>
                    <th>Flight Date</th>
                    <th>Departure</th>
                    <th>Arrival</th>
                    <th>Stops</th>
                    <th>Cost</th>
                    <th>Booking ID</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="booking" items="${booking_history}">
                <tr>
                    <td>${booking.bookingId}</td>
                    <td></td>
                    <td></td>
                    <td></td>
		    <td></td>
		    <td>${booking.totalCost}</td>
                    <td>${booking.bookingId}</td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
        
            <div class="submit">
            <a href="UserLevel/flight_search_query.jsp"><button type="button" value="home" name="home">Home</button></a>
            </div>
            
        </form>
    </body>
</html>
