package pengyi.service.rescue;

import pengyi.core.api.ApiPagination;
import pengyi.core.exception.ApiRemoteCallFailedException;
import pengyi.model.rescue.Rescue;
import pengyi.service.rescue.command.EditRescueCommand;
import pengyi.service.rescue.command.ListRescueCommand;

/**
 * Created by LvDi on 2016/3/22.
 */
public interface IRescueService {
    Rescue info(String id) throws ApiRemoteCallFailedException;

    ApiPagination<Rescue> searchRescue(ListRescueCommand command) throws ApiRemoteCallFailedException;

    void updateRescue(EditRescueCommand command) throws ApiRemoteCallFailedException;

    void finish(EditRescueCommand command) throws ApiRemoteCallFailedException;
}
