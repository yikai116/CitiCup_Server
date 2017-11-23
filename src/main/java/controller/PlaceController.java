package controller;

import dao.PlaceMapper;
import dto.Location;
import entity.Place;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by p on 2017/11/23.
 */
@RestController
@RequestMapping(value = "/set")
@CrossOrigin
public class PlaceController {
    @Autowired
    private PlaceMapper placeMapper;

    @RequestMapping(value = "/savePlace", method = RequestMethod.POST)
    public void savePlace(@RequestBody Location location, HttpServletRequest request) throws IOException {
        if (location != null) {
            String url = "http://api.map.baidu.com/geocoder/v2/?" +
                    "ak=NFYfR3g8z5PCD5L4tHyhGHWCx0YzGbvN&callback=renderReverse&output=json&pois=1"
                    + "&location=" + location.getLatitude() + "," + location.getLongitude();
            Connection.Response response = Jsoup.connect(url)
                    .ignoreContentType(true)
                    .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                    .execute();
            String res = response.body();
            res = res.substring(res.indexOf("{"),res.lastIndexOf("}"));
            JSONObject json = JSONObject.fromObject(res).getJSONObject("result");

            String phone = String.valueOf(request.getAttribute("phone"));
            Place place = new Place();
            place.setPhone(phone);
            place.setSite(json.getString("formatted_address"));
            place.setTime(new Time(new Date().getTime()));
            ArrayList<String> list = new ArrayList<>();
            JSONArray array = json.getJSONArray("pois");
            for (Object object : array){
                JSONObject js = (JSONObject)object;
                list.add(js.getString("poiType"));
            }
            place.setType(StringUtils.join(list.toArray(), ","));
            Place temp = placeMapper.selectLastOne(phone);
            place.setId(temp == null?1:temp.getId());
            return;
        }
    }
}
