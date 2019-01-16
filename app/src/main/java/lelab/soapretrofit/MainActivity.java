package lelab.soapretrofit;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import lelab.soapretrofit.api.RetrofitGenerator;
import lelab.soapretrofit.model.request.SurveyRequestBody;
import lelab.soapretrofit.model.request.SurveyRequestEnvelope;
import lelab.soapretrofit.model.request.SurveyRequestModel;
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

        Button btnRetry = findViewById(R.id.btn_retry);
        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callAPI();
            }
        });
        callAPI();
    }

    private void callAPI() {
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

                    //List<ShopResponseModel> list = responseEnvelope.body.shopModel.shopResponseMainMainModel.shopResponseModels;
                    //Log.d("nabil", ": " + list.size());
                }
            }

            @Override
            public void onFailure(@NonNull Call<SurveyResponseEnvelope> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                pbMain.setVisibility(View.GONE);
            }
        });
    }
}
