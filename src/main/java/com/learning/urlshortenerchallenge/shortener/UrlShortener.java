package com.learning.urlshortenerchallenge.shortener;

import com.learning.urlshortenerchallenge.model.Url;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Random;

@Data
@Component
public class UrlShortener {

    private final String CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private final String PREFIX = "https://shortned.url/";

    private Random rand;

    public UrlShortener() {
        this.rand = new Random();
    }

    public Url shortenUrl(String rawUrl) {
        // Defining the shortened url code length
        int length = rand.ints(5,11)
                .findAny()
                .getAsInt();

        StringBuilder sb = new StringBuilder(PREFIX);
        char[] charArray = CHARS.toCharArray();
        for(int i = 0; i < length; i++) {
            sb.append(charArray[rand.nextInt(CHARS.length())]);
        }

        return new Url(null, rawUrl, sb.toString(), null);
    }

}
