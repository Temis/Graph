package com.temis.shared;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.CssResource.NotStrict;
import com.google.gwt.resources.client.TextResource;

public interface Resources extends ClientBundle {
	public final static Resources INSTANCE = GWT.create(Resources.class);
	
	@Source("jquery-1.6.1.min.js")
	TextResource jquery();
	
	@Source("jquery.address-1.5.min.js")
	TextResource jaddress();
	
	@Source("arbor-tween.js")
	TextResource arborTween();
	
	@Source("graphics.js")
	TextResource graphics();
	
	@Source("edit.js")
	TextResource edit();
	
	@Source("mainarbor.js")
	TextResource mainarbor();
	
	@Source("vivagraph.js")
	TextResource vivagraph();
	
	@Source("mainvivagraph.js")
	TextResource mainvivagraph();
	
	@NotStrict
	@Source("vivagraph.css")
	CssResource vivagraphCss();
	
}
