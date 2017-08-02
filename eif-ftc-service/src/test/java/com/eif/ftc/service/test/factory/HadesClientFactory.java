package com.eif.ftc.service.test.factory;

import com.eif.framework.cache.HadesClient;
import com.eif.framework.test.factory.TestProxyFactory;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class HadesClientFactory extends TestProxyFactory{
	
	@Override
	protected void mockDetailsWith(Object instance) {
		HadesClient mockClient = (HadesClient)instance;
		try {
			Mockito.when(mockClient.getset(Mockito.anyString(), Mockito.anyString())).thenAnswer(new Answer<String>() {
				@Override
				public String answer(InvocationOnMock invocation) throws Throwable {
					 Object[] args = invocation.getArguments();  
					return "" + args[1];
				}
			});
		} catch (Exception e) {
			//TODO:
		}
	}


}
