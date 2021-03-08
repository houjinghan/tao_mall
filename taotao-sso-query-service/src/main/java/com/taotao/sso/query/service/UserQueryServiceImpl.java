package com.taotao.sso.query.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.common.service.RedisService;
import com.taotao.sso.query.api.UserQueryService;
import com.taotao.sso.query.bean.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserQueryServiceImpl implements UserQueryService {
    @Autowired
    private RedisService redisService;

    private static final Integer REDIS_TIME = 60*30;

    private static final ObjectMapper MAPPER = new ObjectMapper();
    @Override
    public User queryUserByToken(String token) {
        String key="TOKEN_"+token;

        String data=redisService.get(key);
        if(StringUtils.isEmpty(data)){
            return null;
        }
        redisService.expire(key,REDIS_TIME);
        try {
            MAPPER.readValue(data,User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
