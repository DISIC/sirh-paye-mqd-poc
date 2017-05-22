package com.sirh.mqd.commons.utils.abstracts;

//import org.apache.cxf.configuration.jsse.TLSClientParameters;
//import org.apache.cxf.frontend.ClientProxy;
//import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
//import org.apache.cxf.transport.http.HTTPConduit;
//import org.apache.cxf.transports.http.configuration.ConnectionType;
//import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
//import com.sirh.mqd.commons.utils.gzip.GZIPFeature;
/**
 * Class abstraite permet a tous les clients de se connecter aux differents web
 * services
 *
 * @author alexandre
 * @param <I>
 *            Web Service Interface
 */
public abstract class WebServiceConnexionAbstract<I> {

	// private static final String HTTPS = "https";

	/**
	 * Map de connexions qui lie une adresse à sa connexion. Cette map permet de
	 * garder en mémoire les connexions qui prennent un peu de temps à être
	 * instanciées
	 */
	// private final Map<String, I> adressToConnexion = new HashMap<>();

	/**
	 * Interface du web service
	 *
	 * @return
	 */
	public abstract Class<I> getIFace();

	public WebServiceConnexionAbstract() {
		super();
	}

	/**
	 *
	 * @param adresse
	 *            L'adresse complète http://{ip}:{port}/{service}
	 *
	 * @return return un proxy connecté au web service demandé.
	 */
	/*
	 * @SuppressWarnings("unchecked") public synchronized I getConnexion(final
	 * String adresse, final boolean compressPermitted, final String
	 * timeoutEchange) { I clientProxy = adressToConnexion.get(adresse); if
	 * (clientProxy == null) { final JaxWsProxyFactoryBean factoryBean = new
	 * JaxWsProxyFactoryBean(); factoryBean.setServiceClass(getIFace());
	 * factoryBean.setAddress(adresse); clientProxy = (I) factoryBean.create();
	 *
	 * final long timeout = timeoutEchange != null ?
	 * Long.parseLong(timeoutEchange, 10) : 2500L; final long receiveTimeout =
	 * timeoutEchange != null ? Long.parseLong(timeoutEchange, 10) : 2500L;
	 *
	 * // configuration initiale lue une seule fois à la création du proxy final
	 * org.apache.cxf.endpoint.Client client =
	 * configureGZIPOptions(compressPermitted, clientProxy); final HTTPConduit
	 * httpConduit = (HTTPConduit) client.getConduit();
	 *
	 * final HTTPClientPolicy policy = new HTTPClientPolicy();
	 * policy.setConnectionTimeout(timeout);
	 * policy.setReceiveTimeout(receiveTimeout);
	 * policy.setConnection(ConnectionType.KEEP_ALIVE);
	 * httpConduit.setClient(policy);
	 *
	 * configureSSL(adresse, httpConduit);
	 *
	 * adressToConnexion.put(adresse, clientProxy); } else { // reload compress
	 * zip configuration for each time proxy client
	 * configureGZIPOptions(compressPermitted, clientProxy); } return
	 * clientProxy;
	 *
	 * }
	 */

	/**
	 * @param compressPermitted
	 * @param clientProxy
	 * @return
	 */
	/*
	 * private org.apache.cxf.endpoint.Client configureGZIPOptions(final boolean
	 * compressPermitted, final I clientProxy) { // compression des
	 * flux
	 * org.apache.cxf.endpoint.Client client =
	 * ClientProxy.getClient(clientProxy);
	 *
	 * // remove all of the features from the list
	 * client.getEndpoint().getActiveFeatures().clear();
	 *
	 * final GZIPFeature feature = new GZIPFeature();
	 * feature.initialize(client.getEndpoint(), client.getBus(),
	 * compressPermitted);
	 * client.getEndpoint().getActiveFeatures().add(feature);
	 *
	 * return client; }
	 */

	/**
	 *
	 * @param adresse
	 *            L'adresse complète http://{ip}:{port}/{service}
	 *
	 * @return return un proxy connecté au web service demandé.
	 */
	/*
	 * @SuppressWarnings("unchecked") public synchronized I
	 * getConnexionWithRetry(final String adresse, final boolean
	 * compressPermitted, final int nbRetry, final String timeoutEchange) throws
	 * ConnectException, javax.xml.ws.WebServiceException { I clientProxy =
	 * adressToConnexion.get(adresse); if (clientProxy == null) { final
	 * JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
	 * factoryBean.setServiceClass(getIFace()); factoryBean.setAddress(adresse);
	 * clientProxy = (I) factoryBean.create(); final long timeout =
	 * timeoutEchange != null ? Long.parseLong(timeoutEchange, 10) : 2500L;
	 * final long receiveTimeout = timeoutEchange != null ?
	 * Long.parseLong(timeoutEchange, 10) : 2500L;
	 *
	 * // configuration initiale lue une seule fois à la création du proxy final
	 * org.apache.cxf.endpoint.Client client =
	 * configureGZIPOptions(compressPermitted, clientProxy); final HTTPConduit
	 * httpConduit = (HTTPConduit) client.getConduit();
	 *
	 * final HTTPClientPolicy policy = new HTTPClientPolicy();
	 * policy.setConnectionTimeout(timeout);
	 * policy.setReceiveTimeout(receiveTimeout);
	 * policy.setMaxRetransmits(nbRetry);
	 * policy.setConnection(ConnectionType.KEEP_ALIVE);
	 * httpConduit.setClient(policy);
	 *
	 * configureSSL(adresse, httpConduit);
	 *
	 * adressToConnexion.put(adresse, clientProxy); } else { // reload compress
	 * zip configuration for each time proxy client
	 * configureGZIPOptions(compressPermitted, clientProxy); } return
	 * clientProxy; }
	 */

	/**
	 * Configure une connection SSL avec le destinatire si besoin. Si l'url
	 * commence par HTTPS alors on établit une communication sécurisé avec le
	 * serveur Le certificat du serveur doit être signé par une authoritée de
	 * confiance ou alors le certificat doit etre ajouté dans le keystore de la
	 * JVM
	 *
	 * @param adresse
	 * @param httpConduit
	 */
	/*
	 * private void configureSSL(final String adresse, final HTTPConduit
	 * httpConduit) { if (adresse.startsWith(HTTPS)) { final TLSClientParameters
	 * tlsParams = new TLSClientParameters(); tlsParams.setDisableCNCheck(true);
	 * httpConduit.setTlsClientParameters(tlsParams); } }
	 */

}
