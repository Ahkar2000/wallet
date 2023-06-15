package com.ahkar.service.imp;

import com.ahkar.projection.BookingProjection;
import com.ahkar.repository.BookingRepository;
import com.ahkar.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailServiceImp implements EmailService {

    @Autowired
    private BookingRepository bookingRepository;
    private final JavaMailSender mailSender;
    public EmailServiceImp(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(Long id) {
        try{
            BookingProjection bookingProjection = bookingRepository.bookingForEmail(id);
            log.info("email : "+bookingProjection.getEmail());
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(bookingProjection.getEmail());
            message.setSubject("Booking Confirmation");
            message.setText(emailFormat(bookingProjection));
            mailSender.send(message);
        }catch (Exception e){
            log.info("Email failed to send."+e);
        }
    }
    private String emailFormat(BookingProjection bookingProjection) {
        String body = "<html>"
                + "<body>"
                + "<h2>Dear " + bookingProjection.getUserName() + ",</h2>"
                + "<p>Thank you for your recent purchase. We are excited to have you as part of our community.</p>"
                + "<h3>Package Details:</h3>"
                + "<p><strong>Package Name:</strong> " + bookingProjection.getPackageName() + "</p>"
                + "<p><strong>Category:</strong> " + bookingProjection.getCategoryName() + "</p>"
                + "<h3>Booking Information:</h3>"
                + "<p><strong>Group Size:</strong> " + bookingProjection.getGroupSize() + "</p>"
                + "<p><strong>Amount:</strong> " + bookingProjection.getTotalPrice() + "</p>"
                + "<p><strong>Schedule:</strong> " + bookingProjection.getSchedule() + "</p>"
                + "<p>Your order was created on " + bookingProjection.getCreatedAt() + ".</p>"
                + "<p>If you have any questions or need further assistance, please feel free to contact us.</p>"
                + "<br>"
                + "<p>Thank you,</p>"
                + "</body>"
                + "</html>";
        return body;
    }

    @KafkaListener(topics = "tourism-topic", groupId = "email-group")
    public void listen(ConsumerRecord<String, String> record) {
        Long value = Long.valueOf(record.value());
        sendEmail(value);
    }
}
