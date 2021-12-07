package fr.baba.word;

public enum EnumOper implements EnumAll {
	is;
	public static boolean contains(String test) {
	    for (EnumOper o : EnumOper.values()) {
	        if (o.name().equals(test)) {
	            return true;
	        }
	    }
	    return false;
	}
}
