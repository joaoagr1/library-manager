package com.manager.library.dtos;

import lombok.Builder;

import java.util.List;

@Builder
public class GoogleBooksDetailResponseDTO {
    private String id;
    private VolumeInfo volumeInfo;

    public GoogleBooksDetailResponseDTO(String id, VolumeInfo volumeInfo) {
        this.id = id;
        this.volumeInfo = volumeInfo;
    }

    public String getId() {
        return id;
    }

    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }

    public static class VolumeInfo {
        private String title;
        private List<String> authors;
        private String publishedDate;
        private List<IndustryIdentifier> industryIdentifiers;
        private List<String> categories;

        public VolumeInfo(String title, List<String> authors, String publishedDate, List<IndustryIdentifier> industryIdentifiers, List<String> categories) {

            this.title = title;
            this.authors = authors;
            this.publishedDate = publishedDate;
            this.industryIdentifiers = industryIdentifiers;
            this.categories = categories;

        }

        public String getTitle() {
            return title;
        }

        public List<String> getAuthors() {
            return authors;
        }

        public String getPublishedDate() {
            return publishedDate;
        }

        public List<IndustryIdentifier> getIndustryIdentifiers() {
            return industryIdentifiers;
        }

        public List<String> getCategories() {
            return categories;
        }
    }

    public static class IndustryIdentifier {

        private String type;
        private String identifier;

        public IndustryIdentifier(String type, String identifier) {

            this.type = type;
            this.identifier = identifier;

        }

        public String getType() {
            return type;
        }

        public String getIdentifier() {
            return identifier;
        }
    }
}