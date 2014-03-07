package com.temis.shared;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

public interface Resources extends ClientBundle {
	public final static Resources INSTANCE = GWT.create(Resources.class);
	
	@Source("vivagraph.js")
	TextResource jquery();
}
