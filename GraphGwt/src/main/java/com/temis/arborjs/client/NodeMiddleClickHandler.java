package com.temis.arborjs.client;

import com.google.gwt.json.client.JSONObject;

/**
 * Interface of the NodeMiddleClickHandler.
 * @author Quentin Fahaut
 *
 */
public interface NodeMiddleClickHandler {
	void onMiddleClick(String nodeName, GraphGWT o, JSONObject currentNodeData);
	
	void onMiddleClickRelease(String nodeName, GraphGWT o, JSONObject currentNodeData);
}
