package pengyi.service.company;

import pengyi.core.exception.ApiRemoteCallFailedException;
import pengyi.model.user.BaseUser;
import pengyi.model.user.company.Company;
import pengyi.service.company.command.*;

/**
 * Created by YJH on 2016/3/16.
 */
public interface ICompanyService {
    BaseUser searchByRole(String userName) throws ApiRemoteCallFailedException;

    BaseUser searchByUserName(String userName) throws ApiRemoteCallFailedException;

    BaseUser login(LoginUserCommand command) throws ApiRemoteCallFailedException;

    void register(RegisterCompanyCommand command) throws ApiRemoteCallFailedException;

    Company info(String companyId) throws ApiRemoteCallFailedException;

    void edit(EditCompanyCommand command) throws ApiRemoteCallFailedException;

    void updatePassword(UpdatePasswordCommand command) throws ApiRemoteCallFailedException;

    void resetPassword(ResetPasswordCommand command) throws ApiRemoteCallFailedException;
}
