package br.com.pedido.util;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.stereotype.Component;

@Component
public class CustomizationBeanUtils extends BeanUtilsBean {
	
	@Override
	public void copyProperty(Object bean, String name, Object value) throws IllegalAccessException, InvocationTargetException {
		// Só copia se o valor for diferente de nulo
		if (value == null)
			return;
		super.copyProperty(bean, name, value);
	}
}