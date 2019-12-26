package com.tensquare.friend.client;


import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient("tensquare-user")
public interface UserClient {
    @PutMapping("/user/follow/{userid}/{x}")
    Result updateFollow(@PathVariable("userid") String userid,@PathVariable("x") int x);
    @PutMapping("/user/fans/{userid}/{x}")
    Result updateFans(@PathVariable("userid") String userid,@PathVariable("x") int x);

}
