package com.cse.leavetest.Network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {



//
//    @POST("Api/LoginApi")
//    Call<LoginResponse> userLogin(@Query("username") String username,
//                                     @Query("userPassword") String userPassword,LoginRequest loginRequest);

//    @POST("basic")
//    Call<LoginResponse> getuser(@Header("Authorization")  String authHeader) ;


    @GET("Api/LeaveApi")
    Call<List<LeaveSummary>> leavesummary(
            @Query("CompanyId") String CompanyId,
            @Query("Employee") String Employee
    );


}
