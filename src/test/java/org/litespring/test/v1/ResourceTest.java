package org.litespring.test.v1;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.FileSystemResource;
import org.litespring.core.io.Resource;

import java.io.InputStream;

public class ResourceTest {
    @Test
    public void testClassPathResource() throws Exception{
        Resource resource =  new ClassPathResource("petstore-v1.xml");
        InputStream is = null;
        try{
            is = resource.getInputStream();
            Assert.assertNotNull(is);
        }finally {
            if(null != is){
                is.close();
            }
        }
    }

    @Test
    public void testFileSystemResource() throws Exception{
        Resource resource =  new FileSystemResource("D:\\diy-spring\\code\\diy-spring\\src\\test\\resource\\petstore-v1.xml");
        InputStream is = null;
        try{
            is = resource.getInputStream();
            Assert.assertNotNull(is);
        }finally {
            if(null != is){
                is.close();
            }
        }
    }
}
