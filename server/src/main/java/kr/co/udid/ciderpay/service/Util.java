package kr.co.udid.ciderpay.service;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class Util {
    public String makeRandomStr() {
        return IntStream.range (0, 6).mapToObj ((f) -> makeShuffleStr()).collect(Collectors.joining(""));
    }

    public String makeShuffleStr() {
        List<String> makeShuffle = Arrays.asList(
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "n",
                "m", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "0");

        Collections.shuffle(makeShuffle);

        return makeShuffle.get(0);
    }

    public String makeRandomNum() {
        return IntStream.range (0, 4).mapToObj ((f) -> makeShuffleNum()).collect(Collectors.joining(""));
    }

    public String makeShuffleNum() {
        List<String> makeShuffle = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "0");

        Collections.shuffle(makeShuffle);

        return makeShuffle.get(0);
    }

    public boolean isEmptyStr (String str)
    {
        if (str == null)
            return true;

        if (str.equals (""))
            return true;

        if (str.equals ("null"))
            return true;

        return false;
    }

}
