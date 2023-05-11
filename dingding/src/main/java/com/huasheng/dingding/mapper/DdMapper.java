package com.huasheng.dingding.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huasheng.dingding.domain.dto.LoginUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DdMapper extends BaseMapper<LoginUser> {
}
