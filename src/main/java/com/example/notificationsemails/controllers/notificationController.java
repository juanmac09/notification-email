package com.example.notificationsemails.controllers;

import com.example.notificationsemails.servicesInterfaces.notifications.INotificationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.notificationsemails.dtos.notifications.sendNotificationDto;

@RestController
@RequestMapping("/notification")
public class notificationController {
    private final INotificationService notificationService;

    public notificationController(INotificationService notificationService) {
        this.notificationService = notificationService;
    }

    /**
     * Handles the HTTP POST request to send a notification via email.
     *
     * This method is mapped to the `/send-notification` endpoint and expects a
     * JSON payload containing the notification details. The request body is
     * validated using the `@Valid` annotation to ensure that the required
     * fields are present and meet the validation criteria defined in the
     * `sendNotificationDto` class.
     *
     * Upon receiving a valid request, the method invokes the `sendEmail`
     * method of the `notificationService` to send an email containing the
     * visit date, location, and hour as provided in the `notificationDto`.
     * If the email is sent successfully, the method returns a response
     * indicating that the email is being sent.
     *
     * @param notificationDto the data transfer object containing the details
     *                        of the notification to be sent.
     * @return a `ResponseEntity` containing a success message and an HTTP
     *         status code of 200 (OK).
     */
    @PostMapping("/send-notification")
    public ResponseEntity<String> sendNotification(@Valid @RequestBody sendNotificationDto notificationDto) {
        this.notificationService.sendEmail(notificationDto.getVisitDate(),notificationDto.getLocation(),notificationDto.getHour());
        return new ResponseEntity<>("sending email...", HttpStatus.OK);
    }
}
