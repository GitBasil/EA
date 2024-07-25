package FinalPractice.classes;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.jupiter.api.*;

public class CounterTest {
    private Counter counter;
    @BeforeClass
    public void init(){
        counter = new Counter();
    }
    @BeforeEach
    public void doit(){
        counter = new Counter();
    }

    @Test
    public void testIncrement(){
        assertThat(counter.increment(), equalTo(1));
        assertThat(counter.increment(), equalTo(2));
    }

    @Test
    public void testDecrement(){
        assertEquals("Wrong bbb",-1, counter.decrement());
        assertEquals("Wrong bbb",-2, counter.decrement());
    }
    
}
