package com.wuhulala.spring.batch.config.partition;

import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;

import java.util.HashMap;
import java.util.Map;

/**
 * 0_0 o^o
 *
 * @author wuhulala<br>
 * @date 2018/10/15<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
public class FilePartitioner implements Partitioner {


    ///////////////////////////// 方法区 ////////////////////////////////////


    @Override
    public Map<String, ExecutionContext> partition(int gridSize) {
        Map<String, ExecutionContext> result = new HashMap<String, ExecutionContext>();

        for (int i = 1; i <= gridSize; i++) {
            ExecutionContext value = new ExecutionContext();
            String fileName = "person" + i + ".csv";
            System.out.println("Starting : Thread" + i);
            System.out.println("file : " + fileName);
            value.put("input.file.path", fileName);
            result.put("partition" + i, value);

        }

        return result;
    }
}
