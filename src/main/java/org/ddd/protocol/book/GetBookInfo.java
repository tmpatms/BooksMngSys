package org.ddd.protocol.book;

import lombok.Data;

/**
 * @author dudaidong
 * @date 2023/06/23
 */
@Data
public class GetBookInfo {

    private Long id;

    private String name;

    private String position;

}
