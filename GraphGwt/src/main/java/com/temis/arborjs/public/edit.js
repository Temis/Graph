// Benjamin Han 2013
// Graph editing commands

function addNode(returnName) {
    numAdded++;
    name = 'node' + numAdded
    sys.addNode(name, {'color':defCol, 'shape':'dot', 'label':name});
    newNode = sys.getNode(name);
    selectNode(newNode);
    if (returnName)
	return name;
}

function delNode() {
    if (selectedNode != null && selectedNode.data.ref != true) {
	sys.pruneNode(selectedNode);
	unselectNode(); 
    }
}

function delEdge() {
    if (selectedEdge != null && selectedEdge.data.ref != true) {
	sys.pruneEdge(selectedEdge);
	unselectEdge();
    }
}

function addEdge(u, v) {
    if (u.data.label == "hidden1" || u.data.label == "hidden2" ||
	v.data.label == "hidden1" || v.data.label == "hidden2")
	return false
    currentEdge = sys.getEdges(u, v).concat(sys.getEdges(v, u));
    if (currentEdge.length == 0) {
	sys.addEdge(u, v);
	selectEdge(u, v);
	selectedEdge.data.directed = true;
    } else {
	if (sys.getEdges(u, v).length == 0)
	    selectEdge(v, u);
	else
	    selectEdge(u, v);
    }

}

function selectNode(nearestNode) {
    if (prevNode != null)
	unselectNode();
    if (selectedEdge != null) {
	unselectEdge();
    }
    if (nearestNode != null) {
	selectedNode = nearestNode;
	prevNode = selectedNode;
	sys.tweenNode(selectedNode, 0.2, {color:highCol});
	setCaret();
    }
}

function selectEdge(u, v) {
    if (prevNode != null)
	unselectNode();
    if (selectedEdge != null) {
	unselectEdge();
    }
    edges = sys.getEdges(u, v);
    if (edges.length > 0) 
	selectedge(edges[0]);
}

function selectedge(edge) {
    selectedEdge = edge;
    sys.tweenEdge(selectedEdge, 0.2, {color:highCol});
    setCaret();
    if (selectedEdge.data.directed)
	document.getElementById('ways').src = "one.png"
    else
	document.getElementById('ways').src = "two.png"
}

function unselectNode() {
    if (prevNode != null) {
	prevColor = defCol
	if (selectedNode.data.ref)
	    prevColor = refCol
	sys.tweenNode(prevNode, 0.2, {color:prevColor});
	selectedNode = prevNode = null;
    }
}

function unselectEdge() {
    if (selectedEdge != null) {
	prevColor = defEdgeCol;
	if (selectedEdge.data.ref)
	    prevColor = refEdgeCol;
	sys.tweenEdge(selectedEdge, 0.2, {color:prevColor});
	selectedEdge = null;
    }
}

function rename() {
    newName = document.getElementById('label').value;
    if (selectedNode != null && selectedNode.data.ref != true)
	sys.tweenNode(selectedNode, 0.05, {label:newName});
    if (selectedEdge != null && selectedEdge.data.ref != true)
	sys.tweenEdge(selectedEdge, 0.05, {name:newName});
}

function setCaret() {
    if (selectedNode != null)
	document.getElementById('label').value = selectedNode.data.label;
    if (selectedEdge != null)
	document.getElementById('label').value = selectedEdge.data.name;
    document.getElementById('label').select();
}

function changeWays() {
    if (selectedEdge != null) 
	if (!selectedEdge.data.ref) {
	    selectedEdge.data.directed = !selectedEdge.data.directed;
	    if (selectedEdge.data.directed)
		document.getElementById('ways').src = "one.png"
	    else
		document.getElementById('ways').src = "two.png"
	    sys.tweenEdge(selectedEdge, 0.5);	    
	}
}

document.onkeypress = keyPress;

function keyPress(e) {
    if (e.keyCode == 13) 
	rename();
    else if (e.keyCode == 46) {
	delNode();
	delEdge();
    }
}

function clearMap() {
    sys.eachNode(function(node, pt){
	selectNode(node)
	delNode()
    })
    sys.eachEdge(function(edge, pt1, pt2){
	selectedge(edge)
	delEdge()
    })
}	

function importMap() {
    text = document.getElementById('label').value.split(";")
    clearMap();
    for (var i = 0; i < text.length; i++) {
	line = text[i]
	if (line.indexOf("-") == -1 && line.length > 0) {
	    sys.addNode(line, {'color':defCol, 'shape':'dot', 'label':line})
	} else if (line.indexOf("-") != -1) {
	    named = false
	    if (line.indexOf("(") != -1 && line.indexOf(")") != -1) {
		named = true
		edgename = line.substring(line.indexOf("(") + 1, line.indexOf(")"))
	    }
	    if (line.indexOf("->") != -1) {
		directed = true
		line = line.split("->")
	    } else {
		directed = false
		line = line.split("-")
	    }
	    sourceName = line[0]
	    targName = named ? line[1].substring(0, line[1].indexOf("(")) : line[1]
	    source = sys.getNode(sourceName)
	    target = sys.getNode(targName)
	    sys.addEdge(source, target)
	    edge = sys.getEdges(source, target)[0];
	    if (named && edgename != 'undefined')
		sys.tweenEdge(edge, 0.05, {name:edgename});
	    if (directed)
		edge.data.directed = true;
	}
    }
}

function exportMap() {
    text = ""
    sys.eachNode(function(node, pt){
	label = node.data.label
	if (label != "hidden1" && label != "hidden2")
	    text = text.concat(label + ";")
    })
    sys.eachEdge(function(edge, pt1, pt2){
	label = "(" + edge.data.name + ")"
	directed = (edge.data.directed) ? "->" : "-"
	source = edge.source.data.label
	target = edge.target.data.label
	text = text.concat(source + directed + target + label + ";")
    })
    window.open("data:text/plain;charset=utf-8," + text)
}