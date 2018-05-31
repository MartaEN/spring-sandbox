package com.marta.sandbox.spring.kitchen.chef;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class OutOfStockReplacementBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		
		//получаем имена всех BeanDefinition, чтобы получить доступ к каждому из них
		String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
		
		//перебираем все имена
		for(String name: beanDefinitionNames){
			
	 		//получаем BeanDefinition по имени
			BeanDefinition beanDefinition = beanFactory.getBeanDefinition(name);
			
			/*получаем имя класса создавамого бина, чтобы проверить , 
			 * содержит ли он аннотацию OutOfStock
			 */ 
			String className = beanDefinition.getBeanClassName();
			
			try {
				//получаем класс по имени
				Class<?> beanClass = Class.forName(className);
				
				/*пытаемся получить объект аннотации и ее значение,
				 * если  класс не содержит данную аннотацию, то  метод вернет null
				 */
				OutOfStock annotation = beanClass.getAnnotation(OutOfStock.class);
				
				//проверяем, содержал ли класс эту аннотацию
				if(annotation!=null){
					
					 //получаем значение указанное в параметрах аннотации(класс пленки, которую необходимо использовать)
					 Class usingCameraRollName =annotation.replaceWithClass();
					 
					 //меняем класс будущего бина
				     beanDefinition.setBeanClassName(usingCameraRollName.getName());	
				}
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}
	
}
