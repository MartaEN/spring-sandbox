package com.marta.sandbox.spring.avito.web;

import com.google.common.collect.Lists;
import com.marta.sandbox.spring.avito.domain.*;
import com.marta.sandbox.spring.avito.service.AdvertisementService;
import com.marta.sandbox.spring.avito.service.CategoryService;
import com.marta.sandbox.spring.avito.service.UserService;
import com.marta.sandbox.spring.avito.web.ajax.AdvertisementsAjax;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/advertisements")
public class AdvertisementController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AdvertisementService advertisementService;
	
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private MessageSource messageSource;
	
	/**
	 * Метод перенаправляет клиента с адреса https://localhost:8080/avito/advertisements
	 * на https://localhost:8080/avito (необходим для следования стилю REST)
	 */
	@RequestMapping(method=RequestMethod.GET)
	public String list(){
		
		 return "redirect:/";
		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public String view(@PathVariable("id") Long id,Model uiModel){
		
		Advertisement advertisement = advertisementService.get(id);
		uiModel.addAttribute("advertisement", advertisement);
		return "advertisement/view";
		
	}
	
	@RequestMapping(value="/add", method= RequestMethod.GET)
	public String addForm(Model uiModel){
		//создание пустого объекта
		Advertisement advertisement = new Advertisement();
		advertisement.setCompany(new Company());
		//получение списка всех категорий для возможности выбора категории, к которому будет принадлежать новое объявление
		List<Category> categories = categoryService.getAll();
		//связывание объекта объявления с формой и добавление списка категорий на страницу
		uiModel.addAttribute("advertisement", advertisement).addAttribute("categories", categories);
		return "advertisement/add";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String add(Model uiModel,
					  @ModelAttribute("advertisement") @Valid Advertisement advertisement,
					  BindingResult bindingResult,
					  @RequestParam("categoryId") Long categoryId,
					  Locale locale,
					  RedirectAttributes redirectAttributes){

		// Проверяем форму на наличие ошибок
		if(bindingResult.hasErrors() || categoryId.equals(0L)){
			// Если ошибка найдена, то заново создаем объект article для формы
			uiModel.addAttribute( "advertisement", advertisement)
					// список категорий для выбора категорий в форме
					.addAttribute ( "categories", categoryService.getAll())
					// и добавляем сообщение о результате добавления статьи
					.addAttribute ( "message" , messageSource.getMessage ( "ad_create_fail" ,
							new Object []{}, locale ) );
			return "advertisement/add";
		}
		//Получаем логин пользователя, публикующего объявление
		String currentLogin = SecurityContextHolder.getContext().getAuthentication().getName();
		//По логину находим автора
		User user = userService.getByLogin(currentLogin);
		//Ищем категорию по id категории
		Category category = categoryService.get(categoryId);
		//Если валидация прошла успешно, то задаем категорию вновь созданной статьи
		advertisement.setCategory(category);
		//Устанавливаем автора
		advertisement.setUser(user);
		//сохраняем статью
		advertisementService.save(advertisement);
		//редиректим юзера на главную страницу, выводя сообщение об успехе добавления статьи
		redirectAttributes.addFlashAttribute("message", messageSource.getMessage("article_create_success", new Object[]{}, locale));
		return "redirect:/";
	}

	/**
	 * Метод обрабатывающий асинхронный запрос 
	 */
	@RequestMapping(value="/advertisements_ajax",method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	/**
	 * @param pageCounter-текущая страница(блок из number статей)
	 * @param number - количество статей в одном блоке
	 * @param order - порядок сортировки(ASC-прямая, DESC-обратная)
	 * @param orderBy - поле по которому происходит сортировка
	 * @return объект класса ArticlesAjax, который содержит список статей, 
	 * данный объект преобразовывается в JSON-формат
	 */
	public AdvertisementsAjax listAjax(@RequestParam("pageCounter") Integer pageCounter, @RequestParam("number") Integer number, @RequestParam("order") String order, @RequestParam("orderBy") String orderBy){
		
		//объект, который будет содержать информацию о сортировке
		Sort sort = null;
		
		if(order.equalsIgnoreCase("DESC")){
			//конструктор Sort принимает в качестве параметров тип сортировки и поле,
			//по которому будет происходить соритровка
			sort = new Sort(Sort.Direction.DESC, orderBy);
			
		}else{
			
			
			sort = new Sort(Sort.Direction.ASC, orderBy);
		}
		//конструктор принимает полную информацию о текущем блоке,количестве статей и сортировке
		PageRequest pageable = new PageRequest(pageCounter,number, sort);
		
		Page<Advertisement> advertisementPage = advertisementService.getAll(pageable);
		
		AdvertisementsAjax responsive = new  AdvertisementsAjax();
		//из объекта Page возвращаем итератор и с помощью библиотеки google guava создаем списочный массив
		responsive.setAdvertisements(Lists.newArrayList(advertisementPage.iterator()));
		
		return responsive;

	}
}
