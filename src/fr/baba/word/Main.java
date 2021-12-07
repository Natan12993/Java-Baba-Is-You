package fr.baba.word;

import java.io.IOException;

import fr.baba.graphics.Graphics;
import fr.baba.map.Map;

public class Main {
	public static void main(String[] args) {
		Map map;
		
		try {
			map = new Map("level_3");
		} catch (IOException e) {
			 System.err.println(e.getMessage());
			 System.exit(1);
			 return;
		}
		
		Graphics screen = new Graphics(map);
		screen.run();
	}
}
