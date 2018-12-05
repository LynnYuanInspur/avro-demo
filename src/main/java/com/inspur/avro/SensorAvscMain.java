package com.inspur.avro;

import com.google.common.collect.Maps;
import com.google.protobuf.Descriptors;
import com.google.protobuf.DynamicMessage;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by yuanxiaolong on 2018/10/3.
 */
public class SensorAvscMain {

    public static GenericRecord newRecord(Schema schema, Map<String, Object> fvs){
        GenericRecord record = new GenericData.Record(schema);
        for(Map.Entry<String, Object> entry: fvs.entrySet()){
            if(entry.getValue() instanceof Map){
                Schema.Field field = record.getSchema().getField(entry.getKey());
                Map<String, Object> value = (Map<String, Object> )entry.getValue();
                record.put(entry.getKey(), newRecord(field.schema(), value));
            }else {
                record.put(entry.getKey(), entry.getValue());
            }
        }

        return record;
    }
    /**
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {
        System.out.println("1. initialize field-values map...");
        Map<String, Object> fvs = Maps.newHashMap();

        fvs.put("client", "client-40");
        fvs.put("timestamp", 1538294941336L);

        Map<String, Object> reported = Maps.newHashMap();
        reported.put("temperature", 97.83d);
        reported.put("humity", 23.3d);
        Map<String, Object> state = Maps.newHashMap();
        state.put("reported", reported);
        fvs.put("state", state);

        System.out.println("2. new Message...");
        Schema schema = new Schema.Parser().parse(new File("src/main/avro/sensor.avsc"));

        GenericRecord sensor = newRecord(schema, fvs);
        System.out.println(sensor);
    }
}
