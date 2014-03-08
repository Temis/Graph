package com.temis.client.common;

import org.timepedia.exporter.client.Exportable;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.ui.SimplePanel;

public abstract class GraphEvent extends SimplePanel implements Exportable {
	
	public abstract void clickedNode(String nodeName, GraphGWT o);
	
	public abstract void clickedReleaseNode(String nodeName, GraphGWT o);
	
	public abstract void rightClickedNode(String nodeName, GraphGWT o);
	
	public abstract void rightClickedReleaseNode(String nodeName, GraphGWT o);
	
	public abstract void middleClickedNode(String nodeName, GraphGWT o);
	
	public abstract void middleClickedReleaseNode(String nodeName, GraphGWT o);
	
	public abstract void mouseHoverNode(String nodeName, GraphGWT o);
	
	public abstract void mouseOutNode(String nodeName, GraphGWT o);
	
	public JSONObject getCurrentNodeJsonData(String nodeName, GraphGWT o) {
		JSONObject currentNodeData = new JSONObject();
		if (o.getJsonDataGraph().containsKey("nodes")) {
			JSONObject nodes = (JSONObject) o.getJsonDataGraph().get("nodes");
			if (nodes.containsKey(nodeName)) {
				currentNodeData = (JSONObject) nodes.get(nodeName);
			}
		}
		return currentNodeData;
	}
}
