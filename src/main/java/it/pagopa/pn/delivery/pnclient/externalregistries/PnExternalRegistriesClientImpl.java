package it.pagopa.pn.delivery.pnclient.externalregistries;

import it.pagopa.pn.delivery.PnDeliveryConfigs;
import it.pagopa.pn.delivery.generated.openapi.clients.externalregistries.ApiClient;
import it.pagopa.pn.delivery.generated.openapi.clients.externalregistries.api.PaymentInfoApi;
import it.pagopa.pn.delivery.generated.openapi.clients.externalregistries.model.PaymentInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class PnExternalRegistriesClientImpl {

    private final PaymentInfoApi paymentInfoApi;

    public PnExternalRegistriesClientImpl(@Qualifier("withTracing") RestTemplate restTemplate, PnDeliveryConfigs cfg) {
        ApiClient newApiClient = new ApiClient( restTemplate );
        newApiClient.setBasePath( cfg.getExternalRegistriesBaseUrl() );
        this.paymentInfoApi = new PaymentInfoApi( newApiClient );
    }

    public PaymentInfo getPaymentInfo( String paTaxId, String noticeNumber ) {
        return paymentInfoApi.getPaymentInfo( paTaxId, noticeNumber );
    }
}
