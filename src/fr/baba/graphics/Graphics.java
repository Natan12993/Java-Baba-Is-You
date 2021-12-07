package fr.baba.graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import fr.baba.map.Map;
import fr.baba.movement.Move;
import fr.baba.word.Elements;
import fr.umlv.zen5.Application;
import fr.umlv.zen5.ApplicationContext;
import fr.umlv.zen5.Event;
import fr.umlv.zen5.Event.Action;
import fr.umlv.zen5.KeyboardKey;

public class Graphics {
	
	private int mapX;
	private int mapY;
	private Map map;
	private float width;
	private float height;
	private int elementSize;
	

	public Graphics(Map map) {
		mapY = map.getSize()[0];
		mapX = map.getSize()[1];
		this.map = map;
	}
	
	private void getInfos(ApplicationContext context) {
		this.width = context.getScreenInfo().getWidth();
	    this.height = context.getScreenInfo().getHeight();
	    this.elementSize = (int) ((width - 50) / mapX < (height - 50) / mapY ? (width - 50) / mapX : (height - 50) / mapY);
	}
	
	private void printToScreen(Graphics2D graphics) {
		graphics.setColor(Color.BLACK);
        graphics.fill(new  Rectangle2D.Float(0, 0, width, height));
        graphics.setColor(Color.DARK_GRAY);
        graphics.fill(new  Rectangle2D.Float((width-(mapX*elementSize))/2,
        		(height-(mapY*elementSize))/2, mapX*elementSize, mapY*elementSize));
        for (int line=0; line<map.getBoard().length; line++) {
            for (int column=0; column<map.getBoard()[line].length; column++) {
            	for (Elements elem : map.getBoard()[line][column]) { //on affiche d'abord baba puis le reste des éléments
            		//stream si baba affiche sinon affiche le reste
            		graphics.drawImage(elem.getImg(), (int) ((width-(mapX*elementSize))/2) + column * elementSize,
            				(int) ((height-(mapY*elementSize))/2) + line * elementSize, elementSize, elementSize, null);
				}
            }
        }
	}
	
	private void everything(ApplicationContext context) {
		this.getInfos(context);
		context.renderFrame(graphics -> {
			this.printToScreen(graphics);
		});
	}
	
	public void run() {
		Application.run(Color.BLACK, context -> {
			this.getInfos(context);
			everything(context);
			for(;!map.victory();) {
				map.findRules();
				Event event = context.pollOrWaitEvent(10);
				if (event == null) {  // no event
					continue;
				}
				Action action = event.getAction();
				if (action == Action.KEY_PRESSED) {
					if(event.getKey() == KeyboardKey.Q) {
						context.exit(0);
						return;
					}
					else {
						Move.move(map, event.getKey());
						everything(context);
						continue;
					}
				}
				map.clearRules();
			}
			context.exit(0);
			return;
		});
	}
}
