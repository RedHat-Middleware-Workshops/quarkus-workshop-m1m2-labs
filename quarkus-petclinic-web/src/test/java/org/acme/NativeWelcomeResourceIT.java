package org.acme;

import io.quarkus.test.junit.NativeImageTest;

@NativeImageTest
public class NativeWelcomeResourceIT extends WelcomeResourceTest {

    // Execute the same tests but in native mode.
}