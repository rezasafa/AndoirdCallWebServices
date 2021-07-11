package ir.setarehsepehr.sepehr3.manager;

import org.ksoap2.serialization.*;
import org.ksoap2.transport.*;
import org.ksoap2.SoapEnvelope;

public class callSoap_CheckConnetion {
    public callSoap_CheckConnetion(){}

    public String Call(String sIp)
    {
        sVar c = new sVar();
        String SOAP_ACTION = c.Get_WSDL_TARGET_NAMESPACE() + c.Get_Operator_CheckConnection();
        String OPERATION_NAME = c.Get_Operator_CheckConnection();
        String SOAP_ADDRESS = sIp + c.Get_WAPI();

        SoapObject request = new SoapObject(c.Get_WSDL_TARGET_NAMESPACE(),OPERATION_NAME);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response=null;
        try
        {
            httpTransport.call(SOAP_ACTION, envelope);
            response = envelope.getResponse();
        }
        catch (Exception exception)
        {
            response=exception.toString();
        }

        return response.toString();
    }
}
