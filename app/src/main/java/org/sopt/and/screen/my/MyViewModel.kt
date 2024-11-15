package org.sopt.and.screen.my

import android.util.Log
import androidx.lifecycle.ViewModel
import org.sopt.and.R
import org.sopt.and.api.dto.response.ResponseMyHobby
import org.sopt.and.api.factory.ServicePool
import org.sopt.and.data.ContentItem
import org.sopt.and.userPreferences.UserViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewModel: ViewModel() {
    private val userService by lazy { ServicePool.userService }

    val userProfileImg: Int = R.drawable.img_sample
    private val _viewHistory = mutableListOf<ContentItem>()
    val viewHistory: List<ContentItem> get() = _viewHistory
    private val _interestContent = mutableListOf<ContentItem>()
    val interestContent: List<ContentItem> get() = _interestContent

    fun getMyHobby(userViewModel: UserViewModel) {
        val token = userViewModel.preferenceToken.value

        userService.getMyHobby(token).enqueue(object : Callback<ResponseMyHobby> {
            override fun onResponse(
                call: Call<ResponseMyHobby>,
                response: Response<ResponseMyHobby>
            ) {
                if (response.isSuccessful) {
                    response.body()?.result?.let { userViewModel.updateHobby(it.hobby) }
                } else {
                    val error = response.message()
                    Log.e("error", error.toString())
                }
            }

            override fun onFailure(call: Call<ResponseMyHobby>, t: Throwable) {
                Log.e("failure", t.message.toString())
            }

        })
    }
}