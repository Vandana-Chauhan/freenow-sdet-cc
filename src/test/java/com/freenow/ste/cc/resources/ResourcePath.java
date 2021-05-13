package com.freenow.ste.cc.resources;

public enum ResourcePath {
	
	getuserAPI("/users"),
	getPostsApI("/posts"),
	getCommentsAPT("/comments");

	
	private String resource;
	
	ResourcePath(String resource) {
		this.resource=resource;
	}

	
	public String getResource() {
		return resource;
		
	}
}
