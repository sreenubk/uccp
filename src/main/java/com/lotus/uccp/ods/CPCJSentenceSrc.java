package com.lotus.uccp.ods;
import com.lotus.uccp.util.Constant; 

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(schema = Constant.SCHEMANAME_ODS , name = "CP_CJ_SENTENCE_SRC")
public class CPCJSentenceSrc implements java.io.Serializable  {

	BigDecimal CP_CJ_CJSentence_SEQ_NUM ;
	String caseNum ;
	String offense ;
	String disposition ;
	String address ;
	Date sentenceDate ;
	String term ;	
	BigDecimal CLIENT_PROFILE_CJ_SEQ_NUM;
	
	@Id
	@Column(name = "CP_CJ_SENTENCE_SEQ_NUM", unique = true, nullable = false, precision = 31, scale = 0)
	public BigDecimal getCP_CJ_CJSentence_SEQ_NUM() {
		return CP_CJ_CJSentence_SEQ_NUM;
	}
	public void setCP_CJ_CJSentence_SEQ_NUM(BigDecimal cP_CJ_CJSentence_SEQ_NUM) {
		CP_CJ_CJSentence_SEQ_NUM = cP_CJ_CJSentence_SEQ_NUM;
	}
	
	@Column(name = "caseNum", length = 100)
	public String getCaseNum() {
		return caseNum;
	}
	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}
	
	@Column(name = "offense", length = 100)
	public String getOffense() {
		return offense;
	}
	public void setOffense(String offense) {
		this.offense = offense;
	}
	
	@Column(name = "disposition", length = 100)
	public String getDisposition() {
		return disposition;
	}
	public void setDisposition(String disposition) {
		this.disposition = disposition;
	}
	
	@Column(name = "SENTENCEADDRESS", length = 150)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "sentenceDate", length = 10)
	public Date getSentenceDate() {
		return sentenceDate;
	}
	public void setSentenceDate(Date sentenceDate) {
		this.sentenceDate = sentenceDate;
	}
	
	@Column(name = "term", length = 100)
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	
	
	@Column(name = "CLIENT_PROFILE_CJ_SEQ_NUM", length = 30)
	public BigDecimal getCLIENT_PROFILE_CJ_SEQ_NUM() {
		return CLIENT_PROFILE_CJ_SEQ_NUM;
	}
	public void setCLIENT_PROFILE_CJ_SEQ_NUM(BigDecimal cLIENT_PROFILE_CJ_SEQ_NUM) {
		CLIENT_PROFILE_CJ_SEQ_NUM = cLIENT_PROFILE_CJ_SEQ_NUM;
	}
}
/*
CREATE TABLE CP_CJ_SENTENCE_SRC 
(CP_CJ_SENTENCE_SEQ_NUM NUMBER(31) NOT NULL, 
  CASENUM VARCHAR2(100 CHAR), 
  OFFENSE VARCHAR2(100 CHAR), 
  DISPOSITION VARCHAR2(100 CHAR), 
  SENTENCEADDRESS VARCHAR2(150 CHAR), 
  SENTENCEDATE DATE, 
  TERM VARCHAR2(100 CHAR), 
  CLIENT_PROFILE_CJ_SEQ_NUM NUMBER(31) NOT NULL, 
  PRIMARY KEY (CP_CJ_SENTENCE_SEQ_NUM));
*/