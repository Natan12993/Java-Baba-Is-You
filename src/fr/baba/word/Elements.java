package fr.baba.word;

import java.awt.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Elements {

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Elements other = (Elements) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	private EnumAll name;
	private Image img;

	public Elements(String name) throws FileNotFoundException, IOException {
		this.img = ImageIO.read(new FileInputStream("img/" + name + ".gif"));
	}

	public Image getImg(){
		return img;
	}
	
	public Boolean isText() {
		return true;
	}
	
	public EnumAll getName() {
		return name;		
	}
	
	public String typeOf() {
		return "Elements";
	}
}
