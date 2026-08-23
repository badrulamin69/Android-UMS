package com.brilliantsoft.sms.model;

import java.time.LocalDate;

public class BookIssueResponse {
    private Long id;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private Double fine;
    private BookIssueStatus status;
    private Long bookId;
    private String bookTitle;
    private Long studentId;
    private String studentName;

    public BookIssueResponse() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getIssueDate() { return issueDate; }
    public void setIssueDate(LocalDate issueDate) { this.issueDate = issueDate; }
    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }
    public Double getFine() { return fine; }
    public void setFine(Double fine) { this.fine = fine; }
    public BookIssueStatus getStatus() { return status; }
    public void setStatus(BookIssueStatus status) { this.status = status; }
    public Long getBookId() { return bookId; }
    public void setBookId(Long bookId) { this.bookId = bookId; }
    public String getBookTitle() { return bookTitle; }
    public void setBookTitle(String bookTitle) { this.bookTitle = bookTitle; }
    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
}
