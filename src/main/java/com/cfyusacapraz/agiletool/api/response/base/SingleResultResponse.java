package com.cfyusacapraz.agiletool.api.response.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class SingleResultResponse<D> extends BaseApiResponse implements Serializable {

    private D result;
}
