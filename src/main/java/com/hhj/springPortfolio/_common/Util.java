package com.hhj.springPortfolio._common;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Util {
	
	public String[] getServerInfo(HttpServletRequest request) throws UnknownHostException {
		String referer = request.getHeader("REFERER");
		if(referer == null || referer.trim().equals("")) {
			referer = "";
		}
		String path = request.getContextPath();
		String url = request.getRequestURL().toString();
		String uri = request.getRequestURI().toString();
		String ip = Inet4Address.getLocalHost().getHostAddress();
		
		String ip6 = request.getHeader("X-Forwarded-For");  
        if (ip6 == null || ip6.length() == 0 || "unknown".equalsIgnoreCase(ip6)) {  
        	ip6 = request.getHeader("Proxy-Client-IP");  
        }  
        if (ip6 == null || ip6.length() == 0 || "unknown".equalsIgnoreCase(ip6)) {  
        	ip6 = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if (ip6 == null || ip6.length() == 0 || "unknown".equalsIgnoreCase(ip6)) {  
        	ip6 = request.getHeader("HTTP_CLIENT_IP");  
        }  
        if (ip6 == null || ip6.length() == 0 || "unknown".equalsIgnoreCase(ip6)) {  
        	ip6 = request.getHeader("HTTP_X_FORWARDED_FOR");  
        }  
        if (ip6 == null || ip6.length() == 0 || "unknown".equalsIgnoreCase(ip6)) {  
        	ip6 = request.getRemoteAddr();  
        }
        
        String[] imsiUriArray = uri.split("/"); // {jspPortfolioModel2 , dashBoard_index.do} length = 2
        String imsiUriFileName = imsiUriArray[imsiUriArray.length - 1]; // dashBoard_index.do
        
        if(path.equals("/" + imsiUriFileName)) {
        	imsiUriFileName = "dashBoard_index.do";
        }
        
        imsiUriFileName = imsiUriFileName.replace(".do", ""); // dashBoard_index
          
        String[] imsiArray = imsiUriFileName.split("_"); // {dashBoard , index}
        String folderName = imsiArray[0]; // dashBoard
        String fileName = imsiArray[1]; // index
      
             		    		  
		String[] array = new String[8];
		array[0] = referer;
		array[1] = path;
		array[2] = url;
		array[3] = uri;
		array[4] = ip;
		array[5] = ip6;
		array[6] = folderName;
		array[7] = fileName;

		return array;
	}
	public String[] getSessionChk(HttpServletRequest request) {
		int sessionNo = 0;
		String sessionId = "";
		String sessionName = "";
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("sessionNo") != null) {
			sessionNo = (Integer)session.getAttribute("sessionNo");
		}
		if(session.getAttribute("sessionId") != null) {
			sessionId = (String)session.getAttribute("sessionId");
		}
		if(session.getAttribute("sessionName") != null) {
			sessionName = (String)session.getAttribute("sessionName");
		}
		String[] result = new String[3];
		result[0] = sessionNo + "";
		result[1] = sessionId;
		result[2] = sessionName;
		return result;
	}
	public String getCheckString(String str) {
		String result = "";
		result = str;
		result = result.replace("&", "&amp;");
		result = result.replace(">", "&gt;");
		result = result.replace("<", "&lt;");
		result = result.replace("\"", "&quot;");
		result = result.replace("'", "&apos;");
		return result;
	}
	public int getNumberCheck(String str, int defaultStr) {
		int result = 0;
		
		String temp = str;
		
		if(temp == null || temp.trim().equals("")) {
			temp = "-";
		} 	
		for(int i=0; i<=9; i++) {
			temp = temp.replace(i + "", "");
		}		
		/*
		 * try {
		 *  Double.parseDouble(str); 
		 *  return Integer.parseInt(str);
		 * } catch(Exception e) { 
		 * 	return Integer.parseInt(imsiDefaultNumber); 
		 * }
		 */
		if(temp.equals("")) { // 숫자로만 구성 된
			result = Integer.parseInt(str);
		} else {
			result = defaultStr;
		}
		return result;
	}
	
	public String getNullBlankCheck(String str) {
		String result = str;
		if(result == null || result.trim().equals("")) {
			result = "";
		}
		return result;
	}
	
	public int[] getCalendar() {
		Calendar cal = Calendar.getInstance();
		
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		//System.out.println("현재 시각 - > " + year + "년" + month + "월" + day + "일" + hour + "시" + minute + "분" + second + "초");
		
		int[] result = new int[6];
		result[0] = year;
		result[1] = month;
		result[2] = day;
		result[3] = hour;
		result[4] = minute;
		result[5] = second;
		
		return result;
	}
	public String getDateTime() {
		int[] intResult = getCalendar();
		String result = "";
		int imsi = 0;
		
		result += intResult[0];
		for(int i = 1; i<intResult.length; i++) {
			imsi = intResult[i];
			
			if(imsi < 10) {
				result += "0" + imsi;
			} else {
				result += imsi;
			}		
		}
		return result;
	}
	public String createUuid() {
		return UUID.randomUUID().toString(); //java.util
	}
	public Map<String, Integer> getPagerMap(int pageNumber, int pageSize, int blockSize, int totalRecord) {
		int jj = totalRecord - pageSize * (pageNumber - 1);	   
		int startRecord = pageSize * (pageNumber -1) + 1;
		int lastRecord = pageSize * pageNumber;
		if(lastRecord > totalRecord) {
			lastRecord = totalRecord;
		}
  	  
		int totalPage = 0;
		int startPage = 1;
		int lastPage = 1;
		if(totalRecord > 0) {
			totalPage = totalRecord / pageSize + (totalRecord % pageSize == 0 ? 0 : 1);
			startPage = (pageNumber /  blockSize - (pageNumber % blockSize != 0 ? 0 : 1)) * blockSize + 1;
			lastPage = startPage + blockSize -1;
			if(lastPage > totalPage) {
  			  	lastPage = totalPage;
			}
		}
		Map<String, Integer> map = new HashMap<>();
		map.put("jj", jj);
		map.put("startRecord", startRecord);
		map.put("lastRecord", lastRecord);
		map.put("totalPage", totalPage);
		map.put("startPage", startPage);
		map.put("lastPage", lastPage);
		return map;
	}
	    public String security (String str) {
	    	String result = "";
	        try {
	            MessageDigest md = MessageDigest.getInstance("SHA-512"); 
	            md.update(str.getBytes()); // 
	 
	            byte byteData[] = md.digest();
	 
	            StringBuffer sb = new StringBuffer(); 
	            for(int i=0; i<byteData.length; i++) {
	                sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
	            }	 
	            result = sb.toString();
	             // 결과물이 출력됨. aed19017dbb4d25a580b7f9e012e29be089bd1f3
	        } catch(Exception e){
	           
	        }
	        return result;
	    }
}
