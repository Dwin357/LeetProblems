/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.dwin357.leetcode.thirty.comboSubstring;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author dwin
 */
public class Solution {
    /*
    Runtime: 59 ms, faster than 72.98% of Java online submissions for Substring with Concatenation of All Words.
Memory Usage: 38.7 MB, less than 96.42% of Java online submissions for Substring with Concatenation of All Words
    */
public List<Integer> findSubstring(String s, String[] words) {
	List<Integer> rtn = new LinkedList<>();

    	if(words.length == 0) {
			return rtn;
		}
    
		MapStruct struct = new MapStruct(words);
		int step = 1;
		
		for(int i=0; (i+struct.getTotCt()) <= s.length(); i = i+step) {
			if(struct.check(s.substring(i, i+struct.getTotCt()))) {
				rtn.add(i);
			}
		}
		return rtn;
	}
	
	private class MapStruct {
		private int wrdLn;		
		private int wrdCt;
		private int totCt;
		private Map<String, Integer> dic;
		
		private MapStruct(String[] wrds) {
			super();
			setWrdCt(wrds.length);
			if(wrds.length > 0 && wrds[0] != null) {
				setWrdLn(wrds[0].length());
				setTotCt(getWrdCt() * getWrdLn());
				setDic(mapCount(wrds));
			} else {
				setWrdLn(0);
				setTotCt(0);
				setDic(new HashMap<>());
			}
		}
		
		public boolean check(String compare) {
			if(compare.length() != getTotCt()) {
				return false;
			}
			Map<String, Integer> localMap = new HashMap<String, Integer>(getDic());
			Integer ct;
			String wrd;
			for(int i = 0; i < compare.length(); i = i + getWrdLn()) {
				wrd = compare.substring(i, i+getWrdLn());
				ct = localMap.get(wrd);
				if(ct == null) {
					return false;
				}
				ct = ct -1;
				if(ct.intValue() < 0 ) {
					return false;
				}
				localMap.put(wrd, ct);
			}
			return true;
		}
		
		private Map<String, Integer> mapCount(String[] wrds) {
			Map<String, Integer> map = new HashMap<>();
			Integer ct;
			for(int i=0; i< wrds.length; i++) {
				ct = map.get(wrds[i]);
				if(ct == null) {
					ct = 0;
				}
				map.put(wrds[i], ct+1);
			}
			return map;
		}

		public int getWrdLn() {
			return wrdLn;
		}

		public void setWrdLn(int wrdLn) {
			this.wrdLn = wrdLn;
		}

		public int getWrdCt() {
			return wrdCt;
		}

		public void setWrdCt(int wrdCt) {
			this.wrdCt = wrdCt;
		}

		public int getTotCt() {
			return totCt;
		}

		public void setTotCt(int totCt) {
			this.totCt = totCt;
		}

		public Map<String, Integer> getDic() {
			return dic;
		}

		public void setDic(Map<String, Integer> dic) {
			this.dic = dic;
		}
		
		
		
	}
}