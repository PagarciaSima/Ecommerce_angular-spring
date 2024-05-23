package com.ecommerce.angularspring.Springbootecommerce.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.ecommerce.angularspring.Springbootecommerce.entity.Country;
import com.ecommerce.angularspring.Springbootecommerce.entity.Product;
import com.ecommerce.angularspring.Springbootecommerce.entity.ProductCategory;
import com.ecommerce.angularspring.Springbootecommerce.entity.State;

import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer{
	
	private EntityManager entityManager;
	
	public MyDataRestConfig( EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
		HttpMethod[] theUnsupportedActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE};
		
		
		
		// Disable http methods for product PUT, POST AND DELETE.
		disableHttpMethods(Product.class, config, theUnsupportedActions);
		
		// Disable http methods for ProductCategory PUT, POST AND DELETE.
		disableHttpMethods(ProductCategory.class, config, theUnsupportedActions);
		
		// Disable http methods for Country PUT, POST AND DELETE.
		disableHttpMethods(Country.class, config, theUnsupportedActions);
		
		// Disable http methods for State PUT, POST AND DELETE.
		disableHttpMethods(State.class, config, theUnsupportedActions);
		
		// Call internal helper method to expose ids
		exposeIds(config);
	}

	private void disableHttpMethods(Class theClass, RepositoryRestConfiguration config, HttpMethod[] theUnsupportedActions) {
		config.getExposureConfiguration()
			.forDomainType(theClass)
			.withItemExposure((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
			.withCollectionExposure((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
	}

	private void exposeIds(RepositoryRestConfiguration config) {
		// Get a list of all entity classes from entity manager
		Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
		
		// Create an array of entity types
		List<Class> entityClasses = new ArrayList<>();
		
		// Get entity types for the entities
		for (EntityType tempEntityType: entities) {
			entityClasses.add(tempEntityType.getJavaType());
		}
		
		// Expose entity Ids for array entity / domain types
		Class [] domainTypes = entityClasses.toArray(new Class [0]);
		config.exposeIdsFor(domainTypes);
		
	}
	
	
}
