package bg.softuni.pathfinder.model.views;

import java.util.List;
import java.util.Set;

public class RouteDetailsView {
    // route.getId(),
    //                route.getName(),
    //                route.getAuthor().getFullName(),
    //                route.getLevel().name(),
    //                route.getVideoUrl(),
    //                route.getDescription(),
    //                route.getGpxCoordinates(),
    //                route.getPictures().stream().map(Picture::getUrl).collect(Collectors.toList())
    //

    private Long id;
    private String name;
    private String authorName;
    private String level;

    private String videoUrl;

    private String description;
    private String gpxCoordinates;
    private List<String> picturesUrls;

    public RouteDetailsView(Long id, String name, String authorName, String level, String videoUrl, String description, String gpxCoordinates, List<String> picturesUrls) {
        this.id = id;
        this.name = name;
        this.authorName = authorName;
        this.level = level;
        this.videoUrl = videoUrl;
        this.description = description;
        this.gpxCoordinates = gpxCoordinates;
        this.picturesUrls = picturesUrls;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGpxCoordinates() {
        return gpxCoordinates;
    }

    public void setGpxCoordinates(String gpxCoordinates) {
        this.gpxCoordinates = gpxCoordinates;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public List<String> getPicturesUrls() {
        return picturesUrls;
    }

    public void setPicturesUrls(List<String> picturesUrls) {
        this.picturesUrls = picturesUrls;
    }

}
