package com.huasheng.dingding.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huasheng.dingding.domain.entity.CustomerNeedType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CustomerNeedTypeMapper extends BaseMapper<CustomerNeedType> {
}
