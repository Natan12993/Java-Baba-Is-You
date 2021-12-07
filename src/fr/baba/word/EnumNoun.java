package fr.baba.word;

public enum EnumNoun implements EnumAll {
	baba, flag, wall, water, skull, lava, rock;
	public static boolean contains(String test) {
	    for (EnumNoun n : EnumNoun.values()) {
	        if (n.name().equals(test)) {
	            return true;
	        }
	    }
	    return false;
	}
}
