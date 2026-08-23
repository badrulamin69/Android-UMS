package com.brilliantsoft.sms.model;

import java.time.LocalDate;

public class StudentPromotionResponse {
    private Long id;
    private String uniqueCode;
    private Long studentId;
    private Long fromSemesterId;
    private Long toSemesterId;
    private Long fromBatchId;
    private Long toBatchId;
    private LocalDate promotionDate;
    private String status;
    private String remarks;
    private Long approvedById;

    public StudentPromotionResponse() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUniqueCode() { return uniqueCode; }
    public void setUniqueCode(String uniqueCode) { this.uniqueCode = uniqueCode; }
    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
    public Long getFromSemesterId() { return fromSemesterId; }
    public void setFromSemesterId(Long fromSemesterId) { this.fromSemesterId = fromSemesterId; }
    public Long getToSemesterId() { return toSemesterId; }
    public void setToSemesterId(Long toSemesterId) { this.toSemesterId = toSemesterId; }
    public Long getFromBatchId() { return fromBatchId; }
    public void setFromBatchId(Long fromBatchId) { this.fromBatchId = fromBatchId; }
    public Long getToBatchId() { return toBatchId; }
    public void setToBatchId(Long toBatchId) { this.toBatchId = toBatchId; }
    public LocalDate getPromotionDate() { return promotionDate; }
    public void setPromotionDate(LocalDate promotionDate) { this.promotionDate = promotionDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
    public Long getApprovedById() { return approvedById; }
    public void setApprovedById(Long approvedById) { this.approvedById = approvedById; }
}
