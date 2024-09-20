package com.manager.library.model.dtos;

import lombok.Data;

import java.util.List;

@Data
public class GoogleBooksResponseDTO {
    private String kind;
    private int totalItems;
    private List<Item> items;

    @Data
    public static class Item {
        private String id;
        private VolumeInfo volumeInfo;

        @Data
        public static class VolumeInfo {
            private String title;
            private List<String> authors;
            private String publishedDate;
            private List<IndustryIdentifier> industryIdentifiers;
            private List<String> categories;

            @Data
            public static class IndustryIdentifier {
                private String type;
                private String identifier;
            }
        }
    }
}