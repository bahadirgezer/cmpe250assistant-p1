import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class FactoryImplTest {

    //@EnabledOnOS DisabledOnOS
    FactoryImpl factory;

    @BeforeEach
    public void setUp() {
        factory = new FactoryImpl();

    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    void main() {
    }

    @Test
    @DisplayName("Factory Initialization")
    public void initializeFactory() {
        FactoryImpl factory = new FactoryImpl();

        assertNull(factory.getFirst());
        assertNull(factory.getLast());
        assertEquals(factory.getSize(), 0);

    }
}