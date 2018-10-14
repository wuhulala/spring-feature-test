package com.wuhulala.spring.batch.config.simple;

import com.wuhulala.spring.batch.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

import java.util.Date;

/**
 * 功能
 *
 * @author xueah20964 2018/10/14 Create 1.0  <br>
 * @version 1.0
 */
@Slf4j
public class SimpleItemProcessor implements ItemProcessor<Person, Person> {

    @Override
    public Person process(Person person) throws Exception {
        person.setName(person.getName().toUpperCase());
        person.setUpdateTime(new Date());
        return person;
    }
}
