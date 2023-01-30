package com.lostsidewalk.buffy.post;

import com.rometools.modules.itunes.EntryInformationImpl;
import com.rometools.modules.itunes.FeedInformationImpl;
import com.rometools.modules.itunes.ITunes;
import lombok.Data;

import java.io.Serializable;
import java.net.URL;

@Data
public class PostITunes implements Serializable {

    public static long serialVersionUID = 983403122303L;

    String author;
    boolean isBlock; // wtf does this mean
    boolean isExplicit;
    Boolean isExplicitNullable; // again, sounds stupid
    URL image;
    String imageUri;
    String[] keywords;
    String subTitle;
    String summary;

    PostITunes(String author, boolean isBlock, boolean isExplicit, Boolean isExplicitNullable, URL image, String imageUri, String[] keywords, String subTitle, String summary) {
        this.author = author;
        this.isBlock = isBlock;
        this.isExplicit = isExplicit;
        this.isExplicitNullable = isExplicitNullable;
        this.image = image;
        this.imageUri = imageUri;
        this.keywords = keywords;
        this.subTitle = subTitle;
        this.summary = summary;
    }

    public static PostITunes from(ITunes iTunes) {
        String author = iTunes.getAuthor();
        boolean isBlock = iTunes.getBlock(); // wtf does this mean
        boolean isExplicit = iTunes.getExplicit();
        Boolean isExplicitNullable = iTunes.getExplicitNullable(); // again, sounds stupid
        URL image = iTunes.getImage();
        String imageUri = iTunes.getImageUri();
        String[] keywords = iTunes.getKeywords();
        String subTitle = iTunes.getSubtitle();
        String summary = iTunes.getSummary();

        return new PostITunes(
                author,
                isBlock,
                isExplicit,
                isExplicitNullable,
                image,
                imageUri,
                keywords,
                subTitle,
                summary
        );
    }

    @SuppressWarnings("unused")
    public ITunes toEntryModule() {
        EntryInformationImpl e = new EntryInformationImpl();
        e.setAuthor(author);
        e.setBlock(isBlock);
        e.setExplicit(isExplicit);
        e.setExplicitNullable(isExplicitNullable);
        e.setImage(image);
        e.setImageUri(imageUri);
        e.setKeywords(keywords);
        e.setSubtitle(subTitle);
        e.setSummary(summary);

        return e;
    }

    @SuppressWarnings("unused")
    public ITunes toFeedModule() {
        FeedInformationImpl f = new FeedInformationImpl();
        f.setAuthor(author);
        f.setBlock(isBlock);
        f.setExplicit(isExplicit);
        f.setExplicitNullable(isExplicitNullable);
        f.setImage(image);
        f.setImageUri(imageUri);
        f.setKeywords(keywords);
        f.setSubtitle(subTitle);
        f.setSummary(summary);

        return f;
    }
}
