package com.lotus.uccp.product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.lotus.uccp.customer.Subscriber;

@Entity
@Table(name = "purchase")
public class Purchase {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="subscriber_id")
	private long id;
	
	@Column(name="price", nullable=false)
	private String price;
	
	@Column(name="date")
	private Date date;
	
	@OneToMany(mappedBy="purchase")
	private Collection<Product>  product = new ArrayList<Product>();
	
	@OneToMany
	private Collection<Subscriber> subscriber = new ArrayList<Subscriber>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	

	public Collection<Product> getProduct() {
		return product;
	}

	public void setProduct(Collection<Product> product) {
		this.product = product;
	}

	public Collection<Subscriber> getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(Collection<Subscriber> subscriber) {
		this.subscriber = subscriber;
	}

	
		
	
	

}
