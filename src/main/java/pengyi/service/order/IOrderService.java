package pengyi.service.order;

import pengyi.core.api.ApiPagination;
import pengyi.core.exception.ApiRemoteCallFailedException;
import pengyi.model.order.Order;
import pengyi.service.order.command.OrderListCommand;

import java.util.List;

/**
 * Created by YJH on 2016/3/18.
 */
public interface IOrderService {
    ApiPagination<Order> pagination(OrderListCommand command) throws ApiRemoteCallFailedException;

    Order show(String id) throws ApiRemoteCallFailedException;

    List<Order> exportExcel(OrderListCommand command) throws ApiRemoteCallFailedException;
}
