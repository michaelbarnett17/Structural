$(document).on("click", "#ajaxButton", function() { 
    var beamForm = $("#beam");
    var beamArray = beamForm.serializeArray();
    var beamJSON = JSON.stringify(beamArray);
    //alert (beamJSON);
    
    validateThenPostThenGet(beamJSON);
});
   
function postAjax(beamJSON) {
    $.ajax({
     type: "post",
     url: "beamUpdateServlet",
     data: {blah : beamJSON},
     success: function(){
         alert("Solution on Bottom of Screen");
         getAjax();       
     },
     error: function(){
         alert("Unstable Beam, Empty From Data or No Loads");
         console.log("POST ERROR");
     }
   });    
}

function getAjax() { 
    $.ajax({
     type: "get",
     url: "beamUpdateServlet",
     success: function(data){
         data = data.toString();
         data = data.split(',');
         //alert("GET SUCCESS");
         var pinX    = parseFloat(data[0]).toFixed(2);
         var pinY    = parseFloat(data[1]).toFixed(2);
         var rollerY = parseFloat(data[2]).toFixed(2);
        
         $("#pinReactionX").html   (pinX);
         $("#pinReactionY").html   (pinY);         
         $("#rollerReactionY").html(rollerY);         
     },
     error: function(){alert("GET ERROR");}
   });   
}


function validateThenPostThenGet(beamJSON) {
    var lengthInput = Number($("#length")[0].value);
    var pinInput    = Number($("#pinLocation")[0].value);
    var rollerInput = Number($("#rollerLocation")[0].value);
    var forcesInput = document.getElementsByClassName("loadLocation");
        
    if(pinInput > lengthInput || rollerInput > lengthInput || pinInput < 0 || rollerInput < 0) {
        alert("Support Not Located On The Beam");
        return;
    }
    
    for(var i=0; i<forcesInput.length; i++){
        var forceLocation = Number(forcesInput[i].value);
        if(forceLocation > lengthInput || forceLocation < 0) {
            alert("Force Not Located On The Beam");
            return;
        }
    }
    postAjax(beamJSON);
}




