GraphGWT
========

Gwt wrapper for javascript graph library like Arborjs

GraphGWT is an open source project allowing to display and interact with a javascript graph library.

Currently, the wrapper has only one implementation of of the graph which is Arbrorjs javascript library: http://arborjs.org/

It can manage link on a node, transparency, color, direction on edge, weight edge, and mouse events.

#### How to make a graph
The graph can be configured with a json object like following :
``` java
  var data = { 
               nodes:{ 
                 temis:{'color':'red','shape':'rect','label':'Temis',        alpha:1,link:'http://www.temis.com/fr&#39;}, 
                 colombia:{'color':'green','shape':'dot','label':'colombia'}, 
                 paris:{'color':'blue','shape':'dot','label':'paris'}, 
                 luxidNav:{'color':CLR.goal,'shape':'dot','label':'luxidNav', alpha:0.4}, 
                 navigator:{'color':'green','shape':'dot','label':'navigator'}, 
                 luxid:{'color':'blue','shape':'dot','label':'luxid X'} 
               }, 
               edges:{ 
                 temis:{ colombia:{'weight':4.0, 'name':'headquarter', 'color':CLR.branch, 'directed':true},    paris:{'name':'subsidiary', 'color':CLR.branch, 'directed':false}}, 
                 paris:{ colombia:{'name':'vpn'},luxid:{} }, 
                 colombia:{ navigator:{} }, 
                 luxidNav:{ luxid:{'weight':4.0,'color':CLR.branch, 'directed':true},navigator:{} } 
               } 
             };
```
Also a java API existe to this step by step, here is the same graph with the API :

``` java
GraphGWT graph = new ArborJS();
graph.addNode("temis", "red", "rect", "Temis", 1, "http://www.temis.com/fr");
graph.addNode("colombia", "green", "dot", "Colombia");
graph.addNode("paris", "blue", "dot", "Paris");
graph.addNode("luxidNav", "#000000", "dot", "Lux-Nav", 0.4);
graph.addNode("navigator", "green", "dot", "Navigator");
graph.addNode("luxid", "blue", "dot", "Luxid X");
		
graph.addEdge("temis", "colombia", 4.0, "Headquarter", "#FFA500", true);
graph.addEdge("temis", "paris", "subsidiary", "#FFA500", false);

graph.addEdge("paris", "colombia", "vpn");
graph.addEdge("paris", "luxid");
		
graph.addEdge("colombia", "navigator");

graph.addEdge("luxidNav", "luxid", 4.0, null, "#FFA500", true);
graph.addEdge("luxidNav", "navigator");
```

And this is the result you will get with the previous code:

<img src=http://i.imgur.com/H4XrluA.png>

#### Using graphGWT in a GWT project



#### Maven central repository

