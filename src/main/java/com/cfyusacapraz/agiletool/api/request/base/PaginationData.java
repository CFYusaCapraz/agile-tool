package com.cfyusacapraz.agiletool.api.request.base;

import com.cfyusacapraz.agiletool.api.constants.ParamConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginationData implements Serializable {

    private int pageNumber = ParamConstants.DEFAULT_PAGE_NUMBER;

    private int pageSize = ParamConstants.DEFAULT_PAGE_SIZE;

    private List<String> sortRules = ParamConstants.DEFAULT_SORT_RULES;
}
