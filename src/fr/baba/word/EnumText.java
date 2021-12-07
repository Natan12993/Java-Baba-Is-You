package fr.baba.word;

public enum EnumText implements EnumAll {
	batxt {
		@Override
		public EnumNoun toObj() {
			return EnumNoun.baba;
		}
	}, 
	fltxt {
		@Override
		public EnumNoun toObj() {
			return EnumNoun.flag;
		}
	}, 
	waltxt {
		@Override
		public EnumNoun toObj() {
			return EnumNoun.wall;
		}
	}, 
	wattxt {
		@Override
		public EnumNoun toObj() {
			return EnumNoun.water;
		}
	}, 
	sktxt {
		@Override
		public EnumNoun toObj() {
			return EnumNoun.skull;
		}
	}, 
	latxt {
		@Override
		public EnumNoun toObj() {
			return EnumNoun.lava;
		}
	}, 
	rotxt {
		@Override
		public EnumNoun toObj() {
			return EnumNoun.rock;
		}
	};
	public static boolean contains(String test) {
	    for (EnumText t : EnumText.values()) {
	        if (t.name().equals(test)) {
	            return true;
	        }
	    }
	    return false;
	}
	public abstract EnumNoun toObj();
}
