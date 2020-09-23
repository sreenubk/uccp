package com.lotus.uccp.customer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.lotus.uccp.book.vo.Tag;

public class EmailSequence {
	
	private String title;
	
	@ElementCollection
	@JoinTable(name="tag", joinColumns=@JoinColumn(name="email_sequence_id"))
	@GenericGenerator(name="increment", strategy="increment")
	@CollectionId(columns={@Column(name="tag_id")}, generator="increment", type=@Type(type="long"))
	private Collection<Tag> tags = new ArrayList<Tag>();
	
	private List<Email> emails;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public Collection<Tag> getTags() {
		return tags;
	}

	public void setTags(Collection<Tag> tags) {
		this.tags = tags;
	}

	public List<Email> getEmails() {
		return emails;
	}

	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}
	
	
	

}
