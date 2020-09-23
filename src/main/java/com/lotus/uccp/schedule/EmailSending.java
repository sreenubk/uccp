package com.lotus.uccp.schedule;

import java.util.Date;

public class EmailSending {
	
	private Date date;
	private EmailsScheduling emailsScheduling;
	

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public EmailsScheduling getEmailsScheduling() {
		return emailsScheduling;
	}

	public void setEmailsSending(EmailsScheduling emailsScheduling) {
		this.emailsScheduling = emailsScheduling;
	}

}
