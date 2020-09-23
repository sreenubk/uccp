package com.lotus.uccp.book.vo;

import javax.persistence.Embeddable;

@Embeddable
public class Tag {
	
	private String tagValue;

	public String getTagValue() {
		return tagValue;
	}

	public void setTagValue(String tagValue) {
		this.tagValue = tagValue;
	}
	
	

}
