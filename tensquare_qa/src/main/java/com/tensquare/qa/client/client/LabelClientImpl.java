package com.tensquare.qa.client.client;

import com.tensquare.qa.client.LabelClient;
import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Component;

@Component
public class LabelClientImpl implements LabelClient {
    @Override
    public Result findByLabelId(String labelId) {
        return new Result(false,StatusCode.ERROR,"熔断器工作了。。。");
    }
}
