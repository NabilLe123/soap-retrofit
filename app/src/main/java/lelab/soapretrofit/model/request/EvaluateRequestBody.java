package lelab.soapretrofit.model.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(name = "soap:Body", strict = false)
public class EvaluateRequestBody {
    @Element(name = "EvaluateShop", required = false)
    @Namespace(reference = "http://tempuri.org/")
    public EvaluateRequestModel evaluateRequestModel;
}
