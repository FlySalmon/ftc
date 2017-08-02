package com.eif.ftc.util.uuid;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.UUID;

@Component
public class UUIDGenerator {
	public static String gen() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
