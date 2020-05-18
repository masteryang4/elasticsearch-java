package com.atguigu.read;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ESReader {

    public static void main(String[] args) throws IOException {

        //1.获取客户端对象
        //1.1 创建ES客户端的工厂对象
        JestClientFactory jestClientFactory = new JestClientFactory();

        //1.2 创建配置信息
        HttpClientConfig config = new HttpClientConfig.Builder("http://hadoop102:9200").build();
        jestClientFactory.setHttpClientConfig(config);

        //1.3 获取客户端对象
        JestClient jestClient = jestClientFactory.getObject();

        //2.读取数据
        //2.0 创建查询条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.filter(new TermQueryBuilder("class_id", "190218"));
        searchSourceBuilder.query(boolQueryBuilder);

        searchSourceBuilder.from(0);
        searchSourceBuilder.size(2);

        //2.1 创建Search对象
        Search search = new Search.Builder(searchSourceBuilder.toString())
                .addIndex("student")
                .addType("_doc")
                .build();

        //2.2 执行查询操作
        SearchResult searchResult = jestClient.execute(search);

        //2.3 解析searchResult
        System.out.println("查询数据" + searchResult.getTotal() + "条！");
        List<SearchResult.Hit<Map, Void>> hits = searchResult.getHits(Map.class);
        for (SearchResult.Hit<Map, Void> hit : hits) {
            Map source = hit.source;
            for (Object key : source.keySet()) {
                System.out.println(hit.id + ":" + key.toString() + ":" + source.get(key).toString());
            }
            System.out.println("*************");
        }

        //3.关闭资源
        jestClient.shutdownClient();
    }

}
