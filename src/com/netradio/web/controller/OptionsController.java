package com.netradio.web.controller;

import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.netradio.entity.Option;
import com.netradio.service.OptionService;
import com.netradio.task.AudioThreadsChekerTask;

@Controller
public class OptionsController {

    @Autowired
    private OptionService service;

    @Autowired
    private AudioThreadsChekerTask task;

    @Autowired
    @Qualifier("validator")
    private OptionValidator validator;

    @RequestMapping("/options")
    public String options(final Model model) {
        List<Option> options = service.getOptions();
        model.addAttribute("options", options);
        return "options/options";
    }

    @RequestMapping("/option")
    public String option(
            @RequestParam(value = "key", required = true) final String key,
            final Model model) {
        model.addAttribute("option", service.getOption(key));
        return "options/option";
    }

    @RequestMapping(value = "/saveOption", method = RequestMethod.POST)
    public String save(@ModelAttribute("option") final Option option,
            final BindingResult result, final Model model) {

        if (option.getKey() != null) {
            validator.validate(option, result);

            if (result.hasErrors()) {
                model.addAttribute("option", option);
                return "options/option";
            }

            service.saveOption(option);

            if (option.getKey().equals("crontab")) {
                task.replaceCronTrigger();
            }
        }

        return "redirect:/options.html";
    }

    @Component("validator")
    public static class OptionValidator implements Validator {

        @Autowired
        private OptionService srv;

        @Override
        public boolean supports(final Class<?> clazz) {
            return Option.class.equals(clazz);
        }

        @Override
        public void validate(final Object obj, final Errors errors) {

            Option option = (Option) obj;

            if (srv.getTypeByKey(option.getKey()).getName().equals("crontab")) {
                try {
                    new CronTrigger(option.getValue());
                } catch (IllegalArgumentException e) {
                    errors.rejectValue("value", "cron.error");
                }
            }

            if (srv.getTypeByKey(option.getKey()).getName().equals("regexp")) {
                try {
                    Pattern.compile(option.getValue());
                } catch (PatternSyntaxException e) {
                    errors.rejectValue("value", "regexp.error");
                }
            }

            if (srv.getTypeByKey(option.getKey()).getName().equals("count")) {
                try {
                    if (Integer.parseInt(option.getValue()) < 1) {
                        errors.rejectValue("value", "count.errorNegative");
                    }
                } catch (NumberFormatException e) {
                    errors.rejectValue("value", "count.error");
                }
            }
        }
    }
}
