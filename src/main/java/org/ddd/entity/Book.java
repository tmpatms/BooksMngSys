package org.ddd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author dudaidong
 * @date 2023/06/23
 */

@Component
@Data
public class Book {
    /**
     * primary key
     */
    @Autowired(required = false)
    private Long id;

    /**
     * username
     */
    @Autowired(required = false)
    private String name;

    /**
     * status
     * 0 stands for remains in libarary,
     * 1 stands for it has been borrowed.
     */
    @Autowired(required = false)
    private Integer status;

    /**
     * borrowCount the count that the book had been borrowed.
     */
    @Autowired(required = false)
    private Integer borrowedCount;

    /**
     * position the position of this book in the libarary.
     */
    @Autowired(required = false)
    private String position;
}
