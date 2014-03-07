package com.temis.client.common;

import com.google.gwt.json.client.JSONObject;

/**
 * Interface of the NodeClickHandler.
 * @author Quentin Fahaut
 *
 */
public interface NodeClickHandler {
	void onClick(String nodeName, GraphGWT o, JSONObject currentNodeData);
	
	void onClickRelease(String nodeName, GraphGWT o, JSONObject currentNodeData);
}
