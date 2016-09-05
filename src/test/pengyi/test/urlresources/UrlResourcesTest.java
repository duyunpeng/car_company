package pengyi.test.urlresources;

import org.testng.annotations.Test;
import pengyi.core.type.EnableStatus;
import pengyi.core.util.CoreStringUtils;
import pengyi.core.util.HttpUtil;
import pengyi.service.company.command.RegisterCompanyCommand;
import pengyi.service.driver.command.ListDriverCommand;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by YJH on 2016/3/15.
 */
public class UrlResourcesTest {

    @Test
    public void test() {
        HttpUtil.urlConnection("http://127.0.0.1:8080/company/api/register", "userName=123");

    }

    @Test
    public void test_1() throws IllegalAccessException, InvocationTargetException {
        ListDriverCommand command = new ListDriverCommand();
//        command.setDriverName("q132");
        command.setCompany("123123");
        command.setStatus(EnableStatus.ENABLE);
        command.verifyPage();
        command.verifyPageSize(10);
        System.out.println(CoreStringUtils.assemblingParameters(command));
//        System.out.println(command.getClass().getResource(""));

    }
}
