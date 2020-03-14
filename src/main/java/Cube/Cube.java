package Cube;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Math.random;


final class Cube {

    private enum outputMode {
        Letters,
        Colors,
    }

    public void changeOutputMode() {
        if (Mode == outputMode.Letters) {
            System.out.println("Changed: Colors ---> Letters");
            Mode = outputMode.Colors;
        } else {
            System.out.println("Changed: Letters ---> Colors");
            Mode = outputMode.Letters;
        }
    }


    private enum Type {
        FrontSide,
        BackSide,
        TopSide,
        BottomSide,
        LeftSide,
        RightSide,
    }


    private enum Direction {
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

    public static String setColor(Colors enteredColorName) {
        return enteredColorName.Color;
    }

    public static String resetColor() {
        return Colors.reset.Color;
    }


    private static class Cell {

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

    public static List<Cell> stringToCells(List<String> listOfString) {
        List<Cell> result = new ArrayList<>();
        for (String symbol : listOfString) {
            result.add(new Cell(symbol));
        }
        return result;
    }


    public final class Side {

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
                    result.append("Front:").append(System.lineSeparator());
                    break;
                case BackSide:
                    result.append("Back:").append(System.lineSeparator());
                    break;
                case TopSide:
                    result.append("Top:").append(System.lineSeparator());
                    break;
                case BottomSide:
                    result.append("Bottom:").append(System.lineSeparator());
                    break;
                case LeftSide:
                    result.append("Left:").append(System.lineSeparator());
                    break;
                case RightSide:
                    result.append("Right:").append(System.lineSeparator());
                    break;

            }
            int c = 0;
            for (Cell cell : this.sideState) {
                if (c == cubeSize) {
                    c = 0;
                    result.append(System.lineSeparator());
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

    public List<Side> sideFromString(String enteredSide) {
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
    private outputMode Mode;

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
        Mode = outputMode.Colors;
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
        List<Cell> phantomFront = new ArrayList<>();
        List<Cell> phantomBack = new ArrayList<>();
        List<Cell> phantomTop = new ArrayList<>();
        List<Cell> phantomBottom = new ArrayList<>();
        List<Cell> phantomLeft = new ArrayList<>();
        List<Cell> phantomRight = new ArrayList<>();

        for (int i = 0; i < cubeSize * cubeSize; i++) {
            phantomFront.add(new Cell(""));
            phantomBack.add(new Cell(""));
            phantomTop.add(new Cell(""));
            phantomBottom.add(new Cell(""));
            phantomLeft.add(new Cell(""));
            phantomRight.add(new Cell(""));
        }

        Collections.copy(phantomFront, Front.sideState);
        Collections.copy(phantomBack, Back.sideState);
        Collections.copy(phantomTop, Top.sideState);
        Collections.copy(phantomBottom, Bottom.sideState);
        Collections.copy(phantomLeft, Left.sideState);
        Collections.copy(phantomRight, Right.sideState);

        int row;
        int column;

        switch (turnDirection) {
            case Up:
                for (int i = turnNumber; i <= turnNumber + cubeSize * (cubeSize - 1); i += cubeSize) {
                    row = i / cubeSize;
                    column = i % cubeSize;
                    Front.sideState.set(i, phantomBottom.get(i));
                    Bottom.sideState.set(i, phantomBack.get(cubeSize * cubeSize - 1 - i));
                    Back.sideState.set(cubeSize * row + cubeSize - 1 - column, phantomTop.get((cubeSize - 1 - row) * cubeSize + column));
                    Top.sideState.set(i, phantomFront.get(i));
                }
                if (turnNumber == 0) this.flipSide(Left, Direction.CounterClockwise);
                if (turnNumber == cubeSize - 1) this.flipSide(Right, Direction.Clockwise);
                break;

            case Down:
                for (int i = turnNumber; i <= turnNumber + cubeSize * (cubeSize - 1); i += cubeSize) {
                    row = i / cubeSize;
                    column = i % cubeSize;
                    Front.sideState.set(i, phantomTop.get(i));
                    Bottom.sideState.set(i, phantomFront.get(i));
                    Back.sideState.set(cubeSize * row + cubeSize - 1 - column, phantomBottom.get((cubeSize - 1 - row) * cubeSize + column));
                    Top.sideState.set(i, phantomBack.get(cubeSize * cubeSize - 1 - i));
                }
                if (turnNumber == 0) this.flipSide(Left, Direction.Clockwise);
                if (turnNumber == cubeSize - 1) this.flipSide(Right, Direction.CounterClockwise);
                break;

            case Left:
                for (int i = turnNumber * cubeSize; i <= (turnNumber + 1) * cubeSize - 1; i++) {
                    Front.sideState.set(i, phantomRight.get(i));
                    Left.sideState.set(i, phantomFront.get(i));
                    Back.sideState.set(i, phantomLeft.get(i));
                    Right.sideState.set(i, phantomBack.get(i));
                }
                if (turnNumber == 0) this.flipSide(Top, Direction.Clockwise);
                if (turnNumber == cubeSize - 1) this.flipSide(Bottom, Direction.CounterClockwise);
                break;

            case Right:
                for (int i = turnNumber * cubeSize; i <= (turnNumber + 1) * cubeSize - 1; i++) {
                    Front.sideState.set(i, phantomLeft.get(i));
                    Right.sideState.set(i, phantomFront.get(i));
                    Back.sideState.set(i, phantomRight.get(i));
                    Left.sideState.set(i, phantomBack.get(i));
                }
                if (turnNumber == 0) this.flipSide(Top, Direction.CounterClockwise);
                if (turnNumber == cubeSize - 1) this.flipSide(Bottom, Direction.Clockwise);
                break;

            case Clockwise:
                for (int i = 0; i < cubeSize; i ++) {
                    row = i / cubeSize;
                    column = i % cubeSize;
                    Bottom.sideState.set(i, phantomRight.get((cubeSize - 1 - column) * cubeSize));
                    Right.sideState.set(i * cubeSize, phantomTop.get((cubeSize - 1 - row) * cubeSize + column));
                    Top.sideState.set(cubeSize * cubeSize - 1 - i, phantomLeft.get(column * cubeSize + cubeSize - 1));
                    Left.sideState.set(column * cubeSize + cubeSize - 1, phantomBottom.get(i));
                }
                this.flipSide(Front, Direction.Clockwise);
                break;

            case CounterClockwise:
                for (int i = 0; i < cubeSize; i ++) {
                    row = i / cubeSize;
                    column = i % cubeSize;
                    Bottom.sideState.set(i, phantomLeft.get(column * cubeSize + cubeSize - 1));
                    Right.sideState.set((cubeSize - 1 - column) * cubeSize, phantomBottom.get(i));
                    Top.sideState.set((cubeSize - 1 - row) * cubeSize + column, phantomRight.get(column * cubeSize));
                    Left.sideState.set(column * cubeSize + cubeSize - 1, phantomTop.get((cubeSize * cubeSize - 1 - i)));
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

    public void interactWithCube(List<String> rotationSequence) {
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

    public void shuffleCube() {
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
