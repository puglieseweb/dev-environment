package com.puglieseweb.app.sample.kafka_stream.broker.stream.promotion;

import com.puglieseweb.app.sample.kafka_stream.broker.message.PromotionMessage;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.context.annotation.Bean;

import com.puglieseweb.app.sample.kafka_stream.broker.serde.PromotionSerde;

//@Configuration
public class PromotionUppercaseCustomJsonStream {

	@Bean
	public KStream<String, PromotionMessage> kstreamPromotionUppercase(StreamsBuilder builder) {
		var stringSerde = Serdes.String();
		var jsonSerde = new PromotionSerde();

		KStream<String, PromotionMessage> sourceStream = builder.stream("t.commodity.promotion",
				Consumed.with(stringSerde, jsonSerde));
		KStream<String, PromotionMessage> uppercaseStream = sourceStream.mapValues(this::uppercasePromotionCode);

		uppercaseStream.to("t.commodity.promotion-uppercase", Produced.with(stringSerde, jsonSerde));

		sourceStream.print(Printed.<String, PromotionMessage>toSysOut().withLabel("Custom JSON Serde Original Stream"));
		uppercaseStream
				.print(Printed.<String, PromotionMessage>toSysOut().withLabel("Custom JSON Serde Uppercase Stream"));

		return sourceStream;
	}

	private PromotionMessage uppercasePromotionCode(PromotionMessage message) {
		return new PromotionMessage(message.getPromotionCode().toUpperCase());
	}

}