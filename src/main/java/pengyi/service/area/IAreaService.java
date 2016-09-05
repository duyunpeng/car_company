package pengyi.service.area;

import pengyi.core.exception.ApiRemoteCallFailedException;
import pengyi.model.area.Area;
import pengyi.service.area.command.SearchAreaCommand;

import java.util.List;

/**
 * Created by YJH on 2016/3/17.
 */
public interface IAreaService {
    List<Area> searchArea(SearchAreaCommand command) throws ApiRemoteCallFailedException;
}
