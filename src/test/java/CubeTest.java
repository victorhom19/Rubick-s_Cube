import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CubeTest {

    @Test
    void interactWithCube() {

        Cube testCube;
        Cube trueCube = new Cube();

        testCube = new Cube();
        testCube.initializeCube(2);
        testCube.interactWithCube(new ArrayList<>(Arrays.asList(
                "R1", "L0", "CCW", "Left", "CCW", "D1", "D1")));
        trueCube.Front = new ArrayList<>(Arrays.asList("Y","Y","B","W"));
        trueCube.Back = new ArrayList<>(Arrays.asList("B","G","W","G"));
        trueCube.Top = new ArrayList<>(Arrays.asList("Y","R","R","B"));
        trueCube.Bottom = new ArrayList<>(Arrays.asList("R","G","W","O"));
        trueCube.Left = new ArrayList<>(Arrays.asList("O","G","O","W"));
        trueCube.Right = new ArrayList<>(Arrays.asList("O","Y","R","B"));
        assertEquals(testCube, trueCube);

        testCube = new Cube();
        testCube.initializeCube(2);
        testCube.interactWithCube(new ArrayList<>(Arrays.asList(
                "Left", "R1", "R1", "CCW", "CCW", "D0", "U1", "Right", "CCW", "R1", "U0")));
        trueCube.Front = new ArrayList<>(Arrays.asList("R","W","O","O"));
        trueCube.Back = new ArrayList<>(Arrays.asList("W","G","O","R"));
        trueCube.Top = new ArrayList<>(Arrays.asList("W","O","G","B"));
        trueCube.Bottom = new ArrayList<>(Arrays.asList("B","B","Y","G"));
        trueCube.Left = new ArrayList<>(Arrays.asList("R","Y","B","W"));
        trueCube.Right = new ArrayList<>(Arrays.asList("R","G","Y","Y"));
        assertEquals(testCube, trueCube);

        testCube = new Cube();
        testCube.initializeCube(2);
        testCube.interactWithCube(new ArrayList<>(Arrays.asList(
                "D0", "D0", "CCW", "R1", "L0", "L0", "Down", "R0", "U0", "CW", "Up", "CW", "Right", "CCW")));
        trueCube.Front = new ArrayList<>(Arrays.asList("G","R","G","B"));
        trueCube.Back = new ArrayList<>(Arrays.asList("B","G","B","O"));
        trueCube.Top = new ArrayList<>(Arrays.asList("Y","W","Y","B"));
        trueCube.Bottom = new ArrayList<>(Arrays.asList("W","Y","G","W"));
        trueCube.Left = new ArrayList<>(Arrays.asList("O","R","W","R"));
        trueCube.Right = new ArrayList<>(Arrays.asList("Y","R","O","O"));
        assertEquals(testCube, trueCube);

        testCube = new Cube();
        testCube.initializeCube(3);
        testCube.interactWithCube(new ArrayList<>(Arrays.asList(
                "R0", "D2", "CW", "CW", "L1")));
        trueCube.Front = new ArrayList<>(Arrays.asList("G","W","W","R","O","O","G","R","R"));
        trueCube.Back = new ArrayList<>(Arrays.asList("B","O","O","R","R","W","B","Y","Y"));
        trueCube.Top = new ArrayList<>(Arrays.asList("G","G","Y","G","G","Y","R","B","B"));
        trueCube.Bottom = new ArrayList<>(Arrays.asList("O","G","G","B","B","W","B","B","W"));
        trueCube.Left = new ArrayList<>(Arrays.asList("Y","Y","W","G","W","W","R","R","W"));
        trueCube.Right = new ArrayList<>(Arrays.asList("R","O","O","B","Y","Y","Y","O","O"));
        assertEquals(testCube, trueCube);

        testCube = new Cube();
        testCube.initializeCube(3);
        testCube.interactWithCube(new ArrayList<>(Arrays.asList(
                "R1","L0","Left","CCW","D2","D2","CW","Up","L2","L1","D0","R2")));
        trueCube.Front = new ArrayList<>(Arrays.asList("Y","O","Y","W","O","Y","Y","G","G"));
        trueCube.Back = new ArrayList<>(Arrays.asList("G","G","R","O","R","R","W","G","G"));
        trueCube.Top = new ArrayList<>(Arrays.asList("W","Y","Y","W","W","W","G","Y","R"));
        trueCube.Bottom = new ArrayList<>(Arrays.asList("B","R","R","Y","Y","O","W","W","O"));
        trueCube.Left = new ArrayList<>(Arrays.asList("B","B","O","B","B","R","O","R","O"));
        trueCube.Right = new ArrayList<>(Arrays.asList("B","O","R","B","G","G","W","B","B"));
        assertEquals(testCube, trueCube);

        testCube = new Cube();
        testCube.initializeCube(3);
        testCube.interactWithCube(new ArrayList<>(Arrays.asList(
                "CW","Left","L1", "L1", "U2", "R1", "Left", "CCW", "Right", "R1", "D0", "Down", "CCW", "Up", "L2")));
        trueCube.Front = new ArrayList<>(Arrays.asList("W","G","W","R","O","O","Y","R","Y"));
        trueCube.Back = new ArrayList<>(Arrays.asList("Y","B","Y","W","R","O","W","Y","W"));
        trueCube.Top = new ArrayList<>(Arrays.asList("G","Y","G","G","G","G","B","W","B"));
        trueCube.Bottom = new ArrayList<>(Arrays.asList("B","Y","B","B","B","B","G","G","G"));
        trueCube.Left = new ArrayList<>(Arrays.asList("R","R","O","W","W","W","R","O","O"));
        trueCube.Right = new ArrayList<>(Arrays.asList("R","O","O","Y","Y","B","R","R","O"));
        assertEquals(testCube, trueCube);


    }

    @Test
    void shuffleCube() {

    }
}