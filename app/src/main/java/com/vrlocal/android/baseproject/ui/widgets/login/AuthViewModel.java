package com.vrlocal.android.baseproject.ui.widgets.login;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;

import com.vrlocal.android.baseproject.api.AuthApi;
import com.vrlocal.android.baseproject.ui.base.BaseViewModel;
import com.vrlocal.android.baseproject.ui.base.SessionManager;

import javax.inject.Inject;

import io.reactivex.functions.Function;


public class AuthViewModel extends BaseViewModel<IAuthView> {

    private static final String TAG = "AuthViewModel";

    // inject
    private final SessionManager sessionManager; // @Singleton scoped dependency
    private final AuthApi authApi; // @AuthScope scoped dependency

    @Inject
    public AuthViewModel(AuthApi authApi, SessionManager sessionManager) {
        this.sessionManager = sessionManager;
        this.authApi = authApi;
        Log.d(TAG, "AuthViewModel: viewmodel is working...");
    }

    public LiveData<AuthResource<User>> observeAuthState(){
        return sessionManager.getAuthUser();
    }

    public void authenticateWithId(int userId) {
        Log.d(TAG, "attemptLogin: attempting to login.");
        sessionManager.authenticateWithId(queryUserId(userId));
    }

    private LiveData<AuthResource<User>> queryUserId(int userId) {

        return LiveDataReactiveStreams.fromPublisher(authApi.getUser(userId)

                // instead of calling onError, do this
                .onErrorReturn(new Function<Throwable, User>() {
                    @Override
                    public User apply(Throwable throwable) throws Exception {
                        User errorUser = new User();
                        errorUser.setId(-1);
                        return errorUser;
                    }
                })

                // wrap User object in AuthResource
                .map(new Function<User, AuthResource<User>>() {
                    @Override
                    public AuthResource<User> apply(User user) throws Exception {
                        if(user.getId() == -1){
                            return AuthResource.error("Could not authenticate", null);
                        }
                        return AuthResource.authenticated(user);
                    }
                })
                .subscribeOn(null));
    }


}









