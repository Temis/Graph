package com.temis.client.common;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Object GraphGWT wrapping a javascript Graph (basically an arborjs graph).
 * @author Quentin Flahaut
 *
 */
public abstract class GraphGWT extends SimplePanel {
	String name;
	int height = 500;
	int width = 600;
	NodeHandler nodeHandler = null;
	NodeClickHandler nodeClickHandler = null;
	NodeRightClickHandler nodeRightClickHandler = null;
	NodeMiddleClickHandler nodeMiddleClickHandler = null;
	NodeMouseHoverHandler nodeMouseHoverHandler = null;
	NodeMouseOutHandler nodeMouseOutHandler = null;
	JSONObject jsonDataGraph = new JSONObject();
	String currentHoverNodeName = "none";
	
	/**
	 * Basic constructor for the graphGWT
	 */
	public GraphGWT() {
		this("graph" + System.currentTimeMillis());
	}
	
	/**
	 * Constructor of the graphGWT specifying the name of its id (only needed to interact from javascript external script).
	 */
	public GraphGWT(String name) {
		this(name, 500, 600);
	}
	
	/**
	 * Constructor of the graphGWT.
	 * @param name of its id (only needed to interact from javascript external script).
	 * @param height of its canvas
	 * @param width of its canvas
	 */
	public GraphGWT(String name, int height, int width) {
		super();
		this.name = name;
		this.height = height;
		this.width = width;
		setWidget(getImplementationWidget(height, width, name));
	}
	
	/**
	 * Generate the widget linked to the Graph.
	 * @param height
	 * @param width
	 * @param name
	 * @return
	 */
	public abstract Widget getImplementationWidget(int height, int width, String name);
	
	/**
	 * Draw the graph.
	 */
	public abstract void draw();
	
	/**
	 * Remove a node in the graph and all its dependency edges.
	 * @param name node reference name
	 * @return true if node has been removed
	 */
	public abstract boolean removeNode(String name);
	
	/**
	 * Add a node into the graph.
	 * @param name reference 
	 * @param color node
	 * @param shape (rect or dot)
	 * @param label displayed name node
	 * @param alpha transparency node
	 * @param link url associated to the node
	 */
	public abstract void addNode(String name, String color, String shape, String label, double alpha, String link);
	
	/**
	 * Add a node into the graph.
	 * @param name reference
	 * @param color node
	 * @param shape (rect or dot)
	 * @param label displayed name node
	 * @param link url associated to the node
	 */
	public void addNode(String name, String color, String shape, String label, String link) {
		addNode(name, color, shape, label, 1, link);
	}
	
	/**
	 * Add a node into the graph.
	 * @param name reference
	 * @param color node
	 * @param shape (rect or dot)
	 * @param label displayed name node
	 * @param alpha transparency node
	 */
	public void addNode(String name, String color, String shape, String label, double alpha) {
		addNode(name, color, shape, label, alpha, null);
	}
	
	/**
	 * Add a node into the graph.
	 * @param name reference
	 * @param color node
	 * @param shape (rect or dot)
	 * @param label displayed name node
	 */
	public void addNode(String name, String color, String shape, String label) {
		addNode(name, color, shape, label, 1, null);
	}
	
	/**
	 * Add an edge into the graph.
	 * @param sourceNodeName the node reference name from where the link start
	 * @param targetNodeName the node reference name where the link end
	 * @param weight the width of the link
	 * @param name the displayed name of the edge
	 * @param color of the node
	 * @param directed (true or false) true display the direction of the link
	 */
	public abstract void addEdge(String sourceNodeName, String targetNodeName, double weight, String name, String color, Boolean directed);
	
	/**
	 * Add an edge into the graph.
	 * @param sourceNodeName the node reference name from where the link start
	 * @param targetNodeName the node reference name where the link end
	 * @param name the displayed name of the edge
	 * @param color of the node
	 * @param directed (true or false) true display the direction of the link
	 */
	public void addEdge(String sourceNodeName, String targetNodeName, String name, String color, boolean directed) {
		addEdge(sourceNodeName, targetNodeName, 1, name, color, directed);
	}
	
	/**
	 * Add an edge into the graph.
	 * @param sourceNodeName the node reference name from where the link start
	 * @param targetNodeName the node reference name where the link end
	 * @param name the displayed name of the edge
	 * @param color of the node
	 */
	public void addEdge(String sourceNodeName, String targetNodeName, String name, String color) {
		addEdge(sourceNodeName, targetNodeName, 1, name, color, null);
	}
	
	/**
	 * Add an edge into the graph.
	 * @param sourceNodeName the node reference name from where the link start
	 * @param targetNodeName the node reference name where the link end
	 * @param name the displayed name of the edge
	 */
	public void addEdge(String sourceNodeName, String targetNodeName, String name) {
		addEdge(sourceNodeName, targetNodeName, 1, name, null, null);
	}
	
	/**
	 * Add an edge into the graph.
	 * @param sourceNodeName the node reference name from where the link start
	 * @param targetNodeName the node reference name where the link end
	 */
	public void addEdge(String sourceNodeName, String targetNodeName) {
		addEdge(sourceNodeName, targetNodeName, 1, null, null, null);
	}
	
	/**
	 * Get a json object representing the data of the graph.
	 * @return a JSONObject
	 */
	public JSONObject getJsonDataGraph() {
		return jsonDataGraph;
	}
	
	/**
	 * Add a json object representing the data of the graph.
	 * @param jsonDataGraph as a JSONObject
	 */
	public void setJsonDataGraph(JSONObject jsonDataGraph) {
		this.jsonDataGraph = jsonDataGraph;
	}
	
	/**
	 * Add a nodeClickHandler handling the click on a node.
	 * @param nodeClickHandler
	 */
	public void addNodeClickHandler(NodeClickHandler nodeClickHandler) {
		this.nodeClickHandler = nodeClickHandler;
	}
	
	/**
	 * Add a nodeRightClickHandler handling the right click on a node.
	 * @param nodeRightClickHandler
	 */
	public void addNodeRightClickHandler(NodeRightClickHandler nodeRightClickHandler) {
		this.nodeRightClickHandler = nodeRightClickHandler;
	}
	
	/**
	 * Add a nodeMiddleClickHandler handling the middle click on a node.
	 * @param nodeMiddleClickHandler
	 */
	public void addNodeMiddleClickHandler(NodeMiddleClickHandler nodeMiddleClickHandler) {
		this.nodeMiddleClickHandler = nodeMiddleClickHandler;
	}
	
	/**
	 * Add a nodeMouseHoverHandler handling the mouse enter of a node area.
	 * @param nodeMouseHoverHandler
	 */
	public void addNodeMouseHoverHandler(NodeMouseHoverHandler nodeMouseHoverHandler) {
		this.nodeMouseHoverHandler = nodeMouseHoverHandler;
	}
	
	/**
	 * Add a NodeMouseOutHandler handling the mouse leaving a node area.
	 * @param nodeMouseOutHandler
	 */
	public void addNodeMouseOutHandler(NodeMouseOutHandler nodeMouseOutHandler) {
		this.nodeMouseOutHandler = nodeMouseOutHandler;
	}
	
	/**
	 * Add a NodeHandler handling every event on a node.
	 * @param nodeHandler
	 */
	public void addNodeHandler(NodeHandler nodeHandler) {
		this.nodeHandler = nodeHandler;
	}
	
	/**
	 * Get the reference name of the node currently under the mouse.
	 * @return the reference name node.
	 */
	public String getCurrentHoverNodeName() {
		return currentHoverNodeName;
	}
	
	/**
	 * Set the reference name of the node currently under the mouse.
	 */
	public void setCurrentHoverNodeName(String currentHoverNodeName) {
		this.currentHoverNodeName = currentHoverNodeName;
	}
	
	public NodeHandler getNodeHandler() {
		return nodeHandler;
	}
	
	public NodeClickHandler getNodeClickHandler() {
		return nodeClickHandler;
	}
	
	public NodeRightClickHandler getNodeRightClickHandler() {
		return nodeRightClickHandler;
	}
	
	public NodeMiddleClickHandler getNodeMiddleClickHandler() {
		return nodeMiddleClickHandler;
	}
	
	public NodeMouseHoverHandler getNodeMouseHoverHandler() {
		return nodeMouseHoverHandler;
	}
	
	public NodeMouseOutHandler getNodeMouseOutHandler() {
		return nodeMouseOutHandler;
	}
	
	public String getName() {
		return name;
	}
}
