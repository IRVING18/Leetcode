package com.leetcode.api;

import java.util.Map;

public class CityData {
    private Map<String, String[]> citysData;

    public static void main(String[] args) {
//        String json = "{\n" +
//                "  \"citysData\": {\n" +
//                "    \"北京市\": [\"北京市\"],\n" +
//                "    \"天津市\": [\"天津市\"],\n" +
//                "    \"河北省\": [\n" +
//                "      \"石家庄市\",\n" +
//                "      \"唐山市\",\n" +
//                "      \"秦皇岛市\",\n" +
//                "      \"邯郸市\",\n" +
//                "      \"邢台市\",\n" +
//                "      \"保定市\",\n" +
//                "      \"张家口市\",\n" +
//                "      \"承德市\",\n" +
//                "      \"沧州市\",\n" +
//                "      \"廊坊市\",\n" +
//                "      \"衡水市\"\n" +
//                "    ]\n" +
//                "  }\n" +
//                "}";
//
//        Gson gson = new Gson();
//        Type type = new TypeToken<Map<String, String[]>>() {}.getType();
//        Map<String, String[]> citysData = gson.fromJson(json, type);
//
//        // 遍历map并输出
//        for (Map.Entry<String, String[]> entry : citysData.entrySet()) {
//            String province = entry.getKey();
//            String[] cities = entry.getValue();
//            System.out.println(province + ": " + String.join(", ", cities));
//        }
    }
}