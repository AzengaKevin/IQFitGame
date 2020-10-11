package comp1110.ass2;

import java.awt.*;

public enum Shape {

    BLOWER(4, 2, new char[][]{{'b', 'b'}, {'b', '_'}, {'b', '_'}, {'b', '_'}}, Colour.BLUE, "B1.png"),
    BUPPER(4, 2, new char[][]{{'b', '_'}, {'b', 'b'}, {'b', '_'}, {'b', 'b'}}, Colour.BLUE, "B2.png"),
    GLOWER(3, 2, new char[][]{{'g','_'},{'g','g'},{'g','_'}}, Colour.GREEN, "G1.png"),
    GUPPER(3, 2, new char[][]{{'g','g'},{'g','g'},{'g','_'}}, Colour.GREEN, "G2.png"),
    ILOWER(3, 2, new char[][]{{'i','_'},{'i','_'},{'i','i'}}, Colour.INDIGO, "I1.png"),
    IUPPER(3, 2, new char[][]{{'i','_'},{'i','i'},{'i','i'}}, Colour.INDIGO, "I2.png"),
    LLOWER(3, 2, new char[][]{{'l','l'},{'l','_'},{'l','_'}}, Colour.LIME, "L1.png"),
    LUPPER(3, 2, new char[][]{{'l','l'},{'l','_'},{'l','l'}},Colour.LIME, "L2.png"),
    NLOWER(3, 2, new char[][]{{'n','_'},{'n','n'},{'n','_'}}, Colour.NAVY, "N1.png"),
    NUPPER(3, 2, new char[][]{{'n','n'},{'n','_'},{'n','n'}}, Colour.NAVY, "N2.png"),
    OLOWER(4, 2, new char[][]{{'o','_'},{'o','o'},{'o','_'},{'o','_'}}, Colour.ORANGE, "O1.png"),
    OUPPER(4, 2, new char[][]{{'o','o'},{'o','_'},{'o','o'},{'o','_'}}, Colour.ORANGE, "O2.png"),
    PLOWER(4, 2, new char[][]{{'p','_'},{'p','_'},{'p','p'},{'p','_'}}, Colour.PINK, "P1.png"),
    PUPPER(4, 2, new char[][]{{'p','p'},{'p','p'},{'p','_'},{'p','_'}}, Colour.PINK, "P2.png"),
    RLOWER(4, 2, new char[][]{{'r','r'},{'r','_'},{'r','_'},{'r','_'}}, Colour.RED, "R1.png"),
    RUPPER(4, 2, new char[][]{{'r','r'},{'r','_'},{'r','_'},{'r','r'}}, Colour.RED, "R2.png"),
    SLOWER(4, 2, new char[][]{{'s','_'},{'s','s'},{'s','_'},{'s','_'}}, Colour.SKYBLUE, "S1.png"),
    SUPPER(4, 2, new char[][]{{'s','_'},{'s','s'},{'s','s'},{'s','_'}}, Colour.SKYBLUE, "S2.png"),
    YLOWER(4, 2, new char[][]{{'y','_'},{'y','_'},{'y','_'},{'y','y'}}, Colour.YELLOW, "Y1.png"),
    YUPPER(4, 2, new char[][]{{'y','_'},{'y','_'},{'y','y'},{'y','y'}}, Colour.YELLOW, "Y2.png");

    private final int width;
    private final int height;
    private final char[][] structure;
    private final Colour colour;
    private final String filename;

    Shape(int width, int height, char[][] structure, Colour colour, String filename) {
        this.width = width;
        this.height = height;
        this.structure = structure;
        this.colour = colour;
        this.filename = filename;
    }

    public static Shape fromPiecePlacement(String piecePlacement) {
        String flip = piecePlacement.substring(0, 1);
        String shapeName;

        if (flip.toLowerCase().equals(flip))
            shapeName = flip.toUpperCase() + "LOWER";
        else
            shapeName = flip + "UPPER";

        return Enum.valueOf(Shape.class, shapeName);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public char[][] getStructure() {
        return structure;
    }

    public Colour getColour() {
        return colour;
    }

    public String getFilename() {
        return filename;
    }

    public Shape flip() {
        if (this.toString().substring(1).equals("LOWER"))
            return Enum.valueOf(Shape.class, this.toString().substring(0, 1) + "UPPER");

        return Enum.valueOf(Shape.class, this.toString().substring(0, 1) + "LOWER");
    }

    public char[][] getStructureFromOrientation (Orientation dir){
        switch (dir){
            case EAST:
                char[][] newStructureEast = new char[height][width];
                for (int x = 0; x < width; x++)
                    for(int y = 0; y < height; y++)
                        newStructureEast[height - 1 - y][x] = structure[x][y];
                return newStructureEast;

            case SOUTH:
                char[][] newStructureSouth = new char[width][height];
                for (int x = 0; x < width; x++)
                    for(int y = 0; y < height; y++)
                        newStructureSouth[width - 1 - x][height - 1 - y] = structure[x][y];
                return newStructureSouth;

            case WEST:
                char[][] newStructureWest = new char[height][width];
                for (int x = 0; x < width; x++)
                    for(int y = 0; y < height; y++)
                    newStructureWest[y][width - 1 - x] = structure[x][y];
                return newStructureWest;

            default:
                return structure;
        }
    }
}