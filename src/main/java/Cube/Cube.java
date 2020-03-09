package Cube;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Math.random;


final class Cube {

    enum outputMode {
        Letters,
        Colors,
    }

    void changeOutputMode() {
        if (Mode == outputMode.Letters) {
            Mode = outputMode.Colors;
        } else {
            Mode = outputMode.Letters;
        }
    }


    enum Type {
        FrontSide,
        BackSide,
        TopSide,
        BottomSide,
        LeftSide,
        RightSide,
    }


    public enum Direction {
        Left,
        Right,
        Up,
        Down,
        Clockwise,
        CounterClockwise,
        CubeLeft,
        CubeRight,
        CubeUp,
        CubeDown,
    }

    private Direction directionFromString(String enteredSide) {
        switch (enteredSide) {
            case "U":
                return Direction.Up;
            case "D":
                return Direction.Down;
            case "L":
                return Direction.Left;
            case "R":
                return Direction.Right;
            case "CW":
                return Direction.Clockwise;
            case "CCW":
                return Direction.CounterClockwise;
            case "Up":
                return Direction.CubeUp;
            case "Down":
                return Direction.CubeDown;
            case "Left":
                return Direction.CubeLeft;
            case "Right":
                return Direction.CubeRight;
            default:
                return null;
        }
    }


    public enum Colors {
        reset("\u001B[0m"),
        Red("\u001B[38;5;196m"),
        Green("\u001B[38;5;40m"),
        Yellow("\u001B[38;5;11m"),
        Blue("\u001B[38;5;27m"),
        Orange("\u001B[38;5;202m"),
        White("\u001B[38;5;255m"),
        bgRed("\u001B[48;5;196m"),
        bgGreen("\u001B[48;5;40m"),
        bgYellow("\u001B[48;5;11m"),
        bgBlue("\u001B[48;5;27m"),
        bgOrange("\u001B[48;5;202m"),
        bgWhite("\u001B[48;5;255m");

        String Color;

        Colors(String enteredColor) {
            this.Color = enteredColor;
        }
    }

    static String setColor(Colors enteredColorName) {
        return enteredColorName.Color;
    }

    static String resetColor() {
        return Colors.reset.Color;
    }


    public static class Cell {

        String Lit;
        Colors Color;
        Colors bgColor;

        Cell(String enteredLit) {
            Lit = enteredLit;
            switch (Lit) {
                case "W":
                    Color = Colors.White;
                    bgColor = Colors.bgWhite;
                    break;
                case "Y":
                    Color = Colors.Yellow;
                    bgColor = Colors.bgYellow;
                    break;
                case "G":
                    Color = Colors.Green;
                    bgColor = Colors.bgGreen;
                    break;
                case "B":
                    Color = Colors.Blue;
                    bgColor = Colors.bgBlue;
                    break;
                case "R":
                    Color = Colors.Red;
                    bgColor = Colors.bgRed;
                    break;
                case "O":
                    Color = Colors.Orange;
                    bgColor = Colors.bgOrange;
                    break;
            }
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;
            if (obj == this) return true;
            if (getClass() != obj.getClass())
                return false;
            final Cell other = (Cell) obj;
            return this.Lit.equals(other.Lit);
        }
    }

    static List<Cell> stringToCells(List<String> listOfString) {
        List<Cell> result = new ArrayList<>();
        for (String symbol : listOfString) {
            result.add(new Cell(symbol));
        }
        return result;
    }


    final class Side {

        List<Cell> sideState;
        Type sideType;

        Side(Type enteredType) {
            sideState = new ArrayList<>();
            sideType = enteredType;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;
            if (obj == this) return true;
            if (getClass() != obj.getClass())
                return false;
            final Side other = (Side) obj;
            for (int i = 0; i < cubeSize * cubeSize; i++) {
                if (!(this.sideState.get(i).equals(other.sideState.get(i)))) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public String toString() {
            StringBuilder result = new StringBuilder();
            switch (this.sideType) {
                case FrontSide:
                    result.append("Front:\n");
                    break;
                case BackSide:
                    result.append("Back:\n");
                    break;
                case TopSide:
                    result.append("Top:\n");
                    break;
                case BottomSide:
                    result.append("Bottom:\n");
                    break;
                case LeftSide:
                    result.append("Left:\n");
                    break;
                case RightSide:
                    result.append("Right:\n");
                    break;

            }
            int c = 0;
            for (Cell cell : this.sideState) {
                if (c == cubeSize) {
                    c = 0;
                    result.append("\n");
                }
                if (Mode == outputMode.Letters) {
                    result.append(setColor(cell.Color)).append(cell.Lit).append(resetColor()).append(" ");
                } else {
                    result.append(setColor(cell.bgColor)).append("   ").append(resetColor());
                }
                c++;
            }
            return result.toString();
        }
    }

    List<Side> sideFromString(String enteredSide) {
        List<Side> result = new ArrayList<>();
        switch (enteredSide) {
            case "Front":
                result.add(Front);
                break;
            case "Back":
                result.add(Back);
                break;
            case "Top":
                result.add(Top);
                break;
            case "Bottom":
                result.add(Bottom);
                break;
            case "Left":
                result.add(Left);
                break;
            case "Right":
                result.add(Right);
                break;
            case "All":
                result.add(Front);
                result.add(Back);
                result.add(Top);
                result.add(Bottom);
                result.add(Left);
                result.add(Right);
                break;
            default:
                return null;
        }
        return result;
    }


    private int cubeSize;
    Side Front = new Side(Type.FrontSide);
    Side Back = new Side(Type.BackSide);
    Side Top = new Side(Type.TopSide);
    Side Bottom = new Side(Type.BottomSide);
    Side Left = new Side(Type.LeftSide);
    Side Right = new Side(Type.RightSide);
    outputMode Mode;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        final Cube other = (Cube) obj;
        if (getClass() != obj.getClass())
            return false;
        return this.Front.equals(other.Front) && this.Back.equals(other.Back) && this.Top.equals(other.Top) &&
                this.Bottom.equals(other.Bottom) && this.Left.equals(other.Left) && this.Right.equals(other.Right);
    }

    Cube(int enteredSize) {
        if (enteredSize <= 0) {
            throw new IllegalArgumentException("");
        }
        cubeSize = enteredSize;
        Mode = outputMode.Letters;
        for (int i = 0; i < cubeSize * cubeSize; i++) {
            Front.sideState.add(new Cell("W"));
            Back.sideState.add(new Cell("Y"));
            Top.sideState.add(new Cell("G"));
            Bottom.sideState.add(new Cell("B"));
            Left.sideState.add(new Cell("R"));
            Right.sideState.add(new Cell("O"));
        }
    }

    private void flipSide(Side side, Direction rotationDirection) {
        ArrayList<Cell> bufferSide = new ArrayList<>();
        for (int i = 0; i < cubeSize * cubeSize; i++) bufferSide.add(new Cell(""));
        switch (rotationDirection) {
            case Clockwise:
                for (int c = 0, i = 0, j = 0; c < cubeSize * cubeSize; c++, i = c / cubeSize, j = c % cubeSize) {
                    int index_from = i * cubeSize + j;
                    int index_to = j * cubeSize + cubeSize - i - 1;
                    bufferSide.set(index_to, side.sideState.get(index_from));
                }
                break;

            case CounterClockwise:
                for (int c = 0, i = 0, j = 0; c < cubeSize * cubeSize; c++, i = c / cubeSize, j = c % cubeSize) {
                    int index_from = i * cubeSize + j;
                    int index_to = cubeSize * (cubeSize - j - 1) + i;
                    bufferSide.set(index_to, side.sideState.get(index_from));
                }
                break;
        }
        side.sideState = bufferSide;
    }

    private void rotateSide(Direction turnDirection, Integer turnNumber) {
        List<Cell> buffer;
        switch (turnDirection) {
            case Up:
                buffer = new ArrayList<>();
                for (int i = turnNumber; i <= turnNumber + cubeSize * (cubeSize - 1); i += cubeSize) {
                    buffer.add(Front.sideState.get(i));
                }
                for (int i = turnNumber; i <= turnNumber + cubeSize * (cubeSize - 1); i += cubeSize) {
                    Front.sideState.set(i, Bottom.sideState.get(i));
                }
                for (int i = turnNumber; i <= turnNumber + cubeSize * (cubeSize - 1); i += cubeSize) {
                    Bottom.sideState.set(i, Back.sideState.get(cubeSize * (cubeSize - 1 - i / cubeSize) +
                            cubeSize - 1 - i % cubeSize));
                }
                for (int i = turnNumber; i <= turnNumber + cubeSize * (cubeSize - 1); i += cubeSize) {
                    Back.sideState.set(cubeSize * (i / cubeSize + 1) - i % cubeSize - 1,
                            Top.sideState.get(cubeSize * (cubeSize - 1 - i / cubeSize) + i % cubeSize));
                }
                for (int i = turnNumber, j = 0; i <= turnNumber + cubeSize * (cubeSize - 1); i += cubeSize, j++) {
                    Top.sideState.set(i, buffer.get(j));
                }

                if (turnNumber == 0) this.flipSide(Left, Direction.CounterClockwise);
                if (turnNumber == cubeSize - 1) this.flipSide(Right, Direction.Clockwise);

                break;

            case Down:
                buffer = new ArrayList<>();
                for (int i = turnNumber; i <= turnNumber + cubeSize * (cubeSize - 1); i += cubeSize) {
                    buffer.add(Front.sideState.get(i));
                }
                for (int i = turnNumber; i <= turnNumber + cubeSize * (cubeSize - 1); i += cubeSize) {
                    Front.sideState.set(i, Top.sideState.get(i));
                }
                for (int i = turnNumber; i <= turnNumber + cubeSize * (cubeSize - 1); i += cubeSize) {
                    Top.sideState.set(i, Back.sideState.get(cubeSize * (cubeSize - 1 - i / cubeSize) +
                            cubeSize - 1 - i % cubeSize));
                }
                for (int i = turnNumber; i <= turnNumber + cubeSize * (cubeSize - 1); i += cubeSize) {
                    Back.sideState.set(cubeSize * (i / cubeSize + 1) - i % cubeSize - 1,
                            Bottom.sideState.get(cubeSize * (cubeSize - 1 - i / cubeSize) + i % cubeSize));
                }
                for (int i = turnNumber, j = 0; i <= turnNumber + cubeSize * (cubeSize - 1); i += cubeSize, j++) {
                    Bottom.sideState.set(i, buffer.get(j));
                }

                if (turnNumber == 0) this.flipSide(Left, Direction.Clockwise);
                if (turnNumber == cubeSize - 1) this.flipSide(Right, Direction.CounterClockwise);

                break;

            case Left:
                buffer = new ArrayList<>();
                for (int i = turnNumber * cubeSize; i <= (turnNumber + 1) * cubeSize - 1; i++) {
                    buffer.add(Front.sideState.get(i));
                }
                for (int i = turnNumber * cubeSize; i <= (turnNumber + 1) * cubeSize - 1; i++) {
                    Front.sideState.set(i, Right.sideState.get(i));
                }
                for (int i = turnNumber * cubeSize; i <= (turnNumber + 1) * cubeSize - 1; i++) {
                    Right.sideState.set(i, Back.sideState.get(i));
                }
                for (int i = turnNumber * cubeSize; i <= (turnNumber + 1) * cubeSize - 1; i++) {
                    Back.sideState.set(i, Left.sideState.get(i));
                }
                for (int i = turnNumber * cubeSize, j = 0; i <= (turnNumber + 1) * cubeSize - 1; i++, j++) {
                    Left.sideState.set(i, buffer.get(j));
                }

                if (turnNumber == 0) this.flipSide(Top, Direction.Clockwise);
                if (turnNumber == cubeSize - 1) this.flipSide(Bottom, Direction.CounterClockwise);

                break;

            case Right:
                buffer = new ArrayList<>();
                for (int i = turnNumber * cubeSize; i <= (turnNumber + 1) * cubeSize - 1; i++) {
                    buffer.add(Front.sideState.get(i));
                }
                for (int i = turnNumber * cubeSize; i <= (turnNumber + 1) * cubeSize - 1; i++) {
                    Front.sideState.set(i, Left.sideState.get(i));
                }
                for (int i = turnNumber * cubeSize; i <= (turnNumber + 1) * cubeSize - 1; i++) {
                    Left.sideState.set(i, Back.sideState.get(i));
                }
                for (int i = turnNumber * cubeSize; i <= (turnNumber + 1) * cubeSize - 1; i++) {
                    Back.sideState.set(i, Right.sideState.get(i));
                }
                for (int i = turnNumber * cubeSize, j = 0; i <= (turnNumber + 1) * cubeSize - 1; i++, j++) {
                    Right.sideState.set(i, buffer.get(j));
                }

                if (turnNumber == 0) this.flipSide(Top, Direction.CounterClockwise);
                if (turnNumber == cubeSize - 1) this.flipSide(Bottom, Direction.Clockwise);

                break;

            case Clockwise:
                buffer = new ArrayList<>();
                for (int i = (cubeSize - 1) * cubeSize; i <= cubeSize * cubeSize - 1; i++) {
                    buffer.add(Top.sideState.get(i));
                }
                for (int i = (cubeSize - 1) * cubeSize, j = cubeSize * cubeSize - 1;
                     i <= cubeSize * cubeSize - 1; i++, j -= cubeSize) {
                    Top.sideState.set(i, Left.sideState.get(j));
                }
                for (int i = cubeSize - 1, j = 0; i <= cubeSize * cubeSize - 1; i += cubeSize, j++) {
                    Left.sideState.set(i, Bottom.sideState.get(j));
                }
                for (int i = 0, j = cubeSize * (cubeSize - 1); i <= cubeSize - 1; i++, j -= cubeSize) {
                    Bottom.sideState.set(i, Right.sideState.get(j));
                }
                for (int i = 0, j = 0; i <= cubeSize * (cubeSize - 1); j++, i += cubeSize) {
                    Right.sideState.set(i, buffer.get(j));
                }

                this.flipSide(Front, Direction.Clockwise);

                break;

            case CounterClockwise:
                buffer = new ArrayList<>();
                for (int i = (cubeSize - 1) * cubeSize; i <= cubeSize * cubeSize - 1; i++) {
                    buffer.add(Top.sideState.get(i));
                }
                for (int i = (cubeSize - 1) * cubeSize, j = 0; i <= cubeSize * cubeSize - 1; i++, j += cubeSize) {
                    Top.sideState.set(i, Right.sideState.get(j));
                }
                for (int i = 0, j = cubeSize - 1; i <= cubeSize * (cubeSize - 1); i += cubeSize, j--) {
                    Right.sideState.set(i, Bottom.sideState.get(j));
                }
                for (int i = 0, j = cubeSize - 1; i <= cubeSize - 1; i++, j += cubeSize) {
                    Bottom.sideState.set(i, Left.sideState.get(j));
                }
                for (int i = cubeSize - 1, j = cubeSize - 1; i <= cubeSize * cubeSize - 1; j--, i += cubeSize) {
                    Left.sideState.set(i, buffer.get(j));
                }

                this.flipSide(Front, Direction.CounterClockwise);

                break;
        }
    }

    private void rotateCube(Direction turnDirection) {
        List<Cell> bufferSide = new ArrayList<>();
        for (int i = 0; i < cubeSize * cubeSize; i++) {
            bufferSide.add(new Cell(""));
        }
        switch (turnDirection) {

            case CubeDown: {
                Collections.copy(bufferSide, Front.sideState);
                Collections.copy(Front.sideState, Top.sideState);

                for (int c = 0, i = 0, j = 0; c < cubeSize * cubeSize; c++, i = c / cubeSize, j = c % cubeSize) {
                    Top.sideState.set(i * cubeSize + j,
                            Back.sideState.get(cubeSize * cubeSize - 1 - (i * cubeSize + j)));
                }

                for (int c = 0, i = 0, j = 0; c < cubeSize * cubeSize; c++, i = c / cubeSize, j = c % cubeSize) {
                    Back.sideState.set(i * cubeSize + j,
                            Bottom.sideState.get(cubeSize * cubeSize - 1 - (i * cubeSize + j)));
                }

                Collections.copy(Bottom.sideState, bufferSide);

                this.flipSide(Left, Direction.Clockwise);
                this.flipSide(Right, Direction.CounterClockwise);

                break;
            }
            case CubeUp: {
                Collections.copy(bufferSide, Front.sideState);
                Collections.copy(Front.sideState, Bottom.sideState);

                for (int c = 0, i = 0, j = 0; c < cubeSize * cubeSize; c++, i = c / cubeSize, j = c % cubeSize) {
                    Bottom.sideState.set(i * cubeSize + j,
                            Back.sideState.get(cubeSize * cubeSize - 1 - (i * cubeSize + j)));
                }

                for (int c = 0, i = 0, j = 0; c < cubeSize * cubeSize; c++, i = c / cubeSize, j = c % cubeSize) {
                    Back.sideState.set(i * cubeSize + j,
                            Top.sideState.get(cubeSize * cubeSize - 1 - (i * cubeSize + j)));
                }

                Collections.copy(Top.sideState, bufferSide);

                this.flipSide(Left, Direction.CounterClockwise);
                this.flipSide(Right, Direction.Clockwise);

                break;
            }
            case CubeLeft: {
                Collections.copy(bufferSide, Front.sideState);
                Collections.copy(Front.sideState, Right.sideState);
                Collections.copy(Right.sideState, Back.sideState);
                Collections.copy(Back.sideState, Left.sideState);
                Collections.copy(Left.sideState, bufferSide);

                this.flipSide(Bottom, Direction.CounterClockwise);
                this.flipSide(Top, Direction.Clockwise);

                break;
            }
            case CubeRight: {
                Collections.copy(bufferSide, Front.sideState);
                Collections.copy(Front.sideState, Left.sideState);
                Collections.copy(Left.sideState, Back.sideState);
                Collections.copy(Back.sideState, Right.sideState);
                Collections.copy(Right.sideState, bufferSide);

                this.flipSide(Top, Direction.CounterClockwise);
                this.flipSide(Bottom, Direction.Clockwise);

                break;
            }
        }
    }

    void interactWithCube(ArrayList<String> rotationSequence) {
        for (String rotation : rotationSequence) {
            Pattern rowNumberPattern = Pattern.compile("\\d+");
            Matcher rowNumberMatcher = rowNumberPattern.matcher(rotation);
            Integer rowNumber = null;
            while (rowNumberMatcher.find()) {
                rowNumber = Integer.parseInt(rowNumberMatcher.group());
            }
            Pattern turnDirectionPattern = Pattern.compile("\\D+");
            Matcher turnDirectionMatcher = turnDirectionPattern.matcher(rotation);
            String turnDirection = "";
            while (turnDirectionMatcher.find()) {
                turnDirection = turnDirectionMatcher.group();
            }

            if (directionFromString(turnDirection) != null &&
                    (rowNumber != null && rowNumber >= 0 && rowNumber < cubeSize ||
                            directionFromString(turnDirection).equals(Direction.Clockwise) ||
                            directionFromString(turnDirection).equals(Direction.CounterClockwise) ||
                            directionFromString(turnDirection).equals(Direction.CubeDown) ||
                            directionFromString(turnDirection).equals(Direction.CubeLeft) ||
                            directionFromString(turnDirection).equals(Direction.CubeRight) ||
                            directionFromString(turnDirection).equals(Direction.CubeUp))) {
                this.rotateSide(Objects.requireNonNull(directionFromString(turnDirection)), rowNumber);
                this.rotateCube(directionFromString(turnDirection));
            } else throw new IllegalArgumentException();
        }
    }

    void shuffleCube() {
        int numberOfActions = cubeSize * 10;
        StringBuilder generatedAction = new StringBuilder();
        ArrayList<String> actionSequence = new ArrayList<>();
        List<String> allActions = new ArrayList<>(Arrays.asList("U", "D", "L", "R", "CW", "CCW", "Up", "Down", "Left", "Right"));
        for (int i = 0; i < numberOfActions; i++) {
            generatedAction.append(allActions.get((int) (Math.random() * 10))).append((int) (random() * (cubeSize)));
            actionSequence.add(generatedAction.toString());
        }
        this.interactWithCube(actionSequence);
    }
}
