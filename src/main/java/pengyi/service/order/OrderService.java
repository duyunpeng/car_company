package pengyi.service.order;

import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengyi.core.api.ApiPagination;
import pengyi.core.api.ApiRequest;
import pengyi.core.api.ApiUrl;
import pengyi.core.exception.ApiRemoteCallFailedException;
import pengyi.core.type.OrderStatus;
import pengyi.core.util.CoreStringUtils;
import pengyi.model.order.Order;
import pengyi.service.order.command.OrderListCommand;
import pengyi.service.share.command.ShareCommand;

import java.util.List;

/**
 * Created by YJH on 2016/3/18.
 */
@Service("orderService")
public class OrderService implements IOrderService {

    @Autowired
    private ApiRequest apiAdminService;

    @Override
    public ApiPagination<Order> pagination(OrderListCommand command) throws ApiRemoteCallFailedException {
        command.verifyPage();
        command.verifyPageSize(20);
        apiAdminService.urlConnection(ApiUrl.ORDER_COMPANY_PAGINATION_URL, CoreStringUtils.assemblingParameters(command));
        ApiPagination<Order> pagination = apiAdminService.convertJsonTo(new TypeReference<ApiPagination<Order>>() {
        });
        return pagination;
    }

    @Override
    public Order show(String id) throws ApiRemoteCallFailedException {
        ShareCommand command = new ShareCommand();
        command.setId(id);
        apiAdminService.urlConnection(ApiUrl.ORDER_COMPANY_SHOW_URL, CoreStringUtils.assemblingParameters(command));
        Order order = apiAdminService.convertJsonTo(new TypeReference<Order>() {
        });
        return order;
    }

    @Override
    public List<Order> exportExcel(OrderListCommand command) throws ApiRemoteCallFailedException {
        apiAdminService.urlConnection(ApiUrl.ORDER_COMPANY_EXPORT_EXCEL_URL, CoreStringUtils.assemblingParameters(command));
        return apiAdminService.convertJsonTo(new TypeReference<List<Order>>() {
        });
    }
}
