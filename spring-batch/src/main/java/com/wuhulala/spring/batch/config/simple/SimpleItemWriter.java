package com.wuhulala.spring.batch.config.simple;

import com.wuhulala.spring.batch.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * 功能
 *
 * @author xueah20964 2018/10/14 Create 1.0  <br>
 * @version 1.0
 */
@Slf4j
public class SimpleItemWriter implements ItemWriter<Person> {

    @Override
    public void write(List<? extends Person> list) throws Exception {
        for (Person each: list) {
            log.info("处理后结果: {}", each);
        }
    }
}
