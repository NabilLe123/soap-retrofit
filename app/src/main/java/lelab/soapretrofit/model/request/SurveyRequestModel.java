package lelab.soapretrofit.model.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(name = "GetShopCheckList")
@Namespace(reference = "http://tempuri.org/")
public class SurveyRequestModel {
    @Element(name = "licenseNo", required = false)
    private String licenseNo;
    @Element(name = "language", required = false)
    private String language;

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
