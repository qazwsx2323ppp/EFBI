package com.nanting.point.nantingdianping.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private Long UserId;//用户id
    private String city;///城市
    private String introduce;//个人介绍
    private Integer fans;//粉丝数量
    private Integer follows;//关注的人的数量
    private boolean gender;//0：男  1：女
    private LocalDate birthday;
    private Integer credits;//积分
    private boolean isVip;//是否是会员(0：不是  1：是)
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
}
