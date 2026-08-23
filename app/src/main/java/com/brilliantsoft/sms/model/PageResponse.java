package com.brilliantsoft.sms.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> {
    @SerializedName("content")
    private List<T> content;
    @SerializedName("totalElements")
    private long totalElements;
    @SerializedName("totalPages")
    private int totalPages;
    @SerializedName("number")
    private int number;
    @SerializedName("size")
    private int size;
    @SerializedName("first")
    private boolean first;
    @SerializedName("last")
    private boolean last;
    @SerializedName("empty")
    private boolean empty;
}
