package com.gama.manhour.utils;

import java.util.HashMap;
import java.util.Map;

public class Domain {
	private String id;
	private String caption;
	private int size;
	Map<String, String> options = new HashMap<String, String>();
	
	public Domain(String id, String caption, int size) {
		super();
		this.id = id;
		this.caption = caption;
		this.size = size;
	}

	public String getId() {
		return id;
	}

	public String getCaption() {
		return caption;
	}

	public int size() {
		return size;
	}

	public void addOption(String value,String caption) {
		options.put(value, caption);
	}
	
	public Map<String, String> getOptions() {
		return options;
	}

	public void println() {
		System.out.println("DOMAIN: " + id + " KEY SIZE: " + size);
		for (String s:options.keySet()) {
			System.out.println("  " + s + " -> " + options.get(s));
		}
		
	}
}
