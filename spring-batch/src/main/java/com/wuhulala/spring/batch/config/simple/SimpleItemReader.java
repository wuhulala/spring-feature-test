package com.wuhulala.spring.batch.config.simple;

import com.wuhulala.spring.batch.model.Person;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.core.io.ClassPathResource;

/**
 * 功能
 *
 * @author xueah20964 2018/10/14 Create 1.0  <br>
 * @version 1.0
 */
public class SimpleItemReader implements ItemReader<Person> {

    @Override
    public Person read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
//        FlatFileItemReader<Person> reader = new FlatFileItemReader<>();
//        reader.setResource(new ClassPathResource("people.csv"));
//        return read()
        return null;
    }
}
