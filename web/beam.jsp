<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="konva.min.js"></script>
    <script src="beamAJAX.js"> </script>
    <script src="beamDynamicHTML.js"></script>
    <script src="beamDiagram.js"> </script>
    <link rel="stylesheet" href="beam.css">    
</head>

<body>
<%@include file="header.jsp" %>

<meta charset="utf-8">
<title>Beam</title>

<div class="container-fluid">  
    
    <form action="beamUpdateServlet" id="beam" class="form-inline" method="GET">
        <div class="row">
            <label class="col-xs-2 col-sm-2">Length of Beam:</label>        
            <input class="col-xs-1 col-sm-2" type="text" name="length" id="length">
        </div>
        <div class="row">
            <label class="col-xs-2 col-sm-2">Pin Location:</label>
            <input class="col-xs-1 col-sm-2" type="text" name="pinLocation" id="pinLocation">
            <label class="col-xs-2 col-sm-2">Roller Location:</label>
            <input class="col-xs-1 col-sm-2" type="text" name="rollerLocation" id="rollerLocation">
        </div>
        <div id="loadFields"></div>
        
        <div id="group1">
        <div class="row myButtons">
            <div class="col-xs-1 col-sm-1"></div>
            <input type="button" id="addForces"  class="btn-info" value="Add Forces"></input><br/>
            <div class="col-xs-1 col-sm-1"></div>
            <input type="button" id="diagram"    class="btn-info btn-lg" value="Create/Update Diagram"></input>
            <input type="button" id="ajaxButton" class="btn-success btn-lg" value="Solve"></input>
        </div>
        </div>

    </form>   
   
    <div id="container"></div>
    <div id="container2"></div>
    <div class="row">
        <div class="col-xs-1 col-sm-1"></div>
        <div class="col-xs-4 col-sm-4">
            <table class="table table-bordered">
                <tr> 
                    <td>pin reaction x</td>
                    <td id="pinReactionX"></td>
                </tr>
                <tr> 
                    <td>pin reaction y</td>
                    <td id="pinReactionY"></td>
                </tr>
                <tr> 
                    <td>roller reaction y</td>
                    <td id="rollerReactionY"></td>
                </tr>
            </table>
        </div>
    </div>
            
</div>
</body>
</html>
