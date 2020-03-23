package util;

public class StringUtil {
	/**
	 * ×Ö·û´®ÅÐ¿Õ
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		if(str==null||str.trim().length()==0){
			return true;
		}else{
			return false;
		}
	}
}
