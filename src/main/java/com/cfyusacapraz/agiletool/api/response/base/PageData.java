package com.cfyusacapraz.agiletool.api.response.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageData implements Serializable {

    private int pageNumber;

    private long totalElements;

    private int totalPages;
}
