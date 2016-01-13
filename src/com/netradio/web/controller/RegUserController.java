package com.netradio.web.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.netradio.entity.User;
import com.netradio.service.UserService;

@Controller
@RequestMapping("/reguser")
public class RegUserController {

    @Autowired
    @Qualifier("uValidator")
    private Validator uValidator;

    @Autowired
    private UserService srv;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String user(final Model model) {
        model.addAttribute("user", new User());
        return "reguser";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("user") final User user, final BindingResult result) {
        uValidator.validate(user, result);

        if (result.hasErrors()) {
            return "reguser";
        } else {
            user.setIdCreator(new Long(0));
            Long[] role  = {2L};
            srv.saveUser(user, role);
            return "redirect:/login.html";
        }
    }

    @Component("uValidator")
    public static class uValidator implements Validator {

        @Autowired
        private UserService srv;

        @Autowired
        private Configuration conf;

        @Override
        public boolean supports(final Class<?> clazz) {
            return User.class.equals(clazz);
        }

        @Override
        public void validate(final Object obj, final Errors errors) {

            User user = (User) obj;
            Pattern pattern;
            Matcher matcher;

            if (user.getId() == null) {
                if (srv.getUser(user.getName()) != null) {
                    errors.rejectValue("name", "login.exists");
                }
            } else {
                if (!(srv.getUser(user.getId()).getName()
                        .equals(user.getName()))
                        && srv.getUser(user.getName()) != null) {
                    errors.rejectValue("name", "login.exists");
                }
            }

            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "login.empty");

            if (user.getId() == null) {
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwd", "passwd.empty");

                pattern = Pattern.compile(conf.getString("userPasswdPattern"));
                matcher = pattern.matcher(user.getPasswd());
                if (!matcher.matches()) {
                    errors.rejectValue("passwd", "passwd.error");
                }
            }

            pattern = Pattern.compile(conf.getString("userNamePattern"));
            matcher = pattern.matcher(user.getName());
            if (!matcher.matches()) {
                errors.rejectValue("name", "login.invalid");
            }
        }
    }
}
