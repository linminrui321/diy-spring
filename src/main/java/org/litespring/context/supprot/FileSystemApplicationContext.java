package org.litespring.context.supprot;

import org.litespring.core.io.FileSystemResource;
import org.litespring.core.io.Resource;

public class FileSystemApplicationContext extends AbstractApplicationContext {

    public FileSystemApplicationContext(String configFile) {
        super(configFile);
    }

    protected Resource getResourceByPath(String path) {
      return new FileSystemResource(path);
    }

}
