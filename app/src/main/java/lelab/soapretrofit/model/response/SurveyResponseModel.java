package lelab.soapretrofit.model.response;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "GetShopCheckListResponse", strict = false)
@Namespace(reference = "http://tempuri.org/")
public class SurveyResponseModel {

    @ElementList(name = "GetShopCheckListResult", required = false)
    public List<Survey> surveys;
}
