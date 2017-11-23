package dao;

import entity.Place;

/**
 * Created by p on 2017/11/23.
 */
public interface PlaceMapper {
    void insert(Place place);
    Place selectLastOne(String phone);
}
