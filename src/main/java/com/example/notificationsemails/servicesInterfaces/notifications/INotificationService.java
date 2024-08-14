package com.example.notificationsemails.servicesInterfaces.notifications;

import java.util.Date;

public interface INotificationService {

    void sendEmail(Date visitDate, String location, String  hour);
}
