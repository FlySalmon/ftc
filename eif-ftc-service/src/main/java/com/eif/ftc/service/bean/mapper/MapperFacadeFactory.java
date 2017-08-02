package com.eif.ftc.service.bean.mapper;

import com.eif.ftc.dal.model.FundBonusDetailAlteration;
import com.eif.ftc.dal.model.FundDetail;
import com.eif.ftc.facade.fund.amc.dto.MemberAssetBean;
import com.eif.ftc.facade.fund.amc.dto.response.BonusBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import com.eif.ftc.dal.model.FundOfflineDetail;
import com.eif.ftc.facade.fund.amc.dto.request.FundOfflineAssetBean;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Component
public class MapperFacadeFactory implements FactoryBean<MapperFacade>{
    public MapperFacade getObject() throws Exception {
//        return new DefaultMapperFactory.Builder().build().getMapperFacade();

        return init().getMapperFacade();
    }
 
    public Class<?> getObjectType() {
        return MapperFacade.class;
    }
 
    public boolean isSingleton() {
        return true;
    }

    private MapperFactory init()
    {
        MapperFactory factory = new DefaultMapperFactory.Builder().build();

        factory.classMap(FundOfflineAssetBean.class,FundOfflineDetail.class)
                .field("bonusAmount","bonusTotalAmount")
                .field("profitAmount","profitTotalAmount")
                .field("customerCardNo","customerCardno")
                .byDefault().register();


        factory.classMap(FundBonusDetailAlteration.class,BonusBean.class)
                .field("fundBonusAmount","bonusAmount")
                .field("bonusTime","bonusDate")
                .byDefault().register();

        factory.classMap(FundDetail.class,MemberAssetBean.class)
                .field("fundTotalAmount","totalAmount")
                .field("bonusTotalAmount","totalBonusAmount")
                .field("profitTotalAmount","totalProfitAmount")
                .field("grouponBonus","grouponAmount")
                .field("productCloseType","closeType")
                .field("settlementTime","settlementDate")
                .field("unconfirmedAddAmount","unConfirmedAddAmount")
                .field("unconfirmedSubAmount","unConfirmedSubAmount")
                .byDefault().register();

        return factory;
    }
}


