package eu.miaplatform.customplugin.springboot.example.model

import com.google.gson.annotations.SerializedName
import eu.miaplatform.crud.library.annotations.CollectionAnnotation
import eu.miaplatform.crud.model.BaseObject

@CollectionAnnotation(collectionName = "news")

class News (
        @SerializedName("title")
        var title: String,
        @SerializedName("body")
        var body: String,
        @SerializedName("author")
        var author: String,
        @SerializedName("publishedAt")
        var publishedAt: String,
        @SerializedName("maxCharacters")
        var maxCharacters: Int
) : BaseObject()