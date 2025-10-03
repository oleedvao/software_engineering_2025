package org.musicas.core.dto;

public class CreateArtistRequest {
    private final String name;

    public CreateArtistRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
