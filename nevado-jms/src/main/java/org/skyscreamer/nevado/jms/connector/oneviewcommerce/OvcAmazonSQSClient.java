package org.skyscreamer.nevado.jms.connector.oneviewcommerce;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.handlers.HandlerChainFactory;
import com.amazonaws.services.sqs.AmazonSQSClient;

public class OvcAmazonSQSClient extends AmazonSQSClient {

	public OvcAmazonSQSClient(AWSCredentials awsCredentials,
	                       ClientConfiguration clientConfiguration) {
		super(awsCredentials, clientConfiguration);
		init();
	}

	private void init() {
		HandlerChainFactory chainFactory = new HandlerChainFactory();
		requestHandler2s.addAll(chainFactory.newRequestHandlerChain(
				"/com/oneviewcommerce/shade/nevado/org/skyscreamer/nevado/jms/resource/request.handlers"));
	}

}
