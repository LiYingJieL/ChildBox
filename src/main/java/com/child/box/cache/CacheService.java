package com.child.box.cache;

import com.child.box.core.session.UserRef;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.child.box.util.RedisUtil;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author xxm
 * @date 2018-07-04
 */
@Service
public class CacheService {

    private static final String SYS_KEY_PREFIX = "beile:sys:";
    private static final String SYS_LOGIN_KEY = SYS_KEY_PREFIX+"login:";

    private static final String API_KEY_PREFIX = "beile:api:";
    private static final String USER_LOGIN_KEY = API_KEY_PREFIX+"login:";
    private static final String REGREPEAT_KEY = API_KEY_PREFIX+"reg:";
    private static final String HOTTEST_BOOK_KEY = API_KEY_PREFIX+"hot";

    private static final String AREA_KEY = API_KEY_PREFIX+"area";
    private static final String PROVINCE_KEY = AREA_KEY + ":province";
    private static final String CITY_KEY = AREA_KEY + ":city";
    private static final String REGION_KEY = AREA_KEY + ":region";
    private static final String CITIES_KEY = AREA_KEY + ":cities";
    private static final String REGIONS_KEY = AREA_KEY + ":regions";

    private static final String REPEAT_KEY = API_KEY_PREFIX+"trade:repeat";
    private static final String REFUND_SHARE_KEY = SYS_KEY_PREFIX+"refund:id";

    private static final String EXPRESS_KEY = API_KEY_PREFIX+"express";

    @Autowired
    private RedisUtil redisUtil;
    @Resource(name = "redisTemplate")
    private ZSetOperations<String, String> zSetOperations;

    @Resource(name = "redisTemplate")
    private HashOperations<String, String, Map<Long, String>> mapOperations;
    @Resource(name = "redisTemplate")
    private ValueOperations<String, String> valueOperations;

    public void saveAdmin(String auth, Long  id){
        String key = SYS_LOGIN_KEY+auth;
        redisUtil.set(key,id.toString(),7L, TimeUnit.DAYS);
    }

    public UserRef getAdminByAuth(String auth) {
        if (StringUtils.isEmpty(auth)) {
            return null;
        }
        String key = SYS_LOGIN_KEY + auth;
        String id = (String) redisUtil.get(key);
        if (!StringUtils.isEmpty(id)) {
            UserRef user = new UserRef();
            user.setId(Long.valueOf(id));
            return user;
        }
        return null;
    }

    public void saveUser(String auth, Long  id){
        String key = USER_LOGIN_KEY+auth;
        redisUtil.set(key,id.toString(),7L, TimeUnit.DAYS);
    }

    public UserRef getUserByAuth(String auth) {
        if (StringUtils.isEmpty(auth)) {
            return null;
        }
        String key = USER_LOGIN_KEY + auth;
        String id = (String) redisUtil.get(key);
        if (!StringUtils.isEmpty(id)) {
            UserRef user = new UserRef();
            user.setId(Long.valueOf(id));
            return user;
        }
        return null;
    }

    public boolean checRegRepeatSubmit(String unionid) {
        String key = REGREPEAT_KEY + unionid;
        long value = redisUtil.increment(key, 1);
        if (value > 1) {
            return true;
        }
        redisUtil.expire(key, 2, TimeUnit.SECONDS);
        return false;
    }

    public String getHottest(){
        Set<String> sets = zSetOperations.reverseRange(HOTTEST_BOOK_KEY,0,1);
        if(sets.size()>0){
            Iterator<String> it = sets.iterator();
            if(it.hasNext()){
                return it.next();
            }
        }
        return "";
    }
    public void saveHottest(String bookName){
        if(!StringUtils.isEmpty(bookName)){
            zSetOperations.incrementScore(HOTTEST_BOOK_KEY,bookName,1);
        }
    }

    public boolean checkRepeatSubmit(String uri, String params) {
        String key = REPEAT_KEY + DigestUtils.md5Hex(uri + params);
        long value = redisUtil.increment(key, 1);
        if (value > 1) {
            return true;
        }
        redisUtil.expire(key, 2, TimeUnit.SECONDS);
        return false;
    }

    public boolean checkRefundOnEdit(String refundid) {
        String key = REFUND_SHARE_KEY + ":" + refundid;
        long value = redisUtil.increment(key, 1);
        return value > 1;
    }

    public void removeRefundOnEdit(String refundid) {
        String key = REFUND_SHARE_KEY + ":" + refundid;
        redisUtil.remove(key);
    }

}
