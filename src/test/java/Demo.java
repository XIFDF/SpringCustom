import com.xifdf.bean.User;
import com.xifdf.service.UserService;
import com.xifdf.spring.ClassPathXmlApplicationContext;
import org.junit.Test;

public class Demo {

    @Test
    public void dologin(){
        //创建一个模拟要登录的用户
        User user = new User(1, "admin", "testpassword");
        //通过我们实现的模拟spring IOC的功能模块来获取service实例，以此模拟登陆操作
        ClassPathXmlApplicationContext cpx = new ClassPathXmlApplicationContext("beans.xml");
        UserService userService = (UserService) cpx.getBean("userService");

        if(userService.login(user)){
            System.out.println("登陆成功！");
        }
        else System.out.println("登陆失败！");
    }
}
