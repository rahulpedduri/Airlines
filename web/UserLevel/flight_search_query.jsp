<%-- 
    Document   : flight_search_query
    Created on : Feb 10, 2013, 4:00:40 PM
    Author     : Jagan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Flight Search Page</title>
        <link href="../resources/css/style.css" rel="stylesheet" type="text/css"/>
        <script src="../resources/jquery/jquery-ui-1.10.0.custom/js/jquery-1.9.0.js"></script> 
        <link href="../resources/jquery/jquery-ui-1.10.0.custom/css/no-theme/jquery-ui-1.10.0.custom.css" rel="stylesheet" type="text/css" ></link>
        <script src="../resources/jquery/jquery-ui-1.10.0.custom/js/jquery-ui-1.10.0.custom.js"></script>
        <script>
            $(function() {
            $( "#datepicker" ).datepicker();
            });
        </script>
    </head>
    <body>
        <body>
        <form name ="form" action="../Handle" method="GET">
            <a href="Login?logout=true><button type="button" value="logout" name="logout" align="right">Logout</button></a>
            <a href="booking_history.jsp"><button type="button" value="history" name="history">Booking History</button></a>
        
            <h3>Available Flights</h3>
             <div>
             <label for="Source">Source</label>
             <input type="text" name="source" class ="required"/>
             </div>
            
             <div> 
             <label for="dest">Destination</label>
             <input type="text" name="dest" class ="required"/>
             </div>
            
            <div>
            <label for="date">Date of Travel</label>
            <input type="text" id="datepicker" name="date" class ="required"/>
            </div>
            
            <div>
            <label for="seats">Number of Seats</label>
            <select name="seats">
                <option>1</option>
                <option>2</option>
                <option>3</option>
                <option>4</option>
                <option>5</option>
                <option>6</option>
                <option>7</option>
                <option>8</option>
                <option>9</option>
                <option>10</option>     
            </select>
                
            </div>
            <div>
            <label for="class">Class</label>
            
            <select name="class" class="required">
                <option>Economy</option> 
                <option>First</option> 
                <option>Business</option> 
            </select>
           
            </div>
            
            <div class ="submit">
            <button type="submit" value="submit" name="flight_search_submit">submit</button>
            </div>
        </form>
    
    </body>
</html>
