package com.github.btheu.settesting;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.github.btheu.settesting.core.impl.PrimitiveValueResult;
import com.github.btheu.settesting.core.impl.VoidResult;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ResultComparatorTest extends SetTestingCase {

    public static Domain domain = new Domain();
    
    @Test 
    public void test2() {
        this.usecases.add(ValidateUseCase.class);
        this.bos.add(NewObject1.class);
        this.bos.add(NewObject1.class);
        this.bos.add(ValidObject1.class);
        this.bos.add(ValidObject1.class);
    }
    
    static class SimpleUseCase2 implements UseCase {
        
        public Result execute(BusinessObject...businessObjects) {
            return new PrimitiveValueResult(1);
        }
        
    }
    
    static abstract class EntityBO implements BusinessObject {
        
        public abstract Long getId();
        
    }
    
    // Test Classes
    
    static class ValidateUseCase implements UseCase {

        public Result execute(BusinessObject...businessObjects) {
            
            Assert.assertEquals(1, businessObjects.length);
            
            BusinessObject businessObject = businessObjects[0];
            if(businessObject instanceof EntityBO){
                EntityBO entity = (EntityBO) businessObject;
                
                Entity find = domain.find(entity.getId());
                
                domain.validate(find);
             
                find = domain.find(entity.getId());
                
                Assert.assertEquals("validate", find.getStatus());
                
            }
            
            return new VoidResult();
        }
        
    }
    

    
    static class NewObject1 extends EntityBO {

        @Override
        public Long getId() {
            return 1L;
        }
        
        public void create() {
            
            Entity entity = new Entity(1L,"Entité une");
            
            domain.entities.put(entity.getId(), entity);
        }

        public void remove() {
            
        }

        
    }
    
    static class ValidObject1 extends EntityBO {
        
        @Override
        public Long getId() {
            return 2L;
        }
        
        public void create() {
            
            Entity entity = new Entity(2L,"Entité deux");
            
            entity.setStatus("validate");
            
            domain.entities.put(entity.getId(), entity);
        }
        
        public void remove() {
            
        }

        
    }
    
    
    
    // Domain Classes
    
    @Slf4j
    static class Domain {

        Map<Long, Entity> entities  = new HashMap<Long, Entity>();

        public Entity find(Long id) {
            return entities.get(id);
        }

        public void validate(Entity find) {
            log.debug("validating...");
            if(find.getStatus().equals("validate")){
                throw new RuntimeException("BO already validated");
            }
            
            entities.get(find.getId()).setStatus("validate");
        }
        
    }
    
    @Data
    static class Entity {
        
        public Entity(long l, String string) {
            id = l;
            name = string;
        }

        long id;
        
        String name;
        
        String description;
        
        String status = "new";
    }
}
