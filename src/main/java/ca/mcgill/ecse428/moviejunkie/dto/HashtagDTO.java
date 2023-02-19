package ca.mcgill.ecse428.moviejunkie.dto;
import ca.mcgill.ecse428.moviejunkie.model.Hashtag;

import java.util.HashSet;
import java.util.Set;

public class HashtagDTO {

    public String text;

    public HashtagDTO() {}
    public HashtagDTO(String text) {
        this.text = text;
    }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public static HashtagDTO convertToDTO(Hashtag hashtag) {
        if (hashtag == null) {
            throw new IllegalArgumentException("Cannot create hashtag.");
        }
        HashtagDTO hashtagDTO = new HashtagDTO(hashtag.getText());
        return hashtagDTO;
    }
    public static Set<HashtagDTO> convertToDTO(Set<Hashtag> hashtags) {
        Set<HashtagDTO> hashtagDTOList = new HashSet<HashtagDTO>();
        for (Hashtag hashtag : hashtags) {
            hashtagDTOList.add(convertToDTO(hashtag));
        }
        return hashtagDTOList;
    }
}