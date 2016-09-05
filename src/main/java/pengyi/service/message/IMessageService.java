package pengyi.service.message;

import pengyi.core.api.ApiPagination;
import pengyi.core.exception.ApiRemoteCallFailedException;
import pengyi.model.message.Message;
import pengyi.service.message.command.CreateMessageCommand;
import pengyi.service.message.command.ListMessageCommand;

/**
 * Created by liubowen on 2016/3/24.
 */
public interface IMessageService {
    ApiPagination<Message> pagination(ListMessageCommand command) throws ApiRemoteCallFailedException;

    void create(CreateMessageCommand command) throws ApiRemoteCallFailedException;

    Message show(String messageId) throws ApiRemoteCallFailedException;

}
