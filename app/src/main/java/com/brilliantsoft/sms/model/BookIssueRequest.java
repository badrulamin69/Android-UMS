package com.brilliantsoft.sms.model;

import java.time.LocalDate;

public class BookIssueRequest {
    private LocalDate issueDate;
    private LocalDate dueDate;
    private Long bookId;
    private Long studentId;

    public BookIssueRequest() {}

    public LocalDate getIssueDate() { return issueDate; }
    public void setIssueDate(LocalDate issueDate) { this.issueDate = issueDate; }
    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public Long getBookId() { return bookId; }
    public void setBookId(Long bookId) { this.bookId = bookId; }
    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
}
