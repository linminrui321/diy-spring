package org.litespring.test.v3;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.litespring.test.v1.V1AllTest;
import org.litespring.test.v2.V2AllTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ApplicationContextV3.class, ApplicationContextV3.class,ConstructorResolvedTest.class
      })
public class V3AllTest {
}
