package com.cfyusacapraz.agiletool.api.request;

import com.cfyusacapraz.agiletool.api.request.base.BasePagedApiRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserFilterRequest extends BasePagedApiRequest implements Serializable {

    private String email;

    private String name;
}
