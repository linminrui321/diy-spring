package org.litespring.test.v1;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.context.ApplicationContext;
import org.litespring.context.supprot.ClassPathXmlApplicationContext;
import org.litespring.context.supprot.FileSystemApplicationContext;
import org.litespring.service.v1.PetStoreService;

public class ApplicationContextTest {
    @Test
    public void ClassPathGetBean(){
        ApplicationContext ctx =  new ClassPathXmlApplicationContext("petstore-v1.xml");
        PetStoreService petStore = (PetStoreService) ctx.getBean("petStore");
        Assert.assertNotNull(petStore);
    }


    @Test
    public void FileSystemGetBean(){
        ApplicationContext ctx =  new FileSystemApplicationContext("src\\test\\resource\\petstore-v1.xml");
        PetStoreService petStore = (PetStoreService) ctx.getBean("petStore");
        Assert.assertNotNull(petStore);
    }

}
