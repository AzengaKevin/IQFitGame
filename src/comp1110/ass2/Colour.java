package comp1110.ass2;

public enum Colour {

    BLUE, GREEN, LIME, INDIGO, NAVY, ORANGE, PINK, RED, SKYBLUE, YELLOW;

    public char toChar() {
        if (this == GREEN) {
            return 'G';
        } else if (this == BLUE) {
            return 'B';
        } else if (this == RED) {
            return 'R';
        } else if (this == LIME) {
            return 'L';
        } else if (this == INDIGO) {
            return 'I';
        } else if (this == NAVY) {
            return 'N';
        } else if (this == ORANGE) {
            return 'O';
        } else if (this == PINK) {
            return 'P';
        } else if (this == SKYBLUE) {
            return 'S';
        } else {
            return 'Y';
        }
    }

    public static Colour toColor(char c) {
        switch (c) {
            case 'G':
                return GREEN;
            case 'B':
                return BLUE;
            case 'R':
                return RED;
            case 'L':
                return LIME;
            case 'I':
                return INDIGO;
            case 'N':
                return NAVY;
            case 'O':
                return ORANGE;
            case 'P':
                return PINK;
            case 'S':
                return SKYBLUE;
            case 'Y':
                return YELLOW;
        }
        return null;
    }

}

