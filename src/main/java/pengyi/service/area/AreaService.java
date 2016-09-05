package pengyi.service.area;

import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengyi.core.api.ApiRequest;
import pengyi.core.api.ApiUrl;
import pengyi.core.exception.ApiRemoteCallFailedException;
import pengyi.core.util.CoreStringUtils;
import pengyi.model.area.Area;
import pengyi.service.area.command.SearchAreaCommand;

import java.util.List;

/**
 * Created by YJH on 2016/3/17.
 */
@Service("areaService")
public class AreaService implements IAreaService {

    @Autowired
    private ApiRequest apiAdminService;

    @Override
    public List<Area> searchArea(SearchAreaCommand command) throws ApiRemoteCallFailedException {
        apiAdminService.urlConnection(ApiUrl.AREA_SEARCH_URL, CoreStringUtils.assemblingParameters(command));
        List<Area> data = apiAdminService.convertJsonTo(new TypeReference<List<Area>>() {
        });
        return data;
    }

}
