package com.takeoff.iot.modbus.test.config;

import com.takeoff.iot.modbus.netty.MiiServer;
import com.takeoff.iot.modbus.netty.message.MiiMessage;
import com.takeoff.iot.modbus.test.listener.*;
import com.takeoff.iot.modbus.test.properties.IotModbusNettyProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

/**
 * 类功能说明：通讯协议配置注册<br/>
 * 公司名称：takeoff开源 <br/>
 * 作者：luorongxi <br/>
 */
@Slf4j
@Configuration
public class IotModbusNettyConfig implements ApplicationRunner {

	@Resource
	private IotModbusNettyProperties iotModbusNettyProperties;

	@Resource
	private CardListener cardListener;

	@Resource
	private BarCodeListener barCodeListener;

	@Resource
	private BackLightListener backLightListener;

	@Resource
	private LockListener lockListener;

	@Resource
	private FingerListener fingerListener;

	@Resource
	private HumitureListener humitureListener;
	
	@Getter
	private MiiServer miiServer;

	@Override
    public void run(ApplicationArguments args) throws Exception {
		if(iotModbusNettyProperties.getOpen()){
			miiServer = new MiiServer(iotModbusNettyProperties.getPort(), iotModbusNettyProperties.getThread());
			miiServer.addListener(MiiMessage.BACKLIGHT, backLightListener);
			miiServer.addListener(MiiMessage.LOCK, lockListener);
			miiServer.addListener(MiiMessage.CARD, cardListener);
			miiServer.addListener(MiiMessage.BARCODE, barCodeListener);
			miiServer.addListener(MiiMessage.FINGER, fingerListener);
			miiServer.addListener(MiiMessage.HM, humitureListener);
			log.info("IOT通讯协议已开启Socket服务，占用端口： " + iotModbusNettyProperties.getPort() + ",执行线程池线程数:" + iotModbusNettyProperties.getThread());
			miiServer.start();
		}else{
			log.info("IOT通讯协议未开启Socket服务");
		}
	}
}
