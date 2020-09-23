package com.lotus.uccp.schedule;

public class EmailsScheduling {
	
	private String importance;
	private String timeSensitive;
	private boolean blocked;
	
	
	
	public String getImportance() {
		return importance;
	}
	public void setImportance(String importance) {
		this.importance = importance;
	}
	public String getTimeSensitive() {
		return timeSensitive;
	}
	public void setTimeSensitive(String timeSensitive) {
		this.timeSensitive = timeSensitive;
	}
	public boolean isBlocked() {
		return blocked;
	}
	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

}
