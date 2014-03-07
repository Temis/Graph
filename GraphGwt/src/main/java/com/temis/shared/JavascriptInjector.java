package com.temis.shared;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.HeadElement;
import com.google.gwt.dom.client.ScriptElement;

public class JavascriptInjector {
	
	private static HeadElement head;
	
	/**
	 * Injects JavaScript text(in the "script" tag) in the head element.
	 * 
	 * @param js
	 *            JavaScript text to be injected.
	 */
	public static void inject(String js) {
		HeadElement head = getHead();
		ScriptElement element = createScriptElement();
		element.setText(js);
		
		head.appendChild(element);
	}
	
	/**
	 * Creates the Script element.
	 * 
	 * @return returns a ScriptElement.
	 */
	private static ScriptElement createScriptElement() {
		ScriptElement script = Document.get().createScriptElement();
		script.setAttribute("language", "javascript");
		
		return script;
	}
	
	/**
	 * Creates a Head element.
	 * 
	 * @return returns a HeadElement.
	 */
	private static HeadElement getHead() {
		if (head == null) {
			Element element = Document.get().getElementsByTagName("head").getItem(0);
			assert element != null : "HTML Head element required";
			
			HeadElement head = HeadElement.as(element);
			JavascriptInjector.head = head;
		}
		
		return JavascriptInjector.head;
	}
}