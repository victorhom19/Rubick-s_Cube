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
        Cube cube1 = new Cube();
        cube1.initializeCube();
        String command = "";

        while (!command.equals("Stop")) {

            System.out.println("Command: ");
            command = keyboard.next();

            switch (command) {
                case "Status":
                    System.out.println("Enter a side of cube: ");
                    String requestedSide = keyboard.next();
                    cube1.sideStatus(requestedSide);
                    break;
                case "Rotate":
                    System.out.println("Enter rotation sequence:");
                    String[] rotationSequence = keyboard.next().split(",");

                    for (int i = 0; i < rotationSequence.length; i ++) {
                        String rotation = rotationSequence[i];

                        System.out.print(rotationSequence.length);

                        Pattern rowNumberPattern = Pattern.compile("\\d+");
                        Matcher rowNumberMatcher = rowNumberPattern.matcher(rotation);
                        Integer rowNumber = 0;
                        while(rowNumberMatcher.find()) {
                            rowNumber = Integer.valueOf(rowNumberMatcher.group());
                        }

                        Pattern turnDirectionPattern = Pattern.compile("\\D+");
                        Matcher turnDirectionMatcher = turnDirectionPattern.matcher(rotation);
                        String turnDirection = "";
                        while(turnDirectionMatcher.find()) {
                            turnDirection = turnDirectionMatcher.group();
                        }

                        cube1.rotateSide(turnDirection, rowNumber);
                        cube1.rotateCube(turnDirection);
                    }
                    break;
                case "changeOutputMode":
                    System.out.print("Changed from " + cube1.Output_Mode);
                    if (cube1.Output_Mode.equals("Letters")) cube1.Output_Mode = "Colors";
                    else if (cube1.Output_Mode.equals("Colors")) cube1.Output_Mode = "Letters";
                    System.out.print(" to " + cube1.Output_Mode + "\n");
                    break;
            }
        }
    }
}

class Cube {

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

        /*Front.add("1FR");
        Front.add("2FR");
        Front.add("3FR");
        Front.add("4FR");
        Front.add("5FR");
        Front.add("6FR");
        Front.add("7FR");
        Front.add("8FR");
        Front.add("9FR");

        Back.add("1BA");
        Back.add("2BA");
        Back.add("3BA");
        Back.add("4BA");
        Back.add("5BA");
        Back.add("6BA");
        Back.add("7BA");
        Back.add("8BA");
        Back.add("9BA");

        Top.add("1TO");
        Top.add("2TO");
        Top.add("3TO");
        Top.add("4TO");
        Top.add("5TO");
        Top.add("6TO");
        Top.add("7TO");
        Top.add("8TO");
        Top.add("9TO");

        Bottom.add("1BO");
        Bottom.add("2BO");
        Bottom.add("3BO");
        Bottom.add("4BO");
        Bottom.add("5BO");
        Bottom.add("6BO");
        Bottom.add("7BO");
        Bottom.add("8BO");
        Bottom.add("9BO");

        Left.add("1LE");
        Left.add("2LE");
        Left.add("3LE");
        Left.add("4LE");
        Left.add("5LE");
        Left.add("6LE");
        Left.add("7LE");
        Left.add("8LE");
        Left.add("9LE");

        Right.add("1RI");
        Right.add("2RI");
        Right.add("3RI");
        Right.add("4RI");
        Right.add("5RI");
        Right.add("6RI");
        Right.add("7RI");
        Right.add("8RI");
        Right.add("9RI");*/

    }


    void rotateSide(String turnDirection, Integer turnNumber) {


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


                //По часовой
                if (turnNumber == cubeSize - 1) {
                    ArrayList<String> bufferSide = new ArrayList<>();
                    for (int i = 0; i < cubeSize * cubeSize; i++) bufferSide.add("");
                    for (int c = 0, i = 0, j = 0; c < cubeSize * cubeSize; c++, i = c / cubeSize, j = c % cubeSize) {
                        int index_from = i * cubeSize + j;
                        int index_to = j * cubeSize + cubeSize - i - 1;
                        bufferSide.set(index_to, Right.get(index_from));
                    }
                    Right = bufferSide;
                }

                //Против часовой
                if (turnNumber == 0) {
                    ArrayList<String> bufferSide = new ArrayList<>();
                    for (int i = 0; i < cubeSize * cubeSize; i++) bufferSide.add("");
                    for (int c = 0, i = 0, j = 0; c < cubeSize * cubeSize; c++, i = c / cubeSize, j = c % cubeSize) {
                        int index_from = i * cubeSize + j;
                        int index_to = cubeSize * (cubeSize - j - 1) + i;
                        bufferSide.set(index_to, Left.get(index_from));
                    }
                    Left = bufferSide;
                }

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


                //По часовой
                if (turnNumber == 0) {
                    ArrayList<String> bufferSide = new ArrayList<>();
                    for (int i = 0; i < cubeSize * cubeSize; i++) bufferSide.add("");
                    for (int c = 0, i = 0, j = 0; c < cubeSize * cubeSize; c++, i = c / cubeSize, j = c % cubeSize) {
                        int index_from = i * cubeSize + j;
                        int index_to = j * cubeSize + cubeSize - i - 1;
                        bufferSide.set(index_to, Left.get(index_from));
                    }
                    Left = bufferSide;
                }

                //Против часовой
                if (turnNumber == cubeSize - 1) {
                    ArrayList<String> bufferSide = new ArrayList<>();
                    for (int i = 0; i < cubeSize * cubeSize; i++) bufferSide.add("");
                    for (int c = 0, i = 0, j = 0; c < cubeSize * cubeSize; c++, i = c / cubeSize, j = c % cubeSize) {
                        int index_from = i * cubeSize + j;
                        int index_to = cubeSize * (cubeSize - j - 1) + i;
                        bufferSide.set(index_to, Right.get(index_from));
                    }
                    Right = bufferSide;
                }
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


                //По часовой
                if (turnNumber == 0) {
                    ArrayList<String> bufferSide = new ArrayList<>();
                    for (int i = 0; i < cubeSize * cubeSize; i++) bufferSide.add("");
                    for (int c = 0, i = 0, j = 0; c < cubeSize * cubeSize; c++, i = c / cubeSize, j = c % cubeSize) {
                        int index_from = i * cubeSize + j;
                        int index_to = j * cubeSize + cubeSize - i - 1;
                        bufferSide.set(index_to, Top.get(index_from));
                    }
                    Top = bufferSide;
                }

                //Против часовой
                if (turnNumber == cubeSize - 1) {
                    ArrayList<String> bufferSide = new ArrayList<>();
                    for (int i = 0; i < cubeSize * cubeSize; i++) bufferSide.add("");
                    for (int c = 0, i = 0, j = 0; c < cubeSize * cubeSize; c++, i = c / cubeSize, j = c % cubeSize) {
                        int index_from = i * cubeSize + j;
                        int index_to = cubeSize * (cubeSize - j - 1) + i;
                        bufferSide.set(index_to, Bottom.get(index_from));
                    }
                    Bottom = bufferSide;
                }

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


                //По часовой
                if (turnNumber == cubeSize - 1) {
                    ArrayList<String> bufferSide = new ArrayList<>();
                    for (int i = 0; i < cubeSize * cubeSize; i++) bufferSide.add("");
                    for (int c = 0, i = 0, j = 0; c < cubeSize * cubeSize; c++, i = c / cubeSize, j = c % cubeSize) {
                        int index_from = i * cubeSize + j;
                        int index_to = j * cubeSize + cubeSize - i - 1;
                        bufferSide.set(index_to, Bottom.get(index_from));
                    }
                    Bottom = bufferSide;
                }

                //Против часовой
                if (turnNumber == 0) {
                    ArrayList<String> bufferSide = new ArrayList<>();
                    for (int i = 0; i < cubeSize * cubeSize; i++) bufferSide.add("");
                    for (int c = 0, i = 0, j = 0; c < cubeSize * cubeSize; c++, i = c / cubeSize, j = c % cubeSize) {
                        int index_from = i * cubeSize + j;
                        int index_to = cubeSize * (cubeSize - j - 1) + i;
                        bufferSide.set(index_to, Top.get(index_from));
                    }
                    Top = bufferSide;
                }
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


                //По часовой
                ArrayList<String> bufferSide = new ArrayList<>();
                for (int i = 0; i < cubeSize * cubeSize; i++) bufferSide.add("");
                for (int c = 0, i = 0, j = 0; c < cubeSize * cubeSize; c++, i = c / cubeSize, j = c % cubeSize) {
                    int index_from = i * cubeSize + j;
                    int index_to = j * cubeSize + cubeSize - i - 1;
                    bufferSide.set(index_to, Front.get(index_from));
                }
                Front = bufferSide;


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


                //Против часовой
                ArrayList<String> bufferSide = new ArrayList<>();
                for (int i = 0; i < cubeSize * cubeSize; i++) bufferSide.add("");
                for (int c = 0, i = 0, j = 0; c < cubeSize * cubeSize; c++, i = c / cubeSize, j = c % cubeSize) {
                    int index_from = i * cubeSize + j;
                    int index_to = cubeSize * (cubeSize - j - 1) + i;
                    bufferSide.set(index_to, Front.get(index_from));
                }
                Front = bufferSide;

            }

        }
    }


    void rotateCube(String turnDirection) {

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

            //Против часовой
            bufferSide = new ArrayList<>();
            for (int i = 0; i < cubeSize * cubeSize; i++) bufferSide.add("");
            for (int c = 0, i = 0, j = 0; c < cubeSize * cubeSize; c++, i = c / cubeSize, j = c % cubeSize) {
                int index_from = i * cubeSize + j;
                int index_to = cubeSize * (cubeSize - j - 1) + i;
                bufferSide.set(index_to, Right.get(index_from));
            }
            Right = bufferSide;

            //По часовой
            bufferSide = new ArrayList<>();
            for (int i = 0; i < cubeSize * cubeSize; i++) bufferSide.add("");
            for (int c = 0, i = 0, j = 0; c < cubeSize * cubeSize; c++, i = c / cubeSize, j = c % cubeSize) {
                int index_from = i * cubeSize + j;
                int index_to = j * cubeSize + cubeSize - i - 1;
                bufferSide.set(index_to, Left.get(index_from));
            }
            Left = bufferSide;

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

            //Против часовой
            bufferSide = new ArrayList<>();
            for (int i = 0; i < cubeSize * cubeSize; i++) bufferSide.add("");
            for (int c = 0, i = 0, j = 0; c < cubeSize * cubeSize; c++, i = c / cubeSize, j = c % cubeSize) {
                int index_from = i * cubeSize + j;
                int index_to = cubeSize * (cubeSize - j - 1) + i;
                bufferSide.set(index_to, Left.get(index_from));
            }
            Left = bufferSide;

            //По часовой
            bufferSide = new ArrayList<>();
            for (int i = 0; i < cubeSize * cubeSize; i++) bufferSide.add("");
            for (int c = 0, i = 0, j = 0; c < cubeSize * cubeSize; c++, i = c / cubeSize, j = c % cubeSize) {
                int index_from = i * cubeSize + j;
                int index_to = j * cubeSize + cubeSize - i - 1;
                bufferSide.set(index_to, Right.get(index_from));
            }
            Right = bufferSide;

        } else if (turnDirection.equals("Left")) {
            ArrayList<String> bufferSide = Front;
            Front = (ArrayList<String>) Right.clone();
            Right = (ArrayList<String>) Back.clone();
            Back = (ArrayList<String>) Left.clone();
            Left = bufferSide;

            //Против часовой
            bufferSide = new ArrayList<>();
            for (int i = 0; i < cubeSize * cubeSize; i++) bufferSide.add("");
            for (int c = 0, i = 0, j = 0; c < cubeSize * cubeSize; c++, i = c / cubeSize, j = c % cubeSize) {
                int index_from = i * cubeSize + j;
                int index_to = cubeSize * (cubeSize - j - 1) + i;
                bufferSide.set(index_to, Bottom.get(index_from));
            }
            Bottom = bufferSide;

            //По часовой
            bufferSide = new ArrayList<>();
            for (int i = 0; i < cubeSize * cubeSize; i++) bufferSide.add("");
            for (int c = 0, i = 0, j = 0; c < cubeSize * cubeSize; c++, i = c / cubeSize, j = c % cubeSize) {
                int index_from = i * cubeSize + j;
                int index_to = j * cubeSize + cubeSize - i - 1;
                bufferSide.set(index_to, Top.get(index_from));
            }
            Top = bufferSide;

        } else if (turnDirection.equals("Right")) {
            ArrayList<String> bufferSide = Front;
            Front = (ArrayList<String>) Left.clone();
            Left = (ArrayList<String>) Back.clone();
            Back = (ArrayList<String>) Right.clone();
            Right = bufferSide;

            //Против часовой
            bufferSide = new ArrayList<>();
            for (int i = 0; i < cubeSize * cubeSize; i++) bufferSide.add("");
            for (int c = 0, i = 0, j = 0; c < cubeSize * cubeSize; c++, i = c / cubeSize, j = c % cubeSize) {
                int index_from = i * cubeSize + j;
                int index_to = cubeSize * (cubeSize - j - 1) + i;
                bufferSide.set(index_to, Top.get(index_from));
            }
            Top = bufferSide;

            //По часовой
            bufferSide = new ArrayList<>();
            for (int i = 0; i < cubeSize * cubeSize; i++) bufferSide.add("");
            for (int c = 0, i = 0, j = 0; c < cubeSize * cubeSize; c++, i = c / cubeSize, j = c % cubeSize) {
                int index_from = i * cubeSize + j;
                int index_to = j * cubeSize + cubeSize - i - 1;
                bufferSide.set(index_to, Bottom.get(index_from));
            }
            Bottom = bufferSide;

        }

    }
}


