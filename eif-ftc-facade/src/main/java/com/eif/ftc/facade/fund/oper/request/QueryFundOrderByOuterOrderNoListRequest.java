package com.eif.ftc.facade.fund.oper.request;

import com.eif.framework.common.utils.pkg.BaseRequest;

import java.util.List;

/**
 * Created by Matt on 2017/4/26.
 */
public class QueryFundOrderByOuterOrderNoListRequest extends BaseRequest{

    private static final long serialVersionUID = 2809081492124876358L;

    private List<String> outerOrderNoList;

    private String outerSysNo;


    public List<String> getOuterOrderNoList() {
        return outerOrderNoList;
    }

    /**
     * @param outerOrderNoList 外部关联单号列表
     * @occurs required
     */
    public void setOuterOrderNoList(List<String> outerOrderNoList) {
        this.outerOrderNoList = outerOrderNoList;
    }

    public String getOuterSysNo() {
        return outerSysNo;
    }

    /**
     * @param outerSysNo 外部关联系统号
     * @occurs required
     */
    public void setOuterSysNo(String outerSysNo) {
        this.outerSysNo = outerSysNo;
    }
}
