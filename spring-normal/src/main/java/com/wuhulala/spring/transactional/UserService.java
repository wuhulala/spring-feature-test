package com.wuhulala.spring.transactional;

import com.wuhulala.api.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 功能说明:0o0<br>
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: xueah20964<br>
 * 开发时间: 2018/8/19<br>
 */
@Service
public class UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    ///////////////////////////// 方法区 ////////////////////////////////////

    //@Transactional
    public void createUser(User user) {


        jdbcTemplate.execute("insert into tb_user(name, age) values ('wuhulala', 21)");
        // dosave
        System.out.println("保存成功");
        throw new RuntimeException("我不想保存成功");
    }

    public void initMillionData() {
        for (int i = 0; i < 100; i++) {
            String[] sqls = new String[1000];
            for (int j = 0; j < 1000; j++) {
                String provinceCode = "12";
                String instCode = "I";
                String instName = "公司_"+i;
                sqls[i] = "insert into tb_risk_score values ('wuhulala', 21)";
            }
            //jdbcTemplate.batchUpdate();

        }

    }


}
