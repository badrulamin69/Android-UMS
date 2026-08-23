package com.brilliantsoft.sms.network;

import com.brilliantsoft.sms.model.*;
import java.util.List;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.*;

public interface ApiService {

    @POST("api/auth/login")
    Call<LoginResponse> login(@Body LoginRequest request);

    @POST("api/auth/refresh")
    Call<LoginResponse> refresh(@Body RefreshTokenRequest request);

    @POST("api/auth/change-password")
    Call<Map<String, Object>> changePassword(@Body ChangePasswordRequest request);

    @GET("api/students/me")
    Call<StudentResponse> getMe();

    @GET("api/students/profile/me")
    Call<StudentResponse> getProfileMe();

    @GET("api/students/{id}")
    Call<StudentResponse> getStudentById(@Path("id") Long id);

    @GET("api/students")
    Call<PageResponse<StudentResponse>> getStudents(@Query("page") int page, @Query("size") int size, @Query("sortBy") String sortBy, @Query("sortDir") String sortDir, @Query("search") String search);

    @GET("api/student-profiles/me")
    Call<StudentProfileResponse> getMyProfile();

    @GET("api/student-profiles")
    Call<PageResponse<StudentProfileResponse>> getStudentProfiles(@Query("page") int page, @Query("size") int size);

    @GET("api/dashboards/student")
    Call<Map<String, Object>> getStudentDashboard();

    @GET("api/student-portal/my-enrollment")
    Call<Map<String, Object>> getMyEnrollment();

    @GET("api/courses")
    Call<PageResponse<CourseResponse>> getCourses(@Query("page") int page, @Query("size") int size, @Query("search") String search);

    @GET("api/courses/{id}")
    Call<CourseResponse> getCourseById(@Path("id") Long id);

    @GET("api/course-modules")
    Call<PageResponse<CourseModuleResponse>> getCourseModules(@Query("page") int page, @Query("size") int size);

    @GET("api/examinations")
    Call<PageResponse<ExaminationResponse>> getExaminations(@Query("page") int page, @Query("size") int size, @Query("search") String search);

    @GET("api/examinations/{id}")
    Call<ExaminationResponse> getExaminationById(@Path("id") Long id);

    @GET("api/examination-results")
    Call<List<ExaminationResultResponse>> getExaminationResults();

    @GET("api/result-sheets/me")
    Call<ResultSheetResponse> getMyResultSheet(@Query("semester") String semester);

    @GET("api/result-sheets/me/download")
    Call<okhttp3.ResponseBody> downloadMyResultSheet(@Query("semester") String semester);

    @GET("api/invoices/student/{studentId}")
    Call<PageResponse<Map<String, Object>>> getInvoicesByStudent(@Path("studentId") Long studentId, @Query("page") int page, @Query("size") int size);

    @GET("api/invoices")
    Call<PageResponse<Map<String, Object>>> getInvoices(@Query("page") int page, @Query("size") int size, @Query("search") String search);

    @GET("api/student-fees")
    Call<PageResponse<StudentFeeResponse>> getStudentFees(@Query("page") int page, @Query("size") int size);

    @GET("api/payments/student/{studentId}")
    Call<PageResponse<Map<String, Object>>> getPaymentsByStudent(@Path("studentId") Long studentId, @Query("page") int page, @Query("size") int size);

    @POST("api/payments/initiate")
    Call<Map<String, Object>> initiatePayment(@Body Map<String, Object> body);

    @GET("api/student-attendance")
    Call<PageResponse<StudentAttendanceResponse>> getStudentAttendance(@Query("page") int page, @Query("size") int size, @Query("search") String search);

    @GET("api/books")
    Call<PageResponse<BookResponse>> getBooks(@Query("page") int page, @Query("size") int size, @Query("search") String search);

    @GET("api/book-issues")
    Call<PageResponse<BookIssueResponse>> getBookIssues(@Query("page") int page, @Query("size") int size);

    @GET("api/notices")
    Call<PageResponse<Map<String, Object>>> getNotices(@Query("page") int page, @Query("size") int size, @Query("search") String search);

    @GET("api/notices/{id}")
    Call<Map<String, Object>> getNoticeById(@Path("id") Long id);

    @GET("api/academic-calendars")
    Call<PageResponse<Map<String, Object>>> getAcademicCalendars(@Query("page") int page, @Query("size") int size, @Query("search") String search);

    @GET("api/academic-calendars/upcoming")
    Call<List<Map<String, Object>>> getUpcomingCalendars();

    @GET("api/class-routines")
    Call<List<Map<String, Object>>> getClassRoutines(@Query("semesterId") Long semesterId, @Query("sectionId") Long sectionId, @Query("dayOfWeek") String day);

    @GET("api/semester-routines")
    Call<PageResponse<Map<String, Object>>> getSemesterRoutines(@Query("page") int page, @Query("size") int size);

    @GET("api/events")
    Call<PageResponse<Map<String, Object>>> getEvents(@Query("page") int page, @Query("size") int size);

    @GET("api/event-registrations")
    Call<PageResponse<Map<String, Object>>> getEventRegistrations(@Query("page") int page, @Query("size") int size);

    @POST("api/event-registrations")
    Call<Map<String, Object>> createEventRegistration(@Body Map<String, Object> body);

    @GET("api/semesters")
    Call<PageResponse<Map<String, Object>>> getSemesters(@Query("page") int page, @Query("size") int size, @Query("search") String search);

    @GET("api/academic-sessions")
    Call<PageResponse<AcademicSessionResponse>> getAcademicSessions(@Query("page") int page, @Query("size") int size);

    @GET("api/transcripts")
    Call<PageResponse<Map<String, Object>>> getTranscripts(@Query("page") int page, @Query("size") int size);

    @GET("api/certificates")
    Call<PageResponse<Map<String, Object>>> getCertificates(@Query("page") int page, @Query("size") int size);

    @GET("api/hostel-allocations")
    Call<PageResponse<Map<String, Object>>> getHostelAllocations(@Query("page") int page, @Query("size") int size);

    @GET("api/transport-allocations")
    Call<PageResponse<Map<String, Object>>> getTransportAllocations(@Query("page") int page, @Query("size") int size);

    @GET("api/enrollments")
    Call<PageResponse<EnrollmentResponse>> getEnrollments(@Query("page") int page, @Query("size") int size);

    @GET("api/course-registrations")
    Call<PageResponse<Map<String, Object>>> getCourseRegistrations(@Query("page") int page, @Query("size") int size);

    @GET("api/assignments")
    Call<PageResponse<Map<String, Object>>> getAssignments(@Query("page") int page, @Query("size") int size);

    @GET("api/assignment-submissions")
    Call<PageResponse<Map<String, Object>>> getAssignmentSubmissions(@Query("page") int page, @Query("size") int size);

    @POST("api/assignment-submissions")
    Call<Map<String, Object>> submitAssignment(@Body Map<String, Object> body);

    @GET("api/notifications")
    Call<PageResponse<Map<String, Object>>> getNotifications(@Query("page") int page, @Query("size") int size);

    @GET("api/messages")
    Call<PageResponse<Map<String, Object>>> getMessages(@Query("page") int page, @Query("size") int size);
}
