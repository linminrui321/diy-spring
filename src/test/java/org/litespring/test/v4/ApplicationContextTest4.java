package org.litespring.test.v4;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.context.ApplicationContext;
import org.litespring.context.supprot.ClassPathXmlApplicationContext;
import org.litespring.service.v4.PetStoreService;

public class ApplicationContextTest4 {
    @Test
    public void testGetBeanProperty(){
        ApplicationContext ctx =  new ClassPathXmlApplicationContext("petStore-v4.xml");
        PetStoreService petStore =  (PetStoreService)ctx.getBean("petStore");
        Assert.assertNotNull(petStore.getAccountDao());
        Assert.assertNotNull(petStore.getItemDao());
    }
}
