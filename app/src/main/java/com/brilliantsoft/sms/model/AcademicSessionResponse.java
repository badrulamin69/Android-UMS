package com.brilliantsoft.sms.model;

import java.time.LocalDate;

public class AcademicSessionResponse {
    private Long id;
    private String sessionName;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean active;

    public AcademicSessionResponse() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getSessionName() { return sessionName; }
    public void setSessionName(String sessionName) { this.sessionName = sessionName; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
