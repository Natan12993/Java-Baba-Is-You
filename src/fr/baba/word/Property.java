package fr.baba.word;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Property extends Elements {
	private EnumProp name;
	
	public Property(String name) throws FileNotFoundException, IOException {
		super(name);
		this.name = EnumProp.valueOf(name);
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
		Property other = (Property) obj;
		if (name != other.name)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return name.toString();
	}
	
	@Override
	public EnumProp getName() {
		return name;		
	}
	
	@Override
	public String typeOf() {
		return "Property";
	}
}
