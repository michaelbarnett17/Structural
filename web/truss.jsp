<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="konva.min.js"></script>
    <script src="trussDynamicHTML.js"></script>
    <script src="trussDiagram.js"></script>
    <script src="trussAJAX.js"></script>
    <link rel="stylesheet" href="truss.css">
</head>    
       
<body>
<%@include file="header.jsp" %>

<meta charset="utf-8">
<title>Truss</title>

<div class="container-fluid">
    <form action="trussUpdateServlet" id ="truss" method="GET">
        <div id="memberGroup">
            <div id="memberFields"> </div>
            <input type="button" id="addMembers"  value="Add Members" class="btn-info"></input><br/>
        </div>
        
        <div id="forceGroup">
            <div id="forceFields"> </div>
            <input type="button" id="addForces"  value="Add Forces" class="btn-info"></input><br/>
        </div>    
        
        <div id="supportGroup">
            <div class="row">
                <label class="col-xs-1 col-sm-1">Roller Support:</label>
                <label class="col-xs-1 col-sm-1">x:</label><input type="text" name="rx" id="rx" class="col-xs-1 col-sm-1">
                <label class="col-xs-1 col-sm-1">y:</label><input type="text" name="ry" id="ry" class="col-xs-1 col-sm-1">
            </div>

            <div class="row">
                <label class="col-xs-1 col-sm-1">Pin Support:</label>
                <label class="col-xs-1 col-sm-1">x:</label><input type="text" name="px" id="px" class="col-xs-1 col-sm-1">
                <label class="col-xs-1 col-sm-1">y:</label><input type="text" name="py" id="py" class="col-xs-1 col-sm-1">
            </div>
        </div>
               
        
        <input type="button" id="diagram" value="Create Update Diagram" class="btn-info btn-lg">
        <input type="button" id="ajaxButton" value="Solve" class="btn-success btn-lg">
    </form>
    <div id="container"></div>
    
    <div id ="solution" class="col-xs-4 col-sm-4"></div>


</div>

</body>
</html>
