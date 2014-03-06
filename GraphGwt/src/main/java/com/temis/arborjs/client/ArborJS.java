package com.temis.arborjs.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

public class ArborJS extends GraphGWT {
	
	/* (non-Javadoc)
	 * @see com.temis.arborjs.client.GraphGWT#draw()
	 */
	@Override
	public void draw() {
		redrawGraph(name, jsonDataGraph.getJavaScriptObject(), this);
	}
	
	private native void redrawGraph(String graphName, JavaScriptObject graphData, GraphGWT arborjs) /*-{
																									$wnd.redrawGraph(graphData, graphName, arborjs);
																									}-*/;
	
	/* (non-Javadoc)
	 * @see com.temis.arborjs.client.GraphGWT#addNode(java.lang.String, java.lang.String, java.lang.String, java.lang.String, double, java.lang.String)
	 */
	@Override
	public void addNode(String name, String color, String shape, String label, double alpha, String link) {
		JSONObject nodes;
		if (jsonDataGraph.containsKey("nodes")) {
			nodes = (JSONObject) jsonDataGraph.get("nodes");
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
		jsonDataGraph.put("nodes", nodes);
	}
	
	/* (non-Javadoc)
	 * @see com.temis.arborjs.client.GraphGWT#addEdge(java.lang.String, java.lang.String, double, java.lang.String, java.lang.String, java.lang.Boolean)
	 */
	@Override
	public void addEdge(String sourceNodeName, String targetNodeName, double weight, String name, String color, Boolean directed) {
		JSONObject edges;
		if (jsonDataGraph.containsKey("edges")) {
			edges = (JSONObject) jsonDataGraph.get("edges");
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
		jsonDataGraph.put("edges", edges);
	}
	
	/* (non-Javadoc)
	 * @see com.temis.arborjs.client.GraphGWT#removeNode(java.lang.String)
	 */
	@Override
	public boolean removeNode(String name) {
		if (jsonDataGraph.containsKey("nodes")) {
			JSONObject nodes = (JSONObject) jsonDataGraph.get("nodes");
			if (nodes.containsKey(name)) {
				JSONObject node = (JSONObject) nodes.get(name);
				node.put("alpha", new JSONNumber(0));
				return true;
			}
		}
		return false;
	}
	
}
