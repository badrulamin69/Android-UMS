package com.brilliantsoft.sms.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class StudentFeeResponse {
    private Long id;
    private String uniqueCode;
    private Long studentId;
    private Long feeTypeId;
    private Double amount;
    private LocalDate dueDate;
    private Double paidAmount;
    private String status;
    private String academicYear;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public StudentFeeResponse() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUniqueCode() { return uniqueCode; }
    public void setUniqueCode(String uniqueCode) { this.uniqueCode = uniqueCode; }

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public Long getFeeTypeId() { return feeTypeId; }
    public void setFeeTypeId(Long feeTypeId) { this.feeTypeId = feeTypeId; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public Double getPaidAmount() { return paidAmount; }
    public void setPaidAmount(Double paidAmount) { this.paidAmount = paidAmount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getAcademicYear() { return academicYear; }
    public void setAcademicYear(String academicYear) { this.academicYear = academicYear; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
