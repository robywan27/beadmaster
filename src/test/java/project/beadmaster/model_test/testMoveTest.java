package project.beadmaster.model_test;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import project.beadmaster.model.GameException;


/**
 * This class performs some tests to assess the correctness of the model by invoking the moveTest(String) method.
 *
 */
public class testMoveTest {
    private MoveTester moveTester;
    private String configuration;

    @Before
    public void setUp() {
        moveTester = new MoveTester();
        configuration = "";
    }

    @Test
    public void test1() {
        configuration = "2" + // number of players
                "1" + // moving player
                "0120120" + // positions of the horizontal bars
                "2101102" + // positions of the vertical bars
                "0000100" + // beads in the grid
                "0020000" +
                "0000001" +
                "2000000" +
                "0000000" +
                "0001000" +
                "0000000" +
                "h3i" + // a configuration: type of bar, bar number, direction
                "v2o"; // a configuration: type of bar, bar number, direction

        try {
            assertEquals("21011012022011020000100002000000000002000000000000000010000000000", moveTester.moveTest(configuration));
        } catch (NumberFormatException e) {
            System.out.println(e.toString());
        } catch (GameException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    public void test2() {
        configuration = "2"	+
                "1" +
                "0120120" +
                "2101102" +
                "0100100" +
                "0020000" +
                "0000001" +
                "2000000" +
                "0000000" +
                "0001000" +
                "0000000" +
                "h3i" +
                "v2o";

        try {
            assertEquals("error: you can't put a bead in a hole. Put it either on a vertical bar or on a horizontal bar.", moveTester.moveTest(configuration));
        } catch (NumberFormatException e) {
            System.out.println(e.toString());
        } catch (GameException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    public void test3() {
        configuration = "2" +
                "1" +
                "0120120" +
                "2101102" +
                "0000100" +
                "0020000" +
                "0000001" +
                "2000000" +
                "0000000" +
                "0001000" +
                "0000000" +
                "h3i" +
                "v2o";

        try {
            assertEquals("21011012022011020000100002000000000002000000000000000010000000000", moveTester.moveTest(configuration));
        } catch (NumberFormatException e) {
            System.out.println(e.toString());
        } catch (GameException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    public void test4() {
        configuration = "2" +
                "1" +
                "0120120" +
                "2101102" +
                "0000100" +
                "0020000" +
                "0000001" +
                "2000000" +
                "0000000" +
                "0001000" +
                "0000000" +
                "h3i"  +
                "v2o";

        try {
            assertEquals("21011012022011020000100002000000000002000000000000000010000000000", moveTester.moveTest(configuration));
        } catch (NumberFormatException e) {
            System.out.println(e.toString());
        } catch (GameException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    public void test5() {
        configuration = "2" +
                "1" +
                "0120120" +
                "2101102" +
                "0000100" +
                "0020000" +
                "0000001" +
                "2000000" +
                "0000000" +
                "0001000" +
                "0000000" +
                "h3i" +
                "v2o" +
                "h2o";

        try {
            assertEquals("22021012022011020000100000000000000002000000000000000010000000000", moveTester.moveTest(configuration));
        } catch (NumberFormatException e) {
            System.out.println(e.toString());
        } catch (GameException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    public void test6() {
        configuration = "2" +
                "1" +
                "0120120" +
                "2101102" +
                "0000100" +
                "0020000" +
                "0000001" +
                "2000000" +
                "0000000" +
                "0001000" +
                "0000000" +
                "h3i" +
                "v2o" +
                "h2o" +
                "h2o";

        try {
            assertEquals("error: the bar slid by you is the same bar slid by another player in this same turn. Slide another bar.", moveTester.moveTest(configuration));
        } catch (NumberFormatException e) {
            System.out.println(e.toString());
        } catch (GameException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    public void test7() {
        configuration = "2" + "1" + "0120120" + "2101102" + "0000100" + "0020000" + "0000001" + "2000000" + "0000000" + "0001000" + "0000000" + "h3i" + "v2o" + "h2o" + "v4i";

        try {
            assertEquals("21021012022001020000100000000000000002000000000000000010000000000", moveTester.moveTest(configuration));
        } catch (NumberFormatException e) {
            System.out.println(e.toString());
        } catch (GameException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    public void test8() {
        configuration = "2" + "1" + "0211120" + "2200102" + "0000100" + "0000000" + "0000000" + "2000000" + "0000000" + "0001000" + "0000000" + "v1i";
        //Player 1 does the last move which makes the player's 2 bead fall down.
        try {
            assertEquals("21021112012001020000100000000000000000000000000000000010000000000", moveTester.moveTest(configuration));
        } catch (NumberFormatException e) {
            System.out.println(e.toString());
        } catch (GameException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    public void test9() {
        configuration = "2" + "1" + "0210120" + "2200102" + "0000100" + "0000000" + "0000000" + "2000000" + "0000000" + "0001000" + "0000000" + "h50" + "v1i" + "h3i";

        try {
            assertEquals("22020022012001020000100000000000000002000000000000000010000000000", moveTester.moveTest(configuration));
        } catch (NumberFormatException e) {
            System.out.println(e.toString());
        } catch (GameException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    public void test10() {
        configuration = "2" + "2" + "0210120" + "2200102" + "0000100" + "0000000" + "0000000" + "2000000" + "0000000" + "0001000" + "0000000" + "h50" + "v1i" + "h3i";

        try {
            assertEquals("21020022012001020000100000000000000002000000000000000010000000000", moveTester.moveTest(configuration));
        } catch (NumberFormatException e) {
            System.out.println(e.toString());
        } catch (GameException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    public void test11() {
        configuration = "2" + "2" + "0210120" + "2200102" + "0000100" + "0000000" + "0000000" + "2000000" + "0000000" + "0001000" + "0000000" + "h50" + "v1i" + "h3i";

        try {

            assertEquals("21020022012001020000100000000000000002000000000000000010000000000", moveTester.moveTest(configuration));
        } catch (NumberFormatException e) {
            System.out.println(e.toString());
        } catch (GameException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    public void test12() {
        configuration = "4" + "1" + "0120120" + "2101102" + "3010120" + "0020010" + "0032001" + "2030301" + "0300004" + "0004004" + "4004020" + "v3o" + "v6o" + "h6i" + "h4o" + "v4o" + "h3i" + "h4i";

        try {
            assertEquals("error: the bar slid by you is the same bar slid by another player in this same turn. Slide another bar.", moveTester.moveTest(configuration));
        } catch (NumberFormatException e) {
            System.out.println(e.toString());
        } catch (GameException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    public void test13() {
        configuration = "4" + "1" + "0120120" + "2101102" + "3010120"+ "0020010" + "0032001" + "2030301" + "0300004" + "0004004" + "4004020" + "v3o" + "v6o"+ "h6i"+ "h4o"+ "v4o"+ "h3i"+ "h6i" + "h1o" + "v6o" + "h2i" + "v2o" + "h1i" + "v5o" + "v7i" + "h1o" + "v7i" + "v3i" + "v1i";

        try {
            assertEquals("44101110012022200000000000000000020000000000000000000000004004020", moveTester.moveTest(configuration));
        } catch (NumberFormatException e) {
            System.out.println(e.toString());
        } catch (GameException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    public void test14() {
        configuration = "4" + "4" + "1011100" + "1202220" + "0000000" + "0000000" + "0000000" + "0000004" + "0000000" + "0000000" + "0000002" + "v7o";

        try {
            assertEquals("41101110012022210000000000000000000000000000000000000000000000000", moveTester.moveTest(configuration));
        } catch (NumberFormatException e) {
            System.out.println(e.toString());
        } catch (GameException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    public void test15() {
        configuration = "4" + "1" + "1111111" + "1111111" + "0102000" + "0000000" + "0000000" + "0000000" + "0000000" + "0000000" + "0000034" + "h4i" + "h3i" + "h2i" + "h1i" + "h2o" + "h1o" + "h5o";

        try {
            assertEquals("4" + "4" + "1100211" + "1111111" + "0000000" + "0000000" + "0000000" + "0000000" + "0000000" + "0000000" + "0000034", moveTester.moveTest(configuration));
        } catch (NumberFormatException e) {
            System.out.println(e.toString());
        } catch (GameException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    public void test16() {
        configuration = "4" + "1" + "1111111" + "1111111" + "0102000" + "0000000" + "0000000" + "0000000" + "0000000" + "0000000" + "0000034" + "h4i" + "h3i" + "h2i" + "h1i" + "h2o" + "h1o" + "h2i";

        try {
            assertEquals("error: you have slid this bar twice in your previous turns. Slide another bar.", moveTester.moveTest(configuration));
        } catch (NumberFormatException e) {
            System.out.println(e.toString());
        } catch (GameException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    public void test17() {
        configuration = "4" + "1" + "1111111" + "1111111" + "0304000" + "0000000" + "0000000" + "0000000" + "0000000" + "0000000" + "0000012" + "h4i" + "h3i" + "h2i" + "h1i" + "h4o" + "h3o" + "h5o";

        try {
            assertEquals("4" + "2" + "0011211" + "1111111" + "0000000" + "0000000" + "0000000" + "0000000" + "0000000" + "0000000" + "0000012", moveTester.moveTest(configuration));
        } catch (NumberFormatException e) {
            System.out.println(e.toString());
        } catch (GameException e) {
            System.out.println(e.toString());
        }
    }


    @Test
    public void test18() {
        configuration = "4" + "1" + "1111111" + "1111111" + "0102000" + "0000000" + "0000000" + "0000000" + "0000000" + "0000000" + "0000034" + "h4i" + "h3i" + "h2i" + "h1i" + "h2o" + "h2o";

        try {
            assertEquals("error: the bar slid by you is the same bar slid by another player in this same turn. Slide another bar.", moveTester.moveTest(configuration));
        } catch (NumberFormatException e) {
            System.out.println(e.toString());
        } catch (GameException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    public void test19() {
        configuration = "4" + "1" + "1111111" + "1111111" + "0102000" + "0000000" + "0000000" + "0000000" + "0000000" + "0000000" + "0000034" + "h4i" + "h3i" + "h2i" + "h1i" + "h2o" + "h1o" + "h2i";

        try {
            assertEquals("error: you have slid this bar twice in your previous turns. Slide another bar.", moveTester.moveTest(configuration));
        } catch (NumberFormatException e) {
            System.out.println(e.toString());
        } catch (GameException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    public void test20() {
        configuration = "4" + "1" + "1111111" + "1111111" + "0102000" + "0000000" + "0000000" + "0000000" + "0000000" + "0000000" + "0000034" + "h4i" + "h3i" + "h2i" + "h1i" + "h2o" + "h1o" + "h5o";

        try {
            assertEquals("44110021111111110000000000000000000000000000000000000000000000034", moveTester.moveTest(configuration));
        } catch (NumberFormatException e) {
            System.out.println(e.toString());
        } catch (GameException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    public void test21() {
        configuration = "4" + "1" + "1111111" + "1111111" + "0304000" + "0000000" + "0000000" + "0000000" + "0000000" + "0000000" + "0000012" + "h4i" + "h3i" + "h2i" + "h1i"  + "h1o";

        try {
            assertEquals("error: the bar slid by you is the same bar slid by another player in this same turn. Slide another bar.", moveTester.moveTest(configuration));
        } catch (NumberFormatException e) {
            System.out.println(e.toString());
        } catch (GameException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    public void test22() {
        configuration = "4" + "1" + "1111111" + "1111111" + "0304000" + "0000000" + "0000000" + "0000000" + "0000000" + "0000000" + "0000012" + "h4i" + "h3i" + "h2i" + "h1i" + "h4o" + "h2o";

        try {
            assertEquals("error: the bar slid by you is the same bar slid by another player in this same turn. Slide another bar.", moveTester.moveTest(configuration));
        } catch (NumberFormatException e) {
            System.out.println(e.toString());
        } catch (GameException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    public void test23() {
        configuration = "4" + "1" + "1111111" + "1111111" + "0304000" + "0000000" + "0000000" + "0000000" + "0000000" + "0000000" + "0000012" + "h4i" + "h3i" + "h2i" + "h1i"  + "h4o" + "h3o" + "h5o";

        try {
            assertEquals("42001121111111110000000000000000000000000000000000000000000000012", moveTester.moveTest(configuration));
        } catch (NumberFormatException e) {
            System.out.println(e.toString());
        } catch (GameException e) {
            System.out.println(e.toString());
        }
    }

}
