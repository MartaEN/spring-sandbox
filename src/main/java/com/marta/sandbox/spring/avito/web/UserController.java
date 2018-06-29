package com.marta.sandbox.spring.avito.web;

import com.marta.sandbox.spring.avito.domain.User;
import com.marta.sandbox.spring.avito.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Locale;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageSource messageSource;
	
    //возвращает форму регистрации 
	@RequestMapping(value="/registration", method=RequestMethod.GET)
	public String registrationForm(Model uiModel){
		
		User user = new User();
	    uiModel.addAttribute("author", user);
		return "security/registration";
	}
	
	//принимает данные формы
	@RequestMapping(value="/registration", method = RequestMethod.POST)
	public String registration(Model uiModel,
							   @ModelAttribute("author") @Valid User user,
							   BindingResult bindingResult,
							   RedirectAttributes redAttributes,
							   Locale locale){
		
		if(bindingResult.hasErrors()){
			
			uiModel.addAttribute("message",
					messageSource.getMessage("user_create_fail", new Object[]{}, locale));
			return "security/registration";
			
		}
		if(userService.getByLogin(user.getLogin())!=null){
			
		    uiModel.addAttribute("message",
					messageSource.getMessage("user_login_exist", new Object[]{}, locale));
			return "security/registration";
			
		}
		userService.save(user);
		return "redirect:/";
	}

}
