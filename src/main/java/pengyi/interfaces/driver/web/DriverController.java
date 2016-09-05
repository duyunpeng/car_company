package pengyi.interfaces.driver.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pengyi.core.api.ApiPagination;
import pengyi.core.commons.Constants;
import pengyi.core.exception.ApiRemoteCallFailedException;
import pengyi.interfaces.shared.web.AlertMessage;
import pengyi.interfaces.shared.web.BaseController;
import pengyi.interfaces.shared.web.JsonMessage;
import pengyi.model.user.BaseUser;
import pengyi.model.user.driver.Driver;
import pengyi.service.driver.IDriverService;
import pengyi.service.driver.command.CreateDriverCommand;
import pengyi.service.driver.command.EditDriverCommand;
import pengyi.service.driver.command.ListDriverCommand;
import pengyi.service.share.command.ShareCommand;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Locale;

/**
 * Created by YJH on 2016/3/23.
 */
@Controller
@RequestMapping("/driver")
public class DriverController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IDriverService driverService;

    @RequestMapping(value = "/list")
    public ModelAndView pagination(ListDriverCommand command, HttpSession session) {
        BaseUser user = (BaseUser) session.getAttribute(Constants.SESSION_USER);
        if (null == user) {
            return new ModelAndView("redirect:/logout");
        }
        command.setCompany(user.getId());
        AlertMessage alertMessage;
        ApiPagination<Driver> pagination;
        try {
            pagination = driverService.pagination(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            return new ModelAndView("/error/page500").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        return new ModelAndView("/driver/list", "pagination", pagination).addObject("command", command);
    }

    @RequestMapping(value = "/auth_list")
    public ModelAndView authPagination(ListDriverCommand command, HttpSession session) {
        BaseUser user = (BaseUser) session.getAttribute(Constants.SESSION_USER);
        if (null == user) {
            return new ModelAndView("redirect:/logout");
        }
        command.setCompany(user.getId());
        AlertMessage alertMessage;
        ApiPagination<Driver> pagination;
        try {
            pagination = driverService.authPagination(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            return new ModelAndView("/error/page500").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        return new ModelAndView("/driver/authlist", "pagination", pagination).addObject("command", command);
    }

    @RequestMapping(value = "/auth_driver")
    public ModelAndView authDriver(ShareCommand command, HttpSession session,
                                   RedirectAttributes redirectAttributes, Locale locale) {
        BaseUser baseUser = (BaseUser) session.getAttribute(Constants.SESSION_USER);
        if (null == baseUser) {
            return new ModelAndView("redirect:/logout");
        }
        AlertMessage alertMessage;
        try {
            driverService.authDriver(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            return new ModelAndView("/error/page500").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        alertMessage = new AlertMessage(this.getMessage("default.operate.success.message", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        return new ModelAndView("redirect:/driver/auth_list");
    }

    @RequestMapping(value = "/show/{id}")
    public ModelAndView show(@PathVariable String id, HttpSession session, Locale locale) {
        BaseUser baseUser = (BaseUser) session.getAttribute(Constants.SESSION_USER);
        if (null == baseUser) {
            return new ModelAndView("redirect:/logout");
        }
        AlertMessage alertMessage;
        Driver driver = null;
        try {
            driver = driverService.show(id);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            return new ModelAndView("/error/page500").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        return new ModelAndView("/driver/show", "driver", driver);
    }

    @RequestMapping(value = "/edit/{id}")
    public ModelAndView edit(@PathVariable String id, @ModelAttribute("command") EditDriverCommand command, HttpSession session) {
        BaseUser baseUser = (BaseUser) session.getAttribute(Constants.SESSION_USER);
        if (null == baseUser) {
            return new ModelAndView("redirect:/logout");
        }
        AlertMessage alertMessage;
        Driver driver = null;
        try {
            driver = driverService.show(id);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            return new ModelAndView("/error/page500").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        return new ModelAndView("/driver/edit", "driver", driver).addObject("command", command);
    }

    @RequestMapping(value = "/edit")
    public ModelAndView edit(@Valid @ModelAttribute("command") EditDriverCommand command, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes, HttpSession session, Locale locale) {
        AlertMessage alertMessage;
        if (bindingResult.hasErrors()) {
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, this.getMessage("default.parameter.error.message", null, locale));
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            redirectAttributes.addAttribute("id", command.getId());
            return new ModelAndView("redirect:/driver/edit/{id}");
        }
        try {
            driverService.edit(command);
        } catch (ApiRemoteCallFailedException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getResponse().getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            redirectAttributes.addAttribute("id", command.getId());
            return new ModelAndView("redirect:/driver/edit/{id}");
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            return new ModelAndView("/error/page500").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }

        alertMessage = new AlertMessage(this.getMessage("default.edit.success.message", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        redirectAttributes.addAttribute("id", command.getId());
        return new ModelAndView("redirect:/driver/show/{id}");
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create(@ModelAttribute("command") CreateDriverCommand command) {
        return new ModelAndView("/driver/create", "command", command);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(@Valid @ModelAttribute("command'") CreateDriverCommand command, BindingResult bindingResult,
                               HttpSession session, RedirectAttributes redirectAttributes, Locale locale) {
        BaseUser baseUser = (BaseUser) session.getAttribute(Constants.SESSION_USER);
        if (null == baseUser) {
            return new ModelAndView("redirect:/logout");
        }
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/driver/create", "command", command);
        }
        command.setCompany(baseUser.getId());
        AlertMessage alertMessage;
        Driver driver = null;
        try {
            driver = driverService.create(command);
        } catch (ApiRemoteCallFailedException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getResponse().getMessage());
            return new ModelAndView("/driver/create", "command", command).addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            return new ModelAndView("/error/page500").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }

        alertMessage = new AlertMessage(this.getMessage("default.create.success.message", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        redirectAttributes.addAttribute("id", driver.getId());
        return new ModelAndView("redirect:/driver/show/{id}");
    }

    @RequestMapping(value = "/driver_list")
    @ResponseBody
    public ApiPagination<Driver> driverList(@RequestBody ListDriverCommand command, HttpSession session) {
        ApiPagination<Driver> pagination = null;
        BaseUser baseUser = (BaseUser) session.getAttribute(Constants.SESSION_USER);
        if (null == baseUser) {
            return null;
        }
        command.setCompany(baseUser.getId());
        try {
            pagination = driverService.pagination(command);
        } catch (ApiRemoteCallFailedException e) {
            logger.warn(e.getMessage());
            pagination = new ApiPagination<Driver>();
        }
        return pagination;
    }
}
