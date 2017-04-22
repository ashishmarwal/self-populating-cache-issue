# The issue
When a test context and a real context are initialized in seperate tests, somehow one of those ends up running into the incumbent cache issue as seen in the logs below.

# Build Log
```
marwala1-mbpdu:self-populating-cache-issue marwala$ mvn clean install
[INFO] Scanning for projects...
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] Building self-populating-cache-issue Maven app 1.0-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO]
[INFO] --- maven-clean-plugin:2.4.1:clean (default-clean) @ self-populating-cache-issue-demo ---
[INFO]
[INFO] --- maven-resources-plugin:2.5:resources (default-resources) @ self-populating-cache-issue-demo ---
[debug] execute contextualize
[WARNING] Using platform encoding (UTF-8 actually) to copy filtered resources, i.e. build is platform dependent!
[INFO] Copying 2 resources
[INFO]
[INFO] --- maven-compiler-plugin:3.3:compile (default-compile) @ self-populating-cache-issue-demo ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 1 source file to /Volumes/SourceCode/GitHub/self-populating-cache-issue/target/classes
[INFO]
[INFO] --- maven-resources-plugin:2.5:testResources (default-testResources) @ self-populating-cache-issue-demo ---
[debug] execute contextualize
[WARNING] Using platform encoding (UTF-8 actually) to copy filtered resources, i.e. build is platform dependent!
[INFO] Copying 1 resource
[INFO]
[INFO] --- maven-compiler-plugin:3.3:testCompile (default-testCompile) @ self-populating-cache-issue-demo ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 2 source files to /Volumes/SourceCode/GitHub/self-populating-cache-issue/target/test-classes
[INFO]
[INFO] --- maven-surefire-plugin:2.10:test (default-test) @ self-populating-cache-issue-demo ---
[INFO] Surefire report directory: /Volumes/SourceCode/GitHub/self-populating-cache-issue/target/surefire-reports

-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running com.marwals.ashish.issues.selfpopulatingcache.SelfPopulatingCacheRetrievalRealCtxTest
Apr 21, 2017 11:31:00 PM org.springframework.context.support.ClassPathXmlApplicationContext prepareRefresh
INFO: Refreshing org.springframework.context.support.ClassPathXmlApplicationContext@233c0b17: startup date [Fri Apr 21 23:31:00 EDT 2017]; root of context hierarchy
Apr 21, 2017 11:31:00 PM org.springframework.beans.factory.xml.XmlBeanDefinitionReader loadBeanDefinitions
INFO: Loading XML bean definitions from class path resource [applicationContext.xml]
Apr 21, 2017 11:31:00 PM org.springframework.cache.ehcache.EhCacheManagerFactoryBean afterPropertiesSet
INFO: Initializing EhCache CacheManager 'self-populating-cache-issue-demo'
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.808 sec
Running com.marwals.ashish.issues.selfpopulatingcache.SelfPopulatingCacheRetrievalTestCtxTest
Apr 21, 2017 11:31:01 PM org.springframework.context.support.ClassPathXmlApplicationContext prepareRefresh
INFO: Refreshing org.springframework.context.support.ClassPathXmlApplicationContext@4f83df68: startup date [Fri Apr 21 23:31:01 EDT 2017]; root of context hierarchy
Apr 21, 2017 11:31:01 PM org.springframework.beans.factory.xml.XmlBeanDefinitionReader loadBeanDefinitions
INFO: Loading XML bean definitions from class path resource [test-context.xml]
Apr 21, 2017 11:31:01 PM org.springframework.cache.ehcache.EhCacheManagerFactoryBean afterPropertiesSet
INFO: Initializing EhCache CacheManager 'self-populating-cache-issue-demo-test'
Apr 21, 2017 11:31:01 PM org.springframework.context.support.ClassPathXmlApplicationContext refresh
WARNING: Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'alphabetDescriptionCache' defined in class path resource [test-context.xml]: Invocation of init method failed; nested exception is net.sf.ehcache.CacheException: Cannot replace alphabet-description-cache It does not equal the incumbent cache.
Apr 21, 2017 11:31:01 PM org.springframework.cache.ehcache.EhCacheManagerFactoryBean destroy
INFO: Shutting down EhCache CacheManager 'self-populating-cache-issue-demo-test'
Tests run: 1, Failures: 0, Errors: 1, Skipped: 0, Time elapsed: 0.074 sec <<< FAILURE!

Results :

Tests in error:
  testRetrieval(com.marwals.ashish.issues.selfpopulatingcache.SelfPopulatingCacheRetrievalTestCtxTest): Error creating bean with name 'alphabetDescriptionCache' defined in class path resource [test-context.xml]: Invocation of init method failed; nested exception is net.sf.ehcache.CacheException: Cannot replace alphabet-description-cache It does not equal the incumbent cache.

Tests run: 2, Failures: 0, Errors: 1, Skipped: 0

[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 3.383s
[INFO] Finished at: Fri Apr 21 23:31:01 EDT 2017
[INFO] Final Memory: 17M/491M
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-surefire-plugin:2.10:test (default-test) on project self-populating-cache-issue-demo: There are test failures.
[ERROR]
[ERROR] Please refer to /Volumes/SourceCode/GitHub/self-populating-cache-issue/target/surefire-reports for the individual test results.
[ERROR] -> [Help 1]
[ERROR]
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR]
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException
```