package com.child.box.core.base;

import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.base.delete.DeleteByPrimaryKeyMapper;
import tk.mybatis.mapper.common.base.insert.InsertSelectiveMapper;
import tk.mybatis.mapper.common.base.select.SelectAllMapper;
import tk.mybatis.mapper.common.base.select.SelectByPrimaryKeyMapper;
import tk.mybatis.mapper.common.base.update.UpdateByPrimaryKeyMapper;
import tk.mybatis.mapper.common.base.update.UpdateByPrimaryKeySelectiveMapper;
import tk.mybatis.mapper.common.ids.DeleteByIdsMapper;
import tk.mybatis.mapper.common.ids.SelectByIdsMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

@RegisterMapper
public interface MyMapper<T extends BaseModel> extends
        InsertSelectiveMapper<T>,
        InsertListMapper<T>,
        DeleteByPrimaryKeyMapper<T>,
        DeleteByIdsMapper<T>,
        UpdateByPrimaryKeySelectiveMapper<T>,
        UpdateByPrimaryKeyMapper<T>,
        SelectAllMapper<T>,
        SelectByIdsMapper,
        SelectByPrimaryKeyMapper<T>{
}
