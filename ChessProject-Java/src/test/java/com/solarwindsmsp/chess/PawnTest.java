package com.solarwindsmsp.chess;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PawnTest {

    private ChessBoard chessBoard;
    private ChessPiece testSubject;
    private ChessPiece testSubjectWhitePawn;

    @Before
    public void setUp() {
        this.chessBoard = new ChessBoard();
        this.testSubject = new Pawn(PieceColor.BLACK);
        this.testSubjectWhitePawn = new Pawn(PieceColor.WHITE);
    }

    // Calls to Add have been modified to remove color argument
    @Test
    public void testChessBoard_Add_Sets_XCoordinate() {
        this.chessBoard.Add(testSubject, 6, 3);
        assertEquals(6, testSubject.getXCoordinate());
    }

    @Test
    public void testChessBoard_Add_Sets_YCoordinate() {
        this.chessBoard.Add(testSubject, 6, 3);
        assertEquals(3, testSubject.getYCoordinate());
    }


    @Test
    public void testPawn_Move_IllegalCoordinates_Right_DoesNotMove() {
        chessBoard.Add(testSubject, 6, 3);
        testSubject.Move(MovementType.MOVE, 7, 3);
        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
    }

    @Test
    public void testPawn_Move_IllegalCoordinates_Left_DoesNotMove() {
        chessBoard.Add(testSubject, 6, 3);
        testSubject.Move(MovementType.MOVE, 4, 3);
        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
    }

    @Test
    public void testPawn_Move_LegalCoordinates_Forward_UpdatesCoordinates() {
        chessBoard.Add(testSubject, 6, 3);
        testSubject.Move(MovementType.MOVE, 6, 2);
        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(2, testSubject.getYCoordinate());
    }

    // New tests added below

    @Test
    public void testPawn_Move_IllegalCoordinates_Backward_DoesNotMove() {
        chessBoard.Add(testSubject, 4, 6);
        testSubject.Move(MovementType.MOVE, 4, 7);
        assertEquals(4, testSubject.getXCoordinate());
        assertEquals(6, testSubject.getYCoordinate());
    }

    @Test
    public void testPawn_Capture_IllegalCoordinates_Forward_DoesNotMove() {
        chessBoard.Add(testSubject, 4, 6);
        testSubject.Move(MovementType.CAPTURE, 4, 5);
        assertEquals(4, testSubject.getXCoordinate());
        assertEquals(6, testSubject.getYCoordinate());
    }

    @Test
    public void testPawn_Capture_LegalCoordinates_ForwardLeft_UpdatesCoordinates() {
        chessBoard.Add(testSubject, 4, 6);
        testSubject.Move(MovementType.CAPTURE, 5, 5);
        assertEquals(5, testSubject.getXCoordinate());
        assertEquals(5, testSubject.getYCoordinate());
    }

    // White Pawn Moves
    @Test
    public void testWhitePawn_Move_LegalCoordinates_Forward_UpdatesCoordinates() {
        chessBoard.Add(testSubjectWhitePawn, 4, 1);
        testSubjectWhitePawn.Move(MovementType.MOVE, 4, 2);
        assertEquals(4, testSubjectWhitePawn.getXCoordinate());
        assertEquals(2, testSubjectWhitePawn.getYCoordinate());    	
    }

    @Test
    public void testWhitePawn_Move_IllegalCoordinates_Backward_DoesNotMove() {
        chessBoard.Add(testSubjectWhitePawn, 4, 4);
        testSubjectWhitePawn.Move(MovementType.MOVE, 4, 3);
        assertEquals(4, testSubjectWhitePawn.getXCoordinate());
        assertEquals(4, testSubjectWhitePawn.getYCoordinate());    	
    }

    @Test
    public void testWhitePawn_Capture_IllegalCoordinates_Left_DoesNotMove() {
        chessBoard.Add(testSubjectWhitePawn, 4, 3);
        testSubjectWhitePawn.Move(MovementType.CAPTURE, 5, 3);
        assertEquals(4, testSubjectWhitePawn.getXCoordinate());
        assertEquals(3, testSubjectWhitePawn.getYCoordinate());
    }

    @Test
    public void testWhitePawn_Capture_LegalCoordinates_ForwardLeft_UpdatesCoordinates() {
        chessBoard.Add(testSubjectWhitePawn, 4, 6);
        testSubjectWhitePawn.Move(MovementType.CAPTURE, 3, 7);
        assertEquals(3, testSubjectWhitePawn.getXCoordinate());
        assertEquals(7, testSubjectWhitePawn.getYCoordinate());
    }

    @Test
    public void testWhitePawn_Capture_LegalCoordinates_ForwardRight_UpdatesCoordinates() {
        chessBoard.Add(testSubjectWhitePawn, 4, 6);
               testSubjectWhitePawn.Move(MovementType.CAPTURE, 5, 7);
        assertEquals(5, testSubjectWhitePawn.getXCoordinate());
        assertEquals(7, testSubjectWhitePawn.getYCoordinate());
    }

    // Helper methods
    
    @Test
    public void testPawn_toRelativeForwardSquares_ForBlack_NewYGreater_Negative() {
        chessBoard.Add(testSubject, 2, 3);
        int forward = testSubject.toRelativeForwardSquares(4);
        assertEquals(forward, -1);
    }

    @Test
    public void testPawn_toRelativeForwardSquares_ForBlack_NewYLess_Positive() {
        chessBoard.Add(testSubject, 2, 3);
        int forward = testSubject.toRelativeForwardSquares(2);
        assertEquals(forward, 1);
    }

    @Test
    public void testPawn_toRelativeForwardSquares_ForWhite_NewYGreater_Positive() {
        chessBoard.Add(testSubjectWhitePawn, 2, 3);
        int forward = testSubjectWhitePawn.toRelativeForwardSquares(4);
        assertEquals(forward, 1);
    }

    @Test
    public void testPawn_toRelativeForwardSquares_ForWhite_NewYLess_Negative() {
        chessBoard.Add(testSubjectWhitePawn, 2, 3);
        int forward = testSubjectWhitePawn.toRelativeForwardSquares(2);
        assertEquals(forward, -1);
    }

    @Test
    public void testPawn_toRelativeLeftSquares_ForBlack_NewYGreater_Positive() {
        chessBoard.Add(testSubject, 2, 3);
        int forward = testSubject.toRelativeLeftSquares(3);
        assertEquals(forward, 1);
    }

    @Test
    public void testPawn_toRelativeLeftSquares_ForBlack_NewYLess_Negative() {
        chessBoard.Add(testSubject, 2, 3);
        int forward = testSubject.toRelativeLeftSquares(1);
        assertEquals(forward, -1);
    }

    @Test
    public void testPawn_toRelativeLeftSquares_ForWhite_NewYGreater_Negative() {
        chessBoard.Add(testSubjectWhitePawn, 2, 3);
        int forward = testSubjectWhitePawn.toRelativeLeftSquares(3);
        assertEquals(forward, -1);
    }

    @Test
    public void testPawn_toRelativeLeftSquares_ForWhite_NewYLess_Positive() {
        chessBoard.Add(testSubjectWhitePawn, 2, 3);
        int forward = testSubjectWhitePawn.toRelativeLeftSquares(1);
        assertEquals(forward, 1);
    }

    // Other methods
    @Test
    public void testPawn_getMaxPiecesPerColor_Returns_8() {
        assertEquals(8, testSubject.getMaxPiecesPerColor());
    }

    @Test
    public void testBlackPawn_toSymbol_Uppercase_P() {
        assertEquals("P", testSubject.toSymbol());
    }

    @Test
    public void testWhitePawn_toSymbol_Lowercase_p() {
        assertEquals("p", testSubjectWhitePawn.toSymbol());
    }
    
    @Test 
    public void testPawn_toString_ColourAndCoordsInString() {
       chessBoard.Add(testSubject, 2, 3);
       String strRep = testSubject.toString();
       assertTrue(strRep.indexOf("BLACK")>0 && strRep.indexOf("2") >0 && strRep.indexOf("3")>0);
    }
}
