package com.tensquare.client.listener;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.exceptions.ClientException;
import org.omg.CORBA.Environment;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import com.tensquare.utils.SmsUtil;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
@RabbitListener(queues = "sms")
public class SmsListener {
    @Autowired
    private SmsUtil smsUtil;

    @RabbitHandler
    public void getMessage(Map map){
        try {
            String mobile = (String) map.get("mobile");
            String code =  map.get("code").toString();
            Map paramMap = new HashMap<>();
            paramMap.put("code",code);
            String param = JSON.toJSONString(paramMap);
            smsUtil.sendSms(mobile,"SMS_178761398","品优购",param);
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
