import org.junit.jupiter.api.*;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


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
class FactoryImplTest {

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {

    }


    static class AddFirst {

        @Test
        @MethodSource("emptyFactoryProvider")
        @DisplayName("Add First Test")
        void emptyFactoryTest(FactoryImpl testFactory, CorrectFactory correctFactory) {

        }

        @Test
        @MethodSource("oneProductFactoryProvider")
        @DisplayName("Add First Test")
        void oneProductFactoryTest(FactoryImpl testFactory, CorrectFactory correctFactory) {

        }

        @Test
        @MethodSource("twoProductFactoryProvider")
        @DisplayName("Add First Test")
        void twoProductFactoryTest(FactoryImpl testFactory, CorrectFactory correctFactory) {

        }

        @Test
        @MethodSource("fullFactoryProvider")
        @DisplayName("Add First Test")
        void fullFactoryTest(FactoryImpl testFactory, CorrectFactory correctFactory) {

        }

    }


    static Arguments emptyFactoryProvider() {
        return  Arguments.of(new FactoryImpl(), new CorrectFactory());
    }

    static Arguments oneProductFactoryProvider() {
        return Arguments.of(
        );
    }

    static Stream<FactoryImpl> productProvider() {
        return Stream.empty();
    }


}

class CorrectFactory {

    Holder first;

    Holder last;

    Integer size;

    public CorrectFactory() {
        first = null;
        last = null;
        size = 0;
    }

    public void addFirst(Product product) {
        final Holder f = first;
        final Holder newHolder = new Holder(null, product, f);
        first = newHolder;
        if (f == null)
            last = newHolder;
        else
            f.setPreviousHolder(newHolder);
        size++;
    }

    public void addLast(Product product) {
        final Holder l = last;
        final Holder newHolder = new Holder(l, product, null);
        last = newHolder;
        if (l == null)
            first = newHolder;
        else
            l.setNextHolder(newHolder);
        size++;
    }

    public Product removeFirst() throws NoSuchElementException {
        final Holder f = first;
        if (f == null)
            throw new NoSuchElementException();
        final Product product = f.getProduct();
        final Holder next = f.getNextHolder();
        f.setProduct(null);
        f.setNextHolder(null);
        first = next;
        if (next == null)
            last = null;
        else
            next.setPreviousHolder(null);
        size--;
        return product;
    }

    public Product removeLast() throws NoSuchElementException {
        final Holder l = last;
        if (l == null)
            throw new NoSuchElementException();
        final Product product = l.getProduct();
        final Holder prev = l.getPreviousHolder();
        l.setProduct(null);
        l.setPreviousHolder(null);
        last = prev;
        if (prev == null)
            first = null;
        else
            prev.setNextHolder(null);
        size--;
        return product;
    }

    public Product find(int id) throws NoSuchElementException {
        for (Holder x = first; x != null; x = x.getNextHolder()) {
            if (x.getProduct() == null)
                continue;
            if (x.getProduct().getId() == id)
                return x.getProduct();
        }
        throw new NoSuchElementException();
    }

    public Product update(int id, Integer value) throws NoSuchElementException {
        for (Holder x = first; x != null; x = x.getNextHolder()) {
            if (x.getProduct() == null)
                continue;
            if (x.getProduct().getId() == id) {
                final Product p = new Product(
                        x.getProduct().getId(),
                        x.getProduct().getValue());
                x.getProduct().setValue(value);
                return p;
            }
        }
        throw new NoSuchElementException();
    }

    public Product get(int index) throws IndexOutOfBoundsException {
        if (!(index >= 0 && index < size))
            throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);

        Holder x;
        if (index < (size >> 1)) {
            x = first;
            for (int i = 0; i < index; i++)
                x = x.getNextHolder();
        } else {
            x = last;
            for (int i = size - 1; i > index; i--)
                x = x.getPreviousHolder();
        }
        return x.getProduct();
    }

    public String toString() {
        if (size == 0)
            return "{}";

        StringBuilder sb = new StringBuilder("{");
        for (Holder x = first; x != null; x = x.getNextHolder()) {
            sb.append(x).append(",");
        }
        sb.delete(sb.length()-1, sb.length());
        sb.append("}");
        return sb.toString();
    }

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
  public static class SubstractTest {

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
      assertEquals(expected, Calculator.substract(a, b));
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
