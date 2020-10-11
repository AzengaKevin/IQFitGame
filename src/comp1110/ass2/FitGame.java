package comp1110.ass2;

import java.io.Serializable;
import java.util.*;

/**
 * This class provides the text interface for the IQ Fit Game
 * <p>
 * The game is based directly on Smart Games' IQ-Fit game
 * (https://www.smartgames.eu/uk/one-player-games/iq-fit)
 */
public class FitGame {

    /**
     * Determine whether a piece placement is well-formed according to the
     * following criteria:
     * - it consists of exactly four characters
     * - the first character is a valid piece descriptor character (b, B, g, G, ... y, Y)
     * - the second character is in the range 0 .. 9 (column)
     * - the third character is in the range 0 .. 4 (row)
     * - the fourth character is in valid orientation N, S, E, W
     *
     * @param piecePlacement A string describing a piece placement
     * @return True if the piece placement is well-formed
     */
    static boolean isPiecePlacementWellFormed(String piecePlacement) {
        if (piecePlacement.length() != 4) {
            return false;
        } else {
            //access every charater
            char char1 = piecePlacement.charAt(0);
            char char2 = piecePlacement.charAt(1);
            //turn char into int
            int c2 = char2 - '0';
            char char3 = piecePlacement.charAt(2);
            int c3 = char3 - '0';
            char char4 = piecePlacement.charAt(3);
            //check the fourth charater
            if ((char1 >= 78 && char1 <= 80) || (char1 >= 110 && char1 <= 112) || (char1 >= 82 && char1 <= 83) || (char1 >= 114 && char1 <= 115) || (char1 == 66) || (char1 == 98)
                    || (char1 == 71) || (char1 == 103) || (char1 == 73) || (char1 == 105) || (char1 == 76) || (char1 == 108)
                    || (char1 == 89) || (char1 == 121)) {
                if (c2 >= 0 && c2 <= 9) {
                    if (c3 >= 0 && c3 <= 4) {
                        return char4 == 'N' || char4 == 'S' || char4 == 'E' || char4 == 'W';
                    } else return false;
                } else return false;
            } else return false;
        }// FIXME Task 2: determine whether a piece placement is well-formed
    }

    /**
     * Determine whether a placement string is well-formed:
     * - it consists of exactly N four-character piece placements (where N = 1 .. 10);
     * - each piece placement is well-formed
     * - no shape appears more than once in the placement
     * - the pieces are ordered correctly within the string
     *
     * @param placement A string describing a placement of one or more pieces
     * @return True if the placement is well-formed
     */
    public static boolean isPlacementWellFormed(String placement) {
        //check the first situation: 4<=string <=40 and consist of 4-character piece placements
        if ((placement.length() < 4 || placement.length() > 40 || (placement.length() % 4) != 0)) {
            return false;
        } else {
            int len_place = placement.length();
            int n = len_place / 4, b = 0;
            if (n == 1) {
                return isPiecePlacementWellFormed(placement);
            } else {
                int f = 0;
                for (int k = 0; k < n; k++) {
                    if (isPiecePlacementWellFormed(placement.substring(f, f + 4))) {
                        f = f + 4;
                        b = b + 1;
                    } else
                        break;
                }
                return (b == n) && (isOrdered(placement));
            }

        }
    }// FIXME Task 3: determine whether a placement is well-formed


    /**
     * Determine whether a placement string is in correct order.
     *
     * @param placement String
     * @return boolean
     */
    public static Boolean isOrdered(String placement) {
        //to check the order, upper case and lower case will interfere this step, so make the string just in lower case
        // "upper case always lower than lower case."
        String lowerplace = placement.toLowerCase();
        boolean b = false;

        if (lowerplace.length() == 4) {
            b = true;
        } else if (lowerplace.length() > 4 && lowerplace.length() < 40) {
            //use for loop to check the correct order in the string while the string's length is less than 40.
            int times = lowerplace.length() / 4;
            for (int i = 0; i < times - 1; i++) {
                if ((lowerplace.charAt(i * 4) < lowerplace.charAt((i + 1) * 4)) && (lowerplace.charAt(0) < lowerplace.charAt(lowerplace.length() - 4)) && (lowerplace.charAt(0) < lowerplace.charAt((i + 1) * 4)))
                    b = true;

                else
                    b = false;
            }
        } else if (lowerplace.length() == 40) {
            // while the length is 40, the order of the string will be fixed.
            int plclen = lowerplace.length();

            if ((lowerplace.charAt(0) == 98) && (lowerplace.charAt(plclen - 4) == 121) && (lowerplace.charAt(4) == 103) && (lowerplace.charAt(8) == 105) && (lowerplace.charAt(12) == 108) && (lowerplace.charAt(16) == 110)
                    && (lowerplace.charAt(20) == 111) && (lowerplace.charAt(24) == 112) && (lowerplace.charAt(28) == 114) && (lowerplace.charAt(32) == 115)) {
                b = true;
            } else {
                b = false;
            }
        }


        return b;
    }


    /**
     * Determine whether a placement string is valid.
     * <p>
     * To be valid, the placement string must be:
     * - well-formed, and
     * - each piece placement must be a valid placement according to the
     * rules of the game:
     * - pieces must be entirely on the board
     * - pieces must not overlap each other
     *
     * @param placement A placement string
     * @return True if the placement sequence is valid
     */
    public static boolean isPlacementValid(String placement) {
        //check if the placement string is well-formed
        if (!isPlacementWellFormed(placement))
            return false;

        char[][] localBoard = new char[10][5];
        for (int r = 0; r < 5; r++)
            for (int c = 0; c < 10; c++)
                localBoard[c][r] = '_';

        for (int i = 0; i < placement.length(); i += 4) {
            String piecePlacement = placement.substring(i, i + 4);
            Piece piece = new Piece(piecePlacement);
            int xPos = piece.getxPos();
            int yPos = piece.getyPos();
            char[][] structure = piece.getStructure();
            for (int j = 0; j < piece.getWidth(); j++)
                for (int k = 0; k < piece.getHeight(); k++)
                    if ((xPos + j < 10) && (yPos + k < 5) && (localBoard[xPos + j][yPos + k] == '_'))
                        localBoard[xPos + j][yPos + k] = structure[j][k];
                    else if ((xPos + j < 10) && (yPos + k < 5) && (structure[j][k] == '_'))
                        continue;
                    else
                        return false;
        }

        return true;

        // FIXME Task 5: determine whether a placement string is valid
    }

    /**
     * Given a string describing a placement of pieces, and a location
     * that must be covered by the next move, return a set of all
     * possible next viable piece placements which cover the location.
     * <p>
     * For a piece placement to be viable it must:
     * - be a well formed piece placement
     * - be a piece that is not already placed
     * - not overlap a piece that is already placed
     * - cover the location
     *
     * @param placement A starting placement string
     * @param col       The location's column.
     * @param row       The location's row.
     * @return A set of all viable piece placements, or null if there are none.
     */
    static Set<String> getViablePiecePlacements(String placement, int col, int row) {
        Set<String> returnSet = new HashSet();


        return returnSet; // FIXME Task 6: determine the set of all viable piece placements given existing placements
    }

    /**
     * Return the solution to a particular challenge.
     * *
     *
     * @param challenge A challenge string.
     * @return A placement string describing the encoding of the solution to
     * the challenge.
     */
    public static String getSolution(String challenge) {
        return null;  // FIXME Task 9: determine the solution to the game, given a particular challenge
    }
}
