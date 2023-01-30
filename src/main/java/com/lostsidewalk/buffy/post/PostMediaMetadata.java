package com.lostsidewalk.buffy.post;

import com.rometools.modules.mediarss.types.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.net.URI;
import java.net.URL;
import java.util.List;

import static java.util.Arrays.stream;
import static java.util.Optional.ofNullable;

@Data
@EqualsAndHashCode(callSuper = true)
public class PostMediaMetadata extends BasePostMediaObject {

    List<PostMediaThumbnail> thumbnails;
    PostMediaCommunity community;
    List<PostMediaCategory> categories;
    String copyright;
    URI copyrightUrl;
    String desc;
    String descType;
    URL[] backLinks;
    String[] comments;
    List<PostMediaCredit> credits;
    PostMediaHash hash;
    String[] keywords;
    List<PostMediaLicense> licenses;
    List<PostMediaLocation> locations;
    List<PostMediaPeerLink> peerLinks;
    List<PostMediaPrice> prices;
    List<PostMediaRating> ratings;
    String[] responses;
    List<PostMediaRestriction> restrictions;
    String rights;
    List<PostMediaScene> scenes;
    PostMediaStatus status;
    List<PostMediaSubTitle> subTitles;
    List<PostMediaText> text;
    String title;
    String titleType;

    public PostMediaMetadata(
            List<PostMediaThumbnail> thumbnails,
            PostMediaCommunity community,
            List<PostMediaCategory> categories,
            String copyright,
            URI copyrightUrl,
            String desc,
            String descType,
            URL[] backLinks,
            String[] comments,
            List<PostMediaCredit> credits,
            PostMediaHash hash,
            String[] keywords,
            List<PostMediaLicense> licenses,
            List<PostMediaLocation> locations,
            List<PostMediaPeerLink> peerLinks,
            List<PostMediaPrice> prices,
            List<PostMediaRating> ratings,
            String[] responses,
            List<PostMediaRestriction> restrictions,
            String rights,
            List<PostMediaScene> scenes,
            PostMediaStatus status,
            List<PostMediaSubTitle> subTitles,
            List<PostMediaText> text,
            String title,
            String titleType)
    {
        super();
        this.thumbnails = thumbnails;
        this.community = community;
        this.categories = categories;
        this.copyright = copyright;
        this.copyrightUrl = copyrightUrl;
        this.desc = desc;
        this.descType = descType;
        this.backLinks = backLinks;
        this.comments = comments;
        this.credits = credits;
        this.hash = hash;
        this.keywords = keywords;
        this.licenses = licenses;
        this.locations = locations;
        this.peerLinks = peerLinks;
        this.prices = prices;
        this.ratings = ratings;
        this.responses = responses;
        this.restrictions = restrictions;
        this.rights = rights;
        this.scenes = scenes;
        this.status = status;
        this.subTitles = subTitles;
        this.text = text;
        this.title = title;
        this.titleType = titleType;
    }

    public static PostMediaMetadata from(Metadata metadata) {
        List<PostMediaThumbnail> thumbnails = stream(metadata.getThumbnail()).map(PostMediaThumbnail::from).toList();
        PostMediaCommunity community = ofNullable(metadata.getCommunity()).map(PostMediaCommunity::from).orElse(null);
        List<PostMediaCategory> categories = stream(metadata.getCategories()).map(PostMediaCategory::from).toList();
        String copyright = metadata.getCopyright();
        URI copyrightUrl = metadata.getCopyrightUrl();
        String desc = metadata.getDescription();
        String descType = metadata.getDescriptionType();
        URL[] backLinks = metadata.getBackLinks();
        String[] comments = metadata.getComments();
        List<PostMediaCredit> credits = stream(metadata.getCredits()).map(PostMediaCredit::from).toList();
        // metadata.getEmbed(); // ignore
        PostMediaHash hash = ofNullable(metadata.getHash()).map(PostMediaHash::from).orElse(null);
        String[] keywords = metadata.getKeywords();
        List<PostMediaLicense> licenses = stream(metadata.getLicenses()).map(PostMediaLicense::from).toList();
        List<PostMediaLocation> locations = stream(metadata.getLocations()).map(PostMediaLocation::from).toList();
        List<PostMediaPeerLink> peerLinks = stream(metadata.getPeerLinks()).map(PostMediaPeerLink::from).toList();
        List<PostMediaPrice> prices = stream(metadata.getPrices()).map(PostMediaPrice::from).toList();
        List<PostMediaRating> ratings = stream(metadata.getRatings()).map(PostMediaRating::from).toList();
        String[] responses = metadata.getResponses();
        List<PostMediaRestriction> restrictions = stream(metadata.getRestrictions()).map(PostMediaRestriction::from).toList();
        String rights = ofNullable(metadata.getRights()).map(Enum::toString).orElse(null);
        List<PostMediaScene> scenes = stream(metadata.getScenes()).map(PostMediaScene::from).toList();
        PostMediaStatus status = ofNullable(metadata.getStatus()).map(PostMediaStatus::from).orElse(null);
        List<PostMediaSubTitle> subTitles = stream(metadata.getSubTitles()).map(PostMediaSubTitle::from).toList();
        List<PostMediaText> text = stream(metadata.getText()).map(PostMediaText::from).toList();
        String title = metadata.getTitle();
        String titleType = metadata.getTitleType();

        return new PostMediaMetadata(
                thumbnails,
                community,
                categories,
                copyright,
                copyrightUrl,
                desc,
                descType,
                backLinks,
                comments,
                credits,
                hash,
                keywords,
                licenses,
                locations,
                peerLinks,
                prices,
                ratings,
                responses,
                restrictions,
                rights,
                scenes,
                status,
                subTitles,
                text,
                title,
                titleType
        );
    }

    @SuppressWarnings({"unused"})
    public Metadata toModule() {
        Metadata metadata = new Metadata();
        metadata.setThumbnail(convertArray(this::getThumbnails, PostMediaThumbnail::toModule, Thumbnail.class));
        metadata.setCategories(convertArray(this::getCategories, PostMediaCategory::toModule, Category.class));
        metadata.setCopyright(getCopyright());
        metadata.setCopyrightUrl(getCopyrightUrl());
        metadata.setDescription(getDesc());
        metadata.setDescriptionType(getDescType());
        metadata.setBackLinks(getBackLinks());
        metadata.setComments(getComments());
        metadata.setCommunity(ofNullable(getCommunity()).map(PostMediaCommunity::toModule).orElse(null));
        metadata.setCredits(convertArray(this::getCredits, PostMediaCredit::toModule, Credit.class));
        metadata.setHash(ofNullable(getHash()).map(PostMediaHash::toModule).orElse(null));
        metadata.setKeywords(getKeywords());
        metadata.setLicenses(convertArray(this::getLicenses, PostMediaLicense::toModule, License.class));
        metadata.setLocations(convertArray(this::getLocations, PostMediaLocation::toModule, Location.class));
        metadata.setPeerLinks(convertArray(this::getPeerLinks, PostMediaPeerLink::toModule, PeerLink.class));
        metadata.setPrices(convertArray(this::getPrices, PostMediaPrice::toModule, Price.class));
        metadata.setRatings(convertArray(this::getRatings, PostMediaRating::toModule, Rating.class));
        metadata.setResponses(getResponses());
        metadata.setRestrictions(convertArray(this::getRestrictions, PostMediaRestriction::toModule, Restriction.class));
        metadata.setRights(ofNullable(getRights()).map(Metadata.RightsStatus::valueOf).orElse(null)); // TODO: safety
        metadata.setScenes(convertArray(this::getScenes, PostMediaScene::toModule, Scene.class));
        metadata.setStatus(ofNullable(getStatus()).map(PostMediaStatus::toModule).orElse(null));
        metadata.setSubTitles(convertArray(this::getSubTitles, PostMediaSubTitle::toModule, SubTitle.class));
        metadata.setText(convertArray(this::getText, PostMediaText::toModule, Text.class));
        metadata.setTitle(getTitle());
        metadata.setTitleType(getTitleType());

        return metadata;
    }
}
