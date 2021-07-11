package ir.setarehsepehr.sepehr3.manager;
import android.content.Context;
import org.ksoap2.serialization.*;
import org.ksoap2.transport.*;
import org.ksoap2.SoapEnvelope;
public class callSoap_Tafsil2_Accounts {
    public callSoap_Tafsil2_Accounts(){}
    public String Call(Context context, String co, String y ,String Acc_No){
        sVar c = new sVar();
        String SOAP_ACTION = c.Get_WSDL_TARGET_NAMESPACE() + c.Get_Operator_Get_Tafsil2_Accounts();
        String OPERATION_NAME = c.Get_Operator_Get_Tafsil2_Accounts();
        String SOAP_ADDRESS = new dbApp(context).Read_Setting_Field("IP_Address") + c.Get_WAPI();

        SoapObject request = new SoapObject(c.Get_WSDL_TARGET_NAMESPACE(),OPERATION_NAME);

        PropertyInfo pi=new PropertyInfo();
        pi.setName("Acc_No");
        pi.setValue(Acc_No);
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

