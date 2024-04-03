package com.pavlob;

import org.springframework.http.HttpHeaders;
import org.springframework.kafka.support.KafkaHeaders;

public class Constant {

    public static final String BEARER_PREFIX = "Bearer ";

    public static class Kafka {
        public static final String CARGO_REPORT_TOPIC = "cargoReportsTopic";
        public static final String KAFKA_AUTH_HEADER = KafkaHeaders.PREFIX + HttpHeaders.AUTHORIZATION;
    }
}
