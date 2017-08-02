package com.eif.ftc.service.transfer.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.eif.framework.pagination.pagehelper.Page;
import com.eif.framework.pagination.pagehelper.PageHelper;
import com.eif.ftc.dal.dao.FundTransfereeOrderMapper;
import com.eif.ftc.dal.model.FundTransfereeOrder;
import com.eif.ftc.dal.model.FundTransfereeOrderExample;
import com.eif.ftc.facade.fund.transfer.oper.request.GetFundTransfereeOrderByPageRequest;
import com.eif.ftc.service.transfer.FundTransfereeOperService;

import tk.mybatis.mapper.util.StringUtil;

@Service("fundTransfereeOperService")
public class FundTransfereeOperServiceImpl implements FundTransfereeOperService {

    @Autowired
	private FundTransfereeOrderMapper fundTransfereeOrderMapper;
    
	@Override
	public FundTransfereeOrder getByBusinessOrderItemNo(String businessOrderItemNo) {
		FundTransfereeOrder queryOrder = new FundTransfereeOrder();
		if (StringUtil.isEmpty(businessOrderItemNo)) {
			return null;
		}
		queryOrder.setBusinessOrderItemNo(businessOrderItemNo);

		return fundTransfereeOrderMapper.selectOne(queryOrder);
	}

	@Override
	public FundTransfereeOrder getByFundTransfereeOrderNo(String fundTransfereeOrderNo) {
		FundTransfereeOrder queryOrder = new FundTransfereeOrder();
		if (StringUtil.isEmpty(fundTransfereeOrderNo)) {
			return null;
		}
		queryOrder.setFundTransfereeOrderNo(fundTransfereeOrderNo);

		return fundTransfereeOrderMapper.selectOne(queryOrder);
	}

	@Override
	public List<FundTransfereeOrder> getByBusinessOrderItemNoList(List<String> businessOrderItemNoList) {
		if (CollectionUtils.isEmpty(businessOrderItemNoList)) {
			return null;
		}
		
		FundTransfereeOrderExample example = new FundTransfereeOrderExample();
		FundTransfereeOrderExample.Criteria criteria = example.createCriteria();
		criteria.andBusinessOrderItemNoIn(businessOrderItemNoList);
		return fundTransfereeOrderMapper.selectByExample(example);
	}

	@Override
	public List<FundTransfereeOrder> getByFundTransfereeOrderNoList(List<String> fundTransfereeOrderNoList) {
		if (CollectionUtils.isEmpty(fundTransfereeOrderNoList)) {
			return null;
		}
		
		FundTransfereeOrderExample example = new FundTransfereeOrderExample();
		FundTransfereeOrderExample.Criteria criteria = example.createCriteria();
		criteria.andFundTransfereeOrderNoIn(fundTransfereeOrderNoList);
		return fundTransfereeOrderMapper.selectByExample(example);
	}

	@Override
	public List<FundTransfereeOrder> getByOrderBasicInfo(String memberNo, Long mktProductId, Integer status) {
		if (StringUtil.isEmpty(memberNo) && mktProductId == null) {
			return null;
		}
		
		FundTransfereeOrderExample example = new FundTransfereeOrderExample();
		FundTransfereeOrderExample.Criteria criteria = example.createCriteria();
		if (!StringUtil.isEmpty(memberNo)) {
			criteria.andMemberNoEqualTo(memberNo);
		}
		if (mktProductId != null) {
			criteria.andMktProductIdEqualTo(mktProductId);	
		}
		if (status != null) {
			criteria.andStatusEqualTo(status);
		}
		return fundTransfereeOrderMapper.selectByExample(example);
	}

	@Override
	public Page<FundTransfereeOrder> getByPage(GetFundTransfereeOrderByPageRequest pageRequest) {
		FundTransfereeOrderExample example = new FundTransfereeOrderExample();
		FundTransfereeOrderExample.Criteria criteria = example.createCriteria();
		if (!StringUtil.isEmpty(pageRequest.getMemberNo())) {
			criteria.andMemberNoEqualTo(pageRequest.getMemberNo());
			example.setOrderByClause("create_time desc");
		}
		if (pageRequest.getMktProductId() != null) {
			criteria.andMktProductIdEqualTo(pageRequest.getMktProductId());	
		}
		if (pageRequest.getStatus() != null) {
			criteria.andStatusEqualTo(pageRequest.getStatus());
		}
		if (pageRequest.getCreateStartTime() != null) {
			criteria.andCreateTimeGreaterThanOrEqualTo(pageRequest.getCreateStartTime());
		}
		if (pageRequest.getCreateEndTime() != null) {
			criteria.andCreateTimeLessThanOrEqualTo(pageRequest.getCreateEndTime());
		}
		if (!StringUtil.isEmpty(pageRequest.getRefTransferorOrderNo())) {
			criteria.andRefFundTransferorOrderNoEqualTo(pageRequest.getRefTransferorOrderNo());
		}

        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize(), pageRequest.isCalCount());
        return (Page<FundTransfereeOrder>) fundTransfereeOrderMapper.selectByExample(example);
	}

	@Override
	public List<FundTransfereeOrder> getByRefFundTransferorOrderNo(String refFundTransferorOrderNo, Integer status) {
		if (StringUtil.isEmpty(refFundTransferorOrderNo)) {
			return null;
		}
		
		FundTransfereeOrderExample example = new FundTransfereeOrderExample();
		FundTransfereeOrderExample.Criteria criteria = example.createCriteria();
		criteria.andRefFundTransferorOrderNoEqualTo(refFundTransferorOrderNo);
		if (status != null) {
			criteria.andStatusEqualTo(status);
		}
		return fundTransfereeOrderMapper.selectByExample(example);
	}

}
