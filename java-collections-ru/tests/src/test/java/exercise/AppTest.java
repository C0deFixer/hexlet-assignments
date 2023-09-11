package exercise;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
//import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AppTest {
    //List<Integer> numbers;
/*    final int RANGE = 100;
    final int BOUND = 100;
    final int MIN_BOUND = 10;*/


    @Test
    void testTake() {
        // BEGIN
/*        Random randomGen = new Random();
        int countMembersTest = randomGen.nextInt(BOUND) + MIN_BOUND;
        List<Integer> numbersTest = new ArrayList<>();
        Integer[] numbersActual = new Integer[countMembersTest];
        for (int i = 0; i < RANGE; i++) {
            int value = randomGen.nextInt(RANGE);
            numbersTest.add(value);
            if (i < countMembersTest) {
                numbersActual[i] = value;
            }
        }
        List<Integer> taken = App.take(numbersTest, countMembersTest);
        Assertions.assertArrayEquals(taken.toArray(), numbersActual);*/
        List<Integer> numbers1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        Assertions.assertArrayEquals(App.take(numbers1, 3).toArray(), numbers1.subList(0, 3).toArray());
    }
    // END
}
