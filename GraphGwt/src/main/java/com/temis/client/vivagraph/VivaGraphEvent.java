package com.temis.client.vivagraph;

import org.timepedia.exporter.client.Export;
import org.timepedia.exporter.client.ExportPackage;

import com.google.gwt.json.client.JSONObject;
import com.temis.client.common.GraphEvent;
import com.temis.client.common.GraphGWT;

@ExportPackage("vivagraph")
@Export("Graph")
public class VivaGraphEvent extends GraphEvent {
	
	@Override
	@Export("clickedNode")
	public void clickedNode(String nodeName, GraphGWT o) {
		if (o.getNodeClickHandler() != null) {
			JSONObject currentNodeData = getCurrentNodeJsonData(nodeName, o);
			o.getNodeClickHandler().onClick(nodeName, o, currentNodeData);
			if (o.getNodeHandler() != null) {
				o.getNodeHandler().onClick(nodeName, o, currentNodeData);
			}
		}
	}
	
	@Override
	@Export("clickedReleaseNode")
	public void clickedReleaseNode(String nodeName, GraphGWT o) {
		if (o.getNodeClickHandler() != null) {
			JSONObject currentNodeData = getCurrentNodeJsonData(nodeName, o);
			o.getNodeClickHandler().onClickRelease(nodeName, o, currentNodeData);
			if (o.getNodeHandler() != null) {
				o.getNodeHandler().onClickRelease(nodeName, o, currentNodeData);
			}
		}
	}
	
	@Override
	@Export("rightClickedNode")
	public void rightClickedNode(String nodeName, GraphGWT o) {
		if (o.getNodeRightClickHandler() != null) {
			JSONObject currentNodeData = getCurrentNodeJsonData(nodeName, o);
			o.getNodeRightClickHandler().onRightClick(nodeName, o, currentNodeData);
			if (o.getNodeHandler() != null) {
				o.getNodeHandler().onRightClick(nodeName, o, currentNodeData);
			}
		}
	}
	
	@Override
	@Export("rightClickedReleaseNode")
	public void rightClickedReleaseNode(String nodeName, GraphGWT o) {
		if (o.getNodeRightClickHandler() != null) {
			JSONObject currentNodeData = getCurrentNodeJsonData(nodeName, o);
			o.getNodeRightClickHandler().onRightClickRelease(nodeName, o, currentNodeData);
			if (o.getNodeHandler() != null) {
				o.getNodeHandler().onRightClickRelease(nodeName, o, currentNodeData);
			}
		}
	}
	
	@Override
	@Export("middleClickedNode")
	public void middleClickedNode(String nodeName, GraphGWT o) {
		if (o.getNodeMiddleClickHandler() != null) {
			JSONObject currentNodeData = getCurrentNodeJsonData(nodeName, o);
			o.getNodeMiddleClickHandler().onMiddleClick(nodeName, o, currentNodeData);
			if (o.getNodeHandler() != null) {
				o.getNodeHandler().onMiddleClick(nodeName, o, currentNodeData);
			}
		}
	}
	
	@Override
	@Export("middleClickedReleaseNode")
	public void middleClickedReleaseNode(String nodeName, GraphGWT o) {
		if (o.getNodeMiddleClickHandler() != null) {
			JSONObject currentNodeData = getCurrentNodeJsonData(nodeName, o);
			o.getNodeMiddleClickHandler().onMiddleClickRelease(nodeName, o, currentNodeData);
			if (o.getNodeHandler() != null) {
				o.getNodeHandler().onMiddleClickRelease(nodeName, o, currentNodeData);
			}
		}
	}
	
	@Override
	@Export("mouseHoverNode")
	public void mouseHoverNode(String nodeName, GraphGWT o) {
		if (o.getNodeMouseHoverHandler() != null) {
			JSONObject currentNodeData = getCurrentNodeJsonData(nodeName, o);
			o.getNodeMouseHoverHandler().onMouseHover(nodeName, o, currentNodeData);
		}
	}
	
	@Override
	@Export("mouseOutNode")
	public void mouseOutNode(String nodeName, GraphGWT o) {
		if (o.getNodeMouseOutHandler() != null) {
			JSONObject currentNodeData = getCurrentNodeJsonData(nodeName, o);
			o.getNodeMouseOutHandler().onMouseOut(nodeName, o, currentNodeData);
		}
	}
	
	private JSONObject getCurrentNodeJsonData(String nodeName, GraphGWT o) {
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
