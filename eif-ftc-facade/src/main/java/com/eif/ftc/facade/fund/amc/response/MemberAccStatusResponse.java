package com.eif.ftc.facade.fund.amc.response;

import com.eif.framework.common.utils.pkg.BaseResponse;

/**
 * Created by Matt on 16/2/23.
 */
public class MemberAccStatusResponse extends BaseResponse {


    private static final long serialVersionUID = -4142335529969403511L;

    private Integer status;


    /**
     * @return 用户资产账户交易状态
     * @occurs required
     */
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
