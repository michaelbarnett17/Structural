$(document).ready(function() {
    $(".image").on( "mouseover", function(){
        var element = $(this);
        var elementId = element.attr('id');
        var elementIdToSelect = elementId + "Text";
        $('#' + elementIdToSelect).css("visibility","visible");});
     $(".image").on( "mouseout", function(){
        var element = $(this);
        var elementId = element.attr('id');
        var elementIdToSelect = elementId + "Text";
        $('#' + elementIdToSelect).css("visibility","hidden");});
});