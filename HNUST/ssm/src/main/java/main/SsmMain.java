package main;

import com.elin4it.ssm.pojo.User;
import com.elin4it.ssm.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.List;

/**
 * Created by songll on 2017/6/29.
 */
public class SsmMain {
    private static ClassPathXmlApplicationContext context;
    public static void main(String[] args) throws IOException {
        context = new ClassPathXmlApplicationContext(
                new String[] {
                        "config/spring/applicationContext-dao.xml",
                        "config/spring/applicationContext-transaction.xml",
                        "config/spring/applicationContext-service.xml"
                });
        context.start();
        try {
            UserService service = (UserService)context.getBean("userService");
            List<User> list = service.findUser();
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("-----------------------spring初始化完成----------------------------");

        //System.in.read();
    }
}
