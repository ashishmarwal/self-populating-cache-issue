package com.marwals.ashish.issues.selfpopulatingcache;

import net.sf.ehcache.constructs.blocking.CacheEntryFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * All we want is a decorated (SelfPopulating) cache, so data here is not important.
 */
public class AlphabetDescriptionCacheEntryFactory implements CacheEntryFactory {

    /* This is our database :) */
    private static final Map<String, String> DATA = new HashMap<>();


    static {
        DATA.put("a", "The letter A");
    }


    @Override
    public Object createEntry(final Object key) throws Exception {
        String description = null;

        if (key != null && key instanceof String) {
            final String cacheKey = (String) key;
            description = DATA.get(cacheKey);
        } else {
            throw new IllegalArgumentException("Invalid key! " + key);
        }

        return description;
    }

}