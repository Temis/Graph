package com.temis.arborjs.client;

import com.google.gwt.json.client.JSONObject;

/**
 * Interface of the NodeMouseHoverHandler.
 * @author Quentin Fahaut
 *
 */
public interface NodeMouseHoverHandler {
	void onMouseHover(String nodeName, GraphGWT o, JSONObject currentNodeData);
}
