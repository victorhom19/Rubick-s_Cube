package Cube;

import java.util.*;

public class Main {


    public static void main(String[] args) {

        System.out.print("-".repeat(196) + "\n" +
                "| Use \"Rotate\" to start entering rotation sequence. Use U, D, L, R, CW, CCW + " +
                "number of row (if needed) to turn specified side of the cube " +
                "Up, Down, Left, Right, Clockwise and Counter-clockwise. |\n" +
                "| Rows are numerated from 0 to \"size of cube - 1\" from left to right and top-down." +
                " ".repeat(113) + "|\n" +
                "| You can also turn whole cube by entering direction of turning: Up, Down, Right, Left." +
                " ".repeat(108) + "|\n" +
                "| To see current state of cube sides enter \"Status\" and choose specific side: " +
                "\"Front\", \"Back\", \"Top\", \"Bottom\", \"Left\", \"Right\" or whole cube: \"All\"" +
                " ".repeat(47) + "|\n" +
                "| To shuffle cube use command \"Shuffle\"" +
                " ".repeat(156) + "|\n" +
                "| To change displaying mode use command \"ChangeOutputMode\"" +
                " ".repeat(137) + "|\n" +
                "-".repeat(196) + "\n\n");

        Scanner keyboard = new Scanner(System.in);


        int enteredSize = -1;
        System.out.println("Enter size of a cube:");
        try {
            enteredSize = keyboard.nextInt();
        } catch (Exception e) {
            System.out.print(Cube.setColor(Cube.Colors.Red) + "Failed to create cube. Invalid size."
                    + Cube.resetColor());
            System.exit(0);
        }

        if (enteredSize <= 0) {
            System.out.print(Cube.setColor(Cube.Colors.Red) + "Failed to create cube. Invalid size."
                    + Cube.resetColor());
            System.exit(0);
        }

        System.out.println(enteredSize + "x" + enteredSize + " Cube created.");
        Cube cubeExample = new Cube(enteredSize);

        String command;
        while (true) {
            System.out.println("Command: ");
            command = keyboard.next();
            switch (command) {
                case "Status":
                    System.out.println("Enter a side of cube: ");
                    String requestedSide = keyboard.next();
                    if (cubeExample.sideFromString(requestedSide) == null) {
                        System.out.println(Cube.setColor(Cube.Colors.Red) + "Invalid name of side"
                                + Cube.resetColor());
                        break;
                    }
                    for (Cube.Side side : Objects.requireNonNull(cubeExample.sideFromString(requestedSide))) {
                        System.out.println(side.toString() + "\n");
                    }
                    break;

                case "Rotate":
                    System.out.println("Enter rotation sequence:");
                    try {
                        ArrayList<String> rotationSequence =
                                new ArrayList<>(Arrays.asList(keyboard.next().split(",")));
                        cubeExample.interactWithCube(rotationSequence);
                    } catch (Exception e) {
                        System.out.println(Cube.setColor(Cube.Colors.Red) + "Invalid rotation command."
                                + Cube.resetColor());
                        break;
                    }

                    break;

                case "ChangeOutputMode":
                    if (cubeExample.Mode.equals(Cube.outputMode.Colors)) {
                        System.out.println("Changed: Colors ---> Letters");
                    } else {
                        System.out.println("Changed: Letters ---> Colors");
                    }
                    cubeExample.changeOutputMode();
                    break;

                case "Shuffle":
                    cubeExample.shuffleCube();
                    break;

                case "Stop":
                    System.exit(0);

                default:
                    System.out.println(Cube.setColor(Cube.Colors.Red) + "Unknown command"
                            + Cube.resetColor());
                    break;
            }
        }
    }
}




