import com.wuhulala.spring.batch.SpringBatchApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 0_0 o^o
 *
 * @author wuhulala<br>
 * @date 2018/10/30<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringBatchApplication.class)
public class KafkaConfigurationTest{

    @Autowired
    KafkaTemplate kafkaTemplate;

    @Test
    public void test() throws InterruptedException {
        kafkaTemplate.send("foo", "asdasd");
        Thread.sleep(10000);
    }


}