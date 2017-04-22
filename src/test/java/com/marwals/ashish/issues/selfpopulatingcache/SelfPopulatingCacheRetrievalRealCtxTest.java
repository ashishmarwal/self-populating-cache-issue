package com.marwals.ashish.issues.selfpopulatingcache;

import net.sf.ehcache.Ehcache;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertNotNull;

public class SelfPopulatingCacheRetrievalRealCtxTest {

    ApplicationContext applicationContext;

    Ehcache alphabetDescriptionCache;

    @Before
    public void setUp() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    }

    @Test
    public void testRetrieval() throws Exception {
        assertNotNull(applicationContext);

        alphabetDescriptionCache = (Ehcache) applicationContext.getBean("alphabetDescriptionCache");
        assertNotNull(alphabetDescriptionCache);

    }
}