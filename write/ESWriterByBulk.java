package com.atguigu.write;

import com.atguigu.bean.Stu;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Bulk;
import io.searchbox.core.Index;

import java.io.IOException;

public class ESWriterByBulk {

    public static void main(String[] args) throws IOException {

        //一、创建ES客户端对象
        //1.1 创建ES客户端的工厂对象
        JestClientFactory jestClientFactory = new JestClientFactory();

        //1.2 创建配置信息
        HttpClientConfig config = new HttpClientConfig.Builder("http://hadoop102:9200").build();
        jestClientFactory.setHttpClientConfig(config);

        //1.3 获取客户端对象
        JestClient jestClient = jestClientFactory.getObject();

        //二、批量写入
        //2.1 准备数据
        Stu stu1 = new Stu("008", "麻瓜");
        Stu stu2 = new Stu("009", "海格");

        //2.2 创建Bulk.Builder对象
        Bulk.Builder builder = new Bulk.Builder();

        //2.3 创建Index对象
        Index index1 = new Index.Builder(stu1).id("1008").build();
        Index index2 = new Index.Builder(stu2).id("1009").build();

        //2.4 赋值默认的索引名称及类型名
        builder.defaultIndex("stu_temp_01");
        builder.defaultType("_doc");

        //2.5 添加Index之Bulk
        builder.addAction(index1);
        builder.addAction(index2);

        //2.6 真正构建Bulk对象
        Bulk bulk = builder.build();

        //2.7 执行批量写入数据操作
        jestClient.execute(bulk);

        //3.关闭连接
        jestClient.shutdownClient();

    }
}
