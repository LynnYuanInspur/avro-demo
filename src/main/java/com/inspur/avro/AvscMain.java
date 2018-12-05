package com.inspur.avro;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;

import java.io.File;
import java.io.IOException;

/**
 * Created by yuanxiaolong on 2018/10/1.
 */
public class AvscMain {

    /**
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {

        Schema schema = new Schema.Parser().parse(new File("src/main/avro/user.avsc"));

        //Using this schema, let's create some users.
        GenericRecord user1 = new GenericData.Record(schema);
        user1.put("name", "Alyssa");
        user1.put("favorite_number", 256);

        System.out.println(user1);

        // Leave favorite color null
        GenericRecord user2 = new GenericData.Record(schema);
        user2.put("name", "Ben");
        user2.put("favorite_number", 7);
        user2.put("favorite_color", "red");

        System.out.println(user2);
    }
}
