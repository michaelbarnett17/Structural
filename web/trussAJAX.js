$(document).on("click", "#ajaxButton", function() { 
    var trussForm = $("#truss");
    var trussArray = trussForm.serializeArray();
    var trussJSON = JSON.stringify(trussArray);
    //alert (trussJSON);
    
    validateForceOnNodes();
    
    $.ajax({
     type: "post",
     url: "trussUpdateServlet",
     data: {trussData : trussJSON},
     success: function(){
         //alert("Solution on Bottom of Screen");
         getAjax(); 
     },
     error: function(){
         alert("Unstable Truss, Statically Indeterminate Truss or Missing Form Data");
         console.log("POST ERROR");
     }
   });   
});

function getAjax() { 
    $.ajax({
     type: "get",
     url: "trussUpdateServlet",
     success: function(data){
         solution = $("#solution");
         solution.html("");
         solution.append("<table id='myTable' class='table table-bordered'>");
         table = $("#myTable");
         
         data = data.toString();
         data = data.split(',');
         //alert(data);
         alert("Solution on Bottom of Screen");
         for(i=0; i<(data.length - 3); i++) {
             var j = i + 1;
             table.append("<tr><td>Member" + j + "</td><td>" + parseFloat(data[i]).toFixed(2) + "</td></tr");
         }
         for(i=(data.length - 3); i<data.length; i++) {
             //roller Y Pin X PinY
             if (i == (data.length - 3)) {
                 table.append("<tr><td>Roller Y</td><td>" + parseFloat(data[i]).toFixed(2) + "</td></tr>");
             } else if (i == (data.length - 2)) {
                 table.append("<tr><td>Pin X</td><td>"    + parseFloat(data[i]).toFixed(2) + "</td></tr>");
             } else if (i == (data.length - 1)) {
                 table.append("<tr><td>Pin Y</td><td>"    + parseFloat(data[i]).toFixed(2) + "</td></tr>");
             }             
         }        
     },
     error: function(){alert("POST ERROR");}
   });   
}

function validateForceOnNodes() {
    var forceArrayX = [];
    var forceArrayY = [];
    var forces = $(".force");
    for(i = 0; i < forces.length; i = i+4) {
        forceArrayX.push(Number(forces[i].value));
        forceArrayY.push(Number(forces[i+1].value));
    }
   
    var memberArrayX = [];
    var memberArrayY = [];
    var members = $(".member");
    for(i = 0; i < members.length; i = i+2) {
        memberArrayX.push(Number(members[i].value));
        memberArrayY.push(Number(members[i+1].value));
    }
    
    var forcePointArray = [];
    for(i = 0; i < forceArrayX.length; i++) {
        var Point = {x:forceArrayX[i], y:forceArrayY[i]};
        forcePointArray.push(Point);
    }
    
    var memberPointArray = [];
    for(i = 0; i < memberArrayX.length; i++) {
        var Point = {x:memberArrayX[i], y:memberArrayY[i]};
        memberPointArray.push(Point);
    }
    
    for(i = 0; i < forcePointArray.length; i++) {
        var match = false;
        var pointForce = forcePointArray[i];
        for(j = 0; j < memberPointArray.length; j++) {
            var pointMember = memberPointArray[j]
            if(pointForce.x == pointMember.x && pointForce.y == pointMember.y) {
                match = true;
            }
        }
        if(match == false) {
            alert("Force Not On Joint Will Be Ignored");
            break;
        }
    }
}



