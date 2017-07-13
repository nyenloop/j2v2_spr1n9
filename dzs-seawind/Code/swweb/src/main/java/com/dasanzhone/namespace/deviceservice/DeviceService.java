
package com.dasanzhone.namespace.deviceservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import com.dasanzhone.namespace.deviceservice.general.CommonOperationReturn;
import com.dasanzhone.namespace.deviceservice.general.DeviceInformationReturn;
import com.dasanzhone.namespace.deviceservice.general.DeviceReturn;
import com.dasanzhone.namespace.deviceservice.general.ForecastRequest;
import com.dasanzhone.namespace.deviceservice.general.ForecastReturn;
import com.dasanzhone.namespace.deviceservice.general.NetworkDeviceReturn;
import com.dasanzhone.namespace.deviceservice.general.OntInput;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b14002
 * Generated source version: 2.1
 * 
 */
@WebService(name = "DeviceService", targetNamespace = "http://www.dasanzhone.com/namespace/deviceservice/")
@XmlSeeAlso({
    com.dasanzhone.namespace.deviceservice.datatypes.ObjectFactory.class,
    com.dasanzhone.namespace.deviceservice.exception.ObjectFactory.class,
    com.dasanzhone.namespace.deviceservice.general.ObjectFactory.class,
    org.w3._2005._05.xmlmime.ObjectFactory.class
})
public interface DeviceService {


    /**
     * Gets Information
     * 				for each DeviceID
     * 			
     * 
     * @param zip
     * @return
     *     returns com.dasanzhone.namespace.deviceservice.general.DeviceInformationReturn
     * @throws DeviceException
     */
    @WebMethod(operationName = "GetDeviceInformation", action = "http://www.dasanzhone.com/namespace/deviceservice/GetDeviceInformation")
    @WebResult(name = "GetDeviceInformationResult", targetNamespace = "http://www.dasanzhone.com/namespace/deviceservice/general")
    @RequestWrapper(localName = "GetDeviceInformation", targetNamespace = "http://www.dasanzhone.com/namespace/deviceservice/general", className = "com.dasanzhone.namespace.deviceservice.general.GetDeviceInformation")
    @ResponseWrapper(localName = "GetDeviceInformationResponse", targetNamespace = "http://www.dasanzhone.com/namespace/deviceservice/general", className = "com.dasanzhone.namespace.deviceservice.general.GetDeviceInformationResponse")
    public DeviceInformationReturn getDeviceInformation(
        @WebParam(name = "ZIP", targetNamespace = "http://www.dasanzhone.com/namespace/deviceservice/general")
        String zip)
        throws DeviceException
    ;

    /**
     * Allows you to get
     * 				your City Forecast Over the Next 7 Days, which is updated hourly.
     * 			
     * 
     * @param forecastRequest
     * @return
     *     returns com.dasanzhone.namespace.deviceservice.general.ForecastReturn
     * @throws DeviceException
     */
    @WebMethod(operationName = "GetCityForecastByZIP", action = "http://www.dasanzhone.com/namespace/deviceservice/GetCityForecastByZIP")
    @WebResult(name = "GetCityForecastByZIPResult", targetNamespace = "http://www.dasanzhone.com/namespace/deviceservice/general")
    @RequestWrapper(localName = "GetCityForecastByZIP", targetNamespace = "http://www.dasanzhone.com/namespace/deviceservice/general", className = "com.dasanzhone.namespace.deviceservice.general.GetCityForecastByZIP")
    @ResponseWrapper(localName = "GetCityForecastByZIPResponse", targetNamespace = "http://www.dasanzhone.com/namespace/deviceservice/general", className = "com.dasanzhone.namespace.deviceservice.general.GetCityForecastByZIPResponse")
    public ForecastReturn getCityForecastByZIP(
        @WebParam(name = "ForecastRequest", targetNamespace = "http://www.dasanzhone.com/namespace/deviceservice/general")
        ForecastRequest forecastRequest)
        throws DeviceException
    ;

    /**
     * Allows you to get
     * 				your City's Device, which is updated hourly.
     * 			
     * 
     * @param forecastRequest
     * @return
     *     returns com.dasanzhone.namespace.deviceservice.general.DeviceReturn
     * @throws DeviceException
     */
    @WebMethod(operationName = "GetCityDeviceByZIP", action = "http://www.dasanzhone.com/namespace/deviceservice/GetCityDeviceByZIP")
    @WebResult(name = "GetCityDeviceByZIPResult", targetNamespace = "http://www.dasanzhone.com/namespace/deviceservice/general")
    @RequestWrapper(localName = "GetCityDeviceByZIP", targetNamespace = "http://www.dasanzhone.com/namespace/deviceservice/general", className = "com.dasanzhone.namespace.deviceservice.general.GetCityDeviceByZIP")
    @ResponseWrapper(localName = "GetCityDeviceByZIPResponse", targetNamespace = "http://www.dasanzhone.com/namespace/deviceservice/general", className = "com.dasanzhone.namespace.deviceservice.general.GetCityDeviceByZIPResponse")
    public DeviceReturn getCityDeviceByZIP(
        @WebParam(name = "ForecastRequest", targetNamespace = "http://www.dasanzhone.com/namespace/deviceservice/general")
        ForecastRequest forecastRequest)
        throws DeviceException
    ;

    /**
     * 
     * @param deviceId
     * @return
     *     returns com.dasanzhone.namespace.deviceservice.general.NetworkDeviceReturn
     * @throws DeviceException
     */
    @WebMethod(operationName = "GetNetworkDeviceById", action = "http://www.dasanzhone.com/namespace/deviceservice/GetNetworkDeviceById")
    @WebResult(name = "GetNetworkDeviceByIdResult", targetNamespace = "http://www.dasanzhone.com/namespace/deviceservice/general")
    @RequestWrapper(localName = "GetNetworkDeviceById", targetNamespace = "http://www.dasanzhone.com/namespace/deviceservice/general", className = "com.dasanzhone.namespace.deviceservice.general.GetNetworkDeviceById")
    @ResponseWrapper(localName = "GetNetworkDeviceByIdResponse", targetNamespace = "http://www.dasanzhone.com/namespace/deviceservice/general", className = "com.dasanzhone.namespace.deviceservice.general.GetNetworkDeviceByIdResponse")
    public NetworkDeviceReturn getNetworkDeviceById(
        @WebParam(name = "DeviceId", targetNamespace = "http://www.dasanzhone.com/namespace/deviceservice/general")
        String deviceId)
        throws DeviceException
    ;

    /**
     * 
     * @param ontInput
     * @return
     *     returns com.dasanzhone.namespace.deviceservice.general.CommonOperationReturn
     * @throws DeviceException
     */
    @WebMethod(operationName = "DeclareOntId", action = "http://www.dasanzhone.com/namespace/deviceservice/DeclareOntId")
    @WebResult(name = "DeclareOntIdResult", targetNamespace = "http://www.dasanzhone.com/namespace/deviceservice/general")
    @RequestWrapper(localName = "DeclareOntId", targetNamespace = "http://www.dasanzhone.com/namespace/deviceservice/general", className = "com.dasanzhone.namespace.deviceservice.general.DeclareOntId")
    @ResponseWrapper(localName = "DeclareOntIdResponse", targetNamespace = "http://www.dasanzhone.com/namespace/deviceservice/general", className = "com.dasanzhone.namespace.deviceservice.general.DeclareOntIdResponse")
    public CommonOperationReturn declareOntId(
        @WebParam(name = "OntInput", targetNamespace = "http://www.dasanzhone.com/namespace/deviceservice/general")
        OntInput ontInput)
        throws DeviceException
    ;

    /**
     * 
     * @param ontInput
     * @return
     *     returns com.dasanzhone.namespace.deviceservice.general.CommonOperationReturn
     * @throws DeviceException
     */
    @WebMethod(operationName = "ActivateDeactivateOntId", action = "http://www.dasanzhone.com/namespace/deviceservice/ActivateDeactivateOntId")
    @WebResult(name = "ActivateDeactivateOntIdResult", targetNamespace = "http://www.dasanzhone.com/namespace/deviceservice/general")
    @RequestWrapper(localName = "ActivateDeactivateOntId", targetNamespace = "http://www.dasanzhone.com/namespace/deviceservice/general", className = "com.dasanzhone.namespace.deviceservice.general.ActivateDeactivateOntId")
    @ResponseWrapper(localName = "ActivateDeactivateOntIdResponse", targetNamespace = "http://www.dasanzhone.com/namespace/deviceservice/general", className = "com.dasanzhone.namespace.deviceservice.general.ActivateDeactivateOntIdResponse")
    public CommonOperationReturn activateDeactivateOntId(
        @WebParam(name = "OntInput", targetNamespace = "http://www.dasanzhone.com/namespace/deviceservice/general")
        OntInput ontInput)
        throws DeviceException
    ;

    /**
     * 
     * @param ontInput
     * @return
     *     returns com.dasanzhone.namespace.deviceservice.general.CommonOperationReturn
     * @throws DeviceException
     */
    @WebMethod(operationName = "DeclarePpptpCard", action = "http://www.dasanzhone.com/namespace/deviceservice/DeclarePpptpCard")
    @WebResult(name = "DeclarePpptpCardResult", targetNamespace = "http://www.dasanzhone.com/namespace/deviceservice/general")
    @RequestWrapper(localName = "DeclarePpptpCard", targetNamespace = "http://www.dasanzhone.com/namespace/deviceservice/general", className = "com.dasanzhone.namespace.deviceservice.general.DeclarePpptpCard")
    @ResponseWrapper(localName = "DeclarePpptpCardResponse", targetNamespace = "http://www.dasanzhone.com/namespace/deviceservice/general", className = "com.dasanzhone.namespace.deviceservice.general.DeclarePpptpCardResponse")
    public CommonOperationReturn declarePpptpCard(
        @WebParam(name = "OntInput", targetNamespace = "http://www.dasanzhone.com/namespace/deviceservice/general")
        OntInput ontInput)
        throws DeviceException
    ;

    /**
     * 
     * @param ontInput
     * @return
     *     returns com.dasanzhone.namespace.deviceservice.general.CommonOperationReturn
     * @throws DeviceException
     */
    @WebMethod(operationName = "ConfigureUniLanPorts", action = "http://www.dasanzhone.com/namespace/deviceservice/ConfigureUniLanPorts")
    @WebResult(name = "ConfigureUniLanPortsResult", targetNamespace = "http://www.dasanzhone.com/namespace/deviceservice/general")
    @RequestWrapper(localName = "ConfigureUniLanPorts", targetNamespace = "http://www.dasanzhone.com/namespace/deviceservice/general", className = "com.dasanzhone.namespace.deviceservice.general.ConfigureUniLanPorts")
    @ResponseWrapper(localName = "ConfigureUniLanPortsResponse", targetNamespace = "http://www.dasanzhone.com/namespace/deviceservice/general", className = "com.dasanzhone.namespace.deviceservice.general.ConfigureUniLanPortsResponse")
    public CommonOperationReturn configureUniLanPorts(
        @WebParam(name = "OntInput", targetNamespace = "http://www.dasanzhone.com/namespace/deviceservice/general")
        OntInput ontInput)
        throws DeviceException
    ;

}
