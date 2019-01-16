package lelab.soapretrofit.model.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(name = "EvaluateShopResponse", strict = false)
@Namespace(reference = "http://tempuri.org/")
public class EvaluateResponseModel {
    @Element(name = "EvaluateShopResult", required = false)
    public Evaluate evaluate;
}