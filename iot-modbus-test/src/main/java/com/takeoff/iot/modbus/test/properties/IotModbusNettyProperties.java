package com.takeoff.iot.modbus.test.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * 类功能说明：netty通讯参数配置<br/>
 * 公司名称：takeoff开源 <br/>
 * 作者：luorongxi <br/>
 */
@Data
@Component
@ConfigurationProperties(prefix = "iot.netty")
public class IotModbusNettyProperties {
	
	/**
	 * 是否开启Socket服务
	 */
	private Boolean open;

	/**
	 * Socket服务端口
	 */
	private Integer port;
	
	/**
	 * Socket服务执行线程数
	 */
    private Integer thread;
}
