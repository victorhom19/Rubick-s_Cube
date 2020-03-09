

package Cube;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;


class CubeTest {

    private Cube testCube;
    private Cube trueCube;

    @Test
    void interactTest2x2() {

        trueCube = new Cube(2);
        testCube = new Cube(2);
        testCube.interactWithCube(new ArrayList<>(Arrays.asList(
                "R1", "L0", "CCW", "Left", "CCW", "D1", "D1")));
        trueCube.Front.sideState = Cube.stringToCells(Arrays.asList(
                "Y", "Y",
                "B", "W"));

        trueCube.Back.sideState = Cube.stringToCells(Arrays.asList(
                "B", "G",
                "W", "G"));

        trueCube.Top.sideState = Cube.stringToCells(Arrays.asList(
                "Y", "R",
                "R", "B"));

        trueCube.Bottom.sideState = Cube.stringToCells(Arrays.asList(
                "R", "G",
                "W", "O"));

        trueCube.Left.sideState = Cube.stringToCells(Arrays.asList(
                "O", "G",
                "O", "W"));

        trueCube.Right.sideState = Cube.stringToCells(Arrays.asList(
                "O", "Y",
                "R", "B"));

        assertEquals(testCube, trueCube);



        trueCube = new Cube(2);
        testCube = new Cube(2);
        testCube.interactWithCube(new ArrayList<>(Arrays.asList(
                "Left", "R1", "R1", "CCW", "CCW", "D0", "U1", "Right", "CCW", "R1", "U0")));
        trueCube.Front.sideState = Cube.stringToCells(Arrays.asList(
                "R", "W",
                "O", "O"));

        trueCube.Back.sideState = Cube.stringToCells(Arrays.asList(
                "W", "G",
                "O", "R"));

        trueCube.Top.sideState = Cube.stringToCells(Arrays.asList(
                "W", "O",
                "G", "B"));

        trueCube.Bottom.sideState = Cube.stringToCells(Arrays.asList(
                "B", "B",
                "Y", "G"));

        trueCube.Left.sideState = Cube.stringToCells(Arrays.asList(
                "R", "Y",
                "B", "W"));

        trueCube.Right.sideState = Cube.stringToCells(Arrays.asList(
                "R", "G",
                "Y", "Y"));

        assertEquals(testCube, trueCube);



        trueCube = new Cube(2);
        testCube = new Cube(2);
        testCube.interactWithCube(new ArrayList<>(Arrays.asList(
                "D0", "D0", "CCW", "R1", "L0", "L0", "Down", "R0", "U0", "CW", "Up", "CW", "Right", "CCW")));
        trueCube.Front.sideState = Cube.stringToCells(Arrays.asList(
                "G", "R",
                "G", "B"));

        trueCube.Back.sideState = Cube.stringToCells(Arrays.asList(
                "B", "G",
                "B", "O"));

        trueCube.Top.sideState = Cube.stringToCells(Arrays.asList(
                "Y", "W",
                "Y", "B"));

        trueCube.Bottom.sideState = Cube.stringToCells(Arrays.asList(
                "W", "Y",
                "G", "W"));

        trueCube.Left.sideState = Cube.stringToCells(Arrays.asList(
                "O", "R",
                "W", "R"));

        trueCube.Right.sideState = Cube.stringToCells(Arrays.asList(
                "Y", "R",
                "O", "O"));

        assertEquals(testCube, trueCube);
    }


    @Test
    void interactTest3x3() {

        trueCube = new Cube(3);
        testCube = new Cube(3);
        testCube.interactWithCube(new ArrayList<>(Arrays.asList(
                "R0", "D2", "CW", "CW", "L1")));
        trueCube.Front.sideState = Cube.stringToCells(Arrays.asList(
                "G","W","W",
                "R","O","O",
                "G","R","R"));

        trueCube.Back.sideState = Cube.stringToCells(Arrays.asList(
                "B","O","O",
                "R","R","W",
                "B","Y","Y"));

        trueCube.Top.sideState = Cube.stringToCells(Arrays.asList(
                "G","G","Y",
                "G","G","Y",
                "R","B","B"));

        trueCube.Bottom.sideState = Cube.stringToCells(Arrays.asList(
                "O","G","G",
                "B","B","W",
                "B","B","W"));

        trueCube.Left.sideState = Cube.stringToCells(Arrays.asList(
                "Y","Y","W",
                "G","W","W",
                "R","R","W"));

        trueCube.Right.sideState = Cube.stringToCells(Arrays.asList(
                "R","O","O",
                "B","Y","Y",
                "Y","O","O"));

        assertEquals(testCube, trueCube);



        trueCube = new Cube(3);
        testCube = new Cube(3);
        testCube.interactWithCube(new ArrayList<>(Arrays.asList(
                "R1","L0","Left","CCW","D2","D2","CW","Up","L2","L1","D0","R2")));

        trueCube.Front.sideState = Cube.stringToCells(Arrays.asList(
                "Y","O","Y",
                "W","O","Y",
                "Y","G","G"));

        trueCube.Back.sideState = Cube.stringToCells(Arrays.asList(
                "G","G","R",
                "O","R","R",
                "W","G","G"));

        trueCube.Top.sideState = Cube.stringToCells(Arrays.asList(
                "W","Y","Y",
                "W","W","W",
                "G","Y","R"));

        trueCube.Bottom.sideState = Cube.stringToCells(Arrays.asList(
                "B","R","R",
                "Y","Y","O",
                "W","W","O"));

        trueCube.Left.sideState = Cube.stringToCells(Arrays.asList(
                "B","B","O",
                "B","B","R",
                "O","R","O"));

        trueCube.Right.sideState = Cube.stringToCells(Arrays.asList(
                "B","O","R",
                "B","G","G",
                "W","B","B"));

        assertEquals(testCube, trueCube);



        trueCube = new Cube(3);
        testCube = new Cube(3);
        testCube.interactWithCube(new ArrayList<>(Arrays.asList(
                "CW","Left","L1", "L1", "U2", "R1", "Left", "CCW", "Right", "R1", "D0", "Down", "CCW", "Up", "L2")));

        trueCube.Front.sideState = Cube.stringToCells(Arrays.asList(
                "W","G","W",
                "R","O","O",
                "Y","R","Y"));

        trueCube.Back.sideState = Cube.stringToCells(Arrays.asList(
                "Y","B","Y",
                "W","R","O",
                "W","Y","W"));

        trueCube.Top.sideState = Cube.stringToCells(Arrays.asList(
                "G","Y","G",
                "G","G","G",
                "B","W","B"));

        trueCube.Bottom.sideState = Cube.stringToCells(Arrays.asList(
                "B","Y","B",
                "B","B","B",
                "G","G","G"));

        trueCube.Left.sideState = Cube.stringToCells(Arrays.asList(
                "R","R","O",
                "W","W","W",
                "R","O","O"));

        trueCube.Right.sideState = Cube.stringToCells(Arrays.asList(
                "R","O","O",
                "Y","Y","B",
                "R","R","O"));

        assertEquals(testCube, trueCube);
    }

    @Test
    void interactTest4x4() {

        trueCube = new Cube(4);
        testCube = new Cube(4);
        testCube.interactWithCube(new ArrayList<>(Arrays.asList(
                "CW","Left","L1", "L2", "U2", "R3", "Left", "CW", "Right", "R2", "CCW", "Up", "L2")));

        trueCube.Front.sideState = Cube.stringToCells(Arrays.asList(
                "W","O","W","B",
                "B","B","B","W",
                "B","B","R","R",
                "B","B","B","R"));

        trueCube.Back.sideState = Cube.stringToCells(Arrays.asList(
                "O","Y","O","G",
                "Y","Y","G","R",
                "W","O","G","G",
                "O","O","G","R"));

        trueCube.Top.sideState = Cube.stringToCells(Arrays.asList(
                "O","B","O","B",
                "B","B","O","W",
                "O","Y","O","W",
                "G","Y","G","W"));

        trueCube.Bottom.sideState = Cube.stringToCells(Arrays.asList(
                "Y","Y","Y","G",
                "Y","R","R","B",
                "W","W","G","G",
                "B","R","G","G"));

        trueCube.Left.sideState = Cube.stringToCells(Arrays.asList(
                "W","O","G","R",
                "W","O","W","R",
                "R","W","W","W",
                "W","G","G","R"));

        trueCube.Right.sideState = Cube.stringToCells(Arrays.asList(
                "O","O","O","Y",
                "B","Y","R","R",
                "Y","Y","G","R",
                "Y","R","Y","Y"));

        assertEquals(testCube, trueCube);



        trueCube = new Cube(4);
        testCube = new Cube(4);
        testCube.interactWithCube(new ArrayList<>(Arrays.asList(
                "R3","CW","L2","Up","D1","Left","L2","R0","D1","Down","CW","CW","R2","CCW","Up","L3")));
        trueCube.Front.sideState = Cube.stringToCells(Arrays.asList(
                "G","Y","O","G",
                "W","G","O","O",
                "G","G","R","G",
                "G","R","O","G"));

        trueCube.Back.sideState = Cube.stringToCells(Arrays.asList(
                "B","W","W","B",
                "R","R","B","Y",
                "B","O","R","B",
                "B","W","Y","B"));

        trueCube.Top.sideState = Cube.stringToCells(Arrays.asList(
                "R","G","G","R",
                "Y","W","B","R",
                "W","O","O","W",
                "W","O","B","W"));

        trueCube.Bottom.sideState = Cube.stringToCells(Arrays.asList(
                "Y","B","B","Y",
                "Y","G","B","Y",
                "W","R","G","Y",
                "O","G","O","O"));

        trueCube.Left.sideState = Cube.stringToCells(Arrays.asList(
                "W","G","O","O",
                "B","W","Y","B",
                "W","Y","Y","O",
                "W","R","O","O"));

        trueCube.Right.sideState = Cube.stringToCells(Arrays.asList(
                "R","R","B","Y",
                "G","W","Y","G",
                "R","B","W","Y",
                "R","R","R","Y"));

        assertEquals(testCube, trueCube);



        trueCube = new Cube(4);
        testCube = new Cube(4);
        testCube.interactWithCube(new ArrayList<>(Arrays.asList(
                "R1","U2","CW","Up","Left","R1","D0","U3","D1","Down","CCW","Right","R2","U0","CCW","L3")));
        trueCube.Front.sideState = Cube.stringToCells(Arrays.asList(
                "W","B","B","Y",
                "B","B","B","O",
                "Y","O","G","W",
                "G","R","G","B"));

        trueCube.Back.sideState = Cube.stringToCells(Arrays.asList(
                "Y","Y","R","Y",
                "G","O","W","Y",
                "R","R","G","Y",
                "B","G","B","O"));

        trueCube.Top.sideState = Cube.stringToCells(Arrays.asList(
                "B","W","R","G",
                "B","Y","W","G",
                "R","G","Y","O",
                "G","W","R","R"));

        trueCube.Bottom.sideState = Cube.stringToCells(Arrays.asList(
                "R","B","R","O",
                "O","B","Y","G",
                "W","B","W","Y",
                "G","Y","O","R"));

        trueCube.Left.sideState = Cube.stringToCells(Arrays.asList(
                "O","O","G","R",
                "O","O","G","Y",
                "O","O","R","G",
                "W","B","W","Y"));

        trueCube.Right.sideState = Cube.stringToCells(Arrays.asList(
                "B","B","O","O",
                "W","W","R","W",
                "R","R","Y","Y",
                "W","W","G","W"));

        assertEquals(testCube, trueCube);
    }

    @Test
    void destroyTest() {
        assertThrows(IllegalArgumentException.class, () -> testCube = new Cube(-20));
        assertThrows(IllegalArgumentException.class, () -> testCube = new Cube(0));

        testCube = new Cube(3);
        assertThrows(IllegalArgumentException.class, () ->
                testCube.interactWithCube(new ArrayList<>(Arrays.asList("R1","HelloWorld"))));

        testCube = new Cube(3);
        assertThrows(IllegalArgumentException.class, () ->
                testCube.interactWithCube(new ArrayList<>(Arrays.asList("R10","U2"))));

        testCube = new Cube(3);
        assertThrows(IllegalArgumentException.class, () ->
                testCube.interactWithCube(new ArrayList<>(Arrays.asList("R1","U-7"))));

    }


    @Test
    void shuffleCube() {
        trueCube = new Cube(10);
        testCube = new Cube(10);
        testCube.shuffleCube();
        assertNotEquals(trueCube, testCube);

        trueCube = new Cube(15);
        testCube = new Cube(15);
        testCube.shuffleCube();
        assertNotEquals(trueCube, testCube);

        trueCube = new Cube(20);
        testCube = new Cube(20);
        testCube.shuffleCube();
        assertNotEquals(trueCube, testCube);
    }
}
