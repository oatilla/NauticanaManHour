package com.nauticana.nams.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Domain {
	private String id;
	private String caption;
	private int size;
	Map<String, String> options = new HashMap<String, String>();
	Map<String, Map<String, String>> langOptions = new HashMap<String, Map<String, String>>();
	
	
	public Domain(String id, String caption, int size) {
		super();
		this.id = id;
		this.caption = caption;
		this.size = size;
		options.put("", " - ");
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

	public Map<String, String> getOptions(PortalLanguage l) {
		Map<String, String> o = langOptions.get(l.code);
		if (o != null) return o;
		o = new HashMap<String, String>();
		for (String key : options.keySet()) {
			o.put(key, l.getText(options.get(key)));
		}
		List<Entry<String, String>> ol = new ArrayList<Entry<String, String>>(o.entrySet());
		Collections.sort(ol, new MapComparator());
		o.clear();
		for (Entry<String, String> entry : ol) {
			o.put(entry.getKey(), entry.getValue());
		}
		langOptions.put(l.code, o);
		return o;
	}

	public void println() {
		System.out.println("DOMAIN: " + id + " KEY SIZE: " + size);
		for (String s:options.keySet()) {
			System.out.println("  " + s + " -> " + options.get(s));
		}
	}

	public void println(PortalLanguage l) {
		System.out.println("DOMAIN: " + id + " KEY SIZE: " + size + " LANGUAGE: " + l.caption);
		Map<String, String> o = langOptions.get(l.code);
		for (String s:o.keySet()) {
			System.out.println("  " + s + " -> " + o.get(s));
		}
	}
	
	public void clear() {
		options.clear();
		langOptions.clear();
	}
}
