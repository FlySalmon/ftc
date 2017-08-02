package com.eif.ftc.dal.dao;

import com.eif.ftc.dal.model.FundOfflineDetail;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface FundOfflineDetailMapper extends Mapper<FundOfflineDetail> {
    List<FundOfflineDetail> queryOfflineAssetByMemberNO(@Param("memberNo") String memberNo,@Param("softDeleted") Integer softDeleted);
    FundOfflineDetail queryOfflineAssetByUuid(@Param("fundOfflineDetailUuid") String fundOfflineDetailUuid,@Param("softDeleted") Integer softDeleted);
}