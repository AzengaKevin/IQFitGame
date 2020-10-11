package comp1110.ass2;

public enum Orientation {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    public static Orientation fromPiecePlacement(String piecePlacement){
        switch (piecePlacement.charAt(3)) {
            case 'N':
                return NORTH;
            case 'E':
                return EAST;
            case 'S':
                return SOUTH;
            default:
                return WEST;
        }
    }
}
