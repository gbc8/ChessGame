package application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {

	public static void clearScreen() {
		System.out.print("\033\143");
	}
	
	public static ChessPosition readChessPosition(Scanner in) {
		try {
			String s = in.nextLine();
			char column = s.charAt(0);
			int row = Integer.parseInt(s.substring(1));
			return new ChessPosition(column,row);
		}catch(RuntimeException e) {
			throw new InputMismatchException("Error reading ChessPosition. Valid values are from a1 to h8.");
		}
	}
	
	public static void printMatch(ChessMatch chessMatch, List<ChessPiece> captured) {
		printBoard(chessMatch.getPieces());
		printCapturedPieces(captured);
		System.out.println("\nTurn: " + chessMatch.getTurn());
		System.out.println("Waiting player: " + chessMatch.getCurrentPlayer());
		if(chessMatch.getCheck()) {
			System.out.println("CHECK!");
		}
	}
	
	public static void printBoard(ChessPiece[][] pieces) {
		for(int i = 0; i < pieces.length; ++i) {
			System.out.print(8-i + " ");
			for(int j = 0; j < pieces.length; ++j) {
				printPiece(pieces[i][j], false);
			}
			System.out.println();
		}
		System.out.print("  a b c d e f g h\n");
	}
	
	public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
		for(int i = 0; i < pieces.length; ++i) {
			System.out.print(8-i + " ");
			for(int j = 0; j < pieces.length; ++j) {
				printPiece(pieces[i][j], possibleMoves[i][j]);
			}
			System.out.println();
		}
		System.out.print("  a b c d e f g h\n");
	}
	
	private static void printPiece(ChessPiece piece, boolean background) {
		if(background) {
			System.out.print("*");
		}else if(piece == null){
            System.out.print("-");
        }else{
            if(piece.getColor() == Color.WHITE){
                System.out.print(piece);
            }else{
                System.out.print(piece.toString().toLowerCase());
            }
        }
        System.out.print(" ");
    }
	
	private static void printCapturedPieces(List<ChessPiece> captured) {
		List<ChessPiece> white = captured.stream().filter(x -> x.getColor() == Color.WHITE).collect(Collectors.toList());
		List<ChessPiece> black = captured.stream().filter(x -> x.getColor() == Color.BLACK).collect(Collectors.toList());
		System.out.println();
		System.out.println("Captured pieces:");
		System.out.print("White: ");
		System.out.print(Arrays.toString(white.toArray()));
		System.out.print("\nBlack: ");
		System.out.print(Arrays.toString(black.toArray()));
		System.out.println();
	}
}
