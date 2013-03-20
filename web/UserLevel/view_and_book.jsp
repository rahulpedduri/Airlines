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
    </head>
    <body>
        
      <form name ="form" action="" method="POST"> 
      <a href="../login.jsp"><button type="button" value="logout" name="logout" align="right">Logout</button></a>    
      <h3>View Flights:</h3>
        <table border="1" cellpadding="10" align="center">
            <thead>
                <tr>
                    <th>Flight Number</th>
                    <th> ${flight.flightNumber}</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>Flight Name</td>
                    <td>United Airlines</td>
                </tr>
                <tr>
                    <td>Date</td>
                    <td>20 Feb</td>
                </tr>
                <tr>
                    <td>Departure</td>
                    <td>Charlotte</td>
                </tr>
                <tr>
                    <td>Arrival</td>
                    <td>New Ark</td>
                </tr>
                <tr>
                    <td>Stops</td>
                    <td>0</td>
                </tr>
                <tr>
                    <td>Cost</td>
                    <td>$125</td>
                </tr>
            </tbody>
               
        </table>
          
            <div class ="submit">
            <a href="transaction.jsp"><button type="button" value="Select" name="select">select</button></a>
            <a href="flight_search_results.jsp"><button type="button" value="Back" name="back">back</button></a>
            <a href="flight_search_query.jsp"><button type="button" value="Home" name="home">home</button></a>
            </div>
          
      </form>
        
        
        
    </body>
</html>
