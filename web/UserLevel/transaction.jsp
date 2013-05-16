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
        <script src="../resources/jquery/jquery-ui-1.10.0.custom/js/jquery-1.9.0.js"></script>

    </head>
    <body>


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

        <div id ="ajax">
            <form name ="form" action="../BankWelcome" method="POST" target="_blank">                              
                <input type="hidden"  name="merchant_name" value="abc airlines" />
                <input type="hidden"  name="username" value="${sessionScope['user'].username}" />
                <input type="hidden"  name="bill" value="${sessionScope['bill']}" />
                <input type="hidden"  name="session" value="${pageContext.session.id}" />
                <input type="hidden"  name="bill_description" value=".........." />
                <input type="hidden"  name="callback" value="http://localhost:8084/Airlines/Banking" />

                <button type="submit" id="transaction_submit" value="confirm" name="transaction_submit">confirm</button>
                <a href="flight_search_query.jsp"><button type="button" value="cancel" name="cancel">Continue shopping</button></a>
    </form>

        </div>

                 <script>
            var da;
            $('#transaction_submit').click(function(){
                $('#ajax').html("<img src='../resources/img/ajax-loader.gif'/>");  
                $.ajax({
                    url: '/Airlines/PendingTransaction',
                    type: "GET",
                    data: { status: "set_pending" },                    
                    success:
                        i = setInterval(function(){
                        $.ajax({
                            url: '/Airlines/PendingTransaction',
                            type: "GET",
                            data: { status: "waiting" },                            
                            success: function(data){
                            //alert("ajax sucess");
                                da=data;
                                //alert(da + "....."+ da.status);
                                var reply = jQuery.parseJSON( data );
                                //  alert(reply.status + "..."+reply.html);
                                // alert(reply);
                                if(reply.status == "true"){
                                    //alert(reply.status);
                                    clearInterval(i);
                                    $('#ajax').html(reply.html);
                                    
                                }
                            }
                        })
                    },10000)
                    //alert("Success");
                    //1. PendingTransaction .. status=set_pending
                    //2. setInterval($.ajax{status=waiting}  ,1000);
                    //3. call Transaction servlet using ajax
                })//TODO: flaw somewhere..
            });
        </script>


</body>
</html>