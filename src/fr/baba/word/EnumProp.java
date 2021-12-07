package fr.baba.word;

public enum EnumProp implements EnumAll {
	you, win, stop, push, melt, hot, defeat, sink;
	public static boolean contains(String test) {
	    for (EnumProp p : EnumProp.values()) {
	        if (p.name().equals(test)) {
	            return true;
	        }
	    }
	    return false;
	}
}
