package com.eif.ftc.dal.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_ftc_fund_acct_order")
public class FundAcctOrder {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 会员号
     */
    @Column(name = "member_no")
    private String memberNo;

    /**
     * 用户产品ID
     */
    @Column(name = "product_id")
    private Long productId;

    /**
     * 资产账户号
     */
    @Column(name = "asset_account_no")
    private String assetAccountNo;

    /**
     * 基金账户业务单号
     */
    @Column(name = "fund_acct_order_no")
    private String fundAcctOrderNo;

    /**
     * 基金交易账户号
     */
    @Column(name = "fund_trans_acct_no")
    private String fundTransAcctNo;

    /**
     * 关联基金交易业务单号
     */
    @Column(name = "ref_fund_trans_order_no")
    private String refFundTransOrderNo;

    /**
     * 基金账户操作类型: 0 - 开户
     */
    @Column(name = "fund_acct_oper_type")
    private Integer fundAcctOperType;

    /**
     * 防止幂等,追踪
     */
    @Column(name = "tracking_no")
    private String trackingNo;

    /**
     * ta业务代码
     */
    @Column(name = "ref_fund_business_code")
    private String refFundBusinessCode;

    /**
     * 渠道类型：IOS = 11; ANDROID = 12; H5 = 13; WECHAT = 14; WEB = 20;
     */
    @Column(name = "biz_channel")
    private Integer bizChannel;

    /**
     * 基金提交方式：1 - 实时; 2 - 文件
     */
    @Column(name = "fund_interact_type")
    private Integer fundInteractType;

    /**
     * 状态: 1 - 资产账户开户中; 2 - 资产账户开户成功; 3 - 资产账户开户失败; 4 - 基金公司开户中; 5 - 基金公司开户成功;  6 - 基金公司开户失败
     */
    private Integer status;

    /**
     * 总有效期
     */
    @Column(name = "total_expiry_time")
    private Date totalExpiryTime;

    /**
     * 当前状态过期时间
     */
    @Column(name = "cur_status_expiry_time")
    private Date curStatusExpiryTime;

    /**
     * ta申请单号, 可关联请求，确认，结果
     */
    @Column(name = "ref_app_sheet_serial_no")
    private String refAppSheetSerialNo;

    /**
     * reserved
     */
    @Column(name = "ext_info_order_no")
    private String extInfoOrderNo;

    /**
     * 备注
     */
    private String remark;

    /**
     * 业务扩展字段，保存json字串
     */
    @Column(name = "ext_field")
    private String extField;

    /**
     * 处理时间
     */
    @Column(name = "trans_time")
    private Date transTime;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 操作人号
     */
    @Column(name = "operator_no")
    private String operatorNo;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取会员号
     *
     * @return member_no - 会员号
     */
    public String getMemberNo() {
        return memberNo;
    }

    /**
     * 设置会员号
     *
     * @param memberNo 会员号
     */
    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    /**
     * 获取用户产品ID
     *
     * @return product_id - 用户产品ID
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * 设置用户产品ID
     *
     * @param productId 用户产品ID
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * 获取资产账户号
     *
     * @return asset_account_no - 资产账户号
     */
    public String getAssetAccountNo() {
        return assetAccountNo;
    }

    /**
     * 设置资产账户号
     *
     * @param assetAccountNo 资产账户号
     */
    public void setAssetAccountNo(String assetAccountNo) {
        this.assetAccountNo = assetAccountNo;
    }

    /**
     * 获取基金账户业务单号
     *
     * @return fund_acct_order_no - 基金账户业务单号
     */
    public String getFundAcctOrderNo() {
        return fundAcctOrderNo;
    }

    /**
     * 设置基金账户业务单号
     *
     * @param fundAcctOrderNo 基金账户业务单号
     */
    public void setFundAcctOrderNo(String fundAcctOrderNo) {
        this.fundAcctOrderNo = fundAcctOrderNo;
    }

    /**
     * 获取基金交易账户号
     *
     * @return fund_trans_acct_no - 基金交易账户号
     */
    public String getFundTransAcctNo() {
        return fundTransAcctNo;
    }

    /**
     * 设置基金交易账户号
     *
     * @param fundTransAcctNo 基金交易账户号
     */
    public void setFundTransAcctNo(String fundTransAcctNo) {
        this.fundTransAcctNo = fundTransAcctNo;
    }

    /**
     * 获取关联基金交易业务单号
     *
     * @return ref_fund_trans_order_no - 关联基金交易业务单号
     */
    public String getRefFundTransOrderNo() {
        return refFundTransOrderNo;
    }

    /**
     * 设置关联基金交易业务单号
     *
     * @param refFundTransOrderNo 关联基金交易业务单号
     */
    public void setRefFundTransOrderNo(String refFundTransOrderNo) {
        this.refFundTransOrderNo = refFundTransOrderNo;
    }

    /**
     * 获取基金账户操作类型: 0 - 开户
     *
     * @return fund_acct_oper_type - 基金账户操作类型: 0 - 开户
     */
    public Integer getFundAcctOperType() {
        return fundAcctOperType;
    }

    /**
     * 设置基金账户操作类型: 0 - 开户
     *
     * @param fundAcctOperType 基金账户操作类型: 0 - 开户
     */
    public void setFundAcctOperType(Integer fundAcctOperType) {
        this.fundAcctOperType = fundAcctOperType;
    }

    /**
     * 获取防止幂等,追踪
     *
     * @return tracking_no - 防止幂等,追踪
     */
    public String getTrackingNo() {
        return trackingNo;
    }

    /**
     * 设置防止幂等,追踪
     *
     * @param trackingNo 防止幂等,追踪
     */
    public void setTrackingNo(String trackingNo) {
        this.trackingNo = trackingNo;
    }

    /**
     * 获取ta业务代码
     *
     * @return ref_fund_business_code - ta业务代码
     */
    public String getRefFundBusinessCode() {
        return refFundBusinessCode;
    }

    /**
     * 设置ta业务代码
     *
     * @param refFundBusinessCode ta业务代码
     */
    public void setRefFundBusinessCode(String refFundBusinessCode) {
        this.refFundBusinessCode = refFundBusinessCode;
    }

    /**
     * 获取渠道类型：IOS = 11; ANDROID = 12; H5 = 13; WECHAT = 14; WEB = 20;
     *
     * @return biz_channel - 渠道类型：IOS = 11; ANDROID = 12; H5 = 13; WECHAT = 14; WEB = 20;
     */
    public Integer getBizChannel() {
        return bizChannel;
    }

    /**
     * 设置渠道类型：IOS = 11; ANDROID = 12; H5 = 13; WECHAT = 14; WEB = 20;
     *
     * @param bizChannel 渠道类型：IOS = 11; ANDROID = 12; H5 = 13; WECHAT = 14; WEB = 20;
     */
    public void setBizChannel(Integer bizChannel) {
        this.bizChannel = bizChannel;
    }

    /**
     * 获取基金提交方式：1 - 实时; 2 - 文件
     *
     * @return fund_interact_type - 基金提交方式：1 - 实时; 2 - 文件
     */
    public Integer getFundInteractType() {
        return fundInteractType;
    }

    /**
     * 设置基金提交方式：1 - 实时; 2 - 文件
     *
     * @param fundInteractType 基金提交方式：1 - 实时; 2 - 文件
     */
    public void setFundInteractType(Integer fundInteractType) {
        this.fundInteractType = fundInteractType;
    }

    /**
     * 获取状态: 1 - 资产账户开户中; 2 - 资产账户开户成功; 3 - 资产账户开户失败; 4 - 基金公司开户中; 5 - 基金公司开户成功;  6 - 基金公司开户失败
     *
     * @return status - 状态: 1 - 资产账户开户中; 2 - 资产账户开户成功; 3 - 资产账户开户失败; 4 - 基金公司开户中; 5 - 基金公司开户成功;  6 - 基金公司开户失败
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态: 1 - 资产账户开户中; 2 - 资产账户开户成功; 3 - 资产账户开户失败; 4 - 基金公司开户中; 5 - 基金公司开户成功;  6 - 基金公司开户失败
     *
     * @param status 状态: 1 - 资产账户开户中; 2 - 资产账户开户成功; 3 - 资产账户开户失败; 4 - 基金公司开户中; 5 - 基金公司开户成功;  6 - 基金公司开户失败
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取总有效期
     *
     * @return total_expiry_time - 总有效期
     */
    public Date getTotalExpiryTime() {
        return totalExpiryTime;
    }

    /**
     * 设置总有效期
     *
     * @param totalExpiryTime 总有效期
     */
    public void setTotalExpiryTime(Date totalExpiryTime) {
        this.totalExpiryTime = totalExpiryTime;
    }

    /**
     * 获取当前状态过期时间
     *
     * @return cur_status_expiry_time - 当前状态过期时间
     */
    public Date getCurStatusExpiryTime() {
        return curStatusExpiryTime;
    }

    /**
     * 设置当前状态过期时间
     *
     * @param curStatusExpiryTime 当前状态过期时间
     */
    public void setCurStatusExpiryTime(Date curStatusExpiryTime) {
        this.curStatusExpiryTime = curStatusExpiryTime;
    }

    /**
     * 获取ta申请单号, 可关联请求，确认，结果
     *
     * @return ref_app_sheet_serial_no - ta申请单号, 可关联请求，确认，结果
     */
    public String getRefAppSheetSerialNo() {
        return refAppSheetSerialNo;
    }

    /**
     * 设置ta申请单号, 可关联请求，确认，结果
     *
     * @param refAppSheetSerialNo ta申请单号, 可关联请求，确认，结果
     */
    public void setRefAppSheetSerialNo(String refAppSheetSerialNo) {
        this.refAppSheetSerialNo = refAppSheetSerialNo;
    }

    /**
     * 获取reserved
     *
     * @return ext_info_order_no - reserved
     */
    public String getExtInfoOrderNo() {
        return extInfoOrderNo;
    }

    /**
     * 设置reserved
     *
     * @param extInfoOrderNo reserved
     */
    public void setExtInfoOrderNo(String extInfoOrderNo) {
        this.extInfoOrderNo = extInfoOrderNo;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取业务扩展字段，保存json字串
     *
     * @return ext_field - 业务扩展字段，保存json字串
     */
    public String getExtField() {
        return extField;
    }

    /**
     * 设置业务扩展字段，保存json字串
     *
     * @param extField 业务扩展字段，保存json字串
     */
    public void setExtField(String extField) {
        this.extField = extField;
    }

    /**
     * 获取处理时间
     *
     * @return trans_time - 处理时间
     */
    public Date getTransTime() {
        return transTime;
    }

    /**
     * 设置处理时间
     *
     * @param transTime 处理时间
     */
    public void setTransTime(Date transTime) {
        this.transTime = transTime;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取操作人号
     *
     * @return operator_no - 操作人号
     */
    public String getOperatorNo() {
        return operatorNo;
    }

    /**
     * 设置操作人号
     *
     * @param operatorNo 操作人号
     */
    public void setOperatorNo(String operatorNo) {
        this.operatorNo = operatorNo;
    }
}