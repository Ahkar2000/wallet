package com.ahkar.projection;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface BookingProjection {
    String getUserName();
    String getEmail();
    String getCategoryName();
    String getPackageName();
    Integer getGroupSize();
    Double getTotalPrice();
    LocalDate getSchedule();
    LocalDateTime getCreatedAt();
}
