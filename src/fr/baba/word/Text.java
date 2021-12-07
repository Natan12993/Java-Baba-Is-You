package fr.baba.word;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Text extends Elements {
	private EnumText name;

	public Text(String name) throws FileNotFoundException, IOException {
		super(name);
		this.name = EnumText.valueOf(name);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Text other = (Text) obj;
		if (name != other.name)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return name.toString();
	}
	
	@Override
	public EnumText getName() {
		return name;		
	}
	
	@Override
	public String typeOf() {
		return "Text";
	}
}
