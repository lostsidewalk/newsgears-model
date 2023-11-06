package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rometools.modules.mediarss.types.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.io.Serializable;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static java.util.Arrays.stream;
import static java.util.Optional.ofNullable;
import static org.apache.commons.collections4.CollectionUtils.size;

/**
 * Represents the metadata associated with media in a post.
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(NON_EMPTY)
@NoArgsConstructor
public class PostMediaMetadata extends BasePostMediaObject implements Serializable {

    @Serial
    private static final long serialVersionUID = 212267345121249823L;

    /**
     * List of thumbnails associated with the media.
     */
    List<PostMediaThumbnail> thumbnails;

    /**
     * Community information associated with the media.
     */
    PostMediaCommunity community;

    /**
     * List of categories associated with the media.
     */
    List<PostMediaCategory> categories;

    /**
     * Copyright information associated with the media.
     */
    String copyright;

    /**
     * Copyright URL associated with the media.
     */
    URI copyrightUrl;

    /**
     * Description of the media.
     */
    String desc;

    /**
     * Description type of the media.
     */
    String descType;

    /**
     * Array of backlinks associated with the media.
     */
    URL[] backLinks;

    /**
     * Array of comments associated with the media.
     */
    String[] comments;

    /**
     * List of credits associated with the media.
     */
    List<PostMediaCredit> credits;

    /**
     * Hash value associated with the media.
     */
    PostMediaHash hash;

    /**
     * Array of keywords associated with the media.
     */
    String[] keywords;

    /**
     * List of licenses associated with the media.
     */
    List<PostMediaLicense> licenses;

    /**
     * List of locations associated with the media.
     */
    List<PostMediaLocation> locations;

    /**
     * List of peer links associated with the media.
     */
    List<PostMediaPeerLink> peerLinks;

    /**
     * List of prices associated with the media.
     */
    List<PostMediaPrice> prices;

    /**
     * List of ratings associated with the media.
     */
    List<PostMediaRating> ratings;

    /**
     * Array of responses associated with the media.
     */
    String[] responses;

    /**
     * List of restrictions associated with the media.
     */
    List<PostMediaRestriction> restrictions;

    /**
     * Rights associated with the media.
     */
    String rights;

    /**
     * List of scenes associated with the media.
     */
    List<PostMediaScene> scenes;

    /**
     * Status of the media.
     */
    PostMediaStatus status;

    /**
     * List of subtitles associated with the media.
     */
    List<PostMediaSubTitle> subTitles;

    /**
     * List of text elements associated with the media.
     */
    List<PostMediaText> text;

    /**
     * Title of the media.
     */
    String title;

    /**
     * Title type of the media.
     */
    String titleType;

    /**
     * Creates a new instance of PostMediaMetadata.
     *
     * @param thumbnails   List of thumbnails associated with the media.
     * @param community    Community information associated with the media.
     * @param categories   List of categories associated with the media.
     * @param copyright    Copyright information associated with the media.
     * @param copyrightUrl Copyright URL associated with the media.
     * @param desc         Description of the media.
     * @param descType     Description type of the media.
     * @param backLinks    Array of backlinks associated with the media.
     * @param comments     Array of comments associated with the media.
     * @param credits      List of credits associated with the media.
     * @param hash         Hash value associated with the media.
     * @param keywords     Array of keywords associated with the media.
     * @param licenses     List of licenses associated with the media.
     * @param locations    List of locations associated with the media.
     * @param peerLinks    List of peer links associated with the media.
     * @param prices       List of prices associated with the media.
     * @param ratings      List of ratings associated with the media.
     * @param responses    Array of responses associated with the media.
     * @param restrictions List of restrictions associated with the media.
     * @param rights       Rights associated with the media.
     * @param scenes       List of scenes associated with the media.
     * @param status       Status of the media.
     * @param subTitles    List of subtitles associated with the media.
     * @param text         List of text elements associated with the media.
     * @param title        Title of the media.
     * @param titleType    Title type of the media.
     */
    private PostMediaMetadata(
            Collection<? extends PostMediaThumbnail> thumbnails,
            PostMediaCommunity community,
            Collection<? extends PostMediaCategory> categories,
            String copyright,
            URI copyrightUrl,
            String desc,
            String descType,
            URL[] backLinks,
            String[] comments,
            Collection<? extends PostMediaCredit> credits,
            PostMediaHash hash,
            String[] keywords,
            Collection<? extends PostMediaLicense> licenses,
            Collection<? extends PostMediaLocation> locations,
            Collection<? extends PostMediaPeerLink> peerLinks,
            Collection<? extends PostMediaPrice> prices,
            Collection<? extends PostMediaRating> ratings,
            String[] responses,
            Collection<? extends PostMediaRestriction> restrictions,
            String rights,
            Collection<? extends PostMediaScene> scenes,
            PostMediaStatus status,
            Collection<? extends PostMediaSubTitle> subTitles,
            Collection<? extends PostMediaText> text,
            String title,
            String titleType) {
        this.thumbnails = thumbnails == null ? null : List.copyOf(thumbnails);
        this.community = community;
        this.categories = categories == null ? null : List.copyOf(categories);
        this.copyright = copyright;
        this.copyrightUrl = copyrightUrl;
        this.desc = desc;
        this.descType = descType;
        this.backLinks = backLinks == null ? null : Arrays.copyOf(backLinks, size(backLinks));
        this.comments = comments == null ? null : Arrays.copyOf(comments, comments.length);
        this.credits = credits == null ? null : List.copyOf(credits);
        this.hash = hash;
        this.keywords = keywords == null ? null : Arrays.copyOf(keywords, size(keywords));
        this.licenses = licenses == null ? null : List.copyOf(licenses);
        this.locations = locations == null ? null : List.copyOf(locations);
        this.peerLinks = peerLinks == null ? null : List.copyOf(peerLinks);
        this.prices = prices == null ? null : List.copyOf(prices);
        this.ratings = ratings == null ? null : List.copyOf(ratings);
        this.responses = responses == null ? null : Arrays.copyOf(responses, size(responses));
        this.restrictions = restrictions == null ? null : List.copyOf(restrictions);
        this.rights = rights;
        this.scenes = scenes == null ? null : List.copyOf(scenes);
        this.status = status;
        this.subTitles = subTitles == null ? null : List.copyOf(subTitles);
        this.text = text == null ? null : List.copyOf(text);
        this.title = title;
        this.titleType = titleType;
    }

    /**
     * Creates a new instance of PostMediaMetadata from a Metadata instance.
     *
     * @param metadata The Metadata instance to convert from.
     * @return A PostMediaMetadata object with data from the Metadata instance.
     */
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

    /**
     * Converts the PostMediaMetadata object to a Metadata instance.
     *
     * @return A Metadata instance representing the PostMediaMetadata data.
     */
    @SuppressWarnings("unused")
    public final Metadata toModule() {
        Metadata metadata = new Metadata();
        metadata.setThumbnail(convertArray(this::getThumbnails, PostMediaThumbnail::toModule, Thumbnail.class));
        metadata.setCategories(convertArray(this::getCategories, PostMediaCategory::toModule, Category.class));
        metadata.setCopyright(copyright);
        metadata.setCopyrightUrl(copyrightUrl);
        metadata.setDescription(desc);
        metadata.setDescriptionType(descType);
        metadata.setBackLinks(backLinks);
        metadata.setComments(comments);
        metadata.setCommunity(ofNullable(community).map(PostMediaCommunity::toModule).orElse(null));
        metadata.setCredits(convertArray(this::getCredits, PostMediaCredit::toModule, Credit.class));
        metadata.setHash(ofNullable(hash).map(PostMediaHash::toModule).orElse(null));
        metadata.setKeywords(keywords);
        metadata.setLicenses(convertArray(this::getLicenses, PostMediaLicense::toModule, License.class));
        metadata.setLocations(convertArray(this::getLocations, PostMediaLocation::toModule, Location.class));
        metadata.setPeerLinks(convertArray(this::getPeerLinks, PostMediaPeerLink::toModule, PeerLink.class));
        metadata.setPrices(convertArray(this::getPrices, PostMediaPrice::toModule, Price.class));
        metadata.setRatings(convertArray(this::getRatings, PostMediaRating::toModule, Rating.class));
        metadata.setResponses(responses);
        metadata.setRestrictions(convertArray(this::getRestrictions, PostMediaRestriction::toModule, Restriction.class));
        metadata.setRights(ofNullable(rights).map(Metadata.RightsStatus::valueOf).orElse(null)); // TODO: safety
        metadata.setScenes(convertArray(this::getScenes, PostMediaScene::toModule, Scene.class));
        metadata.setStatus(ofNullable(status).map(PostMediaStatus::toModule).orElse(null));
        metadata.setSubTitles(convertArray(this::getSubTitles, PostMediaSubTitle::toModule, SubTitle.class));
        metadata.setText(convertArray(this::getText, PostMediaText::toModule, Text.class));
        metadata.setTitle(title);
        metadata.setTitleType(titleType);

        return metadata;
    }
}
