package com.ylv.modules.user.repository;

import com.ylv.modules.user.bean.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {
}
