package eu.miaplatform.customplugin.springboot.example.model

import com.google.gson.annotations.SerializedName
import eu.miaplatform.crud.library.annotations.CollectionAnnotation
import eu.miaplatform.crud.model.BaseObject


@CollectionAnnotation(collectionName = "author")

class Author(
        @SerializedName("name")
        var name: String,
        @SerializedName("surname")
        var surname: String,
        @SerializedName("age")
        var age: Int
) : BaseObject()