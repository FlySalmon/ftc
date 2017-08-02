package com.eif.ftc.facade.dto;

import com.eif.framework.common.utils.pkg.BaseDTO;

import java.io.Serializable;
import java.util.Date;

public class FundAcctOrderBean extends BaseDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getAssetAccountNo() {
		return assetAccountNo;
	}

	public void setAssetAccountNo(String assetAccountNo) {
		this.assetAccountNo = assetAccountNo;
	}

	public String getFundAcctOrderNo() {
		return fundAcctOrderNo;
	}

	public void setFundAcctOrderNo(String fundAcctOrderNo) {
		this.fundAcctOrderNo = fundAcctOrderNo;
	}

	public String getRefFundTransOrderNo() {
		return refFundTransOrderNo;
	}

	public void setRefFundTransOrderNo(String refFundTransOrderNo) {
		this.refFundTransOrderNo = refFundTransOrderNo;
	}

	public Integer getFundAcctOperType() {
		return fundAcctOperType;
	}

	public void setFundAcctOperType(Integer fundAcctOperType) {
		this.fundAcctOperType = fundAcctOperType;
	}

	public String getTrackingNo() {
		return trackingNo;
	}

	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}

	public String getRefFundBusinessCode() {
		return refFundBusinessCode;
	}

	public void setRefFundBusinessCode(String refFundBusinessCode) {
		this.refFundBusinessCode = refFundBusinessCode;
	}

	public Integer getBizChannel() {
		return bizChannel;
	}

	public void setBizChannel(Integer bizChannel) {
		this.bizChannel = bizChannel;
	}

	public Integer getFundInteractType() {
		return fundInteractType;
	}

	public void setFundInteractType(Integer fundInteractType) {
		this.fundInteractType = fundInteractType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getTotalExpiryTime() {
		return totalExpiryTime;
	}

	public void setTotalExpiryTime(Date totalExpiryTime) {
		this.totalExpiryTime = totalExpiryTime;
	}

	public Date getCurStatusExpiryTime() {
		return curStatusExpiryTime;
	}

	public void setCurStatusExpiryTime(Date curStatusExpiryTime) {
		this.curStatusExpiryTime = curStatusExpiryTime;
	}

	public String getRefAppSheetSerialNo() {
		return refAppSheetSerialNo;
	}

	public void setRefAppSheetSerialNo(String refAppSheetSerialNo) {
		this.refAppSheetSerialNo = refAppSheetSerialNo;
	}

	public String getExtInfoOrderNo() {
		return extInfoOrderNo;
	}

	public void setExtInfoOrderNo(String extInfoOrderNo) {
		this.extInfoOrderNo = extInfoOrderNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getExtField() {
		return extField;
	}

	public void setExtField(String extField) {
		this.extField = extField;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getOperatorNo() {
		return operatorNo;
	}

	public void setOperatorNo(String operatorNo) {
		this.operatorNo = operatorNo;
	}

	/**
     *  主键,所属表字段为t_ftc_fund_acct_order.id
     */
    private Long id;

    /**
     *  会员号,所属表字段为t_ftc_fund_acct_order.member_no
     */
    private String memberNo;

    /**
     *  用户产品ID,所属表字段为t_ftc_fund_acct_order.product_id
     */
    private Long productId;

    /**
     *  资产账户号,所属表字段为t_ftc_fund_acct_order.asset_account_no
     */
    private String assetAccountNo;

    /**
     *  基金账户业务单号,所属表字段为t_ftc_fund_acct_order.fund_acct_order_no
     */
    private String fundAcctOrderNo;

    /**
     *  关联基金交易业务单号,所属表字段为t_ftc_fund_acct_order.ref_fund_trans_order_no
     */
    private String refFundTransOrderNo;

    /**
     *  基金账户操作类型: 0 - 开户,所属表字段为t_ftc_fund_acct_order.fund_acct_oper_type
     */
    private Integer fundAcctOperType;

    /**
     *  防止幂等,追踪,所属表字段为t_ftc_fund_acct_order.tracking_no
     */
    private String trackingNo;

    /**
     *  ta业务代码,所属表字段为t_ftc_fund_acct_order.ref_fund_business_code
     */
    private String refFundBusinessCode;

    /**
     *  渠道类型：IOS = 11; ANDROID = 12; H5 = 13; WECHAT = 14; WEB = 20;,所属表字段为t_ftc_fund_acct_order.biz_channel
     */
    private Integer bizChannel;

    /**
     *  基金提交方式：1 - 实时; 2 - 文件,所属表字段为t_ftc_fund_acct_order.fund_interact_type
     */
    private Integer fundInteractType;

    /**
     *  状态: 1 - 资产账户开户中; 2 - 资产账户开户成功; 3 - 资产账户开户失败; 4 - 基金公司开户中; 5 - 基金公司开户成功;  6 - 基金公司开户失败,所属表字段为t_ftc_fund_acct_order.status
     */
    private Integer status;

    /**
     *  总有效期,所属表字段为t_ftc_fund_acct_order.total_expiry_time
     */
    private Date totalExpiryTime;

    /**
     *  当前状态过期时间,所属表字段为t_ftc_fund_acct_order.cur_status_expiry_time
     */
    private Date curStatusExpiryTime;

    /**
     *  ta申请单号, 可关联请求，确认，结果,所属表字段为t_ftc_fund_acct_order.ref_app_sheet_serial_no
     */
    private String refAppSheetSerialNo;

    /**
     *  reserved,所属表字段为t_ftc_fund_acct_order.ext_info_order_no
     */
    private String extInfoOrderNo;

    /**
     *  备注,所属表字段为t_ftc_fund_acct_order.remark
     */
    private String remark;

    /**
     *  业务扩展字段，保存json字串,所属表字段为t_ftc_fund_acct_order.ext_field
     */
    private String extField;

    /**
     *  创建时间,所属表字段为t_ftc_fund_acct_order.create_time
     */
    private Date createTime;

    /**
     *  更新时间,所属表字段为t_ftc_fund_acct_order.update_time
     */
    private Date updateTime;

    /**
     *  操作人号,所属表字段为t_ftc_fund_acct_order.operator_no
     */
    private String operatorNo;
}
