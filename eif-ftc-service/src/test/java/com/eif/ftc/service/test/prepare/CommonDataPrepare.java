package com.eif.ftc.service.test.prepare;

import com.eif.fis.facade.response.ftc.QueryProdTransInfoResp;
import com.eif.framework.concurrent.lock.RedisConcurrentLock;
import org.mockito.Mockito;

/**
 * Created by bohan on 10/17/16.
 */
public class CommonDataPrepare {

    public static void buildConcurrentSuccess(RedisConcurrentLock redisConcurrentLock) {
        Mockito.doReturn(true).when(redisConcurrentLock).tryAcquire(Mockito.anyString(), Mockito.anyInt());
    }
}
