package lelab.soapretrofit.model.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "EvaluateShop")
public class EvaluateRequestModel {
    @Element(name = "shopCheckList", required = false)
    public EvaluateCheckList checkList;
}
