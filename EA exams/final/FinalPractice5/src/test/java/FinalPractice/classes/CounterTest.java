package FinalPractice.classes;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.jupiter.api.*;

public class CounterTest {
    private Counter counter;

    @BeforeEach
    public void init(){
        counter = new Counter();
    }
    @BeforeClass
    public void init1(){
        counter=new Counter();
    }
    @Test
    public void incrementTest()
    {
        assertThat(counter.increment(), equalTo(1));
        assertThat(counter.increment(), equalTo(2));
    }
    @Test
    public void decrementTest()
    {
        assertEquals(-1, counter.decrement());
        assertEquals(-2, counter.decrement());
    }
}
