package pengyi.interfaces.billing.web;

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
import pengyi.model.billing.Billing;
import pengyi.model.user.BaseUser;
import pengyi.service.billing.IBillingService;
import pengyi.service.billing.command.CreateBillingCommand;
import pengyi.service.billing.command.EditBillingCommand;
import pengyi.service.billing.command.ListBillingCommand;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Locale;

/**
 * Created by YJH on 2016/3/25.
 */
@Controller
@RequestMapping("/billing")
public class BillingController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IBillingService billingService;

    @RequestMapping(value = "/list")
    public ModelAndView list(ListBillingCommand command, HttpSession session) {
        BaseUser baseUser = (BaseUser) session.getAttribute(Constants.SESSION_USER);
        if (null == baseUser) {
            return new ModelAndView("redirect:/logout");
        }
        AlertMessage alertMessage;
        command.setCompany(baseUser.getId());
        ApiPagination<Billing> pagination = null;
        try {
            pagination = billingService.pagination(command);
        } catch (ApiRemoteCallFailedException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getResponse().getMessage());
            return new ModelAndView("/error/page500", AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        return new ModelAndView("/billing/list", "pagination", pagination).addObject("command", command);
    }

    @RequestMapping(value = "/show/{id}")
    public ModelAndView info(@PathVariable String id, HttpSession session) {
        BaseUser baseUser = (BaseUser) session.getAttribute(Constants.SESSION_USER);
        if (null == baseUser) {
            return new ModelAndView("redirect:/logout");
        }
        AlertMessage alertMessage;
        Billing billing = null;
        try {
            billing = billingService.show(id);
        } catch (ApiRemoteCallFailedException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getResponse().getMessage());
            return new ModelAndView("/error/page500", AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }

        return new ModelAndView("/billing/show", "billing", billing);
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create(@ModelAttribute("command") CreateBillingCommand command) {
        return new ModelAndView("/billing/create", "command", command);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(@Valid @ModelAttribute("command") CreateBillingCommand command,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes,
                               HttpSession session, Locale locale) {
        BaseUser baseUser = (BaseUser) session.getAttribute(Constants.SESSION_USER);
        if (null == baseUser) {
            return new ModelAndView("redirect:/logout");
        }

        if (bindingResult.hasErrors()) {
            return new ModelAndView("/billing/create", "command", command);
        }
        AlertMessage alertMessage;
        command.setCompany(baseUser.getId());
        try {

            billingService.create(command);
        } catch (ApiRemoteCallFailedException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getResponse().getMessage());
            return new ModelAndView("/billing/create", AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            return new ModelAndView("/error/page500", AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        alertMessage = new AlertMessage(this.getMessage("default.create.success.message", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        return new ModelAndView("redirect:/billing/list");
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable String id, @ModelAttribute("command") EditBillingCommand command, RedirectAttributes redirectAttributes,
                             HttpSession session, Locale locale) {
        BaseUser baseUser = (BaseUser) session.getAttribute(Constants.SESSION_USER);
        if (null == baseUser) {
            return new ModelAndView("redirect:/logout");
        }
        AlertMessage alertMessage;
        Billing billing = null;
        try {
            billing = billingService.show(id);
        } catch (ApiRemoteCallFailedException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getResponse().getMessage());
            return new ModelAndView("/error/page500", AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }

        return new ModelAndView("/billing/edit", "billing", billing).addObject("command", command);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView edit(@Valid @ModelAttribute("command") EditBillingCommand command, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes, HttpSession session, Locale locale) {
        BaseUser baseUser = (BaseUser) session.getAttribute(Constants.SESSION_USER);
        if (null == baseUser) {
            return new ModelAndView("redirect:/logout");
        }
        AlertMessage alertMessage;
        try {
            billingService.edit(command);
        } catch (ApiRemoteCallFailedException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getResponse().getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/billing/info");
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            return new ModelAndView("/error/page500", AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }

        alertMessage = new AlertMessage(this.getMessage("default.edit.success.message", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        return new ModelAndView("redirect:/billing/list");
    }
}
