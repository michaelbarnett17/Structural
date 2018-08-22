//WARNING DO NOT USE j AGAIN!!!!!!!!!!!
var j = 1;

$(document).ready(function() {
    //$("#addForces").on("click", {param1: i}, function(something){alert(something.data.param1);});
    $("#addForces").on("click", function(){addFields();});
});

function addFields(){
    var row = $("<div class='row'>").appendTo("#loadFields");
    
    row.append("<label class='col-xs-2'>Load Location " + j + ":</label>");
    row.append("<input id=\"loadLocation" + j + "\"></input>");
    $("#loadLocation" + j).attr("name", "loadLocation" + j);
    $("#loadLocation" + j).addClass("loadLocation col-xs-1 col-sm-2");

    row.append("<label class='col-xs-2'>Load Magnitude " + j + ":</label>");
    row.append("<input id=\"loadMagnitude" + j + "\"></input>");
    $("#loadMagnitude" + j).attr("name", "loadMagnitude" + j);
    $("#loadMagnitude" + j).addClass("loadMagnitude col-xs-1 col-sm-2");

    row.append("<label class='col-xs-2'>Load Angle " + j + " (deg):</label>");
    row.append("<input id=\"angle" + j + "\"></input>");
    $("#angle" + j).attr("name", "angle" + j);
    $("#angle" + j).addClass("angle col-xs-1 col-sm-1");

    j++;
}


