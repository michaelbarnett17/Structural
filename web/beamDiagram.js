window.addEventListener('load', assignEvenHandler);

function assignEvenHandler() {
    document.getElementById("diagram").onclick = checkForEmptyBeamLength;
}

function createDiagram() {
    
    //WINDOW VARIABLES
    var width = 1200;
    var height = 220;

    var stage = new Konva.Stage({
        container: 'container',
        width: width,
        height: height
    });

    var stage2 = new Konva.Stage({
        container: 'container2',
        width: width,
        height: height
    });

    var layer = new Konva.Layer();
    var layer2 = new Konva.Layer();

    var length           = document.getElementById("length").value;
    var scaleFactor = 800/length;

    var length           = document.getElementById("length").value          *scaleFactor;
    var pinLocation      = document.getElementById("pinLocation").value     *scaleFactor;
    var rollerLocation   = document.getElementById("rollerLocation").value  *scaleFactor;
    
    var loadLocations           = document.getElementsByClassName("loadLocation");
    var loadMagnitudes          = document.getElementsByClassName("loadMagnitude");
    var angles                  = document.getElementsByClassName("angle");
    
    var loadLocationsArray  = [];
    var loadMagnitudesArray = [];
    var anglesArray         = []; 
    var loadArrowsArray     = [];
  
    for(i=0; i < loadLocations.length; i++) {
        loadLocationsArray.push(loadLocations.item(i).value);
    }
    
    for(i=0; i < loadMagnitudes.length; i++) {
        loadMagnitudesArray.push(loadMagnitudes.item(i).value);
    }
    
    for(i=0; i < angles.length; i++) {
        anglesArray.push(angles.item(i).value);
    }
    
    //DRAWING VARIABLES
    var offsetX = 100;
    var offsetY = 100;
    var beamThickness = 30;

    //SHAPES
    var rect = new Konva.Rect({
        x: offsetX,
        y: offsetY,
        width: 800,
        height: beamThickness,
        fill: '#1a75ff',
        stroke: 'black',
        strokeWidth: 2
    });

    var rect2 = new Konva.Rect({
        x: offsetX,
        y: offsetY,
        width: 800,
        height: beamThickness,
        fill: '#1a75ff',
        stroke: 'black',
        strokeWidth: 2
    });

    //Object Oriented Load Arrow
    function LoadArrow(loadLocation, angle) {
        drawLocation = loadLocation;
        drawAngle = angle;
        var loadArrow = new Konva.Arrow({
            x: offsetX + drawLocation,
            y: offsetY + beamThickness/2,
            points: [0, 30, 0, 100],
            pointerLength: 14,
            pointerWidth: 14,
            fill: 'red',
            stroke: 'red',
            strokeWidth: 4,
            offsetY: 100,
            rotation: drawAngle
        });    
    return loadArrow;
    }
    
    for(i=0; i < loadLocationsArray.length; i++) {
        var loadLocation = 0;
        var angle = 0;
        
        loadLocation = loadLocationsArray[i] * scaleFactor;
        angle = Number(anglesArray[i]) + 180;
        loadArrowsArray[i] = new LoadArrow(loadLocation, angle);       
    }

    var pinArrowY = new Konva.Arrow({
        x: offsetX + pinLocation,
        y: offsetY + beamThickness + 6,
        points: [0, 30, 0, 100],
        pointerLength: 14,
        pointerWidth: 14,
        fill: '#3dce33',
        stroke: '#3dce33',
        strokeWidth: 4,
        offsetY: 100,
        rotation: 180
    });

    var pinArrowX = new Konva.Arrow({
        x: offsetX - 5 + pinLocation,
        y: offsetY + beamThickness,
        points: [0, 30, 0, 100],
        pointerLength: 14,
        pointerWidth: 14,
        fill: '#3dce33',
        stroke: '#3dce33',
        strokeWidth: 4,
        offsetY: 100,
        rotation: -90
    });

    var rollerArrow = new Konva.Arrow({
        x: offsetX + rollerLocation,
        y: offsetY + beamThickness + 6,
        points: [0, 30, 0, 100],
        pointerLength: 14,
        pointerWidth: 14,
        fill: '#3dce33',
        stroke: '#3dce33',
        strokeWidth: 4,
        offsetY: 100,
        rotation: 180
    });

    //TEXT
    var rollerText = new Konva.Text({
        x: rollerLocation + 105,
        y: 175,
        text: 'Ry',
        fontSize: 30,
        fontFamily: 'Calibri',
        fill: '#3dce33'
    });

    var pinTextX = new Konva.Text({
        x: pinLocation + 40,
        y: 140,
        text: 'Px',
        fontSize: 30,
        fontFamily: 'Calibri',
        fill: '#3dce33'
    });

    var pinTextY = new Konva.Text({
        x: pinLocation + 105,
        y: 175,
        text: 'Py',
        fontSize: 30,
        fontFamily: 'Calibri',
        fill: '#3dce33'
    });

    // ADD SHAPES TO LAYERS
    layer.add(rect);

    for(i=0; i < loadArrowsArray.length; i++) {
        layer.add(loadArrowsArray[i]);        
    }

    layer2.add(rect2);
    
    if(document.getElementById("pinLocation").value != "") {
        layer2.add(pinArrowY);
        layer2.add(pinArrowX);
        layer2.add(pinTextX);
        layer2.add(pinTextY);
        
        var triangle = new Konva.RegularPolygon({
            x: offsetX + pinLocation,
            y: 162,
            sides: 3,
            radius: 30,
            fill: '#3dce33',
            stroke: 'black',
            strokeWidth: 2
        });        
        layer.add(triangle);
    }
    
    if(document.getElementById("rollerLocation").value != "") {    
        layer2.add(rollerArrow);
        layer2.add(rollerText);
        
        var circle = new Konva.Circle({
            x: offsetX + rollerLocation,
            y: offsetY + beamThickness + 25,
            radius: 25,
            fill: '#3dce33',
            stroke: 'black',
            strokeWidth: 2
        });
        layer.add(circle);
    }
        
    // ADD LAYERS TO STAGE
    stage.add(layer);
    stage2.add(layer2);  
}

function checkForEmptyBeamLength() {
    var length = document.getElementById("length").value;
    if(length == "" || length == 0){
        alert("Enter a non-zero Beam Length");
        return;
    }
    createDiagram();
}

