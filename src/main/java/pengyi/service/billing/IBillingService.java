package pengyi.service.billing;

import pengyi.core.api.ApiPagination;
import pengyi.core.exception.ApiRemoteCallFailedException;
import pengyi.service.billing.command.EditBillingCommand;
import pengyi.model.billing.Billing;
import pengyi.service.billing.command.CreateBillingCommand;
import pengyi.service.billing.command.ListBillingCommand;

/**
 * Created by YJH on 2016/3/25.
 */
public interface IBillingService {
    Billing show(String id) throws ApiRemoteCallFailedException;

    void create(CreateBillingCommand command) throws ApiRemoteCallFailedException;

    void edit(EditBillingCommand command) throws ApiRemoteCallFailedException;

    ApiPagination<Billing> pagination(ListBillingCommand command) throws ApiRemoteCallFailedException;
}
