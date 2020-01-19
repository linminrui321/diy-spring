package org.litespring.test.v4;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.core.io.PackageResourceLoader;
import org.litespring.core.io.Resource;

import java.io.IOException;

public class PackageResorceLoaderTest {

    @Test
    public void testGetResoucrce() throws IOException {
        PackageResourceLoader loader = new PackageResourceLoader();
        Resource[] resources = loader.getResources("org.litespring.dao.v4");
        Assert.assertEquals(2, resources.length);

    }
}
