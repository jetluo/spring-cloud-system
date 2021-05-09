package com.cloud.kjetboy.server.common.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 功能描述：字符串操作工具类
 * @author jet
 * @date 2017-04-09 15:50:32
 * @version 1.0
 */
@SuppressWarnings("all")
public class StringUtils {

	/**
	 * 功能描述：判断字符串数据是否为空
	 * @param data 需要判断的数据信息
	 * @return     返回值：如果数据为null，或者为空串，则返回false，否则返回true
	 * @author jet
	 */
	public static boolean isNotEmpty(String data){
		if(data == null || data.trim().length() == 0){
			return false;
		}
		return true;
	}
	
	/**
	 * 功能描述：判断集合数据是否为空
	 * @param collection 数据列表
	 * @return     返回值：如果数据为null，或者为空集合，则返回false，否则返回true
	 * @author jet
	 */
	public static boolean isNotEmpty(Collection<?> collection){
		return !isEmpty(collection);
	}
	
	/**
	 * 功能描述：判断集合数据是否为空
	 * @param collection 数据列表
	 * @return     返回值：如果数据为null，或者为空集合，则返回true，否则返回false
	 * @author jet
	 */
	public static boolean isEmpty(Collection<?> collection){
		return (collection == null || collection.isEmpty());
	}
	
	/**
	 * 功能描述：判断map数据信息是否为空
	 * @param map 数据map
	 * @return    返回值：如果数据为null，或者为空集合，则返回false，否则返回true
	 * @author jet
	 */
	public static boolean isNotEmpty(Map<? , ?> map){
		return !isEmpty(map);
	}
	
	/**
	 * 功能描述：判断map数据信息是否为空
	 * @param map 数据map
	 * @return    返回值：如果数据为null，或者为空集合，则返回true，否则返回false
	 * @author jet
	 */
	public static boolean isEmpty(Map<? , ?> map){
		return (map == null || map.isEmpty());
	}
	/**
     * 功能描述：判断JavaBean是否为空
     * @param obj 数据obj
     * @return    返回值：如果数据为null，或者为空集合，则返回true，否则返回false
     * @author jet
     */
    public static boolean isNullOrEmpty(Object obj) {  
        if (obj == null)  
            return true;  
  
        if (obj instanceof CharSequence)  
            return ((CharSequence) obj).length() == 0;  
  
        if (obj instanceof Collection)  
            return ((Collection) obj).isEmpty();  
  
        if (obj instanceof Map)  
            return ((Map) obj).isEmpty();  
  
        if (obj instanceof Object[]) {  
            Object[] object = (Object[]) obj;  
            if (object.length == 0) {  
                return true;  
            }  
            boolean empty = true;  
            for (int i = 0; i < object.length; i++) {  
                if (!isNullOrEmpty(object[i])) {  
                    empty = false;  
                    break;  
                }  
            }  
            return empty;  
        }  
        return false;  
    }
    /**
     * 功能描述：判断JavaBean是否为空
     * @param obj 数据obj
     * @return    返回值：如果数据为null，或者为空集合，则返回false，否则返回true
     * @author jet
     */
    public static boolean isNotNullOrEmpty(Object obj) {  
       return !isNullOrEmpty(obj);
    }
	/**
	 * 功能描述：把Object类型转换为int类型数据
	 * @param obj 数据信息 
	 * @return    返回值：返回转换后的int数值，如果数据为空，则返回为0
	 * @author jet
	 */
	public static Integer converObjectToInteger(Object obj){
		return Integer.parseInt(converObjectToString(obj , "0"));
	}
	
	/**
	 * 功能描述： 把Object数据转换为字符串
	 * @param obj        需要转换的数据信息
	 * @param defaultVal 默认值
	 * @return           返回值：返回转换后的字符串数据信息，如果需要转换的数据为空，则返回默认值
	 * @author jet
	 */
	public static String converObjectToString(Object obj , String defaultVal){
		if(obj == null || obj.toString().trim().length() == 0){
			return defaultVal;
		}
		return obj.toString();
	}
	
	/**
	 * 功能描述： 把Object数据转换为字符串
	 * @param obj 需要转换的数据信息
	 * @return    返回值：返回转换后的字符串数据信息，如果需要转换的数据为空，则返回空串
	 * @author jet
	 */
	public static String converObjectToString(Object obj){
		return converObjectToString(obj , "");
	}
	
	/**
	 * 功能描述：数组连接
	 * @param array  数组
	 * @param link   需要连接的字符串
	 * @return       返回值：返回连接后的字符串信息
	 */
	public static String join(String[] array, String link) {
		return join(array, link, null);
	}
	
	/**
	 * 功能描述：数组连接
	 * @param array  数组
	 * @param link   需要连接的字符串
	 * @param pre    前缀符号
	 * @return       返回值：返回连接后的字符串信息
	 */
	public static String join(String[] array, String link , String pre) {
		if(array == null || array.length == 0){
			return "";
		}
		if(!isNotEmpty(link)){
			link = "";
		}
		StringBuffer sb = new StringBuffer();
		if(isNotEmpty(pre)){
			sb.append(pre);
		}
		int size = array.length;
		int max = size - 1;
		for(int i = 0 ; i < size ; i++){
			sb.append(array[i]);
			if(i < max){
				sb.append(link);
			}
		}
		return sb.toString();
	}
	
	public static String validFilePath(String filepath) throws Exception{  
	    List<String> allowedExtensions = new ArrayList<>();  
	    boolean result = false;  
	    allowedExtensions.add(".doc");  
	    allowedExtensions.add(".docx");  
	    allowedExtensions.add(".xls");  
	    allowedExtensions.add(".xlsx");  
	    allowedExtensions.add(".pdf");  
	    allowedExtensions.add(".ppt");  
	    allowedExtensions.add(".zip");  
	    allowedExtensions.add(".rar");  
	    allowedExtensions.add(".txt");
	    allowedExtensions.add(".png"); 
	    allowedExtensions.add(".jpg");  
	    for (String suf:allowedExtensions){  
	        if(filepath.endsWith(suf)){  
	            result = true;  
	            break;  
	        }  
	    }  
	    if (!result) {        
	        // 按指定模式在字符串查找路径格式  
	        String pattern1 = "(.*)(\\.log\\.{0,1}\\d{0,})$";  
	        Pattern r1 = Pattern.compile(pattern1);  
	        Matcher m1 = r1.matcher(filepath);  
	        if (!m1.matches()) {  
	            Exception Exception = new Exception("file name is Illage") ;  
	                throw Exception   ;  
	            }  
	    }  
	    filepath.replaceAll("../", "");  
	    filepath.replaceAll("..\\\\", "");  
	    return filepath;       
	}  

}
