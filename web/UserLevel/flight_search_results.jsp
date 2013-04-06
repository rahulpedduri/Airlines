<%-- 
    Document   : flight_search_results
    Created on : Feb 10, 2013, 4:01:20 PM
    Author     : Jagan
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Flight Results</title>
        <link href="../resources/css/style.css" rel="stylesheet" type="text/css"></link>
    </head>
    <body>
        
        <form name ="form" action="reg" method="POST">
       <a href="Login?logout=true">Logout</a>    
        <a href="UserLevel/flight_search_query.jsp">Home</a>  
             <a href="Handle?booking_history=true">Booking History</a>    
        <h3>Search Flights</h3>
            
        <table border="1" cellpadding="2" align="center" bgcolor="#FFFAF0">
            <thead>
                <tr>
                    <th>Flight Number</th>
                    <th>Airlines</th>
                    <th>Origin</th>
                    <th>Destination</th>
                    <th>Cost</th>
                    <th>Class</th>
                    <th>Detail</th>
                </tr>
            </thead>
            <tbody>
               <c:forEach var="flight" items="${flights}">
                <tr>
                    <td>${flight.flightNumber}</td>
                    <td>${flight.operator}</td>
                    <td>${flight.source}</td>
                    <td>${flight.destination}</td>
		    <td>${flight.cost}</td>
		    <td>${flight.cls}</td>
                    <td><a href="Handle?detailed=true&flight=${flight.flightNumber}">Details</a></td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
        </form>

    </body>
</html>
