<%-- 
    Document   : view_and_book
    Created on : Feb 12, 2013, 4:01:46 PM
    Author     : Phani Rahul
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Flights</title>
        <link href="../resources/css/style.css" rel="stylesheet" type="text/css"/>
        <script language="Javascript">
            var totalCost;
            function total() {
                var1 = form.seats.value;
                var2 = ${flight.cost};
                totalCost= var1 * var2;
                document.getElementById("total_cost").innerHTML="<strong>"+totalCost+"</strong>";
            }
        </script>
    </head>

    <body>

        <form name ="form" action="ViewAndBook" method="POST"> 
           <a href="Login?logout=true">Logout</a>    
            <a href="UserLevel/flight_search_query.jsp">Home</a>  
             <a href="Handle?booking_history=true">Booking History</a>    
            <h3>View Flights:</h3>
            <table border="1" cellpadding="10" align="center">
                <tr>
                    <td>Flight Number</td>
                    <td>${flight.flightNumber}</td>
                </tr>

                <tr>
                    <td>Airlines</td>
                    <td>${flight.operator}</td>
                </tr>

                <tr>
                    <td>Departure Time</td>
                    <td>${flight.departureTime}</td>
                </tr>    

                <tr>
                    <td>Arrival Time</td>
                    <td>${flight.arrivalTime}</td>
                </tr>

                <tr>
                    <td>Number of Seats</td>
                    <td>
                <c:choose>
                    <c:when test="${flight.seatsTotal-flight.seatsTaken > 0}">

                        <select name="seats" onchange="total()">
                            <c:forEach var="number" begin="1" end="${flight.seatsTotal-flight.seatsTaken}" >  		       
                                <option>${number}</option>   		      
                            </c:forEach>
                        </select>
                    </c:when>

                    <c:otherwise>
                        0
                    </c:otherwise>
                </c:choose>
                        </td>

                </tr>

                <tr>
                    <td>Cost per seat</td>
                    <td>${flight.cost}</td>
                </tr>

                <tr>
                    <td>Source</td>
                    <td>${flight.source}</td>
                </tr>

                <tr>
                    <td>Destination</td>
                    <td>${flight.destination}</td>
                </tr>

                <tr>
                    <td>Class</td>
                    <td>${flight.cls}</td>
                </tr>   
                <tr>
                    <td>Total Cost</td>
                    <td id="total_cost"></td>
                </tr>   
            </table>
                <input type="hidden" name="cost" value="${flight.cost}" />
                <input type="hidden" name="id" value="${flight.flightNumber}" />
            <div class ="submit">
                <button type="submit" value="Select" name="view_and_book_submit" >select</button>
                <a href="flight_search_results.jsp"><button type="button" value="Back" name="back">back</button></a>
                <a href="flight_search_query.jsp"><button type="button" value="Home" name="home">home</button></a>
            </div>

        </form>



    </body>
</html>
