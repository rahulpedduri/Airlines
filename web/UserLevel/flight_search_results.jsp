<%-- 
    Document   : flight_search_results
    Created on : Feb 10, 2013, 4:01:20 PM
    Author     : Jagan
--%>

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
        <a href="../login.jsp"><button type="button" value="logout" name="logout" align="right">Logout</button></a>
        <h3>Search Flights</h3>
            
        <table border="1" cellpadding="2" align="center" bgcolor="#FFFAF0">
            <thead>
                <tr>
                    <th>Flight Number</th>
                    <th>Flight Date</th>
                    <th>Departure</th>
                    <th>Arrival</th>
                    <th>Stops</th>
                    <th>Cost</th>
                    <th>Detail</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="flight" items="${list}">
                <tr>
                    <td><c:out value="${flight.source}"  /></td>
                    <td><c:out value="${flight.dest}" /></td>
                    <td><c:out value="${flight.date}" /></td>
                    <td><c:out value="${flight.seats}"  /></td>
		    <td><c:out value="${flight.cls}"  /></td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
        </form>

    </body>
</html>
