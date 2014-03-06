package com.temis.arborjs.client;

import org.timepedia.exporter.client.Export;
import org.timepedia.exporter.client.ExportPackage;

import com.google.gwt.json.client.JSONObject;

@ExportPackage("arborjs")
@Export("Graph")
public class ArborJsEvent extends GraphEvent {
	
	@Override
	@Export("clickedNode")
	public void clickedNode(String nodeName, GraphGWT o) {
		if (o.nodeClickHandler != null) {
			JSONObject currentNodeData = getCurrentNodeJsonData(nodeName, o);
			o.nodeClickHandler.onClick(nodeName, o, currentNodeData);
			if (o.nodeHandler != null) {
				o.nodeHandler.onClick(nodeName, o, currentNodeData);
			}
		}
	}
	
	@Override
	@Export("clickedReleaseNode")
	public void clickedReleaseNode(String nodeName, GraphGWT o) {
		if (o.nodeClickHandler != null) {
			JSONObject currentNodeData = getCurrentNodeJsonData(nodeName, o);
			o.nodeClickHandler.onClickRelease(nodeName, o, currentNodeData);
			if (o.nodeHandler != null) {
				o.nodeHandler.onClickRelease(nodeName, o, currentNodeData);
			}
		}
	}
	
	@Override
	@Export("rightClickedNode")
	public void rightClickedNode(String nodeName, GraphGWT o) {
		if (o.nodeRightClickHandler != null) {
			JSONObject currentNodeData = getCurrentNodeJsonData(nodeName, o);
			o.nodeRightClickHandler.onRightClick(nodeName, o, currentNodeData);
			if (o.nodeHandler != null) {
				o.nodeHandler.onRightClick(nodeName, o, currentNodeData);
			}
		}
	}
	
	@Override
	@Export("rightClickedReleaseNode")
	public void rightClickedReleaseNode(String nodeName, GraphGWT o) {
		if (o.nodeRightClickHandler != null) {
			JSONObject currentNodeData = getCurrentNodeJsonData(nodeName, o);
			o.nodeRightClickHandler.onRightClickRelease(nodeName, o, currentNodeData);
			if (o.nodeHandler != null) {
				o.nodeHandler.onRightClickRelease(nodeName, o, currentNodeData);
			}
		}
	}
	
	@Override
	@Export("middleClickedNode")
	public void middleClickedNode(String nodeName, GraphGWT o) {
		if (o.nodeMiddleClickHandler != null) {
			JSONObject currentNodeData = getCurrentNodeJsonData(nodeName, o);
			o.nodeMiddleClickHandler.onMiddleClick(nodeName, o, currentNodeData);
			if (o.nodeHandler != null) {
				o.nodeHandler.onMiddleClick(nodeName, o, currentNodeData);
			}
		}
	}
	
	@Override
	@Export("middleClickedReleaseNode")
	public void middleClickedReleaseNode(String nodeName, GraphGWT o) {
		if (o.nodeMiddleClickHandler != null) {
			JSONObject currentNodeData = getCurrentNodeJsonData(nodeName, o);
			o.nodeMiddleClickHandler.onMiddleClickRelease(nodeName, o, currentNodeData);
			if (o.nodeHandler != null) {
				o.nodeHandler.onMiddleClickRelease(nodeName, o, currentNodeData);
			}
		}
	}
	
	@Override
	@Export("mouseHoverNode")
	public void mouseHoverNode(String nodeName, GraphGWT o) {
		if (o.nodeMouseHoverHandler != null) {
			if (!"none".equals(o.getCurrentHoverNodeName()) && "none".equals(nodeName)) {
				mouseOutNode(o.getCurrentHoverNodeName(), o);
			}
			else if (!"none".equals(nodeName) && !nodeName.equals(o.getCurrentHoverNodeName())) {
				o.setCurrentHoverNodeName(nodeName);
				JSONObject currentNodeData = getCurrentNodeJsonData(nodeName, o);
				o.nodeMouseHoverHandler.onMouseHover(nodeName, o, currentNodeData);
				if (o.nodeHandler != null) {
					o.nodeHandler.onMouseHover(nodeName, o, currentNodeData);
				}
			}
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
	
	@Override
	public void mouseOutNode(String nodeName, GraphGWT o) {
		if (o.nodeMouseOutHandler != null) {
			JSONObject currentNodeData = getCurrentNodeJsonData(nodeName, o);
			o.nodeMouseOutHandler.onMouseOut(nodeName, o, currentNodeData);
			o.setCurrentHoverNodeName("none");
		}
	}
}
