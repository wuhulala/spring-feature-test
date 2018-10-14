import com.wuhulala.spring.Application;
import com.wuhulala.spring.aop.AOPService;
import com.wuhulala.spring.aop.AService;
import com.wuhulala.spring.properties.Properties;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Wuhulala
 * @version 1.0
 * @updateTime 2016/10/28
 */
//@RunWith(value = SpringJUnit4ClassRunner.class)
//@ContextConfiguration(value = {"file:src/main/resources/spring.xml"})
@ContextConfiguration(classes = {Application.class})
public class Client {
    @Autowired
    private AService aService;
    @Autowired
    private AOPService aopService;

    @Autowired
    Properties p;

/*

    public static void main(String[] args) {
        int i = 10 ;
        int j = 1;
        do{
            if(i-- > ++j) {
                System.out.println("continue");
                continue;
            }
        }while(i>5);


        System.out.println(i+"----"+j);
    }
*/

    @Test
    public void test3(){
         //p.print();
    }

    @Test
    public void test4(){
        aopService.a("asd");
    }

    public static void main(String[] args) {
        try {
            System.out.println("00000");
        }catch (Exception e){

        }finally {
            try {
                System.out.println("1111");
                throw new RuntimeException();
            }catch (Exception e){
                System.out.println("error111");
                e.printStackTrace();
            }
            try {
                System.out.println("22222");
            }catch (Exception e){
                System.out.println("error2222");
            }
        }
    }
}
