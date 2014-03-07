package com.temis.arborjs.client;

import org.timepedia.exporter.client.ExporterUtil;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.ui.RootPanel;
import com.temis.client.common.GraphGWT;
import com.temis.client.common.NodeClickHandler;
import com.temis.client.common.NodeMiddleClickHandler;
import com.temis.client.common.NodeMouseHoverHandler;
import com.temis.client.common.NodeMouseOutHandler;
import com.temis.client.common.NodeRightClickHandler;
import com.temis.client.vivagraph.VivaGraph;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GraphTesterWar implements EntryPoint {
	
	@Override
	public void onModuleLoad() {
		VivaGraph graph = new VivaGraph();
		ExporterUtil.exportAll();
		RootPanel.get().add(graph);
		graph.addNode("temis", "red", "rect", "Temis", 1, "http://www.temis.com/fr");
		graph.addNode("colombia", "green", "dot", "Colombia");
		graph.addNode("paris", "blue", "dot", "Paris");
		graph.addNode("luxidNav", "#000000", "dot", "Lux-Nav", 0.4);
		graph.addNode("navigator", "green", "dot", "Navigator");
		graph.addNode("luxid", "blue", "91bad8ceeec43ae303790f8fe238164b");
		
		graph.addEdge("temis", "colombia", 4.0, "Headquarter", "#FFA500", true);
		graph.addEdge("temis", "paris", "subsidiary", "#FFA500", false);
		
		graph.addEdge("paris", "colombia", "vpn");
		graph.addEdge("paris", "luxid");
		
		graph.addEdge("colombia", "navigator");
		
		graph.addEdge("luxidNav", "luxid", 4.0, null, "#FFA500", true);
		graph.addEdge("luxidNav", "navigator");
		graph.draw();
		
		graph.addNodeMouseHoverHandler(new NodeMouseHoverHandler() {
			
			@Override
			public void onMouseHover(String nodeName, GraphGWT o, JSONObject currentNodeData) {
				System.out.println(nodeName);
			}
			
		});
		graph.addNodeMouseOutHandler(new NodeMouseOutHandler() {
			
			@Override
			public void onMouseOut(String nodeName, GraphGWT o, JSONObject currentNodeData) {
				System.out.println(nodeName);
			}
		});
		graph.addNodeClickHandler(new NodeClickHandler() {
			
			@Override
			public void onClickRelease(String nodeName, GraphGWT o, JSONObject currentNodeData) {
				System.out.println(nodeName + ": clickReleased");
				
			}
			
			@Override
			public void onClick(String nodeName, GraphGWT o, JSONObject currentNodeData) {
				System.out.println(nodeName + ": click");
				
			}
		});
		graph.addNodeRightClickHandler(new NodeRightClickHandler() {
			
			@Override
			public void onRightClickRelease(String nodeName, GraphGWT o, JSONObject currentNodeData) {
				System.out.println(nodeName + ": rightclickReleased");
				
			}
			
			@Override
			public void onRightClick(String nodeName, GraphGWT o, JSONObject currentNodeData) {
				System.out.println(nodeName + ": rightclick");
				
			}
		});
		graph.addNodeMiddleClickHandler(new NodeMiddleClickHandler() {
			
			@Override
			public void onMiddleClickRelease(String nodeName, GraphGWT o, JSONObject currentNodeData) {
				System.out.println(nodeName + ": middleclickReleased");
				
			}
			
			@Override
			public void onMiddleClick(String nodeName, GraphGWT o, JSONObject currentNodeData) {
				System.out.println(nodeName + ": middletclick");
			}
		});
		
		//		GraphGWT graph = new ArborJS();
		//		ExporterUtil.exportAll();
		//		RootPanel.get().add(graph);
		//		
		//		graph.addNodeClickHandler(new NodeClickHandler() {
		//			@Override
		//			public void onClick(String nodeName, GraphGWT o, JSONObject currentNodeData) {
		//				currentNodeData.put("color", new JSONString("red"));
		//			}
		//			
		//			@Override
		//			public void onClickRelease(String nodeName, GraphGWT o, JSONObject currentNodeData) {
		//				currentNodeData.put("color", new JSONString("blue"));
		//				
		//			}
		//			
		//		});
		//		
		//		graph.addNodeRightClickHandler(new NodeRightClickHandler() {
		//			@Override
		//			public void onRightClick(String nodeName, GraphGWT o, JSONObject currentNodeData) {
		//				currentNodeData.put("color", new JSONString("yellow"));
		//				
		//			}
		//			
		//			@Override
		//			public void onRightClickRelease(String nodeName, GraphGWT o, JSONObject currentNodeData) {
		//				o.removeNode(nodeName);
		//				
		//			}
		//		});
		//		
		//		graph.addNodeMiddleClickHandler(new NodeMiddleClickHandler() {
		//			
		//			@Override
		//			public void onMiddleClickRelease(String nodeName, GraphGWT o, JSONObject currentNodeData) {
		//				currentNodeData.put("color", new JSONString("grey"));
		//				
		//			}
		//			
		//			@Override
		//			public void onMiddleClick(String nodeName, GraphGWT o, JSONObject currentNodeData) {
		//				currentNodeData.put("color", new JSONString("black"));
		//				
		//			}
		//		});
		//		
		//		graph.addNodeMouseHoverHandler(new NodeMouseHoverHandler() {
		//			
		//			@Override
		//			public void onMouseHover(String nodeName, GraphGWT o, JSONObject currentNodeData) {
		//				currentNodeData.put("color", new JSONString("orange"));
		//				
		//			}
		//		});
		//		graph.addNodeMouseOutHandler(new NodeMouseOutHandler() {
		//			
		//			@Override
		//			public void onMouseOut(String nodeName, GraphGWT o, JSONObject currentNodeData) {
		//				currentNodeData.put("color", new JSONString("purple"));
		//			}
		//		});
		//		graph.addNode("temis", "red", "rect", "Temis", 1, "http://www.temis.com/fr");
		//		graph.addNode("colombia", "green", "dot", "Colombia");
		//		graph.addNode("paris", "blue", "dot", "Paris");
		//		graph.addNode("luxidNav", "#000000", "dot", "Lux-Nav", 0.4);
		//		graph.addNode("navigator", "green", "dot", "Navigator");
		//		graph.addNode("luxid", "blue", "dot", "Luxid X");
		//		
		//		graph.addEdge("temis", "colombia", 4.0, "Headquarter", "#FFA500", true);
		//		graph.addEdge("temis", "paris", "subsidiary", "#FFA500", false);
		//		
		//		graph.addEdge("paris", "colombia", "vpn");
		//		graph.addEdge("paris", "luxid");
		//		
		//		graph.addEdge("colombia", "navigator");
		//		
		//		graph.addEdge("luxidNav", "luxid", 4.0, null, "#FFA500", true);
		//		graph.addEdge("luxidNav", "navigator");
		//		
		//		graph.draw();
	}
}
