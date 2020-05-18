package com.atguigu.write;

import com.atguigu.bean.Stu;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Index;

import java.io.IOException;

public class ESWriter {

    public static void main(String[] args) throws IOException {

        //一、创建ES客户端对象
        //1.1 创建ES客户端的工厂对象
        JestClientFactory jestClientFactory = new JestClientFactory();

        //1.2 创建配置信息
        HttpClientConfig config = new HttpClientConfig.Builder("http://hadoop102:9200").build();
        jestClientFactory.setHttpClientConfig(config);

        //1.3 获取客户端对象
        JestClient jestClient = jestClientFactory.getObject();

        //二、写入数据
        //2.1 创建Action对象 --> Index
        Stu stu = new Stu("004", "少爷");
        Index index = new Index.Builder(stu)
                .index("stu_temp_01")
                .type("_doc")
                .id("1004")
                .build();

        //2.2 执行写入数据操作
        jestClient.execute(index);

        //三、关闭资源
        jestClient.shutdownClient();

    }

}
