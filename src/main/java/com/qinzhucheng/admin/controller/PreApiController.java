package com.qinzhucheng.admin.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qinzhucheng.admin.base.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/preApi")
public class PreApiController extends BaseController {

    @RequestMapping("/step1")
    public void getStep1() {
        String param1 = this.getPara("param1");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", param1);
        this.ajaxDoneSuccess(response, "成功", jsonObject);
    }

    @RequestMapping("/step2")
    public void getStep2() {
        String param2 = this.getPara("param2");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", "2" + param2);
        this.ajaxDoneSuccess(response, "成功", jsonObject);
    }

    @RequestMapping("/step3")
    public void getStep3() {
        String param1 = this.getPara("param1");
        String param3 = this.getPara("param3");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", param1 + "3" + param3);
        this.ajaxDoneSuccess(response, "成功", jsonObject);
    }

    @RequestMapping("/step4")
    public void getStep4() {
        String param1 = this.getPara("param1");
        JSONArray ja = new JSONArray();
        JSONObject jsonObject;
        JSONObject jsonObject2;
        JSONArray ja2;
        for (int i = 0; i < 3; i++) {
            jsonObject = new JSONObject();
            jsonObject2 = new JSONObject();
            jsonObject2.put("param4", "4444");
            jsonObject2.put("param5", "5555");
            jsonObject.put("param2", jsonObject2);
            ja2 = new JSONArray();
            for (int j = 0; j < 2; j++) {
                jsonObject2 = new JSONObject();
                jsonObject2.put("param6", "6666");
                jsonObject2.put("param7", "7777");
                ja2.add(jsonObject2);
            }
            jsonObject.put("param1", ja2);
            jsonObject.put("param3", param1 + "|" + i);
            ja.add(jsonObject);
        }
        this.ajaxDoneSuccess(response, "成功", ja);
    }

    @RequestMapping("/step5")
    public void getStep5() {
        JSONArray ja = new JSONArray();
        JSONObject jo;
        for (int i = 0; i < 4; i++) {
            jo = new JSONObject();
            jo.put("name", "a" + i);
            jo.put("num", i);
            ja.add(jo);
        }
        this.ajaxDoneSuccess(response, "成功", ja);
    }
}
