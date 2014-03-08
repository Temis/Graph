package com.temis.client.arborjs;

import org.timepedia.exporter.client.Export;
import org.timepedia.exporter.client.ExportPackage;

import com.google.gwt.json.client.JSONObject;
import com.temis.client.common.GraphEvent;
import com.temis.client.common.GraphGWT;

@ExportPackage("arborjs")
@Export("Graph")
public class ArborJsEvent extends GraphEvent {
	
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
			if (!"none".equals(o.getCurrentHoverNodeName()) && "none".equals(nodeName)) {
				mouseOutNode(o.getCurrentHoverNodeName(), o);
			}
			else if (!"none".equals(nodeName) && !nodeName.equals(o.getCurrentHoverNodeName())) {
				o.setCurrentHoverNodeName(nodeName);
				JSONObject currentNodeData = getCurrentNodeJsonData(nodeName, o);
				o.getNodeMouseHoverHandler().onMouseHover(nodeName, o, currentNodeData);
				if (o.getNodeHandler() != null) {
					o.getNodeHandler().onMouseHover(nodeName, o, currentNodeData);
				}
			}
		}
		
	}
	
	@Override
	public void mouseOutNode(String nodeName, GraphGWT o) {
		if (o.getNodeMouseOutHandler() != null) {
			JSONObject currentNodeData = getCurrentNodeJsonData(nodeName, o);
			o.getNodeMouseOutHandler().onMouseOut(nodeName, o, currentNodeData);
			o.setCurrentHoverNodeName("none");
		}
	}
}
