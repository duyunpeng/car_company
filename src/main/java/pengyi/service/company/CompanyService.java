package pengyi.service.company;

import com.alibaba.fastjson.TypeReference;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengyi.core.api.ApiRequest;
import pengyi.core.api.ApiUrl;
import pengyi.core.exception.ApiRemoteCallFailedException;
import pengyi.core.exception.NoFoundException;
import pengyi.core.exception.NotEqualException;
import pengyi.core.redis.RedisService;
import pengyi.core.util.CoreStringUtils;
import pengyi.model.user.BaseUser;
import pengyi.model.user.company.Company;
import pengyi.service.company.command.*;

/**
 * Created by YJH on 2016/3/16.
 */
@Service("companyService")
public class CompanyService implements ICompanyService {

    @Autowired
    private ApiRequest apiAdminService;

    @Autowired
    private RedisService redisService;

    @Override
    public BaseUser searchByRole(String userName) throws ApiRemoteCallFailedException {
        ListBaseCommand command = new ListBaseCommand();
        command.setUserName(userName);
        command.setRoleName("company");
        apiAdminService.urlConnection(ApiUrl.BASE_USER_SEARCH_BY_USER_ROLE_URL, CoreStringUtils.assemblingParameters(command));
        BaseUser data = apiAdminService.convertJsonTo(new TypeReference<BaseUser>() {
        });
        return data;
    }

    @Override
    public BaseUser searchByUserName(String userName) throws ApiRemoteCallFailedException {
        SearchBaseUserCommand command = new SearchBaseUserCommand();
        command.setUserName(userName);
        apiAdminService.urlConnection(ApiUrl.BASE_USER_SEARCH_BY_USER_URL, CoreStringUtils.assemblingParameters(command
        ));

        BaseUser data = apiAdminService.convertJsonTo(new TypeReference<BaseUser>() {
        });
        return data;
    }

    @Override
    public BaseUser login(LoginUserCommand command) throws ApiRemoteCallFailedException {
        BaseUser baseUser = this.searchByUserName(command.getUsername());
        if (null == baseUser) {
            throw new UnknownAccountException();
        }

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(command.getUsername(), command.getPassword());
        subject.login(token);

        return baseUser;
    }

    @Override
    public void register(RegisterCompanyCommand command) throws ApiRemoteCallFailedException {
        if (redisService.exists(command.getUserName())) {
            if (redisService.getCache(command.getUserName()).equals(command.getVerificationCode())) {
                apiAdminService.urlConnection(ApiUrl.COMPANY_REGISTER_URL, CoreStringUtils.assemblingParameters(command));
                redisService.delete(command.getUserName());
            } else {
                throw new NotEqualException("验证码错误!");
            }
        } else {
            throw new NoFoundException("请先发送验证码!");
        }
    }

    @Override
    public Company info(String companyId) throws ApiRemoteCallFailedException {
        SearchBaseUserCommand command = new SearchBaseUserCommand();
        command.setId(companyId);
        apiAdminService.urlConnection(ApiUrl.COMPANY_INFO_URL, CoreStringUtils.assemblingParameters(command));
        Company company = apiAdminService.convertJsonTo(new TypeReference<Company>() {
        });
        return company;
    }

    @Override
    public void edit(EditCompanyCommand command) throws ApiRemoteCallFailedException {
        apiAdminService.urlConnection(ApiUrl.COMPANY_EDIT_URL, CoreStringUtils.assemblingParameters(command));
    }

    @Override
    public void updatePassword(UpdatePasswordCommand command) throws ApiRemoteCallFailedException {
        apiAdminService.urlConnection(ApiUrl.COMPANY_UPDATE_PASSWORD_URL, CoreStringUtils.assemblingParameters(command));
    }

    @Override
    public void resetPassword(ResetPasswordCommand command) throws ApiRemoteCallFailedException {
        if (redisService.exists(command.getUserName())) {
            if (redisService.getCache(command.getUserName()).equals(command.getVerificationCode())) {
                apiAdminService.urlConnection(ApiUrl.COMPANY_RESET_PASSWORD_URL, CoreStringUtils.assemblingParameters(command));
                redisService.delete(command.getUserName());
            } else {
                throw new NotEqualException("验证码错误!");
            }
        } else {
            throw new NoFoundException("请先发送验证码!");
        }
    }
}
