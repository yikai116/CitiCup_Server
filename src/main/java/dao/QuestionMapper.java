package dao;

import entity.Question;

/**
 * Created by p on 2017/11/19.
 */
public interface QuestionMapper {
    Question get(String phone);
    void insert(Question question);
}
