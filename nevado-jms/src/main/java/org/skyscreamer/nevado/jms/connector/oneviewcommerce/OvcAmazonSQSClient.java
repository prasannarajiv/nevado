package org.skyscreamer.nevado.jms.connector.oneviewcommerce;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.handlers.HandlerChainFactory;
import com.amazonaws.services.sqs.AmazonSQSClient;

public class OvcAmazonSQSClient extends AmazonSQSClient {

	public OvcAmazonSQSClient(AWSCredentials awsCredentials,
	                       ClientConfiguration clientConfiguration) {
		AmazonWebServiceClient awsClient = new AmazonWebServiceClient(clientConfiguration);
		super.awsCredentialsProvider = new StaticCredentialsProvider(awsCredentials);
		init();
	}

	/*public AmazonSQSClient(AWSCredentials awsCredentials, ClientConfiguration clientConfiguration) {
        	super(clientConfiguration);
        	this.awsCredentialsProvider = new StaticCredentialsProvider(awsCredentials);
        	init();
    	}*/

	private void init() {
		exceptionUnmarshallers.add(new QueueDeletedRecentlyExceptionUnmarshaller());
	        exceptionUnmarshallers.add(new QueueNameExistsExceptionUnmarshaller());
	        exceptionUnmarshallers.add(new EmptyBatchRequestExceptionUnmarshaller());
	        exceptionUnmarshallers.add(new UnsupportedOperationExceptionUnmarshaller());
	        exceptionUnmarshallers.add(new InvalidMessageContentsExceptionUnmarshaller());
	        exceptionUnmarshallers.add(new InvalidBatchEntryIdExceptionUnmarshaller());
	        exceptionUnmarshallers.add(new OverLimitExceptionUnmarshaller());
	        exceptionUnmarshallers.add(new TooManyEntriesInBatchRequestExceptionUnmarshaller());
	        exceptionUnmarshallers.add(new InvalidIdFormatExceptionUnmarshaller());
	        exceptionUnmarshallers.add(new QueueDoesNotExistExceptionUnmarshaller());
	        exceptionUnmarshallers.add(new InvalidAttributeNameExceptionUnmarshaller());
	        exceptionUnmarshallers.add(new BatchRequestTooLongExceptionUnmarshaller());
	        exceptionUnmarshallers.add(new ReceiptHandleIsInvalidExceptionUnmarshaller());
	        exceptionUnmarshallers.add(new MessageNotInflightExceptionUnmarshaller());
	        exceptionUnmarshallers.add(new PurgeQueueInProgressExceptionUnmarshaller());
	        exceptionUnmarshallers.add(new BatchEntryIdsNotDistinctExceptionUnmarshaller());
	        
	        exceptionUnmarshallers.add(new StandardErrorUnmarshaller());
	        
	        // calling this.setEndPoint(...) will also modify the signer accordingly
	        super.setEndpoint("sqs.us-east-1.amazonaws.com");
	
		HandlerChainFactory chainFactory = new HandlerChainFactory();
		requestHandler2s.addAll(chainFactory.newRequestHandlerChain(
				"/com/oneviewcommerce/shade/nevado/org/skyscreamer/nevado/jms/resource/request.handlers"));
	}

}
