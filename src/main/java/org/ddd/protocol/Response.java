package org.ddd.protocol;

import lombok.Data;

/**
 * @author dudaidong
 * @date 2023/06/23
 */
@Data
public class Response<T> {

    private Integer status;

    private String msg;

}
