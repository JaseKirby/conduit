package io.jenkins.plugins.conduit;

import io.jenkins.plugins.conduit.categories.Integration;
import io.jenkins.plugins.conduit.categories.Unit;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(Unit.class)
public class HelloWorldBuilderUT {
    @Test
    public void alwaysPassUnitTest() {
        assert true;
    }
}
