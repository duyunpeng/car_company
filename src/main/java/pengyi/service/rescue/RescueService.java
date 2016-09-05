package pengyi.service.rescue;

import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengyi.core.api.ApiPagination;
import pengyi.core.api.ApiRequest;
import pengyi.core.api.ApiUrl;
import pengyi.core.exception.ApiRemoteCallFailedException;
import pengyi.core.type.RescueStatus;
import pengyi.core.util.CoreStringUtils;
import pengyi.model.rescue.Rescue;
import pengyi.service.rescue.command.EditRescueCommand;
import pengyi.service.rescue.command.ListRescueCommand;
import pengyi.service.share.command.ShareCommand;

/**
 * Created by LvDi on 2016/3/22.
 */
@Service("rescueService")
public class RescueService implements IRescueService {
    @Autowired
    private ApiRequest apiAdminService;

    @Override
    public Rescue info(String id) throws ApiRemoteCallFailedException {
        ShareCommand command = new ShareCommand();
        command.setId(id);
        apiAdminService.urlConnection(ApiUrl.RESCUE_INFO_URL, CoreStringUtils.assemblingParameters(command));
        Rescue rescue = apiAdminService.convertJsonTo(new TypeReference<Rescue>() {
        });
        return rescue;
    }

    @Override
    public ApiPagination<Rescue> searchRescue(ListRescueCommand command) throws ApiRemoteCallFailedException {

        command.verifyPage();
        command.verifyPageSize(20);
        apiAdminService.urlConnection(ApiUrl.RESCUE_LIST_URL, CoreStringUtils.assemblingParameters(command
        ));
        ApiPagination<Rescue> pagination = apiAdminService.convertJsonTo(new TypeReference<ApiPagination<Rescue>>() {
        });
        return pagination;
    }

    @Override
    public void updateRescue(EditRescueCommand command) throws ApiRemoteCallFailedException {
        command.setRescueStatus(RescueStatus.IN_RESCUE);
        apiAdminService.urlConnection(ApiUrl.RESCUE_DEAL_URL, CoreStringUtils.assemblingParameters(command
        ));
    }

    @Override
    public void finish(EditRescueCommand command) throws ApiRemoteCallFailedException {
        apiAdminService.urlConnection(ApiUrl.RESCUE_FINISH_URL, CoreStringUtils.assemblingParameters(command
        ));
    }
}
