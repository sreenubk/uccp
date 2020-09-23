package com.lotus.uccp.authentication.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.lotus.uccp.util.Constant;

@Entity
@Table(name = "authorizationpolicy", schema = Constant.SCHEMA )
public class AuthorizationPolicy {
	
	private BigDecimal authorizationpolicyid;
	private AuthorizationPolicySet authorizationPolicySet;
	private AuthorizationGroup authorizationGroup;
	private String policyComments ;
	
	// authorizationpolicyid , authorizationgroupid , 
	// policysetid ,policyComments
	
	public AuthorizationPolicy() {
	}

	public AuthorizationPolicy(BigDecimal authorizationpolicyid) {
		this.authorizationpolicyid = authorizationpolicyid;
	}
	
	public AuthorizationPolicy(BigDecimal authorizationpolicyid ,AuthorizationPolicySet authorizationPolicySet , AuthorizationGroup authorizationGroup ,String policyComments ) {
		
		this.authorizationpolicyid = authorizationpolicyid ;
		this.authorizationPolicySet =authorizationPolicySet ;
		this.authorizationGroup = authorizationGroup ;
		this.policyComments = policyComments ;
	}
	
	@Id
	@SequenceGenerator(name = "authPolicySeq", sequenceName = "authPolicySeq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="authPolicySeq")
	@Column(name = "authorizationpolicyid", unique = true, nullable = false, scale = 0)	
	public BigDecimal getAuthorizationpolicyid() {
		return authorizationpolicyid;
	}
	public void setAuthorizationpolicyid(BigDecimal authorizationpolicyid) {
		this.authorizationpolicyid = authorizationpolicyid;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "policysetid")
	public AuthorizationPolicySet getAuthorizationPolicySet() {
		return authorizationPolicySet;
	}
	public void setAuthorizationPolicySet(AuthorizationPolicySet authorizationPolicySet) {
		this.authorizationPolicySet = authorizationPolicySet;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "authorizationgroupid")
	public AuthorizationGroup getAuthorizationGroup() {
		return authorizationGroup;
	}
	public void setAuthorizationGroup(AuthorizationGroup authorizationGroup) {
		this.authorizationGroup = authorizationGroup;
	}
	
	@Column(name = "policyComments", length = 64)
	public String getPolicyComments() {
		return policyComments;
	}
	public void setPolicyComments(String policyComments) {
		this.policyComments = policyComments;
	}
	
	
}