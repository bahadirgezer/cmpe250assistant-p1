import org.junit.jupiter.api.*;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


/*
    addFirst        empty factory
                    1 product factory
                    full factory

    addLast         empty factory
                    1 product factory
                    full factory

    removeFirst     empty factory
                    2 product factory
                    1 product factory
                    empty factory

    removeLast      empty factory
                    2 product factory
                    1 product factory
                    empty factory

    find            empty factory (non-existing product)
                    full factory (non-existing product)
                    full factory (first product)
                    full factory (last product)
                    full factory (random product)
                    1 product factory (that product)
                    2 product factory (random product)

    update          empty factory (non-existing product)
                    full factory (non-existing product)
                    full factory (first product)
                    full factory (last product)
                    full factory (random product)
                    1 product factory (that product)
                    2 product factory (random product)

    get             full factory (high index)
                    full factory (negative index)
                    full factory (middle)
                    full factory (end)
                    full factory (beginning)
                    empty factory (index 0)
                    1 product factory (index 0)
                    2 product factory (index 0)
                    2 product factory (index 1)
 */


public class FactoryImplTest {
    /*
    // Use the @BeforeAll annotation to specify that this method should be run
    // once before all of the test methods in the class.
    @BeforeAll
    public static void setUp(String[] args) {
        if (args.length < 1) {
            System.out.println("Please specify the student's name as the first argument");
            return;
        }

        String studentName = args[0];

        // Create an instance of the correct class
        FactoryImpl factory = new FactoryImpl();

        // Create an instance of the student submission class using the fully qualified class name
        // constructed from the student's name
        // e.g. "submssions.StudentName.Project1.src.FactoryImpl"
    }

    // Use the @Test annotation to specify that this method is a test method
    @Test
    public void testStudentSubmission() {
        // Test the student submission
        // ...
    }
}

public class FactoryImplTest {

}


/*
@RunWith(Enclosed.class)
public class CalculatorTest {

  @RunWith(Parameterized.class)
  public static class AddTest {

    @Parameters
    public static Collection<Object[]> data() {
      return Arrays.asList(new Object[][] {
          { 23.0, 5.0, 28.0 }
      });
    }

    private Double a, b, expected;

    public AddTest(Double a, Double b, Double expected) {
      this.a = a;
      this.b = b;
      this.expected = expected;
    }

    @Test
    public void testAdd() {
      assertEquals(expected, Calculator.add(a, b));
    }
  }

  @RunWith(Parameterized.class)
  public static class SubtractTest {

    @Parameters
    public static Collection<Object[]> data() {
      return Arrays.asList(new Object[][] {
          { 3.0, 2.0, 1.0 }
      });
    }

    @Parameter(0)
    private Double a;
    @Parameter(1)
    private Double b;
    @Parameter(2)
    private Double expected;

    @Test
    public void testSubstract() {
      assertEquals(expected, Calculator.subtract(a, b));
    }
  }

  @RunWith(Parameterized.class)
  public static class MethodWithOtherParametersTest {

    @Parameters
    public static Collection<Object[]> data() {
      return Arrays.asList(new Object[][] {
          { 3.0, 2.0, "OTHER", 1.0 }
      });
    }

    private Double a;
    private BigDecimal b;
    private String other;
    private Double expected;

    public MethodWithOtherParametersTest(Double a, BigDecimal b, String other, Double expected) {
      this.a = a;
      this.b = b;
      this.other = other;
      this.expected = expected;
    }

    @Test
    public void testMethodWithOtherParametersTest() {
      assertEquals(expected, Calculator.methodWithOtherParametersTest(a, b, other));
    }
  }

  public static class OtherNonParameterizedTests {

    // here you can add any other test which is not parameterized

    @Test
    public void otherTest() {
      // test something else
    }
  }
}
 */
}