package com.child.box.core.base;

import com.child.box.core.session.UserRef;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public abstract class BaseService<T extends BaseModel> {

    public void prepareSave(T model) {
        Date date = new Date();
        model.setCreatedAt(date);
        model.setUpdatedAt(date);
        model.setStatus(Constant.STATUS_VALID);
    }

    public void prepareSave(T model, UserRef user) {
        Date date = new Date();
        model.setCreatedAt(date);
        model.setUpdatedAt(date);
        model.setStatus(Constant.STATUS_VALID);
        if (user != null) {
            model.setCreateUser(user.getId());
            model.setUpdateUser(user.getId());
        }
    }

    public void prepareDel(T model) {
        model.setUpdatedAt(new Date());
        model.setStatus(Constant.STATUS_UNVALID);
    }

    public void prepareUpdate(T model) {
        model.setUpdatedAt(new Date());
    }

    public void prepareUpdate(T model, UserRef user) {
        model.setUpdatedAt(new Date());
        if(user!=null){
            model.setUpdateUser(user.getId());
        }
    }

    public List<Long> getAdminIdList(UserRef user) {
        return this.getAdminIds();
    }

    private List<Long> getAdminIds() {
        List<Long> adminIds = new ArrayList<>();

        return adminIds;
    }

}
