package pengyi.service.urlresources;

import pengyi.core.exception.ApiRemoteCallFailedException;
import pengyi.model.urlresources.UrlResources;

import java.util.List;

/**
 * Created by YJH on 2016/3/15.
 */
public interface IUrlResourcesService {
    List<UrlResources> allList() throws ApiRemoteCallFailedException;
}
