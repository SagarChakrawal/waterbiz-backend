package com.kashiquench.waterbiz_backend.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import com.kashiquench.waterbiz_backend.entity.FeedbackRequest;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200", "https://kashiquench.netlify.app"})
public class FeedbackController {

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/feedback")
    public Map<String, String> handleFeedback(@RequestBody FeedbackRequest request) {
        Map<String, String> response = new HashMap<>();

        try {
            // Prepare email
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("physicswithsagar21@gmail.com");  // fixed sender
            message.setTo("sagarchakrawal30@gmail.com");      // fixed receiver
            message.setSubject("Feedback from user");
            message.setText(request.getFeedback());

            // Send email
            mailSender.send(message);

            response.put("status", "success");
            response.put("message", "Feedback sent successfully!");
        } catch (Exception e) {
            // Log full error to Railway logs
            e.printStackTrace();

            response.put("status", "error");
            response.put("message", "Failed to send feedback: " + e.getMessage());
        }

        return response;
    }
}
