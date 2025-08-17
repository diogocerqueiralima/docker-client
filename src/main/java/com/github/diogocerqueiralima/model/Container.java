package com.github.diogocerqueiralima.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public record Container(
        @SerializedName("Id") String id,
        @SerializedName("Names") List<String> names,
        @SerializedName("Image") String image,
        @SerializedName("ImageID") String imageId,
        @SerializedName("ImageManifestDescriptor") ImageManifestDescriptor imageManifestDescriptor,
        @SerializedName("Command") String command,
        @SerializedName("Created") Long created,
        @SerializedName("Ports") List<Port> ports,
        @SerializedName("SizeRw") Long sizeRw,
        @SerializedName("SizeRootFs") Long sizeRootFs,
        @SerializedName("Labels") Map<String, String> labels,
        @SerializedName("State") String state,
        @SerializedName("Status") String status,
        @SerializedName("HostConfig") HostConfig hostConfig,
        @SerializedName("NetworkSettings") NetworkSettings networkSettings,
        @SerializedName("Mounts") List<Mount> mounts
) {

    private record Port(
            @SerializedName("PrivatePort") Integer privatePort,
            @SerializedName("PublicPort") Integer publicPort,
            @SerializedName("Type") String type
    ) {}

    private record ImageManifestDescriptor(
            String mediaType,
            String digest,
            Integer size,
            List<String> urls,
            Map<String, String> annotations,
            String data,
            Platform platform,
            String artifactType
    ) {}

    private record Platform(
            String architecture,
            String os,
            @SerializedName("os.version") String osVersion,
            @SerializedName("os.features") List<String> osFeatures,
            String variant
    ) {}

    private record HostConfig(
            @SerializedName("NetworkMode") String networkMode,
            @SerializedName("Annotations") Map<String, String> annotations
    ) {}

    private record NetworkSettings(
            @SerializedName("Networks") Map<String, Network> networks
    ) {}

    private record Network(
        @SerializedName("IPAMConfig") IPAMConfig ipamConfig,
        @SerializedName("Links") List<String> links,
        @SerializedName("MacAddress") String macAddress,
        @SerializedName("Aliases") List<String> aliases,
        @SerializedName("DriverOpts") Map<String, String> driverOpts,
        @SerializedName("GwPriority") Integer gwPriority,
        @SerializedName("NetworkID") String networkId,
        @SerializedName("EndpointID") String endpointId,
        @SerializedName("Gateway") String gateway,
        @SerializedName("IPAddress") String ipAddress,
        @SerializedName("IPPrefixLen") Integer ipPrefixLen,
        @SerializedName("IPv6Gateway") String ipv6Gateway,
        @SerializedName("GlobalIPv6Address") String globalIPv6Address,
        @SerializedName("GlobalIPv6Len") Integer globalIPv6Len,
        @SerializedName("DNSNames") List<String> dnsNames
    ) {}

    private record IPAMConfig(
            @SerializedName("IPv4Address") String ipv4Address,
            @SerializedName("IPv6Address") String ipv6Address,
            @SerializedName("LinkLocalIPs") List<String> linkLocalIPs
    ) {}

    private record Mount(
            @SerializedName("Type") String type,
            @SerializedName("Name") String name,
            @SerializedName("Source") String source,
            @SerializedName("Destination") String destination,
            @SerializedName("Driver") String driver,
            @SerializedName("Mode") String mode,
            @SerializedName("RW") Boolean rw,
            @SerializedName("Propagation") String propagation
    ) {}

}
