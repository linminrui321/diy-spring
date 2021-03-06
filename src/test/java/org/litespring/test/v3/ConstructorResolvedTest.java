package org.litespring.test.v3;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.support.ConstructorResolver;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.core.io.ClassPathResource;
import org.litespring.service.v3.PetStoreService;

public class ConstructorResolvedTest {
    @Test
    public void test(){
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        ClassPathResource resource = new ClassPathResource("petstore-v3.xml");
        reader.loadBeanDefinitions(resource);
        BeanDefinition bd = factory.getBeanDefinition("petStore");
        ConstructorResolver resolver =  new ConstructorResolver(factory);
        PetStoreService petStore = (PetStoreService) resolver.autowireConstructor(bd);
        Assert.assertEquals(1, petStore.getVersion());
        Assert.assertNotNull(petStore.getAccountDao());
        Assert.assertNotNull(petStore.getItemDao());
    }
}
