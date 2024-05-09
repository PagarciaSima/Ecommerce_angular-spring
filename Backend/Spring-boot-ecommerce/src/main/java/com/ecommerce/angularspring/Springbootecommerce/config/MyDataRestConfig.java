package com.ecommerce.angularspring.Springbootecommerce.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.ecommerce.angularspring.Springbootecommerce.entity.Product;
import com.ecommerce.angularspring.Springbootecommerce.entity.ProductCategory;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer{

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
		HttpMethod[] theUnsupportedActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE};
		
		// Disable http methods for product PUT, POST AND DELETE.
		config.getExposureConfiguration()
			.forDomainType(Product.class)
			.withItemExposure((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
			.withCollectionExposure((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
		
		// Disable http methods for ProductCategory PUT, POST AND DELETE.
				config.getExposureConfiguration()
					.forDomainType(ProductCategory.class)
					.withItemExposure((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
					.withCollectionExposure((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
	}
	
	
}
