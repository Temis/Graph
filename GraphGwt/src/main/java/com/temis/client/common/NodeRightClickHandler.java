package com.temis.client.common;

import com.google.gwt.json.client.JSONObject;

/**
 * Interface of the NodeRightClickHandler.
 * @author Quentin Fahaut
 *
 */
public interface NodeRightClickHandler {
	void onRightClick(String nodeName, GraphGWT o, JSONObject currentNodeData);
	
	void onRightClickRelease(String nodeName, GraphGWT o, JSONObject currentNodeData);
}
