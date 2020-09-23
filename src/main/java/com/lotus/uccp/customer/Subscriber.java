package com.lotus.uccp.customer;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.lotus.uccp.book.vo.Tag;


@Entity
@Table(name = "Subscriber")
public class Subscriber {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private Date subscriptionDate;
	private String country;
	
	@ElementCollection
	@JoinTable(name="tag", joinColumns=@JoinColumn(name="subscriber_id"))
	@GenericGenerator(name="increment", strategy="increment")
	@CollectionId(columns={@Column(name="tag_id")}, generator="increment", type=@Type(type="long"))
	private Collection<Tag> tags = new ArrayList<Tag>();
	
	@OneToMany
	@JoinColumn(name="email_id")
	private Collection<Email> email= new ArrayList<Email>();
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "subscription_date", nullable = false)
	public Date getSubscriptionDate() {
		return subscriptionDate;
	}
	public void setSubscriptionDate(Date subscriptionDate) {
		this.subscriptionDate = subscriptionDate;
	}
	@Column(name = "Country", nullable = false)
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	public Collection<Tag> getTags() {
		return tags;
	}
	public void setTags(Collection<Tag> tags) {
		this.tags = tags;
	}
	
	public Collection<Email> getEmail() {
		return email;
	}
	public void setEmail(Collection<Email> email) {
		this.email = email;
	}
	
	
	
}
