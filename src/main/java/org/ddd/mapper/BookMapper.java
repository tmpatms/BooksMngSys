package org.ddd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.ddd.entity.Book;
import org.ddd.entity.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author dudaidong
 * @date 2023/06/23
 */
@Repository
public interface BookMapper extends BaseMapper<Book> {
}
