package fr.baba.movement;

import java.util.HashSet;
//import java.util.List;
//import java.util.stream.Collectors;

import fr.baba.map.Map;
import fr.baba.word.Elements;
import fr.baba.word.EnumProp;
import fr.umlv.zen5.KeyboardKey;

public class Move {
	
	// Si l'élément peut être déplacé on l'ajoute à ca nouvelle position et on l'enlève à l'ancienne.
	public static void moveAndRemove(Map map, KeyboardKey motion, int i, int j, Elements e) {
		if(motion == KeyboardKey.UP) {
			map.add(i-1, j, e);
			map.remove(i, j, e);
		}
		if(motion == KeyboardKey.DOWN) {
			map.add(i+1, j, e);
			map.remove(i, j, e);
		}
		if(motion == KeyboardKey.LEFT) {
			map.add(i, j-1, e);
			map.remove(i, j, e);
		}
		if(motion == KeyboardKey.RIGHT) {
			map.add(i, j+1, e);
			map.remove(i, j, e);
		}		
	}
	
	// On regarde si le déplacement d'un bloc n'est pas en dehors du board.
	public static boolean outOfMap(Map map, KeyboardKey motion, int coordX, int coordY) {
		if(motion == KeyboardKey.UP && (coordX-1) < 0) {
			return true;
		}
		else if(motion == KeyboardKey.DOWN && (coordX+1) > map.getSize()[0] - 1) {
			return true;
		}
		else if(motion == KeyboardKey.LEFT && (coordY-1) < 0) {
			return true;
		}
		else if(motion == KeyboardKey.RIGHT && (coordY+1) > map.getSize()[1] - 1) {
			return true;
		}
		else {
			return false;
		}
	}
		
	// On déplace le block après celui que l'on veut déplacer si il existe et si c'est possible
	public static boolean moveBlock(Map map, KeyboardKey motion, int i, int j) {
		int i_bis = i, j_bis = j;
		switch (motion) {
		case UP: {i_bis -= 1; break;}
		case DOWN: {i_bis += 1; break;}
		case LEFT: {j_bis -= 1; break;}
		case RIGHT: {j_bis += 1; break;}
		default:
			return false;
		}
		if(map.getBoard()[i_bis][j_bis].isEmpty() || 
				(map.getBoard()[i_bis][j_bis].stream().filter(p -> map.verifyRule(p, EnumProp.push)).count() == 0 &&
				 map.getBoard()[i_bis][j_bis].stream().filter(s -> map.verifyRule(s, EnumProp.stop)).count() == 0)){
			return true;
		}
		int i_tier = i_bis, j_tier = j_bis;
		HashSet<Elements> toMove = new HashSet<Elements>();
		map.getBoard()[i_bis][j_bis].stream().filter(x -> map.verifyRule(x, EnumProp.push))
		.filter(x -> possibleMove(map, motion, i_tier, j_tier)).forEach(x -> toMove.add(x));
		toMove.stream().forEach(y -> moveAndRemove(map, motion, i_tier, j_tier, y));
		return toMove.size() > 0 ;
	}
	
	// Si tout les propriété son vérifié pour que le prochain bloc puisse être déplacé
	public static boolean possibleMove(Map map, KeyboardKey motion, int coordX, int coordY) {
		//On passe tout les tests	
		if(!outOfMap(map, motion, coordX, coordY)) {
			if(moveBlock(map, motion, coordX, coordY)) {
				return true;
			}
		}
		return false;
	}
		
	// On déplace notre block qui a la propriété 'is You'
	public static void move(Map map, KeyboardKey motion) {
		//parcours le tableau et bouge chaque élément 'is You'
		for(int i = 0; i < map.getSize()[0]; i++) {
			for(int j = 0; j < map.getSize()[1]; j++) { //stream tout les elements -> si isYou() -> to list -> foreach possiblemove
				
//				int i_bis = i, j_bis = j;
//				
//				List<Elements>toMove = map.getBoard()[i][j].stream().filter(e -> map.verifyRule(e, EnumProp.you)).collect(Collectors.toList());
//				toMove.stream().filter(x -> possibleMove(map, motion, i_bis, j_bis)).forEach(x -> moveAndRemove(map, motion, i_bis, j_bis, x));
				
				for (Elements e : map.getBoard()[i][j]) {
					if (map.verifyRule(e, EnumProp.you)) {
						if(possibleMove(map, motion, i, j)) {
							moveAndRemove(map, motion, i, j, e);
							return; 
						}
					}
				}
			}
		}
	}
}
