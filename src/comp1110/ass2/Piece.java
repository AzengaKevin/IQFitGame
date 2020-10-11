
package comp1110.ass2;

public class Piece {
    private Orientation orientation;
    private Shape shape;
    private int xPos;
    private int yPos;
    private char[][] structure;

    public Piece(String piecePlacement) {
        this.orientation = Orientation.fromPiecePlacement(piecePlacement);
        this.shape = Shape.fromPiecePlacement(piecePlacement);
        this.xPos = piecePlacementToPosX(piecePlacement);
        this.yPos = piecePlacementToPosY(piecePlacement);
        this.structure = shape.getStructureFromOrientation(orientation);
    }


    private int piecePlacementToPosX(String piecePlacement) {
        return Character.getNumericValue(piecePlacement.charAt(1));
    }

    private int piecePlacementToPosY(String piecePlacement) {
        return Character.getNumericValue(piecePlacement.charAt(2));
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public Shape getShape() {
        return shape;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public int getWidth() {
        return structure.length;
    }

    public int getHeight() {
        return structure[0].length;
    }

    public char[][] getStructure(){
        return structure;
    }

    public void flip() {
        this.shape = shape.flip();
        this.structure = shape.getStructureFromOrientation(orientation);
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
        this.structure = shape.getStructureFromOrientation(orientation);
    }
}


