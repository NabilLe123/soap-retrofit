package lelab.soapretrofit;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import lelab.soapretrofit.api.RetrofitGenerator;
import lelab.soapretrofit.model.request.EvaluateCheckList;
import lelab.soapretrofit.model.request.EvaluateRequest;
import lelab.soapretrofit.model.request.EvaluateRequestBody;
import lelab.soapretrofit.model.request.EvaluateRequestEnvelope;
import lelab.soapretrofit.model.request.EvaluateRequestModel;
import lelab.soapretrofit.model.request.SurveyRequestBody;
import lelab.soapretrofit.model.request.SurveyRequestEnvelope;
import lelab.soapretrofit.model.request.SurveyRequestModel;
import lelab.soapretrofit.model.response.EvaluateResponseEnvelope;
import lelab.soapretrofit.model.response.SurveyResponseEnvelope;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ProgressBar pbMain;
    private String TAG = "soap_retro";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pbMain = findViewById(R.id.pb_main);
        pbMain.setVisibility(View.GONE);

        Button btnSurveyCall = findViewById(R.id.btn_survey_call);
        btnSurveyCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callSurveyAPI();
            }
        });

        Button btnEvaluationCall = findViewById(R.id.btn_evaluation_call);
        btnEvaluationCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callEvaluationAPI();
            }
        });
    }

    private void callSurveyAPI() {
        pbMain.setVisibility(View.VISIBLE);

        SurveyRequestEnvelope requestEnvelop = new SurveyRequestEnvelope();
        SurveyRequestBody requestBody = new SurveyRequestBody();
        SurveyRequestModel requestModel = new SurveyRequestModel();
        requestModel.setLanguage("en-US");
        requestModel.setLicenseNo("2200294");
        requestBody.surveyRequestModel = requestModel;
        requestEnvelop.surveyRequestBody = requestBody;
        Call<SurveyResponseEnvelope> call = RetrofitGenerator.getApiInterface().getShopSurvey(requestEnvelop);
        call.enqueue(new Callback<SurveyResponseEnvelope>() {
            @Override
            public void onResponse(@NonNull Call<SurveyResponseEnvelope> call, @NonNull Response<SurveyResponseEnvelope> response) {

                Log.d(TAG, "onResponse: call " + call + " code " + response.code()
                        + " headers " + response.headers()
                        + " message " + response.message()
                        + " raw " + response.raw()
                        + " body " + response.body());

                pbMain.setVisibility(View.GONE);
                SurveyResponseEnvelope responseEnvelope = response.body();
                if (responseEnvelope != null) {
                    Log.d(TAG, "responseEnvelope " + responseEnvelope);
                    Log.d(TAG, "responseEnvelope.surveyResponseBody " + responseEnvelope.surveyResponseBody);
                    Log.d(TAG, "responseEnvelope.surveyResponseBody.surveyResponseModel " + responseEnvelope.surveyResponseBody.surveyResponseModel);
                    Log.d(TAG, "responseEnvelope.surveyResponseBody.surveyResponseModel.surveys " + responseEnvelope.surveyResponseBody.surveyResponseModel.surveys.size());

                    //Log.d(TAG, "getTaskOperationID " + responseEnvelope.surveyResponseBody.surveyResponseModel.surveys.get(0).getTaskOperationID());
                }
            }

            @Override
            public void onFailure(@NonNull Call<SurveyResponseEnvelope> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                pbMain.setVisibility(View.GONE);
            }
        });
    }

    private void callEvaluationAPI() {
        pbMain.setVisibility(View.VISIBLE);
        List<EvaluateRequest> evaluateRequests = new ArrayList<>();
        evaluateRequests.add(new EvaluateRequest("26506", "true"));
        evaluateRequests.add(new EvaluateRequest("51440", "test"));

        EvaluateRequestEnvelope requestEnvelop = new EvaluateRequestEnvelope();
        EvaluateRequestBody requestBody = new EvaluateRequestBody();
        EvaluateRequestModel requestModel = new EvaluateRequestModel();
        EvaluateCheckList checkList = new EvaluateCheckList();

        checkList.evaluateRequests = evaluateRequests;
        requestModel.checkList = checkList;
        requestBody.evaluateRequestModel = requestModel;
        requestEnvelop.evaluateRequestBody = requestBody;

        Call<EvaluateResponseEnvelope> call = RetrofitGenerator.getApiInterface().evaluateShop(requestEnvelop);
        call.enqueue(new Callback<EvaluateResponseEnvelope>() {
            @Override
            public void onResponse(@NonNull Call<EvaluateResponseEnvelope> call, @NonNull Response<EvaluateResponseEnvelope> response) {
                Log.d(TAG, "onResponse: call " + call + " code " + response.code()
                        + " headers " + response.headers()
                        + " message " + response.message()
                        + " raw " + response.raw()
                        + " body " + response.body());
                pbMain.setVisibility(View.GONE);

                EvaluateResponseEnvelope responseEnvelope = response.body();
                if (responseEnvelope != null) {
                    Log.d(TAG, "responseEnvelope " + responseEnvelope);
                    Log.d(TAG, "responseEnvelope.evaluateResponseBody " + responseEnvelope.evaluateResponseBody);
                    Log.d(TAG, "responseEnvelope.evaluateResponseBody.evaluateResponseModel " + responseEnvelope.evaluateResponseBody.evaluateResponseModel);
                    Log.d(TAG, "responseEnvelope.evaluateResponseBody.evaluateResponseModel.evaluate " + responseEnvelope.evaluateResponseBody.evaluateResponseModel.evaluate);
                    Log.d(TAG, "responseEnvelope.evaluateResponseBody.evaluateResponseModel.evaluate.penalties " + responseEnvelope.evaluateResponseBody.evaluateResponseModel.evaluate.penalties.size());

                    //Log.d(TAG, "getEvaluationPercentage " + responseEnvelope.evaluateResponseBody.evaluateResponseModel.evaluate.getEvaluationPercentage());
                    //Log.d(TAG, "getPenaltyCode " + responseEnvelope.evaluateResponseBody.evaluateResponseModel.evaluate.penalties.get(0).getPenaltyCode());
                }
            }

            @Override
            public void onFailure(@NonNull Call<EvaluateResponseEnvelope> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                pbMain.setVisibility(View.GONE);
            }
        });
    }
}
