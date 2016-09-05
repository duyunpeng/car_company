package pengyi.service.driver;

import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengyi.core.api.ApiPagination;
import pengyi.core.api.ApiRequest;
import pengyi.core.api.ApiUrl;
import pengyi.core.exception.ApiRemoteCallFailedException;
import pengyi.core.type.AuthStatus;
import pengyi.core.type.EnableStatus;
import pengyi.core.util.CoreStringUtils;
import pengyi.model.user.driver.Driver;
import pengyi.service.driver.command.CreateDriverCommand;
import pengyi.service.driver.command.EditDriverCommand;
import pengyi.service.driver.command.ListDriverCommand;
import pengyi.service.share.command.ShareCommand;

/**
 * Created by YJH on 2016/3/23.
 */
@Service("driverService")
public class DriverService implements IDriverService {

    @Autowired
    private ApiRequest apiAdminService;

    @Override
    public ApiPagination<Driver> pagination(ListDriverCommand command) throws ApiRemoteCallFailedException {
        command.verifyPage();
        command.verifyPageSize(10);
        apiAdminService.urlConnection(ApiUrl.DRIVER_PAGINATION_URL, CoreStringUtils.assemblingParameters(command));
        ApiPagination<Driver> pagination = apiAdminService.convertJsonTo(new TypeReference<ApiPagination<Driver>>() {
        });
        return pagination;
    }

    @Override
    public ApiPagination<Driver> authPagination(ListDriverCommand command) throws ApiRemoteCallFailedException {
        command.verifyPage();
        command.verifyPageSize(10);
        command.setStatus(EnableStatus.DISABLE);
        command.setAuthStatus(AuthStatus.AUTH_WAIT);
        apiAdminService.urlConnection(ApiUrl.DRIVER_PAGINATION_URL, CoreStringUtils.assemblingParameters(command));
        ApiPagination<Driver> pagination = apiAdminService.convertJsonTo(new TypeReference<ApiPagination<Driver>>() {
        });
        return pagination;
    }

    @Override
    public void authDriver(ShareCommand command) throws ApiRemoteCallFailedException {
        apiAdminService.urlConnection(ApiUrl.COMPANY_DRIVER_AUDITING_URL, CoreStringUtils.assemblingParameters(command));
    }

    @Override
    public Driver show(String id) throws ApiRemoteCallFailedException {
        ShareCommand command = new ShareCommand();
        command.setId(id);
        apiAdminService.urlConnection(ApiUrl.DRIVER_SHOW_URL, CoreStringUtils.assemblingParameters(command));
        Driver driver = apiAdminService.convertJsonTo(new TypeReference<Driver>() {
        });
        return driver;
    }

    @Override
    public void edit(EditDriverCommand command) throws ApiRemoteCallFailedException {
        apiAdminService.urlConnection(ApiUrl.DRIVER_EDIT_URL, CoreStringUtils.assemblingParameters(command));
    }

    @Override
    public Driver create(CreateDriverCommand command) throws ApiRemoteCallFailedException {
        apiAdminService.urlConnection(ApiUrl.DRIVER_CREATE_URL, CoreStringUtils.assemblingParameters(command));
        Driver driver = apiAdminService.convertJsonTo(new TypeReference<Driver>() {
        });
        return driver;
    }
}
