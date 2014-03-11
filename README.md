GraphGWT
========
GraphGWT is an open source project allowing to display and interact with a javascript graph library.

Currently, the wrapper has 2 javascripts implementations of of the graph: arborjs and vivagraph:
 - http://arborjs.org/
 - https://github.com/anvaka/VivaGraphJS

It can manage link on a node, transparency, color, direction on edge, weight edge, and mouse events.


Lets see how to make an gwt objet :
If you want to use arborjs lib :
``` java
GraphGWT graph = new ArborJS();
```

If you want to use vivagraph lib :
``` java
GraphGWT graph = new VivaGraph();
```

### How to make a graph
The graph can be configured with a json object like following :
```json
{
   "nodes":{
      "europe":{
         "color":"red",
         "shape":"rect",
         "label":"Europe",
         "alpha":1,
         "link":"http://fr.wikipedia.org/wiki/Europe"
      },
      "asia":{
         "color":"red",
         "shape":"rect",
         "label":"Asia",
         "alpha":1,
         "link":"http://fr.wikipedia.org/wiki/Asie"
      },
      "america":{
         "color":"red",
         "shape":"rect",
         "label":"America",
         "alpha":1,
         "link":"http://fr.wikipedia.org/wiki/Amerique"
      },
      "france":{
         "color":"green",
         "shape":"dot",
         "label":"France",
         "alpha":1
      },
      "germany":{
         "color":"green",
         "shape":"dot",
         "label":"Germany",
         "alpha":1
      },
      "italy":{
         "color":"green",
         "shape":"dot",
         "label":"Italy",
         "alpha":1
      },
      "spain":{
         "color":"green",
         "shape":"dot",
         "label":"spain",
         "alpha":1
      },
      "uk":{
         "color":"green",
         "shape":"dot",
         "label":"UK",
         "alpha":1
      },
      "etce":{
         "color":"#BB2BFF",
         "shape":"dot",
         "label":"...",
         "alpha":1
      },
      "na":{
         "color":"green",
         "shape":"dot",
         "label":"North America",
         "alpha":1
      },
      "canada":{
         "color":"green",
         "shape":"dot",
         "label":"Canada",
         "alpha":1
      },
      "chili":{
         "color":"green",
         "shape":"dot",
         "label":"Chili",
         "alpha":1
      },
      "brasil":{
         "color":"green",
         "shape":"dot",
         "label":"Brasil",
         "alpha":1
      },
      "etca":{
         "color":"#BB2BFF",
         "shape":"dot",
         "label":"...",
         "alpha":1
      },
      "china":{
         "color":"green",
         "shape":"dot",
         "label":"China",
         "alpha":1
      },
      "japan":{
         "color":"green",
         "shape":"dot",
         "label":"Japan",
         "alpha":1
      },
      "korea":{
         "color":"green",
         "shape":"dot",
         "label":"Korea",
         "alpha":1
      },
      "etcas":{
         "color":"#BB2BFF",
         "shape":"dot",
         "label":"...",
         "alpha":1
      }
   },
   "edges":{
      "america":{
         "na":{
            "directed":"true",
            "color":"#FFA500",
            "name":"$",
            "weight":1
         },
         "canada":{
            "directed":"true",
            "color":"#FFA500",
            "name":"$",
            "weight":1
         },
         "chili":{
            "directed":"true",
            "color":"#FFA500",
            "weight":1
         },
         "brasil":{
            "directed":"true",
            "color":"#FFA500",
            "weight":1
         },
         "etca":{
            "directed":"true",
            "color":"#0000FF",
            "weight":4
         }
      },
      "europe":{
         "france":{
            "directed":"true",
            "color":"#FFA500",
            "name":"euro",
            "weight":1
         },
         "germany":{
            "directed":"true",
            "color":"#FFA500",
            "name":"euro",
            "weight":1
         },
         "italy":{
            "directed":"true",
            "color":"#FFA500",
            "name":"euro",
            "weight":1
         },
         "spain":{
            "directed":"true",
            "color":"#FFA500",
            "name":"euro",
            "weight":1
         },
         "etce":{
            "directed":"true",
            "color":"#0000FF",
            "name":"euro",
            "weight":4
         },
         "uk":{
            "directed":"true",
            "color":"#FFA500",
            "name":"?",
            "weight":1
         },
         "america":{
            "weight":1
         },
         "asia":{
            "weight":1
         }
      },
      "asia":{
         "china":{
            "weight":1
         },
         "japan":{
            "weight":1
         },
         "korea":{
            "weight":1
         },
         "etcas":{
            "directed":"false",
            "color":"#0000FF",
            "weight":4
         },
         "america":{
            "weight":1
         }
      }
   }
}
```


Also a java API exist to do this step by step, here is the same graph with the API 

``` java
graph.addNode("europe", "red", "rect", "Europe", 1, "http://fr.wikipedia.org/wiki/Europe");
graph.addNode("asia", "red", "rect", "Asia", 1, "http://fr.wikipedia.org/wiki/Asie");
graph.addNode("america", "red", "rect", "America", 1, "http://fr.wikipedia.org/wiki/Amerique");
		
graph.addNode("france", "green", "dot", "France");
graph.addNode("germany", "green", "dot", "Germany");
graph.addNode("italy", "green", "dot", "Italy");
graph.addNode("spain", "green", "dot", "spain");
graph.addNode("uk", "green", "dot", "UK");
graph.addNode("etce", "#BB2BFF", "dot", "...");

graph.addNode("na", "green", "dot", "North America");
graph.addNode("canada", "green", "dot", "Canada");
graph.addNode("chili", "green", "dot", "Chili");
graph.addNode("brasil", "green", "dot", "Brasil");
graph.addNode("etca", "#BB2BFF", "dot", "...");
	
graph.addNode("china", "green", "dot", "China");
graph.addNode("japan", "green", "dot", "Japan");
graph.addNode("korea", "green", "dot", "Korea");
graph.addNode("etcas", "#BB2BFF", "dot", "...");
		
graph.addEdge("america", "na", "$", "#FFA500", true);
graph.addEdge("america", "canada", "$", "#FFA500", true);
graph.addEdge("america", "chili", null, "#FFA500", true);
graph.addEdge("america", "brasil", null, "#FFA500", true);
graph.addEdge("america", "etca", 4.0, null, "#0000FF", true);
		
graph.addEdge("europe", "france", "euro", "#FFA500", true);
graph.addEdge("europe", "germany", "euro", "#FFA500", true);
graph.addEdge("europe", "italy", "euro", "#FFA500", true);
graph.addEdge("europe", "spain", "euro", "#FFA500", true);
graph.addEdge("europe", "etce", 4.0, "euro", "#0000FF", true);
graph.addEdge("europe", "uk", "�", "#FFA500", true);
	
graph.addEdge("asia", "china");
graph.addEdge("asia", "japan");
graph.addEdge("asia", "korea");
graph.addEdge("asia", "etcas", 4.0, null, "#0000FF", false);
		
graph.addEdge("europe", "america");
graph.addEdge("europe", "asia");
graph.addEdge("asia", "america");
		
```

And this is the result you will get with the previous code:

With ArborJs :

<img src=http://i.imgur.com/EKQt0dp.png>

With VivaGraph

<img src=http://i.imgur.com/dLJEyfd.png>


#### Using graphGWT in a GWT project
Create a folder named "public" next to the gwt.xml file. Add the file [arbor.js](https://raw.github.com/Temis/Graph/master/GraphGwt/src/main/java/com/temis/shared/arbor.js)
in the gwt.xml file, add these lines :
```xml
<public path="public" />
<script src="arbor.js" />
<inherits name='com.temis.GraphGwt' />
```
In your onLoadModule specify to export graphgwt method into javascript, just like this:
```java
public void onModuleLoad() {
		ExporterUtil.exportAll(); 
```
### Maven central repository
#### Gradle 
`compile 'com.temis:GraphGwt:1.0.1'`
#### Maven
```xml
<dependency>
 <groupId>com.temis</groupId>
  <artifactId>GraphGwt</artifactId>
  <version>1.0.1</version>
</dependency>
```

### Release notes
#### v1.0.1
 - fix dependencies
 -  Add support for vivagraph (but not modification of the graph once its displayed

#### v1.0.0
 - First version of the project, using of arbor.js


### Disclaimer
Due to a bug of event in the lib vivagraph, there is not support for changing a node following an event.
We can display a graph get any event but we can't change the graph display fllowing an event.
https://github.com/anvaka/VivaGraphJS/issues/77
