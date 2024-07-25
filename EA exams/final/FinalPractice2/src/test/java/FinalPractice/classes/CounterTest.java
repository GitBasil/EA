package FinalPractice.classes;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.jupiter.api.*;

public class CounterTest {
    private Counter counter;

    @BeforeEach
    public void init() throws Exception{
        counter = new Counter();
    }

    @BeforeClass
    public void startClass() throws Exception{
        System.out.println("Started");
    }

    @Test
    public void testIncrement()
    {
        assertThat(counter.increment(), equalTo(1));
        assertThat(counter.increment(), equalTo(2));
    }

    @Test
    public void testDecrement(){
        assertEquals("Error message", -1, counter.decrement());
        assertEquals("Error message", -2, counter.decrement());
    }
}
