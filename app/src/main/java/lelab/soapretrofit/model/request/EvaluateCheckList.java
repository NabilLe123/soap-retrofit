package lelab.soapretrofit.model.request;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "shopCheckList")
public class EvaluateCheckList {
    @ElementList(name = "TaskOperationEvaluation", required = false)
    public List<EvaluateRequest> evaluateRequests;
}
