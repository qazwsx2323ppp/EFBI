package com.nanting.point.nantingdianping.service;

import com.nanting.point.nantingdianping.dto.LoginFormDTO;
import com.nanting.point.nantingdianping.dto.Result;
import jakarta.servlet.http.HttpSession;

public interface userService {
    Result sendCode(String phone, HttpSession session);

    Result login(LoginFormDTO loginForm, HttpSession session);

    Result sign();

    Result signCount();
}
