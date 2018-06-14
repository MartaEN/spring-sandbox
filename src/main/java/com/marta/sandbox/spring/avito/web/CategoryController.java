package com.marta.sandbox.spring.avito.web;

import java.util.List;

import com.marta.sandbox.spring.avito.domain.Advertisement;
import com.marta.sandbox.spring.avito.domain.Category;
import com.marta.sandbox.spring.avito.service.AdvertisementService;
import com.marta.sandbox.spring.avito.service.CategoryService;
import com.marta.sandbox.spring.avito.web.ajax.AdvertisementsAjax;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;

@Controller
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private AdvertisementService advertisementService;
	
	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public String view(@PathVariable("id") Long id,Model uiModel){
		
		Category category = categoryService.get(id);
		uiModel.addAttribute("category", category);
		List<Category> categories = categoryService.getAll();
		uiModel.addAttribute("categories",categories);
		return "category/view";
		
	}

	@RequestMapping(value="/{id}/advertisements_ajax",method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public AdvertisementsAjax viewAjax(@PathVariable("id") Long id, @RequestParam("pageCounter") Integer pageCounter, @RequestParam("number") Integer number, @RequestParam("order") String order, @RequestParam("orderBy") String orderBy){

		Sort sort = null;
		if(order.equalsIgnoreCase("DESC")){
			sort = new Sort(Sort.Direction.DESC, orderBy);
		}else{
			sort = new Sort(Sort.Direction.ASC, orderBy);
		}

		PageRequest pageable = new PageRequest(pageCounter, number, sort);
		Page <Advertisement> advertisementPage = advertisementService.getByCategoryId(id, pageable);
		AdvertisementsAjax responsive = new AdvertisementsAjax();
		responsive.setAdvertisements(Lists.newArrayList(advertisementPage.iterator()));

		return responsive;

	}

}
