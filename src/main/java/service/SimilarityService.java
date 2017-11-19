package service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by p on 2017/11/15.
 */
@Service
@Transactional
public interface SimilarityService {
    void initInsuTest(ArrayList<String> list) throws NoSuchFieldException, IllegalAccessException, IOException;
}
