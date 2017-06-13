package com.dasanzhone.seawind.endpoint;

import java.io.IOException;

import javax.jws.WebParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dasanzhone.namespace.deviceservice.DeviceException;
import com.dasanzhone.namespace.deviceservice.DeviceService;
import com.dasanzhone.namespace.deviceservice.datatypes.NetworkDevice;
import com.dasanzhone.namespace.deviceservice.general.DeviceInformationReturn;
import com.dasanzhone.namespace.deviceservice.general.DeviceReturn;
import com.dasanzhone.namespace.deviceservice.general.ForecastRequest;
import com.dasanzhone.namespace.deviceservice.general.ForecastReturn;
import com.dasanzhone.namespace.deviceservice.general.NetworkDeviceReturn;
import com.dasanzhone.seawind.controller.DeviceServiceController;

public class DeviceServiceEndpoint implements DeviceService {

	private static final Logger LOG = LoggerFactory.getLogger(DeviceServiceEndpoint.class);

	@Autowired
	private DeviceServiceController deviceServiceController;

	@Override
	public ForecastReturn getCityForecastByZIP(ForecastRequest forecastRequest) throws DeviceException {
		LOG.debug("Method getCityForecastByZIP() was called. Processing the Request in the backend");

		return deviceServiceController.getCityForecastByZIP(forecastRequest);
	}

	@Override
	public DeviceInformationReturn getDeviceInformation(String zip) throws DeviceException {
		LOG.debug("Method getDeviceInformation() was called. Processing the Request in the backend");

		try {
			return deviceServiceController.getDeviceInformation(zip);
		} catch (IOException e) {
			throw new DeviceException(e.toString(), new com.dasanzhone.namespace.deviceservice.exception.DeviceException());
		}
	}

	@Override
	public DeviceReturn getCityDeviceByZIP(ForecastRequest forecastRequest) throws DeviceException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.dasanzhone.namespace.deviceservice.DeviceService#getNetworkDeviceById(java.lang.String)
	 */
	@Override
	public NetworkDeviceReturn getNetworkDeviceById(
			@WebParam(name = "DeviceId", targetNamespace = "http://www.dasanzhone.com/namespace/deviceservice/general") String deviceId)
			throws DeviceException {

		NetworkDeviceReturn result = new NetworkDeviceReturn();
		
		result.setSuccess(true);
		result.setResponseText("abc");
		result.setNetworkDevice(new NetworkDevice());
		
		return result;
	}

}
