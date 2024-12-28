package com.anw.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PagedRequest {
    private int page;
    private int size;
    private String query;
}
