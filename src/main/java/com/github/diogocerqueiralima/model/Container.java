package com.github.diogocerqueiralima.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public record Container(
        @SerializedName("Id") String id,
        @SerializedName("Names") List<String> names,
        @SerializedName("Image") String image,
        @SerializedName("ImageID") String imageId,
        //TODO ImageManifestDescription
        @SerializedName("Command") String command,
        @SerializedName("Created") Long created
) {}
