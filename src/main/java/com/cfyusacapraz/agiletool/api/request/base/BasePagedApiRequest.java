package com.cfyusacapraz.agiletool.api.request.base;

import com.cfyusacapraz.agiletool.api.constants.ParamConstants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasePagedApiRequest implements Serializable {

    @PositiveOrZero
    private int pageNumber = ParamConstants.DEFAULT_PAGE_NUMBER;

    @Positive
    private int pageSize = ParamConstants.DEFAULT_PAGE_SIZE;

    @NotNull
    @Size(min = 1)
    private List<String> sortRules = ParamConstants.DEFAULT_SORT_RULES;

    public PaginationData toPaginationData() {
        return new PaginationData(pageNumber, pageSize, sortRules);
    }
}
