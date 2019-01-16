package lelab.soapretrofit.model.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(name = "soap:Body", strict = false)
public class EvaluateResponseBody {
    @Element(name = "EvaluateShopResponse", required = false)
    @Namespace(reference = "http://tempuri.org/")
    public EvaluateResponseModel evaluateResponseModel;
}
