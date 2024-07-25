package FinalPractice.domain;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.jupiter.api.*;

public class CounterTest {
    private Counter counter;
    @BeforeClass
    public void init() throws Exception {
        counter = new Counter();
    }
    @BeforeEach
    public void setUp() throws Exception {
        counter = new Counter();
    }
    @Test
    public void testIncrement(){
            assertEquals("Counter.increment does not work correctly",1,counter.increment());
            assertEquals("Counter.increment does not work correctly",2,counter.increment());
    }
    @Test
    public void testDecrement(){
            assertThat(counter.decrement(), equalTo(-1));
            assertThat(counter.decrement(), equalTo(-2));
    }
    }