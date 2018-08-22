window.addEventListener('load', assignEvenHandler);

function assignEvenHandler() {
    document.getElementById("diagram").onclick = checkForEmptyMemberData;
}

function createDiagram() {

    //WINDOW VARIABLES
    var width = 1200;
    var height = 400; 
    
    var offsetY = height - 50;
    var offsetX = 50;
    var flipY = -1;
    
    var members = document.getElementsByClassName("member");
    var forces  = document.getElementsByClassName("force");
    var rollerX = parseFloat(document.getElementById("rx").value);
    var rollerY = parseFloat(document.getElementById("ry").value);
    var pinX    = parseFloat(document.getElementById("px").value);
    var pinY    = parseFloat(document.getElementById("py").value);
    var membersArray = [];
    var linesArray   = [];
    var forcesArray  = [];
    var arrowsArray  = [];
    var pointsArray  = [];
    
    for(i=0; i < members.length; i++) {
        membersArray.push(parseFloat(members.item(i).value));
    }
    
    for(i=0; i < forces.length; i++) {
        forcesArray.push(parseFloat(forces.item(i).value));
    }
       
    var offsetAndScale = findScaleFactor(membersArray, width, height);
    
    var scaleFactor = offsetAndScale[2];
    offsetX = offsetX - offsetAndScale[0] * scaleFactor;
    offsetY = offsetY + offsetAndScale[1] * scaleFactor;  
   
    for(i=0, j= 0; i < membersArray.length; i = i + 4, j++) {
        var x1 = membersArray[i];
        var y1 = membersArray[i+1];
        var x2 = membersArray[i+2];
        var y2 = membersArray[i+3];
        
        linesArray[j] = new line(x1, y1, x2, y2, offsetX, offsetY, scaleFactor, flipY);       
    }
    
    for(i=0, j= 0; i < membersArray.length; i = i + 2, j++) {
        var x1 = membersArray[i];
        var y1 = membersArray[i+1];
        
        pointsArray[j] = new Point(x1, y1, offsetX, offsetY, scaleFactor, flipY);       
    }    
      
    for(i=0, j= 0; i < forces.length; i = i + 4, j++) {
        var forceXLocation = forcesArray[i];
        var forceYLocation = forcesArray[i+1];
        var forceMagnitude = forcesArray[i+2];
        var forceAngle     = forcesArray[i+3] + 180;
        
        arrowsArray[j] = new LoadArrow(forceMagnitude, forceAngle, forceXLocation, forceYLocation, offsetX, offsetY, scaleFactor, flipY);       
    }    
    
    var roller = new circle(rollerX, rollerY, offsetX, offsetY, scaleFactor, flipY);
    var pin = new triangle(pinX, pinY, offsetX, offsetY, scaleFactor, flipY);

    var stage = new Konva.Stage({
        container: 'container',
        width: width,
        height: height
    });
    
    var layer = new Konva.Layer();
      
    for(i=0; i < linesArray.length; i++) {
        layer.remove(linesArray[i]);
        layer.add(linesArray[i]);        
    }
    
    for(i=0; i < pointsArray.length; i++) {
        layer.remove(pointsArray[i]);
        layer.add(pointsArray[i]);        
    }    
    
    for(i=0; i < arrowsArray.length; i++) {
        layer.remove(arrowsArray[i]);
        layer.add(arrowsArray[i]);        
    }        
    
    if(!isNaN(rollerX) && !isNaN(rollerY)) {
        layer.add(roller);
    }
    if(!isNaN(pinX) && !isNaN(pinY)) {
        layer.add(pin);
    }
    
    stage.add(layer);
}

function line(x1, y1, x2, y2, offsetX, offsetY, scaleFactor, flipY) {
    var line = new Konva.Line({
        points: [offsetX + x1*scaleFactor, offsetY + y1*scaleFactor*flipY, offsetX + x2*scaleFactor, offsetY + y2*scaleFactor*flipY],
        stroke: 'blue',
        tension: 1
    });
    return line;
}

function circle(x, y, offsetX, offsetY, scaleFactor, flipY) {
    var circle = new Konva.Circle({
        x: offsetX + x*scaleFactor,
        y: offsetY + y*scaleFactor*flipY,
        radius: 8,
        fill: '#3dce33',
        stroke: 'black',
        strokeWidth: 2
    });
    return circle;
}

function Point(x, y, offsetX, offsetY, scaleFactor, flipY) {
    var circle = new Konva.Circle({
        x: offsetX + x*scaleFactor,
        y: offsetY + y*scaleFactor*flipY,
        radius: 4,
        fill: 'blue',
        strokeWidth: 2
    });
    return circle;
}

function triangle(x, y, offsetX, offsetY, scaleFactor, flipY){
    var triangle = new Konva.RegularPolygon({
        x: offsetX + x*scaleFactor,
        y: offsetY + y*scaleFactor*flipY,
        sides: 3,
        radius: 10,
        fill: '#3dce33',
        stroke: 'black',
        strokeWidth: 2
    });
    return triangle;
}

function LoadArrow(forceMagnitude, forceAngle, forceXLocation, forceYLocation, offsetX, offsetY, scaleFactor, flipY) {
    var loadArrow = new Konva.Arrow({
        
        x: offsetX + forceXLocation*scaleFactor,
        y: offsetY + forceYLocation*scaleFactor*flipY,
        points: [0, 40, 0, 100],
        pointerLength: 12,
        pointerWidth: 12,
        fill: 'red',
        stroke: 'red',
        strokeWidth: 3,
        offsetY: 100,
        rotation: forceAngle
    });    
    return loadArrow;
}


function findScaleFactor(membersArray, width, height) {
    var scaleFactor;
    var arrayOfXValues = [];
    var arrayOfYValues = [];
    var lengthX;
    var heightY;
    
    for(i=0; i < membersArray.length; i = i + 2) {
        arrayOfXValues.push(membersArray[i]);
    }
    
    for(i=1; i < membersArray.length; i = i + 2) {
        arrayOfYValues.push(membersArray[i]);
    }
    
    var maxX = Math.max.apply(Math, arrayOfXValues);
    var minX = Math.min.apply(Math, arrayOfXValues);
    var maxY = Math.max.apply(Math, arrayOfYValues);
    var minY = Math.min.apply(Math, arrayOfYValues);
    
    var lengthX = maxX - minX;
    var heightY = maxY - minY;
    
    if(lengthX > heightY*4) {
        scaleFactor = .7*width/lengthX;
    } else {
        scaleFactor = .7*height/heightY;
    }
    return [minX, minY, scaleFactor];
}
    
function checkForEmptyMemberData() {
    var members = document.getElementsByClassName("member");
    if (members.length == 0) {
        alert("Add Members To See Diagram");
        return;
    }
    
    for(i=0; i < members.length; i++) {
        if(members[i].value == "") {
            alert("Fill in Empty Member Fields To Update Diagram");
            return;
        }
    } 
    createDiagram();
}