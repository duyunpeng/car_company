package pengyi.service.driver;

import pengyi.core.api.ApiPagination;
import pengyi.core.exception.ApiRemoteCallFailedException;
import pengyi.model.user.driver.Driver;
import pengyi.service.driver.command.CreateDriverCommand;
import pengyi.service.driver.command.EditDriverCommand;
import pengyi.service.driver.command.ListDriverCommand;
import pengyi.service.share.command.ShareCommand;

/**
 * Created by YJH on 2016/3/23.
 */
public interface IDriverService {
    ApiPagination<Driver> pagination(ListDriverCommand command) throws ApiRemoteCallFailedException;

    ApiPagination<Driver> authPagination(ListDriverCommand command) throws ApiRemoteCallFailedException;

    void authDriver(ShareCommand command) throws ApiRemoteCallFailedException;

    Driver show(String id) throws ApiRemoteCallFailedException;

    void edit(EditDriverCommand command) throws ApiRemoteCallFailedException;

    Driver create(CreateDriverCommand command) throws ApiRemoteCallFailedException;
}
