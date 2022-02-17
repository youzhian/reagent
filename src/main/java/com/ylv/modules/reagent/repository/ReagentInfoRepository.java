package com.ylv.modules.reagent.repository;

import com.ylv.modules.reagent.bean.ReagentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReagentInfoRepository extends JpaRepository<ReagentInfo,Long> {

    public int countByReagentNameEqualsAndIdIsNot(String name,Long id);

    public int countByReagentName(String name);
}
