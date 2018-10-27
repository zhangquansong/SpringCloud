package com.zqs.api.util;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.*;
/**
 * 说明：参数封装Map
 * 修改时间：2014年9月20日
 * @version
 */
public class PageData extends HashMap implements Map{

	private static final long serialVersionUID = 1L;

	Map map = null;
	HttpServletRequest request;
	public PageData(HttpServletRequest request){
		this(request, false);
	}

	public PageData(HttpServletRequest request, boolean upperCase){
		this.request = request;
		String enctype = request.getContentType();
//	    if (StringUtils.isNotBlank(enctype) && enctype.contains("multipart/form-data")) {
//	    	map = getFileData(upperCase);
//	    }else {
	    	map = getWWWForm(upperCase);
//	    }
	}
	private Map getFileData(boolean upperCase) {
		Map returnMap = new HashMap();
		try {
			// Create a factory for disk-based file items
			FileItemFactory factory = new DiskFileItemFactory();

			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
			// Parse the request
			List<FileItem> items = upload.parseRequest(request);

			// Process the uploaded items
			Iterator<FileItem> iter = items.iterator();

			// Parameters map
			Map<String,String> params = new HashMap<String,String>();

			// Do list
			while (iter.hasNext())
			{
			    FileItem item = iter.next();

			    // Form Field
			    if (item.isFormField())
			    {
			        // Field name
			        String name = item.getFieldName();

			        // Set charset = UTF-8 Default = ISO-8859-1
			        // Get field value
			        String value = item.getString("utf-8");

			        // Put into map
			        params.put(name, value.trim());
			    }
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnMap;
	}
	private Map getWWWForm(boolean upperCase) {
		Map properties = request.getParameterMap();
		Map returnMap = new HashMap();
		Iterator entries = properties.entrySet().iterator();
		Entry entry;
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			entry = (Entry) entries.next();
			name = (String) entry.getKey();
			if(upperCase) {
				name = name.toUpperCase();
			}
			Object valueObj = entry.getValue();
			if(null == valueObj){
				value = "";
				returnMap.put(name, value);
			}else if(name.lastIndexOf("[]") > 0){
				name = name.replace("[]", "");
				returnMap.put(name, valueObj);
			}else if(valueObj instanceof String[]){
				String[] values = (String[])valueObj;
				if(values.length > 1) {
					returnMap.put(name, values);
				}else {
					for(int i=0;i<values.length;i++){
						 value = values[i] + ",";
					}
					value = value.substring(0, value.length()-1);
					returnMap.put(name, value);
				}
			}else{
				value = valueObj.toString();
				returnMap.put(name, value);
			}
			//returnMap.put(name, value);
		}
		return returnMap;
	}

	/*public PageData(HttpServletRequest request){
		// Create a factory for disk-based file items
        FileItemFactory factory = new DiskFileItemFactory();  

        // Create a new file upload handler  
        ServletFileUpload upload = new ServletFileUpload(factory);
        // Parse the request  
        List<FileItem> items = upload.parseRequest(req);  

        // Process the uploaded items  
        Iterator<FileItem> iter = items.iterator();  

        // Parameters map  
        Map<String,String> params = new HashMap<String,String>();  

        // Do list  
        while (iter.hasNext())  
        {  
            FileItem item = iter.next();  
             
            // Form Field  
            if (item.isFormField())  
            {  
                // Field name  
                String name = item.getFieldName();  
                 
                // Set charset = UTF-8 Default = ISO-8859-1   
                // Get field value  
                String value = item.getString("utf-8");  
                  
                // Put into map  
                params.put(name, value.trim());  
            }  
        }  
	}*/

	public PageData() {
		map = new HashMap();
	}

	@Override
	public Object get(Object key) {
		Object obj = null;
		if(map.get(key) instanceof Object[]) {
			Object[] arr = (Object[])map.get(key);
			obj = request == null ? arr:(request.getParameter((String)key) == null ? arr:arr.length > 1 ? arr:arr[0]);
		} else {
			obj = map.get(key);
		}
		return obj;
	}

	public String[] getObj(Object key) {
		if(map.get(key) instanceof Object[]) {
			return (String[])map.get(key);
		}else {
			return new String[]{(String)map.get(key)};
		}
	}

	public String getString(Object key) {
		return (String)get(key);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object put(Object key, Object value) {
		return map.put(key, value);
	}

	@Override
	public Object remove(Object key) {
		return map.remove(key);
	}

	public void clear() {
		map.clear();
	}

	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return map.containsKey(key);
	}

	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return map.containsValue(value);
	}

	public Set entrySet() {
		// TODO Auto-generated method stub
		return map.entrySet();
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return map.isEmpty();
	}

	public Set keySet() {
		// TODO Auto-generated method stub
		return map.keySet();
	}

	@SuppressWarnings("unchecked")
	public void putAll(Map t) {
		// TODO Auto-generated method stub
		map.putAll(t);
	}

	public int size() {
		// TODO Auto-generated method stub
		return map.size();
	}

	public Collection values() {
		// TODO Auto-generated method stub
		return map.values();
	}

}
