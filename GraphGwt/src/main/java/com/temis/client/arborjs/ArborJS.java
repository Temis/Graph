package com.temis.client.arborjs;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.ui.Widget;
import com.temis.client.common.GraphGWT;
import com.temis.shared.Resources;

public class ArborJS extends GraphGWT {
	
	/* (non-Javadoc)
	 * @see com.temis.client.common.GraphGWT#getImplementationWidget(int, int, java.lang.String)
	 */
	@Override
	public Widget getImplementationWidget(int height, int width, String name) {
		Canvas canvas = Canvas.createIfSupported();
		canvas.setWidth(width + "px");
		canvas.setHeight(height + "px");
		canvas.setCoordinateSpaceWidth(width);
		canvas.setCoordinateSpaceHeight(height);
		canvas.getElement().setId(name);
		return canvas;
	}
	
	/* (non-Javadoc)
	 * @see com.temis.client.common.GraphGWT#draw()
	 */
	@Override
	public void draw() {
		ScriptInjector.fromString(Resources.INSTANCE.jquery().getText()).setWindow(ScriptInjector.TOP_WINDOW).inject();
		ScriptInjector.fromString(Resources.INSTANCE.jaddress().getText()).setWindow(ScriptInjector.TOP_WINDOW).inject();
		ScriptInjector.fromString(Resources.INSTANCE.arborTween().getText()).setWindow(ScriptInjector.TOP_WINDOW).inject();
		ScriptInjector.fromString(Resources.INSTANCE.graphics().getText()).setWindow(ScriptInjector.TOP_WINDOW).inject();
		ScriptInjector.fromString(Resources.INSTANCE.edit().getText()).setWindow(ScriptInjector.TOP_WINDOW).inject();
		ScriptInjector.fromString(Resources.INSTANCE.arbor().getText()).setWindow(ScriptInjector.TOP_WINDOW).inject();
		ScriptInjector.fromString(Resources.INSTANCE.mainarbor().getText()).setWindow(ScriptInjector.TOP_WINDOW).inject();
		redrawGraph(getName(), getJsonDataGraph().getJavaScriptObject(), this);
	}
	
	private native void redrawGraph(String graphName, JavaScriptObject graphData, GraphGWT arborjs) /*-{
																									$wnd.redrawArborjsGraph(graphData, graphName, arborjs);
																									}-*/;
	
	/* (non-Javadoc)
	 * @see com.temis.client.common.GraphGWT#addNode(java.lang.String, java.lang.String, java.lang.String, java.lang.String, double, java.lang.String)
	 */
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
	
	/* (non-Javadoc)
	 * @see com.temis.client.common.GraphGWT#addEdge(java.lang.String, java.lang.String, double, java.lang.String, java.lang.String, java.lang.Boolean)
	 */
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
	
	/* (non-Javadoc)
	 * @see com.temis.client.common.GraphGWT#removeNode(java.lang.String)
	 */
	@Override
	public boolean removeNode(String name) {
		if (getJsonDataGraph().containsKey("nodes")) {
			JSONObject nodes = (JSONObject) getJsonDataGraph().get("nodes");
			if (nodes.containsKey(name)) {
				JSONObject node = (JSONObject) nodes.get(name);
				node.put("alpha", new JSONNumber(0));
				return true;
			}
		}
		return false;
	}
	
}
