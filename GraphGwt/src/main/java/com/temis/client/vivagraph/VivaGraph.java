package com.temis.client.vivagraph;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.temis.client.common.GraphGWT;

public class VivaGraph extends GraphGWT {
	
	@Override
	public Widget getImplementationWidget(int height, int width, String name) {
		SimplePanel widget = new SimplePanel();
		widget.getElement().setId(name);
		widget.addStyleName("vivagraphcss");
		return widget;
	}
	
	@Override
	public void draw() {
		redrawGraph(getName(), getJsonDataGraph().getJavaScriptObject(), this);
	}
	
	private native void redrawGraph(String graphName, JavaScriptObject graphData, GraphGWT arborjs) /*-{
																									$wnd.redrawGraph(graphData, graphName, arborjs);
																									}-*/;
	
	@Override
	public boolean removeNode(String name) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * Add a node into the graph.
	 * @param name reference 
	 * @param displayed name node
	 * @param image picture of the node
	 */
	public void addNode(String name, String label, String image) {
		JSONObject nodes;
		if (getJsonDataGraph().containsKey("nodes")) {
			nodes = (JSONObject) getJsonDataGraph().get("nodes");
		}
		else {
			nodes = new JSONObject();
		}
		JSONObject node = new JSONObject();
		node.put("label", new JSONString(label));
		node.put("image", new JSONString(image));
		nodes.put(name, node);
		getJsonDataGraph().put("nodes", nodes);
	}
	
	@Override
	public void addNode(String name, String color, String shape, String label, double alpha, String link) {
		JSONObject nodes;
		if (getJsonDataGraph().containsKey("nodes")) {
			nodes = (JSONObject) getJsonDataGraph().get("nodes");
		}
		else {
			nodes = new JSONObject();
		}
		JSONObject node = new JSONObject();
		node.put("color", new JSONString(color));
		node.put("shape", new JSONString(shape));
		node.put("label", new JSONString(label));
		node.put("alpha", new JSONNumber(alpha));
		if (link != null) {
			node.put("link", new JSONString(link));
		}
		nodes.put(name, node);
		getJsonDataGraph().put("nodes", nodes);
		
	}
	
	@Override
	public void addEdge(String sourceNodeName, String targetNodeName, double weight, String name, String color, Boolean directed) {
		JSONObject edges;
		if (getJsonDataGraph().containsKey("edges")) {
			edges = (JSONObject) getJsonDataGraph().get("edges");
		}
		else {
			edges = new JSONObject();
		}
		JSONObject edge;
		if (edges.containsKey(sourceNodeName)) {
			edge = (JSONObject) edges.get(sourceNodeName);
		}
		else {
			edge = new JSONObject();
		}
		
		JSONObject edgeData = new JSONObject();
		
		if (directed != null) {
			edgeData.put("directed", new JSONString(String.valueOf(directed)));
		}
		if (color != null) {
			edgeData.put("color", new JSONString(color));
		}
		if (name != null) {
			edgeData.put("name", new JSONString(name));
		}
		edgeData.put("weight", new JSONNumber(weight));
		
		edge.put(targetNodeName, edgeData);
		
		edges.put(sourceNodeName, edge);
		getJsonDataGraph().put("edges", edges);
	}
	
}
