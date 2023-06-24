package org.ddd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.ddd.entity.Book;
import org.ddd.entity.User;
import org.mapstruct.Mapper;

/**
 * @author dudaidong
 * @date 2023/06/23
 */
@Mapper
public interface BookMapper extends BaseMapper<Book> {
}
