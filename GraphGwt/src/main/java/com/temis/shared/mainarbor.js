var Renderer = function(elt, javaGraphInstance) {
	var canvas = $(elt).get(0)
	var ctx = canvas.getContext("2d");
	var thatInstance = javaGraphInstance;
	var gfx = arbor.Graphics(canvas)
	var particleSystem

	canvas.oncontextmenu = function() {
	     return false;  
	}
	 
	var that = {
		init : function(system) {
			//
			// the particle system will call the init function once, right
			// before the
			// first frame is to be drawn. it's a good place to set up the
			// canvas and
			// to pass the canvas size to the particle system
			//
			// save a reference to the particle system for use in the .redraw()
			// loop
			particleSystem = system

			// inform the system of the screen dimensions so it can map coords
			// for us.
			// if the canvas is ever resized, screenSize should be called again
			// with
			// the new dimensions
			particleSystem.screenSize(canvas.width, canvas.height)
			particleSystem.screenPadding(80) // leave an extra 80px of
			// whitespace per side

			// set up some event handlers to allow for node-dragging
			that.initMouseHandling()
		},

		redraw : function() {
			if (!particleSystem)
				return

			gfx.clear() // convenience ƒ: clears the whole canvas rect

			// draw the nodes & save their bounds for edge drawing
			var nodeBoxes = {}
			particleSystem.eachNode(function(node, pt) {
				var label = node.data.label || ""
				var w = ctx.measureText("" + label).width + 10
				if (!("" + label).match(/^[ \t]*$/)) {
					pt.x = Math.floor(pt.x)
					pt.y = Math.floor(pt.y)
				} else {
					label = null
				}

				if (node.data.color)
					ctx.fillStyle = node.data.color
				else
					ctx.fillStyle = "rgba(0,0,0,.2)"
				if (node.data.color == 'none')
					ctx.fillStyle = "white"

				if (node.data.shape == 'dot') {
					gfx.oval(pt.x - w / 2, pt.y - w / 2, w, w, {
						fill : ctx.fillStyle,
						alpha : node.data.alpha
					})
					nodeBoxes[node.name] = [ pt.x - w / 2, pt.y - w / 2, w, w ]
				} else {
					gfx.rect(pt.x - w / 2, pt.y - 10, w, 20, 4, {
						fill : ctx.fillStyle,
						alpha : node.data.alpha
					})
					nodeBoxes[node.name] = [ pt.x - w / 2, pt.y - 11, w, 22 ]
				}

				// draw the text
				if (label) {
					ctx.font = "12px Helvetica"
					ctx.textAlign = "center"
					ctx.fillStyle = "white"
					if (node.data.color == 'none')
						ctx.fillStyle = '#333333'
					ctx.fillText(label || "", pt.x, pt.y + 4)
					ctx.fillText(label || "", pt.x, pt.y + 4)
				}
			})

			particleSystem
					.eachEdge(function(edge, pt1, pt2) {
						if (edge.source.data.alpha * edge.target.data.alpha == 0)
							return

						

						var weight = edge.data.weight
						var color = edge.data.color
						var r_at = (!isNaN(edge.target.data.alpha)) ? parseFloat(edge.target.data.alpha)
								: 1
						var r_as = (!isNaN(edge.source.data.alpha)) ? parseFloat(edge.source.data.alpha)
								: 1
						var r_a = (r_at < r_as) ? r_at : r_as
						var colortmp = (color) ? color : "#000000"
						var rgbaCol = 'rgba('
								+ parseInt(colortmp.slice(-6, -4), 16) + ','
								+ parseInt(colortmp.slice(-4, -2), 16) + ','
								+ parseInt(colortmp.slice(-2), 16) + ',' + r_a
								+ ')';

						if (!color || ("" + color).match(/^[ \t]*$/))
							color = null

							// find the start point
						var tail = intersect_line_box(pt1, pt2,
								nodeBoxes[edge.source.name])
						var head = intersect_line_box(tail, pt2,
								nodeBoxes[edge.target.name])

						ctx.save()
						ctx.beginPath()
						ctx.lineWidth = (!isNaN(weight)) ? parseFloat(weight)
								: 1

						ctx.strokeStyle = rgbaCol

						ctx.fillStyle = "rgba(0,0,0," + r_a + ")"

						ctx.moveTo(tail.x, tail.y)
						ctx.lineTo(head.x, head.y)
						ctx.font = "12px Helvetica"
						var text = (edge.data.name) ? edge.data.name : ""
						ctx.stroke()
						ctx.fillText(text, (pt1.x + pt2.x) / 2.0,
								(pt1.y + pt2.y) / 2.0);

						ctx.restore()

						// draw an arrowhead if this is a -> style edge
						if (edge.data.directed) {
							ctx.save()
							// move to the head position of the edge we just
							// drew
							var wt = !isNaN(weight) ? parseFloat(weight) : 1
							var arrowLength = 6 + wt
							var arrowWidth = 2 + wt
							ctx.fillStyle = rgbaCol
							ctx.translate(head.x, head.y);
							ctx.rotate(Math.atan2(head.y - tail.y, head.x
									- tail.x));

							// delete some of the edge that's already there (so
							// the point isn't hidden)
							ctx.clearRect(-arrowLength / 2, -wt / 2,
									arrowLength / 2, wt)

							// draw the chevron
							ctx.beginPath();
							ctx.moveTo(-arrowLength, arrowWidth);
							ctx.lineTo(0, 0);
							ctx.lineTo(-arrowLength, -arrowWidth);
							ctx.lineTo(-arrowLength * 0.8, -0);
							ctx.closePath();
							ctx.fill();
							ctx.restore()
						}
					})

		},

		initMouseHandling : function() {
			var dragged = null;

			var handler = {
				moved : function(e) {
					var pos = $(canvas).offset();
					_mouseP = arbor
							.Point(e.pageX - pos.left, e.pageY - pos.top)
							
				        var hovernode = particleSystem.nearest(_mouseP);
				        var arborEvent = new arborjs.Graph();
				        var mouseNodeName = hovernode ? hovernode.node.name : "none"
						arborEvent.mouseHoverNode(mouseNodeName,thatInstance);
				        that.redraw();
						return false
				},
				clicked : function(e) {

					
					
					var pos = $(canvas).offset();
					_mouseP = arbor
							.Point(e.pageX - pos.left, e.pageY - pos.top)
					selected = nearest = dragged = particleSystem
							.nearest(_mouseP);

					dragged = particleSystem.nearest(_mouseP);

					
					var nodeName = selected.node.name;
					var arborEvent = new arborjs.Graph();
					if(e.button === 2) {
						arborEvent.rightClickedNode(nodeName,thatInstance);
				    } else if (e.button === 1) {
				    	arborEvent.middleClickedNode(nodeName,thatInstance);
				    } else {
				    	arborEvent.clickedNode(nodeName,thatInstance);
				    }
					
					
					var link = selected.node.data.link
					
					if (link != null) {
						if (link.match(/^#/)) {
							$(that).trigger({
								type : "navigate",
								path : link.substr(1)
							})
						} else {
							window.location = link
						}
					} else {
						if (dragged.node !== null)
							dragged.node.fixed = true

						$(canvas).bind('mousemove', handler.dragged)
						$(window).bind('mouseup', handler.dropped)

					}

					return false
				},
				dragged : function(e) {
					var pos = $(canvas).offset();
					var s = arbor.Point(e.pageX - pos.left, e.pageY - pos.top)

					if (dragged && dragged.node !== null) {
						var p = particleSystem.fromScreen(s)
						dragged.node.p = p
					}

					return false
				},

				dropped : function(e) {
					if (dragged === null || dragged.node === undefined)
						return

						var nodeName = dragged.node.name;
						var arborEvent = new arborjs.Graph();
						if(e.button === 2){
							arborEvent.rightClickedReleaseNode(nodeName,thatInstance);
					    } else if (e.button === 1) {
					    	arborEvent.middleClickedReleaseNode(nodeName,thatInstance);
					    } else {
					    	arborEvent.clickedReleaseNode(nodeName,thatInstance);
					    }
						
						

					if (dragged.node !== null)
						dragged.node.fixed = false
					dragged.node.tempMass = 1000
					dragged = null
					$(canvas).unbind('mousemove', handler.dragged)
					$(window).unbind('mouseup', handler.dropped)
					_mouseP = null
					return false
				}
			}

			$(canvas).mousedown(handler.clicked);
			$(canvas).mousemove(handler.moved);

		},

	}
	// helpers for figuring out where to draw arrows (thanks springy.js)
	var intersect_line_line = function(p1, p2, p3, p4) {
		var denom = ((p4.y - p3.y) * (p2.x - p1.x) - (p4.x - p3.x)
				* (p2.y - p1.y));
		if (denom === 0)
			return false // lines are parallel
		var ua = ((p4.x - p3.x) * (p1.y - p3.y) - (p4.y - p3.y) * (p1.x - p3.x))
				/ denom;
		var ub = ((p2.x - p1.x) * (p1.y - p3.y) - (p2.y - p1.y) * (p1.x - p3.x))
				/ denom;

		if (ua < 0 || ua > 1 || ub < 0 || ub > 1)
			return false
		return arbor
				.Point(p1.x + ua * (p2.x - p1.x), p1.y + ua * (p2.y - p1.y));
	}

	var intersect_line_box = function(p1, p2, boxTuple) {
		var p3 = {
			x : boxTuple[0],
			y : boxTuple[1]
		}, w = boxTuple[2], h = boxTuple[3]

		var tl = {
			x : p3.x,
			y : p3.y
		};
		var tr = {
			x : p3.x + w,
			y : p3.y
		};
		var bl = {
			x : p3.x,
			y : p3.y + h
		};
		var br = {
			x : p3.x + w,
			y : p3.y + h
		};

		return intersect_line_line(p1, p2, tl, tr)
				|| intersect_line_line(p1, p2, tr, br)
				|| intersect_line_line(p1, p2, br, bl)
				|| intersect_line_line(p1, p2, bl, tl) || false
	}

	return that
}

function redrawArborjsGraph(theUI, graphName, javaGraphInstance) {
	var sys = arbor.ParticleSystem()
	sys.parameters({
		stiffness : 500,
		repulsion : 1000,
		gravity : false,
		dt : 0.015
	})
	sys.renderer = Renderer("#" + graphName, javaGraphInstance)
	sys.graft(theUI)

}