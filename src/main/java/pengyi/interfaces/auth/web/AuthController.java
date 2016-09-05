package pengyi.interfaces.auth.web;

import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.ImageCaptchaService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pengyi.core.commons.Constants;
import pengyi.core.exception.ApiRemoteCallFailedException;
import pengyi.interfaces.shared.web.AlertMessage;
import pengyi.interfaces.shared.web.BaseController;
import pengyi.model.user.BaseUser;
import pengyi.service.company.command.LoginUserCommand;
import pengyi.service.company.command.RegisterCompanyCommand;
import pengyi.service.company.ICompanyService;
import pengyi.service.company.command.ResetPasswordCommand;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.Locale;

/**
 * Created by YJH on 2016/3/8.
 */
@Controller
public class AuthController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private ImageCaptchaService imageCaptchaService;

    @Autowired
    private ICompanyService companyService;

    @RequestMapping(value = "/verificationCode", headers = "Accept=image/jpeg, image/jpg, image/png, image/gif")
    @ResponseBody
    public byte[] verificationCode(HttpServletRequest request) throws Exception {
        String captchaId = request.getRequestedSessionId();
        BufferedImage bufferedImage = (BufferedImage) imageCaptchaService.getChallengeForID(captchaId, request.getLocale());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(LoginUserCommand command) {
        return new ModelAndView("/login", "user", command);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@Valid @ModelAttribute("user") LoginUserCommand command, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes, HttpServletRequest request, HttpSession session, Locale locale) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("/login", "user", command);
        }

        AlertMessage alertMessage;

        if (command.getVerificationCode().isEmpty()) {
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("login.verificationCode.NotEmpty.message", null, locale));
            return new ModelAndView("/login", AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage)
                    .addObject("user", command);
        }

        boolean flag = false;
        try {
            flag = imageCaptchaService.validateResponseForID(request.getRequestedSessionId(),
                    command.getVerificationCode());
        } catch (CaptchaServiceException e) {
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("login.verificationCode.Error.message", null, locale));
            return new ModelAndView("/login", "user", command).addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }

        if (flag) {

            try {
                BaseUser user = companyService.login(command);
                Subject subject = SecurityUtils.getSubject();
                if (subject.hasRole("company")) {
                    logger.info("公司用户:" + subject.getPrincipal() + "登录成功！时间:" + new Date());
                    session.setAttribute(Constants.SESSION_USER, user);
                    return new ModelAndView("redirect:/logined");
                } else {
                    subject.logout();
                    alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING,
                            this.getMessage("login.NoPermission.Error.message", null, locale));
                    redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
                    return new ModelAndView("redirect:/");
                }
            } catch (UnknownAccountException e) {
                alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                        this.getMessage("login.account.NotExists.message", null, locale));
                redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
                return new ModelAndView("redirect:/");
            } catch (IncorrectCredentialsException e) {
                alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                        this.getMessage("login.account.Error.message", null, locale));
                redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
                return new ModelAndView("redirect:/");
            } catch (LockedAccountException e) {
                alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING,
                        this.getMessage("login.account.Disable.message", null, locale));
                redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
                return new ModelAndView("redirect:/");
            } catch (Exception e) {
                alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                        this.getMessage("login.login.Failure.message", null, locale));
                redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
                return new ModelAndView("redirect:/");
            }
        } else {
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("login.verificationCode.Error.message", null, locale));
            return new ModelAndView("/login", "user", command).addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
    }

    @RequestMapping("/logout")
    public ModelAndView logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        if (null == subject.getPrincipal()) {
            return new ModelAndView("redirect:/");
        } else {
            try {
                String username = subject.getPrincipal().toString();
                logger.info("用户:" + username + "登出成功！时间:" + new Date());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                return new ModelAndView("redirect:/");
            }
        }
    }

    @RequestMapping("/unauthorized")
    public ModelAndView unauthorized() {
        return new ModelAndView("/unauthorized");
    }


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register(@ModelAttribute("command") RegisterCompanyCommand command) {
        return new ModelAndView("/register", "command", command);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(@Valid @ModelAttribute("command") RegisterCompanyCommand command,
                                 BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                 Locale locale) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/register", "command", command);
        }

        AlertMessage alertMessage;

        try {
            companyService.register(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            return new ModelAndView("/register", "command", command)
                    .addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }

        alertMessage = new AlertMessage(this.getMessage("company.register.success", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        return new ModelAndView("/registersuccess");
    }

    @RequestMapping(value = "/reset_password", method = RequestMethod.GET)
    public ModelAndView resetPassword(@ModelAttribute("command") ResetPasswordCommand command) {
        return new ModelAndView("/resetpassword", "command", command);
    }

    @RequestMapping(value = "/reset_password", method = RequestMethod.POST)
    public ModelAndView resetPassword(@Valid @ModelAttribute("command") ResetPasswordCommand command,
                                      BindingResult bindingResult, RedirectAttributes redirectAttributes, Locale locale) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/resetpassword", "command", command);
        }

        AlertMessage alertMessage;
        try {
            companyService.resetPassword(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            return new ModelAndView("/resetpassword", "command", command)
                    .addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        alertMessage = new AlertMessage(this.getMessage("company.password.reset.message", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        return new ModelAndView("redirect:/login");
    }
}
