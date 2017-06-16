package com.dasanzhone.seawind.swservice.controller;

import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.Variable;
import org.springframework.stereotype.Component;

import com.adventnet.snmp.snmp2.SnmpPDU;
import com.dasanzhone.namespace.deviceservice.general.CommonOperationReturn;
import com.dasanzhone.namespace.deviceservice.general.DeviceInformationReturn;
import com.dasanzhone.namespace.deviceservice.general.ForecastRequest;
import com.dasanzhone.namespace.deviceservice.general.ForecastReturn;
import com.dasanzhone.namespace.deviceservice.general.OntInput;
import com.dasanzhone.seawind.swservice.entity.OntInputSwapper;
import com.dasanzhone.seawind.swservice.service.SnmpManager;
import com.dasanzhone.seawind.swservice.snmp.SnmpOid;
import com.dasanzhone.seawind.swservice.snmp.SnmpOperation;
import com.dasanzhone.seawind.swservice.snmp.SnmpOperationInput;
import com.dasanzhone.seawind.swservice.snmp.SnmpUtil;
import com.dasanzhone.seawind.swservice.util.BusinessUtil;

import net.logstash.logback.encoder.org.apache.commons.lang.exception.ExceptionUtils;

/*
 *  Example-Controller:
 *  This Class would be responsible for Mapping from Request to internal Datamodel (and backwards),
 *  for calling Backend-Services and handling Backend-Exceptions
 *  So it decouples the WSDL-generated Classes from the internal Classes - for when the former changes,
 *  nothing or only the mapping has to be changed
 */
@Component
public class DeviceServiceController {

	private static final String STRING_ENABLE = "ENABLE";
	private static final Logger LOG = LoggerFactory.getLogger(DeviceServiceController.class);

	public ForecastReturn getCityForecastByZIP(ForecastRequest forecastRequest) {
		/*
		 * We leave out inbound transformation, plausibility-checking, logging,
		 * backend-calls e.g. for the moment
		 *
		 * Just some Log-Statements here :)
		 */
		LOG.info("Starting inbound transformation into internal datamodel");

		LOG.info("Checking plausibility of data");

		LOG.info("Calling Backend No. 1");

		LOG.info("Calling Backend No. 2");

		LOG.info("Starting outbound transformation into external datamodel");

		return null; // GetCityForecastByZIPOutMapper.mapGeneralOutlook2Forecast();
	}

	/*
	 * Other Methods would follow here...
	 */
	// public DeviceReturn getCityDeviceByZIP(ForecastRequest forecastRequest)
	// throws BusinessException {}

	public DeviceInformationReturn getDeviceInformation(String zip) throws IOException {
		/**
		 * Port 161 is used for Read and Other operations Port 162 is used for
		 * the trap generation
		 */
		SnmpManager client = new SnmpManager("udp:10.72.200.125/161");
		client.start();

		/**
		 * OID - .1.3.6.1.2.1.1.1.0 => SysDec OID - .1.3.6.1.2.1.1.5.0 =>
		 * SysName => MIB explorer will be usefull here, as discussed in
		 * previous article
		 */
		// final String oid = ".1.3.6.1.4.1.637.61.1.35.10.1.1.5.331481088";
		final String oid = ".1.3.6.1.4.1.637.61.1.35.10.1.1.82.331481088";

		Variable var = client.getAsVariable(new OID(oid));

		OctetString sysDescr = (OctetString) var;

		LOG.info(String.format("sysDescr [%s] = %s", oid, sysDescr.toString()));

		DeviceInformationReturn deviceInformationReturn = new DeviceInformationReturn();

		deviceInformationReturn.setResponseText(sysDescr.toString());
		deviceInformationReturn.setSuccess(true);

		return deviceInformationReturn;
	}

	public static String ROOT_SW_VER_PLAND = ".1.3.6.1.4.1.637.61.1.35.10.1.1.11.";
	public static String ROOT_SW_DNLOAD_VERSION = ".1.3.6.1.4.1.637.61.1.35.10.1.1.60.";
	public static String ROOT_SERNUM = ".1.3.6.1.4.1.637.61.1.35.10.1.1.5.";
	public static String ROOT_FEC_UP = ".1.3.6.1.4.1.637.61.1.35.10.1.1.39.";
	public static String ROOT_ENABLE_AES = ".1.3.6.1.4.1.637.61.1.35.10.1.1.75.";
	public static String ROOT_PLND_VAR = ".1.3.6.1.4.1.637.61.1.35.10.1.1.65.";

	public CommonOperationReturn declareOntId(OntInput ontInput) {
		CommonOperationReturn result = new CommonOperationReturn();

		OntInputSwapper ontInputSwapper = new OntInputSwapper(ontInput);

		int leafNode;
		try {
			leafNode = BusinessUtil.tranformInterfaceToOid(ontInputSwapper.getOntInterface());
		} catch (Exception e) {
			result.setSuccess(false);
			result.setResponseText(e.toString());
			result.setDescription(ExceptionUtils.getFullStackTrace(e));
			return result;
		}

		SnmpOperationInput input = new SnmpOperationInput();

		input.setHost("10.72.200.125");
		input.setVersion("v2");
		input.setOids(new ArrayList<SnmpOid>());

		SnmpOid snmpOid = new SnmpOid(".1.3.6.1.4.1.637.61.1.35.10.1.1.2." + leafNode, SnmpUtil.getSnmpVar("INTEGER", "4"));
		input.getOids().add(snmpOid);

		snmpOid = new SnmpOid(".1.3.6.1.4.1.637.61.1.35.10.1.1.5." + leafNode, SnmpUtil.getSnmpVar("STRING", ontInputSwapper.getSernum()));
		input.getOids().add(snmpOid);

		snmpOid = new SnmpOid(".1.3.6.1.4.1.637.61.1.35.10.1.1.8." + leafNode, SnmpUtil.getSnmpVar("INTEGER", "0"));
		input.getOids().add(snmpOid);

		snmpOid = new SnmpOid(".1.3.6.1.4.1.637.61.1.35.10.1.1.11." + leafNode, SnmpUtil.getSnmpVar("STRING", ontInputSwapper.getSwVerPland()));
		input.getOids().add(snmpOid);

		snmpOid = new SnmpOid(".1.3.6.1.4.1.637.61.1.35.10.1.1.18." + leafNode, SnmpUtil.getSnmpVar("INTEGER", ontInputSwapper.getBerint()));
		input.getOids().add(snmpOid);

		snmpOid = new SnmpOid(".1.3.6.1.4.1.637.61.1.35.10.1.1.21." + leafNode, SnmpUtil.getSnmpVar("STRING", ontInputSwapper.getProvversion()));
		input.getOids().add(snmpOid);

		snmpOid = new SnmpOid(".1.3.6.1.4.1.637.61.1.35.10.1.1.39." + leafNode, SnmpUtil.getSnmpVar("INTEGER", ontInputSwapper.getFecUp()));
		input.getOids().add(snmpOid);

		snmpOid = new SnmpOid(".1.3.6.1.4.1.637.61.1.35.10.1.1.60." + leafNode, SnmpUtil.getSnmpVar("STRING", ontInputSwapper.getSwDnloadVersion()));
		input.getOids().add(snmpOid);

		snmpOid = new SnmpOid(".1.3.6.1.4.1.637.61.1.35.10.1.1.63." + leafNode, SnmpUtil.getSnmpVar("INTEGER", ontInputSwapper.getOpticsHist()));
		input.getOids().add(snmpOid);

		snmpOid = new SnmpOid(".1.3.6.1.4.1.637.61.1.35.10.1.1.65." + leafNode, SnmpUtil.getSnmpVar("STRING", ontInputSwapper.getPlndVar()));
		input.getOids().add(snmpOid);

		snmpOid = new SnmpOid(".1.3.6.1.4.1.637.61.1.47.6.1.1.18." + leafNode, SnmpUtil.getSnmpVar("INTEGER", "8"));
		input.getOids().add(snmpOid);

		snmpOid = new SnmpOid(".1.3.6.1.4.1.637.61.1.47.6.1.1.19." + leafNode, SnmpUtil.getSnmpVar("INTEGER", "2"));
		input.getOids().add(snmpOid);

		snmpOid = new SnmpOid(".1.3.6.1.4.1.637.61.1.35.10.1.1.75." + leafNode, SnmpUtil.getSnmpVar("INTEGER", ontInputSwapper.getEnableAes()));
		input.getOids().add(snmpOid);

		try {
			SnmpPDU snmpPDU = SnmpOperation.setWithoutMib_Low(input);
			if (snmpPDU.getErrstat() == 0) {
				result.setSuccess(true);
				result.setResponseText("SUCCESSFUL");
				result.setDescription(snmpPDU.printVarBinds());
			} else {
				result.setSuccess(false);
				result.setResponseText("UNSUCCESSFUL");
				result.setDescription(snmpPDU.getError());
			}

			return result;

		} catch (Exception e) {

			result.setSuccess(false);
			result.setResponseText(e.toString());
			result.setDescription(ExceptionUtils.getFullStackTrace(e));
			return result;
		}
	}

	public CommonOperationReturn activateDeactivateOntId(OntInput ontInput) {
		CommonOperationReturn result = new CommonOperationReturn();

		OntInputSwapper ontInputSwapper = new OntInputSwapper(ontInput);

		int leafNode;
		try {
			leafNode = BusinessUtil.tranformInterfaceToOid(ontInputSwapper.getOntInterface());
		} catch (Exception e) {
			result.setSuccess(false);
			result.setResponseText(e.toString());
			result.setDescription(ExceptionUtils.getFullStackTrace(e));
			return result;
		}

		SnmpOperationInput input = new SnmpOperationInput();

		input.setHost("10.72.200.125");
		input.setVersion("v2");
		input.setOids(new ArrayList<SnmpOid>());

		SnmpOid snmpOid = new SnmpOid(".1.3.6.1.2.1.2.2.1.7." + leafNode, SnmpUtil.getSnmpVar("INTEGER", ontInputSwapper.getAdminState()));
		input.getOids().add(snmpOid);

		try {
			SnmpPDU snmpPDU = SnmpOperation.setWithoutMib_Low(input);
			if (snmpPDU.getErrstat() == 0) {
				result.setSuccess(true);
				result.setResponseText("SUCCESSFUL");
				result.setDescription(snmpPDU.printVarBinds());
			} else {
				result.setSuccess(false);
				result.setResponseText("UNSUCCESSFUL");
				result.setDescription(snmpPDU.getError());
			}

			return result;

		} catch (Exception e) {

			result.setSuccess(false);
			result.setResponseText(e.toString());
			result.setDescription(ExceptionUtils.getFullStackTrace(e));
			return result;
		}
	}

}
