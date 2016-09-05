package pengyi.interfaces.rescue.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
import pengyi.model.rescue.Rescue;
import pengyi.model.user.BaseUser;
import pengyi.service.rescue.IRescueService;
import pengyi.service.rescue.command.EditRescueCommand;
import pengyi.service.rescue.command.ListRescueCommand;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Locale;


/**
 * Created by LvDi on 2016/3/23.
 */
@Controller
@RequestMapping("/rescue")
public class RescueController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private IRescueService rescueService;

    @RequestMapping(value = "/info")
    public ModelAndView info(String id, HttpSession session, RedirectAttributes redirectAttributes, Locale locale) {

        Rescue rescue = null;
        AlertMessage alertMessage;
        try {
            rescue = rescueService.info(id);
        } catch (ApiRemoteCallFailedException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getResponse().getMessage());
            return new ModelAndView("/errors/page500", AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        return new ModelAndView("/rescue/info", "rescue", rescue);
    }

    @RequestMapping(value = "/finish")
    public ModelAndView finish(EditRescueCommand command) {

        AlertMessage alertMessage;
        try {
            rescueService.finish(command);
        } catch (ApiRemoteCallFailedException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getResponse().getMessage());
            return new ModelAndView("/errors/page500", AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        return new ModelAndView("redirect:/rescue/list");
    }

    @RequestMapping(value = "/list")
    public ModelAndView list(ListRescueCommand command, HttpSession session) {
        BaseUser user = (BaseUser) session.getAttribute(Constants.SESSION_USER);
        if (null == user) {
            return new ModelAndView("redirect:/logout");
        }
        ApiPagination<Rescue> pagination = null;
        AlertMessage alertMessage;
        command.setCompany(user.getId());
        try {
            pagination = rescueService.searchRescue(command);
        } catch (ApiRemoteCallFailedException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getResponse().getMessage());
            return new ModelAndView("/errors/page500", AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        return new ModelAndView("/rescue/list", "pagination", pagination)
                .addObject("command", command);
    }

    @RequestMapping(value = "/deal/{id}", method = RequestMethod.GET)
    public ModelAndView deal(@PathVariable String id,@ModelAttribute("command") EditRescueCommand command, HttpSession session,
                             RedirectAttributes redirectAttributes, Locale locale) {
        BaseUser user = (BaseUser) session.getAttribute(Constants.SESSION_USER);
        if (null == user) {
            return new ModelAndView("redirect:/logout");
        }
        Rescue rescue = null;
        AlertMessage alertMessage;
        try {
            rescue = rescueService.info(id);
        } catch (ApiRemoteCallFailedException e) {
            logger.warn(e.getMessage());
        }
        return new ModelAndView("/rescue/deal", "command", command).addObject("rescue", rescue);
    }

    @RequestMapping(value = "/deal", method = RequestMethod.POST)
    public ModelAndView deal(@Valid @ModelAttribute("command") EditRescueCommand command, BindingResult bindingResult,
                             HttpSession session, RedirectAttributes redirectAttributes,
                             Locale locale) {
        BaseUser baseUser = (BaseUser) session.getAttribute(Constants.SESSION_USER);
        if (null == baseUser) {
            return new ModelAndView("redirect:/logout");
        }
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/rescue/deal", "command", command);
        }
        AlertMessage alertMessage;
        try {
            rescueService.updateRescue(command);
        } catch (ApiRemoteCallFailedException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getResponse().getMessage());
            return new ModelAndView("/rescue/deal", "command", command)
                    .addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);

        }
        alertMessage = new AlertMessage(this.getMessage("default.edit.success.message", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        return new ModelAndView("redirect:/rescue/list");
    }

}
