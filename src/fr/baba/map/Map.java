package fr.baba.map;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.StringJoiner;
import fr.baba.word.*;

public class Map {
	private int[] mapSize;
	private HashSet<Elements>[][] board;
	private Rules rules;
	
	public Map(String level) throws IOException {
		Scanner in = new Scanner(new FileReader("levels/" + level));
		this.mapSize = new int[2];
		if(in.next().equals("size")) {
			this.mapSize[0] = Integer.parseInt(in.next());
			this.mapSize[1] = Integer.parseInt(in.next());
		}
		this.board = initBoard(mapSize[0], mapSize[1]);
		if(in.next().equals("board")) {
			this.firstAdd(in);
		}
		this.rules = new Rules();
	}
	
	@Override
	public String toString() {
		StringJoiner sj = new StringJoiner(",\n ", "[", "]");
        for (int line=0; line<this.board.length; line++) {
        	StringJoiner sj2 = new StringJoiner(", ", "[", "]");
            for (int column=0; column<board[line].length; column++) {
                    sj2.add(board[line][column].toString());
            }
            sj.add(sj2.toString());
        }
        return sj.toString();
    }
	
	public int[] getSize() {
		return mapSize;
		
	}

	public HashSet<Elements>[][] getBoard() {
		return board;
	}
	
	public void firstAdd(Scanner in) throws FileNotFoundException, IOException {
		for(int i = 0; i < mapSize[0]; i++) {
			for(int j = 0; j < mapSize[1]; j++) {
				String next = in.next();
				if(EnumNoun.contains(next)) { this.board[i][j].add(new Noun(next)); }
				else if(EnumProp.contains(next)) { this.board[i][j].add(new Property(next)); }
				else if(EnumOper.contains(next)) { this.board[i][j].add(new Operator(next)); }
				else if(EnumText.contains(next)) { this.board[i][j].add(new Text(next)); }
			}
		}
	}
	
	public HashSet<Elements>[][] initBoard(int length, int width){
		board = new HashSet[length][width];
		for(int i = 0; i < length; i++) {
			for(int j = 0; j < width; j++) {
				board[i][j] = new HashSet<Elements>();
			}
		}
		return board;
	}
	
	public void add(int line, int column, Elements e) {
		this.board[line][column].add(e);
	}
	
	public void remove(int line, int column, Elements e) {
		this.board[line][column].remove(e);
	}
	
	public void findRules() {
		for(int i = 0; i < this.mapSize[0] - 2; i++) {
			for(int j = 0; j < this.mapSize[1] - 2; j++) { 
				int right = 1, down = 0;
				for(int k = 0; k < 2; k++) {
					int i_here = i, j_here = j, down_here = down, right_here = right;
					this.board[i][j].stream().filter(x -> x.typeOf().equals("Text")).forEach(x -> 
					this.board[i_here+down_here][j_here+right_here].stream().filter(y -> y.typeOf().equals("Operator")).forEach(y -> 
					this.board[i_here+2*down_here][j_here+2*right_here].stream().filter(z -> z.typeOf().equals("Property")).forEach(z -> 
					rules.add((Text) x, (EnumProp) z.getName()))));
					right = 0; down = 1;
				}
			}
		}
	}
	
	public void clearRules() {
		this.rules.clear();
	}
	
	public Boolean verifyRule(Elements e, EnumProp p) {
		return rules.verify(e, p);
	}

	public Rules getRules() {
		return rules;
	}
	
	public Boolean victory() {
		for(int i = 0; i < this.mapSize[0]; i++) {
			for(int j = 0; j < this.mapSize[1]; j++) {
				if(this.board[i][j].stream().filter(y -> this.verifyRule(y, EnumProp.you)).count() > 0 &&
					this.board[i][j].stream().filter(w -> this.verifyRule(w, EnumProp.win)).count() > 0) {
					return true;
				}
			}
		}
		return false;
	}
}