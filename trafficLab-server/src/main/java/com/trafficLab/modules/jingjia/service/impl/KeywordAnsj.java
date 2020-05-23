package com.trafficLab.modules.jingjia.service.impl;//package com.trafficLab.modules.jingjia.service.impl;
//
//import com.trafficLab.modules.jingjia.entity.JjGetInfoEntity;
//import lombok.extern.slf4j.Slf4j;
//import org.ansj.domain.Result;
//import org.ansj.domain.Term;
//import org.ansj.splitWord.analysis.ToAnalysis;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.stereotype.Service;
//
//import java.util.*;
//
///**
// * author jinsq
// * 分词
// * @date 2019/6/4 15:47
// */
//@Slf4j
//@Service
//public class KeywordAnsj {
//
//    private String appendWord(List<JjGetInfoEntity> jjGetInfoEntityList){
//        StringBuilder sb = new StringBuilder();
//        for(JjGetInfoEntity jjGetInfoEntity : jjGetInfoEntityList){
//            String keyword = jjGetInfoEntity.getKeyword();
//            if(StringUtils.isNotEmpty(keyword)){
//                sb.append(keyword);
//            }
//        }
//        return sb.toString();
//    }
//
//    public Map<String,List<String>> parseKeyWord(List<JjGetInfoEntity> jjGetInfoEntityList){
//        String textKeyword = appendWord(jjGetInfoEntityList);
//        Map<String,List<String>> mapResult = new HashMap<>();
//        Map<String, Integer> map = new TreeMap<>();
//
//        Result result = ToAnalysis.parse(textKeyword);
//
//        for(Term term : result.getTerms()){
//            String splitKeyword = term.getName();
//
//            if(map.containsKey(splitKeyword)){
//                int value = map.get(splitKeyword);
//                map.replace(splitKeyword,value+1);
//            }else{
//                map.put(splitKeyword,1);
//            }
//        }
//        List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
//        //然后通过比较器来实现
//        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
//            @Override
//            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
//                return o2.getValue().compareTo(o1.getValue());
//            }
//        });
//        List<String> keywordNameList = new ArrayList<>();
//        List<String> keywordValueList = new ArrayList<>();
//        for(Map.Entry<String,Integer> mapping : list){
//            if(keywordNameList.size() >= 20)
//                break;
//            keywordNameList.add(mapping.getKey());
//            keywordValueList.add(mapping.getValue()+"");
//        }
//        mapResult.put("keywordName",keywordNameList);
//        mapResult.put("keywordValue",keywordValueList);
//        return mapResult;
//    }
//}
