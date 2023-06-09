package com.huasheng.dingding.domain.dto;

import lombok.Data;

@Data
public class DingTalkLoginDto {
    private String dingId;
    private String nick;
    private String openid;
    private String unionid;
}
