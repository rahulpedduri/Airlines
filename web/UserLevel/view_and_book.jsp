<%-- 
    Document   : view_and_book
    Created on : Feb 12, 2013, 4:01:46 PM
    Author     : Phani Rahul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Flights</title>
        <link href="../resources/css/style.css" rel="stylesheet" type="text/css"/>
	<script language="Javascript">
		function total() {
		var1 = document.getElementById('seats').value;
		var2 = ${flight.cost};
		return var1 * var2;
		}
	</script>
    </head>
    
    <body>
        
      <form name ="form" action="" method="POST"> 
      <a href="../login.jsp"><button type="button" value="logout" name="logout" align="right">Logout</button></a>    
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
		    <c:choose>
  			<c:when test="${flight.seatsTotal-flight.seatsTaken >= 10}">
    				num = 10
  			</c:when>
	
  			<c:otherwise>
    				num = flight.seatsTotal-flight.seatsTaken
  			</c:otherwise>
		    </c:choose>
		
		    <select name="seats">
		    <c:forEach var="number" begin="1" end=num >
  		       <td>
      		           <option>${number}</option> 
  		       </td> 
		    </c:forEach>
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
        </table>
          
            <div class ="submit">
            <a href="transaction.jsp"><button type="button" value="Select" name="select">select</button></a>
            <a href="flight_search_results.jsp"><button type="button" value="Back" name="back">back</button></a>
            <a href="flight_search_query.jsp"><button type="button" value="Home" name="home">home</button></a>
            </div>
          
      </form>
        
        
        
    </body>
</html>
