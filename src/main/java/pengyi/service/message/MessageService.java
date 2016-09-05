package pengyi.service.message;

import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengyi.core.api.ApiPagination;
import pengyi.core.api.ApiRequest;
import pengyi.core.api.ApiUrl;
import pengyi.core.exception.ApiRemoteCallFailedException;
import pengyi.core.redis.RedisService;
import pengyi.core.util.CoreStringUtils;
import pengyi.model.message.Message;
import pengyi.service.message.command.CreateMessageCommand;
import pengyi.service.message.command.ListMessageCommand;
import pengyi.service.share.command.ShareCommand;

/**
 * Created by liubowen on 2016/3/24.
 */
@Service("messageService")
public class MessageService implements IMessageService {
    @Autowired
    private ApiRequest apiMessageService;

    @Autowired
    private RedisService redisService;


    @Override
    public ApiPagination<Message> pagination(ListMessageCommand command) throws ApiRemoteCallFailedException {

        command.verifyPage();
        command.verifyPageSize(20);
        apiMessageService.urlConnection(ApiUrl.MESSAGE_COMPANY_LIST_URL, CoreStringUtils.assemblingParameters(command));
        ApiPagination<Message> pagination = apiMessageService.convertJsonTo(new TypeReference<ApiPagination<Message>>() {
        });
        return pagination;
    }


    @Override
    public void create(CreateMessageCommand command) throws ApiRemoteCallFailedException {
        apiMessageService.urlConnection(ApiUrl.MESSAGE_COMPANY_CREATE, CoreStringUtils.assemblingParameters(command));

    }

    @Override
    public Message show(String messageId) throws ApiRemoteCallFailedException {
        ShareCommand command = new ShareCommand();
        command.setId(messageId);
        apiMessageService.urlConnection(ApiUrl.MESSAGE_COMPANY_SHOW, CoreStringUtils.assemblingParameters(command));
        Message message = apiMessageService.convertJsonTo(new TypeReference<Message>() {
        });
        return message;
    }
}
