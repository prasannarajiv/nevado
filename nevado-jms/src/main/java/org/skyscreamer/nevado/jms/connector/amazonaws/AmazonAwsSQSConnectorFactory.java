package org.skyscreamer.nevado.jms.connector.amazonaws;

import org.apache.commons.lang.StringUtils;
import org.skyscreamer.nevado.jms.connector.AbstractSQSConnectorFactory;

/**
 * Connectory factory for Amazon AWS connector.
 *
 * @author Carter Page <carter@skyscreamer.org>
 */
public class AmazonAwsSQSConnectorFactory extends AbstractSQSConnectorFactory {
    protected boolean _useAsyncSend = false;
    
    @Override
    public AmazonAwsSQSConnector getInstance(String awsAccessKey, String awsSecretKey, String awsSQSEndpoint, String awsSNSEndpoint) {
        AmazonAwsSQSConnector amazonAwsSQSConnector = createConnector(awsAccessKey, awsSecretKey);
        if (StringUtils.isNotEmpty(awsSQSEndpoint)) {
            amazonAwsSQSConnector.getAmazonSQS().setEndpoint(awsSQSEndpoint);
        }
        if (StringUtils.isNotEmpty(awsSNSEndpoint)) {
            amazonAwsSQSConnector.getAmazonSNS().setEndpoint(awsSNSEndpoint);
        }
        return amazonAwsSQSConnector;
    }

    protected AmazonAwsSQSConnector createConnector(String awsAccessKey, String awsSecretKey) {
//        try {
//            return new AmazonAwsSQSConnector(awsAccessKey, awsSecretKey, _isSecure, _receiveCheckIntervalMs, _useAsyncSend);
//        } catch (AmazonClientException e) {
//            try {
//                OvcNewClassloader onc = new OvcNewClassloader(Thread.currentThread().getContextClassLoader());
//                //Class aClass = onc.loadClass(e.getCause().getMessage());
//                onc.invokeOvcShadedClass(e.getCause().getMessage());
//            } catch (Exception exc) {
//                exc.printStackTrace();
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return new AmazonAwsSQSConnector(awsAccessKey, awsSecretKey, _isSecure, _receiveCheckIntervalMs, _useAsyncSend);
    }

    public void setUseAsyncSend(boolean useAsyncSend) {
        _useAsyncSend = useAsyncSend;
    }
    
    public boolean isUseAsyncSend() {
        return _useAsyncSend;
    }
}
