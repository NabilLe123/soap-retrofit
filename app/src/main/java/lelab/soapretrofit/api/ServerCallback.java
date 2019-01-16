package lelab.soapretrofit.api;

//this is an interface called for network calls
//so that we wont have an issue with handling
//bg tasks
public interface ServerCallback {

    void onSuccess(Object object);

    void onTokenExpiry();

    void onError(Throwable throwable);

    void onNetworkError();

    void onComplete();
}
