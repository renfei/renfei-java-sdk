package net.renfei.sdk.test;

import org.junit.jupiter.api.*;

/**
 * @author RenFei
 */
public class TestApplication {
    @BeforeAll
    static void initAll() {
        System.out.println("==== Start testing ======");
    }

    @BeforeEach
    void init() {
        System.out.println("==== Start ====");
    }

    @AfterEach
    void tearDown() {
        System.out.println("==== End ====");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("==== End testing ======");
    }
}
