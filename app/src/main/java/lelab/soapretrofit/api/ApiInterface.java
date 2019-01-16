package lelab.soapretrofit.api;

import io.reactivex.Observable;
import lelab.soapretrofit.model.request.EvaluateRequestEnvelope;
import lelab.soapretrofit.model.request.SurveyRequestEnvelope;
import lelab.soapretrofit.model.response.EvaluateResponseEnvelope;
import lelab.soapretrofit.model.response.SurveyResponseEnvelope;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {
    @Headers({"Content-Type: text/xml;charset=UTF-8"})
    @POST("Tasks.asmx?op=GetShopCheckList")
    Observable<SurveyResponseEnvelope> getShopSurvey(@Body SurveyRequestEnvelope requestEnvelope);

    @Headers({"Content-Type: text/xml;charset=UTF-8"})
    @POST("Tasks.asmx?op=EvaluateShop")
    Call<EvaluateResponseEnvelope> evaluateShop(@Body EvaluateRequestEnvelope requestEnvelope);
}
