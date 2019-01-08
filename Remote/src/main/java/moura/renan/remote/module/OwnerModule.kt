package moura.renan.remote.module

import com.google.gson.annotations.SerializedName

class OwnerModel(@SerializedName("login") val ownerName: String,
                 @SerializedName("avatar_url") val ownerAvatar: String)