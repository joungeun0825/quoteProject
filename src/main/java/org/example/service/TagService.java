package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.QuoteTag;
import org.example.domain.Tag;
import org.example.dto.AddTagRequest;
import org.example.repository.QuoteTagRepository;
import org.example.repository.TagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TagService {
    private final TagRepository tagRepository;
    public void save(AddTagRequest request){
        tagRepository.save(request.toEntity());
    }
    @Transactional(readOnly = true)
    public List<Tag> selectTags(List<Long> tagId){
        List<Tag> tags = new ArrayList<>();
        for (Long ti : tagId) {
            Tag tag = tagRepository.selectTag(ti).orElseThrow(() -> new IllegalArgumentException("not found : " + ti));
            tags.add(tag);
        }
        return tags;
    }

    @Transactional(readOnly = true)
    public List<Tag> selectAllTags(){
        return tagRepository.findAllTags();
    }
}
