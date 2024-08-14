package com.example.notificationsemails.dtos.notifications;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.util.Date;

public class sendNotificationDto {
    @Past(message = "The date must be in the past")
    @NotNull(message = "Date cannot be null")
    private Date visitDate;

    private String location = null;

    @NotNull(message = "Time cannot be null")
    private String hour;

    //Setters and Getters here...
    public Date getVisitDate() {
        return this.visitDate;
    }
    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public String getLocation() {
        return this.location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public String getHour() {
        return this.hour;
    }
    public void setHour(String hour) {
        this.hour = hour;
    }
}
