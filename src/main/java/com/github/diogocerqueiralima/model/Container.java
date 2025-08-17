package com.github.diogocerqueiralima.model;

import com.google.gson.annotations.SerializedName;

public record Container(

        @SerializedName("Id") String id

) {}
