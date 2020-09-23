package com.lotus.uccp.customer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.lotus.uccp.book.vo.Tag;

@Entity
@Table(name = "email")
public class Email {
	
	
	@Id
	@Column(name = "email_id", nullable = false)
	private String emailId;
	
	@Column(name="title", nullable=false)
	private String title;
	@Column(name="conent", nullable=false)
	private String content;
	
	@ElementCollection
	@JoinTable(name="tag", joinColumns=@JoinColumn(name="email_id"))
	@GenericGenerator(name="increment", strategy="increment")
	@CollectionId(columns={@Column(name="tag_id")}, generator="increment", type=@Type(type="long"))
	private Collection<Tag> tags = new ArrayList<Tag>();
	
	
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Collection<Tag> getTags() {
		return tags;
	}
	public void setTags(Collection<Tag> tags) {
		this.tags = tags;
	}
	
	
	

}
