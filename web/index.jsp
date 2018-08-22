<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="indexDynamicHTML.js"></script>
    <link rel="stylesheet" href="index.css">
</head>

<body>
<%@include file="header.jsp" %>
<h2>Find Beam Reactions or Solve a Truss</h2>
    <ul>
        <li>Beam Reactions will allow you to solve the reactions of a simply supported beam</li>
        <li>Truss solver will allow you to create a truss and calculate internal forces</li>
        <li>FAQ for instructions and help!</li>
    </ul>

<div class="row">
    <div class="col-md-1"></div>
    <div class="col-md-2"><a href="beamServlet"><img src="images/beamButton.png" class="image" id="beam"></a></div>
    <div class="col-md-2"><a href="trussServlet"><img src="images/trussButton.png" class="image" id="truss"></a></div>
    <div class="col-md-2"><a href="instructionsServlet"><img src="images/faqButton.png" class="image" id="faq"></a></div>
</div>

<div class="row" id="description">
    <div class="col-md-1"></div>
    <div class="col-md-2" id="beamText"><h4><b>Beam Reactions</b><h4></div>
    <div class="col-md-2" id="trussText"><h4><b>Truss Solver</b></h4></div>
    <div class="col-md-2" id="faqText"><h4><b>FAQ / Instructions</b></h4></div>
</div>
</body>