package com.temis.client.common;

import com.google.gwt.json.client.JSONObject;

/**
 * Interface of the NodeMouseOutHandler.
 * @author Quentin Fahaut
 *
 */
public interface NodeMouseOutHandler {
	void onMouseOut(String nodeName, GraphGWT o, JSONObject currentNodeData);
}
