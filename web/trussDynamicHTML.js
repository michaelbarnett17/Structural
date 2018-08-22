//WARNING DO NOT USE a or b or memberCounter again!!!!!!!!!!!!!
//Note memberCounter does not match a, it does not matter since all forces are put into an array in order when form is submitted. Member counter is just so the user can match the member with the displayed solution.
var a = 1;
var b = 1;
var memberCounter = 1;

$(document).ready(function() {
    $("#addMembers").on("click", function(){addMemberFields();});
    $("#addForces").on("click", function(){addForceFields();});
    
    $("#memberFields").on("click", ".deleteMember", function(){
        var element = $(this);
        deleteMember(element);
    });
    
    $("#forceFields").on("click", ".deleteForce", function(){
        var element = $(this);
        deleteForce(element);
    });
});

function addMemberFields(){
    var row = $("<div class='row'>").appendTo("#memberFields");
    
    row.append("<label class='col-xs-1 col-sm-1'>Member<span class='memberCount'>" + memberCounter + "</span></label><label class='col-xs-1 col-sm-1'> x1:</label>");
    row.append("<input id=\"" + a + "x1\">");
    $("#" + a + "x1").attr("name", "member" + a + "x1");
    $("#" + a + "x1").addClass("member col-xs-1 col-sm-1");
    
    row.append("<label class='col-xs-1 col-sm-1'>y1:</label>");
    row.append("<input id=\"" + a + "y1\">");
    $("#" + a + "y1").attr("name", "member" + a + "y1");
    $("#" + a + "y1").addClass("member col-xs-1 col-sm-1");
    
    row.append("<label class='col-xs-1 col-sm-1'>x2:</label>");
    row.append("<input id=\"" + a + "x2\">");
    $("#" + a + "x2").attr("name", "member" + a + "x2");
    $("#" + a + "x2").addClass("member col-xs-1 col-sm-1");
    
    row.append("<label class='col-xs-1 col-sm-1'>y2:</label>");
    row.append("<input id=\"" + a + "y2\">");
    $("#" + a + "y2").attr("name", "member" + a + "y2");
    $("#" + a + "y2").addClass("member col-xs-1 col-sm-1");
    
    row.append("<input type=\"button\" id=" + a + "m value=\"Delete\">");
    $("#" + a + "m").attr("id", a + "m");
    $("#" + a + "m").addClass("deleteMember btn-danger");

    a++;
    memberCounter++;
}

function addForceFields() {
    var row = $("<div class='row'>").appendTo("#forceFields");
    
    row.append("<div class='col-xs-1 col-sm-1'></div>");
    
    row.append("<label class='col-xs-1 col-sm-1'>x:</label>");
    row.append("<input id=\"" + b + "fx\">");
    $("#" + b + "fx").attr("name", "force" + b + "fx");
    $("#" + b + "fx").addClass("force col-xs-1 col-sm-1");
    
    row.append("<label class='col-xs-1 col-sm-1'>y:</label>");
    row.append("<input id=\"" + b + "fy\">");
    $("#" + b + "fy").attr("name", "force" + b + "fy");
    $("#" + b + "fy").addClass("force col-xs-1 col-sm-1");

    row.append("<label class='col-xs-1 col-sm-1'>Magnitude:</label>");
    row.append("<input id=\"" + b + "fm\">");
    $("#" + b + "fm").attr("name", "force" + b + "fm");
    $("#" + b + "fm").addClass("force col-xs-1 col-sm-1");
    
    row.append("<label class='col-xs-1 col-sm-1'>Angle:</label>");
    row.append("<input id=\"" + b + "fa\">");
    $("#" + b + "fa").attr("name", "force" + b + "fa");
    $("#" + b + "fa").addClass("force col-xs-1 col-sm-1");
        
    row.append("<input type=\"button\" id=" + b + "f value=\"Delete\">");
    $("#" + b + "f").attr("id", b + "f");
    $("#" + b + "f").addClass("deleteForce btn-danger");
    
    b++;
}

function deleteMember(element) {
    element.parent().nextAll().find(".memberCount").each(function() {
        var index = Number($(this).html());
        index = index - 1;
        $(this).html(index);
    });
    
    element.parent().remove();

    memberCounter--;
}

function deleteForce(element) {
    element.parent().remove();
}