package com.eif.ftc.service.transfer.exchange.bean.req;

import java.io.Serializable;

public class TransfereeBean implements Serializable {

    private static final long serialVersionUID = -4002338377251644780L;

    /**
     * 受让人姓名、企业全称
     */
    private String name;
    
    /**
     * 受让人身份证、组织机构代码
     */
    private String identity_id;

    /**
     * 受让人用户Id（选填）
     */
    private String user_id;
    
    /**
     * 受让人手机号码（选填）
     */
    private String telephone;

    /**
     * 受让人风险级别（选填）
     */
    private String risk_level;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the identity_id
	 */
	public String getIdentity_id() {
		return identity_id;
	}

	/**
	 * @param identity_id the identity_id to set
	 */
	public void setIdentity_id(String identity_id) {
		this.identity_id = identity_id;
	}

	/**
	 * @return the user_id
	 */
	public String getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * @return the risk_level
	 */
	public String getRisk_level() {
		return risk_level;
	}

	/**
	 * @param risk_level the risk_level to set
	 */
	public void setRisk_level(String risk_level) {
		this.risk_level = risk_level;
	}

}
