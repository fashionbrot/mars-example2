package com.github.fashionbrot.console.filter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.fashionbrot.tool.CollectionUtil;
import com.github.fashionbrot.common.util.XssFilterUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 重写请求参数处理函数
 */
@Slf4j
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private HttpServletRequest orgRequest;
    private boolean isIncludeRichText;

    private final static String JSON_CONTENT_TYPE = "application/json";
    private final static String CONTENT_TYPE = "Content-Type";

    public XssHttpServletRequestWrapper(HttpServletRequest request, boolean isIncludeRichText){
        super( request );
        orgRequest = request;
        this.isIncludeRichText = isIncludeRichText;
    }



    @Override
    public ServletInputStream getInputStream() throws IOException{
        // 非json处理
        if( !JSON_CONTENT_TYPE.equalsIgnoreCase(super.getHeader( CONTENT_TYPE )) ){
            return super.getInputStream();
        }
        InputStream in = super.getInputStream();
        String body = IOUtils.toString(in,"UTF-8");

        //空串处理直接返回
        if( StringUtils.isBlank(body)){
            return super.getInputStream();
        }

//        JSONObject b = dealBody((JSONObject) JSON.toJSON((body)));
        return new RequestCachingInputStream(body.getBytes());
    }
    /**
     * 两种方式解决JSONArray
     * 第一种直接传入处理之后还是原对象的地址
     * 第二种每次传入的和处理之后的不一样需要重新new JSONArray()
     * @param jsonObject jsonObject
     * @return JSONObject
     */
    private JSONObject dealBody(JSONObject jsonObject){
        Map<String, Object> objectMap = jsonObject.getInnerMap();
        objectMap.forEach(((key, value) -> {
            if (value instanceof JSONObject){
                objectMap.put(key, dealBody((JSONObject) value));
            }else if (value instanceof JSONArray){
                ((JSONArray) value).forEach(arrayObject -> dealBody((JSONObject) arrayObject));
            }else if (value instanceof String) {
                objectMap.put(key, xssFilter((String) value));
            }
        }));
        return jsonObject;
    }



    /**
     * 覆盖getParameter方法，将参数名和参数值都做xss过滤.
     * 如果需要获得原始的值，则通过super.getParameterValues(name)来获取
     * getParameterNames,getParameterValues和getParameterMap也可能需要覆盖
     */
    @Override
    public String getParameter(String name) {
        boolean isHtml = "content".equals(name) || name.endsWith("WithHtml");
        if (isHtml && !isIncludeRichText) {
            return super.getParameter(name);
        }
        name = xssFilter(name);
        String value = super.getParameter(name);
        if (StringUtils.isNotBlank(value)) {
            value = xssFilter(value);
        }
        return value;
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] arr = super.getParameterValues(name);
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = xssFilter(arr[i]);
            }
        }
        return arr;
    }

    @Override
    public Map<String, String[]> getParameterMap(){
        Map<String, String[]> parameters = super.getParameterMap();
        if (CollectionUtil.isNotEmpty(parameters)){
            Map<String, String[]> map = new LinkedHashMap<>();
            for( String key : parameters.keySet() ){
                String[] values = parameters.get( key );
                for( int i = 0; i < values.length; i++ ){
                    values[i] = xssFilter( values[i] );
                }
                map.put( key, values );
            }
            return map;
        }
        return parameters;
    }


    /**
     * 覆盖getHeader方法，将参数名和参数值都做xss过滤。<br/>
     * 如果需要获得原始的值，则通过super.getHeaders(name)来获取<br/>
     * getHeaderNames 也可能需要覆盖
     */
    @Override
    public String getHeader(String name) {
        name = xssFilter(name);
        String value = super.getHeader(name);
        if (StringUtils.isNotBlank(value)) {
            value = xssFilter(value);
        }
        return value;
    }

    /**
     * 获取最原始的request
     * @return
     */
    public HttpServletRequest getOrgRequest() {
        return orgRequest;
    }

    /**
     * 获取最原始的request的静态方法
     * @return
     */
    public static HttpServletRequest getOrgRequest(HttpServletRequest req) {
        if (req instanceof XssHttpServletRequestWrapper) {
            return ((XssHttpServletRequestWrapper) req).getOrgRequest();
        }
        return req;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    private static String reg = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|"
            + "(\\b(select|update|union|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";

    private static Pattern sqlPattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);

    private boolean isValid(String str) {
        if (sqlPattern.matcher(str).find()) {
            log.error("xss sql keyword str:{}",str);
            return false;
        }
        return true;
    }

    private String xssFilter(String input){
        if (RequestFilter.REQUEST_HEADER_UUID_KEY.equals(input)){
            return input;
        }
        if (!isValid(input)){
            return input+"warning";
        }
        return XssFilterUtil.clean(input);
    }

    /**
     * <pre>
     * servlet中inputStream只能一次读取，后续不能再次读取inputStream
     * xss过滤body后，重新把流放入ServletInputStream中
     * </pre>
     */
    private static class RequestCachingInputStream extends ServletInputStream {
        private final ByteArrayInputStream inputStream;
        public RequestCachingInputStream(byte[] bytes) {
            inputStream = new ByteArrayInputStream(bytes);
        }

        @Override
        public int read() throws IOException {
            return inputStream.read();
        }

        @Override
        public boolean isFinished() {
            return inputStream.available() == 0;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setReadListener( ReadListener readListener ){
        }
    }

}