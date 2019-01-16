package lelab.soapretrofit.model.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;

@Root(name = "soap:Envelope")
@NamespaceList({
        @Namespace(prefix = "soap", reference = "http://schemas.xmlsoap.org/soap/envelope/"),
        @Namespace(prefix = "xsi", reference = "http://www.w3.org/2001/XMLSchema-instance"),
        @Namespace(prefix = "xsd", reference = "http://www.w3.org/2001/XMLSchema")
})
public class EvaluateResponseEnvelope {
    @Element(name = "Body")
    @Namespace(prefix = "soap")
    public EvaluateResponseBody evaluateResponseBody;
}
