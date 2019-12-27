package com.tensquare.qa.client;

import com.tensquare.qa.client.client.LabelClientImpl;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "tensquare-base",fallback = LabelClientImpl.class)
public interface LabelClient {
    @GetMapping("label/{id}")
    Result findByLabelId(@PathVariable("id") String labelId);
}
