package pengyi.service.billing;

import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengyi.core.api.ApiPagination;
import pengyi.core.api.ApiRequest;
import pengyi.core.api.ApiUrl;
import pengyi.core.exception.ApiRemoteCallFailedException;
import pengyi.core.util.CoreStringUtils;
import pengyi.service.billing.command.EditBillingCommand;
import pengyi.model.billing.Billing;
import pengyi.service.billing.command.CreateBillingCommand;
import pengyi.service.billing.command.ListBillingCommand;
import pengyi.service.share.command.ShareCommand;

/**
 * Created by YJH on 2016/3/25.
 */
@Service("billingService")
public class BillingService implements IBillingService {

    @Autowired
    private ApiRequest apiAdminService;

    @Override
    public Billing show(String id) throws ApiRemoteCallFailedException {
        ShareCommand command = new ShareCommand();
        command.setId(id);
        apiAdminService.urlConnection(ApiUrl.BILLING_INFO_URL, CoreStringUtils.assemblingParameters(command));
        Billing billing = apiAdminService.convertJsonTo(new TypeReference<Billing>() {
        });
        return billing;
    }

    @Override
    public void create(CreateBillingCommand command) throws ApiRemoteCallFailedException {
        apiAdminService.urlConnection(ApiUrl.BILLING_CREATE_URL, CoreStringUtils.assemblingParameters(command));
    }

    @Override
    public void edit(EditBillingCommand command) throws ApiRemoteCallFailedException {
        apiAdminService.urlConnection(ApiUrl.BILLING_EDIT_URL, CoreStringUtils.assemblingParameters(command));
    }

    @Override
    public ApiPagination<Billing> pagination(ListBillingCommand command) throws ApiRemoteCallFailedException {
        command.verifyPage();
        command.verifyPageSize(10);
        apiAdminService.urlConnection(ApiUrl.BILLING_LIST_URL, CoreStringUtils.assemblingParameters(command));
        ApiPagination<Billing> pagination = apiAdminService.convertJsonTo(new TypeReference<ApiPagination<Billing>>() {
        });
        return pagination;
    }

}
