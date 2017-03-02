package com.masteklabs.sparkreporting.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "valueHolder")
public class ValueHolder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ValueHolder() {

	}

	public ValueHolder(String query) {
		this.query = query;
	}

	@XmlElement
	private String query;

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

}