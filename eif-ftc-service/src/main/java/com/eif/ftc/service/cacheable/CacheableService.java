package com.eif.ftc.service.cacheable;

import com.eif.fis.facade.response.ftc.QueryProdTransInfoResp;

public interface CacheableService {
	public QueryProdTransInfoResp queryProdTransInfo(Long productID);
}
