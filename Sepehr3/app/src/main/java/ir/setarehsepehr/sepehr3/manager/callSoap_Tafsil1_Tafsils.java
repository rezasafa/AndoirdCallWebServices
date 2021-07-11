package ir.setarehsepehr.sepehr3.manager;
import android.content.Context;
import org.ksoap2.serialization.*;
import org.ksoap2.transport.*;
import org.ksoap2.SoapEnvelope;
public class callSoap_Tafsil1_Tafsils {
    public callSoap_Tafsil1_Tafsils(){}
    public String Call(Context context, String co, String y ,String TafsilNo){
        sVar c = new sVar();
        String SOAP_ACTION = c.Get_WSDL_TARGET_NAMESPACE() + c.Get_Operator_Get_Tafsil1_Tafsils();
        String OPERATION_NAME = c.Get_Operator_Get_Tafsil1_Tafsils();
        String SOAP_ADDRESS = new dbApp(context).Read_Setting_Field("IP_Address") + c.Get_WAPI();

        SoapObject request = new SoapObject(c.Get_WSDL_TARGET_NAMESPACE(),OPERATION_NAME);

        PropertyInfo pi=new PropertyInfo();
        pi.setName("TafsilNo");
        pi.setValue(TafsilNo);
        pi.setType(Integer.class);
        request.addProperty(pi);

        pi=new PropertyInfo();
        pi.setName("co");
        pi.setValue(co);
        pi.setType(Integer.class);
        request.addProperty(pi);

        pi=new PropertyInfo();
        pi.setName("y");
        pi.setValue(y);
        pi.setType(Integer.class);
        request.addProperty(pi);

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
