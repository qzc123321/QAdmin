package com.qinzhucheng.admin.base.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qinzhucheng.admin.utils.StringUtils;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;
import org.springframework.web.util.JavaScriptUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Api
@RestController
public class BaseController {

    public final static Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Resource
    public HttpServletRequest request;
    @Resource
    public HttpServletResponse response;

    public void ajaxDoneSuccess(HttpServletResponse response, String message) {
        this.ajaxDoneSuccess(response, message, (Object)null);
    }

    public void ajaxDoneSuccess(HttpServletResponse response, String message, Object data) {
        JSONObject json = new JSONObject();
        json.put("code", 200);
        if(StringUtils.isNotBlank(message)) {
            json.put("msg", message);
        } else {
            json.put("msg", "操作成功!");
        }

        if(data != null) {
            if(data instanceof JSONObject) {
                json.put("data", data);
            } else {
                String jsonStr;
                if(data instanceof List) {
                    jsonStr = JSONArray.toJSONString(data, true);
                    JSONArray array = JSONArray.parseArray(jsonStr);
                    json.put("data", array);
                } else {
                    jsonStr = JSONObject.toJSONString(data, true);
                    json.put("data", JSONObject.parseObject(jsonStr));
                }
            }
        }

        this.renderJson(response, json.toString());
    }

    public void ajaxDoneError(HttpServletResponse response, String message) {
        this.ajaxDoneError(response, 300, message, (Object)null);
    }

    public void ajaxDoneError(HttpServletResponse response, int code, String message, Object data) {
        JSONObject json = new JSONObject();
//        json.put("state", Integer.valueOf("0"));
        json.put("code", code);
        if(StringUtils.isNotBlank(message)) {
            json.put("msg", message);
        } else {
            json.put("msg", "操作失败!");
        }

        if(data != null) {
            if(data instanceof JSONObject) {
                json.put("data", data);
            } else {
                String jsonStr;
                if(data instanceof List) {
                    jsonStr = JSONArray.toJSONString(data, true);
                    JSONArray array = JSONArray.parseArray(jsonStr);
                    json.put("data", array);
                } else {
                    jsonStr = JSONObject.toJSONString(data, true);
                    json.put("data", JSONObject.parseObject(jsonStr));
                }
            }
        }

        this.renderJson(response, json.toString());
    }

    public void ajaxAuthError(HttpServletResponse response, int code,  String message) {
        this.ajaxDoneError(response, code, message, (Object)null);
    }

    public void renderJson(HttpServletResponse response, String text) {
        this.render(response, "application/json;charset=UTF-8", text);
    }

    private void render(HttpServletResponse response, String contentType, String text) {
        response.setContentType(contentType);
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);

        try {
            response.getWriter().write(text);
        } catch (IOException var5) {
            this.logger.error(var5.getMessage(), var5);
        }

    }

    public String getPara(String name) {
        String str = this.request.getParameter(name);
        if(StringUtils.isNotBlank(str)) {
            str = str.trim();
        }

        str = StringUtils.escapeSql(str);
        str = HtmlUtils.htmlEscape(str);
        str = JavaScriptUtils.javaScriptEscape(str);
        return str;
    }

    public String getPara(String name, String defaultValue) {
        String result = this.request.getParameter(name);
        return result != null && !"".equals(result)?result:defaultValue;
    }

    public Integer getParaToInt(String name) {
        return this.toInt(this.request.getParameter(name), (Integer)null);
    }

    public Integer getParaToInt(String name, Integer defaultValue) {
        return this.toInt(this.request.getParameter(name), defaultValue);
    }

    public Long getParaToLong(String name) {
        return this.toLong(this.request.getParameter(name), (Long)null);
    }

    public Long getParaToLong(String name, Long defaultValue) {
        return this.toLong(this.request.getParameter(name), defaultValue);
    }

    public Boolean getParaToBoolean(String name) {
        return this.toBoolean(this.request.getParameter(name), (Boolean)null);
    }

    public Boolean getParaToBoolean(String name, Boolean defaultValue) {
        return this.toBoolean(this.request.getParameter(name), defaultValue);
    }

    public Map<String, String[]> getParaMap() {
        return this.request.getParameterMap();
    }

    private Integer toInt(String value, Integer defaultValue) {
        return value != null && !"".equals(value.trim())?(!value.startsWith("N") && !value.startsWith("n")?Integer.valueOf(Integer.parseInt(value)):Integer.valueOf(-Integer.parseInt(value.substring(1)))):defaultValue;
    }

    private Long toLong(String value, Long defaultValue) {
        return value != null && !"".equals(value.trim())?(!value.startsWith("N") && !value.startsWith("n")?Long.valueOf(Long.parseLong(value)):Long.valueOf(-Long.parseLong(value.substring(1)))):defaultValue;
    }

    private Boolean toBoolean(String value, Boolean defaultValue) {
        if(value != null && !"".equals(value.trim())) {
            value = value.trim().toLowerCase();
            if(!"1".equals(value) && !"true".equals(value)) {
                if(!"0".equals(value) && !"false".equals(value)) {
                    throw new RuntimeException("Can not parse the parameter \"" + value + "\" to boolean value.");
                } else {
                    return Boolean.FALSE;
                }
            } else {
                return Boolean.TRUE;
            }
        } else {
            return defaultValue;
        }
    }
}
