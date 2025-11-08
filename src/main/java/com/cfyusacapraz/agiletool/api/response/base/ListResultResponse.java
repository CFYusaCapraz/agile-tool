package com.cfyusacapraz.agiletool.api.response.base;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListResultResponse<D extends List<?>> extends BaseApiResponse implements Serializable {

    private D resultList;
}
