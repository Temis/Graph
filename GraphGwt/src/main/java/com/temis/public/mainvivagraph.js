// This demo shows how to create an SVG node which is a bit more complex
// than single image. Do accomplish this we use 'g' element and
// compose group of elements to represent a node.

function redrawGraph(theUI, graphName, javaGraphInstance) {
	// Step 1. Create a graph:
	var thatInstance = javaGraphInstance;
	var graph = Viva.Graph.graph();

	var graphics = Viva.Graph.View.svgGraphics(), nodeSize = 24;

	graphics.node(
			function(node) {
				// This time it's a group of elements:
				// http://www.w3.org/TR/SVG/struct.html#Groups
				var ui = Viva.Graph.svg('g'),
				// Create SVG text element with user id as content
				svgText = Viva.Graph.svg('text').attr('y', '-4px').attr('x',
						'-' + (nodeSize / 4) + 'px').text(node.data.label),

				img = node.data.image ? Viva.Graph.svg('image').attr('width',
						nodeSize).attr('height', nodeSize)
						.link(
								'https://secure.gravatar.com/avatar/'
										+ node.data.image) : Viva.Graph.svg(
						'rect').attr('width', nodeSize)
						.attr('height', nodeSize).attr('fill',
								node.data.color ? node.data.color : '#00a2e8');
				$(ui).hover(function() {
					var vivaEvent = new vivagraph.Graph();
					vivaEvent.mouseHoverNode(this.node.id, thatInstance);
				}, function() {
					var vivaEvent = new vivagraph.Graph();
					vivaEvent.mouseOutNode(this.node.id, thatInstance);
				});

				$(ui).mousedown(function(e){
					var nodeName = this.node.id;
					var vivaEvent = new vivagraph.Graph();
					if(e.button === 2) {
						vivaEvent.rightClickedNode(nodeName,thatInstance);
				    } else if (e.button === 1) {
				    	vivaEvent.middleClickedNode(nodeName,thatInstance);
				    } else {
				    	vivaEvent.clickedNode(nodeName,thatInstance);
				    }
				});
				
				

				$(ui).mouseup(function(e){
					var nodeName = this.node.id;
					var vivaEvent = new vivagraph.Graph();
					if(e.button === 2) {
						vivaEvent.rightClickedReleaseNode(nodeName,thatInstance);
				    } else if (e.button === 1) {
				    	vivaEvent.middleClickedReleaseNode(nodeName,thatInstance);
				    } else {
				    	vivaEvent.clickedReleaseNode(nodeName,thatInstance);
				    }
				});
				
				ui.append(svgText);
				ui.append(img);
				return ui;
			}).placeNode(
			function(nodeUI, pos) {
				// 'g' element doesn't have convenient (x,y) attributes,
				// instead
				// we have to deal with transforms:
				// http://www.w3.org/TR/SVG/coords.html#SVGGlobalTransformAttribute
				nodeUI.attr('transform', 'translate(' + (pos.x - nodeSize / 2)
						+ ',' + (pos.y - nodeSize / 2) + ')');
			});

	if (theUI.nodes)
		$.each(theUI.nodes, function(name, nodeData) {
			graph.addNode(name, nodeData);
		})

	if (theUI.edges)
		$.each(theUI.edges, function(src, dsts) {

			$.each(dsts, function(dst, edgeData) {

				graph.addLink(src, dst);
			})
		})

		// Step 2. Add graph content.
		// graph.addNode('anvaka', '91bad8ceeec43ae303790f8fe238164b');
		// graph.addNode('indexzero', 'd43e8ea63b61e7669ded5b9d3c2e980f');

		// graph.addLink('anvaka', 'indexzero');

	var layout = Viva.Graph.Layout.forceDirected(graph, {
		springLength : 100,
		springCoeff : 0.0008,
		dragCoeff : 0.02,
		gravity : -1.2
	});

	var renderer = Viva.Graph.View.renderer(graph, {
		layout : layout,
		graphics : graphics,
		container : document.getElementById(graphName)
	});

	document.oncontextmenu = function() {
	    return false;
	}
	
	var events = Viva.Graph.webglInputEvents(graphics, graph);

	renderer.run();
}
