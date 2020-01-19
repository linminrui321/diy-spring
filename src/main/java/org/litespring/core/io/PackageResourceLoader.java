package org.litespring.core.io;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.litespring.util.Assert;
import org.litespring.util.ClassUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class PackageResourceLoader {
    private static Log logger = LogFactory.getLog(PackageResourceLoader.class);
    private ClassLoader classLoader;

    public PackageResourceLoader(ClassLoader classLoader) {
        Assert.notNull(classLoader, "ResourceLoader must be not null");
        this.classLoader = classLoader;
    }

    public PackageResourceLoader() {
        this.classLoader = ClassUtils.getDefaultClassLoader();
    }


    public Resource[] getResources(String basePackage) throws IOException{
        Assert.notNull(basePackage, "basePackage must not be null");
        String location = ClassUtils.convertClassNameToResourcePath(basePackage);
        ClassLoader cl =  getClassLoader();
        URL url = cl.getResource(location);
        File rootDir = new File(url.getFile());
        Set<File> matchingFiles  =  retrieceMatchingFiles(rootDir);
        Resource[] result = new Resource[matchingFiles.size()];
        int i = 0;
        for (File file : matchingFiles) {
            result[i++] = new FileSystemResource(file);
        }
        return result;
    }

    protected Set<File> retrieceMatchingFiles(File rootDir) throws IOException{
        if (!rootDir.exists()) {
            if (logger.isDebugEnabled()) {
                logger.debug("Skipping [" + rootDir.getAbsolutePath() + "] because it does not exist");
            }
            return Collections.emptySet();
        }
        //判断rootDir是不是一个目录
        if (!rootDir.isDirectory()) {
            // Complain louder if it exists but is no directory.
            if (logger.isWarnEnabled()) {
                logger.warn("Skipping [" + rootDir.getAbsolutePath() + "] because it does not denote a directory");
            }
            return Collections.emptySet();
        }
        //判断rootDir是否可读
        if (!rootDir.canRead()) {
            if (logger.isWarnEnabled()) {
                logger.warn("Cannot search for matching files underneath directory [" + rootDir.getAbsolutePath() +
                        "] because the application is not allowed to read the directory");
            }
            return Collections.emptySet();
        }
		/*String fullPattern = StringUtils.replace(rootDir.getAbsolutePath(), File.separator, "/");
		if (!pattern.startsWith("/")) {
			fullPattern += "/";
		}
		fullPattern = fullPattern + StringUtils.replace(pattern, File.separator, "/");
		*/
        //result用来存放找到的文件
        Set<File> result = new LinkedHashSet<File>(8);
        //调用doRetrieveMatchingFiles查找rootDir目录下的文件，并将文件存放在result中
        doRetrieveMatchingFiles(rootDir, result);
        return result;
    }

    protected void doRetrieveMatchingFiles(File dir, Set<File> result) {
        File[] dirContents = dir.listFiles();
        if (dirContents == null) {
            if (logger.isWarnEnabled()) {
                logger.warn("Could not retrieve contents of directory [" + dir.getAbsolutePath() + "]");
            }
            return;
        }
        for (File content : dirContents) {
            if (content.isDirectory()) {
                if (!content.canRead()) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("Skipping subdirectory [" + dir.getAbsolutePath() +
                                "] because the application is not allowed to read the directory");
                    }
                }else {
                    //在content目录下继续递归
                    doRetrieveMatchingFiles(content, result);
                }
            } else{
                //content是一个文件，直接将文件放入result中
                result.add(content);
            }
        }

    }

    public ClassLoader getClassLoader() {
        return this.classLoader;
    }


}
