package io.jenkins.plugins.conduit;

import io.jenkins.plugins.conduit.categories.Integration;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(Integration.class)
public class HelloWorldBuilderIT {
    @Test
    public void alwaysPassIntegrationTest() {
        assert true;
    }
}
