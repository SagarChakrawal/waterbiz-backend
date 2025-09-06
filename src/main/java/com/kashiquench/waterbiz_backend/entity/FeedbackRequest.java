package com.kashiquench.waterbiz_backend.entity;

import lombok.Data;

@Data
public class FeedbackRequest {
    private String feedback; // The message typed by user

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
    
}
