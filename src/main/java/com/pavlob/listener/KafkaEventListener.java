package com.pavlob.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pavlob.aspect.ValidateKafkaHeader;
import com.pavlob.cargotracking.dto.CargoDto;
import com.pavlob.cargotracking.facade.CargoTrackingFacade;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.pavlob.Constant.Kafka.CARGO_REPORT_TOPIC;
import static com.pavlob.Constant.Kafka.KAFKA_AUTH_HEADER;

@Component
@Slf4j
public class KafkaEventListener {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CargoTrackingFacade cargoTrackingFacade;

    @KafkaListener(topics = CARGO_REPORT_TOPIC, groupId = "${kafka.groupId}")
    @ValidateKafkaHeader
    public void listenCargoReportTopic(@Header(value = KAFKA_AUTH_HEADER, defaultValue = StringUtils.EMPTY) String token,
                                       @Payload String message) throws JsonProcessingException {
        final CargoDto cargoDto = objectMapper.readValue(message, CargoDto.class);

        if (StringUtils.isBlank(cargoDto.getId())) {
            log.debug("create cargo info flow");
            cargoTrackingFacade.createCargo(cargoDto);
        } else {
            log.debug("update cargo info flow");
            cargoTrackingFacade.updateCargo(cargoDto);
        }
    }
}
