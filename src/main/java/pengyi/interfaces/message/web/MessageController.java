package pengyi.interfaces.message.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pengyi.core.api.ApiPagination;
import pengyi.core.commons.Constants;
import pengyi.core.exception.ApiRemoteCallFailedException;
import pengyi.interfaces.shared.web.AlertMessage;
import pengyi.interfaces.shared.web.BaseController;
import pengyi.model.message.Message;
import pengyi.model.user.BaseUser;
import pengyi.service.message.IMessageService;
import pengyi.service.message.command.CreateMessageCommand;
import pengyi.service.message.command.ListMessageCommand;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Locale;

/**
 * Created by liubowen on 2016/3/24.
 */
@Controller
@RequestMapping("/message")
public class MessageController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private IMessageService messageService;

    @RequestMapping(value = "/list")
    public ModelAndView list(ListMessageCommand command, HttpSession session, Locale locale) {
        BaseUser user = (BaseUser) session.getAttribute(Constants.SESSION_USER);
        if (null == user) {
            return new ModelAndView("redirect:/logout");
        }
        command.setCompany(user.getId());
        ApiPagination<Message> pagination = null;
        AlertMessage alertMessage;
        try {
            pagination = messageService.pagination(command);
        } catch (ApiRemoteCallFailedException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getResponse().getMessage());
            return new ModelAndView("/errors/page500", AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);

        }
        return new ModelAndView("message/list", "pagination", pagination).addObject("command", command);
    }

    @RequestMapping(value = "/show/{id}")
    public ModelAndView show(@PathVariable String id,int searchType, HttpSession session, RedirectAttributes redirectAttributes, Locale locale) {
        BaseUser user = (BaseUser) session.getAttribute(Constants.SESSION_USER);
        if (null == user) {
            return new ModelAndView("redirect:/logout");
        }
        Message message = null;
        AlertMessage alertMessage;
        try {
            message = messageService.show(id);
        } catch (ApiRemoteCallFailedException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getResponse().getMessage());
            return new ModelAndView("/errors/page500", AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        return new ModelAndView("message/show", "message", message).addObject("searchType", searchType);
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {
        return new ModelAndView("message/create");
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(@Valid @ModelAttribute("command") CreateMessageCommand command, HttpSession session,
                               RedirectAttributes redirectAttributes, Locale locale) {
        BaseUser user = (BaseUser) session.getAttribute(Constants.SESSION_USER);
        if (null == user) {
            return new ModelAndView("redirect:/logout");
        }
        command.setCompany(user.getId());
        AlertMessage alertMessage;
        try {
            messageService.create(command);
        } catch (ApiRemoteCallFailedException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getResponse().getMessage());
            return new ModelAndView("/message/create", AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage)
                    .addObject("command", command);
        }

        alertMessage = new AlertMessage(this.getMessage("default.create.success.message", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        return new ModelAndView("redirect:/message/list");

    }

}
