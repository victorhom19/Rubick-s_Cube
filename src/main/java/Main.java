
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        System.out.print("Use \"Rotate\" to start entering rotation sequence. Use U, D, L, R, CW, CCW + number of row (if needed) to turn specified side of the cube Up, Down, Left, Right, Clockwise and Counter-clockwise.\n" +
                "Rows are numerated from 0 to \"size of cube - 1\" from left to right and top-down.\n" +
                "You can also turn whole cube by entering direction of turning: Up, Down, Right, Left.\n" +
                "To see current state of cube sides enter \"Status\" and choose specific side: \"Front\", \"Back\", \"Top\", \"Bottom\", \"Left\", \"Right\" or whole cube: \"All\"\n\n");
        Scanner keyboard = new Scanner(System.in);
        Cube cubeExample = new Cube();
        cubeExample.initializeCube();
        String command = "";

        while (!command.equals("Stop")) {

            System.out.println("Command: ");
            command = keyboard.next();

            switch (command) {
                case "Status":
                    System.out.println("Enter a side of cube: ");
                    String requestedSide = keyboard.next();
                    cubeExample.sideStatus(requestedSide);
                    break;

                case "Rotate":
                    System.out.println("Enter rotation sequence:");
                    ArrayList<String> rotationSequence =
                            new ArrayList<>(Arrays.asList(keyboard.next().split(",")));
                    cubeExample.interactWithCube(rotationSequence);

                    break;

                case "changeOutputMode":
                    System.out.print("Changed from " + cubeExample.Output_Mode);
                    if (cubeExample.Output_Mode.equals("Letters")) cubeExample.Output_Mode = "Colors";
                    else if (cubeExample.Output_Mode.equals("Colors")) cubeExample.Output_Mode = "Letters";
                    System.out.print(" to " + cubeExample.Output_Mode + "\n");
                    break;
                case "Shuffle":
                    cubeExample.shuffleCube();
            }
        }
    }
}

final class Cube {

    private int cubeSize;

    private ArrayList<String> Front = new ArrayList<>();
    private ArrayList<String> Back = new ArrayList<>();
    private ArrayList<String> Top = new ArrayList<>();
    private ArrayList<String> Bottom = new ArrayList<>();
    private ArrayList<String> Left = new ArrayList<>();
    private ArrayList<String> Right = new ArrayList<>();

    String Output_Mode = "Letters";

    private Scanner keyboard = new Scanner(System.in);

    void sideStatus(String requestedSide) {
        ArrayList<String> sideToPrint = new ArrayList<>(Collections.emptyList());

        switch (requestedSide) {
            case "Front":
                sideToPrint = Front;
                break;
            case "Back":
                sideToPrint = Back;
                break;
            case "Top":
                sideToPrint = Top;
                break;
            case "Bottom":
                sideToPrint = Bottom;
                break;
            case "Left":
                sideToPrint = Left;
                break;
            case "Right":
                sideToPrint = Right;
                break;
            case "All":
                sideStatus("Front");
                sideStatus("Back");
                sideStatus("Top");
                sideStatus("Bottom");
                sideStatus("Left");
                sideStatus("Right");
                break;
            default:
                throw new IllegalArgumentException("Invalid side name");
        }

        if (Output_Mode.equals("Letters")) {
            String ANSI_RESET = "\u001B[0m";
            String ANSI_RED = "\u001B[38;5;196m";
            String ANSI_GREEN = "\u001B[38;5;40m";
            String ANSI_YELLOW = "\u001B[38;5;11m";
            String ANSI_BLUE = "\u001B[38;5;27m";
            String ANSI_ORANGE = "\u001B[38;5;202m";
            String ANSI_WHITE = "\u001B[38;5;255m";
            if (!(sideToPrint.isEmpty())) {
                System.out.print(requestedSide + ":\n");
                for (int i = 0; i < cubeSize * cubeSize; i++) {
                    String color = sideToPrint.get(i);
                    switch (color) {
                        case "W":
                            color = ANSI_WHITE;
                            break;
                        case "Y":
                            color = ANSI_YELLOW;
                            break;
                        case "G":
                            color = ANSI_GREEN;
                            break;
                        case "B":
                            color = ANSI_BLUE;
                            break;
                        case "R":
                            color = ANSI_RED;
                            break;
                        case "O":
                            color = ANSI_ORANGE;
                            break;
                    }
                    System.out.print(color + sideToPrint.get(i) + " ");
                    if (i % cubeSize == cubeSize - 1) System.out.print(ANSI_RESET + "\n");
                }
                System.out.print("\n");
            }

        } else if (Output_Mode.equals("Colors")) {
            String ANSI_RESET = "\u001B[0m";
            String ANSI_RED = "\u001B[48;5;196m";
            String ANSI_GREEN = "\u001B[48;5;40m";
            String ANSI_YELLOW = "\u001B[48;5;11m";
            String ANSI_BLUE = "\u001B[48;5;27m";
            String ANSI_ORANGE = "\u001B[48;5;202m";
            String ANSI_WHITE = "\u001B[48;5;255m";
            if (!(sideToPrint.isEmpty())) {
                System.out.print(requestedSide + ":\n");
                for (int i = 0; i < cubeSize * cubeSize; i++) {
                    String color = sideToPrint.get(i);
                    switch (color) {
                        case "W":
                            color = ANSI_WHITE;
                            break;
                        case "Y":
                            color = ANSI_YELLOW;
                            break;
                        case "G":
                            color = ANSI_GREEN;
                            break;
                        case "B":
                            color = ANSI_BLUE;
                            break;
                        case "R":
                            color = ANSI_RED;
                            break;
                        case "O":
                            color = ANSI_ORANGE;
                            break;
                    }
                    System.out.print(color + "   ");
                    if (i % cubeSize == cubeSize - 1) System.out.print(ANSI_RESET + "\n");
                }
                System.out.print("\n");
            }

        } else throw new IllegalArgumentException();

    }

    void initializeCube() {
        System.out.println("Enter a size of cube: ");
        cubeSize = keyboard.nextInt();


        for (int i = 0; i < cubeSize * cubeSize; i++) {
            Front.add("W");
        }
        for (int i = 0; i < cubeSize * cubeSize; i++) {
            Back.add("Y");
        }
        for (int i = 0; i < cubeSize * cubeSize; i++) {
            Top.add("G");
        }
        for (int i = 0; i < cubeSize * cubeSize; i++) {
            Bottom.add("B");
        }

        for (int i = 0; i < cubeSize * cubeSize; i++) {
            Left.add("R");
        }
        for (int i = 0; i < cubeSize * cubeSize; i++) {
            Right.add("O");
        }

    }

    private void flipSide(String side, String rotationDirection) {
        ArrayList<String> sideToFlip = new ArrayList<>(Collections.singletonList(""));
        ArrayList<String> bufferSide;
        switch (side) {
            case "Front":
                sideToFlip = Front;
                break;
            case "Back":
                sideToFlip = Back;
                break;
            case "Top":
                sideToFlip = Top;
                break;
            case "Bottom":
                sideToFlip = Bottom;
                break;
            case "Left":
                sideToFlip = Left;
                break;
            case "Right":
                sideToFlip = Right;
                break;
        }
        switch (rotationDirection) {
            case "clockwise":

                bufferSide = new ArrayList<>();
                for (int i = 0; i < cubeSize * cubeSize; i++) bufferSide.add("");
                for (int c = 0, i = 0, j = 0; c < cubeSize * cubeSize; c++, i = c / cubeSize, j = c % cubeSize) {
                    int index_from = i * cubeSize + j;
                    int index_to = j * cubeSize + cubeSize - i - 1;
                    bufferSide.set(index_to, sideToFlip.get(index_from));
                }

                switch (side) {
                    case "Front":
                        Front = bufferSide;
                        break;
                    case "Back":
                        Back = bufferSide;
                        break;
                    case "Top":
                        Top = bufferSide;
                        break;
                    case "Bottom":
                        Bottom = bufferSide;
                        break;
                    case "Left":
                        Left = bufferSide;
                        break;
                    case "Right":
                        Right = bufferSide;
                        break;
                }

                break;
            case "counter-clockwise":

                bufferSide = new ArrayList<>();
                for (int i = 0; i < cubeSize * cubeSize; i++) bufferSide.add("");
                for (int c = 0, i = 0, j = 0; c < cubeSize * cubeSize; c++, i = c / cubeSize, j = c % cubeSize) {
                    int index_from = i * cubeSize + j;
                    int index_to = cubeSize * (cubeSize - j - 1) + i;
                    bufferSide.set(index_to, sideToFlip.get(index_from));
                }

                switch (side) {
                    case "Front":
                        Front = bufferSide;
                        break;
                    case "Back":
                        Back = bufferSide;
                        break;
                    case "Top":
                        Top = bufferSide;
                        break;
                    case "Bottom":
                        Bottom = bufferSide;
                        break;
                    case "Left":
                        Left = bufferSide;
                        break;
                    case "Right":
                        Right = bufferSide;
                        break;
                }
                break;
        }

    }


    private void rotateSide(String turnDirection, Integer turnNumber) {


        if (turnDirection.equals("U") || turnDirection.equals("D")) {

            ArrayList<String> buffer = new ArrayList<>();
            for (int i = turnNumber; i <= turnNumber + cubeSize * (cubeSize - 1); i += cubeSize) {
                buffer.add(Front.get(i));
            }

            if (turnDirection.equals("U")) {

                for (int i = turnNumber; i <= turnNumber + cubeSize * (cubeSize - 1); i += cubeSize) {
                    Front.set(i, Bottom.get(i));
                }
                for (int i = turnNumber; i <= turnNumber + cubeSize * (cubeSize - 1); i += cubeSize) {
                    Bottom.set(i, Back.get(cubeSize * (cubeSize - 1 - i / cubeSize) +
                            cubeSize - 1 - i % cubeSize));
                }
                for (int i = turnNumber; i <= turnNumber + cubeSize * (cubeSize - 1); i += cubeSize) {
                    Back.set(cubeSize * (i / cubeSize + 1) - i % cubeSize - 1,
                            Top.get(cubeSize * (cubeSize - 1 - i / cubeSize) + i % cubeSize));
                }
                for (int i = turnNumber, j = 0; i <= turnNumber + cubeSize * (cubeSize - 1); i += cubeSize, j++) {
                    Top.set(i, buffer.get(j));
                }

                if (turnNumber == 0) this.flipSide("Left", "counter-clockwise");
                if (turnNumber == cubeSize - 1) this.flipSide("Right", "clockwise");

            }

            if (turnDirection.equals("D")) {

                for (int i = turnNumber; i <= turnNumber + cubeSize * (cubeSize - 1); i += cubeSize) {
                    Front.set(i, Top.get(i));
                }
                for (int i = turnNumber; i <= turnNumber + cubeSize * (cubeSize - 1); i += cubeSize) {
                    Top.set(i, Back.get(cubeSize * (cubeSize - 1 - i / cubeSize) +
                            cubeSize - 1 - i % cubeSize));
                }
                for (int i = turnNumber; i <= turnNumber + cubeSize * (cubeSize - 1); i += cubeSize) {
                    Back.set(cubeSize * (i / cubeSize + 1) - i % cubeSize - 1,
                            Bottom.get(cubeSize * (cubeSize - 1 - i / cubeSize) + i % cubeSize));
                }
                for (int i = turnNumber, j = 0; i <= turnNumber + cubeSize * (cubeSize - 1); i += cubeSize, j++) {
                    Bottom.set(i, buffer.get(j));
                }

                if (turnNumber == 0) this.flipSide("Left", "clockwise");
                if (turnNumber == cubeSize - 1) this.flipSide("Right", "counter-clockwise");
            }

        }

        if (turnDirection.equals("L") || turnDirection.equals("R")) {


            ArrayList<String> buffer = new ArrayList<>();
            for (int i = turnNumber * cubeSize; i <= (turnNumber + 1) * cubeSize - 1; i++) {
                buffer.add(Front.get(i));
            }

            if (turnDirection.equals("L")) {

                for (int i = turnNumber * cubeSize; i <= (turnNumber + 1) * cubeSize - 1; i++) {
                    Front.set(i, Right.get(i));
                }
                for (int i = turnNumber * cubeSize; i <= (turnNumber + 1) * cubeSize - 1; i++) {
                    Right.set(i, Back.get(i));
                }
                for (int i = turnNumber * cubeSize; i <= (turnNumber + 1) * cubeSize - 1; i++) {
                    Back.set(i, Left.get(i));
                }
                for (int i = turnNumber * cubeSize, j = 0; i <= (turnNumber + 1) * cubeSize - 1; i++, j++) {
                    Left.set(i, buffer.get(j));
                }


                if (turnNumber == 0) this.flipSide("Top", "clockwise");
                if (turnNumber == cubeSize - 1) this.flipSide("Bottom", "counter-clockwise");

            }

            if (turnDirection.equals("R")) {

                for (int i = turnNumber * cubeSize; i <= (turnNumber + 1) * cubeSize - 1; i++) {
                    Front.set(i, Left.get(i));
                }
                for (int i = turnNumber * cubeSize; i <= (turnNumber + 1) * cubeSize - 1; i++) {
                    Left.set(i, Back.get(i));
                }
                for (int i = turnNumber * cubeSize; i <= (turnNumber + 1) * cubeSize - 1; i++) {
                    Back.set(i, Right.get(i));
                }
                for (int i = turnNumber * cubeSize, j = 0; i <= (turnNumber + 1) * cubeSize - 1; i++, j++) {
                    Right.set(i, buffer.get(j));
                }


                if (turnNumber == 0) this.flipSide("Top", "counter-clockwise");
                if (turnNumber == cubeSize - 1) this.flipSide("Bottom", "clockwise");
            }

        }

        if (turnDirection.equals("CW") || turnDirection.equals("CCW")) {

            ArrayList<String> buffer = new ArrayList<>();
            for (int i = (cubeSize - 1) * cubeSize; i <= cubeSize * cubeSize - 1; i++) {
                buffer.add(Top.get(i));
            }

            if (turnDirection.equals("CW")) {

                for (int i = (cubeSize - 1) * cubeSize, j = cubeSize * cubeSize - 1; i <= cubeSize * cubeSize - 1; i++, j -= cubeSize) {
                    Top.set(i, Left.get(j));
                }
                for (int i = cubeSize - 1, j = 0; i <= cubeSize * cubeSize - 1; i += cubeSize, j++) {
                    Left.set(i, Bottom.get(j));
                }
                for (int i = 0, j = cubeSize * (cubeSize - 1); i <= cubeSize - 1; i++, j -= cubeSize) {
                    Bottom.set(i, Right.get(j));
                }
                for (int i = 0, j = 0; i <= cubeSize * (cubeSize - 1); j++, i += cubeSize) {
                    Right.set(i, buffer.get(j));
                }

                this.flipSide("Front", "clockwise");
            }

            if (turnDirection.equals("CCW")) {

                for (int i = (cubeSize - 1) * cubeSize, j = 0; i <= cubeSize * cubeSize - 1; i++, j += cubeSize) {
                    Top.set(i, Right.get(j));
                }
                for (int i = 0, j = cubeSize - 1; i <= cubeSize * (cubeSize - 1); i += cubeSize, j--) {
                    Right.set(i, Bottom.get(j));
                }
                for (int i = 0, j = cubeSize - 1; i <= cubeSize - 1; i++, j += cubeSize) {
                    Bottom.set(i, Left.get(j));
                }
                for (int i = cubeSize - 1, j = cubeSize - 1; i <= cubeSize * cubeSize - 1; j--, i += cubeSize) {
                    Left.set(i, buffer.get(j));
                }

                this.flipSide("Front", "counter-clockwise");

            }

        }
    }


    private void rotateCube(String turnDirection) {

        if (turnDirection.equals("Down")) {
            ArrayList<String> bufferSide = Front;
            Front = (ArrayList<String>) Top.clone();
            for (int c = 0, i = 0, j = 0; c < cubeSize * cubeSize; c++, i = c / cubeSize, j = c % cubeSize) {
                Top.set(i * cubeSize + j, Back.get(cubeSize * cubeSize - 1 - (i * cubeSize + j)));
            }
            for (int c = 0, i = 0, j = 0; c < cubeSize * cubeSize; c++, i = c / cubeSize, j = c % cubeSize) {
                Back.set(i * cubeSize + j, Bottom.get(cubeSize * cubeSize - 1 - (i * cubeSize + j)));
            }
            Bottom = bufferSide;

            this.flipSide("Left", "clockwise");
            this.flipSide("Right", "counter-clockwise");

        } else if (turnDirection.equals("Up")) {

            ArrayList<String> bufferSide = Front;
            Front = (ArrayList<String>) Bottom.clone();
            for (int c = 0, i = 0, j = 0; c < cubeSize * cubeSize; c++, i = c / cubeSize, j = c % cubeSize) {
                Bottom.set(i * cubeSize + j, Back.get(cubeSize * cubeSize - 1 - (i * cubeSize + j)));
            }
            for (int c = 0, i = 0, j = 0; c < cubeSize * cubeSize; c++, i = c / cubeSize, j = c % cubeSize) {
                Back.set(i * cubeSize + j, Top.get(cubeSize * cubeSize - 1 - (i * cubeSize + j)));
            }
            Top = bufferSide;

            this.flipSide("Left", "counter-clockwise");
            this.flipSide("Right", "clockwise");

        } else if (turnDirection.equals("Left")) {
            ArrayList<String> bufferSide = Front;
            Front = (ArrayList<String>) Right.clone();
            Right = (ArrayList<String>) Back.clone();
            Back = (ArrayList<String>) Left.clone();
            Left = bufferSide;

            this.flipSide("Bottom", "counter-clockwise");
            this.flipSide("Top", "clockwise");

        } else if (turnDirection.equals("Right")) {
            ArrayList<String> bufferSide = Front;
            Front = (ArrayList<String>) Left.clone();
            Left = (ArrayList<String>) Back.clone();
            Back = (ArrayList<String>) Right.clone();
            Right = bufferSide;

            this.flipSide("Top", "counter-clockwise");
            this.flipSide("Bottom", "clockwise");

        }

    }

    void interactWithCube (ArrayList<String> rotationSequence) {
        for (String rotation : rotationSequence) {
            Pattern rowNumberPattern = Pattern.compile("\\d+");
            Matcher rowNumberMatcher = rowNumberPattern.matcher(rotation);
            Integer rowNumber = 0;
            while (rowNumberMatcher.find()) {
                rowNumber = Integer.valueOf(rowNumberMatcher.group());
            }

            Pattern turnDirectionPattern = Pattern.compile("\\D+");
            Matcher turnDirectionMatcher = turnDirectionPattern.matcher(rotation);
            String turnDirection = "";
            while (turnDirectionMatcher.find()) {
                turnDirection = turnDirectionMatcher.group();
            }

            this.rotateSide(turnDirection, rowNumber);
            this.rotateCube(turnDirection);
        }
    }

    void shuffleCube() {
        int numberOfActions = cubeSize * 10 + (int) (Math.random() * (cubeSize) + 5);
        String generatedAction;
        ArrayList<String> actionSequence = new ArrayList<>();
        for (int i = 0; i < numberOfActions; i++) {
            generatedAction = "";
            switch ((int) (Math.random() * 10)) {
                case 0:
                    generatedAction += "U";
                    break;
                case 1:
                    generatedAction += "D";
                    break;
                case 2:
                    generatedAction += "L";
                    break;
                case 3:
                    generatedAction += "R";
                    break;
                case 4:
                    generatedAction += "CW";
                    break;
                case 5:
                    generatedAction += "CCW";
                    break;
                case 6:
                    generatedAction += "Up";
                    break;
                case 7:
                    generatedAction += "Down";
                    break;
                case 8:
                    generatedAction += "Left";
                    break;
                case 9:
                    generatedAction += "Right";
                    break;
            }
            generatedAction += (int) (Math.random() * (cubeSize));
            actionSequence.add(generatedAction);
        }
        System.out.println(actionSequence);
        this.interactWithCube(actionSequence);
    }
}


