package com.brilliantsoft.sms.model;

public class StudentDocumentRequest {
    private Long studentId;
    private String documentType;
    private String documentName;
    private String fileUrl;
    private Long fileSize;
    private String status;
    private Long verifiedById;
    private String remarks;

    public StudentDocumentRequest() {}

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public String getDocumentType() { return documentType; }
    public void setDocumentType(String documentType) { this.documentType = documentType; }

    public String getDocumentName() { return documentName; }
    public void setDocumentName(String documentName) { this.documentName = documentName; }

    public String getFileUrl() { return fileUrl; }
    public void setFileUrl(String fileUrl) { this.fileUrl = fileUrl; }

    public Long getFileSize() { return fileSize; }
    public void setFileSize(Long fileSize) { this.fileSize = fileSize; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Long getVerifiedById() { return verifiedById; }
    public void setVerifiedById(Long verifiedById) { this.verifiedById = verifiedById; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
}
