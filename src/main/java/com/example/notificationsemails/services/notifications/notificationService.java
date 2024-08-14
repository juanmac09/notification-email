package com.example.notificationsemails.services.notifications;

import com.example.notificationsemails.servicesInterfaces.notifications.INotificationService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.context.Context;

import java.util.*;

@Service
public class notificationService implements INotificationService {

    private final JavaMailSender sender;
    private final SpringTemplateEngine templateEngine;

    @Value("${email.to.notification}")
    private String email;


    public notificationService(JavaMailSender sender, SpringTemplateEngine templateEngine) {
        this.sender = sender;
        this.templateEngine = templateEngine;
    }

    /**
     * Asynchronously sends an email notification with details about an upcoming visit.
     *
     * This method is annotated with `@Async`, meaning it will be executed asynchronously,
     * allowing the caller to proceed without waiting for the email to be sent.
     * The method uses a `MimeMessage` to construct an email, which includes a template-based
     * message with information about the visit date, location, and time.
     *
     * The email content is generated using a Thymeleaf template named `activityNotification`,
     * where placeholders are replaced with actual data such as the visit date, time, and location.
     * The email is then sent using the configured mail sender.
     *
     * @param visitDate the date of the scheduled visit.
     * @param location  the location of the visit.
     * @param hour      the time of the visit.
     */
    @Async
    @Override
    public void sendEmail(Date visitDate,String location, String  hour) {
        MimeMessage message = this.sender.createMimeMessage();
        Context context = new Context();
        Map<String, Object> model = new HashMap<>();
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(visitDate);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        model.put("date", day + " / " + month + " / " + year);
        model.put("hour", hour);
        model.put("location", "Bogota");
        context.setVariables(model);

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(this.email);
            helper.setSubject("Actividad en Portafolio");
            helper.setText(this.templateEngine.process("activityNotification",context),true);
            helper.setFrom(this.email);

            this.sender.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
