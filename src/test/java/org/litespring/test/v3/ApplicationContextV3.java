package org.litespring.test.v3;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.context.ApplicationContext;
import org.litespring.context.supprot.ClassPathXmlApplicationContext;
import org.litespring.service.v3.PetStoreService;

public class ApplicationContextV3 {

    @Test
    public void testGetBeanProperty(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("petstore-v3.xml");
        PetStoreService petService = (PetStoreService) ctx.getBean("petStore");
        Assert.assertNotNull(petService.getAccountDao());
        Assert.assertNotNull(petService.getItemDao());
        Assert.assertEquals(1,petService.getVersion());
    }


}
