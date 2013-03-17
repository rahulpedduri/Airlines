<%-- 
    Document   : booking_history
    Created on : Feb 12, 2013, 4:02:44 PM
    Author     : Phani Rahul
--%>

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
            <a href="../login.jsp"><button type="button" value="logout" name="logout" align="right">Logout</button></a>
            
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
                    <th>Ticket Number</th>
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
                    <td>C7PC3N</td>
                </tr>
            </tbody>
        </table>
        
            <div class="submit">
            <a href="flight_search_query.jsp"><button type="button" value="home" name="home">Home</button></a>
            </div>
            
        </form>
    </body>
</html>
