package it.unibo.memory.controller;

import it.unibo.memory.model.Board;
import it.unibo.memory.model.Difficulty;

public class Main {
    public static void main(final String[] args) {
        Difficulty diff = Difficulty.MEDIUM;
        Board board = new Board(diff);
        System.out.println("Righe: " + diff.getRows());
        System.out.println("Colonne: " + diff.getCols());
        System.out.println("Totale carte: " + diff.totalCards());
        System.out.println("Tutte abbinate? " + board.allMatched());
    }
}
