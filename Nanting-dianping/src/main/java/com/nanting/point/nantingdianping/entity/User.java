package com.nanting.point.nantingdianping.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String phone;
    private String password;
    private String nickName;//昵称
    private String icon = "";//头像
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
}
