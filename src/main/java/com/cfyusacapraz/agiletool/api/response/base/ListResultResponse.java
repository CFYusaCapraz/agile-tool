package com.cfyusacapraz.agiletool.api.response.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ListResultResponse<D extends List<?>> extends BaseApiResponse implements Serializable {

    private D resultList;
}
