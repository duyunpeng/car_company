package pengyi.service.urlresources;

import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengyi.core.api.ApiRequest;
import pengyi.core.api.ApiUrl;
import pengyi.core.exception.ApiRemoteCallFailedException;
import pengyi.model.urlresources.UrlResources;

import java.util.List;

/**
 * Created by YJH on 2016/3/15.
 */
@Service("urlResourcesService")
public class UrlResourcesService implements IUrlResourcesService {

    @Autowired
    private ApiRequest apiAdminService;

    @Override
    public List<UrlResources> allList() throws ApiRemoteCallFailedException {

        apiAdminService.urlConnection(ApiUrl.URL_RESOURCES_ALL_LIST_URL, "");

        List<UrlResources> data = apiAdminService.convertJsonTo(new TypeReference<List<UrlResources>>() {
        });
        return data;
    }

}
