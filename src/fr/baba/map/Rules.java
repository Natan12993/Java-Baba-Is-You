package fr.baba.map;

import java.util.HashMap;
import java.util.HashSet;

import fr.baba.word.Elements;
import fr.baba.word.EnumNoun;
import fr.baba.word.EnumProp;
import fr.baba.word.Text;

public class Rules {
	private HashMap<EnumNoun, HashSet<EnumProp>> ruleBoard;

	public Rules() {
		this.ruleBoard = new HashMap<EnumNoun, HashSet<EnumProp>>();
	}
	
	public void add(Text text, EnumProp prop) {
		if(ruleBoard.containsKey(text.getName().toObj())) {
			ruleBoard.get(text.getName().toObj()).add(prop);
		}
		else {
			ruleBoard.put(text.getName().toObj(), new HashSet<EnumProp>());
			this.add(text, prop);
		}
	}
	
	public void clear() {
		ruleBoard.clear();
	}
	
	public Boolean verify(Elements elem, EnumProp prop) {
		if(elem.isText()) {
			return prop.equals(EnumProp.push);
		}
		else {
			if(ruleBoard.containsKey(elem.getName())) {
				return ruleBoard.get(elem.getName()).contains(prop);
			}
			else {
				return false;
			}
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (EnumNoun en : ruleBoard.keySet()) {
			sb.append(en).append(ruleBoard.get(en)).append(" ");
		}
		return sb.toString();
    }
}
