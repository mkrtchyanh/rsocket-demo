package io.hayk.demo;

import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.junit.runner.RunWith;

import java.util.UUID;

@RunWith(EasyMockRunner.class)
public  abstract class AbstractServiceUnitTest extends EasyMockSupport {

    public static String uuid(){
        return UUID.randomUUID().toString();
    }
}
